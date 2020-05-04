package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 影像批次表 im_batch
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImBatch extends BaseEntity {
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
    private String revNum;
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
    private String orderNo;
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
    private Long batchPk;
}
