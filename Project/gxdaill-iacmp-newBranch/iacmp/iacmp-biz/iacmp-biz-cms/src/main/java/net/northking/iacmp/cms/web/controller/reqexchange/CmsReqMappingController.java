package net.northking.iacmp.cms.web.controller.reqexchange;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.cms.service.ICmsReqMappingService;
import net.northking.iacmp.cms.service.ICmsSystemService;
import net.northking.iacmp.common.bean.domain.cms.CmsReqMapping;
import net.northking.iacmp.common.bean.domain.cms.CmsSystem;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 接入系统参数映射 信息操作处理
 *
 * @author wei.chen
 * @date 2019-10-09
 */
@Controller
@RequestMapping("/cms/cmsReqMapping")
public class CmsReqMappingController extends BaseController {
    private String prefix = "reqExchange/cmsReqMapping";

    @Autowired
    private ICmsReqMappingService cmsReqMappingService;

    @Autowired
    private ICmsSystemService cmsSystemService;

    @RequiresPermissions("cms:cmsReqMapping:view")
    @GetMapping()
    public String cmsReqMapping() {
        return prefix + "/cmsReqMapping";
    }

    /**
     * 查询接入系统参数映射列表
     */
    @RequiresPermissions("cms:cmsReqMapping:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsReqMapping cmsReqMapping) {
        startPage();
        List<CmsReqMapping> list = cmsReqMappingService.selectCmsReqMappingList(cmsReqMapping);
        return getDataTable(list);
    }


    /**
     * 导出接入系统参数映射列表
     */
    @RequiresPermissions("cms:cmsReqMapping:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmsReqMapping cmsReqMapping) {
        List<CmsReqMapping> list = cmsReqMappingService.selectCmsReqMappingList(cmsReqMapping);
        ExcelUtil<CmsReqMapping> util = new ExcelUtil<>(CmsReqMapping.class);
        return util.exportExcel(list, "cmsReqMapping");
    }

    /**
     * 新增接入系统参数映射
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        CmsSystem cmsSystem = new CmsSystem();
        mmap.put("systems", cmsSystemService.selectCmsSystemList(cmsSystem));
        return prefix + "/add";
    }

    /**
     * 新增保存接入系统参数映射
     */
    @RequiresPermissions("cms:cmsReqMapping:add")
    @Log(title = "接入系统参数映射", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsReqMapping cmsReqMapping) {
        return toAjax(cmsReqMappingService.insertCmsReqMapping(cmsReqMapping));
    }

    /**
     * 修改接入系统参数映射
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CmsReqMapping cmsReqMapping = cmsReqMappingService.selectCmsReqMappingById(id);
        mmap.put("cmsReqMapping", cmsReqMapping);
        return prefix + "/edit";
    }

    /**
     * 修改保存接入系统参数映射
     */
    @RequiresPermissions("cms:cmsReqMapping:edit")
    @Log(title = "接入系统参数映射", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsReqMapping cmsReqMapping) {
        return toAjax(cmsReqMappingService.updateCmsReqMapping(cmsReqMapping));
    }

    /**
     * 删除接入系统参数映射
     */
    @RequiresPermissions("cms:cmsReqMapping:remove")
    @Log(title = "接入系统参数映射", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cmsReqMappingService.deleteCmsReqMappingByIds(ids));
    }

}
