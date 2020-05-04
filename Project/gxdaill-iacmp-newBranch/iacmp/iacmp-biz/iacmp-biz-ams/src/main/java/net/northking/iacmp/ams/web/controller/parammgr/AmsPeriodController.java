package net.northking.iacmp.ams.web.controller.parammgr;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsPeriod;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.server.service.IAmsPeriodService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 档案期限 信息操作处理
 *
 * @author wxy
 * @date 2019-08-13
 */
@Controller
@RequestMapping("/param/amsPeriod")
public class AmsPeriodController extends BaseController {
    private String prefix = "param/amsPeriod";

    @Autowired
    private IAmsPeriodService amsPeriodService;

    @RequiresPermissions("param:amsPeriod:view")
    @GetMapping()
    public String amsPeriod() {
        return prefix + "/amsPeriod";
    }

    /**
     * 查询档案期限列表
     */
    @RequiresPermissions("param:amsPeriod:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsPeriod amsPeriod) {
        startPage();
        List<AmsPeriod> list = amsPeriodService.selectAmsPeriodList(amsPeriod);
        return getDataTable(list);
    }


    /**
     * 导出档案期限列表
     */
    @RequiresPermissions("param:amsPeriod:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsPeriod amsPeriod) {
        List<AmsPeriod> list = amsPeriodService.selectAmsPeriodList(amsPeriod);
        ExcelUtil<AmsPeriod> util = new ExcelUtil<AmsPeriod>(AmsPeriod.class);
        return util.exportExcel(list, "amsPeriod");
    }

    /**
     * 新增档案期限
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案期限
     */
    @RequiresPermissions("param:amsPeriod:add")
    @Log(title = "档案期限", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsPeriod amsPeriod) {
        return toAjax(amsPeriodService.insertAmsPeriod(amsPeriod));
    }

    /**
     * 修改档案期限
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsPeriod amsPeriod = amsPeriodService.selectAmsPeriodById(id);
        mmap.put("amsPeriod", amsPeriod);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案期限
     */
    @RequiresPermissions("param:amsPeriod:edit")
    @Log(title = "档案期限", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsPeriod amsPeriod) {
        return toAjax(amsPeriodService.updateAmsPeriod(amsPeriod));
    }

    /**
     * 删除档案期限
     */
    @RequiresPermissions("param:amsPeriod:remove")
    @Log(title = "档案期限", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsPeriodService.deleteAmsPeriodByIds(ids));
    }

    /**
     * 修改档案期限
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") String id, ModelMap mmap) {
        AmsPeriod amsPeriod = amsPeriodService.selectAmsPeriodById(id);
        mmap.put("amsPeriod", amsPeriod);
        return prefix + "/view";
    }

}
