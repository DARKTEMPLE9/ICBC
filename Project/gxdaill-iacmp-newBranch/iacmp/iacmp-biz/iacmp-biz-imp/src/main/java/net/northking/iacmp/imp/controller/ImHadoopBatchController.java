package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImHadoopBatch;
import net.northking.iacmp.imp.service.IImHadoopBatchService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 大数据批次 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imHadoopBatch")
public class ImHadoopBatchController extends BaseController {
    private String prefix = "uip/imHadoopBatch";

    @Autowired
    private IImHadoopBatchService imHadoopBatchService;

    @RequiresPermissions("uip:imHadoopBatch:view")
    @GetMapping()
    public String imHadoopBatch() {
        return prefix + "/imHadoopBatch";
    }

    /**
     * 查询大数据批次列表
     */
    @PostMapping("/list")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImHadoopBatch> list(@RequestBody HashMap imHadoopBatch) {
        List<ImHadoopBatch> list = imHadoopBatchService.selectImHadoopBatchList(imHadoopBatch);
        return list;
    }

    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(@RequestBody HashMap map) {
        Integer res = imHadoopBatchService.count(map);
        return res;
    }

    @PostMapping("/selectByRegno")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImHadoopBatch> selectByRegno(@RequestBody String regno) {
        List<ImHadoopBatch> list = imHadoopBatchService.selectByRegno(regno);
        return list;
    }

    /**
     * 导出大数据批次列表
     */
//    @RequiresPermissions("uip:imHadoopBatch:export")
//    @PostMapping("/export")
//
//    public AjaxResult export(ImHadoopBatch imHadoopBatch)
//    {
//    	List<ImHadoopBatch> list = imHadoopBatchService.selectImHadoopBatchList(imHadoopBatch);
//        ExcelUtil<ImHadoopBatch> util = new ExcelUtil<ImHadoopBatch>(ImHadoopBatch.class);
//        return util.exportExcel(list, "imHadoopBatch");
//    }

    /**
     * 新增保存大数据批次
     */
    @PostMapping("/insert")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer addSave(@RequestBody HashMap imHadoopBatch) {
        imHadoopBatch.put("createTime", new Date());
        Integer res = imHadoopBatchService.insertImHadoopBatch(imHadoopBatch);
        return res;
    }

    /**
     * 修改大数据批次
     */
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") String id, ModelMap mmap) {
//        ImHadoopBatch imHadoopBatch = imHadoopBatchService.selectImHadoopBatchById(id);
//		mmap.put("imHadoopBatch", imHadoopBatch);
//	    return prefix + "/edit";
//	}

    /**
     * 修改保存大数据批次
     */
    @RequiresPermissions("uip:imHadoopBatch:edit")
    @Log(title = "大数据批次", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(ImHadoopBatch imHadoopBatch) {
        return toAjax(imHadoopBatchService.updateImHadoopBatch(imHadoopBatch));
    }

    /**
     * 删除大数据批次
     */
    @RequiresPermissions("uip:imHadoopBatch:remove")
    @Log(title = "大数据批次", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imHadoopBatchService.deleteImHadoopBatchByIds(ids));
    }

}
