package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.SmUserOrgan;
import net.northking.iacmp.imp.service.ISmUserOrganService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户机构关联 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/smUserOrgan")
public class SmUserOrganController extends BaseController {
    private String prefix = "uip/smUserOrgan";

    @Autowired
    private ISmUserOrganService smUserOrganService;

    @RequiresPermissions("uip:smUserOrgan:view")
    @GetMapping()
    public String smUserOrgan() {
        return prefix + "/smUserOrgan";
    }

    /**
     * 查询用户机构关联列表
     */
    @RequiresPermissions("uip:smUserOrgan:list")
    @PostMapping("/list")

    public TableDataInfo list(SmUserOrgan smUserOrgan) {
        startPage();
        List<SmUserOrgan> list = smUserOrganService.selectSmUserOrganList(smUserOrgan);
        return getDataTable(list);
    }


    /**
     * 导出用户机构关联列表
     */
    @RequiresPermissions("uip:smUserOrgan:export")
    @PostMapping("/export")

    public AjaxResult export(SmUserOrgan smUserOrgan) {
        List<SmUserOrgan> list = smUserOrganService.selectSmUserOrganList(smUserOrgan);
        ExcelUtil<SmUserOrgan> util = new ExcelUtil<SmUserOrgan>(SmUserOrgan.class);
        return util.exportExcel(list, "smUserOrgan");
    }

    /**
     * 新增用户机构关联
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户机构关联
     */
    @RequiresPermissions("uip:smUserOrgan:add")
    @Log(title = "用户机构关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(SmUserOrgan smUserOrgan) {
        return toAjax(smUserOrganService.insertSmUserOrgan(smUserOrgan));
    }

    /**
     * 修改用户机构关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        SmUserOrgan smUserOrgan = smUserOrganService.selectSmUserOrganById(id);
        mmap.put("smUserOrgan", smUserOrgan);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户机构关联
     */
    @RequiresPermissions("uip:smUserOrgan:edit")
    @Log(title = "用户机构关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(SmUserOrgan smUserOrgan) {
        return toAjax(smUserOrganService.updateSmUserOrgan(smUserOrgan));
    }

    /**
     * 删除用户机构关联
     */
    @RequiresPermissions("uip:smUserOrgan:remove")
    @Log(title = "用户机构关联", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(smUserOrganService.deleteSmUserOrganByIds(ids));
    }

}
