package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImImageLostLog;
import net.northking.iacmp.imp.service.IImImageLostLogService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 影像缺失 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imImageLostLog")
public class ImImageLostLogController extends BaseController {
    private String prefix = "uip/imImageLostLog";

    @Autowired
    private IImImageLostLogService imImageLostLogService;

    @RequiresPermissions("uip:imImageLostLog:view")
    @GetMapping()
    public String imImageLostLog() {
        return prefix + "/imImageLostLog";
    }

    /**
     * 查询影像缺失列表
     */
    @RequiresPermissions("uip:imImageLostLog:list")
    @PostMapping("/list")

    public TableDataInfo list(ImImageLostLog imImageLostLog) {
        startPage();
        List<ImImageLostLog> list = imImageLostLogService.selectImImageLostLogList(imImageLostLog);
        return getDataTable(list);
    }


    /**
     * 导出影像缺失列表
     */
    @RequiresPermissions("uip:imImageLostLog:export")
    @PostMapping("/export")

    public AjaxResult export(ImImageLostLog imImageLostLog) {
        List<ImImageLostLog> list = imImageLostLogService.selectImImageLostLogList(imImageLostLog);
        ExcelUtil<ImImageLostLog> util = new ExcelUtil<ImImageLostLog>(ImImageLostLog.class);
        return util.exportExcel(list, "imImageLostLog");
    }

    /**
     * 新增影像缺失
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存影像缺失
     */
    @RequiresPermissions("uip:imImageLostLog:add")
    @Log(title = "影像缺失", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(ImImageLostLog imImageLostLog) {
        return toAjax(imImageLostLogService.insertImImageLostLog(imImageLostLog));
    }

    /**
     * 修改影像缺失
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        ImImageLostLog imImageLostLog = imImageLostLogService.selectImImageLostLogById(id);
        mmap.put("imImageLostLog", imImageLostLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存影像缺失
     */
    @RequiresPermissions("uip:imImageLostLog:edit")
    @Log(title = "影像缺失", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(ImImageLostLog imImageLostLog) {
        return toAjax(imImageLostLogService.updateImImageLostLog(imImageLostLog));
    }

    /**
     * 删除影像缺失
     */
    @RequiresPermissions("uip:imImageLostLog:remove")
    @Log(title = "影像缺失", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imImageLostLogService.deleteImImageLostLogByIds(ids));
    }

}
