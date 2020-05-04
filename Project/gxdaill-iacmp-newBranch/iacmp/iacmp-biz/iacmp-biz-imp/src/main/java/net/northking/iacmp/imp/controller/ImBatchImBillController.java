package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImBatchImBill;
import net.northking.iacmp.imp.service.IImBatchImBillService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 影像类型流水关联 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imBatchImBill")
public class ImBatchImBillController extends BaseController {

    @Autowired
    private IImBatchImBillService imBatchImBillService;

    /**
     * 查询影像类型流水关联列表
     */
    @RequiresPermissions("uip:imBatchImBill:list")
    @PostMapping("/list")
    public List<ImBatchImBill> list(ImBatchImBill imBatchImBill) {
        List<ImBatchImBill> list = imBatchImBillService.selectImBatchImBillList(imBatchImBill);
        return list;
    }


    /**
     * 导出影像类型流水关联列表
     */
    @PostMapping("/export")
    public AjaxResult export(ImBatchImBill imBatchImBill) {
        List<ImBatchImBill> list = imBatchImBillService.selectImBatchImBillList(imBatchImBill);
        ExcelUtil<ImBatchImBill> util = new ExcelUtil<ImBatchImBill>(ImBatchImBill.class);
        return util.exportExcel(list, "imBatchImBill");
    }


    /**
     * 新增保存影像类型流水关联
     */
    @Log(title = "影像类型流水关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(ImBatchImBill imBatchImBill) {
        return toAjax(imBatchImBillService.insertImBatchImBill(imBatchImBill));
    }

    /**
     * 修改影像类型流水关联
     */
    @GetMapping("/edit/{id}")
    public ImBatchImBill edit(@PathVariable("id") String id, ModelMap mmap) {
        ImBatchImBill imBatchImBill = imBatchImBillService.selectImBatchImBillById(id);
        return imBatchImBill;
    }

    /**
     * 修改保存影像类型流水关联
     */
    @Log(title = "影像类型流水关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editSave(ImBatchImBill imBatchImBill) {
        return toAjax(imBatchImBillService.updateImBatchImBill(imBatchImBill));
    }

    /**
     * 删除影像类型流水关联
     */
    @Log(title = "影像类型流水关联", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(String ids) {
        return toAjax(imBatchImBillService.deleteImBatchImBillByIds(ids));
    }

}
