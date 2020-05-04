package net.northking.iacmp.utils.base64;/**
 * @Description:
 * @Author: weizhe.fan
 * @CreateDate: 2019/8/28
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @Description:base64转MultipartFile
 * @Author: weizhe.fan
 * @CreateDate: 2019/8/28
 */
public class Base64ToMultipartFileUtils {

    private static Logger logger = LoggerFactory.getLogger(Base64ToMultipartFileUtils.class);

    public static MultipartFile base64ToMultipart(String fileName, String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(baseStrs[1]);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(fileName, b, baseStrs[0]);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
