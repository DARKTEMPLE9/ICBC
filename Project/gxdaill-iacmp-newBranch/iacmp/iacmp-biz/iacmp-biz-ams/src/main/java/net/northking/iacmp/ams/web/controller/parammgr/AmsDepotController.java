package net.northking.iacmp.ams.web.controller.parammgr;


import com.netflix.discovery.converters.Auto;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsCabinet;
import net.northking.iacmp.common.bean.domain.ams.AmsDepot;
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

import java.util.*;

/**
 * 库房 信息操作处理
 *
 * @author wxy
 * @date 2019-08-08
 */
@Controller
@RequestMapping("/param/amsDepot")
public class AmsDepotController extends BaseController {
    private String prefix = "param/amsDepot";

    @Autowired
    private IAmsDepotService amsDepotService;
    @Autowired
    private IAmsCabinetService amsCabinetService;
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("param:amsDepot:view")
    @GetMapping()
    public String amsDepot(ModelMap mmap) {
        Long roleId = getRoleId();
        mmap.put("roleId", roleId);
        return prefix + "/amsDepot";
    }

    /**
     * 查询库房列表
     */
    @RequiresPermissions("param:amsDepot:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsDepot amsDepot) {
        Long roleId = getRoleId();

        //根据角色设置部门类型
        if (roleId == 3L) {
            //部门库房
            amsDepot.setDepotType("10");
        } else if (roleId == 4L) {
            //总行库房
            amsDepot.setDepotType("20");
        }
        SysUser sysUser = ShiroUtils.getSysUser();
        List<String> deptList = new ArrayList<>();
        if (amsDepot.getOrgNo() != null && !"".equals(amsDepot.getOrgNo())) {
            deptList.add(amsDepot.getOrgNo());
        } else {
            //查询当前用户管理的全部部门
            deptList = sysUserService.selectAuxiliaryDeptList(sysUser);
            deptList.add(sysUser.getDeptId().toString());
        }
        startPage();
        List<AmsDepot> list = amsDepotService.selectAmsDepotList(amsDepot, deptList);
        return getDataTable(list);
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

    /**
     * 查询库房Map列表
     *
     * @param request
     * @return
     */
    @PostMapping("/depotList/{mId}/{method}")
    @ResponseBody
    public Map<String, List<AmsDepot>> depotList(AmsDepot amsDepot, @PathVariable("mId") String mId, @PathVariable("method") String method) {
        Long roleId = getRoleId();

        //根据角色设置部门类型
        if (roleId == 3L) {
            //部门库房
            amsDepot.setDepotType("10");
        } else if (roleId == 4L) {
            //总行库房
            amsDepot.setDepotType("20");
        }

        Map resultMap = new HashMap<String, List<AmsDepot>>();
        List<AmsDepot> resultList = new ArrayList<>();
        //查询当前用户管理的全部部门
        SysUser sysUser = ShiroUtils.getSysUser();
        List<String> deptList = sysUserService.selectAuxiliaryDeptList(sysUser);
        deptList.add(sysUser.getDeptId().toString());
        List<AmsDepot> amsDepotList = amsDepotService.selectAmsDepotList(amsDepot, deptList);
        //模块判断
        if ("param".equals(mId)) {
            //方法判断
            if ("add".equals(method)) {
                for (AmsDepot obj : amsDepotList) {
                    AmsCabinet amsCabinet = new AmsCabinet();
                    amsCabinet.setDepId(obj.getId());
                    List<AmsCabinet> cabList = amsCabinetService.selectAmsCabinetList(amsCabinet);
                    //已满未满判断
                    if (cabList.size() < obj.getAllNum().intValue()) {
                        resultList.add(obj);
                    }
                }
            } else if ("edit".equals(method)) {
                for (AmsDepot obj : amsDepotList) {
                    AmsCabinet amsCabinet = new AmsCabinet();
                    amsCabinet.setDepId(obj.getId());
                    List<AmsCabinet> cabList = amsCabinetService.selectAmsCabinetList(amsCabinet);
                    //已满未满判断
                    if (cabList.size() <= obj.getAllNum().intValue()) {
                        resultList.add(obj);
                    }
                }
            }
        } else if ("archManage".equals(mId) && "add".equals(method)) {
            for (AmsDepot obj : amsDepotList) {
                resultList.add(obj);
            }
        }

        resultMap.put("depotList", resultList);
        return resultMap;
    }

    /**
     * 导出库房列表
     */
    @RequiresPermissions("param:amsDepot:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsDepot amsDepot) {
        Long roleId = getRoleId();

        //根据角色设置部门类型
        if (roleId == 3L) {
            //部门库房
            amsDepot.setDepotType("10");
        } else if (roleId == 4L) {
            //总行库房
            amsDepot.setDepotType("20");
        }

        List<AmsDepot> list = amsDepotService.selectAmsDepotList(amsDepot);
        ExcelUtil<AmsDepot> util = new ExcelUtil<>(AmsDepot.class);
        return util.exportExcel(list, "amsDepot");
    }

    /**
     * 新增库房
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        Long roleId = getRoleId();

        mmap.put("roleId", roleId.toString());
        return prefix + "/add";
    }

    /**
     * 新增保存库房
     */
    @RequiresPermissions("param:amsDepot:add")
    @Log(title = "库房", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsDepot amsDepot) {
        SysUser sysUser = ShiroUtils.getSysUser();
        amsDepot.setOrgNo(sysUser.getDeptId().toString());
        amsDepot.setOrgName(sysUser.getDept().getDeptName());
        return toAjax(amsDepotService.insertAmsDepot(amsDepot));
    }

    /**
     * 修改库房
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsDepot amsDepot = amsDepotService.selectAmsDepotById(id);
        mmap.put("amsDepot", amsDepot);
        return prefix + "/edit";
    }

    /**
     * 修改保存库房
     */
    @RequiresPermissions("param:amsDepot:edit")
    @Log(title = "库房", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsDepot amsDepot) {
        return toAjax(amsDepotService.updateAmsDepot(amsDepot));
    }

    /**
     * 删除库房
     */
    @RequiresPermissions("param:amsDepot:remove")
    @Log(title = "库房", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        AmsCabinet amsCabinet = new AmsCabinet();
        amsCabinet.setDepId(ids);
        List<AmsCabinet> cabinetList = amsCabinetService.selectAmsCabinetList(amsCabinet);

        if (cabinetList != null && !cabinetList.isEmpty()) {
            String depName = cabinetList.get(0).getDepName();
            String count = String.valueOf(cabinetList.size());
            return AjaxResult.error("【" + depName + "】库房下还有" + count + "个库柜！请先删除库柜。");
        }
        return toAjax(amsDepotService.deleteAmsDepotByIds(ids));
    }

    /**
     * 查看库柜详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap) {
        AmsDepot amsDepot = amsDepotService.selectAmsDepotById(id);
        mmap.put("amsDepot", amsDepot);
        return prefix + "/view";
    }
}
