package net.northking.iacmp.imp.domain;


import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户权限表 sm_role
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class SmRole {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 角色代码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色状态（0-正常；1-停用）
     */
    private BigDecimal status;
    /**
     * 角色类型（01-综合角色；02-特定角色；03=录入角色）
     */
    private String type;
    /**
     * 角色等级
     */
    private BigDecimal grad;
    /**
     * 备注(html编辑)
     */
    private String htmlContent;
    /**
     * 主键
     */
    private Long roleId;

}
