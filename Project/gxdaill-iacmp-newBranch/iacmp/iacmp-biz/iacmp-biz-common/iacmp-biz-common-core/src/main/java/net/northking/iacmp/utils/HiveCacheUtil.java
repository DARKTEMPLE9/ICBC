package net.northking.iacmp.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wei.chen
 * @Date Created: in 2019-12-30 10:46
 */
public class HiveCacheUtil {

    private static Map<String, Object> cacheMap = new HashMap<>();

    public static void putCache(String key, Object value) {
        cacheMap.put(key, value);
    }

    public static Object getCache(String key) {
        return cacheMap.remove(key);
    }

    public static boolean containsKey(String key) {
        return cacheMap.containsKey(key);
    }
}
