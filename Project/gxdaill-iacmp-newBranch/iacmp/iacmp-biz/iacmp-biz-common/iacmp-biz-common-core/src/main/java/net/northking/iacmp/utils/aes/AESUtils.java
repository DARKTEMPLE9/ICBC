package net.northking.iacmp.utils.aes;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * AES对称加密工具
 *
 * @Description:AES对称加密算法组件
 * @Author: weizhe.fan
 * @CreateDate: 2019/12/20
 */
public class AESUtils {
    private static Logger logger = LoggerFactory.getLogger(AESUtils.class);

    /**
     * 私有化构造方法
     */
    private AESUtils() {
    }

    /**
     * 生成密钥方法
     *
     * @return
     */
    public static String init() {
        KeyGenerator kgen = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());

        }
        //设置密钥长度
        kgen.init(128);
        //生成密钥
        SecretKey skey = kgen.generateKey();
        byte[] encoded = skey.getEncoded();
        System.out.println("base64加密后的密钥为：" + Base64.encodeBase64String(encoded));
        return Base64.encodeBase64String(encoded);
    }

    /**
     * 加密
     *
     * @param sSrc
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc, byte[] sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static byte[] decrypt(String sSrc, byte[] sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                return original;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "7Rj4PUc/58SZvC7ZP8AV/Q==";
        // 需要加密的字串
        String cSrc = "air,2582dd863c1c50525a267e1cbe656929,41323812591652864,6000";
        System.out.println(cSrc);
        // 加密
        String enString = encrypt(cSrc, Base64.decodeBase64(cKey));
        System.out.println("加密后的字串是：" + enString);

        // 解密
        byte[] DeString = decrypt(enString, Base64.decodeBase64(cKey));
        System.out.println("解密后的字串是：" + new String(DeString));

        init();
    }
}
