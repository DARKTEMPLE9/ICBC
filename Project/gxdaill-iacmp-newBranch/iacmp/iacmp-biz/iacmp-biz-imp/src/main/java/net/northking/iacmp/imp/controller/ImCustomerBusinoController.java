package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImCustomerBusino;
import net.northking.iacmp.imp.service.IImCustomerBusinoService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户影像流水反向索引 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imCustomerBusino")
public class ImCustomerBusinoController extends BaseController {
    private String prefix = "uip/imCustomerBusino";

    @Autowired
    private IImCustomerBusinoService imCustomerBusinoService;

    @RequiresPermissions("uip:imCustomerBusino:view")
    @GetMapping()
    public String imCustomerBusino() {
        return prefix + "/imCustomerBusino";
    }

    /**
     * 查询客户影像流水反向索引列表
     */
    @PostMapping("/selectRegbillglidenos")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<String> selectRegbillglidenos(@RequestBody String userCode) {
        List<String> list = imCustomerBusinoService.selectRegbillglidenos(userCode);
        return list;
    }


    /**
     * 导出客户影像流水反向索引列表
     */
//    @RequiresPermissions("uip:imCustomerBusino:export")
//    @PostMapping("/export")
//
//    public AjaxResult export(ImCustomerBusino imCustomerBusino)
//    {
//    	List<ImCustomerBusino> list = imCustomerBusinoService.selectImCustomerBusinoList(imCustomerBusino);
//        ExcelUtil<ImCustomerBusino> util = new ExcelUtil<ImCustomerBusino>(ImCustomerBusino.class);
//        return util.exportExcel(list, "imCustomerBusino");
//    }

    /**
     * 新增客户影像流水反向索引
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户影像流水反向索引
     */
    @RequiresPermissions("uip:imCustomerBusino:add")
    @Log(title = "客户影像流水反向索引", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(ImCustomerBusino imCustomerBusino) {
        return toAjax(imCustomerBusinoService.insertImCustomerBusino(imCustomerBusino));
    }

    /**
     * 修改客户影像流水反向索引
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        ImCustomerBusino imCustomerBusino = imCustomerBusinoService.selectImCustomerBusinoById(Long.valueOf(id));
        mmap.put("imCustomerBusino", imCustomerBusino);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户影像流水反向索引
     */
    @RequiresPermissions("uip:imCustomerBusino:edit")
    @Log(title = "客户影像流水反向索引", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(ImCustomerBusino imCustomerBusino) {
        return toAjax(imCustomerBusinoService.updateImCustomerBusino(imCustomerBusino));
    }

    /**
     * 删除客户影像流水反向索引
     */
    @RequiresPermissions("uip:imCustomerBusino:remove")
    @Log(title = "客户影像流水反向索引", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imCustomerBusinoService.deleteImCustomerBusinoByIds(ids));
    }

}
