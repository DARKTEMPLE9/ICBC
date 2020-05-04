package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.WorkItem;
import net.northking.iacmp.imp.service.IWorkItemService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 识别任务 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/workItem")
public class WorkItemController extends BaseController {

    @Autowired
    private IWorkItemService workItemService;

    /**
     * 查询识别任务列表
     */
    @PostMapping("/list")

    public TableDataInfo list(WorkItem workItem) {
        startPage();
        List<WorkItem> list = workItemService.selectWorkItemList(workItem);
        return getDataTable(list);
    }


    /**
     * 导出识别任务列表
     */
    @PostMapping("/export")

    public AjaxResult export(WorkItem workItem) {
        List<WorkItem> list = workItemService.selectWorkItemList(workItem);
        ExcelUtil<WorkItem> util = new ExcelUtil<WorkItem>(WorkItem.class);
        return util.exportExcel(list, "workItem");
    }

    /**
     * 新增保存识别任务
     */
    @Log(title = "识别任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody WorkItem workItem) {
        return toAjax(workItemService.insertWorkItem(workItem));
    }

    /**
     * 修改保存识别任务
     */
    @Log(title = "识别任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody WorkItem workItem) {
        return toAjax(workItemService.updateWorkItem(workItem));
    }

    /**
     * 删除识别任务
     */
    @Log(title = "识别任务", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(workItemService.deleteWorkItemByIds(ids));
    }

    /**
     * 查询识别任务信息
     *
     * @param id 识别任务ID
     * @return 识别任务信息
     */
    @RequestMapping("/selectWorkItemById")
    public WorkItem selectWorkItemById(@RequestBody String id) {
        return workItemService.selectWorkItemById(id);
    }

}
