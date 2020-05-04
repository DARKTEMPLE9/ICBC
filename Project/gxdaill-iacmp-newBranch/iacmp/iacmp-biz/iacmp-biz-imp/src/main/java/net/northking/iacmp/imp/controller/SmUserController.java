package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmUser;
import net.northking.iacmp.imp.service.ISmUserService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 用户 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/smUser")
public class SmUserController extends BaseController {
    private String prefix = "uip/smUser";

    @Autowired
    private ISmUserService smUserService;

    @PostMapping("/selectSmUserById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public SmUser selectSmUserById(@RequestBody String usreId) {
        return smUserService.selectSmUserById(usreId);
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/list")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<SmUser> list(@RequestBody HashMap<String, Object> map) {
        List<SmUser> list = smUserService.selectSmUserList(map);
        return list;
    }

    /**
     * 查询用户列表by id，同时查询机构name
     */
    @PostMapping("/userById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public HashMap selectUserAndOrgNameByUserId(@RequestBody String userId) {
        HashMap<String, Object> map;
        map = smUserService.selectUserAndOrgNameByUserId(userId);
        return map;
    }

    /**
     * 查询用户总数
     *
     * @return
     */
    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer userCount(@RequestBody HashMap map) {
        Integer count = 0;
        count = smUserService.selectSmUserCount(map);
        return count;
    }

    @PostMapping("/findByUserCode")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<SmUser> findByUserCode(@RequestBody String userCode) {
        List<SmUser> list = smUserService.findByUserCode(userCode);
        return list;
    }

    /**
     * 导出用户列表
     */
    @PostMapping("/export")

    public AjaxResult export(SmUser smUser) {
//    	List<SmUser> list = smUserService.selectSmUserList(smUser);
//        ExcelUtil<SmUser> util = new ExcelUtil<SmUser>(SmUser.class);
//        return util.exportExcel(list, "smUser");
        return null;
    }

    /**
     * 新增保存用户
     */
    @PostMapping("/insertUser")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer insertUser(@RequestBody SmUser smUser) {
        Integer res = smUserService.insertSmUser(smUser);
        return res;
    }

    /**
     * 修改用户
     */
    @PostMapping("/updateUser")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer updateUser(@RequestBody SmUser smUser) {
        Integer res = smUserService.updateSmUser(smUser);
        return res;
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("uip:smUser:edit")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(SmUser smUser) {
        return toAjax(smUserService.updateSmUser(smUser));
    }

    /**
     * 删除用户
     */
    @PostMapping("/deleteById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer deleteById(@RequestBody String id) {
        return smUserService.deleteSmUserById(id);
    }

}
