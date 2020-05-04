package net.northking.iacmp.cms.web.controller.bill;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.cms.service.ICmsSystemService;
import net.northking.iacmp.cms.web.util.CmsInitParamsUtil;
import net.northking.iacmp.common.bean.domain.cms.CmsSystem;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.utils.poi.ExcelUtil;
import net.northking.iacmp.utils.security.Md5Utils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接入系统 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Controller
@RequestMapping("/cms/cmsSystem")
public class CmsSystemController extends BaseController {
    private String prefix = "bill/cmsSystem";

    @Autowired
    private ICmsSystemService cmsSystemService;

    @RequiresPermissions("cms:cmsSystem:view")
    @GetMapping()
    public String cmsSystem() {
        return prefix + "/cmsSystem";
    }

    /**
     * 查询接入系统列表
     */
    @RequiresPermissions("cms:cmsSystem:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsSystem cmsSystem) {
        startPage();
        List<CmsSystem> list = cmsSystemService.selectCmsSystemList(cmsSystem);
        return getDataTable(list);
    }


    /**
     * 导出接入系统列表
     */
    @RequiresPermissions("cms:cmsSystem:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmsSystem cmsSystem) {
        List<CmsSystem> list = cmsSystemService.selectCmsSystemList(cmsSystem);
        ExcelUtil<CmsSystem> util = new ExcelUtil<CmsSystem>(CmsSystem.class);
        return util.exportExcel(list, "cmsSystem");
    }

    /**
     * 新增接入系统
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存接入系统
     */
    @RequiresPermissions("cms:cmsSystem:add")
    @Log(title = "接入系统", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsSystem cmsSystem) {
        cmsSystem.setAuthentInfo(Md5Utils.hash(cmsSystem.getAuthentInfo()));
        int i = cmsSystemService.insertCmsSystem(cmsSystem);
        if (i > 0) {
            CmsInitParamsUtil.getCmsInitParamsUtil().refreshCacheCmsSystem();
        }
        return toAjax(i);
    }

    /**
     * 修改接入系统
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        CmsSystem cmsSystem = cmsSystemService.selectCmsSystemById(Long.valueOf(id));
        mmap.put("cmsSystem", cmsSystem);
        return prefix + "/edit";
    }

    /**
     * 修改保存接入系统
     */
    @RequiresPermissions("cms:cmsSystem:edit")
    @Log(title = "接入系统", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsSystem cmsSystem) {
        CmsSystem system = cmsSystemService.selectCmsSystemByCode(cmsSystem.getSysCode());
        if (!system.getAuthentInfo().equalsIgnoreCase(cmsSystem.getAuthentInfo())) {
            cmsSystem.setAuthentInfo(Md5Utils.hash(cmsSystem.getAuthentInfo()));
        }
        int i = cmsSystemService.updateCmsSystem(cmsSystem);
        if (i > 0) {
            CmsInitParamsUtil.getCmsInitParamsUtil().refreshCacheCmsSystem();
        }
        return toAjax(i);
    }

    /**
     * 删除接入系统
     */
    @RequiresPermissions("cms:cmsSystem:remove")
    @Log(title = "接入系统", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int i = cmsSystemService.deleteCmsSystemByIds(ids);
        if (i > 0) {
            CmsInitParamsUtil.getCmsInitParamsUtil().refreshCacheCmsSystem();
        }
        return toAjax(i);
    }

    /**
     * 校验系统编码唯一
     */
    @PostMapping("/checkSystemCodeUnique")
    @ResponseBody
    public String checkConfigKeyUnique(CmsSystem cmsSystem) {
        return cmsSystemService.checkSystemKeyUnique(cmsSystem);
    }

}
