package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImRemark;
import net.northking.iacmp.imp.service.IImRemarkService;
import net.northking.iacmp.imp.vo.ImRemarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 注解 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imRemark")
public class ImRemarkController extends BaseController {
    private String prefix = "uip/imRemark";

    @Autowired
    private IImRemarkService imRemarkService;

    @GetMapping()
    public String imRemark() {
        return prefix + "/imRemark";
    }

    /**
     * 查询注解列表
     */
    @PostMapping("/list")
    public TableDataInfo list(ImRemark imRemark) {
        startPage();
        List<ImRemark> list = imRemarkService.selectImRemarkList(imRemark);
        return getDataTable(list);
    }

    @PostMapping("/queryRemarksByBatchId")
    public List<ImRemark> queryRemarksByBatchId(@RequestBody ImRemarkVO imRemark) {
        return imRemarkService.queryRemarksByBatchId(imRemark);
    }

    /**
     * 新增注解
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存注解
     */
    @Log(title = "注解", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addSave(@RequestBody ImRemark imRemark) {
        return toAjax(imRemarkService.insertImRemark(imRemark));
    }

    /**
     * 修改注解
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        ImRemark imRemark = imRemarkService.selectImRemarkById(id);
        mmap.put("imRemark", imRemark);
        return prefix + "/edit";
    }

    /**
     * 修改保存注解
     */
    @Log(title = "注解", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(ImRemark imRemark) {
        return toAjax(imRemarkService.updateImRemark(imRemark));
    }

    /**
     * 删除注解
     */
    @Log(title = "注解", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(String ids) {
        return toAjax(imRemarkService.deleteImRemarkByIds(ids));
    }

}
