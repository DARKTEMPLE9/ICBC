package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImOcr;
import net.northking.iacmp.imp.service.IImOcrService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 识别结果 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imOcr")
public class ImOcrController extends BaseController {
    private String prefix = "uip/imOcr";

    @Autowired
    private IImOcrService imOcrService;

    @RequiresPermissions("uip:imOcr:view")
    @GetMapping()
    public String imOcr() {
        return prefix + "/imOcr";
    }

    /**
     * 查询识别结果列表
     */
    @PostMapping("/list")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImOcr> list(@RequestBody HashMap imOcr) {
        List<ImOcr> list = imOcrService.selectImOcrList(imOcr);
        return list;
    }

    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(@RequestBody HashMap map) {
        Integer res = imOcrService.count(map);
        return res;
    }

    @PostMapping("/selectById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public HashMap selectById(@RequestBody String id) {
        HashMap map = imOcrService.selectById(id);
        return map;
    }
    /**
     * 导出识别结果列表
     */
//    @RequiresPermissions("uip:imOcr:export")
//    @PostMapping("/export")
//
//    public AjaxResult export(ImOcr imOcr)
//    {
//    	List<ImOcr> list = imOcrService.selectImOcrList(imOcr);
//        ExcelUtil<ImOcr> util = new ExcelUtil<ImOcr>(ImOcr.class);
//        return util.exportExcel(list, "imOcr");
//    }

    /**
     * 新增识别结果
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存识别结果
     */
    @Log(title = "识别结果", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ImOcr imOcr) {
        return toAjax(imOcrService.insertImOcr(imOcr));
    }

    /**
     * 修改保存识别结果
     */
    @Log(title = "识别结果", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody ImOcr imOcr) {
        return toAjax(imOcrService.updateImOcr(imOcr));
    }

    /**
     * 删除识别结果
     */
    @Log(title = "识别结果", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody String ids) {
        return toAjax(imOcrService.deleteImOcrByIds(ids));
    }

}
