package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 流程节点项参数表 wf_act_branch
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WfActBranch extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 结束流程节点ID
     */
    private String toProcActId;
    /**
     * 开始流程节点ID
     */
    private String fromProcActId;
    /**
     * 描述
     */
    private String description;
    /**
     * 字段
     */
    private String field;
    /**
     * 完成操作人
     */
    private BigDecimal compOperator;
    /**
     * 参数值
     */
    private String value;
    /**
     * 流程ID
     */
    private String processId;

}
