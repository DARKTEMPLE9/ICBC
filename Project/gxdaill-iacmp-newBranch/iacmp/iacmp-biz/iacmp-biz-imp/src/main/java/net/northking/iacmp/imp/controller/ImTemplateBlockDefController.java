package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImTemplateBlockDef;
import net.northking.iacmp.imp.service.IImTemplateBlockDefService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 模版碎片定义 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imTemplateBlockDef")
public class ImTemplateBlockDefController extends BaseController {
    private String prefix = "uip/imTemplateBlockDef";

    @Autowired
    private IImTemplateBlockDefService imTemplateBlockDefService;

    @RequiresPermissions("uip:imTemplateBlockDef:view")
    @GetMapping()
    public String imTemplateBlockDef() {
        return prefix + "/imTemplateBlockDef";
    }

    /**
     * 查询模版碎片定义列表
     */
    @PostMapping("/listAll")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImTemplateBlockDef> listAll(@RequestBody HashMap imTemplateBlockDef) {
        List<ImTemplateBlockDef> list = imTemplateBlockDefService.selectImTemplateBlockDefList(imTemplateBlockDef);
        return list;
    }

    /**
     * 查询总数
     *
     * @return
     */
    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(@RequestBody HashMap map) {
        Integer count = 0;
        count = imTemplateBlockDefService.count(map);
        return count;
    }

    @PostMapping("/selectById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public HashMap selectById(@RequestBody String id) {
        HashMap map = imTemplateBlockDefService.selectById(id);
        return map;
    }


    /**
     * 导出模版碎片定义列表
     */
//    @RequiresPermissions("uip:imTemplateBlockDef:export")
//    @PostMapping("/export")
//
//    public AjaxResult export(ImTemplateBlockDef imTemplateBlockDef)
//    {
//    	List<ImTemplateBlockDef> list = imTemplateBlockDefService.selectImTemplateBlockDefList(imTemplateBlockDef);
//        ExcelUtil<ImTemplateBlockDef> util = new ExcelUtil<ImTemplateBlockDef>(ImTemplateBlockDef.class);
//        return util.exportExcel(list, "imTemplateBlockDef");
//    }

    /**
     * 新增模版碎片定义
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存模版碎片定义
     */
    @PostMapping("/insert")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer addSave(@RequestBody HashMap imTemplateBlockDef) {
        return imTemplateBlockDefService.insertImTemplateBlockDef(imTemplateBlockDef);
    }

    /**
     * 修改模版碎片定义
     */
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") String id, ModelMap mmap) {
//        ImTemplateBlockDef imTemplateBlockDef = imTemplateBlockDefService.selectImTemplateBlockDefById(id);
//		mmap.put("imTemplateBlockDef", imTemplateBlockDef);
//	    return prefix + "/edit";
//	}

    /**
     * 修改保存模版碎片定义
     */
    @PostMapping("/updateById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer updateById(@RequestBody HashMap imTemplateBlockDef) {
        return imTemplateBlockDefService.updateById(imTemplateBlockDef);
    }

    /**
     * 删除模版碎片定义
     */
    @RequiresPermissions("uip:imTemplateBlockDef:remove")
    @Log(title = "模版碎片定义", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imTemplateBlockDefService.deleteImTemplateBlockDefByIds(ids));
    }

}
