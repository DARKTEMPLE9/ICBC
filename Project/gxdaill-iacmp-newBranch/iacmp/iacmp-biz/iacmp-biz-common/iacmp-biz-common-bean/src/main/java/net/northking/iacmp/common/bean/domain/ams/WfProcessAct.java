package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 流程表 wf_process_act
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WfProcessAct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 流程ID
     */
    private String processId;
    /**
     * 子流程ID
     */
    private String subProcessId;
    /**
     * 代码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否判断
     */
    private BigDecimal isJudgeState;
    /**
     * X坐标
     */
    private BigDecimal positonX;
    /**
     * Y坐标
     */
    private BigDecimal positionY;
    /**
     * 状态
     */
    private BigDecimal status;
    /**
     * 类型
     */
    private BigDecimal actType;

}
