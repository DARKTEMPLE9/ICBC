package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImAnnotation;
import net.northking.iacmp.imp.service.IImAnnotationService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图片批注 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imAnnotation")
public class ImAnnotationController extends BaseController {
    @Autowired
    private IImAnnotationService imAnnotationService;


    /**
     * 通过批注编id查找批注信息
     *
     * @param id
     * @return
     */
    @GetMapping("/queryImAnnotationById")
    public ImAnnotation queryImAnnotationById(String id) {
        return imAnnotationService.selectImAnnotationById(id);
    }


    /**
     * 通过影像编号查找批注信息
     */
    @GetMapping("/queryImageAnnotationByIds")
    public List<ImAnnotation> queryImageAnnotationByIds(String ids) {
        return imAnnotationService.selectImAnnotationByIds(ids);
    }

    /**
     * 导出图片批注列表
     */
    @PostMapping("/export")
    public AjaxResult export(ImAnnotation imAnnotation) {
        List<ImAnnotation> list = imAnnotationService.selectImAnnotationList(imAnnotation);
        ExcelUtil<ImAnnotation> util = new ExcelUtil<ImAnnotation>(ImAnnotation.class);
        return util.exportExcel(list, "imAnnotation");
    }

    /**
     * 新增保存图片批注
     */
    @Log(title = "图片批注", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addSave(@RequestBody ImAnnotation imAnnotation) {
        return toAjax(imAnnotationService.insertImAnnotation(imAnnotation));
    }

    /**
     * 修改图片批注
     */
    @GetMapping("/edit/{id}")
    public ImAnnotation edit(@PathVariable("id") String id, ModelMap mmap) {
        ImAnnotation imAnnotation = imAnnotationService.selectImAnnotationById(id);
        return imAnnotation;
    }

    /**
     * 修改保存图片批注
     */
    @Log(title = "图片批注", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editSave(ImAnnotation imAnnotation) {
        return toAjax(imAnnotationService.updateImAnnotation(imAnnotation));
    }

    /**
     * 删除图片批注
     */
    @Log(title = "图片批注", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(String ids) {
        return toAjax(imAnnotationService.deleteImAnnotationByIds(ids));
    }

}
