package net.northking.iacmp.cms.web.controller.monitor;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.system.domain.SysOperLog;
import net.northking.iacmp.system.service.ISysOperLogService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作日志记录
 *
 * @author wxy
 */
@Controller
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController {
    private String prefix = "monitor/operlog";

    @Autowired
    private ISysOperLogService operLogService;

    @RequiresPermissions("monitor:operlog:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/operlog";
    }

    @RequiresPermissions("monitor:operlog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysOperLog operLog) {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:operlog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String operIds) {
        List<SysOperLog> sysOperLogs = new ArrayList<>();
        if (operIds != null && !"".equals(operIds)) {
            String[] ids = operIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.parseLong(ids[i]);
                SysOperLog operLog = operLogService.selectOperLogById(id);
                sysOperLogs.add(operLog);
            }
        } else {
            SysOperLog config = new SysOperLog();
            List<SysOperLog> operLogs = operLogService.selectOperLogList(config);
            for (SysOperLog sysOperLog : operLogs) {
                sysOperLogs.add(sysOperLog);
            }
        }
        ExcelUtil<SysOperLog> util = new ExcelUtil<>(SysOperLog.class);
        return util.exportExcel(sysOperLogs, "操作日志");
    }

    @RequiresPermissions("monitor:operlog:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(operLogService.deleteOperLogByIds(ids));
    }

    @RequiresPermissions("monitor:operlog:detail")
    @GetMapping("/detail/{operId}")
    public String detail(@PathVariable("operId") Long operId, ModelMap mmap) {
        mmap.put("operLog", operLogService.selectOperLogById(operId));
        return prefix + "/detail";
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:operlog:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        operLogService.cleanOperLog();
        return success();
    }
}
