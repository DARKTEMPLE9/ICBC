package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 批次表 ceshi_im_batch
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
@Data
public class ImBatch {
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
    @JsonProperty("operationcode")
    private String operationCode;
    /**
     * 操作柜员编号
     */
    @JsonProperty("tellerno")
    private String tellerNo;
    /**
     * 操作柜员姓名
     */
    @JsonProperty("tellername")
    private String tellerName;
    /**
     * 机构号
     */
    @JsonProperty("branchno")
    private String branchNo;
    /**
     * 机构名称
     */
    @JsonProperty("branchname")
    private String branchName;
    /**
     * 顺序码
     */
    @JsonProperty("serialno")
    private String serialNo;
    /**
     * 正本数量
     */
    @JsonProperty("rcvnum")
    private String rcvNum;
    /**
     * 副本数量
     */
    @JsonProperty("totalnum")
    private String totalNum;
    /**
     * 套数
     */
    @JsonProperty("billpackages")
    private String billPackages;
    /**
     * 系统标识
     */
    @JsonProperty("systemflag")
    private String systemFlag;
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
    @JsonProperty("bhour")
    private Integer bHour;
    /**
     * 年份
     */
    @JsonProperty("byear")
    private Integer bYear;
    /**
     * 月份
     */
    @JsonProperty("bmonth")
    private Integer bMonth;
    /**
     * 日期
     */
    @JsonProperty("bday")
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
    @JsonIgnore
    private Long batchId;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 客户ID
     */
    private String userId;
    /**
     * 客户号
     */
    private String userCode;
    /**
     * 客户名称
     */
    private String userName;
    /**
     * 证件号码
     */
    private String idCard;
    /**
     * 证件类型
     */
    private String cardType;
}

