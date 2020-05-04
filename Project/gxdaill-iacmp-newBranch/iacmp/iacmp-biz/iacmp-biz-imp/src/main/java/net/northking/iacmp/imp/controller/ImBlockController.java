package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImBlock;
import net.northking.iacmp.imp.service.IImBlockService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 切片 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imBlock")
public class ImBlockController extends BaseController {

    @Autowired
    private IImBlockService imBlockService;

    /**
     * 查询切片列表
     */
    @PostMapping("/list")
    public List<ImBlock> list(ImBlock imBlock) {
        return imBlockService.selectImBlockList(imBlock);
    }


    /**
     * 导出切片列表
     */
    @RequiresPermissions("uip:imBlock:export")
    @PostMapping("/export")

    public AjaxResult export(ImBlock imBlock) {
        List<ImBlock> list = imBlockService.selectImBlockList(imBlock);
        ExcelUtil<ImBlock> util = new ExcelUtil<ImBlock>(ImBlock.class);
        return util.exportExcel(list, "imBlock");
    }

    /**
     * 新增保存切片
     */
    @Log(title = "切片", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ImBlock imBlock) {
        return toAjax(imBlockService.insertImBlock(imBlock));
    }

    /**
     * 修改保存切片
     */
    @Log(title = "切片", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody ImBlock imBlock) {
        return toAjax(imBlockService.updateImBlock(imBlock));
    }

    /**
     * 删除切片
     */
    @Log(title = "切片", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(imBlockService.deleteImBlockByIds(ids));
    }

}
