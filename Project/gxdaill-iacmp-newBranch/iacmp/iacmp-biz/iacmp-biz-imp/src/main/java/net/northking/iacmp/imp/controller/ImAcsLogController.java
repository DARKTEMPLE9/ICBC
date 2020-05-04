package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImAcsLog;
import net.northking.iacmp.imp.service.IImAcsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 日志 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imAcsLog")
public class ImAcsLogController extends BaseController {

    @Autowired
    private IImAcsLogService imAcsLogService;

    /**
     * 查询日志列表
     */
    @PostMapping("/list")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImAcsLog> list(@RequestBody HashMap map) {
        List<ImAcsLog> list = imAcsLogService.selectImAcsLogList(map);
        return list;
    }

    /**
     * 查询日志总数
     */
    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer count(@RequestBody HashMap map) {
        Integer res = imAcsLogService.selectAcsLogCount(map);
        return res;
    }

    @PostMapping("/imAcsLogById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public HashMap imAcsLogById(@RequestBody String id) {
        return imAcsLogService.imAcsLogById(id);
    }

    /**
     * 新增保存日志
     */
    @Log(title = "日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addSave(@RequestBody ImAcsLog imAcsLog) {
        return toAjax(imAcsLogService.insertImAcsLog(imAcsLog));
    }

    /**
     * 修改保存日志
     */
    @Log(title = "日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editSave(ImAcsLog imAcsLog) {
        return toAjax(imAcsLogService.updateImAcsLog(imAcsLog));
    }

    /**
     * 删除日志
     */
    @Log(title = "日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(String ids) {
        return toAjax(imAcsLogService.deleteImAcsLogByIds(ids));
    }

}
