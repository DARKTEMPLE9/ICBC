package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImBill;
import net.northking.iacmp.imp.service.IImBillService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 影像分类 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imBill")
public class ImBillController extends BaseController {

    @Autowired
    private IImBillService imBillService;

    /**
     * 查询影像分类列表
     */
    @PostMapping("/list")
    public List<ImBill> list(ImBill imBill) {
        List<ImBill> list = imBillService.selectImBillList(imBill);
        return list;
    }

    @PostMapping("/listAll")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImBill> listAll(@RequestBody HashMap map) {
        List<ImBill> list = imBillService.selectImBillListAll(map);
        return list;
    }

    @PostMapping("/imBillById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public HashMap imBillById(@RequestBody String imBillId) {
        HashMap map;
        map = imBillService.imBillById(imBillId);
        return map;
    }

    @GetMapping("/selectImBillById")
    public ImBill selectImBillById(@RequestParam("imBillId") String imBillId) {
        return imBillService.selectImBillById(imBillId);
    }

    /**
     * 查询日志总数
     */
    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(@RequestBody HashMap map) {
        Integer res = imBillService.selectImBillCount(map);
        return res;
    }

    /**
     * 导出影像分类列表
     */
    @PostMapping("/export")
    public AjaxResult export(ImBill imBill) {
        List<ImBill> list = imBillService.selectImBillList(imBill);
        ExcelUtil<ImBill> util = new ExcelUtil<ImBill>(ImBill.class);
        return util.exportExcel(list, "imBill");
    }

    /**
     * 新增前检查
     */
    @PostMapping("/checkDistingct")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer checkDistingct(@RequestBody HashMap map) {
        Integer res = imBillService.checkDistingct(map);
        return res;
    }

    /**
     * 新增保存影像分类
     */
    @PostMapping("/add")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer addSave(@RequestBody HashMap imBill) {
        BigDecimal billOrder = new BigDecimal(Integer.toString((Integer) imBill.get("billOrder")));
        imBill.put("billOrder", billOrder);
        Integer res = imBillService.insertImBill(imBill);
        return res;
    }

    /**
     * 修改影像分类
     */
    @GetMapping("/edit/{id}")
    public ImBill edit(@PathVariable("id") String id, ModelMap mmap) {
        ImBill imBill = imBillService.selectImBillById(id);
        return imBill;
    }

    /**
     * 修改保存影像分类
     */
    @PostMapping("/update")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer editSave(@RequestBody HashMap imBill) {
        BigDecimal billOrder = new BigDecimal(Integer.toString((Integer) imBill.get("billOrder")));
        imBill.put("billOrder", billOrder);
        Integer res = imBillService.updateImBill(imBill);
        return res;
    }

    /**
     * 删除影像分类
     */
    @PostMapping("/deleteByBillId")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer deleteByBillId(@RequestBody String billId) {
        Integer res = imBillService.deleteImBillById(billId);
        return res;
    }

}
