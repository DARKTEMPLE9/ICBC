package net.northking.iacmp.cms.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import iacmp.biz.common.dao.mapper.cms.*;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.IHttpService;
import net.northking.iacmp.common.bean.domain.cms.*;
import net.northking.iacmp.common.bean.dto.cms.CmsFileDTO;
import net.northking.iacmp.common.bean.feign.cms.AmsBatchRecordFeignClient;
import net.northking.iacmp.common.bean.vo.cms.CmsFileVO;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.constant.PmsConstants;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.framework.util.UploadUtil;
import net.northking.iacmp.result.ResultCode;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.domain.SysUserRole;
import net.northking.iacmp.system.mapper.SysDeptMapper;
import net.northking.iacmp.system.mapper.SysRoleMapper;
import net.northking.iacmp.system.mapper.SysUserMapper;
import net.northking.iacmp.system.mapper.SysUserRoleMapper;
import net.northking.iacmp.utils.SnowFlakeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:统一服务
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/26
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class HttpServiceImpl implements IHttpService {

    private static final String BILL_PARENT_CODE = "billParentCode";
    private static final String COMPLETE = "complete";
    private static Logger logger = LoggerFactory.getLogger(HttpServiceImpl.class);
    private static final String FILE_LIST = "fileList";
    private static final String FILENAME = "filename";
    private static final String BATCH_ID = "batchId";
    private static final String TRANS_FILEPATH = "transfilepath";
    private static final String FILE_TYPE = "fileType";
    private static final String BILL_ID = "billId";
    private static final String REMARK = "remark";
    private static final String CREATE_USER = "createuser";
    private static final String RESULT_CODE = "resultCode";
    private static final String ORDER_NUM = "orderNum";
    private static final String FILE_ID = "fileId";
    private static final String FILE_NAME = "fileName";
    private static final String FILE_PATH = "filePath";
    private static final String FILE_DOWNLOAD_URL = "fileDownloadUrl";
    private static final String FILE_PREVIEW_URL = "filePreviewUrl";
    private static final String TECHNOLOGY_PROJECT_MANAGER = "technologyProjectManager";
    private static final String PROJECT_PRODUCT_MANAGER = "projectProductManager";
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

    @Autowired
    private AmsBatchRecordFeignClient amsBatchRecordFeignClient;

    @Autowired
    private CmsFileMapper cmsFileMapper;

    @Autowired
    private CmsImageMapper cmsImageMapper;

    @Autowired
    private PmsBatchMapper pmsBatchMapper;

    @Autowired
    private CmsBillMapper cmsBillMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;

    /**
     * 档案著录上传结构化
     *
     * @param jsonObject
     * @param json
     * @return
     */
    @Override
    public JSONArray amsUpload(JSONObject jsonObject, String json) {
        logger.info("----------------开始进行档案著录上传的数据结构化----------------------");
        JSONArray fileArray = new JSONArray();
        JSONObject fileJson = new JSONObject(true);
        JSONArray list = jsonObject.getJSONArray(FILE_LIST);
        boolean amsBatchOk = false;
        for (int i = 0; i < list.size(); i++) {
            Long fileId = null;
            String fileName = null;
            String path = null;
            String type = null;
            Long orderNum = null;
            Long batchId = null;
            Integer billId = null;
            String remark = null;
            Long createUser = null;
            String hadoopType = null;
            // 查询数据库是否有该MD5
            logger.info("check file according to Md5 and status...");
            List<CmsFile> cmsFile = cmsFileMapper.selectCmsFileByMd5(list.getJSONObject(i).getString(CmsConstants.MD5), String.valueOf(Constants.UPLOAD_OVER));
            List<CmsImage> cmsImage = cmsImageMapper.selectCmsImageByMd5(list.getJSONObject(i).getString(CmsConstants.MD5), Constants.UPLOAD_OVER);
            if (cmsImage.isEmpty() && !cmsFile.isEmpty()) {
                // 若文件内容相同但文件名不同
                if (!list.getJSONObject(i).get(CmsConstants.FILENAME).equals(cmsFile.get(0).getFileName())) {
                    SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils(Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.WORKER_ID)), Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.DATACENTER_ID)));
                    PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchByOpt(String.valueOf(list.getJSONObject(i).get(CmsConstants.OPERATIONCODE)));
                    if (pmsBatch == null) {
                        pmsBatch = savePmsBatch(snowFlakeUtils, list.getJSONObject(i), json, jsonObject);
                    }
                    CmsFile file = new CmsFile();
                    file.setFileId(snowFlakeUtils.nextId());
                    file.setFileName(list.getJSONObject(i).get(CmsConstants.FILENAME).toString());
                    file.setBatchId(pmsBatch.getBatchId());
                    file.setRandomName(cmsFile.get(0).getRandomName());
                    file.setFilePath(cmsFile.get(0).getFilePath());
                    file.setCreateTime(new Date());
                    file.setUpdateTime(new Date());
                    file.setHadoopType(cmsFile.get(0).getHadoopType());
                    file.setFileSysCode(pmsBatch.getSysCode());
                    if (list.getJSONObject(i).get(CmsConstants.ORDERNUM) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.ORDERNUM))) {
                        file.setOrderNum(Long.valueOf(String.valueOf(list.getJSONObject(i).get(CmsConstants.ORDERNUM))));
                    }
                    file.setStatus(String.valueOf(Constants.UPLOAD_OVER));
                    String[] split = list.getJSONObject(i).get(CmsConstants.FILENAME).toString().split("\\.");
                    file.setFileType(split[split.length - 1]);
                    if (list.getJSONObject(i).get(CmsConstants.BILLCODE) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.BILLCODE))) {
                        CmsBill cmsBill = cmsBillMapper.selectCmsBillByCode(String.valueOf(list.getJSONObject(i).get(CmsConstants.BILLCODE)));
                        file.setBillId(cmsBill.getId().intValue());
                    }
                    file.setRemark((String) list.getJSONObject(i).get(CmsConstants.REMARK));
                    if (list.getJSONObject(i).get(CmsConstants.CREATEUSER) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.CREATEUSER))) {
                        file.setCreateBy(String.valueOf(list.getJSONObject(i).get(CmsConstants.CREATEUSER)));
                    }
                    file.setFileSource((String) list.getJSONObject(i).get(CmsConstants.FILESOURCE));
                    file.setVersion(CmsConstants.VERSION);
                    file.setMetadata(json);
                    file.setFileSysCode(pmsBatch.getSysCode());
                    file.setDeptName(pmsBatch.getDeptName());
                    /*if (pmsBatch.getModelList() != null && !"".equals(pmsBatch.getModelList())) {
                        file.setModelId(pmsBatch.getModelList());
                    }*/
                    file.setMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                    cmsFileMapper.insertCmsFile(file);
                    fileId = file.getFileId();
                    fileName = file.getFileName();
                    path = file.getFilePath();
                    type = file.getFileType();
                    hadoopType = file.getHadoopType();
                    if (file.getOrderNum() != null) {
                        orderNum = file.getOrderNum();
                    }
                    batchId = file.getBatchId();
                    billId = file.getBillId();
                    remark = file.getRemark();
                    createUser = file.getCreateUser();
                } else {
                    fileId = cmsFile.get(0).getFileId();
                    fileName = cmsFile.get(0).getFileName();
                    path = cmsFile.get(0).getFilePath();
                    type = cmsFile.get(0).getFileType();
                    hadoopType = cmsFile.get(0).getHadoopType();
                    if (cmsFile.get(0).getOrderNum() != null) {
                        orderNum = cmsFile.get(0).getOrderNum();
                    }
                    batchId = cmsFile.get(0).getBatchId();
                    billId = cmsFile.get(0).getBillId();
                    remark = cmsFile.get(0).getRemark();
                    createUser = cmsFile.get(0).getCreateUser();
                }
            }
            if (!cmsImage.isEmpty() && cmsFile.isEmpty()) {
                // 若文件内容相同但文件名不同
                if (!list.getJSONObject(i).get(CmsConstants.FILENAME).equals(cmsImage.get(0).getImageName())) {
                    SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils(Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.WORKER_ID)), Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.DATACENTER_ID)));
                    PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchByOpt(String.valueOf(list.getJSONObject(i).get(CmsConstants.OPERATIONCODE)));
                    if (pmsBatch == null) {
                        pmsBatch = savePmsBatch(snowFlakeUtils, list.getJSONObject(i), json, jsonObject);
                    }
                    CmsImage image = new CmsImage();
                    image.setImageId(snowFlakeUtils.nextId());
                    image.setImageName(list.getJSONObject(i).get(CmsConstants.FILENAME).toString());
                    image.setBatchId(pmsBatch.getBatchId());
                    image.setRandomName(cmsImage.get(0).getRandomName());
                    image.setImagePath(cmsImage.get(0).getImagePath());
                    image.setCreateTime(new Date());
                    image.setUpdateTime(new Date());
                    image.setImageSysCode(pmsBatch.getSysCode());
                    image.setHadoopType(cmsImage.get(0).getHadoopType());
                    if (list.getJSONObject(i).get(CmsConstants.ORDERNUM) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.ORDERNUM))) {
                        image.setOrderNum(Long.valueOf(String.valueOf(list.getJSONObject(i).get(CmsConstants.ORDERNUM))));
                    }
                    image.setStatus(String.valueOf(Constants.UPLOAD_OVER));
                    String[] split = list.getJSONObject(i).get(CmsConstants.FILENAME).toString().split("\\.");
                    image.setOcrType(split[split.length - 1]);
                    if (list.getJSONObject(i).get(CmsConstants.BILLCODE) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.BILLCODE))) {
                        CmsBill cmsBill = cmsBillMapper.selectCmsBillByCode(String.valueOf(list.getJSONObject(i).get(CmsConstants.BILLCODE)));
                        image.setBillId(cmsBill.getId().intValue());
                    }
                    image.setRemark((String) list.getJSONObject(i).get(CmsConstants.REMARK));
                    if (list.getJSONObject(i).get(CmsConstants.CREATEUSER) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.CREATEUSER))) {
                        image.setCreateBy(String.valueOf(list.getJSONObject(i).get(CmsConstants.CREATEUSER)));
                    }
                    image.setImageSource((String) list.getJSONObject(i).get(CmsConstants.FILESOURCE));
                    image.setVersion(CmsConstants.VERSION);
                    image.setMetadata(json);
                    image.setImageSysCode(pmsBatch.getSysCode());
                    image.setDeptName(pmsBatch.getDeptName());
                    /*if (pmsBatch.getModelList() != null && !"".equals(pmsBatch.getModelList())) {
                        image.setModelId(pmsBatch.getModelList());
                    }*/
                    image.setMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                    cmsImageMapper.insertCmsImage(image);
                    fileId = image.getImageId();
                    fileName = image.getImageName();
                    path = image.getImagePath();
                    type = image.getOcrType();
                    hadoopType = image.getHadoopType();
                    if (image.getOrderNum() != null) {
                        orderNum = image.getOrderNum();
                    }
                    batchId = image.getBatchId();
                    billId = image.getBillId();
                    remark = image.getRemark();
                    createUser = image.getCreateUser();
                } else {
                    fileId = cmsImage.get(0).getImageId();
                    fileName = cmsImage.get(0).getImageName();
                    path = cmsImage.get(0).getImagePath();
                    type = cmsImage.get(0).getOcrType();
                    hadoopType = cmsImage.get(0).getHadoopType();
                    if (cmsImage.get(0).getOrderNum() != null) {
                        orderNum = cmsImage.get(0).getOrderNum();
                    }
                    batchId = cmsImage.get(0).getBatchId();
                    billId = cmsImage.get(0).getBillId();
                    remark = cmsFile.get(0).getRemark();
                    createUser = cmsFile.get(0).getCreateUser();
                }
            }
            // 内管结构化
            if (path == null) {
                logger.info("-------------开始进行文件结构化---------------");
                list.getJSONObject(i).put(
                        CmsConstants.OPERATIONCODE, jsonObject.getString(CmsConstants.OPERATIONCODE));
                String s = uploadCommonToMysql(list.getJSONObject(i), json, path, jsonObject);
                // 上传成功
                if (s.contains("#")) {
                    jsonObject.put(FILE_LIST, list);
                    continue;
                }
                //上传失败
                list.getJSONObject(i).put(FILENAME, fileName);
                list.getJSONObject(i).put("size", new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP));
                list.getJSONObject(i).put(BATCH_ID, String.valueOf(batchId));
                list.getJSONObject(i).put(TRANS_FILEPATH, path);
                list.getJSONObject(i).put(FILE_TYPE, type);
                list.getJSONObject(i).put(BILL_ID, billId);
                list.getJSONObject(i).put(REMARK, remark);
                list.getJSONObject(i).put(CREATE_USER, createUser);
                list.getJSONObject(i).put("hadoopType", hadoopType);
                jsonObject.put(FILE_LIST, list);
                fileJson.put(RESULT_CODE, s);
                fileJson.put(ORDER_NUM, "");
                fileJson.put(FILE_ID, "");
                fileJson.put(FILE_NAME, list.getJSONObject(i).get(CmsConstants.FILENAME));
                fileJson.put(FILE_PATH, "");
                fileJson.put(FILE_DOWNLOAD_URL, "");
                fileJson.put(FILE_PREVIEW_URL, "");
                fileArray.add(fileJson);
                continue;
            }
            // 秒传列表
            list.getJSONObject(i).put(FILENAME, fileName);
            list.getJSONObject(i).put("size", new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP));
            list.getJSONObject(i).put(BATCH_ID, String.valueOf(batchId));
            list.getJSONObject(i).put(TRANS_FILEPATH, path);
            list.getJSONObject(i).put(FILE_TYPE, type);
            list.getJSONObject(i).put(BILL_ID, billId);
            list.getJSONObject(i).put(REMARK, remark);
            list.getJSONObject(i).put(CREATE_USER, createUser);
            list.getJSONObject(i).put("hadoopType", hadoopType);
            jsonObject.put(FILE_LIST, list);

            // 秒传列表不会进行非结构化，所以需要在这里调用feign保存im_file im_image

            // 档案数据结构化->ams
            if (!amsBatchOk) {
                jsonObject.put("createUser", jsonObject.get("operator"));
                jsonObject.put("deptNo", jsonObject.get("deptName"));
                logger.info("-------------保存著录信息到ams---------------");
                amsBatchRecordFeignClient.dealProcess(jsonObject.toJSONString());
                amsBatchOk = true;
            }

            String[] split = fileName.split("\\.");
            if (Arrays.asList(allowedFileExtension).contains(split[split.length - 1])) {
                JSONObject imFileObject = new JSONObject(true);
                imFileObject.put("filename", fileName);
                imFileObject.put("size", new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP));
                imFileObject.put("arcNo", jsonObject.get("arcNo"));
                imFileObject.put("transfilepath", path);
                imFileObject.put("billId", billId);
                imFileObject.put("remark", remark);
                imFileObject.put("createuser", createUser);
                imFileObject.put("hadoopType", hadoopType);
                logger.info("-------------将文件结构化到ams---------------");
                amsBatchRecordFeignClient.saveFilesProcess(imFileObject.toJSONString());
            }
            if (Arrays.asList(allowedImageExtension).contains(split[split.length - 1])) {
                JSONObject imImageObject = new JSONObject(true);
                imImageObject.put("filename", fileName);
                imImageObject.put("size", new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP));
                imImageObject.put("arcNo", jsonObject.get("arcNo"));
                imImageObject.put("transfilepath", path);
                imImageObject.put("billId", billId);
                imImageObject.put("remark", remark);
                imImageObject.put("createuser", createUser);
                imImageObject.put("hadoopType", hadoopType);
                logger.info("-------------将影像结构化到ams---------------");
                amsBatchRecordFeignClient.saveImageProcess(imImageObject.toJSONString());
            }

            fileJson.put(RESULT_CODE, ResultCode.UPLOAD_SUCCESS.code());
            fileJson.put(ORDER_NUM, orderNum);
            fileJson.put(FILE_ID, fileId);
            fileJson.put(FILE_NAME, fileName);
            fileJson.put(FILE_PATH, path);
            fileJson.put(FILE_DOWNLOAD_URL, UploadUtil.downloadAPI(fileId));
            fileJson.put(FILE_PREVIEW_URL, UploadUtil.pdfUrl(String.valueOf(fileId), type, path, hadoopType));
            fileArray.add(fileJson);
        }
        // 档案数据结构化->ams
        if (!amsBatchOk) {
            jsonObject.put("createUser", jsonObject.get("operator"));
            jsonObject.put("deptNo", jsonObject.get("deptName"));
            logger.info("-------------保存著录信息到ams---------------");
            amsBatchRecordFeignClient.dealProcess(jsonObject.toJSONString());
            amsBatchOk = true;
        }
        return fileArray;
    }

    /**
     * 内管上传结构化
     *
     * @param jsonObject
     * @param json
     * @return
     */
    @Override
    public JSONArray cmsUpload(JSONObject jsonObject, String json) {
        logger.info("----------------开始进行内管上传的数据结构化----------------------");
        JSONArray fileArray = new JSONArray();
        JSONObject fileJson = new JSONObject(true);
        JSONArray list = jsonObject.getJSONArray(FILE_LIST);
        for (int i = 0; i < list.size(); i++) {
            Long fileId = null;
            String fileName = null;
            String path = null;
            String type = null;
            Long orderNum = null;
            String hadoopType = null;
            // 查询数据库是否有该MD5
            logger.info("check file according to Md5 and status...");
            List<CmsFile> cmsFile = cmsFileMapper.selectCmsFileByMd5(list.getJSONObject(i).getString(CmsConstants.MD5), String.valueOf(Constants.UPLOAD_OVER));
            List<CmsImage> cmsImage = cmsImageMapper.selectCmsImageByMd5(list.getJSONObject(i).getString(CmsConstants.MD5), Constants.UPLOAD_OVER);
            if (cmsImage.isEmpty() && !cmsFile.isEmpty()) {
                // 若文件内容相同但文件名不同
                if (!list.getJSONObject(i).get(CmsConstants.FILENAME).equals(cmsFile.get(0).getFileName())) {
                    SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils(Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.WORKER_ID)), Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.DATACENTER_ID)));
                    PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchByOpt(String.valueOf(list.getJSONObject(i).get(CmsConstants.OPERATIONCODE)));
                    if (pmsBatch == null) {
                        pmsBatch = savePmsBatch(snowFlakeUtils, list.getJSONObject(i), json, jsonObject);
                    }
                    CmsFile file = new CmsFile();
                    file.setFileId(snowFlakeUtils.nextId());
                    file.setFileName(list.getJSONObject(i).get(CmsConstants.FILENAME).toString());
                    file.setBatchId(pmsBatch.getBatchId());
                    file.setRandomName(cmsFile.get(0).getRandomName());
                    file.setFilePath(cmsFile.get(0).getFilePath());
                    file.setCreateTime(new Date());
                    file.setUpdateTime(new Date());
                    file.setFileSysCode(pmsBatch.getSysCode());
                    file.setHadoopType(cmsFile.get(0).getHadoopType());
                    if (list.getJSONObject(i).get(CmsConstants.ORDERNUM) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.ORDERNUM))) {
                        file.setOrderNum(Long.valueOf(String.valueOf(list.getJSONObject(i).get(CmsConstants.ORDERNUM))));
                    }
                    file.setStatus(String.valueOf(Constants.UPLOAD_OVER));
                    String[] split = list.getJSONObject(i).get(CmsConstants.FILENAME).toString().split("\\.");
                    file.setFileType(split[split.length - 1]);
                    if (list.getJSONObject(i).get(CmsConstants.BILLCODE) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.BILLCODE))) {
                        CmsBill cmsBill = cmsBillMapper.selectCmsBillByCode(String.valueOf(list.getJSONObject(i).get(CmsConstants.BILLCODE)));
                        file.setBillId(cmsBill.getId().intValue());
                    }
                    file.setRemark((String) list.getJSONObject(i).get(CmsConstants.REMARK));
                    if (list.getJSONObject(i).get(CmsConstants.CREATEUSER) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.CREATEUSER))) {
                        file.setCreateBy(String.valueOf(list.getJSONObject(i).get(CmsConstants.CREATEUSER)));
                    }
                    file.setFileSource((String) list.getJSONObject(i).get(CmsConstants.FILESOURCE));
                    file.setVersion(CmsConstants.VERSION);
                    file.setMetadata(json);
                    file.setFileSysCode(pmsBatch.getSysCode());
                    file.setDeptName(pmsBatch.getDeptName());
                    /*if (pmsBatch.getModelList() != null && !"".equals(pmsBatch.getModelList())) {
                        file.setModelId(pmsBatch.getModelList());
                    }*/
                    file.setMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                    cmsFileMapper.insertCmsFile(file);
                    fileId = file.getFileId();
                    fileName = file.getFileName();
                    path = file.getFilePath();
                    type = file.getFileType();
                    if (file.getOrderNum() != null) {
                        orderNum = file.getOrderNum();
                    }
                } else {
                    fileId = cmsFile.get(0).getFileId();
                    fileName = cmsFile.get(0).getFileName();
                    path = cmsFile.get(0).getFilePath();
                    type = cmsFile.get(0).getFileType();
                    hadoopType = cmsFile.get(0).getHadoopType();
                    if (cmsFile.get(0).getOrderNum() != null) {
                        orderNum = cmsFile.get(0).getOrderNum();
                    }
                }
            }
            if (!cmsImage.isEmpty() && cmsFile.isEmpty()) {
                // 若文件内容相同但文件名不同
                if (!list.getJSONObject(i).get(CmsConstants.FILENAME).equals(cmsImage.get(0).getImageName())) {
                    SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils(Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.WORKER_ID)), Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.DATACENTER_ID)));
                    PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchByOpt(String.valueOf(list.getJSONObject(i).get(CmsConstants.OPERATIONCODE)));
                    if (pmsBatch == null) {
                        pmsBatch = savePmsBatch(snowFlakeUtils, list.getJSONObject(i), json, jsonObject);
                    }
                    CmsImage image = new CmsImage();
                    image.setImageId(snowFlakeUtils.nextId());
                    image.setImageName(list.getJSONObject(i).get(CmsConstants.FILENAME).toString());
                    image.setBatchId(pmsBatch.getBatchId());
                    image.setRandomName(cmsImage.get(0).getRandomName());
                    image.setImagePath(cmsImage.get(0).getImagePath());
                    image.setCreateTime(new Date());
                    image.setUpdateTime(new Date());
                    image.setImageSysCode(pmsBatch.getSysCode());
                    image.setHadoopType(cmsImage.get(0).getHadoopType());
                    if (list.getJSONObject(i).get(CmsConstants.ORDERNUM) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.ORDERNUM))) {
                        image.setOrderNum(Long.valueOf(String.valueOf(list.getJSONObject(i).get(CmsConstants.ORDERNUM))));
                    }
                    image.setStatus(String.valueOf(Constants.UPLOAD_OVER));
                    String[] split = list.getJSONObject(i).get(CmsConstants.FILENAME).toString().split("\\.");
                    image.setOcrType(split[split.length - 1]);
                    if (list.getJSONObject(i).get(CmsConstants.BILLCODE) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.BILLCODE))) {
                        CmsBill cmsBill = cmsBillMapper.selectCmsBillByCode(String.valueOf(list.getJSONObject(i).get(CmsConstants.BILLCODE)));
                        image.setBillId(cmsBill.getId().intValue());
                    }
                    image.setRemark((String) list.getJSONObject(i).get(CmsConstants.REMARK));
                    if (list.getJSONObject(i).get(CmsConstants.CREATEUSER) != null && !"".equals(list.getJSONObject(i).get(CmsConstants.CREATEUSER))) {
                        image.setCreateBy(String.valueOf(list.getJSONObject(i).get(CmsConstants.CREATEUSER)));
                    }
                    image.setImageSource((String) list.getJSONObject(i).get(CmsConstants.FILESOURCE));
                    image.setVersion(CmsConstants.VERSION);
                    image.setMetadata(json);
                    image.setImageSysCode(pmsBatch.getSysCode());
                    image.setDeptName(pmsBatch.getDeptName());
                    /*if (pmsBatch.getModelList() != null && !"".equals(pmsBatch.getModelList())) {
                        image.setModelId(pmsBatch.getModelList());
                    }*/
                    image.setMd5(String.valueOf(list.getJSONObject(i).get(CmsConstants.MD5)));
                    cmsImageMapper.insertCmsImage(image);
                    fileId = image.getImageId();
                    fileName = image.getImageName();
                    path = image.getImagePath();
                    type = image.getOcrType();
                    hadoopType = image.getHadoopType();
                    if (image.getOrderNum() != null) {
                        orderNum = image.getOrderNum();
                    }
                } else {
                    fileId = cmsImage.get(0).getImageId();
                    fileName = cmsImage.get(0).getImageName();
                    path = cmsImage.get(0).getImagePath();
                    type = cmsImage.get(0).getOcrType();
                    hadoopType = cmsImage.get(0).getHadoopType();
                    if (cmsImage.get(0).getOrderNum() != null) {
                        orderNum = cmsImage.get(0).getOrderNum();
                    }
                }
            }
            // 内管结构化
            if (path == null) {
                logger.info("-------------开始进行文件结构化---------------");
                String s = uploadCommonToMysql(list.getJSONObject(i), json, path, jsonObject);
                if (s.contains("#")) {
                    continue;
                }
                fileJson.put(RESULT_CODE, s);
                fileJson.put(ORDER_NUM, "");
                fileJson.put(FILE_ID, "");
                fileJson.put(FILE_NAME, list.getJSONObject(i).get(CmsConstants.FILENAME));
                fileJson.put(FILE_PATH, "");
                fileJson.put(FILE_DOWNLOAD_URL, "");
                fileJson.put(FILE_PREVIEW_URL, "");
                fileArray.add(fileJson);
                continue;
            }
            fileJson.put(RESULT_CODE, ResultCode.UPLOAD_SUCCESS.code());
            fileJson.put(ORDER_NUM, orderNum);
            fileJson.put(FILE_ID, fileId);
            fileJson.put(FILE_NAME, fileName);
            fileJson.put(FILE_PATH, path);
            fileJson.put(FILE_DOWNLOAD_URL, UploadUtil.downloadAPI(fileId));
            fileJson.put(FILE_PREVIEW_URL, UploadUtil.pdfUrl(String.valueOf(fileId), type, path, hadoopType));
            fileArray.add(fileJson);
        }
        return fileArray;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 2000; i++) {
            File file = new File("D:\\createfiles\\create(" + i + ").txt");
            FileOutputStream fos = null;
            try {
                file.createNewFile();
                fos = new FileOutputStream(file);
                String str = "这是第" + i + "个测试rowkey的文件";
                fos.write(str.getBytes());
            } catch (IOException e) {
                logger.error(e.getMessage());
            } finally {
                if (null != fos) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * 后台文件查询
     *
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject cmsQuery(JSONObject jsonObject) {
        logger.info("----------------开始进行后台文件查询----------------------");
        JSONArray jsonArray = new JSONArray();
        CmsFileVO cmsFile = new CmsFileVO();
        // 将传来的条件封装到VO中作为条件进行查询
        if (jsonObject.get(CmsConstants.OPERATIONCODE) != null && !"".equals(jsonObject.get(CmsConstants.OPERATIONCODE))) {
            cmsFile.setOperationCode(jsonObject.getString(CmsConstants.OPERATIONCODE));
        }
        if (jsonObject.get(CmsConstants.BUILDDEPT) != null && !"".equals(jsonObject.get(CmsConstants.BUILDDEPT))) {
            cmsFile.setBuildDept(jsonObject.getString(CmsConstants.BUILDDEPT));
        }
        if (jsonObject.get(CmsConstants.ATTRIDEPT) != null && !"".equals(jsonObject.get(CmsConstants.ATTRIDEPT))) {
            cmsFile.setPDeptName(jsonObject.getString(CmsConstants.ATTRIDEPT));
        }
        if (jsonObject.get(CmsConstants.BUDGETID) != null && !"".equals(jsonObject.get(CmsConstants.BUDGETID))) {
            cmsFile.setBuildDept(jsonObject.getString(CmsConstants.BUDGETID));
        }
        if (jsonObject.get(CmsConstants.PROJECTMANAGER) != null && !"".equals(jsonObject.get(CmsConstants.PROJECTMANAGER))) {
            cmsFile.setProjectManager(jsonObject.getString(CmsConstants.PROJECTMANAGER));
        }
        if (jsonObject.get(CmsConstants.MODELS) != null && !"".equals(jsonObject.get(CmsConstants.MODELS))) {
            cmsFile.setPModelId(jsonObject.getString(CmsConstants.MODELS));
        }
        if (jsonObject.get(CmsConstants.BILLCODE) != null && !"".equals(jsonObject.get(CmsConstants.BILLCODE))) {
            CmsBill cmsBill = cmsBillMapper.selectCmsBillByCode(String.valueOf(jsonObject.get(CmsConstants.BILLCODE)));
            cmsFile.setBillId(cmsBill.getId().intValue());
        }
        if (jsonObject.get(CmsConstants.TRG) != null && !"".equals(jsonObject.get(CmsConstants.TRG))) {
            cmsFile.setTrg(jsonObject.getString(CmsConstants.TRG));
        }
        if (jsonObject.get(CmsConstants.UPDATETIMESTART) != null && !"".equals(jsonObject.get(CmsConstants.UPDATETIMESTART))) {
            cmsFile.setUpdateTimeStart(jsonObject.getString(CmsConstants.UPDATETIMESTART));
        }
        if (jsonObject.get(CmsConstants.UPDATETIMEEND) != null && !"".equals(jsonObject.get(CmsConstants.UPDATETIMEEND))) {
            cmsFile.setUpdateTimeEnd(jsonObject.getString(CmsConstants.UPDATETIMEEND));
        }
        List<CmsFileDTO> cmsFiles = cmsFileMapper.selectCmsFileListByOpts(cmsFile);
        if (cmsFiles.size() == 0) {
            logger.info("查询出的文件列表为空");
            JSONObject resultObject = new JSONObject(true);
            resultObject.put("totalResultCode", ResultCode.FILES_MISSING.code());
            resultObject.put("totalResultMsg", ResultCode.FILES_MISSING.msg());
            resultObject.put("fileList", jsonArray);
            return resultObject;
        }
        // 取出BatchId，判断这个批次是否关联项目，不关联的，判断sysCode是否相同，不相同就过滤掉
        List<CmsFileDTO> notUnique = new ArrayList<>();
        List<CmsFileDTO> unique = new ArrayList<>();
        for (int i = 0; i < cmsFiles.size(); i++) {
            if (cmsFiles.get(i).getFProjectBatch().equals(PmsConstants.PROJECT_NAME_NOT_UNIQUE)) {
                notUnique.add(cmsFiles.get(i));
            } else if (cmsFiles.get(i).getFProjectBatch().equals(PmsConstants.PROJECT_NAME_UNIQUE)) {
                unique.add(cmsFiles.get(i));
            }
        }
        // 关联项目的系统编码
        Set<String> notUniqueSet;
        if (notUnique.size() > 0) {
            notUniqueSet = notUnique.stream().map(CmsFileDTO::getFSysCode).filter(Objects::nonNull).collect(Collectors.toSet());
        } else {
            notUniqueSet = new HashSet<>();
        }
        // 不关联项目的系统编码
        Set<String> uniqueSet;
        if (unique.size() > 0) {
            uniqueSet = unique.stream().map(CmsFileDTO::getFSysCode).filter(Objects::nonNull).collect(Collectors.toSet());
        } else {
            uniqueSet = new HashSet<>();
        }
        if (!uniqueSet.contains(jsonObject.getString(CmsConstants.SYSCODE)) && !notUniqueSet.contains(jsonObject.getString(CmsConstants.SYSCODE))) {
            logger.info("根据条件未查出该系统相关文件");
            JSONObject resultObject = new JSONObject(true);
            resultObject.put("totalResultCode", ResultCode.FILES_MISSING.code());
            resultObject.put("totalResultMsg", ResultCode.FILES_MISSING.msg());
            resultObject.put("fileList", jsonArray);
            return resultObject;
        }
        for (int i = 0; i < cmsFiles.size(); i++) {
            if (null == cmsFiles.get(i).getFSysCode() || "".equals(cmsFiles.get(i).getFSysCode())) {
                continue;
            }
            // 该文件为关联项目的文件
            if (cmsFiles.get(i).getFProjectBatch().equals(PmsConstants.PROJECT_NAME_NOT_UNIQUE)) {
                // 若传来的系统编码是非关联项目，过滤掉这个关联项目的文件
                if (uniqueSet.contains(jsonObject.getString(CmsConstants.SYSCODE))) {
                    continue;
                }
                //该文件为非关联项目的文件
            } else if (cmsFiles.get(i).getFProjectBatch().equals(PmsConstants.PROJECT_NAME_UNIQUE)) {
                // 若传来的系统编码是关联项目，过滤掉这个非关联项目的文件
                if (notUniqueSet.contains(jsonObject.getString(CmsConstants.SYSCODE))) {
                    continue;
                }
                // 若传来的系统编码不等于该文件的系统编码，则过滤掉该文件(目的是过滤掉其他非关联项目的文件)
                if (!cmsFiles.get(i).getFSysCode().equals(jsonObject.getString(CmsConstants.SYSCODE))) {
                    continue;
                }
            } else {
                continue;
            }
            // 封装返回报文
            JSONObject object = new JSONObject(true);
            object.put(FILE_NAME, cmsFiles.get(i).getFName());
            object.put("fileSysCode", cmsFiles.get(i).getFSysCode());
            object.put("billCode", cmsFiles.get(i).getFBillCode());
            object.put("fileSource", cmsFiles.get(i).getFSource());
            object.put("trg", cmsFiles.get(i).getFTrg());
            object.put(FILE_PREVIEW_URL, UploadUtil.pdfUrl(String.valueOf(cmsFiles.get(i).getFId()), cmsFiles.get(i).getFType(), cmsFiles.get(i).getFPath(), cmsFiles.get(i).getHadoopType()));
            object.put(FILE_DOWNLOAD_URL, UploadUtil.downloadAPI(cmsFiles.get(i).getFId()));
            object.put("operationCode", cmsFiles.get(i).getFBatchId());
            object.put("budgetId", cmsFiles.get(i).getFBudgetId());
            if (cmsFiles.get(i).getFCreateTime() == null) {
                object.put("createTime", "");
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                object.put("createTime", format.format(cmsFiles.get(i).getFCreateTime()));
            }
            if (cmsFiles.get(i).getFUpdateTime() == null) {
                object.put("updateTime", "");
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                object.put("updateTime", format.format(cmsFiles.get(i).getFUpdateTime()));
            }
            jsonArray.add(object);
        }
        JSONObject resultObject = new JSONObject(true);
        resultObject.put("totalResultCode", ResultCode.SUCCESS.code());
        resultObject.put("totalResultMsg", ResultCode.SUCCESS.msg());
        resultObject.put("fileList", jsonArray);
        logger.info("文件列表查询成功，文件个数为: " + jsonArray.size());
        return resultObject;
    }


    /**
     * 项目信息同步
     *
     * @param jsonObject
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void projectInfoSyncho(JSONObject jsonObject) {
        logger.info("----------------开始进行项目信息同步----------------------");
        // 根据项目编号查询pmsbatch,若已有项目,update;否则新增
        logger.info("checking pmsbatch by operationcode...");
        PmsBatch batch = pmsBatchMapper.selectPmsBatchByOpt(jsonObject.getString(CmsConstants.OPERATIONCODE));
        //查询科技项目经理，产品项目经理角色ID
        SysRole techManager = sysRoleMapper.selectRoleByRoleKey(TECHNOLOGY_PROJECT_MANAGER);
        SysRole productManager = sysRoleMapper.selectRoleByRoleKey(PROJECT_PRODUCT_MANAGER);
        if (null != batch) {
            logger.info("pmsbatch already exists,update...");
            batch.setOperationCode(jsonObject.getString(CmsConstants.OPERATIONCODE));
            if (jsonObject.get(CmsConstants.BUILDDEPT) != null && !"".equals(jsonObject.get(CmsConstants.BUILDDEPT))) {
                batch.setBuildDept(jsonObject.getString(CmsConstants.BUILDDEPT));
            }
            if (jsonObject.get(CmsConstants.ATTRIDEPT) != null && !"".equals(jsonObject.get(CmsConstants.ATTRIDEPT))) {
                batch.setDeptName(jsonObject.getString(CmsConstants.ATTRIDEPT));
                SysDept sysDept = sysDeptMapper.selectDeptByDeptNameOrDeptId(jsonObject.getString(CmsConstants.ATTRIDEPT));
                if (null != sysDept) {
                    batch.setDeptId(sysDept.getDeptId());
                    batch.setDeptName(sysDept.getDeptName());
                }
            }
            if (jsonObject.get(CmsConstants.BUDGETID) != null && !"".equals(jsonObject.get(CmsConstants.BUDGETID))) {
                batch.setBudgetId(jsonObject.getString(CmsConstants.BUDGETID));
            }
            if (jsonObject.get(CmsConstants.PROJECTMANAGER) != null && !"".equals(jsonObject.get(CmsConstants.PROJECTMANAGER))) {
                String[] split = jsonObject.getString(CmsConstants.PROJECTMANAGER).split(",");
                for (String projectManager : split) {
                    SysUser sysUser = sysUserMapper.selectUserByLoginName(projectManager);
                    List<SysUserRole> projectManagerRole = sysUserRoleMapper.selectUserRole(sysUser.getUserId(), techManager.getRoleId());
                    if (projectManagerRole == null || projectManagerRole.size() <= 0) {
                        logger.info("sys_user_role查不到该科技项目经理,将该用户添加至科技项目经理角色中");
                        sysUserRoleMapper.insertUserRole(sysUser.getUserId(), techManager.getRoleId());
                    }
                    CmsUserRole cmsUserRole = new CmsUserRole();
                    cmsUserRole.setUserId(sysUser.getUserId());
                    cmsUserRole.setRoleId(techManager.getRoleId());
                    List<CmsUserRole> cmsProjectManager = cmsUserRoleMapper.selectCmsUserRoleList(cmsUserRole);
                    if (cmsProjectManager == null || cmsProjectManager.size() <= 0) {
                        logger.info("cms_user_role查不到该科技项目经理,将该用户添加至科技项目经理角色中");
                        cmsUserRoleMapper.insertCmsUserRole(cmsUserRole);
                    }
                }
                batch.setProjectManager(jsonObject.getString(CmsConstants.PROJECTMANAGER));
            }
            if (jsonObject.get(CmsConstants.MODELS) != null && !"".equals(jsonObject.get(CmsConstants.MODELS))) {
                batch.setModelList(jsonObject.getString(CmsConstants.MODELS));
            }
            if (jsonObject.get(CmsConstants.DESCRIPTION) != null && !"".equals(jsonObject.get(CmsConstants.DESCRIPTION))) {
                batch.setRemark(jsonObject.getString(CmsConstants.DESCRIPTION));
            }
            if (jsonObject.get(CmsConstants.SYSCODE) != null && !"".equals(jsonObject.get(CmsConstants.SYSCODE))) {
                batch.setSysCode(jsonObject.getString(CmsConstants.SYSCODE));
            }
            if (jsonObject.get(CmsConstants.PRODUCTMANAGER) != null && !"".equals(jsonObject.get(CmsConstants.PRODUCTMANAGER))) {
                String[] split = jsonObject.getString(CmsConstants.PRODUCTMANAGER).split(",");
                for (String productManagerName : split) {
                    SysUser sysUser = sysUserMapper.selectUserByLoginName(productManagerName);
                    List<SysUserRole> productManagerRole = sysUserRoleMapper.selectUserRole(sysUser.getUserId(), productManager.getRoleId());
                    if (productManagerRole == null || productManagerRole.size() <= 0) {
                        logger.info("sys_user_role查不到该产品项目经理,将该用户添加至产品经理角色中");
                        sysUserRoleMapper.insertUserRole(sysUser.getUserId(), productManager.getRoleId());
                    }

                    CmsUserRole cmsUserRole = new CmsUserRole();
                    cmsUserRole.setUserId(sysUser.getUserId());
                    cmsUserRole.setRoleId(productManager.getRoleId());
                    List<CmsUserRole> cmsProductManager = cmsUserRoleMapper.selectCmsUserRoleList(cmsUserRole);
                    if (cmsProductManager == null || cmsProductManager.size() <= 0) {
                        logger.info("cms_user_role查不到该产品项目经理,将该用户添加至产品经理角色中");
                        cmsUserRoleMapper.insertCmsUserRole(cmsUserRole);
                    }
                }
                batch.setProductManager(jsonObject.getString(CmsConstants.PRODUCTMANAGER));
            }
            if (jsonObject.get(CmsConstants.PROJECTNAME) != null && !"".equals(jsonObject.get(CmsConstants.PROJECTNAME))) {
                batch.setProjectName(jsonObject.getString(CmsConstants.PROJECTNAME));
            }
            if (jsonObject.get(CmsConstants.OPERATOR) != null && !"".equals(jsonObject.get(CmsConstants.OPERATOR))) {
                batch.setUpdateBy(jsonObject.getString(CmsConstants.OPERATOR));
            }
            if (jsonObject.get(CmsConstants.UPDATETIME) != null && !"".equals(jsonObject.get(CmsConstants.UPDATETIME))) {
                batch.setUpdateTime(jsonObject.getDate(CmsConstants.UPDATETIME));
            } else {
                batch.setUpdateTime(new Date());
            }
            if (jsonObject.get(CmsConstants.PROJECT_STATUS) != null && !"".equals(jsonObject.get(CmsConstants.PROJECT_STATUS))) {
                batch.setStatus(jsonObject.getString(CmsConstants.PROJECT_STATUS));
            }
            if (jsonObject.get(CmsConstants.SYS_LEVEL) != null && !"".equals(jsonObject.get(CmsConstants.SYS_LEVEL))) {
                batch.setSysLevel(jsonObject.getString(CmsConstants.SYS_LEVEL));
            }
            if (jsonObject.get(CmsConstants.SYS_TYPE) != null && !"".equals(jsonObject.get(CmsConstants.SYS_TYPE))) {
                batch.setSysType(jsonObject.getString(CmsConstants.SYS_TYPE));
            }
            pmsBatchMapper.updatePmsBatch(batch);
        } else {
            logger.info("pmsbatch not exists,insert...");
            SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils(Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.WORKER_ID)), Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.DATACENTER_ID)));
            PmsBatch pmsBatch = new PmsBatch();
            pmsBatch.setBatchId(snowFlakeUtils.nextId());
            pmsBatch.setOperationCode(jsonObject.getString(CmsConstants.OPERATIONCODE));
            if (jsonObject.get(CmsConstants.BUILDDEPT) != null && !"".equals(jsonObject.get(CmsConstants.BUILDDEPT))) {
                pmsBatch.setBuildDept(jsonObject.getString(CmsConstants.BUILDDEPT));
            }
            if (jsonObject.get(CmsConstants.ATTRIDEPT) != null && !"".equals(jsonObject.get(CmsConstants.ATTRIDEPT))) {
                pmsBatch.setDeptName(jsonObject.getString(CmsConstants.ATTRIDEPT));
                SysDept sysDept = sysDeptMapper.selectDeptByDeptNameOrDeptId(jsonObject.getString(CmsConstants.ATTRIDEPT));
                if (null != sysDept) {
                    pmsBatch.setDeptId(sysDept.getDeptId());
                    pmsBatch.setDeptName(sysDept.getDeptName());
                }
            }
            if (jsonObject.get(CmsConstants.BUDGETID) != null && !"".equals(jsonObject.get(CmsConstants.BUDGETID))) {
                pmsBatch.setBudgetId(jsonObject.getString(CmsConstants.BUDGETID));
            }
            if (jsonObject.get(CmsConstants.PROJECTMANAGER) != null && !"".equals(jsonObject.get(CmsConstants.PROJECTMANAGER))) {
                String[] split = jsonObject.getString(CmsConstants.PROJECTMANAGER).split(",");
                for (String projectManager : split) {
                    SysUser sysUser = sysUserMapper.selectUserByLoginName(projectManager);
                    List<SysUserRole> projectManagerRole = sysUserRoleMapper.selectUserRole(sysUser.getUserId(), techManager.getRoleId());
                    if (projectManagerRole == null || projectManagerRole.size() <= 0) {
                        logger.info("sys_user_role查不到该科技项目经理,将该用户添加至科技项目经理角色中");
                        sysUserRoleMapper.insertUserRole(sysUser.getUserId(), techManager.getRoleId());
                    }
                    CmsUserRole cmsUserRole = new CmsUserRole();
                    cmsUserRole.setUserId(sysUser.getUserId());
                    cmsUserRole.setRoleId(techManager.getRoleId());
                    List<CmsUserRole> cmsProjectManager = cmsUserRoleMapper.selectCmsUserRoleList(cmsUserRole);
                    if (cmsProjectManager == null || cmsProjectManager.size() <= 0) {
                        logger.info("cms_user_role查不到该科技项目经理,将该用户添加至科技项目经理角色中");
                        cmsUserRoleMapper.insertCmsUserRole(cmsUserRole);
                    }
                }
                pmsBatch.setProjectManager(jsonObject.getString(CmsConstants.PROJECTMANAGER));
            }
            if (jsonObject.get(CmsConstants.MODELS) != null && !"".equals(jsonObject.get(CmsConstants.MODELS))) {
                pmsBatch.setModelList(jsonObject.getString(CmsConstants.MODELS));
            }
            if (jsonObject.get(CmsConstants.SYSCODE) != null && !"".equals(jsonObject.get(CmsConstants.SYSCODE))) {
                pmsBatch.setSysCode(jsonObject.getString(CmsConstants.SYSCODE));
            }
            if (jsonObject.get(CmsConstants.DESCRIPTION) != null && !"".equals(jsonObject.get(CmsConstants.DESCRIPTION))) {
                pmsBatch.setRemark(jsonObject.getString(CmsConstants.DESCRIPTION));
            }
            if (jsonObject.get(CmsConstants.OPERATOR) != null && !"".equals(jsonObject.get(CmsConstants.OPERATOR))) {
                pmsBatch.setCreateBy(jsonObject.getString(CmsConstants.OPERATOR));
            }
            if (jsonObject.get(CmsConstants.CREATETIME) != null && !"".equals(jsonObject.get(CmsConstants.CREATETIME))) {
                pmsBatch.setCreateTime(jsonObject.getDate(CmsConstants.CREATETIME));
            } else {
                pmsBatch.setCreateTime(new Date());
            }
            if (jsonObject.get(CmsConstants.PRODUCTMANAGER) != null && !"".equals(jsonObject.get(CmsConstants.PRODUCTMANAGER))) {
                String[] split = jsonObject.getString(CmsConstants.PRODUCTMANAGER).split(",");
                for (String productManagerName : split) {
                    SysUser sysUser = sysUserMapper.selectUserByLoginName(productManagerName);
                    List<SysUserRole> productManagerRole = sysUserRoleMapper.selectUserRole(sysUser.getUserId(), productManager.getRoleId());
                    if (productManagerRole == null || productManagerRole.size() <= 0) {
                        logger.info("sys_user_role查不到该产品项目经理,将该用户添加至产品经理角色中");
                        sysUserRoleMapper.insertUserRole(sysUser.getUserId(), productManager.getRoleId());
                    }

                    CmsUserRole cmsUserRole = new CmsUserRole();
                    cmsUserRole.setUserId(sysUser.getUserId());
                    cmsUserRole.setRoleId(productManager.getRoleId());
                    List<CmsUserRole> cmsProductManager = cmsUserRoleMapper.selectCmsUserRoleList(cmsUserRole);
                    if (cmsProductManager == null || cmsProductManager.size() <= 0) {
                        logger.info("cms_user_role查不到该产品项目经理,将该用户添加至产品经理角色中");
                        cmsUserRoleMapper.insertCmsUserRole(cmsUserRole);
                    }
                }
                pmsBatch.setProductManager(jsonObject.getString(CmsConstants.PRODUCTMANAGER));
            }
            if (jsonObject.get(CmsConstants.PROJECTNAME) != null && !"".equals(jsonObject.get(CmsConstants.PROJECTNAME))) {
                pmsBatch.setProjectName(jsonObject.getString(CmsConstants.PROJECTNAME));
            }
            if (jsonObject.get(CmsConstants.PROJECT_STATUS) != null && !"".equals(jsonObject.get(CmsConstants.PROJECT_STATUS))) {
                pmsBatch.setStatus(jsonObject.getString(CmsConstants.PROJECT_STATUS));
            }
            if (jsonObject.get(CmsConstants.SYS_LEVEL) != null && !"".equals(jsonObject.get(CmsConstants.SYS_LEVEL))) {
                pmsBatch.setSysLevel(jsonObject.getString(CmsConstants.SYS_LEVEL));
            }
            if (jsonObject.get(CmsConstants.SYS_TYPE) != null && !"".equals(jsonObject.get(CmsConstants.SYS_TYPE))) {
                pmsBatch.setSysType(jsonObject.getString(CmsConstants.SYS_TYPE));
            }
            if (jsonObject.get(CmsConstants.PROJECTBATCH) != null && !"".equals(jsonObject.get(CmsConstants.PROJECTBATCH))) {
                pmsBatch.setProjectBatch(String.valueOf(jsonObject.get(CmsConstants.PROJECTBATCH)));
            } else {
                pmsBatch.setProjectBatch(PmsConstants.PMS_PROJECT_YES);
            }
//            pmsBatch.setStatus(PmsConstants.PMO_STATUS_NORMAL);
            pmsBatchMapper.insertPmsBatch(pmsBatch);
            if (null != pmsBatch.getBudgetId() && null != pmsBatch.getOperationCode() && !pmsBatch.getBudgetId().equals(pmsBatch.getOperationCode())) {
                // 未立项前是将预算编号当做项目编号的
                PmsBatch originBatch = pmsBatchMapper.selectPmsBatchByOpt(pmsBatch.getBudgetId());
                if (null != originBatch) {
                    // 将之前项目下面所有文件放到新项目下
                    List<CmsFile> originCmsFiles = cmsFileMapper.selectFileByBatchId(originBatch.getBatchId());
                    for (CmsFile cmsFile : originCmsFiles) {
                        cmsFile.setId(null);
                        cmsFile.setFileId(snowFlakeUtils.nextId());
                        cmsFile.setBatchId(pmsBatch.getBatchId());
                    }
                    List<CmsImage> originCmsImages = cmsImageMapper.selectImageByBatchId(originBatch.getBatchId());
                    for (CmsImage cmsImage : originCmsImages) {
                        cmsImage.setId(null);
                        cmsImage.setImageId(snowFlakeUtils.nextId());
                        cmsImage.setBatchId(pmsBatch.getBatchId());
                    }
                    logger.info("-------------将未立项项目暂存的文件放入到新的项目中---------------");
                    cmsFileMapper.insertCmsFiles(originCmsFiles);
                    cmsImageMapper.insertCmsImages(originCmsImages);
                }
            }
        }
    }

    /**
     * 查询叶子结点下的文件
     *
     * @param billCode
     * @return
     */
    private JSONArray billFileList(String billCode) {
        logger.info("-------------查询叶子节点下的文件---------------");
        JSONArray jsonArray = new JSONArray();
        List<CmsFileDTO> cmsFiles = cmsFileMapper.selectCmsFileListByBillCode(billCode);
        for (int i = 0; i < cmsFiles.size(); i++) {
            // 封装返回报文
            JSONObject object = new JSONObject(true);
            object.put(FILE_NAME, cmsFiles.get(i).getFName());
            object.put(FILE_PREVIEW_URL, UploadUtil.pdfUrl(String.valueOf(cmsFiles.get(i).getFId()), cmsFiles.get(i).getFType(), cmsFiles.get(i).getFPath(), cmsFiles.get(i).getHadoopType()));
            object.put(FILE_DOWNLOAD_URL, UploadUtil.downloadAPI(cmsFiles.get(i).getFId()));
            object.put(FILE_ID, cmsFiles.get(i).getFId());
            object.put("trg", cmsFiles.get(i).getFTrg());
            jsonArray.add(object);
        }
        return jsonArray;
    }


    /**
     * 获取项目目录结构及文件列表
     *
     * @param pmsBatch
     * @return
     */
    @Override
    public JSONObject projectBillList(PmsBatch pmsBatch) {
        logger.info("----------------进入获取项目分类结构及文件列表方法----------------------");
        long start = System.currentTimeMillis();
        Map<String, Integer> codeNum = new HashMap<>();
        Map<Long, String> parentBillCodes = new HashMap<>();
        // 查询模型下的所有分类
        List<CmsBill> cmsBills = cmsBillMapper.selectCmsBillListByModelAndLeaf(pmsBatch.getModelList());
        // 分类的所有叶子节点
        long leafStart = System.currentTimeMillis();
        for (int j = 0; j < cmsBills.size(); j++) {
            if (Constants.IS_LEAF.equals(cmsBills.get(j).getLeaf())) {
                Integer integer = cmsBillMapper.selectFileNumByCode(cmsBills.get(j).getBillCode());
                if (integer == null) {
                    integer = 0;
                }
                codeNum.put(cmsBills.get(j).getBillCode(), integer);
            } else {
                parentBillCodes.put(Long.valueOf(cmsBills.get(j).getId()), cmsBills.get(j).getBillCode());
            }
        }
        long leafEnd = System.currentTimeMillis();
        long leafTotal = (leafEnd - leafStart);
        logger.info("计算某节点下所有子节点所需时间为: " + leafTotal + "毫秒");
        JSONArray billArray = new JSONArray();
        // 循环遍历所有分类
        for (int j = 0; j < cmsBills.size(); j++) {
            JSONObject cmsBillSon = new JSONObject(true);
            cmsBillSon.put("billName", cmsBills.get(j).getBillName());
            cmsBillSon.put("billCode", cmsBills.get(j).getBillCode());
            cmsBillSon.put("display", cmsBills.get(j).getDisplay());
            if (cmsBills.get(j).getParentId().equals(Long.valueOf(Constants.ROOT))) {
                cmsBillSon.put(BILL_PARENT_CODE, "0");
            } else {
                cmsBillSon.put(BILL_PARENT_CODE, parentBillCodes.get(cmsBills.get(j).getParentId()));
            }
            cmsBillSon.put("leaf", cmsBills.get(j).getLeaf());
            // 如果是叶子结点，查询是否有关联文件
            Set<String> trgs = new HashSet<String>();
            if (Constants.IS_LEAF.equals(cmsBills.get(j).getLeaf())) {
                JSONArray fileArray = billFileList(cmsBills.get(j).getBillCode());
                cmsBillSon.put(FILE_LIST, fileArray);
                cmsBillSon.put(COMPLETE, !fileArray.isEmpty() ? "1/1" : "0/1");
                for (int i = 0; i < fileArray.size(); i++) {
                    trgs.add(String.valueOf(fileArray.getJSONObject(i).get(CmsConstants.TRG)));
                }
                cmsBillSon.put("trgSet", trgs);
                billArray.add(cmsBillSon);
                continue;
            }
            // 获取该节点下所有叶子结点
            List<CmsBill> cmsBillsLeaf = new ArrayList<>();
            for (int i = 0; i < cmsBills.size(); i++) {
                // 若该节点的子节点是叶子节点，就加入到cmsBillsLeaf
                if (cmsBills.get(i).getParentId().equals(Long.valueOf(cmsBills.get(j).getId())) && Constants.IS_LEAF.equals(cmsBills.get(i).getLeaf())) {
                    cmsBillsLeaf.add(cmsBills.get(i));
                }
            }
            // 遍历该结点下所有叶子结点
            int total = cmsBillsLeaf.size();
            for (int i = 0; i < cmsBillsLeaf.size(); i++) {
                Integer fileNum = codeNum.get(cmsBillsLeaf.get(i).getBillCode());
                if (fileNum == null) {
                    total--;
                }
            }
            cmsBillSon.put(COMPLETE, total + "/" + cmsBillsLeaf.size());
            billArray.add(cmsBillSon);
        }
        // 处理特殊分类(展示方式为3的分类)
        long lStart = System.currentTimeMillis();
        for (int i = 0; i < billArray.size(); i++) {
            if (Constants.SPECIALBILL.equals(billArray.getJSONObject(i).get(CmsConstants.DISPLAY))) {
                // 处理 子节点
                Set<String> trgSet = new HashSet<>();
                // 处理后本节点的分子数
                int afterMolecule = 0;
                // 叶子节点的个数
                int count = 0;
                // 本节点处理前的分子
                int beforeMolecule = 0;
                for (int j = 0; j < billArray.size(); j++) {
                    JSONObject sonJson = billArray.getJSONObject(j);
                    if (billArray.getJSONObject(i).get(CmsConstants.BILLCODE).equals(sonJson.get(CmsConstants.BILLPARENTCODE)) && Constants.IS_LEAF.equals(sonJson.get(CmsConstants.LEAF))) {
                        trgSet.addAll((Set) sonJson.get(CmsConstants.TRGSET));
                    }
                }
                for (int j = 0; j < billArray.size(); j++) {
                    JSONObject sonJson = billArray.getJSONObject(j);
                    if (billArray.getJSONObject(i).get(CmsConstants.BILLCODE).equals(sonJson.get(CmsConstants.BILLPARENTCODE)) && Constants.IS_LEAF.equals(sonJson.get(CmsConstants.LEAF))) {
                        Integer molecule = codeNum.get(sonJson.get(CmsConstants.BILLCODE));
                        if (molecule > 0) {
                            beforeMolecule++;
                        }
                        sonJson.put(COMPLETE, molecule + "/" + trgSet.size());
                        afterMolecule += molecule;
                        count++;
                    }
                }
                // 处理 本节点
                JSONObject json = billArray.getJSONObject(i);
                // 处理后分母
                int deno = (trgSet.size() * count);
                json.put(COMPLETE, afterMolecule + "/" + deno);
                // 处理 父节点
                for (int j = 0; j < billArray.size(); j++) {
                    JSONObject fatherJson = billArray.getJSONObject(j);
                    if (fatherJson.get(CmsConstants.BILLCODE).equals(json.get(CmsConstants.BILLPARENTCODE))) {
                        String complete = fatherJson.getString(CmsConstants.COMPLETE);
                        String[] split = complete.split("/");
                        // 父节点原分子
                        Integer originMolecule = Integer.valueOf(split[0]);
                        // 父节点原分母
                        Integer originDeno = Integer.valueOf(split[1]);
                        fatherJson.put(COMPLETE, (originMolecule + afterMolecule - beforeMolecule) + "/" + (originDeno + deno - count));
                    }
                    // 不到根节点就一直向上递归
                    if (!String.valueOf(Constants.ROOT).equals(fatherJson.get(CmsConstants.BILLPARENTCODE))) {
                        recursion(billArray, fatherJson, afterMolecule, deno, beforeMolecule, count);
                    } else {
                        break;
                    }
                }
            }
            billArray.getJSONObject(i).remove("trgSet");
            billArray.getJSONObject(i).remove("display");
        }
        // 查询项目下所有文件的bill(都是叶子节点)
        List<CmsBill> billList = cmsBillMapper.selectBillsByFileBatchId(pmsBatch.getBatchId());
        if (billList.size() != 0) {
            // 项目下所有文件的billId以及其所有父billId
            Set<Long> allBillId = new HashSet<>();
            for (CmsBill cmsBill : billList) {
                if (null == cmsBill.getAllPath()) {
                    continue;
                }
                String[] split = cmsBill.getAllPath().split(",");
                allBillId.add(cmsBill.getId());
                for (String parentId : split) {
                    allBillId.add(Long.valueOf(parentId));
                }
            }
            // 获取cmsBills的Id,与allBillId进行比对
            Set<Long> cmsBillsCopySet = cmsBills.stream().map(CmsBill::getId).filter(Objects::nonNull).collect(Collectors.toSet());
            Set<Long> surplusId = new HashSet<>();
            for (Long cmsBillId : allBillId) {
                if (!cmsBillsCopySet.contains(cmsBillId)) {
                    if (cmsBillId.intValue() == Constants.ROOT.intValue()) {
                        continue;
                    }
                    surplusId.add(cmsBillId);
                }
            }
            // 如果大于0,则证明上传时超出了项目模型，根据surplusId查询多出的分类,然后根据ParentId进行排序
            if (surplusId.size() > 0) {
                // 根据surplusId查询多出分类
                List<CmsBill> suplusCmsBills = cmsBillMapper.selectBillByIds(surplusId);
                Collections.sort(suplusCmsBills, new Comparator<CmsBill>() {
                    @Override
                    public int compare(CmsBill o1, CmsBill o2) {
                        return o1.getParentId().intValue() - o2.getParentId().intValue();
                    }
                });
                // 加入到billArray中
                for (int i = 0; i < suplusCmsBills.size(); i++) {
                    JSONObject cmsBillSon = new JSONObject(true);
                    cmsBillSon.put("billName", suplusCmsBills.get(i).getBillName());
                    cmsBillSon.put("billCode", suplusCmsBills.get(i).getBillCode());
                    cmsBillSon.put("display", suplusCmsBills.get(i).getDisplay());
                    if (suplusCmsBills.get(i).getParentId().equals(Long.valueOf(Constants.ROOT))) {
                        cmsBillSon.put(BILL_PARENT_CODE, "0");
                    } else {
                        cmsBillSon.put(BILL_PARENT_CODE, parentBillCodes.get(suplusCmsBills.get(i).getParentId()));
                    }
                    cmsBillSon.put("leaf", suplusCmsBills.get(i).getLeaf());
                    // 如果是叶子结点，查询是否有关联文件
                    if (Constants.IS_LEAF.equals(suplusCmsBills.get(i).getLeaf())) {
                        JSONArray fileArray = billFileList(suplusCmsBills.get(i).getBillCode());
                        cmsBillSon.put(FILE_LIST, fileArray);
                    }
                    billArray.add(cmsBillSon);
                }
            }
        }
        long lEnd = System.currentTimeMillis();
        long lTotal = (lEnd - lStart);
        logger.info("处理特殊分类所需时间为: " + lTotal + "毫秒");
        long end = System.currentTimeMillis();
        long totalTime = (end - start);
        logger.info("执行完方法消耗的时间为: " + totalTime + "毫秒");
        JSONObject resultObject = new JSONObject(true);
        resultObject.put("totalResultCode", ResultCode.SUCCESS.code());
        resultObject.put("totalResultMsg", ResultCode.SUCCESS.msg());
        resultObject.put("billList", billArray);
        return resultObject;
    }

    /**
     * 向上递归父节点
     */
    private void recursion(JSONArray billArray, JSONObject fatherJson, int afterMolecule, int deno, int beforeMolecule, int count) {
        for (int j = 0; j < billArray.size(); j++) {
            JSONObject grandFatherJson = billArray.getJSONObject(j);
            if (grandFatherJson.get(CmsConstants.BILLCODE).equals(fatherJson.get(CmsConstants.BILLPARENTCODE))) {
                String complete = grandFatherJson.getString(CmsConstants.COMPLETE);
                String[] split = complete.split("/");
                // 父节点原分子
                Integer originMolecule = Integer.valueOf(split[0]);
                // 父节点原分母
                Integer originDeno = Integer.valueOf(split[1]);
                grandFatherJson.put(COMPLETE, (originMolecule + afterMolecule - beforeMolecule) + "/" + (originDeno + deno - count));
            }
        }
    }

    /**
     * 保存批次
     */
    @Override
    public PmsBatch savePmsBatch(SnowFlakeUtils snowFlakeUtils, JSONObject object, String json, JSONObject jsonObject) {
        PmsBatch pmsBatch = new PmsBatch();
        pmsBatch.setBatchId(snowFlakeUtils.nextId());
        if (object.get(CmsConstants.REGBILLGLIDENO) != null && !"".equals(object.get(CmsConstants.REGBILLGLIDENO))) {
            pmsBatch.setRegbillglideNo(String.valueOf(object.get(CmsConstants.REGBILLGLIDENO)));
        }
        pmsBatch.setOperationCode(String.valueOf(object.get(CmsConstants.OPERATIONCODE)));
        // 默认使用项目
        if (object.get(CmsConstants.PROJECTBATCH) != null && !"".equals(object.get(CmsConstants.PROJECTBATCH))) {
            pmsBatch.setProjectBatch(String.valueOf(object.get(CmsConstants.PROJECTBATCH)));
        } else {
            pmsBatch.setProjectBatch(PmsConstants.PMS_PROJECT_YES);
        }
        if (object.get(CmsConstants.CREATEUSER) != null && !"".equals(object.get(CmsConstants.CREATEUSER))) {
            pmsBatch.setCreateBy(String.valueOf(object.get(CmsConstants.CREATEUSER)));
            pmsBatch.setUpdateBy(String.valueOf(object.get(CmsConstants.CREATEUSER)));
        }
        if (object.get(CmsConstants.DEPTNAME) != null && !"".equals(object.get(CmsConstants.DEPTNAME))) {
            pmsBatch.setDeptName(String.valueOf(object.get(CmsConstants.DEPTNAME)));
        }
        if (object.get(CmsConstants.ORDERNUM) != null && !"".equals(object.get(CmsConstants.ORDERNUM))) {
            pmsBatch.setOrderNum(Long.valueOf(String.valueOf(object.get(CmsConstants.ORDERNUM))));
        }
        pmsBatch.setCreateTime(new Date());
        pmsBatch.setUpdateTime(new Date());
        if (jsonObject.get(CmsConstants.SYSCODE) != null && !"".equals(jsonObject.get(CmsConstants.SYSCODE))) {
            pmsBatch.setSysCode(String.valueOf(jsonObject.get(CmsConstants.SYSCODE)));
        }
        if (object.get(CmsConstants.FILESYSCODE) != null && !"".equals(object.get(CmsConstants.FILESYSCODE))) {
            pmsBatch.setSysCode(String.valueOf(object.get(CmsConstants.FILESYSCODE)));
        }
        if (object.get(CmsConstants.MODELID) != null && !"".equals(object.get(CmsConstants.MODELID))) {
            pmsBatch.setModelList(String.valueOf(object.get(CmsConstants.MODELID)));
        }
        pmsBatch.setMetadata(json);
        pmsBatchMapper.insertPmsBatch(pmsBatch);

        return pmsBatch;
    }

    /**
     * 更新批次
     */
    @Override
    public PmsBatch updatePmsBatch(JSONObject object, String json, PmsBatch pmsBatchByOpt) {
        if (object.get(CmsConstants.CREATEUSER) != null && !"".equals(object.get(CmsConstants.CREATEUSER))) {
            pmsBatchByOpt.setUpdateBy(String.valueOf(object.get(CmsConstants.CREATEUSER)));
        }
        pmsBatchByOpt.setUpdateTime(new Date());
        pmsBatchMapper.updatePmsBatch(pmsBatchByOpt);
        return pmsBatchByOpt;
    }

    /**
     * 上传公用方法(结构化)
     */
    public String uploadCommonToMysql(JSONObject object, String json, String path, JSONObject jsonObject) {
        if (object.get(CmsConstants.FILENAME) == null || "".equals(CmsConstants.FILENAME)) {
            return ResultCode.FILE_NAME_MISSING.code();
        }
        if (object.get(CmsConstants.BILLCODE) == null || "".equals(CmsConstants.BILLCODE)) {
            return ResultCode.BILL_CODE_MISSING.code();
        }
        Long fileId = null;
        String fileName = null;
        String[] fileNames = object.getString(CmsConstants.FILENAME).split("\\.");
        String extension = fileNames[fileNames.length - 1];
        SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils(Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.WORKER_ID)), Long.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.DATACENTER_ID)));
        // 兼容IE浏览器的OriginalFileName

        String originalFilename = object.getString(CmsConstants.FILENAME);
        // 后缀是图像,保存到cms_image
        for (int i = 0; i < allowedImageExtension.length; i++) {
            if (extension.equals(allowedImageExtension[i])) {
                // 登记流水号查询是否已有该批次，如果没有就insert，有就update
                if (object.get(CmsConstants.OPERATIONCODE) == null || "".equals(CmsConstants.OPERATIONCODE)) {
                    return ResultCode.PROJECT_MISSING.code();
                }
                PmsBatch pmsBatchByOpt = pmsBatchMapper.selectPmsBatchByOpt(String.valueOf(object.get(CmsConstants.OPERATIONCODE)));
                PmsBatch pmsBatch;
                if (pmsBatchByOpt == null) {
                    // 保存批次
                    pmsBatch = savePmsBatch(snowFlakeUtils, object, json, jsonObject);
                } else {
                    // 更新批次
                    pmsBatch = updatePmsBatch(object, json, pmsBatchByOpt);
                }
                // 保存影像
                CmsImage cmsImage = new CmsImage();
                cmsImage.setImageId(snowFlakeUtils.nextId());
                cmsImage.setImageName(originalFilename);
                cmsImage.setBatchId(pmsBatch.getBatchId());
                cmsImage.setCreateTime(pmsBatch.getCreateTime());
                cmsImage.setUpdateTime(pmsBatch.getUpdateTime());
                if (object.get(CmsConstants.ORDERNUM) != null && !"".equals(object.get(CmsConstants.ORDERNUM))) {
                    cmsImage.setOrderNum(Long.valueOf(String.valueOf(object.get(CmsConstants.ORDERNUM))));
                }
                if (path == null || "".equals(path)) {
                    cmsImage.setStatus(String.valueOf(Constants.UPLOAD_ING));
                }
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
                cmsImage.setImageSysCode(pmsBatch.getSysCode());
                cmsImage.setRemark((String) object.get(CmsConstants.REMARK));
                if (object.get(CmsConstants.CREATEUSER) != null && !"".equals(object.get(CmsConstants.CREATEUSER))) {
                    cmsImage.setCreateBy(String.valueOf(object.get(CmsConstants.CREATEUSER)));
                }
                cmsImage.setImageSource((String) object.get(CmsConstants.IMGSOURCE));
                cmsImage.setVersion(CmsConstants.VERSION);
                cmsImage.setMetadata(json);
                cmsImage.setDeptName(pmsBatch.getDeptName());
                cmsImage.setImageSysCode(pmsBatch.getSysCode());
                /*if (pmsBatch.getModelList() != null && !"".equals(pmsBatch.getModelList())) {
                    cmsImage.setModelId(pmsBatch.getModelList());
                }*/
                cmsImage.setMd5(String.valueOf(object.get(CmsConstants.MD5)));
                cmsImageMapper.insertCmsImage(cmsImage);
                fileId = cmsImage.getImageId();
                fileName = cmsImage.getImageName();
                object.put(FILENAME, cmsImage.getImageName());
                if (cmsImage.getImageSize() == null) {
                    object.put("size", new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP));
                } else {
                    object.put("size", new BigDecimal(cmsImage.getImageSize().toString()).setScale(0, BigDecimal.ROUND_HALF_UP));
                }
                object.put(BATCH_ID, String.valueOf(pmsBatch.getBatchId()));
                object.put(TRANS_FILEPATH, path);
                object.put(FILE_TYPE, cmsImage.getOcrType());
                object.put(BILL_ID, String.valueOf(cmsImage.getBillId()));
                object.put(REMARK, cmsImage.getRemark());
                object.put(CREATE_USER, String.valueOf(cmsImage.getCreateUser()));
                break;
            }
        }
        for (int i = 0; i < allowedFileExtension.length; i++) {
            // 后缀是文件,保存到cms_file
            if (extension.equals(allowedFileExtension[i])) {
                // 登记流水号查询是否已有该批次，如果没有就insert，有就update
                if (object.get(CmsConstants.OPERATIONCODE) == null || "".equals(CmsConstants.OPERATIONCODE)) {
                    return ResultCode.PROJECT_MISSING.code();
                }
                PmsBatch pmsBatchByOpt = pmsBatchMapper.selectPmsBatchByOpt(String.valueOf(object.get(CmsConstants.OPERATIONCODE)));
                PmsBatch pmsBatch;
                if (pmsBatchByOpt == null) {
                    // 保存批次
                    pmsBatch = savePmsBatch(snowFlakeUtils, object, json, jsonObject);
                } else {
                    // 更新批次
                    pmsBatch = updatePmsBatch(object, json, pmsBatchByOpt);
                }
                // 保存文件
                CmsFile cmsFile = new CmsFile();
                cmsFile.setFileId(snowFlakeUtils.nextId());
                cmsFile.setFileName(originalFilename);
                cmsFile.setBatchId(pmsBatch.getBatchId());
                cmsFile.setCreateTime(pmsBatch.getCreateTime());
                cmsFile.setUpdateTime(pmsBatch.getUpdateTime());
                cmsFile.setFileSysCode(pmsBatch.getSysCode());
                if (object.get(CmsConstants.ORDERNUM) != null && !"".equals(object.get(CmsConstants.ORDERNUM))) {
                    cmsFile.setOrderNum(Long.valueOf(String.valueOf(object.get(CmsConstants.ORDERNUM))));
                }
                if (path == null) {
                    cmsFile.setStatus(String.valueOf(Constants.UPLOAD_ING));
                }
                cmsFile.setFileType(extension);
                if (object.get(CmsConstants.BILLCODE) != null && !"".equals(object.get(CmsConstants.BILLCODE))) {
                    CmsBill cmsBill = cmsBillMapper.selectCmsBillByCode(String.valueOf(object.get(CmsConstants.BILLCODE)));
                    cmsFile.setBillId(cmsBill.getId().intValue());
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
                /*if (pmsBatch.getModelList() != null && !"".equals(pmsBatch.getModelList())) {
                    cmsFile.setModelId(pmsBatch.getModelList());
                }*/
                cmsFile.setMd5(String.valueOf(object.get(CmsConstants.MD5)));
                cmsFileMapper.insertCmsFile(cmsFile);
                fileId = cmsFile.getFileId();
                fileName = cmsFile.getFileName();
                object.remove(CmsConstants.BASE64);
                object.put(FILENAME, cmsFile.getFileName());
                if (cmsFile.getFileSize() == null) {
                    object.put("size", new BigDecimal("0").setScale(0, BigDecimal.ROUND_HALF_UP));
                } else {
                    object.put("size", new BigDecimal(cmsFile.getFileSize().toString()).setScale(0, BigDecimal.ROUND_HALF_UP));
                }
                object.put(BATCH_ID, String.valueOf(pmsBatch.getBatchId()));
                object.put(TRANS_FILEPATH, path);
                object.put(FILE_TYPE, cmsFile.getFileType());
                object.put(BILL_ID, String.valueOf(cmsFile.getBillId()));
                object.put(REMARK, cmsFile.getRemark());
                object.put(CREATE_USER, String.valueOf(cmsFile.getCreateUser()));
                break;
            }
        }
        return String.valueOf(fileId) + "#" + fileName + "#" + path + "#" + object.toJSONString();
    }

    /**
     * 获取项目所有分类
     *
     * @param modelId
     * @return
     */
    @Override
    public JSONObject projectBillQuery(String modelId) {
        logger.info("----------------进入获取项目所有分类方法----------------------");
        List<CmsBill> cmsBills = cmsBillMapper.selectCmsBillListByModelAndLeaf(modelId);
        Map<Long, String> billMap = new HashMap<>();
        JSONArray billArray = new JSONArray();
        JSONObject billObject;
        CmsBill cmsBill;
        // 循环遍历所有分类，非叶子节点放入map
        for (int i = 0; i < cmsBills.size(); i++) {
            if (!Constants.IS_LEAF.equals(cmsBills.get(i).getLeaf())) {
                billMap.put(Long.valueOf(cmsBills.get(i).getId()), cmsBills.get(i).getBillCode());
            }
        }
        // 循环遍历所有分类，生成billArray
        for (int i = 0; i < cmsBills.size(); i++) {
            cmsBill = cmsBills.get(i);
            billObject = new JSONObject(true);
            // 根目录父分类置0，其它父分类从map取值
            if (Long.valueOf(Constants.ROOT).equals(cmsBill.getParentId())) {
                billObject.put(BILL_PARENT_CODE, "" + Constants.ROOT);
            } else {
                billObject.put(BILL_PARENT_CODE, billMap.get(cmsBill.getParentId()));
            }
            billObject.put("billName", cmsBill.getBillName());
            billObject.put("billCode", cmsBill.getBillCode());
            billArray.add(billObject);
        }
        JSONObject resultObject = new JSONObject(true);
        resultObject.put("totalResultCode", ResultCode.SUCCESS.code());
        resultObject.put("totalResultMsg", ResultCode.SUCCESS.msg());
        resultObject.put("billList", billArray);
        return resultObject;
    }

    /**
     * 文件信息绑定
     *
     * @param jsonObject
     * @return
     */
    @Override
    public AjaxResult fileInfoBind(JSONObject jsonObject) {
        logger.info("----------------进入文件信息绑定方法----------------------");
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

        CmsBill cmsBill = cmsBillMapper.selectCmsBillByCode(jsonObject.getString("billType"));
        if (null == cmsBill) {
            JSONObject resultObject = new JSONObject(true);
            resultObject.put("totalResultCode", ResultCode.BILL_CODE_NULL.code());
            resultObject.put("totalResultMsg", ResultCode.BILL_CODE_NULL.msg());
            return AjaxResult.error(resultObject);
        }

        String files = jsonObject.getString("files");
        String[] fileArr = files.split(",");
        CmsFile cmsFile;
        String operator = jsonObject.getString("operator");
        // 需要归档的文件
        List<CmsFile> toAms = new ArrayList<>();
        for (int i = 0; i < fileArr.length; i++) {
            cmsFile = cmsFileMapper.selectCmsFileById(Long.valueOf(fileArr[i]));
            toAms.add(cmsFile);
            if (null == cmsFile) {
                JSONObject resultObject = new JSONObject(true);
                resultObject.put("totalResultCode", ResultCode.FILE_NOT_EXIT.code());
                resultObject.put("totalResultMsg", ResultCode.FILE_NOT_EXIT.msg());
                return AjaxResult.error(resultObject);
            } else {
                cmsFile.setBillId(cmsBill.getId().intValue());
                if (null != operator) {
                    cmsFile.setUpdateBy(operator);
                }
                cmsFile.setUpdateTime(new Date());
                cmsFileMapper.updateCmsFile(cmsFile);
            }
        }

        // 需要归档
        if (CmsConstants.TO_ARC_VALUE_YES.equals(jsonObject.get(CmsConstants.TO_ARC_KEY))) {
            jsonObject.put("deptNo", pmsBatch.getDeptId());
            jsonObject.put("arcBillCode", CmsConstants.CONSTRACT_ARC_BILL_CODE);
            jsonObject.put("arcBillDeptCode", CmsConstants.CONSTRACT_ARC_BILL_DEPT_CODE);
            jsonObject.put("arcNo", "");
            jsonObject.put("arcName", null == pmsBatch.getProjectName() ? "" : pmsBatch.getProjectName());
            jsonObject.put("arcCode", "");
            jsonObject.put("respOpName", null == pmsBatch.getProjectManager() ? "" : pmsBatch.getProjectManager());
            jsonObject.put("arcPageNum", "1");
            jsonObject.put("arcNum", "1");
            jsonObject.put("originMode", "经营产生");
            jsonObject.put("valPeriod", "10");
            jsonObject.put("arcLevel", "03");
            jsonObject.put("expenseInvolve", "0");

            jsonObject.put("busiNo", null == pmsBatch.getOperationCode() ? "" : pmsBatch.getOperationCode());
            JSONArray jsonArray = new JSONArray();
            JSONObject fileObject;
            for (int i = 0; i < toAms.size(); i++) {
                cmsFile = toAms.get(i);
                fileObject = new JSONObject(true);
                fileObject.put(FILE_NAME, cmsFile.getFileName());
                fileObject.put(FILE_PATH, cmsFile.getFilePath());
                fileObject.put(FILE_TYPE, cmsFile.getFileType());
                fileObject.put("createUser", null == cmsFile.getCreateUser() ? "" : cmsFile.getCreateUser());
                jsonArray.add(fileObject);
            }
            jsonObject.put(FILE_LIST, jsonArray);
            // 档案数据结构化->ams
            amsBatchRecordFeignClient.dealProcess(jsonObject.toJSONString());
        }
        JSONObject resultObject = new JSONObject(true);
        resultObject.put("totalResultCode", ResultCode.SUCCESS.code());
        resultObject.put("totalResultMsg", ResultCode.SUCCESS.msg());
        return AjaxResult.success(resultObject);
    }
}