package net.northking.iacmp.ams.web.controller.archmanage;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsArchives;
import net.northking.iacmp.common.bean.domain.ams.AmsBox;
import net.northking.iacmp.common.bean.domain.ams.AmsCabinet;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBoxVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IAmsArchivesService;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.server.service.IAmsBoxService;
import net.northking.iacmp.server.service.IAmsCabinetService;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
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
 * 档案移库
 *
 * @author weizhe.fan
 * @date 2019-08-13
 */
@Controller
@RequestMapping("/archManage/moveCab")
public class MoveCabController extends BaseController {
    private String prefix = "archManage/moveCab";

    @Autowired
    private IAmsBoxService amsBoxService;

    @Autowired
    private IAmsArchivesService amsArchivesService;

    @Autowired
    private IAmsBatchService amsBatchService;

    @Autowired
    private IAmsCabinetService amsCabinetService;

    @RequiresPermissions("archManage:moveCab:view")
    @GetMapping()
    public String amsBox() {
        return prefix + "/moveCab";
    }

    /**
     * 查询档案移库列表(STATUS IN 5,6)
     */
    @RequiresPermissions("archManage:moveCab:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBoxVO amsBox) {
        Long roleId = getRoleId();

        //根据角色设置部门类型
        if (roleId == 3L) {
            //部门库房
            amsBox.setDepType("10");
        } else if (roleId == 4L) {
            //总行库房
            amsBox.setDepType("20");
        }
        //查询当前用户管理的全部部门
        SysUser sysUser = ShiroUtils.getSysUser();
        List<String> deptList = new ArrayList<>();
        deptList.add(sysUser.getDeptId().toString());
        String auxiliaryDept = sysUser.getAuxiliaryDept();
        if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//该用户有辅部门
            String[] auxiliaryDeptArr = auxiliaryDept.split(",");
            for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                deptList.add(auxiliaryDeptArr[i]);
            }
        }
        startPage();
        amsBox.setBoxYear(amsBox.getBoxYear().split("-")[0]);
        List<AmsBox> list = amsBoxService.selectMoveCabList(amsBox, deptList);
        return getDataTable(list);
    }

    /**
     * @Author: weizhe.fan
     * @Description:查询库房
     * @CreateDate: 14:04.2019/8/13
     */
    @RequiresPermissions("archManage:moveCab:queryCab")
    @PostMapping("/queryCab")
    @ResponseBody
    public TableDataInfo queryCab() {
        AmsCabinet amsCabinet = new AmsCabinet();
        startPage();
        List<AmsCabinet> amsCabinets = amsCabinetService.selectAmsCabinetList(amsCabinet);
        return getDataTable(amsCabinets);
    }

    /**
     * @Author: weizhe.fan
     * @Description:跳转到移库页面
     * @CreateDate: 14:41.2019/8/12
     */
    @GetMapping("/jumpToBoxCab")
    public String affirm(String boxId, ModelMap modelMap) {
        AmsBox amsBox = amsBoxService.selectAmsBoxById(boxId);
        modelMap.addAttribute("amsBox", amsBox);
        modelMap.addAttribute("boxId", boxId);
        return prefix + "/boxWithCab";
    }

    /**
     * @Author: weizhe.fan
     * @Description:移库
     * @CreateDate: 14:56.2019/8/13
     */
    @RequiresPermissions("archManage:moveCab:moveCab")
    @PostMapping("/moveToCab")
    @ResponseBody
    public AjaxResult moveCab(AmsBox amsBox, String cabId) {
        AmsArchivesVO amsArchives = new AmsArchivesVO();
        amsArchives.setBoxCode(amsBox.getId());
        List<AmsArchives> archivesList = amsArchivesService.selectAmsArchivesList(amsArchives);
        AmsCabinet cab = amsCabinetService.selectAmsCabinetById(cabId);
        for (AmsArchives ams : archivesList) {
            ams.setBoxCode(amsBox.getId());
            ams.setBoxName(amsBox.getName());
            ams.setDepotId(cab.getDepId());
            ams.setDepotName(cab.getDepName());
            ams.setDepotNo(cab.getDepCode());
            ams.setCabintId(cab.getId());
            ams.setCabintName(cab.getName());
            ams.setCabintNo(cab.getCode());
            amsArchivesService.updateAmsArchives(ams);
        }
        amsBox.setCabId(cab.getId());
        amsBox.setCabName(cab.getName());
        amsBox.setDepId(cab.getDepId());
        amsBox.setDepName(cab.getDepName());
        int i = amsBoxService.updateAmsBox(amsBox);
        return toAjax(i);
    }

    /**
     * 导出档案类型列表
     */
    @RequiresPermissions("archManage:moveCab:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBoxVO amsBox) {
        List<AmsBox> list = amsBoxService.selectAmsBoxList(amsBox);
        ExcelUtil<AmsBox> util = new ExcelUtil<>(AmsBox.class);
        return util.exportExcel(list, "moveCab");
    }

    /**
     * 调转到显示档案类型树页面
     *
     * @return
     */
    @GetMapping("/arcBillTree")
    public String arcBillTree() {
        return prefix + "/arcBillTree";
    }

    /**
     * 新增档案类型
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案类型
     */
    @RequiresPermissions("archManage:moveCab:add")
    @Log(title = "档案类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBox amsBox) {
        return toAjax(amsBoxService.insertAmsBox(amsBox));
    }

    /**
     * 修改档案类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBox amsBox = amsBoxService.selectAmsBoxById(id);
        mmap.put("amsBox", amsBox);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案类型
     */
    @RequiresPermissions("archManage:moveCab:edit")
    @Log(title = "档案类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBox amsBox) {
        return toAjax(amsBoxService.updateAmsBox(amsBox));
    }

    /**
     * 删除档案类型
     */
    @RequiresPermissions("archManage:moveCab:remove")
    @Log(title = "档案类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBoxService.deleteAmsBoxByIds(ids));
    }

    //获取角色Id
    private Long getRoleId() {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        Long roleId = 0L;
        List<Long> roleIds = new ArrayList<>();
        for (SysRole sysRole : roleList) {
            roleIds.add(sysRole.getRoleId());
        }
        roleIds.remove(23L);
        roleId = Collections.max(roleIds);
//		if (roleList != null && !roleList.isEmpty()) {
//			Integer[] arr = new Integer[roleList.size()];
//			for (int i = 0; i < roleList.size(); i++) {
//				arr[i] = roleList.get(i).getRoleId().intValue();
//			}
//			Arrays.sort(arr);
//			//获取最高权限角色Id
//			Integer sysRoleId = arr[(roleList.size() - 1)];
//			//角色Id
//			roleId = Long.valueOf(sysRoleId.longValue());
//		}
        return roleId;
    }

    /**
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/selectCabinet/{id}")
    public String selectCabinet(@PathVariable("id") String id, ModelMap mmap) {
        mmap.put("depId", id);
        return prefix + "/selectCabinet";
    }
}
