package net.northking.iacmp.cms.web.controller.bill;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.cms.service.ICmsRoleService;
import net.northking.iacmp.cms.service.ICmsUserRoleService;
import net.northking.iacmp.cms.service.IPmsBatchService;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.domain.cms.CmsUserRole;
import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.system.domain.CmsUserRoleVO;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.domain.SysUserRole;
import net.northking.iacmp.system.domain.SysUserRoleVO;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类角色 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-08-28
 */
@Controller
@RequestMapping("/cms/cmsRole")
public class CmsRoleController extends BaseController {
    private String prefix = "bill/cmsRole";

    @Autowired
    private ICmsRoleService cmsRoleService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ICmsUserRoleService cmsUserRoleService;

    @Autowired
    private IPmsBatchService pmsBatchService;


    @RequiresPermissions("cms:cmsRole:view")
    @GetMapping()
    public String cmsRole(ModelMap mmap) {
        return prefix + "/cmsRole";
    }

    /**
     * 查询分类角色列表
     */
    @RequiresPermissions("cms:cmsRole:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsRole cmsRole) {
        startPage();
        List<CmsRole> list = cmsRoleService.selectCmsRoleList(cmsRole);
        return getDataTable(list);
    }


    /**
     * 导出分类角色列表
     */
    @RequiresPermissions("cms:cmsRole:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String roleIds) {
        List<CmsRole> roleList = new ArrayList<>();
        if (roleIds != null && !"".equals(roleIds)) {
            String[] ids = roleIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.parseLong(ids[i]);
                CmsRole role = cmsRoleService.selectCmsRoleById(id);
                roleList.add(role);
            }
        } else {
            CmsRole role = new CmsRole();
            List<CmsRole> roles = cmsRoleService.selectCmsRoleList(role);
            for (CmsRole sysRole1 : roles) {
                roleList.add(sysRole1);
            }
        }
        ExcelUtil<CmsRole> util = new ExcelUtil<CmsRole>(CmsRole.class);
        return util.exportExcel(roleList, "用户数据");
    }

    /**
     * 新增分类角色
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存分类角色
     */
    @RequiresPermissions("cms:cmsRole:add")
    @Log(title = "分类角色", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsRole role) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(cmsRoleService.checkRoleNameUnique(role))) {
            return error("新增角色'" + role.getRoleName() + " 失败，角色名称已存在");
        } else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(cmsRoleService.checkRoleKeyUnique(role))) {
            return error("新增角色'" + role.getRoleName() + " 失败，角色权限已存在");
        }
        role.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(cmsRoleService.insertCmsRole(role));
    }

    /**
     * 校验角色名称
     */
    @PostMapping("/checkRoleNameUnique")
    @ResponseBody
    public String checkRoleNameUnique(CmsRole role) {
        return cmsRoleService.checkRoleNameUnique(role);
    }

    /**
     * 校验角色权限
     */
    @PostMapping("/checkRoleKeyUnique")
    @ResponseBody
    public String checkRoleKeyUnique(CmsRole role) {
        return cmsRoleService.checkRoleKeyUnique(role);
    }

    /**
     * 修改分类角色
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CmsRole cmsRole = cmsRoleService.selectCmsRoleById(id);
        mmap.put("role", cmsRole);
        return prefix + "/edit";
    }

    /**
     * 分配用户
     */
    @RequiresPermissions("cms:cmsRole:edit")
    @GetMapping("/authUser/{id}")
    public String authUser(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("role", cmsRoleService.selectCmsRoleById(id));
        return prefix + "/authUser";
    }

    /**
     * 修改保存分类角色
     */
    @RequiresPermissions("cms:cmsRole:edit")
    @Log(title = "分类角色", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsRole role) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(cmsRoleService.checkRoleNameUnique(role))) {
            return error("修改角色'" + role.getRoleName() + " 失败，角色名称已存在");
        } else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(cmsRoleService.checkRoleKeyUnique(role))) {
            return error("修改角色'" + role.getRoleName() + " 失败，角色权限已存在");
        }
        role.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(cmsRoleService.updateCmsRole(role));
    }

    /**
     * 查询已分配用户角色列表
     */
    @RequiresPermissions("cms:cmsRole:list")
    @PostMapping("/authUser/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(SysUser user) {
        startPage();
        List<SysUser> list = cmsUserRoleService.selectAllocatedList(user);
        return getDataTable(list);
    }

    /**
     * 选择用户
     */
    @GetMapping("/authUser/selectUser/{id}")
    public String selectUser(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("role", cmsRoleService.selectCmsRoleById(id));
        return prefix + "/selectUser";
    }

    /**
     * 查询未分配用户角色列表
     */
    @RequiresPermissions("cms:cmsRole:list")
    @PostMapping("/authUser/unallocatedList")
    @ResponseBody
    public TableDataInfo unallocatedList(SysUser user) {
        startPage();
        List<SysUser> list = cmsUserRoleService.selectUnallocatedList(user);
        return getDataTable(list);
    }

    /**
     * 批量选择用户授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/selectAll")
    @ResponseBody
    public AjaxResult selectAuthUserAll(Long id, String userIds) {
        return toAjax(cmsRoleService.insertAuthUsers(id, userIds));
    }

    /**
     * 取消授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(CmsUserRole userRole) {
        return toAjax(cmsRoleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancelAll")
    @ResponseBody
    public AjaxResult cancelAuthUserAll(Long roleId, String userIds) {
        return toAjax(cmsRoleService.deleteAuthUsers(roleId, userIds));
    }

    @RequiresPermissions("cms:cmsRole:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(cmsRoleService.deleteCmsRoleByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @GetMapping("/authProDataScope/{id}")
    @Log(title = "分配数据权限", businessType = BusinessType.OTHER)
    public String authProDataScope(@PathVariable Long id, ModelMap mmap) {
        mmap.put("cmsRole", cmsRoleService.selectCmsRoleById(id));

        return prefix + "/dataScope";
    }

    @GetMapping("/roleProjectTreeData")
    @Log(title = "分配数据权限", businessType = BusinessType.OTHER)
    @ResponseBody
    public List<Ztree> roleProjectTreeData(CmsRole role) {
        List<Ztree> list = pmsBatchService.roleProjectTreeData(role);
        return list;
    }

    /**
     * 保存角色分配数据权限
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/authDataScope")
    @ResponseBody
    public AjaxResult authDataScopeSave(CmsRole role) {
        role.setUpdateBy(ShiroUtils.getLoginName());
        if (cmsRoleService.authDataScope(role) > 0) {
            ShiroUtils.setSysUser(userService.selectUserById(ShiroUtils.getSysUser().getUserId()));
            return success();
        }
        return error();
    }

    /**
     * 批量导入用户授权
     *
     * @return
     */
    @PostMapping("/importUserExcel")
    @ResponseBody
    public AjaxResult importUserExcel(MultipartFile file) throws IOException {
        try {
            ExcelUtil<CmsUserRoleVO> util = new ExcelUtil<>(CmsUserRoleVO.class);
            List<CmsUserRoleVO> userList = util.importExcel(file.getInputStream());

            if (userList == null || userList.isEmpty()) {
                throw new BusinessException("您输入的信息有误，无法读取Excel信息");
            }
            List<CmsUserRole> cmsUserRoles = cmsRoleService.selectUserRoleByUserInfo(userList);
            //取消批量导入用户
            cmsRoleService.deleteUserRoles(Long.valueOf("29"), cmsUserRoles);
            return success(cmsRoleService.importUserRole(cmsUserRoles));

        } catch (Exception e) {
            logger.error("批量导入用户授权失败：", e);
            throw new BusinessException("批量导入用户授权失败，服务器忙！");
        }
    }

    /**
     * 导出模板
     **/
    @GetMapping("/authUser/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<CmsUserRoleVO> util = new ExcelUtil<CmsUserRoleVO>(CmsUserRoleVO.class);
        return util.importTemplateExcel("用户角色模板");
    }
}
