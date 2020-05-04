package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmParam;
import net.northking.iacmp.imp.service.ISmParamService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/smParam")
public class SmParamController extends BaseController {
    private String prefix = "uip/smParam";

    @Autowired
    private ISmParamService smParamService;

    @GetMapping()
    public String smParam() {
        return prefix + "/smParam";
    }

    /**
     * 查询参数配置列表
     */
    @PostMapping("/list")
    public List<SmParam> list(SmParam smParam) {
        List<SmParam> list = smParamService.selectSmParamList(smParam);
        return list;
    }

    @PostMapping("/listAll")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<SmParam> listAll(@RequestBody HashMap smParam) {
        List<SmParam> list = smParamService.listAll(smParam);
        return list;
    }

    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(@RequestBody HashMap smParam) {
        Integer res = smParamService.count(smParam);
        return res;
    }

    @PostMapping("/queryById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public HashMap queryById(@RequestBody String id) {
        HashMap map = smParamService.queryById(id);
        return map;
    }


    /**
     * 导出参数配置列表
     */
    @RequiresPermissions("uip:smParam:export")
    @PostMapping("/export")

    public AjaxResult export(SmParam smParam) {
        List<SmParam> list = smParamService.selectSmParamList(smParam);
        ExcelUtil<SmParam> util = new ExcelUtil<SmParam>(SmParam.class);
        return util.exportExcel(list, "smParam");
    }

    /**
     * 新增参数配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存参数配置
     */
    @RequiresPermissions("uip:smParam:add")
    @Log(title = "参数配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(SmParam smParam) {
        return toAjax(smParamService.insertSmParam(smParam));
    }

    /**
     * 修改参数配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        SmParam smParam = smParamService.selectSmParamById(id);
        mmap.put("smParam", smParam);
        return prefix + "/edit";
    }

    /**
     * 修改保存参数配置
     */
    @PostMapping("/updateById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer updateById(@RequestBody HashMap smParam) {
        Integer res = smParamService.updateSmParam(smParam);
        return res;
    }

    /**
     * 删除参数配置
     */
    @PostMapping("/deleteById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer deleteById(@RequestBody String id) {
        Integer res = smParamService.deleteById(id);
        return res;
    }

}
