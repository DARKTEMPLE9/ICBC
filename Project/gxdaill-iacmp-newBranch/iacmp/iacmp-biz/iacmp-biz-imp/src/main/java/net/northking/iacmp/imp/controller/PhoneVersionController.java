package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.PhoneVersion;
import net.northking.iacmp.imp.service.IPhoneVersionService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 移动端版本 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/phoneVersion")
public class PhoneVersionController extends BaseController {
    private String prefix = "uip/phoneVersion";

    @Autowired
    private IPhoneVersionService phoneVersionService;

    @RequiresPermissions("uip:phoneVersion:view")
    @GetMapping()
    public String phoneVersion() {
        return prefix + "/phoneVersion";
    }

    /**
     * 查询移动端版本列表
     */
    @RequiresPermissions("uip:phoneVersion:list")
    @PostMapping("/list")

    public TableDataInfo list(PhoneVersion phoneVersion) {
        startPage();
        List<PhoneVersion> list = phoneVersionService.selectPhoneVersionList(phoneVersion);
        return getDataTable(list);
    }


    /**
     * 导出移动端版本列表
     */
    @RequiresPermissions("uip:phoneVersion:export")
    @PostMapping("/export")

    public AjaxResult export(PhoneVersion phoneVersion) {
        List<PhoneVersion> list = phoneVersionService.selectPhoneVersionList(phoneVersion);
        ExcelUtil<PhoneVersion> util = new ExcelUtil<PhoneVersion>(PhoneVersion.class);
        return util.exportExcel(list, "phoneVersion");
    }

    /**
     * 新增移动端版本
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存移动端版本
     */
    @RequiresPermissions("uip:phoneVersion:add")
    @Log(title = "移动端版本", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(PhoneVersion phoneVersion) {
        return toAjax(phoneVersionService.insertPhoneVersion(phoneVersion));
    }

    /**
     * 修改移动端版本
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        PhoneVersion phoneVersion = phoneVersionService.selectPhoneVersionById(id);
        mmap.put("phoneVersion", phoneVersion);
        return prefix + "/edit";
    }

    /**
     * 修改保存移动端版本
     */
    @RequiresPermissions("uip:phoneVersion:edit")
    @Log(title = "移动端版本", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(PhoneVersion phoneVersion) {
        return toAjax(phoneVersionService.updatePhoneVersion(phoneVersion));
    }

    /**
     * 删除移动端版本
     */
    @RequiresPermissions("uip:phoneVersion:remove")
    @Log(title = "移动端版本", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(phoneVersionService.deletePhoneVersionByIds(ids));
    }

}
