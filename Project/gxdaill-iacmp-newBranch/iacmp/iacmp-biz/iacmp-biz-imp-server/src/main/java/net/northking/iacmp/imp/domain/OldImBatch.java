package net.northking.iacmp.imp.domain;

import net.northking.iacmp.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 影像流水表 im_batch
 *
 * @author wei.chen
 * @date 2019-10-22
 */
public class OldImBatch extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 批次id
     */
    private String id;
    /**
     * 单据登记流水号
     */
    private String regbillglideno;
    /**
     * 业务编号
     */
    private String operationcode;
    /**
     * 操作柜员编号
     */
    private String tellerno;
    /**
     * 操作柜员姓名
     */
    private String tellername;
    /**
     * 机构号
     */
    private String branchno;
    /**
     * 机构名称
     */
    private String branchname;
    /**
     * 顺序码
     */
    private String serialno;
    /**
     * 正本数量
     */
    private String rcvnum;
    /**
     * 副本数量
     */
    private String totalnum;
    /**
     * 套数
     */
    private String billpackages;
    /**
     * 系统标识
     */
    private String systemflag;
    /**
     * 识别标示(0-不识别;1-识别;2-已识别;)
     */
    private String ocrFlag;
    /**
     * 类型缺失登记
     */
    private String defectType;
    /**
     * 订单号
     */
    private String orderNum;
    /**
     * 小时
     */
    private Integer bHour;
    /**
     * 年份
     */
    private Integer bYear;
    /**
     * 月份
     */
    private Integer bMonth;
    /**
     * 日期
     */
    private Integer bDay;
    /**
     * 系统标识状态
     */
    private Integer sysFlagInt;
    /**
     * 用户管理标识
     */
    private Integer userAssciationFlag;
    /**
     * 主键
     */
    private Long batchId;
    /**
     *
     */
    private String userCode;
    /**
     *
     */
    private String userName;
    /**
     *
     */
    private String idCard;
    /**
     *
     */
    private String userId;
    /**
     *
     */
    private String transactionNo;
    /**
     *
     */
    private String cardType;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setRegbillglideno(String regbillglideno) {
        this.regbillglideno = regbillglideno;
    }

    public String getRegbillglideno() {
        return regbillglideno;
    }

    public void setOperationcode(String operationcode) {
        this.operationcode = operationcode;
    }

    public String getOperationcode() {
        return operationcode;
    }

    public void setTellerno(String tellerno) {
        this.tellerno = tellerno;
    }

    public String getTellerno() {
        return tellerno;
    }

    public void setTellername(String tellername) {
        this.tellername = tellername;
    }

    public String getTellername() {
        return tellername;
    }

    public void setBranchno(String branchno) {
        this.branchno = branchno;
    }

    public String getBranchno() {
        return branchno;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setRcvnum(String rcvnum) {
        this.rcvnum = rcvnum;
    }

    public String getRcvnum() {
        return rcvnum;
    }

    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }

    public String getTotalnum() {
        return totalnum;
    }

    public void setBillpackages(String billpackages) {
        this.billpackages = billpackages;
    }

    public String getBillpackages() {
        return billpackages;
    }

    public void setSystemflag(String systemflag) {
        this.systemflag = systemflag;
    }

    public String getSystemflag() {
        return systemflag;
    }

    public void setOcrFlag(String ocrFlag) {
        this.ocrFlag = ocrFlag;
    }

    public String getOcrFlag() {
        return ocrFlag;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public String getDefectType() {
        return defectType;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setBHour(Integer bHour) {
        this.bHour = bHour;
    }

    public Integer getBHour() {
        return bHour;
    }

    public void setBYear(Integer bYear) {
        this.bYear = bYear;
    }

    public Integer getBYear() {
        return bYear;
    }

    public void setBMonth(Integer bMonth) {
        this.bMonth = bMonth;
    }

    public Integer getBMonth() {
        return bMonth;
    }

    public void setBDay(Integer bDay) {
        this.bDay = bDay;
    }

    public Integer getBDay() {
        return bDay;
    }

    public void setSysFlagInt(Integer sysFlagInt) {
        this.sysFlagInt = sysFlagInt;
    }

    public Integer getSysFlagInt() {
        return sysFlagInt;
    }

    public void setUserAssciationFlag(Integer userAssciationFlag) {
        this.userAssciationFlag = userAssciationFlag;
    }

    public Integer getUserAssciationFlag() {
        return userAssciationFlag;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("regbillglideno", getRegbillglideno())
                .append("operationcode", getOperationcode())
                .append("tellerno", getTellerno())
                .append("tellername", getTellername())
                .append("branchno", getBranchno())
                .append("branchname", getBranchname())
                .append("serialno", getSerialno())
                .append("rcvnum", getRcvnum())
                .append("totalnum", getTotalnum())
                .append("billpackages", getBillpackages())
                .append("systemflag", getSystemflag())
                .append("createTime", getCreateTime())
                .append("ocrFlag", getOcrFlag())
                .append("defectType", getDefectType())
                .append("orderNum", getOrderNum())
                .append("bHour", getBHour())
                .append("bYear", getBYear())
                .append("bMonth", getBMonth())
                .append("bDay", getBDay())
                .append("sysFlagInt", getSysFlagInt())
                .append("userAssciationFlag", getUserAssciationFlag())
                .append("batchId", getBatchId())
                .append("userCode", getUserCode())
                .append("userName", getUserName())
                .append("idCard", getIdCard())
                .append("userId", getUserId())
                .append("transactionNo", getTransactionNo())
                .append("cardType", getCardType())
                .toString();
    }
}
