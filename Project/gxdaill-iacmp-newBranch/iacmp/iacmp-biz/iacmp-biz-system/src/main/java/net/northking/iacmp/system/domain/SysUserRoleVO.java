package net.northking.iacmp.system.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.annotation.Excels;
import net.northking.iacmp.core.domain.BaseEntity;

@Data
@Getter
@Setter
public class SysUserRoleVO extends BaseEntity {
    private static final long serialVersionUID = 1L;
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

    //    @Excels({
//            @Excel(name = "用户登录名（必输项）", targetAttr = "loginName",prompt = "请输入登录名，例如：用户“张三”输入登录名“zhangsan”")
//    })
    private SysUser sysUser;

    //    @Excels({
//            @Excel(name = "角色名称（必输项）", targetAttr = "roleName",prompt = "请输入角色名称，例如：科技项目经理")
//    })
    private SysRole sysRole;

    @Excel(name = "用户登录名（必输项）", prompt = "请输入登录名，例如：用户“张三”输入登录名“zhangsan”")
    private String loginName;
    @Excel(name = "角色名称（必输项）", prompt = "请输入角色名称，例如：科技项目经理")
    private String roleName;
}
