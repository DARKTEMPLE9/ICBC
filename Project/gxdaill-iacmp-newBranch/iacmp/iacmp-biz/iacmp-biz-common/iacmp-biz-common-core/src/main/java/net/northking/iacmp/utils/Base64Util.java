package net.northking.iacmp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>Base64工具类</p>
 *
 * @Author xiehui
 * Create on: 2017年3月21日
 * Last Updated:
 */

public class Base64Util {
    private static Logger log = LoggerFactory.getLogger(Base64Util.class);
    private HttpURLConnection httpUrl = null;


    public void closeHttpConn() {
        httpUrl.disconnect();
    }

    /**
     * 从URL中读取图片,转换成流形式.
     *
     * @param destUrl
     * @return
     */
    public InputStream saveToFile(String destUrl) {

        URL url = null;
        InputStream in = null;
        try {
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            in = httpUrl.getInputStream();
            return in;
        } catch (Exception e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
        return null;
    }

    /**
     * 读取输入流,转换为Base64字符串
     *
     * @param input
     * @return
     */
    public String getImageStrByInPut(InputStream input) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            data = new byte[input.available()];
            input.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }


    /**
     * 图片转化成base64字符串 将图片文 件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @return
     */
    public static String getImageStr(File file) {
        byte[] data = null;
        // 读取图片字节数组
        try (InputStream in = new FileInputStream(file);) {
            data = new byte[in.available()];
            int count = 0;
            while ((count = in.read(data)) > 0) {
//				in.read(data);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    public static int getFileSizeByBase64(String base64) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return decoder.decodeBuffer(base64).length;
        } catch (IOException e) {
            log.error("base64转码异常", e.fillInStackTrace());
            return 0;
        }
    }

    /**
     * base64字符串转化成图片 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imgStr 数据内容(字符串)
     * @param path   输出路径
     * @return
     */
    public static boolean generateImage(String imgStr, String path) {
        // 图像数据为空
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e1) {
                log.error(e.getMessage(), e.fillInStackTrace());
            }
            return false;
        }
    }

}
