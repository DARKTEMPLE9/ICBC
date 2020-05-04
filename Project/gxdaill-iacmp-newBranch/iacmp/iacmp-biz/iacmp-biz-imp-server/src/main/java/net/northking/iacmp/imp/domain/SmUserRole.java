package net.northking.iacmp.imp.domain;

import lombok.Data;

/**
 * 用户角色关联表 sm_user_role
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class SmUserRole {
    private static final long serialVersionUID = 1L;

    /**
     * 用户
     */
    private String userId;
    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 角色
     */
    private String roleId;
    /**
     * 主键
     */
    private Long userRoleId;

}
