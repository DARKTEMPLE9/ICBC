package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImTransactionBusino;
import net.northking.iacmp.imp.service.IImTransactionBusinoService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 全局流水影像流水反向索引 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imTransactionBusino")
public class ImTransactionBusinoController extends BaseController {
    private String prefix = "uip/imTransactionBusino";

    @Autowired
    private IImTransactionBusinoService imTransactionBusinoService;

    @RequiresPermissions("uip:imTransactionBusino:view")
    @GetMapping()
    public String imTransactionBusino() {
        return prefix + "/imTransactionBusino";
    }

    /**
     * 查询全局流水影像流水反向索引列表
     */
    @RequiresPermissions("uip:imTransactionBusino:list")
    @PostMapping("/list")

    public TableDataInfo list(ImTransactionBusino imTransactionBusino) {
        startPage();
        List<ImTransactionBusino> list = imTransactionBusinoService.selectImTransactionBusinoList(imTransactionBusino);
        return getDataTable(list);
    }


    /**
     * 导出全局流水影像流水反向索引列表
     */
    @RequiresPermissions("uip:imTransactionBusino:export")
    @PostMapping("/export")

    public AjaxResult export(ImTransactionBusino imTransactionBusino) {
        List<ImTransactionBusino> list = imTransactionBusinoService.selectImTransactionBusinoList(imTransactionBusino);
        ExcelUtil<ImTransactionBusino> util = new ExcelUtil<ImTransactionBusino>(ImTransactionBusino.class);
        return util.exportExcel(list, "imTransactionBusino");
    }

    /**
     * 新增全局流水影像流水反向索引
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存全局流水影像流水反向索引
     */
    @RequiresPermissions("uip:imTransactionBusino:add")
    @Log(title = "全局流水影像流水反向索引", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(ImTransactionBusino imTransactionBusino) {
        return toAjax(imTransactionBusinoService.insertImTransactionBusino(imTransactionBusino));
    }

    /**
     * 修改全局流水影像流水反向索引
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        ImTransactionBusino imTransactionBusino = imTransactionBusinoService.selectImTransactionBusinoById(id);
        mmap.put("imTransactionBusino", imTransactionBusino);
        return prefix + "/edit";
    }

    /**
     * 修改保存全局流水影像流水反向索引
     */
    @RequiresPermissions("uip:imTransactionBusino:edit")
    @Log(title = "全局流水影像流水反向索引", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(ImTransactionBusino imTransactionBusino) {
        return toAjax(imTransactionBusinoService.updateImTransactionBusino(imTransactionBusino));
    }

    /**
     * 删除全局流水影像流水反向索引
     */
    @RequiresPermissions("uip:imTransactionBusino:remove")
    @Log(title = "全局流水影像流水反向索引", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imTransactionBusinoService.deleteImTransactionBusinoByIds(ids));
    }

}
