package net.northking.iacmp.utils.base64;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description:base64转文件
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/5
 */
public class Base64ToFileUtil {

    private static Logger logger = LoggerFactory.getLogger(Base64ToFileUtil.class);

    public static File base64ToFile(String base64, String filePath) {
        if (base64 == null || "".equals(base64)) {
            return null;
        }
        byte[] buff = Base64.decode(base64);
        File file = null;
        FileOutputStream fout = null;
        try {
            file = new File(filePath);
            fout = new FileOutputStream(file);
            fout.write(buff);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return file;
    }
}
