package net.northking.iacmp.ams.web.controller.amsarcreport;

import java.util.List;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsSendEmail;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.server.service.IAmsSendEmailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.northking.iacmp.utils.poi.ExcelUtil;

/**
 * 邮件 信息操作处理
 *
 * @author wxy
 * @date 2019-10-25
 */
@Controller
@RequestMapping("/amsArcReportcontroller/amsSendEmail")
public class AmsSendEmailController extends BaseController {
    private String prefix = "amsArcReportcontroller/amsSendEmail";

    @Autowired
    private IAmsSendEmailService amsSendEmailService;

    @RequiresPermissions("amsArcReportcontroller:amsSendEmail:view")
    @GetMapping()
    public String amsSendEmail() {
        return prefix + "/amsSendEmail";
    }

    /**
     * 查询邮件列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsSendEmail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsSendEmail amsSendEmail) {
        startPage();
        List<AmsSendEmail> list = amsSendEmailService.selectAmsSendEmailList(amsSendEmail);
        return getDataTable(list);
    }


    /**
     * 导出邮件列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsSendEmail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsSendEmail amsSendEmail) {
        List<AmsSendEmail> list = amsSendEmailService.selectAmsSendEmailList(amsSendEmail);
        ExcelUtil<AmsSendEmail> util = new ExcelUtil<>(AmsSendEmail.class);
        return util.exportExcel(list, "amsSendEmail");
    }

    /**
     * 新增邮件
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存邮件
     */
    @RequiresPermissions("amsArcReportcontroller:amsSendEmail:add")
    @Log(title = "邮件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsSendEmail amsSendEmail) {
        return toAjax(amsSendEmailService.insertAmsSendEmail(amsSendEmail));
    }

    /**
     * 修改邮件
     */
    @GetMapping("/edit/{iD}")
    public String edit(@PathVariable("iD") Integer iD, ModelMap mmap) {
        AmsSendEmail amsSendEmail = amsSendEmailService.selectAmsSendEmailById(iD);
        mmap.put("amsSendEmail", amsSendEmail);
        return prefix + "/edit";
    }

    /**
     * 修改保存邮件
     */
    @RequiresPermissions("amsArcReportcontroller:amsSendEmail:edit")
    @Log(title = "邮件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsSendEmail amsSendEmail) {
        return toAjax(amsSendEmailService.updateAmsSendEmail(amsSendEmail));
    }

    /**
     * 删除邮件
     */
    @RequiresPermissions("amsArcReportcontroller:amsSendEmail:remove")
    @Log(title = "邮件", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsSendEmailService.deleteAmsSendEmailByIds(ids));
    }

}
