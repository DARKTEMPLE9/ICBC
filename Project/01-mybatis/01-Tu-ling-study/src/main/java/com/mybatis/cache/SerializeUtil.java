package com.mybatis.cache;

import java.io.*;

/**
 * 二级缓存使用redis
 * 序列化处理   --- 使用Java原声序列化
 */
public class SerializeUtil {

    public static byte[] serialize(Object o) {
        ObjectOutputStream oss = null;
        ByteArrayOutputStream baos = null;

        try {
            baos = new ByteArrayOutputStream();
            oss = new ObjectOutputStream(baos);
            oss.writeObject(o);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 反序列化
    * */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;

        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
