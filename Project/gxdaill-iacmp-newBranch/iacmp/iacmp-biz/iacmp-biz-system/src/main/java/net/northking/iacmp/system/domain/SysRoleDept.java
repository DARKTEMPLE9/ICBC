package net.northking.iacmp.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色和部门关联 sys_role_dept
 *
 * @author wxy
 */
public class SysRoleDept {
    /**
     * 主键ID
     */
    private Long rdId;
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getRdId() {
        return this.rdId;
    }

    public void setRdId(Long rdId) {
        this.rdId = rdId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rdId", getRdId())
                .append("roleId", getRoleId())
                .append("deptId", getDeptId())
                .toString();
    }
}
