package net.northking.iacmp.cms.web.controller.system;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.cms.service.ICmsRoleService;
import net.northking.iacmp.cms.service.ICmsUserRoleService;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.domain.cms.CmsUserRole;
import net.northking.iacmp.constant.RoleConstants;
import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.domain.SysUserRole;
import net.northking.iacmp.system.domain.SysUserRoleVO;
import net.northking.iacmp.system.service.ISysRoleService;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息
 *
 * @author wxy
 */
@Controller
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    private String prefix = "system/role";

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ICmsRoleService cmsRoleService;

    @Autowired
    private ICmsUserRoleService cmsUserRoleService;

    @RequiresPermissions("system:role:view")
    @GetMapping()
    public String role() {
        return prefix + "/role";
    }

    @RequiresPermissions("system:role:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysRole role) {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:role:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String roleIds) {
        List<SysRole> roleList = new ArrayList<>();
        if (roleIds != null && !"".equals(roleIds)) {
            String[] ids = roleIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.parseLong(ids[i]);
                SysRole role = roleService.selectRoleById(id);
                roleList.add(role);
            }
        } else {
            SysRole role = new SysRole();
            List<SysRole> roles = roleService.selectRoleList(role);
            for (SysRole sysRole1 : roles) {
                roleList.add(sysRole1);
            }
        }
        ExcelUtil<SysRole> util = new ExcelUtil<SysRole>(SysRole.class);
        return util.exportExcel(roleList, "用户数据");
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存角色
     */
    @RequiresPermissions("system:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addSave(@Validated SysRole role) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return error("新增角色'" + role.getRoleName() + " 失败，角色名称已存在");
        } else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return error("新增角色'" + role.getRoleName() + " 失败，角色权限已存在");
        }
        role.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        int i = roleService.insertRole(role);
        SysRole sysRole = roleService.selectRoleByRoleKey(role.getRoleKey());
        if (i > 0) {
            CmsRole cmsRole = new CmsRole();
            cmsRole.setId(sysRole.getRoleId());
            cmsRole.setRoleKey(role.getRoleKey());
            cmsRole.setRoleName(role.getRoleName());
            cmsRole.setRoleSort(Integer.valueOf(role.getRoleSort()));
            cmsRole.setStatus(role.getStatus());
            cmsRole.setRemark(role.getRemark());
            return toAjax(cmsRoleService.insertCmsRole(cmsRole));
        }
        return toAjax(i);
    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/edit";
    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult editSave(@Validated SysRole role) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return error("修改角色'" + role.getRoleName() + " 失败，角色名称已存在");
        } else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return error("修改角色'" + role.getRoleName() + " 失败，角色权限已存在");
        }
        role.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        int i = roleService.updateRole(role);
        if (i > 0) {
            CmsRole cmsRole = new CmsRole();
            cmsRole.setId(role.getRoleId());
            cmsRole.setRoleKey(role.getRoleKey());
            cmsRole.setRoleName(role.getRoleName());
            cmsRole.setRoleSort(Integer.valueOf(role.getRoleSort()));
            cmsRole.setStatus(role.getStatus());
            cmsRole.setRemark(role.getRemark());
            return toAjax(cmsRoleService.updateCmsRole(cmsRole));
        }
        return toAjax(i);
    }

    /**
     * 角色分配数据权限
     */
    @GetMapping("/authDataScope/{roleId}")
    public String authDataScope(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/dataScope";
    }

    /**
     * 保存角色分配数据权限
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/authDataScope")
    @ResponseBody
    public AjaxResult authDataScopeSave(SysRole role) {
        role.setUpdateBy(ShiroUtils.getLoginName());
        if (roleService.authDataScope(role) > 0) {
            ShiroUtils.setSysUser(userService.selectUserById(ShiroUtils.getSysUser().getUserId()));
            return success();
        }
        return error();
    }

    @RequiresPermissions("system:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult remove(String ids) {
        try {
            int i = roleService.deleteRoleByIds(ids);
            if (i > 0) {
                return toAjax(cmsRoleService.deleteCmsRoleByIds(ids));
            }
            return toAjax(i);
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验角色名称
     */
    @PostMapping("/checkRoleNameUnique")
    @ResponseBody
    public String checkRoleNameUnique(SysRole role) {
        return roleService.checkRoleNameUnique(role);
    }

    /**
     * 校验角色权限
     */
    @PostMapping("/checkRoleKeyUnique")
    @ResponseBody
    public String checkRoleKeyUnique(SysRole role) {
        return roleService.checkRoleKeyUnique(role);
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree() {
        return prefix + "/tree";
    }

    /**
     * 角色状态修改
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult changeStatus(SysRole role) {
        int i = roleService.changeStatus(role);
        if (i > 0) {
            CmsRole cmsRole = new CmsRole();
            cmsRole.setId(role.getRoleId());
            cmsRole.setStatus(role.getStatus());
            return toAjax(cmsRoleService.changeStatus(cmsRole));
        }
        return toAjax(i);
    }

    /**
     * 分配用户
     */
    @RequiresPermissions("system:role:edit")
    @GetMapping("/authUser/{roleId}")
    public String authUser(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/authUser";
    }

    /**
     * 查询已分配用户角色列表
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/authUser/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }

    /**
     * 取消授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult cancelAuthUser(SysUserRole userRole) {
        int i = roleService.deleteAuthUser(userRole);
        if (i > 0) {
            CmsUserRole cmsUserRole = new CmsUserRole();
            cmsUserRole.setRoleId(userRole.getRoleId());
            cmsUserRole.setUserId(userRole.getUserId());
            cmsUserRole.setId(userRole.getUrId());
            cmsUserRoleService.deleteAuthUser(cmsUserRole);
        }
        return toAjax(i);
    }

    /**
     * 批量取消授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancelAll")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult cancelAuthUserAll(Long roleId, String userIds) {
        int i = roleService.deleteAuthUsers(roleId, userIds);
        if (i > 0) {
            cmsUserRoleService.deleteAuthUsers(roleId, userIds);
        }
        return toAjax(i);
    }

    /**
     * 选择用户
     */
    @GetMapping("/authUser/selectUser/{roleId}")
    public String selectUser(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/selectUser";
    }

    /**
     * 查询未分配用户角色列表
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/authUser/unallocatedList")
    @ResponseBody
    public TableDataInfo unallocatedList(SysUser user) {
        user.setUserName(user.getLoginName());
        startPage();
        List<SysUser> list = userService.selectUnallocatedList(user);
        return getDataTable(list);
    }

    /**
     * 批量选择用户授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/selectAll")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult selectAuthUserAll(Long roleId, String userIds) {
        int i = roleService.insertAuthUsers(roleId, userIds);
        if (i > 0) {
            return toAjax(cmsUserRoleService.insertAuthUsers(roleId, userIds));
        }
        return toAjax(i);
    }

    /**
     * 批量导入用户授权
     *
     * @return
     */
    @PostMapping("/importUserExcel")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importUserExcel(MultipartFile file) throws IOException {
        try {
            ExcelUtil<SysUserRoleVO> util = new ExcelUtil<>(SysUserRoleVO.class);
            List<SysUserRoleVO> userList = util.importExcel(file.getInputStream());

            if (userList == null || userList.isEmpty()) {
                throw new BusinessException("您输入的信息有误，无法读取Excel信息");
            }
            List<SysUserRole> sysUserRoles = userService.selectUserRoleByUserInfo(userList);

            //取消普通用户权限
            SysRole sysRole = roleService.selectRoleByRoleKey(RoleConstants.CMS_ROLE_COMMON);
            userService.deleteUserRoles(sysRole.getRoleId(), sysUserRoles);
            List<CmsUserRole> cmsUserRoles = new ArrayList<>();
            sysUserRoles.stream().forEach(sysUserRole -> {
                CmsUserRole cmsUserRole = new CmsUserRole();
                cmsUserRole.setId(sysUserRole.getUrId());
                cmsUserRole.setUserId(sysUserRole.getUserId());
                cmsUserRole.setRoleId(sysUserRole.getRoleId());
                cmsUserRoles.add(cmsUserRole);
            });
            cmsRoleService.deleteUserRoles(sysRole.getRoleId(), cmsUserRoles);
            cmsRoleService.importUserRole(cmsUserRoles);
            return success(userService.importUserRole(sysUserRoles));

        } catch (Exception e) {
            logger.error("批量导入用户授权失败：", e);
            throw new BusinessException("批量导入用户授权失败，系统正忙");
        }
    }

    /**
     * 导出模板
     **/
    @GetMapping("/authUser/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<SysUserRoleVO> util = new ExcelUtil<SysUserRoleVO>(SysUserRoleVO.class);
        return util.importTemplateExcel("用户角色模板");
    }
}