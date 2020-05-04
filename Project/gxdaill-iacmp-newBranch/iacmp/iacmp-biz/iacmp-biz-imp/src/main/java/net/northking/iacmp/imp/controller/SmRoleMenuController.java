package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmRoleMenu;
import net.northking.iacmp.imp.service.ISmRoleMenuService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * 用户权限菜单关联 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/smRoleMenu")
public class SmRoleMenuController extends BaseController {
    private String prefix = "uip/smRoleMenu";

    @Autowired
    private ISmRoleMenuService smRoleMenuService;

    @RequiresPermissions("uip:smRoleMenu:view")
    @GetMapping()
    public String smRoleMenu() {
        return prefix + "/smRoleMenu";
    }

    /**
     * 查询用户权限菜单关联列表
     */
    @RequiresPermissions("uip:smRoleMenu:list")
    @PostMapping("/list")

    public TableDataInfo list(SmRoleMenu smRoleMenu) {
        startPage();
        List<SmRoleMenu> list = smRoleMenuService.selectSmRoleMenuList(smRoleMenu);
        return getDataTable(list);
    }


    /**
     * 导出用户权限菜单关联列表
     */
    @RequiresPermissions("uip:smRoleMenu:export")
    @PostMapping("/export")

    public AjaxResult export(SmRoleMenu smRoleMenu) {
        List<SmRoleMenu> list = smRoleMenuService.selectSmRoleMenuList(smRoleMenu);
        ExcelUtil<SmRoleMenu> util = new ExcelUtil<SmRoleMenu>(SmRoleMenu.class);
        return util.exportExcel(list, "smRoleMenu");
    }

    /**
     * 新增用户权限菜单关联
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户权限菜单关联
     */
    @PostMapping("/addMenuWithRole")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer addMenuWithRole(@RequestBody HashMap map) {
        String id = null;
        String roleId = (String) map.get("roleId");
        List<String> list = (List<String>) map.get("menuArray");
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                id = UUID.randomUUID().toString().replaceAll("-", "");
                smRoleMenuService.insertSmRoleMenu(id, roleId, list.get(i));
            }
        }
        return 0;
    }

    /**
     * 修改用户权限菜单关联
     */
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") String menuId, ModelMap mmap) {
        SmRoleMenu smRoleMenu = smRoleMenuService.selectSmRoleMenuById(menuId);
        mmap.put("smRoleMenu", smRoleMenu);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户权限菜单关联
     */
    @RequiresPermissions("uip:smRoleMenu:edit")
    @Log(title = "用户权限菜单关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(SmRoleMenu smRoleMenu) {
        return toAjax(smRoleMenuService.updateSmRoleMenu(smRoleMenu));
    }

    /**
     * 删除用户权限菜单关联
     */
    @PostMapping("/deleteMenuByRole")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer deleteMenuByRole(@RequestBody String roleId) {
        return smRoleMenuService.deleteMenuByRole(roleId);
    }

}
