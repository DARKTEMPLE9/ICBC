package net.northking.iacmp.ams.web.controller.parammgr;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsBillDept;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.server.service.IAmsBillDeptService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门档案配置 信息操作处理
 *
 * @author wxy
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/param/amsBillDept")
public class AmsBillDeptController extends BaseController {
    private String prefix = "param/amsBillDept";

    @Autowired
    private IAmsBillDeptService amsBillDeptService;

    @RequiresPermissions("param:amsBillDept:view")
    @GetMapping()
    public String amsBillDept() {
        return prefix + "/amsBillDept";
    }

    /**
     * 查询部门档案配置列表
     */
    @RequiresPermissions("param:amsBillDept:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBillDept amsBillDept) {
        startPage();
        List<AmsBillDept> list = amsBillDeptService.selectAmsBillDeptList(amsBillDept);
        return getDataTable(list);
    }


    /**
     * 导出部门档案配置列表
     */
    @RequiresPermissions("param:amsBillDept:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBillDept amsBillDept) {
        List<AmsBillDept> list = amsBillDeptService.selectAmsBillDeptList(amsBillDept);
        ExcelUtil<AmsBillDept> util = new ExcelUtil<>(AmsBillDept.class);
        return util.exportExcel(list, "amsBillDept");
    }

    /**
     * 新增部门档案配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存部门档案配置
     */
    @RequiresPermissions("param:amsBillDept:add")
    @Log(title = "部门档案配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBillDept amsBillDept) {
        return toAjax(amsBillDeptService.insertAmsBillDept(amsBillDept));
    }

    /**
     * 修改部门档案配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBillDept amsBillDept = amsBillDeptService.selectAmsBillDeptById(id);
        mmap.put("amsBillDept", amsBillDept);
        return prefix + "/edit";
    }

    /**
     * 修改保存部门档案配置
     */
    @RequiresPermissions("param:amsBillDept:edit")
    @Log(title = "部门档案配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBillDept amsBillDept) {
        return toAjax(amsBillDeptService.updateAmsBillDept(amsBillDept));
    }

    /**
     * 删除部门档案配置
     */
    @RequiresPermissions("param:amsBillDept:remove")
    @Log(title = "部门档案配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBillDeptService.deleteAmsBillDeptByIds(ids));
    }

}
