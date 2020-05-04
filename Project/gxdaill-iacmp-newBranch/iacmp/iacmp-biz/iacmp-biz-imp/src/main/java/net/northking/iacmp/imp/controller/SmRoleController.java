package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmRole;
import net.northking.iacmp.imp.service.ISmRoleService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户权限 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/smRole")
public class SmRoleController extends BaseController {
    private String prefix = "uip/smRole";

    @Autowired
    private ISmRoleService smRoleService;

    @RequiresPermissions("uip:smRole:view")
    @GetMapping()
    public String smRole() {
        return prefix + "/smRole";
    }

    /**
     * 查询用户权限列表
     */
    @PostMapping("/queryRolesForUser")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<HashMap<String, Object>> queryRolesForUser() {
        List<HashMap<String, Object>> list = smRoleService.queryRolesForUser();
        return list;
    }

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    @PostMapping("/queryRolesByUser")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<SmRole> queryRolesByUser(@RequestBody String userId) {
        List<SmRole> list = smRoleService.queryRolesByUser(userId);
        return list;
    }

    /**
     * all角色查询
     *
     * @param map
     * @return
     */
    @PostMapping("/list")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<SmRole> list(@RequestBody HashMap<String, Object> map) {
        List<SmRole> list = smRoleService.selectSmRoleList(map);
        return list;
    }

    /**
     * 查询角色总数
     *
     * @return
     */
    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer roleCount(@RequestBody HashMap map) {
        Integer count = 0;
        count = smRoleService.selectSmRoleCount(map);
        return count;
    }
    /**
     * 导出用户权限列表
     */
    //   @PostMapping("/export")

//    public AjaxResult export(SmRole smRole)
//    {
//    	List<SmRole> list = smRoleService.selectSmRoleList(smRole);
//        ExcelUtil<SmRole> util = new ExcelUtil<SmRole>(SmRole.class);
//        return util.exportExcel(list, "smRole");
//    }

    /**
     * 新增时查询code是否已存在
     */
    @PostMapping("/queryRoleByCode")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<SmRole> queryRoleByCode(@RequestBody String code) {
        List<SmRole> list = smRoleService.queryRoleByCode(code);
        return list;
    }

    @PostMapping("/queryRoleById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public HashMap queryRoleById(@RequestBody String roleId) {
        HashMap<String, Object> map;
        map = smRoleService.queryRoleById(roleId);
        return map;
    }

    /**
     * 新增用户权限
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增角色
     */
    @PostMapping("/addRole")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer addRole(@RequestBody HashMap map) {
        Integer result = smRoleService.addRole(map);
        return result;
    }

    /**
     * 修改用户权限
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        SmRole smRole = smRoleService.selectSmRoleById(id);
        mmap.put("smRole", smRole);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户权限
     */
    @RequiresPermissions("uip:smRole:edit")
    @Log(title = "用户权限", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")

    public AjaxResult editSave(SmRole smRole) {
        return toAjax(smRoleService.updateSmRole(smRole));
    }

    /**
     * 删除用户权限
     */

    @PostMapping("/deleteByRoleId")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer deleteByRoleId(@RequestBody String roleId) {
        return smRoleService.deleteSmRoleById(roleId);
    }


    @RequiresPermissions("uip:smRole:remove")
    @Log(title = "用户权限", businessType = BusinessType.DELETE)
    @PostMapping("/remove")

    public AjaxResult remove(String ids) {
        return toAjax(smRoleService.deleteSmRoleByIds(ids));
    }

}
