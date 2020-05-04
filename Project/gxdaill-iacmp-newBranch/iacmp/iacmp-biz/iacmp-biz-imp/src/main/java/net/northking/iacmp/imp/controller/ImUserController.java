package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImUser;
import net.northking.iacmp.imp.service.IImUserService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imUser")
public class ImUserController extends BaseController {
    private String prefix = "uip/imUser";

    @Autowired
    private IImUserService imUserService;

    @RequiresPermissions("uip:imUser:view")
    @GetMapping()
    public String imUser() {
        return prefix + "/imUser";
    }

    /**
     * 查询客户列表
     */
    @PostMapping("/list")
    public List<ImUser> list(@RequestBody ImUser imUser) {
        List<ImUser> list = imUserService.selectImUserList(imUser);
        return list;
    }

    /**
     * 导出客户列表
     */
    @RequiresPermissions("uip:imUser:export")
    @PostMapping("/export")

    public AjaxResult export(ImUser imUser) {
        List<ImUser> list = imUserService.selectImUserList(imUser);
        ExcelUtil<ImUser> util = new ExcelUtil<ImUser>(ImUser.class);
        return util.exportExcel(list, "imUser");
    }

    /**
     * 新增客户
     */
	/*@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}*/

    /**
     * 新增保存客户
     */
    //@RequiresPermissions("uip:imUser:add")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ImUser imUser) {
        return toAjax(imUserService.insertImUser(imUser));
    }

    /**
     * 修改客户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ImUser imUser = imUserService.selectImUserById(id);
        mmap.put("imUser", imUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户
     */
    //@RequiresPermissions("uip:imUser:edit")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult editSave(@RequestBody ImUser imUser) {
        return toAjax(imUserService.updateImUser(imUser));
    }

    /**
     * 删除客户
     */
    @RequiresPermissions("uip:imUser:remove")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(imUserService.deleteImUserByIds(ids));
    }

}
