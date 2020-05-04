package net.northking.iacmp.imp.util;

import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.ImpServiceConstants;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.utils.HttpClientUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: wei.chen
 * @Date Created: in 2019/11/5 9:57
 */
public class HttpFileTransUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpFileTransUtil.class);

    /**
     * base64字符串转化成图片 并保存到本地
     *
     * @param imgStr      Base64数据
     * @param imgFilePath 保存文件全路径 例子 ：D:ab/cd/1.jpg
     * @return
     */
    public static boolean generateImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        File file = new File(imgFilePath);
        File pathfile = new File(file.getParent());
        //判断是否存在文件夹
        if (!pathfile.exists() && !pathfile.isDirectory()) {
            pathfile.mkdirs();
        }
        //判断是否存在文件
        if (!file.exists()) {
            try {
                boolean flag = file.createNewFile();
                if (!flag) {
                    log.error("文件创建失败!");
                    return false;
                }
            } catch (IOException e) {
                log.error("文件创建失败!", e.fillInStackTrace());
                return false;
            }
        }
        try (OutputStream out = new FileOutputStream(file);) {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            // 生成jpeg图片
            out.write(b);
            return true;
        } catch (Exception e) {
            log.error("文件上传失败!", e.fillInStackTrace());
            return false;
        }
    }

    /**
     * 上传到trans
     *
     * @param filepath
     * @param filename
     * @param transfilepath
     * @return
     */
    public static boolean uploadTrans(String filepath, String filename,
                                      String transfilepath) {
        boolean flag = true;
        File upFile = new File(filepath);
        if (!upFile.exists()) {
            log.warn("要上传的文件不存在!");
            return false;
        }

        // 读取配置信息
        String val = SysConfigInitParamsUtils.getConfig(ImpServiceConstants.UPLOADBUFFERPATH);
        String httpUrlParameters = SysConfigInitParamsUtils.getConfig(ImpServiceConstants.FILETRANSPATHUP);
        try {
            HttpClientUtil.sendByHttp(getServerIp(),
                    Integer.parseInt(SysConfigInitParamsUtils.getConfig(ImpServiceConstants.FILETRANSPORT)),
                    httpUrlParameters + transfilepath + "&" + ImpServiceConstants.LOCALFILEPATH + "=" + val + "/"/*File.separator*/ + filename, val, filename, ImpServiceConstants.K64,
                    ImpServiceConstants.M1, 1);
        } catch (Exception e) {
            log.error("上传trans失败，进行重试", e.fillInStackTrace());
            try {
                HttpClientUtil.sendByHttp(getServerIp(),
                        Integer.parseInt(SysConfigInitParamsUtils.getConfig(ImpServiceConstants.FILETRANSPORT)),
                        httpUrlParameters + transfilepath + "&" + ImpServiceConstants.LOCALFILEPATH + "=" + val + "/"/*File.separator*/ + filename, val, filename, ImpServiceConstants.K64,
                        ImpServiceConstants.M1, 1);
            } catch (Exception e2) {
                log.error("上传trans失败!", e.fillInStackTrace());
                return false;
            }
        } finally {
            try {
                FileUtils.forceDelete(upFile);
            } catch (IOException e) {
                log.error(e.getMessage(), e.fillInStackTrace());
            }
        }
        return flag;

    }

    /**
     * 获取系统环境的ip
     *
     * @return
     * @author yinrui
     * @date 2019-10-31
     */
    private static String getServerIp() {
        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ?
                SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_FILETRANSIP) :
                SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSIP);
    }
}
