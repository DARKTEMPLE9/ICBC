package net.northking.iacmp.ams.web.utils;

import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.framework.util.UploadUtil;
import net.northking.iacmp.utils.NumConstants;
import net.northking.iacmp.utils.StringUtils;
//import org.jodconverter.DocumentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * pdf转换工具类
 *
 * @author yinrui
 * @date 2019-09-30
 */

public class FileConversionUtils {

//    private static final Logger log = LoggerFactory.getLogger(FileConversionUtils.class);
//
//    /**
//     * 文件转化pdf并输出流
//     *
//     * @param response
//     * @param filePath 文件的数据库存放地址
//     * @param fileType 文件类型
//     * @param fileName 文件名
//     */
//    public static void fileToPdf(DocumentConverter converter, HttpServletResponse response,
//                                 String filePath, String fileType, String fileName) {
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.setContentType("application/pdf;charset=UTF-8");
//        ServletOutputStream outputStream = null;
//        BufferedInputStream in = null;
//        String dbPath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
//        // 本地服务器下载地址
//        File savePath = new File(getServerLocalPath() + dbPath);
//        log.info("savePath=" + savePath);
//        if (!savePath.exists()) {
//            savePath.mkdirs();
//        }
//        try {
//            outputStream = response.getOutputStream();
//            File pdfFile = null;
//            if (fileType != null && "pdf".equals(fileType.toLowerCase())) {
//                pdfFile = new File(getServerPdfLocalPath() + filePath);
//                // 如果pdf文件不存在，则下载到本地
//                if (!pdfFile.exists()) {
//                    boolean b = UploadUtil.downloadTrans(getServerPdfLocalPath() + dbPath,
//                            filePath.substring(filePath.lastIndexOf("/") + 1), filePath);
//                    if (!b) {
//                        throw new Exception("HDFS下载失败，请联系服务器管理员");
//                    }
//                }
//            } else {
//                File localFile = new File(getServerLocalPath() + filePath);
//                log.info("localFile=" + localFile);
//                if (!localFile.exists()) {
//                    String[] split = filePath.split("/");
//                    boolean b = UploadUtil.downloadTrans(getServerLocalPath() + dbPath, split[split.length - 1], filePath);
//                    if (!b) {
//                        throw new Exception("HDFS下载失败，请联系服务器管理员");
//                    }
//                }
//                // 服务器本地的转化的PDF存放路径
//                String pdfPath = getServerPdfLocalPath() + dbPath;
//                log.info("pdfPath=" + pdfPath);
//                // 转换之后文件生成的地址
//                File newFile = new File(pdfPath);
//                if (!newFile.exists()) {
//                    newFile.mkdirs();
//                }
//                String pdfFilePath = pdfPath + fileName.substring(0, fileName.lastIndexOf(".")) + ".pdf";
//                // 要转化的文件
//                pdfFile = new File(pdfFilePath);
//                log.info("pdfFilePath=" + pdfFilePath);
//                // 如果已存在，则无需再转化
//                if (!pdfFile.exists()) {
//                    converter.convert(localFile).to(pdfFile).execute();
//                }
//            }
//            //使用response,将pdf文件以流的方式发送的前段
//            // 读取文件
//            in = new BufferedInputStream(new FileInputStream(pdfFile));
//            // 读取生成的PDF文件
//            byte[] b = new byte[NumConstants.NUM_1024];
//            int i = 0;
//            while ((i = in.read(b)) != -1) {
//                outputStream.write(b, 0, b.length);
//            }
//            outputStream.flush();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        }
//    }

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
     * 获取本地存放pdf文件的路径 add by yinrui 2019-10-31
     * windows D:xx/xx/
     * linux /xx/xx/xx/
     *
     * @return
     */
    private static String getServerPdfLocalPath() {
        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_SERVER_PDF_PATH) : SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PDF_PATH);
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
}
