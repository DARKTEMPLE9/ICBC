package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImTemplateDef;
import net.northking.iacmp.imp.service.IImTemplateDefService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 票据模版定义 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imTemplateDef")
public class ImTemplateDefController extends BaseController {
    private String prefix = "uip/imTemplateDef";

    @Autowired
    private IImTemplateDefService imTemplateDefService;

    @PostMapping("/selectById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public HashMap selectById(@RequestBody String id) {
        return imTemplateDefService.selectById(id);
    }

    /**
     * 查询票据模版定义列表
     */
    @PostMapping("/listAll")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImTemplateDef> listAll(@RequestBody HashMap imTemplateDef) {
        List<ImTemplateDef> list = imTemplateDefService.selectImTemplateDefList(imTemplateDef);
        return list;
    }

    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(@RequestBody HashMap map) {
        return imTemplateDefService.count(map);
    }

    @PostMapping("/checkBeforeUpdate")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer checkBeforeUpdate(@RequestBody HashMap map) {
        return imTemplateDefService.checkBeforeUpdate(map);
    }


    /**
     * 导出票据模版定义列表
     */
//    @RequiresPermissions("uip:imTemplateDef:export")
//    @PostMapping("/export")
//
//    public AjaxResult export(ImTemplateDef imTemplateDef)
//    {
//    	List<ImTemplateDef> list = imTemplateDefService.selectImTemplateDefList(imTemplateDef);
//        ExcelUtil<ImTemplateDef> util = new ExcelUtil<ImTemplateDef>(ImTemplateDef.class);
//        return util.exportExcel(list, "imTemplateDef");
//    }

    /**
     * 新增保存票据模版定义
     */
    @PostMapping("/insert")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer addSave(@RequestBody HashMap imTemplateDef) {
        return imTemplateDefService.insertImTemplateDef(imTemplateDef);
    }

    /**
     * 修改保存票据模版定义
     */
    @PostMapping("/updateById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer updateById(@RequestBody HashMap imTemplateDef) {
        return imTemplateDefService.updateImTemplateDef(imTemplateDef);
    }

    /**
     * 删除票据模版定义
     */
    @RequiresPermissions("uip:imTemplateDef:remove")
    @Log(title = "票据模版定义", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imTemplateDefService.deleteImTemplateDefByIds(ids));
    }

}
