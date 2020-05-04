package net.northking.iacmp.ams.web.controller.archmanage;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBoxVO;
import net.northking.iacmp.common.bean.vo.ams.AmsCabinetVO;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.server.service.*;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * 档案入库
 *
 * @author weizhe.fan
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/archManage/archStore")
public class ArchStoreController extends BaseController {
    private String prefix = "archManage/archStore";

    @Autowired
    private IAmsBillService amsBillService;

    @Autowired
    private IAmsBoxService amsBoxService;

    @Autowired
    private IAmsCabinetService amsCabinetService;

    @Autowired
    private IAmsDepotService iAmsDepotService;

    @Autowired
    private IAmsArchivesService amsArchivesService;

    @Autowired
    private IAmsBatchService amsBatchService;

    @Autowired
    private IAmsDepotService amsDepotService;
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("archManage:archStore:view")
    @GetMapping()
    public String amsBox() {
        return prefix + "/archStore";
    }

    /**
     * 查询档案入库列表 条件：status=10
     */
    @RequiresPermissions("archManage:archStore:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBoxVO amsBox) {
        //查询当前用户管理全部部门
        List<String> deptList = new ArrayList<>();
        SysUser sysUser = ShiroUtils.getSysUser();
        deptList.add(sysUser.getDeptId().toString());
        String auxiliaryDept = sysUser.getAuxiliaryDept();
        if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {
            String[] auxiliaryDeptArr = auxiliaryDept.split(",");
            for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                deptList.add(auxiliaryDeptArr[i]);
            }
        }
        startPage();
        Long roleId = getRoleId();

        //根据角色设置部门类型
        if (roleId == 3L) {
            //部门库房
            amsBox.setDepType("10");
        } else if (roleId == 4L) {
            //总行库房
            amsBox.setDepType("20");
        }
        amsBox.setBoxYear(amsBox.getBoxYear().split("-")[0]);
        List<AmsBox> list = amsBoxService.selectArchStoreList(amsBox, deptList);
        return getDataTable(list);
    }

    /**
     * 导出档案类型列表
     */
    @RequiresPermissions("archManage:archStore:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBill amsBill) {
        List<AmsBill> list = amsBillService.selectAmsBillList(amsBill);
        ExcelUtil<AmsBill> util = new ExcelUtil<>(AmsBill.class);
        return util.exportExcel(list, "archStore");
    }

    /**
     * 跳转到查询库页面
     */
    @GetMapping("/jumpToCab")
    public String add(@RequestParam("ids") String ids, ModelMap mmap) {
        mmap.put("ids", ids);
        return prefix + "/cabs";
    }

    /**
     * @Author: weizhe.fan
     * @Description:查询库
     * @CreateDate: 16:37.2019/8/19
     */
    @PostMapping("/queryCab")
    @ResponseBody
    public TableDataInfo queryCab(AmsCabinet amsCabinet, HttpServletRequest request) {
        String[] ids = request.getParameter("ids").split(",");
        Set<String> depIds = new HashSet<>();
        List<AmsCabinetVO> resultList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            AmsBox amsBox = amsBoxService.selectAmsBoxById(ids[i]);
            depIds.add(amsBox.getDepId());
        }
        startPage();
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
        //库房状态 未满：0
        amsCabinet.setDepStatus("0");
        for (String depId : depIds) {
            amsCabinet.setDepId(depId);
            List<AmsCabinetVO> amsCabinets = amsCabinetService.selectAmsCabList(amsCabinet, deptList);
            for (AmsCabinetVO cabinet : amsCabinets) {
                resultList.add(cabinet);
            }
        }
        return getDataTable(resultList);
    }

    /**
     * 入库
     */
    @RequiresPermissions("archManage:archStore:intoCab")
    @PostMapping("/intoCab")
    @ResponseBody
    public AjaxResult confirm(String ids, String id) {
        // 选择一个库房将盒入库
        AmsCabinet amsCab = amsCabinetService.selectAmsCabinetById(id);
        // 将要入库的盒id
        String[] boxids = ids.split(",");
        // 遍历盒ids,更新盒
        for (int i = 0; i < boxids.length; i++) {
            AmsBox amsBox = amsBoxService.selectAmsBoxById(boxids[i]);
            amsBox.setDepId(amsCab.getDepId());
            amsBox.setDepName(amsCab.getDepName());
            amsBox.setCabId(amsCab.getId());
            amsBox.setCabName(amsCab.getName());
            // 入库时间
            amsBox.setIncabTime(new Timestamp(System.currentTimeMillis()));
            // 未满盒入库，状态为5；已满盒入库，状态为6；
            if ("10".equals(amsBox.getStatus())) {
                amsBox.setStatus(Constants.NOT_FULL_INTOCAB);
            } else if ("20".equals(amsBox.getStatus())) {
                amsBox.setStatus(Constants.FULL_INTOCAB);
            }
            amsBoxService.updateAmsBox(amsBox);
        }
        // 更新档案著录和登记信息
        for (String boxId : boxids) {
            AmsArchivesVO amsArchives = new AmsArchivesVO();
            amsArchives.setBoxCode(boxId);
            List<AmsArchives> arcList = amsArchivesService.selectAmsArchivesList(amsArchives);
            for (AmsArchives amsArc : arcList) {
                amsArc.setDepotId(amsCab.getDepId());
                amsArc.setDepotNo(amsCab.getDepCode());
                amsArc.setDepotName(amsCab.getName());
                amsArc.setCabintId(amsCab.getId());
                amsArc.setCabintNo(amsCab.getCode());
                amsArc.setCabintName(amsCab.getName());
                amsArc.setDepotType(amsCab.getDepType());
                amsArc.setStatus(Constants.ALREADY_PUT_STORAGE);
                //设置接收时间
                AmsBatch amsBatch = amsBatchService.selectAmsBatchById(amsArc.getBatchId());
                amsArc.setReceiveTime(amsBatch.getReceiveTime());
                amsArchivesService.updateAmsArchives(amsArc);
            }
            AmsBatch sBatch = new AmsBatch();
            sBatch.setBoxId(boxId);
            List<AmsBatch> batchList = amsBatchService.selectAmsBatchList(sBatch);
            for (AmsBatch amsBatch : batchList) {
                amsBatch.setStatus(Constants.ALREADY_PUT_STORAGE);
                amsBatchService.updateAmsBatch(amsBatch);
            }
        }
        return toAjax(1);
    }

    /**
     * 跳转到脊背签页面
     */
    @GetMapping("/jumpToBackLabel/{boxId}")
    public String jumpToBackLabel(@PathVariable("boxId") String boxId, ModelMap modelMap) {
        AmsBox amsBox = amsBoxService.selectAmsBoxById(boxId);
        modelMap.addAttribute("amsBox", amsBox);
        //全宗号
        String recGroupNum = SysConfigInitParamsUtils.getConfig(Constants.RECORD_GROUP_NUMBER);
        modelMap.put("recGroupNum", recGroupNum);
        return prefix + "/backLabel";
    }

    /**
     * 打印预览
     *
     * @return"
     */
    @RequiresPermissions("archManage:archStore:printLook")
    @GetMapping("/printLook")
    public String printLook(String ids, ModelMap mmap) {

        SysUser loginUser = ShiroUtils.getSysUser();
        List<AmsArchives> list = amsArchivesService.selectAmsArchivesByIds(ids);
        mmap.put("printList", list);
        mmap.put("loginUser", loginUser);
        return prefix + "/printLook";
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
     * 编辑盒内信息
     */
    @GetMapping("/inBox/{id}")
    public String editbox(@PathVariable("id") String boxId, ModelMap modelMap) {
        SysUser loginUser = ShiroUtils.getSysUser();

        AmsBox amsBox = amsBoxService.selectAmsBoxById(boxId);
        AmsArchivesVO archives = new AmsArchivesVO();
        archives.setBoxCode(boxId);
        List<AmsArchives> amsArchives = findArchivesByRole(loginUser, archives);
        modelMap.addAttribute("list", amsArchives);
        modelMap.addAttribute("amsBox", amsBox);
        modelMap.addAttribute("boxId", boxId);

        return prefix + "/edit";
    }

    /**
     * 根据角色查询档案
     *
     * @param loginUser
     * @param archives
     * @return
     */
    private List<AmsArchives> findArchivesByRole(SysUser loginUser, AmsArchivesVO archives) {
        //部门档案列表
        List<AmsArchives> amsArchivesDept = new ArrayList<>();
        //行档案列表
        List<AmsArchives> amsArchivesBranch = new ArrayList<>();

        List<AmsArchives> amsArchives = amsArchivesService.selectAmsArchivesList(archives);
        for (AmsArchives obj : amsArchives) {
            AmsBatch amsBatch = amsBatchService.selectAmsBatchById(obj.getBatchId());
            //判断档案是否为行档案 0：部门档案 1：行档案
            if (amsBatch != null) {
                if (null != amsBatch.getArcHasMoveBank() && !"".equals(amsBatch.getArcHasMoveBank())) {
                    if (amsBatch.getArcHasMoveBank().equals("0")) {
                        amsArchivesDept.add(obj);
                    } else if (amsBatch.getArcHasMoveBank().equals("1")) {
                        amsArchivesBranch.add(obj);
                    }
                }
            }
        }

        List<SysRole> roleList = loginUser.getRoles();
        Long roleId = 0L;
        if (roleList != null && !roleList.isEmpty()) {
            List roles = new ArrayList();
            if (roleList != null && !roleList.isEmpty()) {
                for (SysRole role : roleList) {
                    roles.add(role.getRoleId());
                }
            }
            roles.remove(23L);
            roleId = Long.valueOf(Collections.max(roles).toString());
        }

        //角色判断 部门档案管理员 3L/行档案管理员 4L
        if (roleId == 3L) {
            return amsArchivesDept;
        } else if (roleId == 4L) {
            return amsArchivesBranch;
        }

        return new ArrayList<>();
    }

    /**
     * @Author: weizhe.fan
     * @Description:查询盒内档案列表
     * @CreateDate: 14:38.2019/8/12
     */
    @RequiresPermissions("archManage:archStore:archList")
    @PostMapping("/editBoxArch")
    @ResponseBody
    public TableDataInfo editBoxArch(AmsArchivesVO archives, String boxId) {
        SysUser loginUser = ShiroUtils.getSysUser();

        archives.setBoxCode(boxId);
        startPage();
        List<AmsArchives> amsArchives = findArchivesByRole(loginUser, archives);
        return getDataTable(amsArchives);
    }

    /**
     * 新增保存档案类型
     */
    @RequiresPermissions("archManage:archStore:add")
    @Log(title = "档案类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBill amsBill) {
        return toAjax(amsBillService.insertAmsBill(amsBill));
    }

    /**
     * 修改档案类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBill amsBill = amsBillService.selectAmsBillById(id);
        mmap.put("amsBill", amsBill);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案类型
     */
    @RequiresPermissions("archManage:archStore:edit")
    @Log(title = "档案类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBill amsBill) {
        return toAjax(amsBillService.updateAmsBill(amsBill));
    }

    /**
     * 删除档案类型
     */
    @RequiresPermissions("archManage:archStore:remove")
    @Log(title = "档案类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBillService.deleteAmsBillByIds(ids));
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
}
