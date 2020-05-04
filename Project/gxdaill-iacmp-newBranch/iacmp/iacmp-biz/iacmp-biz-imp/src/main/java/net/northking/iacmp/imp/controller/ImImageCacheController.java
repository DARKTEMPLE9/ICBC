package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImImageCache;
import net.northking.iacmp.imp.service.IImImageCacheService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 异步影像 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imImageCache")
public class ImImageCacheController extends BaseController {
    private String prefix = "uip/imImageCache";

    @Autowired
    private IImImageCacheService imImageCacheService;

    @RequiresPermissions("uip:imImageCache:view")
    @GetMapping()
    public String imImageCache() {
        return prefix + "/imImageCache";
    }

    /**
     * 查询异步影像列表
     */
    @RequiresPermissions("uip:imImageCache:list")
    @PostMapping("/list")

    public TableDataInfo list(ImImageCache imImageCache) {
        startPage();
        List<ImImageCache> list = imImageCacheService.selectImImageCacheList(imImageCache);
        return getDataTable(list);
    }

    @PostMapping("/scanLookAtImageCache")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImImageCache> scanLookAtImageCache(@RequestBody HashMap map) {
        List<ImImageCache> list = imImageCacheService.scanLookAtImageCache(map);
        return list;
    }

    @PostMapping("/selectByImageId")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImImageCache> selectByImageId(@RequestBody String imageId) {
        List<ImImageCache> list = imImageCacheService.selectByImageId(imageId);
        return list;
    }


    /**
     * 导出异步影像列表
     */
    @RequiresPermissions("uip:imImageCache:export")
    @PostMapping("/export")

    public AjaxResult export(ImImageCache imImageCache) {
        List<ImImageCache> list = imImageCacheService.selectImImageCacheList(imImageCache);
        ExcelUtil<ImImageCache> util = new ExcelUtil<ImImageCache>(ImImageCache.class);
        return util.exportExcel(list, "imImageCache");
    }

    /**
     * 新增异步影像
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存异步影像
     */
    @PostMapping("/insert")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public AjaxResult addSave(@RequestBody ImImageCache imImageCache) {
        return toAjax(imImageCacheService.insertImImageCache(imImageCache));
    }

    /**
     * 修改异步影像
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ImImageCache imImageCache = imImageCacheService.selectImImageCacheById(id);
        mmap.put("imImageCache", imImageCache);
        return prefix + "/edit";
    }

    /**
     * 修改保存异步影像
     */
    @PostMapping("/edit")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public AjaxResult editSave(@RequestBody ImImageCache imImageCache) {
        return toAjax(imImageCacheService.updateImImageCache(imImageCache));
    }

    /**
     * 删除异步影像
     */
    @RequiresPermissions("uip:imImageCache:remove")
    @Log(title = "异步影像", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(imImageCacheService.deleteImImageCacheByIds(ids));
    }

}
