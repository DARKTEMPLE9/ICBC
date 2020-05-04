package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImAsynWork;
import net.northking.iacmp.imp.service.IImAsynWorkService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 异步任务 信息操作处理
 *
 * @author weizhe.fan
 * @date 2019-10-29
 */
@Controller
@RequestMapping("/uip/imAsynWork")
public class ImAsynWorkController extends BaseController {

    @Autowired
    private IImAsynWorkService imAsynWorkService;

    /**
     * 查询异步任务列表
     */
    @PostMapping("/list")
    @ResponseBody
    public List<ImAsynWork> list(ImAsynWork imAsynWork) {
        return imAsynWorkService.selectImAsynWorkList(imAsynWork);
    }


    /**
     * 导出异步任务列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ImAsynWork imAsynWork) {
        List<ImAsynWork> list = imAsynWorkService.selectImAsynWorkList(imAsynWork);
        ExcelUtil<ImAsynWork> util = new ExcelUtil<ImAsynWork>(ImAsynWork.class);
        return util.exportExcel(list, "imAsynWork");
    }

    /**
     * 新增保存异步任务
     */
    @Log(title = "异步任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ImAsynWork imAsynWork) {
        return toAjax(imAsynWorkService.insertImAsynWork(imAsynWork));
    }

    /**
     * 修改保存异步任务
     */
    @Log(title = "异步任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody ImAsynWork imAsynWork) {
        return toAjax(imAsynWorkService.updateImAsynWork(imAsynWork));
    }

    /**
     * 删除异步任务
     */
    @Log(title = "异步任务", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(imAsynWorkService.deleteImAsynWorkByIds(ids));
    }

}
