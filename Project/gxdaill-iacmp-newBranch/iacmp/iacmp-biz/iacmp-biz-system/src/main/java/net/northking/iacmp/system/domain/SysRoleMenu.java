package net.northking.iacmp.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author wxy
 */
public class SysRoleMenu {
    /**
     * 主键ID
     */
    private Long rmId;
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRmId() {
        return this.rmId;
    }

    public void setRmId(Long rmId) {
        this.rmId = rmId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rmId", getRmId())
                .append("roleId", getRoleId())
                .append("menuId", getMenuId())
                .toString();
    }
}
