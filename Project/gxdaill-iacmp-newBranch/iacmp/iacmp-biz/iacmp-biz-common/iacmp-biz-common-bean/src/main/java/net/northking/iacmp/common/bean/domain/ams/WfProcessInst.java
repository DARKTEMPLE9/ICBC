package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 流程节点项角色表 wf_process_inst
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WfProcessInst extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 流程ID
     */
    private String procDefId;
    /**
     * 流程代码
     */
    private String procCode;
    /**
     * 流程名称
     */
    private String procName;
    /**
     * 状态
     */
    private BigDecimal status;
    /**
     * 业务类型
     */
    private String busiType;
    /**
     * 创建人代码
     */
    private String creatorCode;
    /**
     * 创建人名称
     */
    private String creatorName;
    /**
     * 结束时间
     */
    private Date finishedTime;

}
