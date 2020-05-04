package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 接入日志表 im_acs_log
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImAcsLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 批次主键
     */
    private String imBatchId;
    /**
     * 操作人id
     */
    private String operatorId;
    /**
     * 操作人机构
     */
    private String operatorOrgan;
    /**
     * 操作类型（0-增；1-删;2-改）
     */
    private String operateType;
    /**
     * 操作时间
     */
    private Date operateDate;
    /**
     * 操作内容
     */
    private String operateContext;
    /**
     * 系统标识
     */
    private String systemFlag;
    /**
     * 单据登记流水号
     */
    private String regbillglideno;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 主键
     */
    private Long acsId;

}
