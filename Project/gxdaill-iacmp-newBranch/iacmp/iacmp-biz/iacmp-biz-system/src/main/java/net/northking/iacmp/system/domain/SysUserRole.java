package net.northking.iacmp.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author wxy
 */
public class SysUserRole {
    /**
     * 主键ID
     */
    private Long urId;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUrId() {
        return this.urId;
    }

    public void setUrId(Long urId) {
        this.urId = urId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("urId", getUrId())
                .append("userId", getUserId())
                .append("roleId", getRoleId())
                .toString();
    }
}
