package net.northking.iacmp.cms.web.util;

import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.cms.web.runner.CmsBillCommandLineRunner;
import net.northking.iacmp.cms.web.runner.CmsSystemCommandLineRunner;
import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.CmsSystem;

import java.util.Map;

/**
 * @Author：Yanqingyu
 * @ClassName:InitParamsUtil
 * @Description：饿汉单例模式初始化参数工具类
 * @Date：Create in 11:11 AM2019/10/11
 * @Modified by:
 * @Version:1.0
 */
public class CmsInitParamsUtil {


    /**
     * 私有化构造器
     */
    private CmsInitParamsUtil() {

    }

    /**
     * 静态内部类
     */
    private static class CmsInitParamsUtilHolder {
        private static CmsInitParamsUtil instance = new CmsInitParamsUtil();
    }

    /**
     * 恶汉单例模式
     *
     * @return
     */
    public static CmsInitParamsUtil getCmsInitParamsUtil() {

        return CmsInitParamsUtilHolder.instance;
    }

    /**
     * 分类表缓存对象，key为billCode，value为对象实体
     */
    @Getter
    @Setter
    private Map<String, CmsBill> cmsBillMap;
    /**
     * 接入系统缓存对象,key为sysCode，value为对象实体
     */
    @Getter
    @Setter
    private Map<String, CmsSystem> cmsSystemMap;

    /**
     * 通过key获取cmsBill
     *
     * @param key
     * @return
     */
    public static CmsBill getCmsBill(String key) {
        return getCmsInitParamsUtil().cmsBillMap.get(key);
    }

    /**
     * 通过key获取cmsSystem
     *
     * @param key
     * @return
     */
    public static CmsSystem getCmsSystem(String key) {
        return getCmsInitParamsUtil().cmsSystemMap.get(key);
    }

    /**
     * 刷新分类缓存
     */
    public void refreshCacheCmsBill() {
        CmsBillCommandLineRunner.refreshCache();
    }

    /**
     * 刷新系接入业务统缓存
     */
    public void refreshCacheCmsSystem() {
        CmsSystemCommandLineRunner.refreshCache();
    }

    /**
     * 刷新所有缓存
     */
    public void refreshAllCache() {
        CmsBillCommandLineRunner.refreshCache();
        CmsSystemCommandLineRunner.refreshCache();
    }
}
