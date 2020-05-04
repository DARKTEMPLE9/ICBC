package net.northking.iacmp.http.chunk.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.northking.iacmp.http.chunk.domain.HttpResult;
import net.northking.iacmp.http.chunk.util.HttpChunkUtils;
import net.northking.iacmp.http.chunk.util.HttpStringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * HttpURLConnection上传多个文件
 *
 * @author: wei.chen
 * @date Created: in 2019/9/20 11:24
 */
public class UploadClient {
    private static final Logger log = LoggerFactory.getLogger(HttpChunkUtils.class);

    private static int RES_CODE_SUCCESS = 200;

    private static String FILE_EXISTS_LIST_KEY = "fileExistList";

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(UploadClient.class.getResourceAsStream("/upload.properties"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 上传元数据和多个文件
     *
     * @param path    待上传文件所在目录或者单个文件路径
     * @param jsonStr 待上传元数据
     * @return HttpResult
     */
    public static HttpResult upload(String path, String jsonStr) {
        return upload(path, jsonStr, null);
    }

    /**
     * 上传元数据和多个文件
     *
     * @param path    待上传文件所在目录或者单个文件路径
     * @param jsonStr 待上传元数据
     * @param url     上传元数据的url
     * @return
     */
    public static HttpResult upload(String path, String jsonStr, String url) {
        String suffixOk = properties.getProperty("SUFFIX_OK");
        String fileListKey = properties.getProperty("FILE_LIST_KEY");
        String fileNameKey = properties.getProperty("FILE_NAME_KEY");
        String fileMd5Key = properties.getProperty("FILE_MD5_KEY");
        String serverUrl = url;
        if (null == serverUrl || serverUrl.trim().length() == 0) {
            serverUrl = properties.getProperty("METADATA_SERVER_URL");
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Map<String, String> md5Map = new HashMap<>();
        // 扫描目录，获取文件列表
        File dir = new File(path);
        if (!dir.exists()) {
            log.error("路径{}不存在！", path);
            return HttpResult.error("路径" + path + "不存在！");
        }
        String parentPath;
        if (dir.isDirectory()) {
            parentPath = path;
        } else {
            parentPath = dir.getParent();
        }
        String[] fileNames = filterFileNames(path, null);

        // 如果json报文中有fileList，则把md5校验值加入数组；否则，添加fileList
        JSONArray fileList = jsonObject.getJSONArray(fileListKey);
        if (fileList != null) {
            List<String> fileNameList = new ArrayList<>();
            for (int i = 0; i < fileList.size(); i++) {
                fileNameList.add(fileList.getJSONObject(i).getString("fileName"));
            }
            for (String fileName : fileNameList) {
                File file = new File(parentPath, fileName);
                String md5 = getMd5ByFile(file);
                md5Map.put(fileName, md5);
                for (int i = 0; i < fileList.size(); i++) {
                    JSONObject fileObject = fileList.getJSONObject(i);
                    if (fileName.equals(fileObject.getString(fileNameKey))) {
                        fileObject.put(fileMd5Key, md5);
                        break;
                    }
                }
            }
        } else {
            fileList = new JSONArray();
            JSONObject fileObject;
            for (String fileName : fileNames) {
                File file = new File(parentPath, fileName);
                String md5 = getMd5ByFile(file);
                md5Map.put(fileName, md5);
                fileObject = new JSONObject();
                fileObject.put(fileNameKey, fileName);
                fileObject.put(fileMd5Key, md5);
                fileList.add(fileObject);
            }
            jsonObject.put(fileListKey, fileList);
        }

        // 先发元数据
        HttpResult ajaxResult = uploadJson(jsonObject.toJSONString(), serverUrl);
        log.info("上传元数据，返回：" + ajaxResult.toString());
        // 内管返回秒传的文件列表
        JSONArray fileExistsList;
        // 秒传的文件列表
        JSONArray existsList = new JSONArray();
        if (ajaxResult.getCode() == HttpResult.Type.SUCCESS.value()) {
            fileExistsList = (JSONArray) ajaxResult.getData();
            // 已上传的文件，直接生成.ok
            if (HttpStringUtils.isNotNull(fileExistsList)) {
                for (int i = 0; i < fileExistsList.size(); i++) {
                    JSONObject fileExists = fileExistsList.getJSONObject(i);
                    existsList.add(fileExists);
                    File okFile = new File(parentPath, fileExists.getString(fileNameKey) + suffixOk);
                    if (!okFile.exists()) {
                        try {
                            if (okFile.createNewFile()) {
                                log.info("生成{}成功！", okFile.getName());
                            }
                        } catch (IOException e) {
                            log.error("生成ok文件{}错误：{}", fileExists.getString(fileNameKey), e.getMessage());
                        }
                    }
                }
            }
        } else {
            return HttpResult.error(ajaxResult.getMsg());
        }

        // 上传文件
        ajaxResult = uploadFiles(path, md5Map, fileExistsList, serverUrl);
        JSONObject resultObj = (JSONObject) ajaxResult.getData();
        // 将秒传文件列表添加到上传成功列表中
        JSONArray successList = (JSONArray) resultObj.getJSONArray(fileListKey);
        if (null != successList) {
            if (null != existsList) {
                for (int i = 0; i < existsList.size(); i++) {
                    successList.add(existsList.getJSONObject(i));
                }
            }
        } else {
            successList = existsList;
        }
        resultObj.put(fileListKey, successList);

        return ajaxResult;
    }

    public static HttpResult uploadJson(String jsonStr) {
        return uploadJson(jsonStr, null);
    }

    public static HttpResult uploadJson(String jsonStr, String url) {
        String serverUrl = url;
        if (null == serverUrl || serverUrl.trim().length() == 0) {
            serverUrl = properties.getProperty("METADATA_SERVER_URL");
        }
        try {
            String jsonResult = HttpChunkUtils.sendHttpJson(
                    serverUrl, jsonStr);
            JSONObject result = JSONObject.parseObject(jsonResult);
            if (result.getInteger(HttpResult.CODE_TAG) == HttpResult.Type.SUCCESS.value()) {
                return HttpResult.success(result.get(HttpResult.DATA_TAG));
            } else {
                return HttpResult.error(result.getString(HttpResult.MSG_TAG), result.get(HttpResult.DATA_TAG));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 扫描目录，上传文件，可以尝试三次
     *
     * @param path   待上传文件所在目录或者单个文件路径
     * @param md5Map 参数列表
     * @return AjaxResult code 0 成功 301 警告 500 失败
     */
    private static HttpResult uploadFiles(String path, Map<String, String> md5Map, JSONArray fileExistsList, String serverUrl) {
        int retryTimes = Integer.valueOf(properties.getProperty("RETRY_TIMES"));
        String suffixOk = properties.getProperty("SUFFIX_OK");
        String fileListKey = properties.getProperty("FILE_LIST_KEY");
        String fileUrl = "";
        if (StringUtils.isEmpty(serverUrl)) {
            fileUrl = properties.getProperty("FILE_SERVER_URL");
        } else {
            fileUrl = serverUrl.replaceAll("acsService", "uploadMulti");
        }
        int bufferLength = Integer.valueOf(properties.getProperty("BUFFER_LENGTH"));

        // 扫描目录，获取文件列表
        File dir = new File(path);
        if (!dir.exists()) {
            log.error("路径{}不存在！", path);
            return HttpResult.error("路径" + path + "不存在！");
        }
        String parentPath;
        if (dir.isDirectory()) {
            parentPath = path;
        } else {
            parentPath = dir.getParent();
        }
        String[] fileNames = filterFileNames(path, fileExistsList);

        // 重试次数
        int times = 0;
        // 上传成功的文件列表
        JSONArray jsonArray = new JSONArray();
        while (ObjectUtils.allNotNull(fileNames) && fileNames.length > 0 && times < retryTimes) {
            for (String fileName : fileNames) {
                HttpResult result = uploadFile(parentPath, fileName, md5Map, fileUrl, bufferLength);
                log.info("上传文件{}，返回：{}", fileName, result.toString());
                if (HttpResult.Type.SUCCESS.value() == result.getCode()) {
                    // 成功在目录下生成文件名.ok
                    File okFile = new File(parentPath, fileName + suffixOk);
                    try {
                        if (okFile.createNewFile()) {
                            log.info("生成{}成功！", okFile.getName());
                        }
                    } catch (IOException e) {
                        log.error(e.getMessage());
                        //return AjaxResult.error(fileName + suffixOk + "生成失败！");
                    }
                    jsonArray.add(result.getData());
                }
            }
            fileNames = filterFileNames(path, fileExistsList);
            times++;
        }

        JSONObject resultObj = new JSONObject();
        if (ObjectUtils.allNotNull(fileNames) && fileNames.length > 0) {
            resultObj.put("totalResultCode", HttpResult.Type.ERROR.value());
            resultObj.put("totalResultMsg", "上传失败！");
            resultObj.put(fileListKey, jsonArray);
            return HttpResult.error("上传失败！", resultObj);
        } else {
            for (int i = 0; i < jsonArray.size(); i++) {
                if (!jsonArray.getJSONObject(i).getString("resultCode").equals("0")) {
                    resultObj.put("totalResultCode", HttpResult.Type.ERROR.value());
                    resultObj.put("totalResultMsg", "上传失败！");
                    resultObj.put(fileListKey, jsonArray);
                    return HttpResult.error("上传失败！", resultObj);
                }
            }
            resultObj.put("totalResultCode", HttpResult.Type.SUCCESS.value());
            resultObj.put("totalResultMsg", "上传成功！");
            resultObj.put(fileListKey, jsonArray);
            return HttpResult.success(resultObj);
        }
    }

    /**
     * 上传文件
     *
     * @param parentPath 待上传文件所在目录
     * @param fileName   待上传文件名
     * @param md5Map     参数列表
     * @return
     */
    public static HttpResult uploadFile(String parentPath, String fileName, Map<String, String> md5Map,
                                        String serverUrl, int bufferLength) {
        log.info("Starting to upload " + fileName + " ...");
        long start = System.currentTimeMillis();
        File file = new File(parentPath, fileName);
        if (!file.exists()) {
            log.error("文件{}不存在！", file.getAbsolutePath());
            return HttpResult.error("文件" + file.getAbsolutePath() + "不存在！");
        }
        HttpURLConnection connection = null;
        OutputStream os = null;
        FileInputStream fis = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(serverUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            connection.setChunkedStreamingMode(0);
            // 文件名添加到header中
            connection.setRequestProperty("FileName", URLEncoder.encode(fileName, "UTF-8"));
            // 缓冲区大小添加到header中
            connection.setRequestProperty("BufferLength", bufferLength + "");
            // md5校验值添加到header中
            connection.setRequestProperty("Md5", md5Map.get(fileName));
            connection.connect();
            os = new DataOutputStream(connection.getOutputStream());
            fis = new FileInputStream(file);
            byte[] buffer = new byte[bufferLength];
            int len;
            long total = 0;
            while ((len = fis.read(buffer)) != -1) {
                total += len;
                os.write(buffer, 0, len);
            }
            long end = System.currentTimeMillis();
            log.info("{}上传耗时{}ms！", fileName, (end - start));
            if (connection.getResponseCode() == RES_CODE_SUCCESS) {
                InputStream is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String strRead = null;
                while ((strRead = br.readLine()) != null) {
                    sb.append(strRead);
                }
                br.close();
                String result = sb.toString();
                JSONObject resultObj = JSONObject.parseObject(result);
                if (HttpResult.Type.SUCCESS.value() == resultObj.getInteger(HttpResult.CODE_TAG)) {
                    return HttpResult.success(resultObj.getJSONObject(HttpResult.DATA_TAG));
                } else {
                    return HttpResult.error(resultObj.getString(HttpResult.MSG_TAG));
                }

            } else {
                return HttpResult.error(URLDecoder.decode(
                        connection.getResponseMessage(), "UTF-8"));
            }
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            return HttpResult.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
            return HttpResult.error(e.getMessage());
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (null != connection) {
                connection.disconnect();
            }
        }
    }

    /**
     * 过滤掉已经上传成功的文件（即秒传文件或存在文件名.ok）
     *
     * @param path           待上传文件目录或者单个文件路径
     * @param fileExistsList 内管返回的秒传文件列表
     * @return
     */
    private static String[] filterFileNames(String path, JSONArray fileExistsList) {
        String suffixOk = properties.getProperty("SUFFIX_OK");
        String fileNameKey = properties.getProperty("FILE_NAME_KEY");
        List<String> toUpload = new ArrayList<>();
        List<String> existsList = new ArrayList<>();
        for (int i = 0; null != fileExistsList && i < fileExistsList.size(); i++) {
            existsList.add(fileExistsList.getJSONObject(i).getString(fileNameKey));
        }
        File dir = new File(path);
        if (dir.isDirectory()) {
            // path是目录
            String[] fileArray = dir.list();
            List<String> fileNameList = Arrays.asList(fileArray);
            for (String fileName : fileArray) {
                // 不是已经上传过的文件
                if (existsList.contains(fileName)) {
                    continue;
                }
                // 过滤文件名，只要没有文件名.ok存在，就需要上传
                if (!fileName.endsWith(suffixOk)) {
                    if (!fileNameList.contains(fileName + suffixOk)) {
                        toUpload.add(fileName);
                    }
                }
            }
        } else {
            // path是文件
            File parent = dir.getParentFile();
            String[] fileArray = parent.list();
            String fileName = dir.getName();
            List<String> fileNameList = Arrays.asList(fileArray);
            // 不是秒传文件
            if (!existsList.contains(fileName)) {
                // 过滤文件名，只要没有文件名.ok存在，就需要上传
                if (!fileNameList.contains(fileName + suffixOk)) {
                    toUpload.add(fileName);
                }
            }
        }
        return toUpload.toArray(new String[toUpload.size()]);
    }

    /**
     * 文件md5加密
     *
     * @param file
     * @return
     */
    private static String getMd5ByFile(File file) {
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
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return value;
    }

    public static void main(String[] args) throws IOException {
        // json+目录
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("pe_name", "普惠信贷事业部智能分发二期");
        jsonObject.put("pe_id", "BSN20190266");
        JSONObject creator = new JSONObject();
        creator.put("name", "刘贺伟");
        creator.put("key", "649");
        jsonObject.put("creator", creator);
        JSONObject peDept = new JSONObject();
        peDept.put("name", "普惠信贷事业部");
        peDept.put("key", "111");
        jsonObject.put("pe_dept", peDept);
        JSONObject budgetDept = new JSONObject();
        budgetDept.put("name", "普惠信贷事业部");
        budgetDept.put("key", "111");
        jsonObject.put("budget_deptname", budgetDept);
        jsonObject.put("pe_createdate", "");
        jsonObject.put("pe_processdate", "");
        jsonObject.put("infco_processdate", "");
        JSONArray projMgr = new JSONArray();
        jsonObject.put("projectmanager", projMgr);
        JSONObject mgrObj = new JSONObject();
        projMgr.add(mgrObj);
        mgrObj.put("name", "孙勃洋");
        mgrObj.put("key", "565");
        JSONArray prodMgr = new JSONArray();
        jsonObject.put("productmanager", prodMgr);
        mgrObj = new JSONObject();
        prodMgr.add(mgrObj);
        mgrObj.put("name", "陶兴禹");
        mgrObj.put("key", "172");
        JSONArray infcoOff = new JSONArray();
        jsonObject.put("infco_officer", infcoOff);
        JSONObject offObj = new JSONObject();
        infcoOff.add(offObj);
        offObj.put("name", "朱清沂");
        offObj.put("key", "351");
        JSONArray opinion = new JSONArray();
        jsonObject.put("signature_opinion", opinion);
        JSONObject opiObj = new JSONObject();
        opinion.add(opiObj);
        opiObj.put("date", "2019-09-17");
        opiObj.put("opt", "普惠信贷事业部智能分发二期立项申请，请领导审核。");
        opiObj.put("name", "刘贺伟");
        opiObj.put("time", "17:46:28");
        opiObj.put("key", "liuhewei");
        jsonObject.put("ysbh", "");
        jsonObject.put("bmsapid", "");
        jsonObject.put("requestid", "25849");
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("ATTACHMENTS", jsonArray);
        JSONObject fileObject = new JSONObject();
        jsonArray.add(fileObject);
        fileObject.put("NAME", "thymeleaf_3.0.5_中文参考手册1.pdf");
        fileObject.put("TYPE", "pdf");
        fileObject = new JSONObject();
        jsonArray.add(fileObject);
        fileObject.put("NAME", "test.txt");
        fileObject.put("TYPE", "txt");
        log.info(UploadClient.upload("/test", jsonObject.toJSONString()).toString());*/

        // json+单个文件
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("pe_name", "普惠信贷事业部智能分发二期");
        jsonObject.put("pe_id", "BSN20190266");
        JSONObject creator = new JSONObject();
        creator.put("name", "刘贺伟");
        creator.put("key", "649");
        jsonObject.put("creator", creator);
        JSONObject peDept = new JSONObject();
        peDept.put("name", "普惠信贷事业部");
        peDept.put("key", "111");
        jsonObject.put("pe_dept", peDept);
        JSONObject budgetDept = new JSONObject();
        budgetDept.put("name", "普惠信贷事业部");
        budgetDept.put("key", "111");
        jsonObject.put("budget_deptname", budgetDept);
        jsonObject.put("pe_createdate", "");
        jsonObject.put("pe_processdate", "");
        jsonObject.put("infco_processdate", "");
        JSONArray projMgr = new JSONArray();
        jsonObject.put("projectmanager", projMgr);
        JSONObject mgrObj = new JSONObject();
        projMgr.add(mgrObj);
        mgrObj.put("name", "孙勃洋");
        mgrObj.put("key", "565");
        JSONArray prodMgr = new JSONArray();
        jsonObject.put("productmanager", prodMgr);
        mgrObj = new JSONObject();
        prodMgr.add(mgrObj);
        mgrObj.put("name", "陶兴禹");
        mgrObj.put("key", "172");
        JSONArray infcoOff = new JSONArray();
        jsonObject.put("infco_officer", infcoOff);
        JSONObject offObj = new JSONObject();
        prodMgr.add(offObj);
        offObj.put("name", "朱清沂");
        offObj.put("key", "351");
        JSONArray opinion = new JSONArray();
        jsonObject.put("signature_opinion", opinion);
        JSONObject opiObj = new JSONObject();
        opinion.add(opiObj);
        opiObj.put("date", "2019-09-17");
        opiObj.put("opt", "普惠信贷事业部智能分发二期立项申请，请领导审核。");
        opiObj.put("name", "刘贺伟");
        opiObj.put("time", "17:46:28");
        opiObj.put("key", "liuhewei");
        jsonObject.put("ysbh", "");
        jsonObject.put("bmsapid", "");
        jsonObject.put("requestid", "25849");
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("ATTACHMENTS", jsonArray);
        JSONObject fileObject = new JSONObject();
        jsonArray.add(fileObject);
        fileObject.put("NAME", "thymeleaf_3.0.5_中文参考手册1.pdf");
        fileObject.put("TYPE", "pdf");
        fileObject = new JSONObject();
        jsonArray.add(fileObject);
        fileObject.put("NAME", "test.txt");
        fileObject.put("TYPE", "txt");
        log.info(UploadClient.upload("/test/thymeleaf_3.0.5_中文参考手册1.pdf", jsonObject.toJSONString()).toString());*/

        // PMS json+文件
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("tranCode", "cms_3001");
        jsonObject.put("sysCode", "PMO");
        jsonObject.put("authCode", "PMO");
        jsonObject.put("deptNo", "1000");
        jsonObject.put("arcBillCode", "10100");
        jsonObject.put("arcBillDeptCode", "11103");
        jsonObject.put("arcNo", "11");
        jsonObject.put("arcName", "11");
        jsonObject.put("arcCode", "11");
        jsonObject.put("arcCreTime", "1982-11-11");
        jsonObject.put("respOpName", "11");
        jsonObject.put("arcPageNum", "11");
        jsonObject.put("arcNum", "11");
        jsonObject.put("originMode", "11");
        jsonObject.put("originMode", "11");
        jsonObject.put("valPeriod", "10");
        jsonObject.put("arcLevel", "2");
        jsonObject.put("expenseInvolve", "4");
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("fileList", jsonArray);
        JSONObject fileObject = new JSONObject();
        jsonArray.add(fileObject);
        fileObject.put("creater", "chenwei");
        fileObject.put("projectId", "77777");
        fileObject.put("name", "百信银行内容管理平台接口服务说明V1.1.doc");
        //fileObject.put("name", "test1.txt");
        fileObject.put("keyword", "Purchase");
        fileObject.put("busiNo", "abcdefghi");
        fileObject.put("Md5", "9a9b51c6a68508e42743217250d44eaf");
        //fileObject.put("Md5", "4a46d1a4d00eff7a572ed2199091e50a");
        log.info(UploadClient.upload("C:\\Users\\Lavine\\Desktop\\iacmp-http-chunk\\test\\百信银行内容管理平台接口服务说明V1.1.doc", jsonObject.toJSONString()).toString());*/

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tranCode", "cms_3001");
        jsonObject.put("sysCode", "PMO");
        jsonObject.put("authCode", "PMO");
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("fileList", jsonArray);
        JSONObject fileObject = new JSONObject();
        jsonArray.add(fileObject);
        fileObject.put("fileName", "47c44f919a66a61d5d7288bd2e73bbce.jpg");
        fileObject.put("fileType", "jpg");
        fileObject.put("createUser", "chenwei");
        fileObject.put("operationCode", "77777");
        fileObject.put("billCode", "approval");
        fileObject.put("regbillglideNo", "abcdefghi");
        //fileObject.put("Md5", "65d02f7b0e63b41eced56993fe5a95e9");

        /*JSONObject fileObject1 = new JSONObject();
        jsonArray.add(fileObject1);
        fileObject1.put("fileName", "jiaofu.pdf");
        fileObject1.put("fileType", "pdf");
        fileObject1.put("createUser", "chenwei");
        fileObject1.put("operationCode", "77777");
        fileObject1.put("billCode", "deliver");
        fileObject1.put("regbillglideNo", "abcdefghi");
        fileObject1.put("Md5", "f996ac20690104648e025b1a571f416f");

        JSONObject fileObject2 = new JSONObject();
        jsonArray.add(fileObject2);
        fileObject2.put("fileName", "jiexiang.gif");
        fileObject2.put("fileType", "gif");
        fileObject2.put("createUser", "chenwei");
        fileObject2.put("operationCode", "77777");
        fileObject2.put("billCode", "termNode");
        fileObject2.put("regbillglideNo", "abcdefghi");
        fileObject2.put("Md5", "b6e0ecdfa6be71508d9d8adc31da7336");*/
        log.info(UploadClient.upload("C:\\Users\\Lavine\\Desktop\\iacmp-http-chunk\\test\\47c44f919a66a61d5d7288bd2e73bbce.jpg", jsonObject.toJSONString()).toString(), "http://localhost:8098/cms/file/acsService");
    }
}
