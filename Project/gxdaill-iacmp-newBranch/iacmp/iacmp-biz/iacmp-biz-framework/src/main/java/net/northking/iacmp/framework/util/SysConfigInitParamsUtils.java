package net.northking.iacmp.framework.util;

import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.framework.runner.SysConfigCommandLineRunner;

import java.util.Map;

/**
 * @Author：Yanqingyu
 * @ClassName:SysConfigInitParamsUtils
 * @Description：TODO
 * @Date：Create in 8:42 PM2019/10/23
 * @Modified by:
 * @Version:1.0
 */
public class SysConfigInitParamsUtils {
    /**
     * 私有化构造器
     */
    private SysConfigInitParamsUtils() {

    }

    /**
     * 静态内部类
     */
    private static class SysConfigInitParamsUtilsHolder {
        private static SysConfigInitParamsUtils instance = new SysConfigInitParamsUtils();
    }

    /**
     * 恶汉单例模式
     *
     * @return
     */
    public static SysConfigInitParamsUtils getSysConfigInitParamsUtils() {

        return SysConfigInitParamsUtilsHolder.instance;
    }

    /**
     * 内管系统配置信息缓存对象，key为configKey，value为configValue
     */
    @Getter
    @Setter
    private Map<String, String> sysConfigMap;


    /**
     * 通过key获取SysConfig 的value
     *
     * @param key
     * @return
     */
    public static String getConfig(String key) {
        return getSysConfigInitParamsUtils().getSysConfigMap().get(key);
    }

    /**
     * 刷新配置表缓存
     */
    public void refreshCacheSysConfig() {
        SysConfigCommandLineRunner.refreshCache();
    }

    /**
     * 刷新所有缓存
     */
    public void refreshAllCache() {
        SysConfigCommandLineRunner.refreshCache();
    }
}
