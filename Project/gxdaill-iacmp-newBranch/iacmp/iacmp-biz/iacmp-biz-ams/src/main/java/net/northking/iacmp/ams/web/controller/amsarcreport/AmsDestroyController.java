package net.northking.iacmp.ams.web.controller.amsarcreport;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsDestroy;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.server.service.IAmsDestroyService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 档案销毁 信息操作处理
 *
 * @author wxy
 * @date 2019-08-02
 */
@Controller
@RequestMapping("/amsArcReportcontroller/amsDestroy")
public class AmsDestroyController extends BaseController {
    private String prefix = "amsArcReportcontroller/amsDestroy";

    @Autowired
    private IAmsDestroyService amsDestroyService;

    @RequiresPermissions("amsArcReportcontroller:amsDestroy:view")
    @GetMapping()
    public String amsDestroy() {
        return prefix + "/amsDestroy";
    }

    /**
     * 查询档案销毁列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsDestroy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsDestroy amsDestroy) {
        startPage();
        List<AmsDestroy> list = amsDestroyService.selectAmsDestroyList(amsDestroy);
        return getDataTable(list);
    }


    /**
     * 导出档案销毁列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsDestroy:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String destoryIds) {
        List<AmsDestroy> userList = new ArrayList<>();
        if (destoryIds != null && !"".equals(destoryIds)) {
            String[] ids = destoryIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                AmsDestroy user = amsDestroyService.selectAmsDestroyById(ids[i]);
                userList.add(user);
            }
        } else {
            AmsDestroy destroy = new AmsDestroy();
            List<AmsDestroy> destroys = amsDestroyService.selectAmsDestroyList(destroy);
            for (AmsDestroy amsDestroy : destroys) {
                userList.add(amsDestroy);
            }
        }
        ExcelUtil<AmsDestroy> util = new ExcelUtil<>(AmsDestroy.class);
        return util.exportExcel(userList, "用户数据");
    }

    /**
     * 新增档案销毁
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案销毁
     */
    @RequiresPermissions("amsArcReportcontroller:amsDestroy:add")
    @Log(title = "档案销毁", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsDestroy amsDestroy) {
        return toAjax(amsDestroyService.insertAmsDestroy(amsDestroy));
    }

    /**
     * 修改档案销毁
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsDestroy amsDestroy = amsDestroyService.selectAmsDestroyById(id);
        mmap.put("amsDestroy", amsDestroy);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案销毁
     */
    @RequiresPermissions("amsArcReportcontroller:amsDestroy:edit")
    @Log(title = "档案销毁", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsDestroy amsDestroy) {
        return toAjax(amsDestroyService.updateAmsDestroy(amsDestroy));
    }

    /**
     * 删除档案销毁
     */
    @RequiresPermissions("amsArcReportcontroller:amsDestroy:remove")
    @Log(title = "档案销毁", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsDestroyService.deleteAmsDestroyByIds(ids));
    }

}
