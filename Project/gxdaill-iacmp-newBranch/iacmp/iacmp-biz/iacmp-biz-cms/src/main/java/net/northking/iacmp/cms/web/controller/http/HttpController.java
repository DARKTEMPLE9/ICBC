package net.northking.iacmp.cms.web.controller.http;

import ch.qos.logback.core.db.dialect.SybaseSqlAnywhereDialect;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import iacmp.biz.common.dao.mapper.cms.CmsBillMapper;
import iacmp.biz.common.dao.mapper.cms.PmsBatchMapper;
//import msoa.org.apache.commons.collections.map.HashedMap;
import net.northking.httpfiletrans.client.HttpUpload;
import net.northking.httpfiletrans.exception.HttpTransException;
import net.northking.iacmp.annotation.LimitKey;
import net.northking.iacmp.cms.adapter.RequestExchangeAdapter;
import net.northking.iacmp.cms.service.*;
import net.northking.iacmp.common.bean.domain.cms.*;
import net.northking.iacmp.common.bean.domain.cms.CmsImage;
import net.northking.iacmp.common.bean.dto.cms.CmsFileDTO;
import net.northking.iacmp.common.bean.feign.cms.AmsBatchRecordFeignClient;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.constant.PmsConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.execption.file.InvalidExtensionException;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.result.ResultCode;
import net.northking.iacmp.result.ResultInfo;
import net.northking.iacmp.result.TotalResult;
import net.northking.iacmp.system.service.ISysConfigService;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.SnowFlakeUtils;
import net.northking.iacmp.framework.util.UploadUtil;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.aes.AESUtils;
import net.northking.iacmp.utils.base64.Base64ToMultipartFileUtils;
import net.northking.iacmp.utils.file.FileUploadUtils;
import net.northking.iacmp.utils.rsa.RSAUtils;
import org.apache.commons.codec.binary.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description:上传与下载
 * @Author: weizhe.fan
 * @CreateDate: 2019/8/26
 */
@Controller
@RequestMapping("/file")
public class HttpController extends BaseController {

    /**
     * 允许上传文件类型
     */
    @Value("${afExtension}")
    private String[] allowedFileExtension;
    /**
     * 允许上传影像类型
     */
    @Value("${aiExtension}")
    private String[] allowedImageExtension;
    /**
     * 允许上传类型
     */
    @Value("${allowExtension}")
    private String[] allowExtension;

    @Autowired
    private ISysConfigService configService;

    /**
     * Hdfs存储地址
     */
    private String hdfsPath;
    /**
     * 临时zip
     */
    @Value("${ZIP_NAME}")
    private String ZIP_NAME;

    @Autowired
    private AmsBatchRecordFeignClient amsBatchRecordFeignClient;

    @Autowired
    private ICmsImageService cmsImageService;

    @Autowired
    private ICmsFileService cmsFileService;

    @Autowired
    private IPmsBatchService pmsBatchService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ICmsSystemService cmsSystemService;

    @Autowired
    private PmsBatchMapper pmsBatchMapper;

    @Autowired
    private CmsBillMapper cmsBillMapper;

    @Autowired
    private ICmsUserRoleService cmsUserRoleService;

    @Autowired
    private IHttpService httpService;

    /**
     * 统一服务接口
     */
    @PostMapping(value = "/acsService")
    @ResponseBody
    public AjaxResult uploadFiles(@RequestBody String data, HttpServletResponse response) throws IOException {
        logger.info("----------------进入统一服务接口----------------------");
        logger.info("----------------接收到的json信息：" + data + "------------");
        JSONParser jsonParser = new JSONParser(data);
        Map<String, Object> map = jsonParser.parseMap();
        JSONObject jsonObject = null;
        // 查询系统来源与鉴权码 (必传)
        if (null != map.get(CmsConstants.SYSCODE) && !map.get(CmsConstants.TRANCODE).equals(Constants.PREVIEW) && !map.get(CmsConstants.TRANCODE).equals(Constants.DOWNLOAD)) {
            CmsSystem cmsSystem = cmsSystemService.selectCmsSystemByCode(String.valueOf(map.get(CmsConstants.SYSCODE)));
            if (cmsSystem == null || !cmsSystem.getAuthentInfo().equals(String.valueOf(map.get(CmsConstants.AUTHCODE)))) {
                return AjaxResult.error("此系统尚未接入,请先接入系统再进行上传操作");
            }
            //适配器
            if (Constants.USE_ADAPTER.equals(cmsSystem.getUseAdapter())) {
                logger.info("---------------开始对请求报文进行适配------------------");
                RequestExchangeAdapter adapter = new RequestExchangeAdapter(cmsSystem.getSysCode());
                jsonObject = adapter.exchangeRequest(JSON.parseObject(data));
            } else {
                jsonObject = new JSONObject(map);
            }
        }
        JSONArray fileArray;
        // 1.档案上传交易码
        if (Constants.AMS_UPLOAD.equals(map.get(CmsConstants.TRANCODE))) {
            logger.info("----------------开始进行文件后缀名校验----------------------");
            // 后缀名校验
            JSONArray list = jsonObject.getJSONArray("fileList");
            List<String> fileNames = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String fileName = list.getJSONObject(i).getString(CmsConstants.FILENAME);
                String[] split = fileName.split("\\.");
                if (!Arrays.asList(allowExtension).contains(split[split.length - 1])) {
                    logger.error("文件:[ " + fileName + " ] 的后缀名不符合规定，请重新上传");
                    fileNames.add(fileName);
                }
            }
            if (fileNames.size() > 0) {
                return AjaxResult.error("文件:" + fileNames + " 的后缀名不符合规定，请重新上传");
            }
            fileArray = httpService.amsUpload(jsonObject, data);
            return AjaxResult.success(fileArray);
        }
        // 2.内管上传交易码
        if (Constants.CMS_FILES_UPLOAD.equals(map.get(CmsConstants.TRANCODE))) {
            logger.info("----------------开始进行文件后缀名校验----------------------");
            // 后缀名校验
            JSONArray list = jsonObject.getJSONArray("fileList");
            List<String> fileNames = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String fileName = list.getJSONObject(i).getString(CmsConstants.FILENAME);
                String[] split = fileName.split("\\.");
                if (!Arrays.asList(allowExtension).contains(split[split.length - 1])) {
                    logger.error("文件:[ " + fileName + " ] 的后缀名不符合规定，请重新上传");
                    fileNames.add(fileName);
                }
            }
            if (fileNames.size() > 0) {
                return AjaxResult.error("文件:" + fileNames + " 的后缀名不符合规定，请重新上传");
            }
            fileArray = httpService.cmsUpload(jsonObject, data);
            logger.info("文件结构化成功，返回报文：" + fileArray.toJSONString());
            return AjaxResult.success(fileArray);
        }
        // 3.后台文件查询
        if (Constants.CMS_FILES_QUERY.equals(map.get(CmsConstants.TRANCODE))) {
            JSONObject object = httpService.cmsQuery(jsonObject);
            logger.info("文件查询成功，返回报文：" + object.toJSONString());
            return AjaxResult.success(object);
        }
        // 4.项目信息同步
        if (Constants.PRO_INFO_SYN.equals(map.get(CmsConstants.TRANCODE))) {
            if (jsonObject.get(CmsConstants.OPERATIONCODE) == null || "".equals(jsonObject.get(CmsConstants.OPERATIONCODE))) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.PROJECT_MISSING.code());
                resultObject.put("totalResultMsg", ResultCode.PROJECT_MISSING.msg());
                return AjaxResult.error(resultObject);
            }
            httpService.projectInfoSyncho(jsonObject);
            JSONObject resultObject = new JSONObject(true);
            resultObject.put("totalResultCode", ResultCode.SUCCESS.code());
            resultObject.put("totalResultMsg", ResultCode.SUCCESS.msg());
            return AjaxResult.success(resultObject);
        }
        // 5.获取项目整体信息
        if (Constants.PRO_INFO_TOTAL.equals(map.get(CmsConstants.TRANCODE))) {
            if (jsonObject.get(CmsConstants.OPERATIONCODE) == null || "".equals(jsonObject.get(CmsConstants.OPERATIONCODE))) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.PROJECT_MISSING.code());
                resultObject.put("totalResultMsg", ResultCode.PROJECT_MISSING.msg());
                return AjaxResult.error(resultObject);
            }
            PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchByOpt(jsonObject.getString(CmsConstants.OPERATIONCODE));
            if (pmsBatch.getModelList() == null) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.MODELS_MISSING.code());
                resultObject.put("totalResultMsg", ResultCode.MODELS_MISSING.msg());
                return AjaxResult.error(resultObject);
            }
            JSONObject billObject = httpService.projectBillList(pmsBatch);
            return AjaxResult.success(billObject);
        }
        // 6.文件信息绑定
        if (Constants.FILE_INFO_BIND.equals(map.get(CmsConstants.TRANCODE))) {
            if (null == jsonObject.get(CmsConstants.OPERATIONCODE) || "".equals(jsonObject.get(CmsConstants.OPERATIONCODE))) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.PROJECT_MISSING.code());
                resultObject.put("totalResultMsg", ResultCode.PROJECT_MISSING.msg());
                return AjaxResult.error(resultObject);
            }
            if (null == jsonObject.get(CmsConstants.BILLTYPE) || "".equals(jsonObject.get(CmsConstants.BILLTYPE))) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.BILL_CODE_MISSING.code());
                resultObject.put("totalResultMsg", ResultCode.BILL_CODE_MISSING.msg());
                return AjaxResult.error(resultObject);
            }
            if (null == jsonObject.get(CmsConstants.FILES) || "".equals(jsonObject.get(CmsConstants.FILES))) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.FILES_MISSING.code());
                resultObject.put("totalResultMsg", ResultCode.FILES_MISSING.msg());
                return AjaxResult.error(resultObject);
            }
            return httpService.fileInfoBind(jsonObject);
        }
        // 7.拉取项目分类
        if (Constants.PRO_BILLS_QUERY.equals(map.get(CmsConstants.TRANCODE))) {
            if (jsonObject.get(CmsConstants.OPERATIONCODE) == null || "".equals(jsonObject.get(CmsConstants.OPERATIONCODE))) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.PROJECT_MISSING.code());
                resultObject.put("totalResultMsg", ResultCode.PROJECT_MISSING.msg());
                return AjaxResult.error(resultObject);
            }
            PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchByOpt(jsonObject.getString(CmsConstants.OPERATIONCODE));
            if (null == pmsBatch) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.PROJECT_NULL.code());
                resultObject.put("totalResultMsg", ResultCode.PROJECT_NULL.msg());
                return AjaxResult.error(resultObject);
            }
            if (null == pmsBatch.getModelList()) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.MODELS_MISSING.code());
                resultObject.put("totalResultMsg", ResultCode.MODELS_MISSING.msg());
                return AjaxResult.error(resultObject);
            }
            JSONObject billList = httpService.projectBillQuery(pmsBatch.getModelList());
            return AjaxResult.success(billList);
        }
        // 8.获取下载预览授权token
        if (Constants.GET_TOKEN.equals(map.get(CmsConstants.TRANCODE))) {
            if (null == map.get("sysCode") || "".equals(map.get("sysCode")) ||
                    null == map.get("authCode") || "".equals(map.get("authCode")) ||
                    null == map.get("fileId") || "".equals(map.get("fileId"))) {
                return AjaxResult.error("请检查请求参数是否完整");
            }
            // 获取当前时间
            long before = System.currentTimeMillis();
            Long timeOut;
            if (null != map.get("validity") && !"".equals(map.get("validity"))) {
                timeOut = before + Long.valueOf(String.valueOf(map.get("validity")));
            } else {
                timeOut = before + 600000L;
            }
            String content = map.get("sysCode") + "," + map.get("authCode") + "," + map.get("fileId") + "," + timeOut;
            byte[] key = Base64.decodeBase64(CmsConstants.PUBLIC_KEY);
            String token = null;
            try {
                token = AESUtils.encrypt(content, key);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.info("报文加密成功,token为:" + token);
            return AjaxResult.success(token);
        }
        // 9.后台下载接口(安全认证)
        if (Constants.DOWNLOAD.equals(map.get(CmsConstants.TRANCODE))) {
            logger.info("--------------------传入的token为：" + String.valueOf(map.get("token")));
            String token = String.valueOf(map.get("token"));
            // 解密
            String content = null;
            try {
                String replace = token.replace(" ", "+");
                byte[] bytes = AESUtils.decrypt(replace, Base64.decodeBase64(CmsConstants.PUBLIC_KEY));
                content = new String(bytes);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            if (null == content) {
                return AjaxResult.error("解析密文出错");
            }
            logger.info("解密后的明文信息为：" + content);
            String[] split = content.split(",");
            String sysCode = split[0];
            String authCode = split[1];
            Long fileId = Long.valueOf(split[2]);
            Long timeAfter = Long.valueOf(split[3]);
            if (System.currentTimeMillis() - timeAfter > 0) {
                return AjaxResult.error("已超过有效时间，请重试");
            }
            // 查询系统来源与鉴权码
            CmsSystem cmsSystem1 = cmsSystemService.selectCmsSystemByCode(sysCode);
            if (cmsSystem1 == null || !cmsSystem1.getAuthentInfo().equals(authCode)) {
                return AjaxResult.error("此系统尚未接入,请先接入系统再进行上传操作");
            }
            Long[] fileIds = {fileId};
            List<CmsFileDTO> cmsFiles = cmsFileService.selectFileImageByIds(fileIds);
            if (cmsFiles.size() <= 0) {
                return AjaxResult.error("请检查fileId是否存在");
            }
            boolean b = UploadUtil.downloadTrans(getServerPath(), cmsFiles.get(0).getRandomName(), cmsFiles.get(0).getFPath() + "&hadoopType=" + cmsFiles.get(0).getHadoopType());
            if (!b) {
                logger.info("从HADOOP下载到服务器失败");
                return AjaxResult.error("从HADOOP下载到服务器失败");
            }
            File file = new File(getServerPath() + cmsFiles.get(0).getRandomName());
            String fileName = URLEncoder.encode(cmsFiles.get(0).getFName(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            response.setContentType("application/x-www-form-urlencoded");
            InputStream ins = null;
            OutputStream outs = null;
            try {
                ins = new FileInputStream(getServerPath() + cmsFiles.get(0).getRandomName());
                outs = response.getOutputStream();
                //写文件
                int byteRead = 0;
                byte[] buffer = new byte[8192];
                //开始向网络传输文件流
                while ((byteRead = ins.read(buffer)) > 0) {
                    outs.write(buffer, 0, byteRead);
                }
                outs.flush();
            } catch (IOException e) {
                logger.error(e.getMessage());
            } finally {
                if (null != outs) {
                    outs.close();
                }
                if (null != ins) {
                    ins.close();
                }
            }
            //删除临时创建的压缩包
            boolean delete = file.delete();
            if (!delete) {
                logger.error("删除临时文件失败");
            }
            logger.info("下载成功");
            return AjaxResult.success("下载成功");
        }
        // 10.后台预览接口(安全认证)
        if (Constants.PREVIEW.equals(map.get(CmsConstants.TRANCODE))) {
            logger.info("--------------------传入的token为：" + String.valueOf(map.get("token")));
            String token = String.valueOf(map.get("token"));
            // 解密
            String content = null;
            try {
                String replace = token.replace(" ", "+");
                byte[] bytes = AESUtils.decrypt(replace, Base64.decodeBase64(CmsConstants.PUBLIC_KEY));
                content = new String(bytes);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            if (null == content) {
                return AjaxResult.error("解析密文出错");
            }
            logger.info("解密后的明文信息为：" + content);
            String[] split = content.split(",");
            String sysCode = split[0];
            String authCode = split[1];
            Long fileId = Long.valueOf(split[2]);
            Long timeAfter = Long.valueOf(split[3]);
            if (System.currentTimeMillis() - timeAfter > 0) {
                return AjaxResult.error("已超过失效时间，请重试");
            }
            // 查询系统来源与鉴权码
            CmsSystem cmsSystem1 = cmsSystemService.selectCmsSystemByCode(sysCode);
            if (cmsSystem1 == null || !cmsSystem1.getAuthentInfo().equals(authCode)) {
                return AjaxResult.error("此系统尚未接入,请先接入系统再进行上传操作");
            }
            Long[] fileIds = {fileId};
            List<CmsFileDTO> cmsFiles = cmsFileService.selectFileImageByIds(fileIds);
            if (cmsFiles.size() <= 0) {
                return AjaxResult.error("请检查fileId是否存在");
            }
            boolean b = UploadUtil.downloadTrans(getServerPath(), cmsFiles.get(0).getRandomName(), cmsFiles.get(0).getFPath() + "&hadoopType=" + cmsFiles.get(0).getHadoopType());
            if (!b) {
                logger.info("从HADOOP下载到服务器失败");
                return AjaxResult.error("从HADOOP下载到服务器失败");
            }
            File file = new File(getServerPath() + cmsFiles.get(0).getRandomName());
            String fileName = URLEncoder.encode(cmsFiles.get(0).getFName(), "UTF-8");
            //response.setHeader("Content-Type","image/jpg");
            InputStream ins = null;
            OutputStream outs = null;
            try {
                ins = new FileInputStream(getServerPath() + cmsFiles.get(0).getRandomName());
                outs = response.getOutputStream();
                //写文件
                int byteRead = 0;
                byte[] buffer = new byte[8192];
                //开始向网络传输文件流
                while ((byteRead = ins.read(buffer)) > 0) {
                    outs.write(buffer, 0, byteRead);
                }
                outs.flush();
            } catch (IOException e) {
                logger.error(e.getMessage());
            } finally {
                if (null != outs) {
                    outs.close();
                }
                if (null != ins) {
                    ins.close();
                }
            }
            //删除临时创建的压缩包
            boolean delete = file.delete();
            if (!delete) {
                logger.error("删除临时文件失败");
            }
            logger.info("操作成功");
            return AjaxResult.success();
        }
        return null;
    }


    /**
     * 内部上传接口
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public TotalResult uploadFiles(@RequestParam("json") String json, @RequestPart(value = "files", required = false) MultipartFile[] multipartFiles) throws InvalidExtensionException, IOException {
        logger.info("------------------------进入内管上传------------------------");
        logger.info("-----------接收到的json信息：" + json + "---------------");
        long start = System.currentTimeMillis();
        JSONParser jsonParser = new JSONParser(json);
        Map<String, Object> map = jsonParser.parseMap();
        JSONObject jsonObject = null;
        // 查询系统来源与鉴权码 (必传)
        CmsSystem cmsSystem = cmsSystemService.selectCmsSystemByCode(String.valueOf(map.get(CmsConstants.SYSCODE)));
        if (cmsSystem == null) {
            return new TotalResult(ResultCode.SYSTEM_NULL);
        }
        logger.info("cmsSystem=" + cmsSystem.toString());
        //适配器
        if (Constants.USE_ADAPTER.equals(cmsSystem.getUseAdapter())) {
            RequestExchangeAdapter adapter = new RequestExchangeAdapter(cmsSystem.getSysCode());
            jsonObject = adapter.exchangeRequest(JSON.parseObject(json));
        } else {
            jsonObject = new JSONObject(map);
        }
        List<ResultInfo> urlList = new ArrayList<>();
        JSONArray list = jsonObject.getJSONArray(CmsConstants.FILELIST);
        //文件非空校验
        for (int i = 0; i < list.size(); i++) {
            if (multipartFiles.length == 0
                    && (list.getJSONObject(i).get(CmsConstants.BASE64) == null || "".equals(String.valueOf(list.getJSONObject(i).get(CmsConstants.BASE64))))) {
                return new TotalResult(ResultCode.UPLOAD_NULL);
            }
        }
        // 通过不同的交易码，走不同的情况
        // 1.档案上传交易码
        int count = 0;
        if (Constants.AMS_UPLOAD.equals(map.get(CmsConstants.TRANCODE))) {
            for (int i = 0; i < list.size(); i++) {
                String[] fileNames = String.valueOf(list.getJSONObject(i).get(CmsConstants.FILENAME)).split("\\.");
                String header = "data/" + fileNames[1] + ";base64,";
                MultipartFile multipartFile = Base64ToMultipartFileUtils.base64ToMultipart(fileNames[0], header + list.getJSONObject(i).get(CmsConstants.BASE64));
                ResultInfo resultInfo = new ResultInfo();
                // md5校验
                if (!checkMd5(list.getJSONObject(i), multipartFile)) {
                    resultInfo.setResultCode(ResultCode.FILE_MISSING.code());
                    resultInfo.setResultMsg(ResultCode.FILE_MISSING.msg());
                    continue;
                }
                // 查询数据库是否有该MD5
                String[] split = null;
                int cmsFileCount = cmsFileService.selectCountCmsFileByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                int cmsImageCount = cmsImageService.selectCountCmsImageByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                if (cmsFileCount == 0 && cmsImageCount == 0) {
                    // 上传到HDFS
                    String resultString = uploadCommon(multipartFiles[i]);
                    split = resultString.split("#");
                    boolean result = Boolean.valueOf(split[0]);
                    if (!result) {
                        resultInfo.setResultCode(ResultCode.UPLOAD_FAIL.code());
                        resultInfo.setResultMsg(ResultCode.UPLOAD_FAIL.msg());
                        if (list.getJSONObject(i).get(CmsConstants.ORDERNUM) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.ORDERNUM))) {
                            resultInfo.setOrderNum(Long.valueOf(String.valueOf(list.getJSONObject(i).get(CmsConstants.ORDERNUM))));
                        }
                        count++;
                        continue;
                    }
                }
                // 内管结构化
                String rn;
                String hadoopType;
                String willPath;
                if (split == null) {
                    CmsFile cmsFile = cmsFileService.selectOneFileByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                    CmsImage image = null;
                    if (cmsFile == null) {
                        image = cmsImageService.selectOneImageByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                        rn = image != null ? image.getRandomName() : "";
                        hadoopType = image != null ? image.getHadoopType() : "";
                        willPath = image != null ? image.getImagePath() : "";
                    } else {
                        rn = cmsFile != null ? cmsFile.getRandomName() : "";
                        hadoopType = cmsFile != null ? cmsFile.getHadoopType() : "";
                        willPath = cmsFile != null ? cmsFile.getFilePath() : "";
                    }
                } else {
                    rn = split[1];
                    hadoopType = split[2];
                    willPath = split[3];
                }
                String s = uploadCommonToMysql(list.getJSONObject(i), json, multipartFile, rn, hadoopType, willPath, jsonObject);
                // 档案数据结构化->ams
                jsonObject.put("fileList", list);
                resultInfo.setResultCode(ResultCode.UPLOAD_SUCCESS.code());
                resultInfo.setResultMsg(ResultCode.UPLOAD_SUCCESS.msg());
                if (s.contains("#")) {
                    resultInfo.setFileId(Long.valueOf(String.valueOf(s.split("#")[1])));
                    resultInfo.setFileUrl(s.split("#")[0]);
                }
                urlList.add(resultInfo);
            }
            amsBatchRecordFeignClient.dealProcess(jsonObject.toJSONString());
            // 2.内管base64上传交易码
        } else if (Constants.CMS_BASE64S_UPLOAD.equals(map.get(CmsConstants.TRANCODE))) {
            for (int i = 0; i < list.size(); i++) {
                String[] fileNames = String.valueOf(list.getJSONObject(i).get(CmsConstants.FILENAME)).split("\\.");
                String header = "data/" + fileNames[1] + ";base64,";
                MultipartFile multipartFile = Base64ToMultipartFileUtils.base64ToMultipart(fileNames[0], header + list.getJSONObject(i).get(CmsConstants.BASE64));
                ResultInfo resultInfo = new ResultInfo();
                // md5校验
                if (!checkMd5(list.getJSONObject(i), multipartFile)) {
                    resultInfo.setResultCode(ResultCode.FILE_MISSING.code());
                    resultInfo.setResultMsg(ResultCode.FILE_MISSING.msg());
                    continue;
                }
                // 查询数据库是否有该MD5
                String[] split = null;
                int cmsFileCount = cmsFileService.selectCountCmsFileByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                int cmsImageCount = cmsImageService.selectCountCmsImageByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                if (cmsFileCount == 0 && cmsImageCount == 0) {
                    // 上传到HDFS
                    String resultString = uploadCommon(multipartFiles[i]);
                    split = resultString.split("#");
                    boolean result = Boolean.valueOf(split[0]);
                    if (!result) {
                        resultInfo.setResultCode(ResultCode.UPLOAD_FAIL.code());
                        resultInfo.setResultMsg(ResultCode.UPLOAD_FAIL.msg());
                        if (list.getJSONObject(i).get(CmsConstants.ORDERNUM) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.ORDERNUM))) {
                            resultInfo.setOrderNum(Long.valueOf(String.valueOf(list.getJSONObject(i).get(CmsConstants.ORDERNUM))));
                        }
                        count++;
                        continue;
                    }
                }
                String rn;
                String hadoopType;
                String willPath;
                if (split == null) {
                    CmsFile cmsFile = cmsFileService.selectOneFileByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                    CmsImage image = null;
                    if (cmsFile == null) {
                        image = cmsImageService.selectOneImageByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                        rn = image != null ? image.getRandomName() : "";
                        hadoopType = image != null ? image.getHadoopType() : "";
                        willPath = image != null ? image.getImagePath() : "";
                    } else {
                        rn = cmsFile != null ? cmsFile.getRandomName() : "";
                        hadoopType = cmsFile != null ? cmsFile.getHadoopType() : "";
                        willPath = cmsFile != null ? cmsFile.getFilePath() : "";
                    }
                } else {
                    rn = split[1];
                    hadoopType = split[2];
                    willPath = split[3];
                }
                String s = uploadCommonToMysql(list.getJSONObject(i), json, multipartFile, rn, hadoopType, willPath, jsonObject);
                resultInfo.setResultCode(ResultCode.UPLOAD_SUCCESS.code());
                resultInfo.setResultMsg(ResultCode.UPLOAD_SUCCESS.msg());
                if (s.contains("#")) {
                    resultInfo.setFileId(Long.valueOf(String.valueOf(s.split("#")[1])));
                    resultInfo.setFileUrl(s.split("#")[0]);
                }
                urlList.add(resultInfo);
            }
        } // 3.内管文件上传交易码
        else if (Constants.CMS_FILES_UPLOAD.equals(map.get(CmsConstants.TRANCODE))) {
            for (int i = 0; i < multipartFiles.length; i++) {
                ResultInfo resultInfo = new ResultInfo();
                // md5校验
                if (!checkMd5(list.getJSONObject(i), multipartFiles[i])) {
                    resultInfo.setResultCode(ResultCode.FILE_MISSING.code());
                    resultInfo.setResultMsg(ResultCode.FILE_MISSING.msg());
                    continue;
                }
                // 查询数据库是否有该MD5
                String[] split = null;
                int cmsFileCount = cmsFileService.selectCountCmsFileByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                int cmsImageCount = cmsImageService.selectCountCmsImageByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                if (cmsFileCount == 0 && cmsImageCount == 0) {
                    // 上传到HDFS
                    String resultString = uploadCommon(multipartFiles[i]);
                    split = resultString.split("#");
                    boolean result = Boolean.valueOf(split[0]);
                    if (!result) {
                        resultInfo.setResultCode(ResultCode.UPLOAD_FAIL.code());
                        resultInfo.setResultMsg(ResultCode.UPLOAD_FAIL.msg());
                        if (list.getJSONObject(i).get(CmsConstants.ORDERNUM) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.ORDERNUM))) {
                            resultInfo.setOrderNum(Long.valueOf(String.valueOf(list.getJSONObject(i).get(CmsConstants.ORDERNUM))));
                        }
                        count++;
                        continue;
                    }
                }
                String rn;
                String hadoopType;
                String willPath;
                if (split == null) {
                    CmsFile cmsFile = cmsFileService.selectOneFileByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                    CmsImage image = null;
                    if (cmsFile == null) {
                        image = cmsImageService.selectOneImageByMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                        rn = image != null ? image.getRandomName() : "";
                        hadoopType = image != null ? image.getHadoopType() : "";
                        willPath = image != null ? image.getImagePath() : "";
                    } else {
                        rn = cmsFile != null ? cmsFile.getRandomName() : "";
                        hadoopType = cmsFile != null ? cmsFile.getHadoopType() : "";
                        willPath = cmsFile != null ? cmsFile.getFilePath() : "";
                    }
                } else {
                    rn = split[1];
                    hadoopType = split[2];
                    willPath = split[3];
                }
                String s = uploadCommonToMysql(list.getJSONObject(i), json, multipartFiles[i], rn, hadoopType, willPath, jsonObject);
                logger.info("s" + s);
                resultInfo.setResultCode(ResultCode.UPLOAD_SUCCESS.code());
                resultInfo.setResultMsg(ResultCode.UPLOAD_SUCCESS.msg());
                if (s.contains(",")) {
                    resultInfo.setFileId(Long.valueOf(s.split("#")[1]));
                    resultInfo.setFileUrl(s.split("#")[0]);
                    resultInfo.setMd5(s.split("#")[3]);
                    resultInfo.setHadoopType(hadoopType);
                    resultInfo.setWillPath(willPath);
                }
                urlList.add(resultInfo);
            }
        }
        long end = System.currentTimeMillis();
        long totalTime = (end - start) / 1000;
        logger.info("执行完上传所需时间为：" + totalTime + "秒");
        return count > 0 ? new TotalResult<>(ResultCode.UPLOAD_FAIL, urlList) : new TotalResult(ResultCode.UPLOAD_SUCCESS, urlList);
    }

    /**
     * 上传公用方法(结构化)
     */
    private String uploadCommonToMysql(JSONObject object, String json, MultipartFile multipartFile, String randomName, String hadoopType, String willPath, JSONObject jsonObject) {
        Long fileId = null;
        String extension = FileUploadUtils.getExtension(multipartFile).toLowerCase();
        String s = "";
        SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils(Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.WORKER_ID)), Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.DATACENTER_ID)));
        // 兼容IE浏览器的OriginalFileName
        String originalFilename = compatibleIE(multipartFile);
        // 后缀是图像,保存到cms_image
        for (int i = 0; i < allowedImageExtension.length; i++) {
            if (extension.equals(allowedImageExtension[i])) {
                // 登记流水号查询是否已有该批次，如果没有就insert，有就update
                if (object.get(CmsConstants.OPERATIONCODE) == null || "".equals(CmsConstants.OPERATIONCODE)) {
                    return "批次编号不能为空！！";
                }
                PmsBatch pmsBatchByOpt = pmsBatchService.selectPmsBatchByOpt(String.valueOf(object.get(CmsConstants.OPERATIONCODE)));
                PmsBatch pmsBatch;
                if (pmsBatchByOpt == null) {
                    // 保存批次
                    pmsBatch = httpService.savePmsBatch(snowFlakeUtils, object, json, jsonObject);
                } else {
                    // 更新批次
                    pmsBatch = httpService.updatePmsBatch(object, json, pmsBatchByOpt);
                }
                // 保存影像
                CmsImage cmsImage = new CmsImage();
                cmsImage.setImageId(snowFlakeUtils.nextId());
                cmsImage.setImageName(originalFilename);
                cmsImage.setStatus(String.valueOf(Constants.NORMAL_IMAGE));
                cmsImage.setBatchId(pmsBatch.getBatchId());
                cmsImage.setRandomName(randomName);
                cmsImage.setCreateTime(new Date());
                if (object.get(CmsConstants.ORDERNUM) != null && !"".equals(object.get(CmsConstants.ORDERNUM))) {
                    cmsImage.setOrderNum(Long.valueOf(String.valueOf(object.get(CmsConstants.ORDERNUM))));
                }
                cmsImage.setImagePath(willPath);
                BigDecimal bd = new BigDecimal(multipartFile.getSize());
                BigDecimal kb = new BigDecimal(1024);
                cmsImage.setImageSize(bd.divide(kb, 2, BigDecimal.ROUND_UP));
                cmsImage.setHadoopType(hadoopType);
                cmsImage.setSysZone(SysConfigInitParamsUtils.getConfig(CmsConstants.SYS_ZONE));
//                try {
//                    cmsImage.setWidth(String.valueOf(ImageIO.read(multipartFile.getInputStream()).getWidth()));
//                    cmsImage.setHeight(String.valueOf(ImageIO.read(multipartFile.getInputStream()).getHeight()));
//                } catch (IOException e) {
//                    logger.error(e.getMessage());
//                }
                if (object.get(CmsConstants.BILLCODE) != null && !"".equals(object.get(CmsConstants.BILLCODE))) {
                    CmsBill cmsBill = cmsBillMapper.selectCmsBillByCode(String.valueOf(object.get(CmsConstants.BILLCODE)));
                    cmsImage.setBillId(cmsBill.getId().intValue());
                }
                if (object.get(CmsConstants.TEMPLATEID) != null && !"".equals(object.get(CmsConstants.TEMPLATEID))) {
                    cmsImage.setTemplateId(String.valueOf(object.get(CmsConstants.TEMPLATEID)));
                }
                cmsImage.setOcrResult((String) object.get(CmsConstants.OCRRESULT));
                cmsImage.setOcrStatus((String) object.get(CmsConstants.OCRSTATUS));
                cmsImage.setOcrType(extension);
                cmsImage.setRemark((String) object.get(CmsConstants.REMARK));
                if (object.get(CmsConstants.CREATEUSER) != null && !"".equals(object.get(CmsConstants.CREATEUSER))) {
                    cmsImage.setCreateBy(String.valueOf(object.get(CmsConstants.CREATEUSER)));
                }
                cmsImage.setImageSource((String) object.get(CmsConstants.IMGSOURCE));
                cmsImage.setVersion(CmsConstants.VERSION);
                cmsImage.setMetadata(json);
                cmsImage.setDeptName(pmsBatch.getDeptName());
                cmsImage.setImageSysCode(pmsBatch.getSysCode());
                cmsImage.setMd5(String.valueOf(object.get(CmsConstants.MD5)));
                cmsImageService.insertCmsImage(cmsImage);
                s = UploadUtil.downloadAPI(cmsImage.getImageId());
                fileId = cmsImage.getImageId();
                object.remove(CmsConstants.BASE64);
                object.put("filename", cmsImage.getImageName());
                object.put("size", new BigDecimal(cmsImage.getImageSize().toString()).setScale(0, BigDecimal.ROUND_HALF_UP));
                object.put("batchId", String.valueOf(pmsBatch.getBatchId()));
                object.put("transfilepath", s);
                object.put("fileType", cmsImage.getOcrType());
                object.put("hadoopType", cmsImage.getHadoopType());
                object.put("billId", String.valueOf(cmsImage.getBillId()));
                object.put("remark", cmsImage.getRemark());
                object.put("createuser", String.valueOf(cmsImage.getCreateUser()));
                break;
            }
        }
        for (int i = 0; i < allowedFileExtension.length; i++) {
            // 后缀是文件,保存到cms_file
            if (extension.equals(allowedFileExtension[i])) {
                // 登记流水号查询是否已有该批次，如果没有就insert，有就update
                if (object.get(CmsConstants.OPERATIONCODE) == null || "".equals(CmsConstants.OPERATIONCODE)) {
                    return "批次编号不能为空！！";
                }
                PmsBatch pmsBatchByOpt = pmsBatchService.selectPmsBatchByOpt(String.valueOf(object.get(CmsConstants.OPERATIONCODE)));
                PmsBatch pmsBatch;
                if (pmsBatchByOpt == null) {
                    // 保存批次
                    pmsBatch = httpService.savePmsBatch(snowFlakeUtils, object, json, jsonObject);
                } else {
                    // 更新批次
                    pmsBatch = httpService.updatePmsBatch(object, json, pmsBatchByOpt);
                }
                // 保存文件
                CmsFile cmsFile = new CmsFile();
                cmsFile.setFileId(snowFlakeUtils.nextId());
                cmsFile.setFileName(originalFilename);
                cmsFile.setRandomName(randomName);
                cmsFile.setStatus(String.valueOf(Constants.UPLOAD_OVER));
                cmsFile.setBatchId(pmsBatch.getBatchId());
                cmsFile.setCreateTime(new Date());
                if (object.get(CmsConstants.ORDERNUM) != null && !"".equals(object.get(CmsConstants.ORDERNUM))) {
                    cmsFile.setOrderNum(Long.valueOf(String.valueOf(object.get(CmsConstants.ORDERNUM))));
                }
                cmsFile.setFilePath(willPath);
                BigDecimal bd = new BigDecimal(multipartFile.getSize());
                BigDecimal kb = new BigDecimal(1024);
                cmsFile.setFileSize(bd.divide(kb, 2, BigDecimal.ROUND_UP));
                cmsFile.setHadoopType(hadoopType);
                cmsFile.setSysZone(SysConfigInitParamsUtils.getConfig(CmsConstants.SYS_ZONE));
                cmsFile.setFileType(extension);
                if (object.get(CmsConstants.BILLCODE) != null && !"".equals(object.get(CmsConstants.BILLCODE))) {
                    CmsBill cmsBill = cmsBillMapper.selectCmsBillByCode(String.valueOf(object.get(CmsConstants.BILLCODE)));
                    cmsFile.setBillId(Integer.valueOf(String.valueOf(cmsBill.getId())));
                }
                cmsFile.setRemark((String) object.get(CmsConstants.REMARK));
                if (object.get(CmsConstants.CREATEUSER) != null && !"".equals(object.get(CmsConstants.CREATEUSER))) {
                    cmsFile.setCreateBy(String.valueOf(object.get(CmsConstants.CREATEUSER)));
                }
                cmsFile.setFileSource((String) object.get(CmsConstants.FILESOURCE));
                cmsFile.setVersion(CmsConstants.VERSION);
                cmsFile.setMetadata(json);
                cmsFile.setFileSysCode(pmsBatch.getSysCode());
                cmsFile.setDeptName(pmsBatch.getDeptName());
                cmsFile.setMd5(String.valueOf(object.get(CmsConstants.MD5)));
                cmsFileService.insertCmsFile(cmsFile);
                s = UploadUtil.downloadAPI(cmsFile.getFileId());
                fileId = cmsFile.getFileId();
                object.remove(CmsConstants.BASE64);
                object.put("filename", cmsFile.getFileName());
                object.put("size", new BigDecimal(cmsFile.getFileSize().toString()));
                object.put("batchId", String.valueOf(pmsBatch.getBatchId()));
                object.put("transfilepath", s);
                object.put("fileType", cmsFile.getFileType());
                object.put("hadoopType", cmsFile.getHadoopType());
                object.put("billId", String.valueOf(cmsFile.getBillId()));
                object.put("remark", cmsFile.getRemark());
                object.put("createuser", String.valueOf(cmsFile.getCreateUser()));
                break;
            }
        }
        return s + "#" + String.valueOf(fileId) + "#" + object.toJSONString() + "#" + String.valueOf(object.get(CmsConstants.MD5));
    }

    /**
     * 上传公用方法(非结构化)
     */
    private String uploadCommon(MultipartFile multipartFile) throws InvalidExtensionException, IOException {
        boolean uploadFlag = true;
        String extension = FileUploadUtils.getExtension(multipartFile).toLowerCase();
        int count = 0;
        // 兼容IE浏览器的OriginalFileName
        String originalFilename = compatibleIE(multipartFile);
        String[] split = originalFilename.split("\\.");
        String randomName = UUID.randomUUID().toString().replace("-", "") + "." + split[split.length - 1];
        for (int i = 0; i < allowExtension.length; i++) {
            if (extension.equals(allowExtension[i])) {
                break;
            } else {
                count++;
            }
            if (count == allowExtension.length) {
                // 后缀不是要求的文件,抛出异常
                throw new InvalidExtensionException(allowExtension, extension, originalFilename);
            }
        }
        // 解析后将文件保存到应用本地
        InputStream fileIn = null;
        BufferedInputStream bin = null;
        FileOutputStream fos = null;
        // 获取文件后缀
        BufferedOutputStream bout = null;
        File fileTemp = null;
        try {
            File file = new File(getServerPath());
            if (!file.exists()) {
                file.mkdir();
            }
            bin = new BufferedInputStream(multipartFile.getInputStream());
            fileTemp = new File(getServerPath() + randomName);
            if (!fileTemp.exists()) {
                boolean tag = fileTemp.createNewFile();
                if (!tag) {
                    logger.error("创建新文件失败");
                }
            }
            fos = new FileOutputStream(getServerPath() + randomName);
            bout = new BufferedOutputStream(fos);
            int i;
            while ((i = bin.read()) != -1) {
                bout.write(i);
            }
            logger.info("[ " + originalFilename + "] 上传本地服务器成功");
        } catch (IOException e) {
            throw new IOException(e.getMessage(), e);
        } finally {
            if (null != bout) {
                bout.close();
            }
            if (null != fos) {
                fos.close();
            }
            if (null != bin) {
                bin.close();
            }
        }
        // 存放去向
        String hadoopType;
        // 文件路径
        String willPath;
        // 调用fileTrans接口上传
        boolean b;
        // 若小于10M，使用hbase，大于10M不变
        if (multipartFile.getSize() < CmsConstants.TEN_MB) {
            hadoopType = CmsConstants.USEHBASE;
            willPath = UUID.randomUUID().toString().replace("-", "");
            fileIn = new FileInputStream(fileTemp);
            b = UploadUtil.uploadTransStream(willPath + "&hadoopType=0", fileIn, 65536, 65536, 1);
        }
        // 上传文件到大数据，并更新元数据
        else {
            hadoopType = CmsConstants.USERHDFS;
            willPath = getHdfsPath() + randomName;
            fileIn = new FileInputStream(fileTemp);
            b = UploadUtil.uploadTransStream(willPath + "&hadoopType=1", fileIn, 65536, 65536, 1);
        }
        fileIn.close();
        if (!b) {
            return !uploadFlag + "#" + randomName + "#" + hadoopType + "#" + willPath;
        }
        boolean delete = fileTemp.delete();
        if (!delete) {
            logger.error("删除临时文件失败");
        }
        return uploadFlag + "#" + randomName + "#" + hadoopType + "#" + willPath;
    }

    /**
     * 使multipartfile兼容IE浏览器
     */
    public String compatibleIE(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        int unixSep = originalFilename.lastIndexOf('/');
        int winSep = originalFilename.lastIndexOf('\\');
        int pos = (winSep > unixSep ? winSep : unixSep);
        if (pos != -1) {
            originalFilename = originalFilename.substring(pos + 1);
        }
        return originalFilename;
    }

    /**
     * md5校验
     */
    public boolean checkMd5(JSONObject jsonObject, MultipartFile multipartFile) {
        try {
            if (!jsonObject.get(CmsConstants.MD5).equals(DigestUtils.md5Hex(multipartFile.getInputStream()))) {
                return false;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return true;
    }

    /**
     * 批量下载文件影像(打包方式)
     */
    @GetMapping("/downloadFiles")
    @ResponseBody
    public String downloadFile(String fileIds, HttpServletResponse response) throws IOException {
        List<CmsFileDTO> cmsFiles = cmsFileService.selectFileImageByIds(Convert.toLongArray(fileIds));
        BufferedInputStream bis = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ZipOutputStream zip = null;
        try {
            fos = new FileOutputStream(getServerPath() + ZIP_NAME);
            zip = new ZipOutputStream(fos);
            for (int i = 0; i < cmsFiles.size(); i++) {
                String[] split1 = cmsFiles.get(i).getFPath().split("/");
                boolean b = UploadUtil.downloadTrans(getServerPath(), split1[split1.length - 1], cmsFiles.get(i).getFPath() + "&hadoopType=" + cmsFiles.get(i).getHadoopType());
                if (!b) {
                    logger.info("从HADOOP下载到服务器失败");
                    return "从HADOOP下载到服务器失败";
                }
                try {
                    fis = new FileInputStream(getServerPath() + split1[split1.length - 1]);
                    bis = new BufferedInputStream(fis);
                    String fileName = cmsFiles.get(i).getFName();
                    // 获取集合中所有文件名
                    Set<String> fileNames = cmsFiles.stream().map(CmsFileDTO::getFName).filter(Objects::nonNull).collect(Collectors.toSet());
                    fileNames.remove(fileName);
                    // 判断是否有重名文件
                    if (i > 0 && fileNames.contains(fileName)) {
                        String[] split = cmsFiles.get(i).getFName().split("\\.");
                        fileName = split[0] + "(" + i + ")." + split[split.length - 1];
                    }
                    zip.putNextEntry(new ZipEntry(fileName));
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    while ((length = bis.read(bytes)) != -1) {
                        zip.write(bytes, 0, length);
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage());
                } finally {
                    if (null != bis) {
                        bis.close();
                    }
                    if (null != fis) {
                        fis.close();
                    }
                }
                zip.closeEntry();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (null != bis) {
                bis.close();
            }
            if (null != fis) {
                fis.close();
            }
            if (null != zip) {
                zip.close();
            }
            if (null != fos) {
                fos.close();
            }
        }
        File file = new File(getServerPath() + ZIP_NAME);
        response.setHeader("Content-Disposition", "attachment;filename=\"fwz.zip\"");
        response.setContentType("application/zip");
        InputStream ins = null;
        OutputStream outs = null;
        try {
            ins = new FileInputStream(getServerPath() + ZIP_NAME);
            outs = response.getOutputStream();
            //写文件
            int byteRead = 0;
            byte[] buffer = new byte[8192];
            //开始向网络传输文件流
            while ((byteRead = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, byteRead);
            }
            outs.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (null != outs) {
                outs.close();
            }
            if (null != ins) {
                ins.close();
            }
        }
        //删除临时创建的压缩包
        boolean delete = file.delete();
        if (!delete) {
            logger.error("删除临时文件失败");
        }
        logger.info("下载成功");
        return "下载成功";
    }


    /**
     * 下载单个文件
     */
    @GetMapping("/downloadFile")
    @ResponseBody
    public String downloadImage(@RequestParam Long fileId, HttpServletResponse response) throws IOException {
        Long[] fileIds = {fileId};
        List<CmsFileDTO> cmsFiles = cmsFileService.selectFileImageByIds(fileIds);
        if (cmsFiles.size() <= 0) {
            return "请检查fileId是否存在";
        }
        boolean b = UploadUtil.downloadTrans(getServerPath(), cmsFiles.get(0).getRandomName(), cmsFiles.get(0).getFPath() + "&hadoopType=" + cmsFiles.get(0).getHadoopType());
        if (!b) {
            logger.info("从HADOOP下载到服务器失败");
            return "从HADOOP下载到服务器失败";
        }
        File file = new File(getServerPath() + cmsFiles.get(0).getRandomName());
        String fileName = URLEncoder.encode(cmsFiles.get(0).getFName(), "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
        response.setContentType("application/x-www-form-urlencoded");
        InputStream ins = null;
        OutputStream outs = null;
        try {
            ins = new FileInputStream(getServerPath() + cmsFiles.get(0).getRandomName());
            outs = response.getOutputStream();
            //写文件
            int byteRead = 0;
            byte[] buffer = new byte[8192];
            //开始向网络传输文件流
            while ((byteRead = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, byteRead);
            }
            outs.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (null != outs) {
                outs.close();
            }
            if (null != ins) {
                ins.close();
            }
        }
        //删除临时创建的压缩包
        boolean delete = file.delete();
        if (!delete) {
            logger.error("删除临时文件失败");
        }
        logger.info("下载成功");
        return "下载成功";
    }

    /**
     * 外部用预览图片接口
     */
    @GetMapping("/preview")
    @ResponseBody
    public AjaxResult preview(@RequestParam String token, HttpServletResponse response) throws IOException {
        logger.info("--------------------进入预览接口（安全认证）-----------------------");
        logger.info("--------------------传入的token为：" + token);
        // 解密
        String content = null;
        try {
            String replace = token.replace(" ", "+");
            byte[] bytes = AESUtils.decrypt(replace, Base64.decodeBase64(CmsConstants.PUBLIC_KEY));
            content = new String(bytes);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (null == content) {
            return AjaxResult.error("解析密文出错");
        }
        logger.info("解密后的明文信息为：" + content);
        String[] split = content.split(",");
        String sysCode = split[0];
        String authCode = split[1];
        Long fileId = Long.valueOf(split[2]);
        Long timeAfter = Long.valueOf(split[3]);
        long timeNow = System.currentTimeMillis();
        if (timeNow - timeAfter > 0) {
            return AjaxResult.error("已超过失效时间，请重试");
        }
        // 查询系统来源与鉴权码
        CmsSystem cmsSystem = cmsSystemService.selectCmsSystemByCode(sysCode);
        if (cmsSystem == null || !cmsSystem.getAuthentInfo().equals(authCode)) {
            return AjaxResult.error("此系统尚未接入,请先接入系统再进行上传操作");
        }
        Long[] fileIds = {fileId};
        List<CmsFileDTO> cmsFiles = cmsFileService.selectFileImageByIds(fileIds);
        if (cmsFiles.size() <= 0) {
            return AjaxResult.error("请检查fileId是否存在");
        }
        boolean b = UploadUtil.downloadTrans(getServerPath(), cmsFiles.get(0).getRandomName(), cmsFiles.get(0).getFPath() + "&hadoopType=" + cmsFiles.get(0).getHadoopType());
        if (!b) {
            logger.info("从HADOOP下载到服务器失败");
            return AjaxResult.error("从HADOOP下载到服务器失败");
        }
        File file = new File(getServerPath() + cmsFiles.get(0).getRandomName());
        String fileName = URLEncoder.encode(cmsFiles.get(0).getFName(), "UTF-8");
        //response.setHeader("Content-Type","image/jpg");
        InputStream ins = null;
        OutputStream outs = null;
        try {
            ins = new FileInputStream(getServerPath() + cmsFiles.get(0).getRandomName());
            outs = response.getOutputStream();
            //写文件
            int byteRead = 0;
            byte[] buffer = new byte[8192];
            //开始向网络传输文件流
            while ((byteRead = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, byteRead);
            }
            outs.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (null != outs) {
                outs.close();
            }
            if (null != ins) {
                ins.close();
            }
        }
        //删除临时创建的压缩包
        boolean delete = file.delete();
        if (!delete) {
            logger.error("删除临时文件失败");
        }
        logger.info("操作成功");
        return AjaxResult.success();
    }

    /**
     * 外部用下载文件接口
     */
    @GetMapping("/download")
    @ResponseBody
    public AjaxResult download(@RequestParam String token, HttpServletResponse response) throws IOException {
        logger.info("--------------------进入下载接口（安全认证）-----------------------");
        logger.info("--------------------传入的token为：" + token);
        // 解密
        String content = null;
        try {
            String replace = token.replace(" ", "+");
            byte[] bytes = AESUtils.decrypt(replace, Base64.decodeBase64(CmsConstants.PUBLIC_KEY));
            content = new String(bytes);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (null == content) {
            return AjaxResult.error("解析密文出错");
        }
        logger.info("解密后的明文信息为：" + content);
        String[] split = content.split(",");
        String sysCode = split[0];
        String authCode = split[1];
        Long fileId = Long.valueOf(split[2]);
        Long timeAfter = Long.valueOf(split[3]);
        if (System.currentTimeMillis() - timeAfter > 0) {
            return AjaxResult.error("已超过失效时间，请重试");
        }
        // 查询系统来源与鉴权码
        CmsSystem cmsSystem = cmsSystemService.selectCmsSystemByCode(sysCode);
        if (cmsSystem == null || !cmsSystem.getAuthentInfo().equals(authCode)) {
            return AjaxResult.error("此系统尚未接入,请先接入系统再进行上传操作");
        }
        Long[] fileIds = {fileId};
        List<CmsFileDTO> cmsFiles = cmsFileService.selectFileImageByIds(fileIds);
        if (cmsFiles.size() <= 0) {
            return AjaxResult.error("请检查fileId是否存在");
        }
        boolean b = UploadUtil.downloadTrans(getServerPath(), cmsFiles.get(0).getRandomName(), cmsFiles.get(0).getFPath() + "&hadoopType=" + cmsFiles.get(0).getHadoopType());
        if (!b) {
            logger.info("从HADOOP下载到服务器失败");
            return AjaxResult.error("从HADOOP下载到服务器失败");
        }
        File file = new File(getServerPath() + cmsFiles.get(0).getRandomName());
        String fileName = URLEncoder.encode(cmsFiles.get(0).getFName(), "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
        response.setContentType("application/x-www-form-urlencoded");
        InputStream ins = null;
        OutputStream outs = null;
        try {
            ins = new FileInputStream(getServerPath() + cmsFiles.get(0).getRandomName());
            outs = response.getOutputStream();
            //写文件
            int byteRead = 0;
            byte[] buffer = new byte[8192];
            //开始向网络传输文件流
            while ((byteRead = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, byteRead);
            }
            outs.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (null != outs) {
                outs.close();
            }
            if (null != ins) {
                ins.close();
            }
        }
        //删除临时创建的压缩包
        boolean delete = file.delete();
        if (!delete) {
            logger.error("删除临时文件失败");
        }
        logger.info("下载成功");
        return AjaxResult.success("下载成功");
    }

    /**
     * 外部系统加密接口
     *
     * @param json
     * @return
     */
    @PostMapping("/encrypt")
    @ResponseBody
    public AjaxResult encrypt(@RequestBody String json) {
        logger.info("--------------------进入下载和预览的授权接口-----------------------");
        JSONObject jsonObject = JSONObject.parseObject(json);
        if (null == jsonObject.get("sysCode") || "".equals(jsonObject.get("sysCode")) ||
                null == jsonObject.get("authCode") || "".equals(jsonObject.get("authCode")) ||
                null == jsonObject.get("fileId") || "".equals(jsonObject.get("fileId"))) {
            return AjaxResult.error("请检查请求参数是否完整");
        }
        // 获取当前时间
        long before = System.currentTimeMillis();
        Long timeOut;
        if (null != jsonObject.getLong("validity") && !"".equals(jsonObject.getLong("validity"))) {
            timeOut = before + Long.valueOf(String.valueOf(jsonObject.getLong("validity")));
        } else {
            timeOut = before + 600000L;
        }
        String content = jsonObject.get("sysCode") + "," + jsonObject.get("authCode") + "," + jsonObject.get("fileId") + "," + timeOut;
        byte[] key = Base64.decodeBase64(CmsConstants.PUBLIC_KEY);
        String token = null;
        try {
            token = AESUtils.encrypt(content, key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("报文加密成功,token为:" + token);
        return AjaxResult.success(token);
    }

    /**
     * 返回预览(是否需要改为Post方式)
     */
    @GetMapping("previewUrl")
    @ResponseBody
    public String previewUrl(Long fileId) {
        Long[] fileIds = {fileId};
        List<CmsFileDTO> cmsFiles = cmsFileService.selectFileImageByIds(fileIds);
        return "http://" + SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_IP) + ":" +
                SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT) + SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHDOWN) +
                cmsFiles.get(0).getFPath() + "&hadoopType=" + cmsFiles.get(0).getHadoopType();
    }

    /**
     * 下载文件(返回url)
     */
    @GetMapping("downloadFileUrl")
    @ResponseBody
    @LimitKey(frequency = 10, methodName = "dowloadFileUrl", url = "file/downloadFileUrl", paramKey = "fileId", timeout = 500L)
    public String dowloadFileUrl(Long fileId) {
        CmsFile cmsFile = cmsFileService.selectCmsFileById(fileId);
        return UploadUtil.downloadAPI(cmsFile.getFileId());
    }

    /**
     * 下载影像(返回url)
     */
    @GetMapping("downloadImageUrl")
    @ResponseBody
    @LimitKey(frequency = 10, methodName = "dowloadFileUrl", url = "file/downloadFileUrl", paramKey = "imageId", timeout = 500L)
    public String downloadImageUrl(Long imageId) {
        CmsImage cmsImage = cmsImageService.selectCmsImageById(imageId);
        String downloadUrl = UploadUtil.downloadAPI(cmsImage.getImageId());
        return downloadUrl;
    }

    /**
     * 下载文件(返回文件)
     * add by yinrui 2019-09-11
     */
    @RequestMapping("/downloadFileGet")
    @ResponseBody
    public void downloadFileGet(Long fileImageId, String type, HttpServletResponse response) throws IOException {

        String path = "";
        String fileName = "";
        String hadoopType = "";
        int count = 0;
        // 文件类型
        for (int i = 0; i < allowedFileExtension.length; i++) {
            if (allowedFileExtension[i].equals(type)) {
                CmsFile cmsFile = cmsFileService.selectCmsFileByFileId(fileImageId);
                path = cmsFile.getFilePath();
                fileName = cmsFile.getFileName();
                hadoopType = cmsFile.getHadoopType();
                count++;
                break;
            }
        }
        if (count == 0) {
            CmsImage cmsImage = cmsImageService.selectCmsImageByImageId(fileImageId);
            path = cmsImage.getImagePath();
            fileName = cmsImage.getImageName();
            hadoopType = cmsImage.getHadoopType();
        }
        // 临时本地服务器地址
        boolean b = UploadUtil.downloadTrans(getServerPath(), fileName, path + "&hadoopType=" + hadoopType);
        if (!b) {
            throw new RuntimeException("无该文件或影像");
        }
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        FileInputStream fis = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/force-download");
        try {
            response.setHeader("Content-disposition ", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            bos = new BufferedOutputStream(response.getOutputStream());
            fis = new FileInputStream(new File(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH), fileName));
            bis = new BufferedInputStream(fis);
            byte[] bytes = new byte[1024];
            int lenth = 0;
            while ((lenth = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, lenth);
                bos.flush();
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage(), e);
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

    /**
     * 批量下载文件(get方式)
     * add by yinrui 2019-09-20
     */
    @RequestMapping("/downloadFilesGet")
    @ResponseBody
    public void downloadFilesGet(String fileIds, HttpServletResponse response) throws Exception {

        List<CmsFile> cmsFiles = cmsFileService.selectCmsFileByIds(Convert.toLongArray(fileIds));
        List<CmsImage> cmsImages = cmsImageService.selectCmsImageByIdArray(Convert.toLongArray(fileIds));
        BufferedInputStream bis = null;
        /**
         * 临时zip名称
         */
        String zipName = "files.zip";
        ZipOutputStream zip = null;
        try (FileOutputStream fis = new FileOutputStream(getServerPath() + zipName)) {
            zip = new ZipOutputStream(fis);
            // 文件类型
            for (int i = 0; i < cmsFiles.size(); i++) {
                boolean b = UploadUtil.downloadTrans(getServerPath(), cmsFiles.get(i).getFileName(), cmsFiles.get(i).getFilePath() + "&hadoopType=" + cmsFiles.get(i).getHadoopType());
                if (!b) {
                    throw new RuntimeException("从HDFS下载到服务器失败");
                }
                try (FileInputStream fisNew = new FileInputStream(getServerPath() + cmsFiles.get(i).getFileName())) {
                    bis = new BufferedInputStream(fisNew);
                    String fileName = cmsFiles.get(i).getFileName();
                    if (i > 0 && fileName.equals(cmsFiles.get(i - 1).getFileName())) {
                        String[] split = cmsFiles.get(i).getFileName().split("\\.");
                        fileName = split[0] + "(" + i + ")." + split[1];
                    }
                    zip.putNextEntry(new ZipEntry(fileName));

                    byte[] bytes = new byte[1024];
                    int length = 0;
                    while ((length = bis.read(bytes)) != -1) {
                        zip.write(bytes, 0, length);
                    }
                } catch (IOException e) {
                    throw new IOException(e.getMessage(), e);
                } finally {
                    if (zip != null && cmsImages.size() == 0) {
                        zip.closeEntry();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null && cmsImages.size() == 0 && i == cmsFiles.size() - 1) {
                        fis.close();
                    }
                }
            }

            // 图片类型
            for (int i = 0; i < cmsImages.size(); i++) {
                boolean b = UploadUtil.downloadTrans(getServerPath(), cmsImages.get(i).getImageName(), cmsImages.get(i).getImagePath() + "&hadoopType=" + cmsImages.get(i).getHadoopType());
                if (!b) {
                    throw new RuntimeException("从HDFS下载到服务器失败");
                }
                try (FileInputStream fis1 = new FileInputStream(getServerPath() + cmsImages.get(i).getImageName())) {
                    bis = new BufferedInputStream(fis1);
                    String fileName = cmsImages.get(i).getImageName();
                    if (i > 0 && fileName.equals(cmsImages.get(i - 1).getImageName())) {
                        String[] split = cmsImages.get(i).getImageName().split("\\.");
                        fileName = split[0] + "(" + i + ")." + split[1];
                    }
                    zip.putNextEntry(new ZipEntry(fileName));
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    while ((length = bis.read(bytes)) != -1) {
                        zip.write(bytes, 0, length);
                    }
                } catch (Exception e) {
                    throw new Exception(e.getMessage(), e);
                } finally {
                    if (zip != null) {
                        zip.closeEntry();
                    }
                    if (fis != null && i == cmsImages.size() - 1) {
                        fis.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                }
            }
        } finally {
            if (null != zip) {
                zip.closeEntry();
            }
        }

        InputStream ins = null;
        OutputStream outs = null;
        File file = new File(getServerPath() + zipName);
        response.setHeader("Content-Disposition", "attachment;filename=\"fwz.zip\"");
        response.setContentType("application/zip");
        try {
            ins = new FileInputStream(getServerPath() + zipName);
            outs = response.getOutputStream();
            //写文件
            int byteRead = 0;
            byte[] buffer = new byte[8192];
            //开始向网络传输文件流
            while ((byteRead = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, byteRead);
            }
            outs.flush();
        } catch (IOException e) {
            throw new IOException(e.getMessage(), e);
        } finally {
            if (ins != null) {
                ins.close();
            }
            if (outs != null) {
                outs.close();
            }
        }
        //删除临时创建的压缩包
        if (!file.delete()) {
            throw new FileNotFoundException("文件删除失败");
        }
    }

    /**
     * 项目文件全量下载(返回zip)
     * add by yinrui 2019-09-20
     */
    @GetMapping("/downloadAllFile")
    @ResponseBody
    public String downloadAllFile(String pmsBatchId, String fileIds, HttpServletResponse response) throws IOException {

        // 选中的文件fileId的list
        List<Long> fileImageIds = new ArrayList<>(Arrays.asList(Convert.toLongArray(fileIds)));
        // 获取模型编号 获得该模型下所有的分类节点
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(Long.valueOf(pmsBatchId));
        List<Long> dataRoleIds = cmsUserRoleService.selectDataRoleIdsByUserId(ShiroUtils.getSysUser().getUserId());
        List<CmsBill> cmsBillList = pmsBatchService.getBillList(pmsBatch.getModelList(), dataRoleIds);
        String projectName = pmsBatch.getOperationCode();
        // 获得分类节点的Map
        Map<Long, CmsBill> billMap = cmsBillList.stream().collect(Collectors.toMap(t -> t.getId(), t -> t));
        for (CmsBill cmsBill : cmsBillList) {
            if ("0".equals(cmsBill.getLeaf())) {
                String downPath = "/" + cmsBill.getBillName();
                if ("0".equals(cmsBill.getLeaf())) { // 0,75,79
                    String[] billIdArray = Convert.toStrArray(cmsBill.getAllPath());
                    for (int i = billIdArray.length - 1; i > 0; i--) {
                        if (!"0".equals(billIdArray[i])) {
                            CmsBill bill = billMap.get(Long.valueOf(billIdArray[i]));
                            downPath = "/" + bill.getBillName() + downPath;
                        }
                    }
                }
                downPath = getServerPath() + projectName + downPath;
                File file = new File(downPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 将叶子节点下的文件下载到本地应用服务器
                List<CmsFile> fileList = cmsFileService.selectFilesByCondition(pmsBatch.getBatchId(), cmsBill.getId());
                if (fileList != null && fileList.size() > 0) {
                    for (CmsFile cmsFile : fileList) {
                        if (fileImageIds.contains(cmsFile.getFileId())) {
                            boolean b = UploadUtil.downloadTrans(downPath, cmsFile.getFileName(), cmsFile.getFilePath() + "&hadoopType=" + cmsFile.getHadoopType());
                        }
                    }
                }
                List<CmsImage> imageList = cmsImageService.selectImageByCondition(pmsBatch.getBatchId(), cmsBill.getId());
                if (imageList != null && imageList.size() > 0) {
                    for (CmsImage cmsImage : imageList) {
                        if (fileImageIds.contains(cmsImage.getImageId())) {
                            boolean b = UploadUtil.downloadTrans(downPath, cmsImage.getImageName(), cmsImage.getImagePath() + "&hadoopType=" + cmsImage.getHadoopType());
                        }
                    }
                }
            }
        }

        // 将创建好的文件路面结构打成zip
        ZipUtil.zip(getServerPath() + projectName);
        // 删除创建好的文件夹
        FileUtil.del(getServerPath() + projectName);

        InputStream ins = null;
        OutputStream outs = null;
        File removeFile = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=\"" + projectName + ".zip\"");
            response.setContentType("application/zip");
            ins = new FileInputStream(getServerPath() + projectName + ".zip");
            outs = response.getOutputStream();
            //写文件
            int byteRead = 0;
            byte[] buffer = new byte[8192];
            //开始向网络传输文件流
            while ((byteRead = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, byteRead);
            }
            outs.flush();
            removeFile = new File(getServerPath() + projectName + ".zip");
        } catch (FileNotFoundException e) {
            logger.error("文件未找到", e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (ins != null) {
                ins.close();
            }
            if (outs != null) {
                outs.close();
            }
            //删除临时创建的压缩包
            if (removeFile != null) {
                boolean delete = removeFile.delete();
                if (!delete) {
                    logger.error("删除临时创建的压缩包失败");
                }
            }
        }
        return "下载成功";
    }

    /**
     * 上传元数据，保存元数据，
     * 根据md5值检验文件是否上传过，是则取已有path，
     * 并返回已上传列表
     *
     * @param jsonStr 元数据
     */
    @PostMapping("/uploadMetadata")
    @ResponseBody
    public AjaxResult uploadMetadata(@RequestBody String jsonStr) {
        logger.info("Starting to receive metadata...");
        // 保存元数据，根据md5值检查文件是否上传过
        // 是则取已有path
        // 返回秒传文件列表
        JSONArray fileExistsList = new JSONArray();
        JSONObject fileExists = new JSONObject(true);
        fileExistsList.add(fileExists);
        fileExists.put("fileName", "test.txt");
        fileExists.put("filePath", "http://baidu.com");
        fileExists.put("fileId", 11);
        return AjaxResult.success(fileExistsList);
    }

    /**
     * 分段上传多个文件，并校验md5
     *
     * @
     */
    @PostMapping("/uploadMulti")
    @ResponseBody
    public AjaxResult uploadMulti(HttpServletRequest request, HttpServletResponse response) {
        // 文件名
        String fileName = null;
        try {
            fileName = URLDecoder.decode(request.getHeader("FileName"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        // 缓冲区大小
        int bufferLength = request.getIntHeader("BufferLength");
        // md5校验值
        String md5 = request.getHeader("Md5");
        // 根据md5值关联到之前提交的元数据，拿到业务编号
        String ywbh = "";
        String[] split = fileName.split("\\.");
        String randomName = UUID.randomUUID().toString().replace("-", "") + "." + split[split.length - 1];
        response.setCharacterEncoding("UTF-8");
        long start = System.currentTimeMillis();
        logger.info("Starting to receive {} ...", fileName);
        InputStream fileIn = null;
        InputStream is = null;
        FileOutputStream fos = null;
        byte[] buffer = new byte[bufferLength];
        byte[] tmpBuff = new byte[0];
        byte[] oldBuff = new byte[0];
        int len = 0;
        long total = 0L;
        // 上传文件保存在/ywbh/
        File parent = new File(getServerPath());
        if (!parent.exists()) {
            parent.mkdirs();
        }
        parent = new File(getServerPath(), ywbh);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File file = new File(parent, randomName);
        if (file.exists()) {
            boolean tag = file.delete();
            if (!tag) {
                logger.error("删除临时文件失败");
            }
        }
        try {
            boolean tag = file.createNewFile();
            if (!tag) {
                logger.error("创建新文件失败");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            try {
                return AjaxResult.error(URLEncoder.encode("创建新文件错误！", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                logger.error(e1.getMessage());
            }
        }
        try {
            is = request.getInputStream();
            fos = new FileOutputStream(file, true);
            while ((len = is.read(buffer)) != -1) {
                if (null != oldBuff && oldBuff.length > 0) {
                    // 如果剩下的字节加上新读取的字节长度大于BUFFER_LENGTH，
                    // 可以写文件，否则继续等待
                    if (oldBuff.length + len >= bufferLength) {
                        tmpBuff = new byte[bufferLength];
                        System.arraycopy(oldBuff, 0, tmpBuff,
                                0, oldBuff.length);
                        System.arraycopy(buffer, 0, tmpBuff,
                                oldBuff.length, bufferLength - oldBuff.length);
                        if (oldBuff.length + len > bufferLength) {
                            oldBuff = Arrays.copyOfRange(buffer,
                                    bufferLength - oldBuff.length, len);
                        } else {
                            oldBuff = null;
                        }
                        len = bufferLength;
                    } else {
                        tmpBuff = new byte[oldBuff.length + len];
                        System.arraycopy(oldBuff, 0, tmpBuff,
                                0, oldBuff.length);
                        System.arraycopy(buffer, 0, tmpBuff,
                                oldBuff.length, len);
                        oldBuff = tmpBuff;
                        tmpBuff = null;
                    }
                } else if (len == bufferLength) {
                    // 没有剩余字节，buffer读满，可以写文件
                    tmpBuff = Arrays.copyOf(buffer, len);
                } else {
                    // buffer未满，不写文件
                    oldBuff = Arrays.copyOf(buffer, len);
                }
                if (len == bufferLength) {
                    if (null != tmpBuff) {
                        fos.write(tmpBuff, 0, tmpBuff.length);
                        total += tmpBuff.length;
                    }
                }
            }
            if (null != oldBuff && oldBuff.length > 0) {
                fos.write(oldBuff, 0, oldBuff.length);
                total += oldBuff.length;
            }
            if (null != is) {
                is.close();
            }
            if (null != fos) {
                fos.close();
            }

            if (md5.equals(getMd5ByFile(file))) {
                logger.info("[{}]MD5校验成功！", fileName);
                long end = System.currentTimeMillis();
                logger.info("Used " + (end - start) + "ms in receiving {}.", file.getAbsolutePath());

                List<CmsFile> cmsFile1 = cmsFileService.selectCmsFileByMd5(md5, Integer.valueOf(Constants.UPLOAD_ING));
                List<CmsImage> cmsImage1 = cmsImageService.selectCmsImageByMd5(md5, Integer.valueOf(Constants.UPLOAD_ING));
                JSONObject jsonObject = new JSONObject(true);
                if (cmsFile1.size() == 0 && cmsImage1.size() == 0) {
                    // 返回
                    jsonObject.put("resultCode", ResultCode.UPLOAD_FAIL.code());
                    jsonObject.put("fileId", "");
                    jsonObject.put("filePath", "");
                    jsonObject.put("fileName", "");
                    jsonObject.put("orderNum", "");
                    jsonObject.put("fileDownloadUrl", "");
                    jsonObject.put("filePreviewUrl", "");
                    logger.info("[ " + fileName + " ] 没有成功结构化，请检查原因");
                    return AjaxResult.error(ResultCode.UPLOAD_FAIL.msg(), jsonObject);
                }
                // 保存到filetrans的文件路径
                String willPath;
                // 存放去向
                String hadoopType;
                boolean b = false;
                // 若小于10M，使用hbase，大于10M不变
                if (file.length() < CmsConstants.TEN_MB) {
                    willPath = UUID.randomUUID().toString().replace("-", "");
                    hadoopType = CmsConstants.USEHBASE;
                    fileIn = new FileInputStream(file);
                    b = UploadUtil.uploadTransStream(willPath + "&hadoopType=0", fileIn, 65536, 65536, 1);//uploadTrans(getServerPath(),randomName,willPath+"&hadoopType=0");
                }
                // 上传文件到大数据，并更新元数据
                else {
                    willPath = getHdfsPath() + randomName;
                    hadoopType = CmsConstants.USERHDFS;
                    fileIn = new FileInputStream(file);
                    b = UploadUtil.uploadTransStream(willPath + "&hadoopType=1", fileIn, 65536, 65536, 1);
                }
                if (b) {
                    if (cmsFile1.size() != 0 && cmsImage1.size() == 0) {
                        if (cmsFile1.size() > 1) {
                            for (int i = 1; i < cmsFile1.size(); i++) {
                                // 与最新的文件相同项目分类的文件状态设置为失败(极小概率导致状态为正在上传的文件)
                                if (cmsFile1.get(i).getBillId().equals(cmsFile1.get(0).getBillId()) && cmsFile1.get(i).getBatchId().equals(cmsFile1.get(0).getBatchId())) {
                                    cmsFile1.get(i).setStatus(String.valueOf(Constants.UPLOAD_FAIL));
                                }
                            }
                        }
                        BigDecimal bd = new BigDecimal(file.length());
                        BigDecimal kb = new BigDecimal(1024);
                        cmsFile1.get(0).setFileSize(bd.divide(kb, 2, BigDecimal.ROUND_UP));
                        cmsFile1.get(0).setStatus(String.valueOf(Constants.UPLOAD_OVER));
                        cmsFile1.get(0).setRandomName(randomName);
                        cmsFile1.get(0).setFilePath(willPath);
                        cmsFile1.get(0).setHadoopType(hadoopType);
                        cmsFile1.get(0).setSysZone(SysConfigInitParamsUtils.getConfig(CmsConstants.SYS_ZONE));
                        cmsFileService.updateCmsFile(cmsFile1.get(0));
                        jsonObject.put("resultCode", ResultCode.UPLOAD_SUCCESS.code());
                        jsonObject.put("fileId", cmsFile1.get(0).getFileId());
                        jsonObject.put("filePath", cmsFile1.get(0).getFilePath());
                        // 返回
                        jsonObject.put("fileName", fileName);
                        jsonObject.put("orderNum", cmsFile1.get(0).getOrderNum());
                        jsonObject.put("fileDownloadUrl", UploadUtil.downloadAPI(cmsFile1.get(0).getFileId()));
                        jsonObject.put("filePreviewUrl", UploadUtil.pdfUrl(String.valueOf(cmsFile1.get(0).getFileId()), cmsFile1.get(0).getFileType(), cmsFile1.get(0).getFilePath(), cmsFile1.get(0).getHadoopType()));
                        logger.info("[ " + fileName + " ] 上传HDFS成功");
                        // 若是ams_2001接口，通过feign保存到档案的im_file表中
                        PmsBatch batch = pmsBatchService.selectPmsBatchByBatchId(cmsFile1.get(0).getBatchId());
                        if (batch.getMetadata().contains(Constants.AMS_UPLOAD)) {
                            JSONParser parser = new JSONParser(batch.getMetadata());
                            JSONObject jojo = new JSONObject(parser.parseMap());
                            JSONObject imFileObject = new JSONObject(true);
                            imFileObject.put("filename", cmsFile1.get(0).getFileName());
                            imFileObject.put("size", cmsFile1.get(0).getFileSize());
                            imFileObject.put("arcNo", jojo.get("arcNo"));
                            imFileObject.put("transfilepath", cmsFile1.get(0).getFilePath());
                            imFileObject.put("billId", cmsFile1.get(0).getBillId());
                            imFileObject.put("remark", cmsFile1.get(0).getRemark());
                            imFileObject.put("createuser", cmsFile1.get(0).getCreateUser());
                            amsBatchRecordFeignClient.saveFilesProcess(imFileObject.toJSONString());
                        }
                        return AjaxResult.success(jsonObject);
                    }
                    if (cmsFile1.size() == 0 && cmsImage1.size() != 0) {
                        if (cmsImage1.size() > 1) {
                            for (int i = 1; i < cmsImage1.size(); i++) {
                                // 与最新的文件相同项目分类的文件状态设置为失败(极小概率导致状态为正在上传的文件)
                                if (cmsImage1.get(i).getBillId().equals(cmsImage1.get(0).getBillId()) && cmsImage1.get(i).getBatchId().equals(cmsImage1.get(0).getBatchId())) {
                                    cmsImage1.get(i).setStatus(String.valueOf(Constants.UPLOAD_FAIL));
                                }
                            }
                        }
                        BigDecimal bd = new BigDecimal(file.length());
                        BigDecimal kb = new BigDecimal(1024);
                        cmsImage1.get(0).setImageSize(bd.divide(kb, 2, BigDecimal.ROUND_UP));
                        cmsImage1.get(0).setStatus(String.valueOf(Constants.UPLOAD_OVER));
                        cmsImage1.get(0).setRandomName(randomName);
                        cmsImage1.get(0).setImagePath(willPath);
                        cmsImage1.get(0).setHadoopType(hadoopType);
                        cmsImage1.get(0).setSysZone(SysConfigInitParamsUtils.getConfig(CmsConstants.SYS_ZONE));
                        cmsImageService.updateCmsImage(cmsImage1.get(0));
                        jsonObject.put("resultCode", ResultCode.UPLOAD_SUCCESS.code());
                        jsonObject.put("fileId", cmsImage1.get(0).getImageId());
                        jsonObject.put("filePath", cmsImage1.get(0).getImagePath());
                        // 返回
                        jsonObject.put("fileName", fileName);
                        jsonObject.put("orderNum", cmsImage1.get(0).getOrderNum());
                        jsonObject.put("fileDownloadUrl", UploadUtil.downloadAPI(cmsImage1.get(0).getImageId()));
                        jsonObject.put("filePreviewUrl", UploadUtil.pdfUrl(String.valueOf(cmsImage1.get(0).getImageId()), cmsImage1.get(0).getOcrType(), cmsImage1.get(0).getImagePath(), cmsImage1.get(0).getHadoopType()));
                        logger.info("[ " + fileName + " ] 上传HDFS成功");
                        PmsBatch batch = pmsBatchService.selectPmsBatchByBatchId(cmsImage1.get(0).getBatchId());
                        if (batch.getMetadata().contains(Constants.AMS_UPLOAD)) {
                            JSONParser parser = new JSONParser(batch.getMetadata());
                            JSONObject jojo = new JSONObject(parser.parseMap());
                            JSONObject imImageObject = new JSONObject(true);
                            imImageObject.put("filename", cmsImage1.get(0).getImageName());
                            imImageObject.put("size", cmsImage1.get(0).getImageSize());
                            imImageObject.put("arcNo", jojo.get("arcNo"));
                            imImageObject.put("transfilepath", cmsImage1.get(0).getImagePath());
                            imImageObject.put("billId", cmsImage1.get(0).getBillId());
                            imImageObject.put("remark", cmsImage1.get(0).getRemark());
                            imImageObject.put("createuser", cmsImage1.get(0).getCreateUser());
                            amsBatchRecordFeignClient.saveImageProcess(imImageObject.toJSONString());
                        }
                        return AjaxResult.success(jsonObject);
                    }
                } else {
                    if (cmsFile1.size() != 0 && cmsImage1.size() == 0) {
                        if (cmsFile1.size() > 1) {
                            for (int i = 1; i < cmsFile1.size(); i++) {
                                // 与最新的文件相同项目分类的文件状态设置为失败(极小概率导致状态为正在上传的文件)
                                if (cmsFile1.get(i).getBillId().equals(cmsFile1.get(0).getBillId()) && cmsFile1.get(i).getBatchId().equals(cmsFile1.get(0).getBatchId())) {
                                    cmsFile1.get(i).setStatus(String.valueOf(Constants.UPLOAD_FAIL));
                                }
                            }
                        }
                        BigDecimal bd = new BigDecimal(file.length());
                        BigDecimal kb = new BigDecimal(1024);
                        cmsFile1.get(0).setFileSize(bd.divide(kb, 2, BigDecimal.ROUND_UP));
                        cmsFile1.get(0).setStatus(String.valueOf(Constants.UPLOAD_FAIL));
                        cmsFile1.get(0).setRandomName(randomName);
                        cmsFile1.get(0).setHadoopType(hadoopType);
                        cmsFile1.get(0).setSysZone(SysConfigInitParamsUtils.getConfig(CmsConstants.SYS_ZONE));
                        cmsFileService.updateCmsFile(cmsFile1.get(0));
                        jsonObject.put("fileName", fileName);
                        logger.info("[ " + fileName + " ] 上传HDFS失败");
                        PmsBatch batch = pmsBatchService.selectPmsBatchByBatchId(cmsFile1.get(0).getBatchId());
                        if (batch.getMetadata().contains(Constants.AMS_UPLOAD)) {
                            JSONParser parser = new JSONParser(batch.getMetadata());
                            JSONObject jojo = new JSONObject(parser.parseMap());
                            JSONObject imFileObject = new JSONObject(true);
                            imFileObject.put("filename", cmsFile1.get(0).getFileName());
                            imFileObject.put("size", cmsFile1.get(0).getFileSize());
                            imFileObject.put("arcNo", jojo.get("arcNo"));
                            imFileObject.put("transfilepath", "");
                            imFileObject.put("billId", cmsFile1.get(0).getBillId());
                            imFileObject.put("remark", cmsFile1.get(0).getRemark());
                            imFileObject.put("createuser", cmsFile1.get(0).getCreateUser());
                            amsBatchRecordFeignClient.saveFilesProcess(imFileObject.toJSONString());
                        }
                        return AjaxResult.error("上传失败: " + jsonObject.toString());
                    }
                    if (cmsFile1.size() == 0 && cmsImage1.size() != 0) {
                        if (cmsImage1.size() > 1) {
                            for (int i = 1; i < cmsImage1.size(); i++) {
                                // 与最新的文件相同项目分类的文件状态设置为失败(极小概率导致状态为正在上传的文件)
                                if (cmsImage1.get(i).getBillId().equals(cmsImage1.get(0).getBillId()) && cmsImage1.get(i).getBatchId().equals(cmsImage1.get(0).getBatchId())) {
                                    cmsImage1.get(i).setStatus(String.valueOf(Constants.UPLOAD_FAIL));
                                }
                            }
                        }
                        BigDecimal bd = new BigDecimal(file.length());
                        BigDecimal kb = new BigDecimal(1024);
                        cmsImage1.get(0).setImageSize(bd.divide(kb, 2, BigDecimal.ROUND_UP));
                        cmsImage1.get(0).setStatus(String.valueOf(Constants.UPLOAD_FAIL));
                        cmsImage1.get(0).setRandomName(randomName);
                        cmsImage1.get(0).setHadoopType(hadoopType);
                        cmsImage1.get(0).setSysZone(SysConfigInitParamsUtils.getConfig(CmsConstants.SYS_ZONE));
                        cmsImageService.updateCmsImage(cmsImage1.get(0));
                        jsonObject.put("fileName", fileName);
                        logger.info("[ " + fileName + " ] 上传HDFS失败");
                        PmsBatch batch = pmsBatchService.selectPmsBatchByBatchId(cmsFile1.get(0).getBatchId());
                        if (batch.getMetadata().contains(Constants.AMS_UPLOAD)) {
                            JSONParser parser = new JSONParser(batch.getMetadata());
                            JSONObject jojo = new JSONObject(parser.parseMap());
                            JSONObject imImageObject = new JSONObject(true);
                            imImageObject.put("filename", cmsImage1.get(0).getImageName());
                            imImageObject.put("size", cmsImage1.get(0).getImageSize());
                            imImageObject.put("arcNo", jojo.get("arcNo"));
                            imImageObject.put("transfilepath", "");
                            imImageObject.put("billId", cmsImage1.get(0).getBillId());
                            imImageObject.put("remark", cmsImage1.get(0).getRemark());
                            imImageObject.put("createuser", cmsImage1.get(0).getCreateUser());
                            amsBatchRecordFeignClient.saveImageProcess(imImageObject.toJSONString());
                        }
                        return AjaxResult.error("上传失败: " + jsonObject.toString());
                    }
                }
            } else {
                return AjaxResult.error(URLEncoder.encode("MD5校验失败！", "UTF-8"));
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            try {
                return AjaxResult.error(URLEncoder.encode(e.getMessage(), "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                logger.error(e1.getMessage());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            try {
                return AjaxResult.error(URLEncoder.encode(e.getMessage(), "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                logger.error(e1.getMessage());
            }
        } finally {
            if (null != fileIn) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }

        return null;
    }

    /**
     * 文件md5加密
     *
     * @param file
     * @return
     */
    private String getMd5ByFile(File file) {
        String value = "";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            MappedByteBuffer byteBuffer = fis.getChannel().map(
                    FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return value;
    }


    public static void main(String[] args) throws UnknownHostException {
        String aa = "air,ris,";
        String[] split = aa.split(",");
        System.out.println(split[split.length - 1]);
        System.out.println(split.length);
        System.out.println(split[0]);
        System.out.println(split[1]);
        System.out.println(null == split[2]);
    }

    private String getServerPath() {
        if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
            System.out.println("本地上传地址：" + CmsConstants.WINDOWS_SERVER_PATH);
//            return SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_SERVER_PATH);
            String path = "F:\\filepath\\";
            return path;
        } else {
//            logger.info("服务器上传地址 {}"+CmsConstants.SERVER_PATH);
            String path = this.configService.selectConfigByKey("linux.url");
            logger.info("服务器上传地址 {}" + path);
//            return SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH);
            return path;
        }
    }

    private String getHdfsPath() {
        if (StringUtils.isEmpty(hdfsPath)) {
            hdfsPath = SysConfigInitParamsUtils.getConfig(CmsConstants.HDFS_PATH) + new SimpleDateFormat("yyyy/MMdd/").format(new Date());
        }

        return hdfsPath;
    }

    /**
     * 获取本地上传或下载文件的路径 add by yinrui 2019-10-31
     * windows D:xx/xx/
     * linux /xx/xx/xx/
     *
     * @return
     */
    private static String getServerLocalPath() {
        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_SERVER_PATH) : SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH);
    }
}
