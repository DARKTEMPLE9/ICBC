package net.northking.iacmp.ams.web.controller.parammgr;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsParam;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.server.service.IAmsParamService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 档案参数 信息操作处理
 *
 * @author wxy
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/param/amsParam")
public class AmsParamController extends BaseController {
    private String prefix = "param/amsParam";

    @Autowired
    private IAmsParamService amsParamService;

    @RequiresPermissions("param:amsParam:view")
    @GetMapping()
    public String amsParam() {
        return prefix + "/amsParam";
    }

    /**
     * 查询档案参数列表
     */
    @RequiresPermissions("param:amsParam:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsParam amsParam) {
        startPage();
        List<AmsParam> list = amsParamService.selectAmsParamList(amsParam);
        return getDataTable(list);
    }


    /**
     * 导出档案参数列表
     */
    @RequiresPermissions("param:amsParam:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsParam amsParam) {
        List<AmsParam> list = amsParamService.selectAmsParamList(amsParam);
        ExcelUtil<AmsParam> util = new ExcelUtil<AmsParam>(AmsParam.class);
        return util.exportExcel(list, "amsParam");
    }

    /**
     * 新增档案参数
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案参数
     */
    @RequiresPermissions("param:amsParam:add")
    @Log(title = "档案参数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsParam amsParam) {
        return toAjax(amsParamService.insertAmsParam(amsParam));
    }

    /**
     * 修改档案参数
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsParam amsParam = amsParamService.selectAmsParamById(id);
        mmap.put("amsParam", amsParam);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案参数
     */
    @RequiresPermissions("param:amsParam:edit")
    @Log(title = "档案参数", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsParam amsParam) {
        return toAjax(amsParamService.updateAmsParam(amsParam));
    }

    /**
     * 删除档案参数
     */
    @RequiresPermissions("param:amsParam:remove")
    @Log(title = "档案参数", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsParamService.deleteAmsParamByIds(ids));
    }

    /**
     * 查看档案参数
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") String id, ModelMap mmap) {
        AmsParam amsParam = amsParamService.selectAmsParamById(id);
        mmap.put("amsParam", amsParam);
        return prefix + "/view";
    }

}
