package net.northking.iacmp.ams.web.controller.monitor;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.system.domain.SysLogininfor;
import net.northking.iacmp.system.service.ISysLogininforService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统访问记录
 *
 * @author wxy
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {
    private String prefix = "monitor/logininfor";

    @Autowired
    private ISysLogininforService logininforService;

    @RequiresPermissions("monitor:logininfor:view")
    @GetMapping()
    public String logininfor() {
        return prefix + "/logininfor";
    }

    /**
     * 跳转选择用户页面
     *
     * @return
     */
    @GetMapping("/selectUser")
    public String selectUser(String id, ModelMap mmap) {
        mmap.put("userId", ShiroUtils.getUserId());
        return prefix + "/selectUser";
    }

    @RequiresPermissions("monitor:logininfor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysLogininfor logininfor) {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    @Log(title = "登陆日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:logininfor:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String infoIds) {
        List<SysLogininfor> sysLogininfors = new ArrayList<>();
        if (infoIds != null && !"".equals(infoIds)) {
            String[] ids = infoIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.parseLong(ids[i]);
                SysLogininfor operLog = logininforService.selectLogininforById(id);
                sysLogininfors.add(operLog);
            }
        } else {
            SysLogininfor config = new SysLogininfor();
            List<SysLogininfor> operLogs = logininforService.selectLogininforList(config);
            for (SysLogininfor sysOperLog : operLogs) {
                sysLogininfors.add(sysOperLog);
            }
        }
        ExcelUtil<SysLogininfor> util = new ExcelUtil<>(SysLogininfor.class);
        return util.exportExcel(sysLogininfors, "操作日志");
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(logininforService.deleteLogininforByIds(ids));
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        logininforService.cleanLogininfor();
        return success();
    }
}
