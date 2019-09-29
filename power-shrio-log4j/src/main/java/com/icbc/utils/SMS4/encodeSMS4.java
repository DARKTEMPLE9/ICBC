package com.icbc.utils.SMS4;

public class encodeSMS4 {
    public static byte[] encodeSMS4(String plaintext, byte[] key) {
        if (plaintext == null || plaintext.equals("")) {
            return null;
        }
        for (int i = plaintext.getBytes().length % 16; i < 16; i++) {
            plaintext += '\0';
        }

        return SMS4.encodeSMS4(plaintext.getBytes(), key);
    }

    /**
     * 不限明文长度的SMS4加密
     *
     * @param plaintext
     * @param key
     * @return
     */
    public static byte[] encodeSMS4(byte[] plaintext, byte[] key) {
        byte[] ciphertext = new byte[plaintext.length];

        int k = 0;
        int plainLen = plaintext.length;
        while (k + 16 <= plainLen) {
            byte[] cellPlain = new byte[16];
            for (int i = 0; i < 16; i++) {
                cellPlain[i] = plaintext[k + i];
            }
            byte[] cellCipher = encode16(cellPlain, key);
            for (int i = 0; i < cellCipher.length; i++) {
                ciphertext[k + i] = cellCipher[i];
            }

            k += 16;
        }

        return ciphertext;
    }

    /**
     * 不限明文长度的SMS4解密
     *
     * @param ciphertext
     * @param key
     * @return
     */
    public static byte[] decodeSMS4(byte[] ciphertext, byte[] key) {
        byte[] plaintext = new byte[ciphertext.length];

        int k = 0;
        int cipherLen = ciphertext.length;
        while (k + 16 <= cipherLen) {
            byte[] cellCipher = new byte[16];
            for (int i = 0; i < 16; i++) {
                cellCipher[i] = ciphertext[k + i];
            }
            byte[] cellPlain = decode16(cellCipher, key);
            for (int i = 0; i < cellPlain.length; i++) {
                plaintext[k + i] = cellPlain[i];
            }

            k += 16;
        }

        return plaintext;
    }

    /**
     * 解密，获得明文字符串
     * @param ciphertext
     * @param key
     * @return
     */
    public static String decodeSMS4toString(byte[] ciphertext, byte[] key) {
        byte[] plaintext = new byte[ciphertext.length];
        plaintext = decodeSMS4(ciphertext, key);
        return new String(plaintext);
    }

    /**
     * 只加密16位明文
     *
     * @param plaintext
     * @param key
     * @return
     */
    private static byte[] encode16(byte[] plaintext, byte[] key) {
        byte[] cipher = new byte[16];
        SMS4 sm4 = new SMS4();
        sm4.sms4(plaintext, 16, key, cipher, ENCRYPT);

        return cipher;
    }

    /**
     * 只解密16位密文
     *
     * @param plaintext
     * @param key
     * @return
     */
    private static byte[] decode16(byte[] ciphertext, byte[] key) {
        byte[] plain = new byte[16];
        SMS4 sm4 = new SMS4();
        sm4.sms4(ciphertext, 16, key, plain, DECRYPT);

        return plain;
    }
}
