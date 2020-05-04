package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.MWorkerNode;
import net.northking.iacmp.imp.service.IMWorkerNodeService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DB WorkerID Assigner for UID Generator 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/mWorkerNode")
public class MWorkerNodeController extends BaseController {
    private String prefix = "uip/mWorkerNode";

    @Autowired
    private IMWorkerNodeService mWorkerNodeService;

    @RequiresPermissions("uip:mWorkerNode:view")
    @GetMapping()
    public String mWorkerNode() {
        return prefix + "/mWorkerNode";
    }

    /**
     * 查询DB WorkerID Assigner for UID Generator列表
     */
    @RequiresPermissions("uip:mWorkerNode:list")
    @PostMapping("/list")

    public TableDataInfo list(MWorkerNode mWorkerNode) {
        startPage();
        List<MWorkerNode> list = mWorkerNodeService.selectMWorkerNodeList(mWorkerNode);
        return getDataTable(list);
    }


    /**
     * 导出DB WorkerID Assigner for UID Generator列表
     */
    @RequiresPermissions("uip:mWorkerNode:export")
    @PostMapping("/export")

    public AjaxResult export(MWorkerNode mWorkerNode) {
        List<MWorkerNode> list = mWorkerNodeService.selectMWorkerNodeList(mWorkerNode);
        ExcelUtil<MWorkerNode> util = new ExcelUtil<MWorkerNode>(MWorkerNode.class);
        return util.exportExcel(list, "mWorkerNode");
    }

    /**
     * 新增DB WorkerID Assigner for UID Generator
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存DB WorkerID Assigner for UID Generator
     */
    @RequiresPermissions("uip:mWorkerNode:add")
    @Log(title = "DB WorkerID Assigner for UID Generator", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(MWorkerNode mWorkerNode) {
        return toAjax(mWorkerNodeService.insertMWorkerNode(mWorkerNode));
    }

    /**
     * 修改DB WorkerID Assigner for UID Generator
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        MWorkerNode mWorkerNode = mWorkerNodeService.selectMWorkerNodeById(id);
        mmap.put("mWorkerNode", mWorkerNode);
        return prefix + "/edit";
    }

    /**
     * 修改保存DB WorkerID Assigner for UID Generator
     */
    @RequiresPermissions("uip:mWorkerNode:edit")
    @Log(title = "DB WorkerID Assigner for UID Generator", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(MWorkerNode mWorkerNode) {
        return toAjax(mWorkerNodeService.updateMWorkerNode(mWorkerNode));
    }

    /**
     * 删除DB WorkerID Assigner for UID Generator
     */
    @RequiresPermissions("uip:mWorkerNode:remove")
    @Log(title = "DB WorkerID Assigner for UID Generator", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(mWorkerNodeService.deleteMWorkerNodeByIds(ids));
    }

}
