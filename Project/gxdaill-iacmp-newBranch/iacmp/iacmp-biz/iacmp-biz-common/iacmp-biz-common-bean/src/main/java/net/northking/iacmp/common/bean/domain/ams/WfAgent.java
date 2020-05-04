package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 流程代理表 wf_agent
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WfAgent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 委托人号
     */
    private String trustorCode;
    /**
     * 委托人名
     */
    private String trustorName;
    /**
     * 代理人号
     */
    private String agentCode;
    /**
     * 代理人名
     */
    private String agentName;
    /**
     * 代理人部门号
     */
    private String agentOrgCode;
    /**
     * 代理人部门名
     */
    private String agentOrgName;
    /**
     * 开始日期
     */
    private Date agentStartDate;
    /**
     * 结束日期
     */
    private Date agentEndDate;
    /**
     * 状态
     */
    private String agentStatus;

}