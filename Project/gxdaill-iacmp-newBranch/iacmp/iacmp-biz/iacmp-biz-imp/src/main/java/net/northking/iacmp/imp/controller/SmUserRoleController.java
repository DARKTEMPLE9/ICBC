package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmUserRole;
import net.northking.iacmp.imp.service.ISmUserRoleService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 用户角色关联 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/smUserRole")
public class SmUserRoleController extends BaseController {
    private String prefix = "uip/smUserRole";

    @Autowired
    private ISmUserRoleService smUserRoleService;

    @RequiresPermissions("uip:smUserRole:view")
    @GetMapping()
    public String smUserRole() {
        return prefix + "/smUserRole";
    }

    /**
     * 查询用户角色关联列表
     */
    @RequiresPermissions("uip:smUserRole:list")
    @PostMapping("/list")

    public TableDataInfo list(SmUserRole smUserRole) {
        startPage();
        List<SmUserRole> list = smUserRoleService.selectSmUserRoleList(smUserRole);
        return getDataTable(list);
    }


    /**
     * 导出用户角色关联列表
     */
    @RequiresPermissions("uip:smUserRole:export")
    @PostMapping("/export")

    public AjaxResult export(SmUserRole smUserRole) {
        List<SmUserRole> list = smUserRoleService.selectSmUserRoleList(smUserRole);
        ExcelUtil<SmUserRole> util = new ExcelUtil<SmUserRole>(SmUserRole.class);
        return util.exportExcel(list, "smUserRole");
    }

    /**
     * 新增用户角色关联
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户角色关联
     */
    @RequiresPermissions("uip:smUserRole:add")
    @Log(title = "用户角色关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")

//    public AjaxResult addSave(SmUserRole smUserRole)
//	{
//		return toAjax(smUserRoleService.insertSmUserRole(smUserRole));
//	}

    /**
     * 修改用户角色关联
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") String userId, ModelMap mmap) {
        SmUserRole smUserRole = smUserRoleService.selectSmUserRoleById(userId);
        mmap.put("smUserRole", smUserRole);
        return prefix + "/edit";
    }

    /**
     * queryByRoleId
     */
    @PostMapping("/queryByRoleId")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer queryByRoleId(@RequestBody String roleId) {
        Integer result = 0;
        result = smUserRoleService.queryByRoleId(roleId);
        return result;
    }

    /**
     * selectByUserId
     */
    @PostMapping("/selectByUserId")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public String selectByUserId(@RequestBody String userId) {
        String result = smUserRoleService.selectByUserId(userId);
        return result;
    }

    /**
     * 分配用户角色
     */
    @PostMapping("/updateForRoles")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer updateForRoles(@RequestBody HashMap map) {
        SmUserRole smUserRole = smUserRoleService.selectSmUserRoleById((String) map.get("userId"));
        Integer result = 0;
        if (smUserRole != null) {
            result = smUserRoleService.updateForRoles(map);
        } else {
            result = smUserRoleService.insertSmUserRole(map);
        }
        return result;
    }

    /**
     * 删除用户角色关联
     */
    @RequiresPermissions("uip:smUserRole:remove")
    @Log(title = "用户角色关联", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(smUserRoleService.deleteSmUserRoleByIds(ids));
    }

}
