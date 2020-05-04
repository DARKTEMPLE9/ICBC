package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmOrgan;
import net.northking.iacmp.imp.service.ISmOrganService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 部门机构 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/smOrgan")
public class SmOrganController extends BaseController {
    private String prefix = "uip/smOrgan";

    @Autowired
    private ISmOrganService smOrganService;

    @RequiresPermissions("uip:smOrgan:view")
    @GetMapping()
    public String smOrgan() {
        return prefix + "/smOrgan";
    }

    /**
     * 查询部门机构列表
     */
    @PostMapping("/queryAllOrgan")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<SmOrgan> queryAllOrgan(@RequestBody HashMap smOrgan) {
        List<SmOrgan> list = smOrganService.selectSmOrganList(smOrgan);
        return list;
    }

    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(@RequestBody HashMap map) {
        Integer res = smOrganService.count(map);
        return res;
    }

    @PostMapping("/findByOrganCode")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<SmOrgan> findByOrganCode(@RequestBody String code) {
        List<SmOrgan> list = smOrganService.findByOrganCode(code);
        return list;
    }


    /**
     * 导出部门机构列表
     */
//    @RequiresPermissions("uip:smOrgan:export")
//    @PostMapping("/export")
//
//    public AjaxResult export(SmOrgan smOrgan)
//    {
//    	List<SmOrgan> list = smOrganService.selectSmOrganList(smOrgan);
//        ExcelUtil<SmOrgan> util = new ExcelUtil<SmOrgan>(SmOrgan.class);
//        return util.exportExcel(list, "smOrgan");
//    }

    /**
     * 新增保存部门机构
     */
    @PostMapping("/insertOrgan")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer insertOrgan(@RequestBody SmOrgan smOrgan) {
        Integer res = smOrganService.insertSmOrgan(smOrgan);
        return res;
    }

    /**
     * 修改部门机构
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        SmOrgan smOrgan = smOrganService.selectSmOrganById(id);
        mmap.put("smOrgan", smOrgan);
        return prefix + "/edit";
    }

    /**
     * 修改保存部门机构
     */
    @RequiresPermissions("uip:smOrgan:edit")
    @Log(title = "部门机构", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(SmOrgan smOrgan) {
        return toAjax(smOrganService.updateSmOrgan(smOrgan));
    }

    /**
     * 删除部门机构
     */
    @RequiresPermissions("uip:smOrgan:remove")
    @Log(title = "部门机构", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(smOrganService.deleteSmOrganByIds(ids));
    }

}
