package net.northking.iacmp.imp.domain;


import lombok.Data;

/**
 * 用户权限菜单关联表 sm_role_menu
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class SmRoleMenu {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 主键
     */
    private Long roleMenuId;


}
