package net.northking.iacmp.ams.web.utils;


import net.northking.iacmp.common.bean.domain.ams.*;

import java.util.List;
import java.util.Map;

/**
 * 单例模式的初始化参数工具类
 * <p>
 * 刘铎
 * <p>
 * 2016年5月23日09:38:17
 */
public class InitParamsUtil {

    private static volatile InitParamsUtil initParamsUtil;

    private InitParamsUtil() {

    }

    public static InitParamsUtil getInitParamsUtil() {
        if (initParamsUtil == null) {
            synchronized (InitParamsUtil.class) {
                if (initParamsUtil == null) {
                    initParamsUtil = new InitParamsUtil();
                }
            }
        }
        return initParamsUtil;
    }

    /* 接入系统表缓存对象。 KEY就是系统标示。VALUE就是对象实体 */
    private Map<String, ImAccessSystem> accessSystem;
    private Map<String, SmParam> smParam;
    private Map<String, SmOrgan> smOrgan;
    private List<SmOrgan> smOrganList;
    private Map<String, ImBill> imBills;
    private Map<String, ImBill> imBillsId;
    private Map<String, AmsParam> amsParam;
    private Map<String, List<AmsParam>> amsParamOne;
    private Map<String, Map<String, List<AmsParam>>> amsParamTwo;
    private Map<String, Map<String, AmsParam>> amsParamThree;

    public Map<String, AmsParam> getAmsParam() {
        return amsParam;
    }

    public void setAmsParam(Map<String, AmsParam> amsParam) {
        this.amsParam = amsParam;
    }

    public Map<String, SmParam> getSmParam() {
        return smParam;
    }

    public void setSmParam(Map<String, SmParam> smParam) {
        this.smParam = smParam;
    }

    public Map<String, ImAccessSystem> getAccessSystem() {
        return accessSystem;
    }

    public void setAccessSystem(Map<String, ImAccessSystem> accessSystem) {
        this.accessSystem = accessSystem;
    }


    public List<SmOrgan> getSmOrganList() {
        return smOrganList;
    }

    public void setSmOrganList(List<SmOrgan> smOrganList) {
        this.smOrganList = smOrganList;
    }

    public Map<String, SmOrgan> getSmOrgan() {
        return smOrgan;
    }

    public void setSmOrgan(Map<String, SmOrgan> smOrgan) {
        this.smOrgan = smOrgan;
    }

    public Map<String, List<AmsParam>> getAmsParamOne() {
        return amsParamOne;
    }

    public void setAmsParamOne(Map<String, List<AmsParam>> amsParamOne) {
        this.amsParamOne = amsParamOne;
    }

    public Map<String, Map<String, List<AmsParam>>> getAmsParamTwo() {
        return amsParamTwo;
    }

    public void setAmsParamTwo(Map<String, Map<String, List<AmsParam>>> amsParamTwo) {
        this.amsParamTwo = amsParamTwo;
    }

    public Map<String, Map<String, AmsParam>> getAmsParamThree() {
        return amsParamThree;
    }

    public void setAmsParamThree(Map<String, Map<String, AmsParam>> amsParamThree) {
        this.amsParamThree = amsParamThree;
    }

    /**
     * 新增接入系统
     *
     * @param imAccessSystem
     */
    public void addAccessSystem(ImAccessSystem imAccessSystem) {
        getAccessSystem().put(imAccessSystem.getCode(), imAccessSystem);
    }

    /**
     * 修改接入系统
     *
     * @param oldImAccessSystem 修改前对象
     * @param newImAccessSystem 修改后对象
     */
    public void updateAccessSystem(ImAccessSystem oldImAccessSystem,
                                   ImAccessSystem newImAccessSystem) {
        removeAccessSystem(oldImAccessSystem);
        addAccessSystem(newImAccessSystem);
    }

    public Map<String, ImBill> getImBills() {
        return imBills;
    }

    public void setImBills(Map<String, ImBill> imBills) {
        this.imBills = imBills;
    }

    public Map<String, ImBill> getImBillsId() {
        return imBillsId;
    }

    public void setImBillsId(Map<String, ImBill> imBillsId) {
        this.imBillsId = imBillsId;
    }

    /**
     * 删除接入系统
     *
     * @param
     */
    public void removeAccessSystem(ImAccessSystem imAccessSystem) {
        getAccessSystem().remove(imAccessSystem.getCode());
    }

    /**
     * 新增著录参数
     */
    public void addAmsParam(AmsParam amsParam) {
        getAmsParam().put(amsParam.getId(), amsParam);
    }

    /**
     * 新增参数
     *
     * @param
     */
    public void addSmParam(SmParam smParam) {
        getSmParam().put(smParam.getCode(), smParam);
    }

    /**
     * 修改著录参数
     */
    public void updateAmsParam(AmsParam oldAmsParam, AmsParam newAmsParam) {
        removeAmsParam(oldAmsParam);
        addAmsParam(newAmsParam);
    }

    /**
     * 修改参数
     *
     * @param
     * @param
     */
    public void updateSmParam(SmParam oldSmParam, SmParam newSmParam) {
        removeSmParam(oldSmParam);
        addSmParam(newSmParam);
    }

    /**
     * 删除著录参数
     */
    public void removeAmsParam(AmsParam amsParam) {
        getAmsParam().remove(amsParam.getId());
    }

    /**
     * 删除参数
     *
     * @param
     */
    public void removeSmParam(SmParam smParam) {
        getAccessSystem().remove(smParam.getCode());
    }
}
