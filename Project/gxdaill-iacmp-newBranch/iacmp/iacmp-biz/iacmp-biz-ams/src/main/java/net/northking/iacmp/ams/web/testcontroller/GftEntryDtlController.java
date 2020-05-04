package net.northking.iacmp.ams.web.testcontroller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.server.service.IGftEntryDtlService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 时间戳 信息操作处理
 *
 * @author wxy
 * @date 2019-08-20
 */
@Controller
@RequestMapping("/testController/gftEntryDtl")
public class GftEntryDtlController extends BaseController {
    private String prefix = "testController/gftEntryDtl";

    @Autowired
    private IGftEntryDtlService gftEntryDtlService;

    @RequiresPermissions("testController:gftEntryDtl:view")
    @GetMapping()
    public String gftEntryDtl() {
        return prefix + "/gftEntryDtl";
    }

    /**
     * 查询时间戳列表
     */
    @RequiresPermissions("testController:gftEntryDtl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GftEntryDtl gftEntryDtl) {
        startPage();
        List<GftEntryDtl> list = gftEntryDtlService.selectGftEntryDtlList(gftEntryDtl);
        return getDataTable(list);
    }


    /**
     * 导出时间戳列表
     */
    @RequiresPermissions("testController:gftEntryDtl:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GftEntryDtl gftEntryDtl) {
        List<GftEntryDtl> list = gftEntryDtlService.selectGftEntryDtlList(gftEntryDtl);
        ExcelUtil<GftEntryDtl> util = new ExcelUtil<GftEntryDtl>(GftEntryDtl.class);
        return util.exportExcel(list, "gftEntryDtl");
    }

    /**
     * 新增时间戳
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存时间戳
     */
    @RequiresPermissions("testController:gftEntryDtl:add")
    @Log(title = "时间戳", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GftEntryDtl gftEntryDtl) {
        return toAjax(gftEntryDtlService.insertGftEntryDtl(gftEntryDtl));
    }

    /**
     * 修改时间戳
     */
    @GetMapping("/edit/{pkEntryDtl}")
    public String edit(@PathVariable("pkEntryDtl") String pkEntryDtl, ModelMap mmap) {
        GftEntryDtl gftEntryDtl = gftEntryDtlService.selectGftEntryDtlById(pkEntryDtl);
        mmap.put("gftEntryDtl", gftEntryDtl);
        return prefix + "/edit";
    }

    /**
     * 查看会计档案详细页面
     *
     * @param pkEntryDtl
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{pkEntryDtl}")
    @RequiresPermissions("iacmp:gftEntryDtl:detail")
    public String Detail(@PathVariable("pkEntryDtl") String pkEntryDtl, ModelMap mmap) {
        GftEntryDtl gftEntryDtl = gftEntryDtlService.selectGftEntryDtlById(pkEntryDtl);
        mmap.put("gftEntryDtl", gftEntryDtl);
        return prefix + "/detail";
    }

    /**
     * 修改保存时间戳
     */
    @RequiresPermissions("testController:gftEntryDtl:edit")
    @Log(title = "时间戳", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GftEntryDtl gftEntryDtl) {
        return toAjax(gftEntryDtlService.updateGftEntryDtl(gftEntryDtl));
    }

    /**
     * 删除时间戳
     */
    @RequiresPermissions("testController:gftEntryDtl:remove")
    @Log(title = "时间戳", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(gftEntryDtlService.deleteGftEntryDtlByIds(ids));
    }

}
