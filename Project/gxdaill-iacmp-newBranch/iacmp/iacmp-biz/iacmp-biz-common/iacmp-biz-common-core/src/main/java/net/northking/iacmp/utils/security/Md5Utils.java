package net.northking.iacmp.utils.security;

import java.security.MessageDigest;

import net.northking.iacmp.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Md5加密方法
 *
 * @author wxy
 */
public class Md5Utils {
    private Md5Utils() {
        throw new IllegalStateException("Md5Utils class");
    }

    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);

    private static byte[] md5(String s) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes(Constants.UTF8));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            log.error("MD5 Error...", e);
        }
        return new byte[0];
    }

    private static final String toHex(byte[] hash) {
        if (hash == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) {
        try {
            String toHexTest = toHex(md5(s));
            if (toHexTest != null) {
                return new String(toHexTest.getBytes(Constants.UTF8), Constants.UTF8);
            }
            return s;
        } catch (Exception e) {
            log.error("not supported charset...{}", e);
            return s;
        }
    }
}
