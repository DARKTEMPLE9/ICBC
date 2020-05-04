package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 审批表 ams_process_info
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsProcessInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long infoId;
    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 审批编号
     */
    private String exaAppId;
    /**
     * 著录编号
     */
    private String brachId;
    /**
     * 档案编号
     */
    private String arcNo;
    /**
     * 档案名称
     */
    private String arcName;
    /**
     * 审批类型
     */
    private String exaAppType;
    /**
     * 申请人号
     */
    private String appOpNo;
    /**
     * 申请人名
     */
    private String appOpName;
    /**
     * 申请人部门号
     */
    private String appOrgNo;
    /**
     * 申请人部门名称
     */
    private String appOrgName;
    /**
     * 申请时间
     */
    private Date appTime;
    /**
     * 申请开始时间
     */
    private Date appTimeStart;
    /**
     * 申请结束时间
     */
    private Date appTimeEnd;
    /**
     * 申请备注
     */
    private String appRemark;
    /**
     * 当前处理人号
     */
    private String nowDispOpNo;
    /**
     * 当前处理人名称
     */
    private String nowDispOpName;
    /**
     * 当前处理人部门号
     */
    private String nowDispOrgNo;
    /**
     * 当前处理人部门名称
     */
    private String nowDispOrgName;
    /**
     * 当前审批意见
     */
    private String nowDispOpnion;
    /**
     * 当前审批时间
     */
    private Date nowDispTime;
    /**
     * 上次处理人号
     */
    private String preDispOpNo;
    /**
     * 上次处理人名称
     */
    private String preDispOpName;
    /**
     * 上次处理人部门号
     */
    private String preDispOrgNo;
    /**
     * 上次处理人部门名称
     */
    private String preDispOrgName;
    /**
     * 上次审批意见
     */
    private String preDispOpnion;
    /**
     * 上次处理时间
     */
    private Date preDispTime;
    /**
     * 审批结束时间
     */
    private Date exaAppEndTime;
    /**
     * 审批状态(10-未提交;20-审判中;30-已审批;)
     */
    private String exaAppStatus;
    /**
     * 审批结果(10-同意;20-不同意;)
     */
    private String exaAppResult;
    /**
     * 是否退回（10-无;20-已退回;）
     */
    private String exaBack;
    /**
     * 流程ID
     */
    private String processId;
    /**
     * 当前流程节点ID
     */
    private String nowProcessId;
    /**
     * 汇总审批状态(1-不为空;0-为空;)
     */
    private String foSearch;
    /**
     * 档案形态（10-电子形态;20-实物形态）
     */
    private String arcFormat;
    /**
     * 代理人名
     */
    private String agentName;
    /**
     * 借阅ID
     */
    private String borrowId;
    /**
     * 是否移交行档室
     */
    private String hasMoveBank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExaAppId() {
        return exaAppId;
    }

    public void setExaAppId(String exaAppId) {
        this.exaAppId = exaAppId;
    }

    public String getBrachId() {
        return brachId;
    }

    public void setBrachId(String brachId) {
        this.brachId = brachId;
    }

    public String getArcNo() {
        return arcNo;
    }

    public void setArcNo(String arcNo) {
        this.arcNo = arcNo;
    }

    public String getArcName() {
        return arcName;
    }

    public void setArcName(String arcName) {
        this.arcName = arcName;
    }

    public String getExaAppType() {
        return exaAppType;
    }

    public void setExaAppType(String exaAppType) {
        this.exaAppType = exaAppType;
    }

    public String getAppOpNo() {
        return appOpNo;
    }

    public void setAppOpNo(String appOpNo) {
        this.appOpNo = appOpNo;
    }

    public String getAppOpName() {
        return appOpName;
    }

    public void setAppOpName(String appOpName) {
        this.appOpName = appOpName;
    }

    public String getAppOrgNo() {
        return appOrgNo;
    }

    public void setAppOrgNo(String appOrgNo) {
        this.appOrgNo = appOrgNo;
    }

    public String getAppOrgName() {
        return appOrgName;
    }

    public void setAppOrgName(String appOrgName) {
        this.appOrgName = appOrgName;
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
    }

    public String getAppRemark() {
        return appRemark;
    }

    public void setAppRemark(String appRemark) {
        this.appRemark = appRemark;
    }

    public String getNowDispOpNo() {
        return nowDispOpNo;
    }

    public void setNowDispOpNo(String nowDispOpNo) {
        this.nowDispOpNo = nowDispOpNo;
    }

    public String getNowDispOpName() {
        return nowDispOpName;
    }

    public void setNowDispOpName(String nowDispOpName) {
        this.nowDispOpName = nowDispOpName;
    }

    public String getNowDispOrgNo() {
        return nowDispOrgNo;
    }

    public void setNowDispOrgNo(String nowDispOrgNo) {
        this.nowDispOrgNo = nowDispOrgNo;
    }

    public String getNowDispOrgName() {
        return nowDispOrgName;
    }

    public void setNowDispOrgName(String nowDispOrgName) {
        this.nowDispOrgName = nowDispOrgName;
    }

    public String getNowDispOpnion() {
        return nowDispOpnion;
    }

    public void setNowDispOpnion(String nowDispOpnion) {
        this.nowDispOpnion = nowDispOpnion;
    }

    public Date getNowDispTime() {
        return nowDispTime;
    }

    public void setNowDispTime(Date nowDispTime) {
        this.nowDispTime = nowDispTime;
    }

    public String getPreDispOpNo() {
        return preDispOpNo;
    }

    public void setPreDispOpNo(String preDispOpNo) {
        this.preDispOpNo = preDispOpNo;
    }

    public String getPreDispOpName() {
        return preDispOpName;
    }

    public void setPreDispOpName(String preDispOpName) {
        this.preDispOpName = preDispOpName;
    }

    public String getPreDispOrgNo() {
        return preDispOrgNo;
    }

    public void setPreDispOrgNo(String preDispOrgNo) {
        this.preDispOrgNo = preDispOrgNo;
    }

    public String getPreDispOrgName() {
        return preDispOrgName;
    }

    public void setPreDispOrgName(String preDispOrgName) {
        this.preDispOrgName = preDispOrgName;
    }

    public String getPreDispOpnion() {
        return preDispOpnion;
    }

    public void setPreDispOpnion(String preDispOpnion) {
        this.preDispOpnion = preDispOpnion;
    }

    public Date getPreDispTime() {
        return preDispTime;
    }

    public void setPreDispTime(Date preDispTime) {
        this.preDispTime = preDispTime;
    }

    public Date getExaAppEndTime() {
        return exaAppEndTime;
    }

    public void setExaAppEndTime(Date exaAppEndTime) {
        this.exaAppEndTime = exaAppEndTime;
    }

    public String getExaAppStatus() {
        return exaAppStatus;
    }

    public void setExaAppStatus(String exaAppStatus) {
        this.exaAppStatus = exaAppStatus;
    }

    public String getExaAppResult() {
        return exaAppResult;
    }

    public void setExaAppResult(String exaAppResult) {
        this.exaAppResult = exaAppResult;
    }

    public String getExaBack() {
        return exaBack;
    }

    public void setExaBack(String exaBack) {
        this.exaBack = exaBack;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getNowProcessId() {
        return nowProcessId;
    }

    public void setNowProcessId(String nowProcessId) {
        this.nowProcessId = nowProcessId;
    }

    public String getFoSearch() {
        return foSearch;
    }

    public void setFoSearch(String foSearch) {
        this.foSearch = foSearch;
    }

    public String getArcFormat() {
        return arcFormat;
    }

    public void setArcFormat(String arcFormat) {
        this.arcFormat = arcFormat;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }


    @Override
    public String toString() {
        return "AmsProcessInfo{" +
                "id='" + id + '\'' +
                ", exaAppId='" + exaAppId + '\'' +
                ", brachId='" + brachId + '\'' +
                ", arcNo='" + arcNo + '\'' +
                ", arcName='" + arcName + '\'' +
                ", exaAppType='" + exaAppType + '\'' +
                ", appOpNo='" + appOpNo + '\'' +
                ", appOpName='" + appOpName + '\'' +
                ", appOrgNo='" + appOrgNo + '\'' +
                ", appOrgName='" + appOrgName + '\'' +
                ", appTime=" + appTime +
                ", appRemark='" + appRemark + '\'' +
                ", nowDispOpNo='" + nowDispOpNo + '\'' +
                ", nowDispOpName='" + nowDispOpName + '\'' +
                ", nowDispOrgNo='" + nowDispOrgNo + '\'' +
                ", nowDispOrgName='" + nowDispOrgName + '\'' +
                ", nowDispOpnion='" + nowDispOpnion + '\'' +
                ", nowDispTime=" + nowDispTime +
                ", preDispOpNo='" + preDispOpNo + '\'' +
                ", preDispOpName='" + preDispOpName + '\'' +
                ", preDispOrgNo='" + preDispOrgNo + '\'' +
                ", preDispOrgName='" + preDispOrgName + '\'' +
                ", preDispOpnion='" + preDispOpnion + '\'' +
                ", preDispTime=" + preDispTime +
                ", exaAppEndTime=" + exaAppEndTime +
                ", exaAppStatus='" + exaAppStatus + '\'' +
                ", exaAppResult='" + exaAppResult + '\'' +
                ", exaBack='" + exaBack + '\'' +
                ", processId='" + processId + '\'' +
                ", nowProcessId='" + nowProcessId + '\'' +
                ", foSearch='" + foSearch + '\'' +
                ", arcFormat='" + arcFormat + '\'' +
                ", agentName='" + agentName + '\'' +
                ", borrowId='" + borrowId + '\'' +
                '}';
    }
}
