package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 审批意见表 ams_approve_info
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsApproveInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 审批信息ID
     */
    private String exaAppInfoId;
    /**
     * 审批编号
     */
    private String exaAppId;
    /**
     * 审批人编号
     */
    private String exaAppOpNo;
    /**
     * 审批人名称
     */
    private String exaAppOpName;
    /**
     * 审批人部门编号
     */
    private String exaAppOrgNo;
    /**
     * 审批人部门名称
     */
    private String exaAppOrgName;
    /**
     * 审批意见
     */
    private String exaAppOpnion;
    /**
     * 审批时间
     */
    private Date exaAppTime;
    /**
     * 审批结果(10-同意;20-不同意;)
     */
    private String exaAppResult;
    /**
     * 是否退回(10-提交;20-退回;30-废弃)
     */
    private String exaBack;
    /**
     * 流程ID
     */
    private String processId;
    /**
     * 当前流程节点ID
     */
    private String nowProcessId;
    /**
     * 代理人编号
     */
    private String agentOpNo;
    /**
     * 代理人名称
     */
    private String agentOpName;
    /**
     * 代理标志（0-未代理；1-已代理）
     */
    private String agentFlag;
    /**
     * 主键
     */
    private Long infoId;
}
