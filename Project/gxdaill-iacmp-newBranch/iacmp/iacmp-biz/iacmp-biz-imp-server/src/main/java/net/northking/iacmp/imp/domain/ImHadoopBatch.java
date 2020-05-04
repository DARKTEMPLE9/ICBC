package net.northking.iacmp.imp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 大数据批次表 im_hadoop_batch
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImHadoopBatch {
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
    private String operationCode;
    /**
     * 操作柜员编号
     */
    private String tellerNo;
    /**
     * 操作柜员姓名
     */
    private String tellerName;
    /**
     * 机构号
     */
    private String branchNo;
    /**
     * 机构名称
     */
    private String branchName;
    /**
     * 顺序码
     */
    private String serialNo;
    /**
     * 正本数量
     */
    private String rcvNum;
    /**
     * 副本数量
     */
    private String totalNum;
    /**
     * 套数
     */
    private String billPackages;
    /**
     * 系统标识
     */
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
    private Long hBatchId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createTime;

}
