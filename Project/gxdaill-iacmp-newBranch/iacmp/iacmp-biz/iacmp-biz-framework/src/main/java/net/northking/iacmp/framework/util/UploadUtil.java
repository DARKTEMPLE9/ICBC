package net.northking.iacmp.framework.util;

import lombok.extern.slf4j.Slf4j;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.execption.HttpTransException;
import net.northking.iacmp.utils.HttpClientUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

/**
 * 上传工具类
 *
 * @Author: wei.chen
 * @Version: 1.0
 * @Date Created: 2019-08-26
 */
@Slf4j
public class UploadUtil {

    /**
     * 上传本地文件
     *
     * @param filePath      本地文件全路径
     * @param fileName      本地文件名称
     * @param transFilePath 上传文件全路径（包括文件名）
     * @return 成功true 失败false
     */
    public static boolean uploadTrans(String filePath, String fileName, String transFilePath) {
        boolean flag = true;
        File uploadFile = new File(filePath, fileName);
        //log.info("Upload file is " + uploadFile.getAbsolutePath());
        if (!uploadFile.exists()) {
            log.warn("上传的文件不存在！");
            return false;
        }
        try {
            HttpClientUtil.sendByHttp(SysConfigInitParamsUtils.getConfig(CmsConstants.LOCALTRANSIP),
                    Integer.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT)),
                    SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHUP)
                            + transFilePath + "&localFilePath=" +
                            getServerLocalPath() + fileName,
                    getServerLocalPath(), fileName);
        } catch (Exception e) {
            log.error("上传trans失败，进行重试", e.fillInStackTrace());
            try {
                HttpClientUtil.sendByHttp(SysConfigInitParamsUtils.getConfig(CmsConstants.LOCALTRANSIP),
                        Integer.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT)),
                        SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHUP)
                                + transFilePath + "&localFilePath=" +
                                getServerLocalPath() + fileName,
                        getServerLocalPath(), fileName);
            } catch (Exception e1) {
                log.error("上传trans失败!", e.fillInStackTrace());
                return false;
            }
        } finally {
            // 删除本地文件
            try {
                // delete the locale file
                FileUtils.forceDelete(uploadFile);
            } catch (IOException e) {
                log.error(e.getMessage(), e.fillInStackTrace());
            }
        }

        return flag;
    }

    /**
     * 下载文件到本地
     *
     * @param filePath      本地文件路径
     * @param fileName      本地文件名称
     * @param transFilePath 下载文件全路径（包括文件名）
     * @return 成功true 失败false
     */
    public static boolean downloadTrans(String filePath, String fileName, String transFilePath) {
        boolean flag = true;
        try {
            HttpClientUtil.sendByHttp(getServerIp(),
                    Integer.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT)),
                    SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHDOWN) + transFilePath,
                    filePath, fileName);
        } catch (Exception e) {
            log.error("下载trans失败，进行重试", e.fillInStackTrace());
            try {
                HttpClientUtil.sendByHttp(getServerIp(),
                        Integer.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT)),
                        SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHDOWN) + transFilePath,
                        filePath, fileName);
            } catch (Exception e1) {
                log.error("下载trans失败!", e.fillInStackTrace());
                return false;
            }
        }

        return flag;
    }

    /**
     * 返回url
     */
    public static String downloadUrl(String transFilePath) {
        if (transFilePath == null || "".equals(transFilePath)) {
            return "路径为空，请确定是否成功上传文件";
        }
        return "http://" +
                SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSIP) + ":" +
                SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT) +
                SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHDOWN) +
                transFilePath;
    }

    public static String downloadAPI(Long fileId) {
        return "http://" +
                SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_IP) + ":" +
                SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PORT) +
                "/cms/file/downloadFile?fileId=" + fileId;
    }

    // 在线预览url
    public static String pdfUrl(String fileId, String type, String path, String hadoopType) {
        String serverAddress = "http://" + SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSIP) +
                ":" + CmsConstants.CMS_PORT + "/cms";
        if ("doc".equals(type) || "docx".equals(type) || "xls".equals(type) || "xlsx".equals(type)
                || "ppt".equals(type) || "pptx".equals(type) || "txt".equals(type) || "pdf".equals(type)) {
            if (type == "pdf") {
                return serverAddress + "/pdf/pms/fileView/openPdfView?id=" + fileId;
            } else if (type == "txt") {
                return serverAddress + "/pdf/pms/fileView/openTextView?id=" + fileId;
            } else if (type == "doc" || type == "docx") {
                return serverAddress + "/pdf/pms/fileView/openWordView?id=" + fileId;
            } else {
                // "xlsx" "xls
                return serverAddress + "/pdf/pms/fileView/openExcelView?id=" + fileId;
            }
        } else {
            if (type == "rar" || type == "zip" || type == "tif" || type == "ppt") {
                return "不支持在线预览，请下载后查看";
            }
            return "http://" + SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_IP) + ":" +
                    SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PORT) +
                    "/cms/file/previewUrl?fileId=" + fileId;
        }
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

    /**
     * 获取系统环境的ip
     *
     * @return
     * @author yinrui
     * @date 2019-10-31
     */
    private static String getServerIp() {
        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_FILETRANSIP) : SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSIP);
    }


    /**
     * 上传文件流
     *
     * @param transFilePath fileTrans url
     * @param in            文件流
     * @param bufferSize    缓存区
     * @param flushSize
     * @return 成功true 失败false
     */
    public static boolean uploadTransStream(String transFilePath, InputStream in, int bufferSize, int flushSize, int reTryCount) {
        boolean flag = true;
        if (reTryCount < 1) {
            reTryCount = 1;
        }

        HttpTransException httpTransException = null;
        int i = 0;

        while (i < reTryCount) {
            try {
                HttpClientUtil.sendByHttp(getServerIp(),
                        Integer.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT)),
                        SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHUP)
                                + transFilePath, in, bufferSize, flushSize);
                return flag;
            } catch (Exception var13) {
                httpTransException = new HttpTransException("UnKnowException:" + var13.getMessage());
                ++i;
            }
        }

        return !flag;
    }

    public static boolean downloadTransStream(String transFilePath, OutputStream out, int bufferSize, int flushSize, int reTryCount) {
        boolean flag = true;

        for (int i = 0; i < reTryCount; ++i) {
            try {
                HttpClientUtil.sendByHttp(getServerIp(),
                        Integer.valueOf(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PORT)),
                        SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHDOWN)
                                + transFilePath, out, bufferSize, flushSize);
                return flag;
            } catch (MalformedURLException var13) {
                log.error(var13.getMessage());
                //httpTransException = new HttpTransException("MalformedURLException!");
            } catch (UnknownHostException var14) {
                log.error(var14.getMessage());
                //httpTransException = new HttpTransException("UnknownHostException!");
            } catch (FileNotFoundException var15) {
                log.error(var15.getMessage());
                //httpTransException = new HttpTransException("FileNotFoundException!");
            } catch (IOException var16) {
                log.error(var16.getMessage());
                //httpTransException = new HttpTransException("IOException!" + var16.getMessage());
            } catch (HttpTransException var17) {
                log.error(var17.getMessage());
                //httpTransException = new HttpTransException(var17.getMessage());
            } catch (Exception var18) {
                log.error(var18.getMessage());
                //httpTransException = new HttpTransException("UnKnowException:" + var18.getMessage());
            }
        }

        return !flag;
    }
}
