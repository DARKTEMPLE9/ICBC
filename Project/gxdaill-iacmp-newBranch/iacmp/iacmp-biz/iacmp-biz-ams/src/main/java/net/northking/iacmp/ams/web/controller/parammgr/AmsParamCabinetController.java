package net.northking.iacmp.ams.web.controller.parammgr;


import com.netflix.discovery.converters.Auto;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsCabinet;
import net.northking.iacmp.common.bean.domain.ams.AmsDepot;
import net.northking.iacmp.common.bean.vo.ams.AmsCabinetVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IAmsCabinetService;
import net.northking.iacmp.server.service.IAmsDepotService;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * 库柜 信息操作处理
 *
 * @author wxy
 * @date 2019-08-08
 */
@Controller
@RequestMapping("/param/amsCabinet")
public class AmsParamCabinetController extends BaseController {
    private String prefix = "param/amsCabinet";

    @Autowired
    private IAmsCabinetService amsCabinetService;
    @Autowired
    private IAmsDepotService amsDepotService;
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("param:amsCabinet:view")
    @GetMapping()
    public String amsCabinet() {
        return prefix + "/amsCabinet";
    }

    /**
     * 查询库柜列表
     */
    @RequiresPermissions("param:amsCabinet:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsCabinet amsCabinet) {
        Long roleId = getRoleId();
        //根据角色设置部门类型
        if (roleId == 3L) {
            //部门库房
            amsCabinet.setDepType("10");
        } else if (roleId == 4L) {
            //总行库房
            amsCabinet.setDepType("20");
        }

        //查询当前用户管理的全部部门
        SysUser sysUser = ShiroUtils.getSysUser();
        List<String> deptList = sysUserService.selectAuxiliaryDeptList(sysUser);
        deptList.add(sysUser.getDeptId().toString());
        startPage();
        List<AmsCabinetVO> list = amsCabinetService.selectAmsCabList(amsCabinet, deptList);
        return getDataTable(list);
    }

    @PostMapping("/getCabList")
    @ResponseBody
    public TableDataInfo getCabList(AmsCabinet amsCabinet) {
        Long roleId = getRoleId();

        //根据角色设置部门类型
        if (roleId == 3L) {
            //部门库房
            amsCabinet.setDepType("10");
        } else if (roleId == 4L) {
            //总行库房
            amsCabinet.setDepType("20");
        }
        //库房状态 未满：0
        amsCabinet.setDepStatus("0");

        List<AmsCabinet> cabList = amsCabinetService.selectAmsCabinetList(amsCabinet);

        return getDataTable(cabList);
    }

    /**
     * 导出库柜列表
     */
    @RequiresPermissions("param:amsCabinet:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsCabinet amsCabinet) {
        List<AmsCabinet> list = amsCabinetService.selectAmsCabinetList(amsCabinet);
        ExcelUtil<AmsCabinet> util = new ExcelUtil<>(AmsCabinet.class);
        return util.exportExcel(list, "amsCabinet");
    }

    /**
     * 新增库柜
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存库柜
     */
    @RequiresPermissions("param:amsCabinet:add")
    @Log(title = "库柜", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsCabinet amsCabinet) {
        AmsDepot amsDepot;
        String depId = amsCabinet.getDepId();
        amsDepot = amsDepotService.selectAmsDepotById(depId);
        amsCabinet.setDepType(amsDepot.getDepotType());
        amsCabinet.setDepStatus(amsDepot.getStatus());
        return toAjax(amsCabinetService.insertAmsCabinet(amsCabinet));
    }

    /**
     * 修改库柜
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsCabinet amsCabinet = amsCabinetService.selectAmsCabinetById(id);
        mmap.put("amsCabinet", amsCabinet);
        return prefix + "/edit";
    }

    /**
     * 修改保存库柜
     */
    @RequiresPermissions("param:amsCabinet:edit")
    @Log(title = "库柜", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsCabinet amsCabinet) {
        AmsDepot amsDepot;
        String depId = amsCabinet.getDepId();
        amsDepot = amsDepotService.selectAmsDepotById(depId);
        amsCabinet.setDepType(amsDepot.getDepotType());
        return toAjax(amsCabinetService.updateAmsCabinet(amsCabinet));
    }

    /**
     * 删除库柜
     */
    @RequiresPermissions("param:amsCabinet:remove")
    @Log(title = "库柜", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsCabinetService.deleteAmsCabinetByIds(ids));
    }

    /**
     * 查看库柜详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap) {
        AmsCabinet amsCabinet = amsCabinetService.selectAmsCabinetById(id);
        mmap.put("amsCabinet", amsCabinet);
        return prefix + "/view";
    }

    //获取角色Id
    private Long getRoleId() {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        Long roleId = 0L;
        List roles = new ArrayList();
        if (roleList != null && !roleList.isEmpty()) {
            for (SysRole role : roleList) {
                roles.add(role.getRoleId());
            }
        }
        roles.remove(23L);
        roleId = Long.valueOf(Collections.max(roles).toString());
        return roleId;
    }
}
