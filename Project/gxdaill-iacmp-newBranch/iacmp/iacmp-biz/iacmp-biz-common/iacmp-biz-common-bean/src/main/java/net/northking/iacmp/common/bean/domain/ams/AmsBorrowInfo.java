package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.domain.BaseEntity;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 档案借阅历史表 ams_borrow_info
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsBorrowInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 档案编号
     */
    private String arcNo;
    /**
     * 档案题名
     */
    private String arcName;
    /**
     * 所属部门
     */
    private String opDepName;
    /**
     * 申请人
     */
    private String appOpName;
    /**
     * 借阅事由
     */
    private String appReason;
    /**
     * 申请时间
     */
    private Date appTime;
    private String appTimeStr;

    private String appTimeStart;
    private String appTimeEnd;

    /**
     * 借阅开始时间
     */
    private Date borStaTime;
    private String borStaTimeStr;
    /**
     * 借阅结束日期
     */
    private Date borEndTime;
    private String borEndTimeStr;
    /**
     * 实际归还日期
     */
    private Date actReturnTime;
    private String actReturnTimeStr;
    /**
     * 审批信息ID
     */
    private String exaAppInfoId;
    /**
     * 状态
     */
    private String status;
    /**
     * 所属部门编号
     */
    private String opDepNo;
    /**
     * 档案类型
     */
    private String arcType;
    /**
     * 利用效果
     */
    private String useEffect;
    /**
     * 实际归还说明
     */
    private String actReturnExp;
    /**
     * 利用目的
     */
    private String usePurpose;
    /**
     * 档案类型姓名
     */
    private String arcBillName;
    /**
     * 档案类型编码
     */
    private String arcBillCode;
    /**
     * 申请人编码
     */
    private String appOpCode;
    /**
     * 借阅类型
     */
    private String borType;
    /**
     * 著入Id
     */
    private String batchId;
    /**
     * 著入编号
     */
    private String batchNo;
    /**
     * 申请部门名称
     */
    private String appDepName;
    /**
     * 申请部门编码
     */
    private String appDepCode;
    /**
     * 借阅形式
     */
    private String borForm;
    /**
     * 复印份数
     */
    private Integer copyNum;
    /**
     * 借出份数
     */
    private Integer loanNum;
    /**
     * 对应档案信息表主键
     */
    private String archiveId;
    /**
     * 档案管理部门号
     */
    private String manaDepNo;
    /**
     * 档案管理部门名
     */
    private String manaDepName;
    /**
     * 是否逾期（1：是；0：否；）
     */
    private String overdue;
    /**
     * 归还操作时间
     */
    private Date returnOpTime;
    /**
     * 归还操作类型
     */
    private String returnOpType;
    /**
     * 原审批信息ID
     */
    private String oldExaAppInfoId;
    /**
     * 代理人号
     */
    private String agentCode;
    /**
     * 代理人名
     */
    private String agentName;
    /**
     * 借阅天数
     */
    private String borDays;
    /**
     * 借阅附件
     */
    private String borAttachment;
    /**
     * 归还附件
     */
    private String returnAttachment;
    /**
     * 代理人部门代码
     */
    private String agentOrgCode;
    /**
     * 代理人机构名称
     */
    private String agentOrgName;
    /**
     * 档案状态
     */
    private String arcStatus;
    /**
     * 归还档案路径
     */
    private String returnPath;
    /**
     * 二级类目code
     */
    private String arcBillDeptCode;
    /**
     * 档案二级类目name
     */
    private String arcBillDeptName;
    /**
     * 责任者
     */
    private String respOpName;
    /**
     * 文件编号
     */
    private String fileNo;
    /**
     * 档案密级
     */
    private String arcLevel;
    /**
     * 档案形成时间
     */
    private Date arcCreTime;
    /**
     * 二级类目
     */
    private String childArcType;
    /**
     * 出库备注
     */
    private String outRemark;
    /**
     * 借阅档案形态名称
     */
    private String borTypel;
    /**
     * 上传附件路径
     */
    private String path;
    /**
     * 上传文件名
     */
    private String fileName;

    /**
     * 保管期限
     */
    private String valPeriod;
    /**
     * 是否移交行档室
     */
    private String hasMoveBank;

    /**
     * 载体形式
     */
    private String carrier;

    /**
     * 归档部门
     */
    private String filingDepaName;
    /**
     * 主键
     */
    private Long infoId;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOpDepName() {
        return opDepName;
    }

    public void setOpDepName(String opDepName) {
        this.opDepName = opDepName;
    }

    public String getAppOpName() {
        return appOpName;
    }

    public void setAppOpName(String appOpName) {
        this.appOpName = appOpName;
    }

    public String getAppReason() {
        return appReason;
    }

    public void setAppReason(String appReason) {
        this.appReason = appReason;
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
        if (appTime != null) {
            String time = sdf.format(appTime);
            setAppTimeStr(time);
        }
    }

    public void setAppTimeStr(String ppTimeStr) {
        this.appTimeStr = appTimeStr;
    }

    public String getAppTimeStr() {
        return appTimeStr;
    }

    public String getAppTimeStart() {
        return appTimeStart;
    }

    public void setAppTimeStart(String appTimeStart) {
        this.appTimeStart = appTimeStart;
    }

    public String getAppTimeEnd() {
        return appTimeEnd;
    }

    public void setAppTimeEnd(String appTimeEnd) {
        this.appTimeEnd = appTimeEnd;
    }

    public void setBorStaTime(Date borStaTime) {
        this.borStaTime = borStaTime;
        if (borStaTime != null) {
            String time = sdf.format(borStaTime);
            setBorStaTimeStr(time);
        }
    }

    public Date getBorStaTime() {
        return borStaTime;
    }

    public String getBorStaTimeStr() {
        return borStaTimeStr;
    }

    public void setBorStaTimeStr(String borStaTimeStr) {
        this.borStaTimeStr = borStaTimeStr;
    }

    public void setBorEndTime(Date borEndTime) {
        this.borEndTime = borEndTime;
        if (borEndTime != null) {
            String time = sdf.format(borEndTime);
            setBorEndTimeStr(time);
        }
    }

    public Date getBorEndTime() {
        return borEndTime;
    }

    public String getBorEndTimeStr() {
        return borEndTimeStr;
    }

    public void setBorEndTimeStr(String borEndTimeStr) {
        this.borEndTimeStr = borEndTimeStr;
    }

    public Date getActReturnTime() {
        return actReturnTime;
    }

    public void setActReturnTime(Date actReturnTime) {
        this.actReturnTime = actReturnTime;
    }

    public String getActReturnTimeStr() {
        return actReturnTimeStr;
    }

    public void setActReturnTimeStr(String actReturnTimeStr) {
        this.actReturnTimeStr = actReturnTimeStr;
    }

    public String getExaAppInfoId() {
        return exaAppInfoId;
    }

    public void setExaAppInfoId(String exaAppInfoId) {
        this.exaAppInfoId = exaAppInfoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpDepNo() {
        return opDepNo;
    }

    public void setOpDepNo(String opDepNo) {
        this.opDepNo = opDepNo;
    }

    public String getArcType() {
        return arcType;
    }

    public void setArcType(String arcType) {
        this.arcType = arcType;
    }

    public String getUseEffect() {
        return useEffect;
    }

    public void setUseEffect(String useEffect) {
        this.useEffect = useEffect;
    }

    public String getActReturnExp() {
        return actReturnExp;
    }

    public void setActReturnExp(String actReturnExp) {
        this.actReturnExp = actReturnExp;
    }

    public String getUsePurpose() {
        return usePurpose;
    }

    public void setUsePurpose(String usePurpose) {
        this.usePurpose = usePurpose;
    }

    public String getArcBillName() {
        return arcBillName;
    }

    public void setArcBillName(String arcBillName) {
        this.arcBillName = arcBillName;
    }

    public String getArcBillCode() {
        return arcBillCode;
    }

    public void setArcBillCode(String arcBillCode) {
        this.arcBillCode = arcBillCode;
    }

    public String getAppOpCode() {
        return appOpCode;
    }

    public void setAppOpCode(String appOpCode) {
        this.appOpCode = appOpCode;
    }

    public String getBorType() {
        return borType;
    }

    public void setBorType(String borType) {
        this.borType = borType;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getAppDepName() {
        return appDepName;
    }

    public void setAppDepName(String appDepName) {
        this.appDepName = appDepName;
    }

    public String getAppDepCode() {
        return appDepCode;
    }

    public void setAppDepCode(String appDepCode) {
        this.appDepCode = appDepCode;
    }

    public String getBorForm() {
        return borForm;
    }

    public void setBorForm(String borForm) {
        this.borForm = borForm;
    }

    public Integer getCopyNum() {
        return copyNum;
    }

    public void setCopyNum(Integer copyNum) {
        this.copyNum = copyNum;
    }

    public Integer getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(Integer loanNum) {
        this.loanNum = loanNum;
    }

    public String getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
    }

    public String getManaDepNo() {
        return manaDepNo;
    }

    public void setManaDepNo(String manaDepNo) {
        this.manaDepNo = manaDepNo;
    }

    public String getManaDepName() {
        return manaDepName;
    }

    public void setManaDepName(String manaDepName) {
        this.manaDepName = manaDepName;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }

    public Date getReturnOpTime() {
        return returnOpTime;
    }

    public void setReturnOpTime(Date returnOpTime) {
        this.returnOpTime = returnOpTime;
    }

    public String getReturnOpType() {
        return returnOpType;
    }

    public void setReturnOpType(String returnOpType) {
        this.returnOpType = returnOpType;
    }

    public String getOldExaAppInfoId() {
        return oldExaAppInfoId;
    }

    public void setOldExaAppInfoId(String oldExaAppInfoId) {
        this.oldExaAppInfoId = oldExaAppInfoId;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getBorDays() {
        return borDays;
    }

    public void setBorDays(String borDays) {
        this.borDays = borDays;
    }

    public String getBorAttachment() {
        return borAttachment;
    }

    public void setBorAttachment(String borAttachment) {
        this.borAttachment = borAttachment;
    }

    public String getReturnAttachment() {
        return returnAttachment;
    }

    public void setReturnAttachment(String returnAttachment) {
        this.returnAttachment = returnAttachment;
    }

    public String getAgentOrgCode() {
        return agentOrgCode;
    }

    public void setAgentOrgCode(String agentOrgCode) {
        this.agentOrgCode = agentOrgCode;
    }

    public String getAgentOrgName() {
        return agentOrgName;
    }

    public void setAgentOrgName(String agentOrgName) {
        this.agentOrgName = agentOrgName;
    }

    public String getArcStatus() {
        return arcStatus;
    }

    public void setArcStatus(String arcStatus) {
        this.arcStatus = arcStatus;
    }

    public String getReturnPath() {
        return returnPath;
    }

    public void setReturnPath(String returnPath) {
        this.returnPath = returnPath;
    }

    public String getArcBillDeptCode() {
        return arcBillDeptCode;
    }

    public void setArcBillDeptCode(String arcBillDeptCode) {
        this.arcBillDeptCode = arcBillDeptCode;
    }

    public String getArcBillDeptName() {
        return arcBillDeptName;
    }

    public void setArcBillDeptName(String arcBillDeptName) {
        this.arcBillDeptName = arcBillDeptName;
    }

    public String getRespOpName() {
        return respOpName;
    }

    public void setRespOpName(String respOpName) {
        this.respOpName = respOpName;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getArcLevel() {
        return arcLevel;
    }

    public void setArcLevel(String arcLevel) {
        this.arcLevel = arcLevel;
    }

    public Date getArcCreTime() {
        return arcCreTime;
    }

    public void setArcCreTime(Date arcCreTime) {
        this.arcCreTime = arcCreTime;
    }

    public String getChildArcType() {
        return childArcType;
    }

    public void setChildArcType(String childArcType) {
        this.childArcType = childArcType;
    }

    public String getOutRemark() {
        return outRemark;
    }

    public void setOutRemark(String outRemark) {
        this.outRemark = outRemark;
    }

    public String getBorTypel() {
        return borTypel;
    }

    public void setBorTypel(String borTypel) {
        this.borTypel = borTypel;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFilingDepaName() {
        return filingDepaName;
    }

    public void setFilingDepaName(String filingDepaName) {
        this.filingDepaName = filingDepaName;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    @Override
    public String toString() {
        return "AmsBorrowInfo{" +
                "id='" + id + '\'' +
                ", arcNo='" + arcNo + '\'' +
                ", arcName='" + arcName + '\'' +
                ", opDepName='" + opDepName + '\'' +
                ", appOpName='" + appOpName + '\'' +
                ", appReason='" + appReason + '\'' +
                ", appTime=" + appTime +
                ", borStaTime=" + borStaTime +
                ", borStaTimeStr='" + borStaTimeStr + '\'' +
                ", borEndTime=" + borEndTime +
                ", borEndTimeStr='" + borEndTimeStr + '\'' +
                ", actReturnTime=" + actReturnTime +
                ", actReturnTimeStr='" + actReturnTimeStr + '\'' +
                ", exaAppInfoId='" + exaAppInfoId + '\'' +
                ", status='" + status + '\'' +
                ", opDepNo='" + opDepNo + '\'' +
                ", arcType='" + arcType + '\'' +
                ", useEffect='" + useEffect + '\'' +
                ", actReturnExp='" + actReturnExp + '\'' +
                ", usePurpose='" + usePurpose + '\'' +
                ", arcBillName='" + arcBillName + '\'' +
                ", arcBillCode='" + arcBillCode + '\'' +
                ", appOpCode='" + appOpCode + '\'' +
                ", borType='" + borType + '\'' +
                ", batchId='" + batchId + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", appDepName='" + appDepName + '\'' +
                ", appDepCode='" + appDepCode + '\'' +
                ", borForm='" + borForm + '\'' +
                ", copyNum=" + copyNum +
                ", loanNum=" + loanNum +
                ", archiveId='" + archiveId + '\'' +
                ", manaDepNo='" + manaDepNo + '\'' +
                ", manaDepName='" + manaDepName + '\'' +
                ", overdue='" + overdue + '\'' +
                ", returnOpTime=" + returnOpTime +
                ", returnOpType='" + returnOpType + '\'' +
                ", oldExaAppInfoId='" + oldExaAppInfoId + '\'' +
                ", agentCode='" + agentCode + '\'' +
                ", agentName='" + agentName + '\'' +
                ", borDays='" + borDays + '\'' +
                ", borAttachment='" + borAttachment + '\'' +
                ", returnAttachment='" + returnAttachment + '\'' +
                ", agentOrgCode='" + agentOrgCode + '\'' +
                ", agentOrgName='" + agentOrgName + '\'' +
                ", arcStatus='" + arcStatus + '\'' +
                ", returnPath='" + returnPath + '\'' +
                ", arcBillDeptCode='" + arcBillDeptCode + '\'' +
                ", arcBillDeptName='" + arcBillDeptName + '\'' +
                ", respOpName='" + respOpName + '\'' +
                ", fileNo='" + fileNo + '\'' +
                ", arcLevel='" + arcLevel + '\'' +
                ", arcCreTime=" + arcCreTime +
                ", childArcType='" + childArcType + '\'' +
                ", outRemark='" + outRemark + '\'' +
                ", borTypel='" + borTypel + '\'' +
                ", path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                ", carrier='" + carrier + '\'' +
                ", filingDepaName='" + filingDepaName + '\'' +
                ", infoId='" + infoId + '\'' +
                '}';
    }
}
