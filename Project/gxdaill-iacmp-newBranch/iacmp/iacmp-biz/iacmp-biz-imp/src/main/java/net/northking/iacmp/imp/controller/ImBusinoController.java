package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImBusino;
import net.northking.iacmp.imp.service.IImBusinoService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 异常流水 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imBusino")
public class ImBusinoController extends BaseController {
    private String prefix = "uip/imBusino";

    @Autowired
    private IImBusinoService imBusinoService;

    @RequiresPermissions("uip:imBusino:view")
    @GetMapping()
    public String imBusino() {
        return prefix + "/imBusino";
    }

    /**
     * 查询异常流水列表
     */
    @RequiresPermissions("uip:imBusino:list")
    @PostMapping("/list")

    public TableDataInfo list(ImBusino imBusino) {
        startPage();
        List<ImBusino> list = imBusinoService.selectImBusinoList(imBusino);
        return getDataTable(list);
    }


    /**
     * 导出异常流水列表
     */
    @RequiresPermissions("uip:imBusino:export")
    @PostMapping("/export")

    public AjaxResult export(ImBusino imBusino) {
        List<ImBusino> list = imBusinoService.selectImBusinoList(imBusino);
        ExcelUtil<ImBusino> util = new ExcelUtil<ImBusino>(ImBusino.class);
        return util.exportExcel(list, "imBusino");
    }

    /**
     * 新增异常流水
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存异常流水
     */
    @RequiresPermissions("uip:imBusino:add")
    @Log(title = "异常流水", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(ImBusino imBusino) {
        return toAjax(imBusinoService.insertImBusino(imBusino));
    }

    /**
     * 修改异常流水
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ImBusino imBusino = imBusinoService.selectImBusinoById(id);
        mmap.put("imBusino", imBusino);
        return prefix + "/edit";
    }

    /**
     * 修改保存异常流水
     */
    @RequiresPermissions("uip:imBusino:edit")
    @Log(title = "异常流水", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(ImBusino imBusino) {
        return toAjax(imBusinoService.updateImBusino(imBusino));
    }

    /**
     * 删除异常流水
     */
    @RequiresPermissions("uip:imBusino:remove")
    @Log(title = "异常流水", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imBusinoService.deleteImBusinoByIds(ids));
    }

}
