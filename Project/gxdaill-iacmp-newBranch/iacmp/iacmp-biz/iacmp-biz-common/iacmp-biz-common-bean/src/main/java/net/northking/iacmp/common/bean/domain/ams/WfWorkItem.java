package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 流程任务表 wf_work_item
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WfWorkItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 流程节点ID
     */
    private String processActId;
    /**
     * 流程ID
     */
    private String processInstId;
    /**
     * 代码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     */
    private BigDecimal status;
    /**
     * 判断值
     */
    private String judgeValue;
    /**
     * 备注
     */
    private String remarkValue;
    /**
     * 操作人ID
     */
    private String operId;
    /**
     * 操作人代码
     */
    private String operCode;
    /**
     * 操作人名称
     */
    private String operName;
    /**
     * 获取时间
     */
    private Date acquiredTime;
    /**
     * 完成时间
     */
    private Date finishedTime;
}
