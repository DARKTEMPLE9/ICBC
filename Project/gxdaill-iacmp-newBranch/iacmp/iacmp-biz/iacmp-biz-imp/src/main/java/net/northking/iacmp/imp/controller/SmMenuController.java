package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmMenu;
import net.northking.iacmp.imp.service.ISmMenuService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单目录 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/smMenu")
public class SmMenuController extends BaseController {
    private String prefix = "uip/smMenu";

    @Autowired
    private ISmMenuService smMenuService;

    @RequiresPermissions("uip:smMenu:view")
    @GetMapping()
    public String smMenu() {
        return prefix + "/smMenu";
    }

    /**
     * 查询菜单目录列表
     */
    @PostMapping("/queryAllMenu")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<HashMap> queryAllMenu() {
        List<HashMap> list = smMenuService.queryAllMenu();
        return list;
    }

    @PostMapping("/queryTreeMenuByRole")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<HashMap> queryTreeMenuByRole(@RequestBody String roleId) {
        List<HashMap> list = smMenuService.queryTreeMenuByRole(roleId);
        return list;
    }

    /**
     * 导出菜单目录列表
     */
//    @RequiresPermissions("uip:smMenu:export")
//    @PostMapping("/export")
//
//    public AjaxResult export(SmMenu smMenu)
//    {
//    	List<SmMenu> list = smMenuService.selectSmMenuList(smMenu);
//        ExcelUtil<SmMenu> util = new ExcelUtil<SmMenu>(SmMenu.class);
//        return util.exportExcel(list, "smMenu");
//    }

    /**
     * 新增菜单目录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存菜单目录
     */
    @RequiresPermissions("uip:smMenu:add")
    @Log(title = "菜单目录", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(SmMenu smMenu) {
        return toAjax(smMenuService.insertSmMenu(smMenu));
    }

    /**
     * 修改菜单目录
     */
    @GetMapping("/edit/{cODE}")
    public String edit(@PathVariable("cODE") String cODE, ModelMap mmap) {
        SmMenu smMenu = smMenuService.selectSmMenuById(cODE);
        mmap.put("smMenu", smMenu);
        return prefix + "/edit";
    }

    /**
     * 修改保存菜单目录
     */
    @RequiresPermissions("uip:smMenu:edit")
    @Log(title = "菜单目录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(SmMenu smMenu) {
        return toAjax(smMenuService.updateSmMenu(smMenu));
    }

    /**
     * 删除菜单目录
     */
    @RequiresPermissions("uip:smMenu:remove")
    @Log(title = "菜单目录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(smMenuService.deleteSmMenuByIds(ids));
    }

}
