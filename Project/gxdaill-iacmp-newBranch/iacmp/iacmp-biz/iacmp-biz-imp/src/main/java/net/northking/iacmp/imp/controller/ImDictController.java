package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImDict;
import net.northking.iacmp.imp.service.IImDictService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 分类字典 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imDict")
public class ImDictController extends BaseController {
    private String prefix = "uip/imDict";

    @Autowired
    private IImDictService imDictService;

    @RequiresPermissions("uip:imDict:view")
    @GetMapping()
    public String imDict() {
        return prefix + "/imDict";
    }

    /**
     * 查询分类字典列表
     */
    @PostMapping("/list")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImDict> list(HashMap imDict) {
        List<ImDict> list = imDictService.selectImDictList(imDict);
        return list;
    }

    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(HashMap imDict) {
        Integer res = imDictService.count(imDict);
        return res;
    }

    /**
     * 导出分类字典列表
     */
//    @RequiresPermissions("uip:imDict:export")
//    @PostMapping("/export")
//
//    public AjaxResult export(ImDict imDict)
//    {
//    	List<ImDict> list = imDictService.selectImDictList(imDict);
//        ExcelUtil<ImDict> util = new ExcelUtil<ImDict>(ImDict.class);
//        return util.exportExcel(list, "imDict");
//    }

    /**
     * 新增分类字典
     */
    @PostMapping("/addDict")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer addDict(@RequestBody HashMap imDict) {
        return imDictService.addDict(imDict);
    }

    /**
     * 新增保存分类字典
     */
//    @RequiresPermissions("uip:imDict:add")
//	@Log(title = "分类字典", businessType = BusinessType.INSERT)
//	@PostMapping("/add")
//
//	public AjaxResult addSave(ImDict imDict)
//	{
//		return toAjax(imDictService.insertImDict(imDict));
//	}

    /**
     * 修改分类字典
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ImDict imDict = imDictService.selectImDictById(id);
        mmap.put("imDict", imDict);
        return prefix + "/edit";
    }

    /**
     * 修改保存分类字典
     */
    @RequiresPermissions("uip:imDict:edit")
    @Log(title = "分类字典", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(ImDict imDict) {
        return toAjax(imDictService.updateImDict(imDict));
    }

    /**
     * 删除分类字典
     */
    @RequiresPermissions("uip:imDict:remove")
    @Log(title = "分类字典", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imDictService.deleteImDictByIds(ids));
    }

}
