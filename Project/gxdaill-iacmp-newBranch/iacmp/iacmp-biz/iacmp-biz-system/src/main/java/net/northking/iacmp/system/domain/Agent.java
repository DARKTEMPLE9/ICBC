package net.northking.iacmp.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 操作代理表 ams_agent
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Agent extends BaseEntity {
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
     * 操作代理状态：1：代理中；2：代理结束
     */
    private String agentStatus;
    /**
     * 代理主键
     */
    private Long agentId;
}
