package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.PhoneUser;
import net.northking.iacmp.imp.service.IPhoneUserService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 移动端用户 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/phoneUser")
public class PhoneUserController extends BaseController {
    private String prefix = "uip/phoneUser";

    @Autowired
    private IPhoneUserService phoneUserService;

    @RequiresPermissions("uip:phoneUser:view")
    @GetMapping()
    public String phoneUser() {
        return prefix + "/phoneUser";
    }

    /**
     * 查询移动端用户列表
     */
    @RequiresPermissions("uip:phoneUser:list")
    @PostMapping("/list")

    public TableDataInfo list(PhoneUser phoneUser) {
        startPage();
        List<PhoneUser> list = phoneUserService.selectPhoneUserList(phoneUser);
        return getDataTable(list);
    }


    /**
     * 导出移动端用户列表
     */
    @RequiresPermissions("uip:phoneUser:export")
    @PostMapping("/export")

    public AjaxResult export(PhoneUser phoneUser) {
        List<PhoneUser> list = phoneUserService.selectPhoneUserList(phoneUser);
        ExcelUtil<PhoneUser> util = new ExcelUtil<PhoneUser>(PhoneUser.class);
        return util.exportExcel(list, "phoneUser");
    }

    /**
     * 新增移动端用户
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存移动端用户
     */
    @RequiresPermissions("uip:phoneUser:add")
    @Log(title = "移动端用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")

    public AjaxResult addSave(PhoneUser phoneUser) {
        return toAjax(phoneUserService.insertPhoneUser(phoneUser));
    }

    /**
     * 修改移动端用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        PhoneUser phoneUser = phoneUserService.selectPhoneUserById(id);
        mmap.put("phoneUser", phoneUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存移动端用户
     */
    @RequiresPermissions("uip:phoneUser:edit")
    @Log(title = "移动端用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(PhoneUser phoneUser) {
        return toAjax(phoneUserService.updatePhoneUser(phoneUser));
    }

    /**
     * 删除移动端用户
     */
    @RequiresPermissions("uip:phoneUser:remove")
    @Log(title = "移动端用户", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(phoneUserService.deletePhoneUserByIds(ids));
    }

}
