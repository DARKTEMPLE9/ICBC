package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImHadoopVideo;
import net.northking.iacmp.imp.service.IImHadoopVideoService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 大数据视频 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imHadoopVideo")
public class ImHadoopVideoController extends BaseController {
    private String prefix = "uip/imHadoopVideo";

    @Autowired
    private IImHadoopVideoService imHadoopVideoService;

    @RequiresPermissions("uip:imHadoopVideo:view")
    @GetMapping()
    public String imHadoopVideo() {
        return prefix + "/imHadoopVideo";
    }

    /**
     * 查询大数据视频列表
     */
    @PostMapping("/list")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImHadoopVideo> list(@RequestBody HashMap imHadoopVideo) {
        List<ImHadoopVideo> list = imHadoopVideoService.selectImHadoopVideoList(imHadoopVideo);
        return list;
    }

    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(@RequestBody HashMap imHadoopVideo) {
        Integer res = imHadoopVideoService.count(imHadoopVideo);
        return res;
    }

    @PostMapping("/selectByBatchId")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImHadoopVideo> selectByBatchId(@RequestBody HashMap batchId) {
        List<ImHadoopVideo> list = imHadoopVideoService.selectByBatchId(batchId);
        return list;
    }

    @PostMapping("/queryAll")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImHadoopVideo> queryAll() {
        List<ImHadoopVideo> list = imHadoopVideoService.queryAll();
        return list;
    }
    /**
     * 导出大数据视频列表
     */
//    @PostMapping("/export")
//    public AjaxResult export(ImHadoopVideo imHadoopVideo)
//    {
//    	List<ImHadoopVideo> list = imHadoopVideoService.selectImHadoopVideoList(imHadoopVideo);
//        ExcelUtil<ImHadoopVideo> util = new ExcelUtil<ImHadoopVideo>(ImHadoopVideo.class);
//        return util.exportExcel(list, "imHadoopVideo");
//    }

    /**
     * 新增保存大数据视频
     */
    @PostMapping("/insert")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer addSave(@RequestBody ImHadoopVideo imHadoopVideo) {
        Integer res = imHadoopVideoService.insertImHadoopVideo(imHadoopVideo);
        return res;
    }

    /**
     * 修改大数据视频
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        ImHadoopVideo imHadoopVideo = imHadoopVideoService.selectImHadoopVideoById(id);
        mmap.put("imHadoopVideo", imHadoopVideo);
        return prefix + "/edit";
    }

    /**
     * 修改保存大数据视频
     */
    @RequiresPermissions("uip:imHadoopVideo:edit")
    @Log(title = "大数据视频", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(ImHadoopVideo imHadoopVideo) {
        return toAjax(imHadoopVideoService.updateImHadoopVideo(imHadoopVideo));
    }

    /**
     * 删除大数据视频
     */
    @RequiresPermissions("uip:imHadoopVideo:remove")
    @Log(title = "大数据视频", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imHadoopVideoService.deleteImHadoopVideoByIds(ids));
    }

}
