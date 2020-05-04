package net.northking.iacmp.ams.web.controller.archmanage;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsArchives;
import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.common.bean.domain.ams.AmsBox;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBoxVO;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.server.service.IAmsArchivesService;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.server.service.IAmsBoxService;
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
 * 档案盒管理
 *
 * @author weizhe.fan
 * @date 2019-08-12
 */
@Controller
@RequestMapping("/archManage/archBox")
public class ArchBoxManageController extends BaseController {

    private String prefix = "archManage/archBoxManage";
    @Autowired
    private IAmsBoxService amsBoxService;
    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private ISysUserService sysUserService;


    @RequiresPermissions("archManage:archBox:view")
    @GetMapping()
    public String amsBox() {
        return prefix + "/archBoxManage";
    }

    /**
     * 查询档案盒列表(档案盒已满parId=01 未满 parId=00)
     */
    @RequiresPermissions("archManage:archBox:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBoxVO amsBox) {
        //查询当前用户管理的全部部门
        SysUser sysUser = ShiroUtils.getSysUser();
        List<String> deptList = sysUserService.selectAuxiliaryDeptList(sysUser);
        deptList.add(sysUser.getDeptId().toString());
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
        List<AmsBox> resultList = new ArrayList<>();
        if ("10".equals(amsBox.getStatus())) {
            //未入库 未满
            List<AmsBox> tmpEmptyBoxes = amsBoxService.selectAmsBoxList(amsBox, deptList);
            for (AmsBox box : tmpEmptyBoxes) {
                resultList.add(box);
            }
            //已入库 未满
            amsBox.setStatus("5");
            List<AmsBox> emptyBoxes = amsBoxService.selectAmsBoxList(amsBox, deptList);
            for (AmsBox box : emptyBoxes) {
                resultList.add(box);
            }
        } else if ("20".equals(amsBox.getStatus())) {
            //未入库 已满
            List<AmsBox> tmpFullBoxes = amsBoxService.selectAmsBoxList(amsBox, deptList);
            for (AmsBox box : tmpFullBoxes) {
                resultList.add(box);
            }
            //已入库 已满
            amsBox.setStatus("6");
            List<AmsBox> fullBoxes = amsBoxService.selectAmsBoxList(amsBox, deptList);
            for (AmsBox box : fullBoxes) {
                resultList.add(box);
            }
        } else {
            resultList = amsBoxService.selectAmsBoxList(amsBox, deptList);
        }

        return getDataTable(resultList);
    }

    /**
     * @Author: weizhe.fan
     * @Description:查询详情
     * @CreateDate: 15:30.2019/8/5
     */
    @RequiresPermissions("archManage:archBox:detail")
    @GetMapping("/{id}")
    public String queryOne(@PathVariable String id, ModelMap modelMap) {
        AmsBox amsBox = amsBoxService.selectAmsBoxById(id);
        modelMap.addAttribute("amsBox", amsBox);
        return prefix + "/detail";
    }

    /**
     * @Author: weizhe.fan
     * @Description:跳转到盒内档案列表
     * @CreateDate: 14:41.2019/8/12
     */
    @GetMapping("/jumpToArchList")
    public String affirm(String boxId, ModelMap modelMap) {
        SysUser loginUser = ShiroUtils.getSysUser();
        AmsBox amsBox = amsBoxService.selectAmsBoxById(boxId);
        AmsArchivesVO archives = new AmsArchivesVO();
        archives.setBoxCode(boxId);
        List<AmsArchives> amsArchives = findArchivesByRole(loginUser, archives);
        modelMap.addAttribute("amsBox", amsBox);
        modelMap.addAttribute("boxId", boxId);
        modelMap.addAttribute("list", amsArchives);
        return prefix + "/boxArchList";
    }

    /**
     * @Author: weizhe.fan
     * @Description:查询盒内档案列表
     * @CreateDate: 14:38.2019/8/12
     */
    @RequiresPermissions("archManage:archBox:archList")
    @PostMapping("/queryBoxArch")
    @ResponseBody
    public TableDataInfo queryBoxArchList(AmsArchivesVO archives, String boxId) {
        SysUser loginUser = ShiroUtils.getSysUser();

        archives.setBoxCode(boxId);
        startPage();
        List<AmsArchives> amsArchives = findArchivesByRole(loginUser, archives);
        return getDataTable(amsArchives);
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
            if (amsBatch != null) {
                //判断档案是否为行档案 0：部门档案 1：行档案
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
        List<Long> roleIds = new ArrayList<>();
        for (SysRole sysRole : roleList) {
            roleIds.add(sysRole.getRoleId());
        }
        roleIds.remove(23L);
        roleId = Collections.max(roleIds);
//        if(roleList != null && !roleList.isEmpty()){
//            Integer[] arr = new Integer[roleList.size()];
//            for(int i = 0; i < roleList.size(); i++){
//                arr[i] = roleList.get(i).getRoleId().intValue();
//            }
//            Arrays.sort(arr);
//            //获取最高权限角色Id
//            Integer sysRoleId = arr[(roleList.size()-1)];
//            //角色Id
//            roleId = Long.valueOf(sysRoleId.longValue());
//        }

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
     * @Description:保存提交
     * @CreateDate: 19:54.2019/8/12
     */
    @RequiresPermissions("archManage:archBox:updateCBS")
    @PostMapping("/updateCBS")
    @ResponseBody
    public AjaxResult updateCodeBoxNumStatus(String boxId, String boxStatus, String boxCode, String archIds, Integer[] boxNum) {
        //判断是否超过最大值
        try {
            for (int i = 0; i < boxNum.length; i++) {
                if (Integer.valueOf(boxNum[i]) > 2147483647) {
                    return toAjax(0);
                }
            }
        } catch (NumberFormatException e) {
            logger.error("数字格式化失败", e.getMessage());
            return toAjax(0);
        }

        int count = 0;
        AmsBox amsBox = amsBoxService.selectAmsBoxById(boxId);
        amsBox.setStatus(boxStatus);
        amsBox.setCode(boxCode);
        int a = amsBoxService.updateAmsBox(amsBox);
        if (archIds != null && !"".equals(archIds)) {
            String[] split = archIds.split(",");
            for (int i = 0; i < split.length; i++) {
                AmsArchives amsArchives = new AmsArchives();
                amsArchives.setId(split[i]);
                amsArchives.setBoxNum(boxNum[i]);
                int j = amsArchivesService.updateAmsArchives(amsArchives);
                count += j;
            }
        }
        return toAjax(archIds == "" ? a : count);
    }

    /**
     * @Author: weizhe.fan
     * @Description:移出档案
     * @CreateDate: 20:24.2019/8/12
     */
    @RequiresPermissions("archManage:archBox:moveOut")
    @PostMapping("/moveOut")
    @ResponseBody
    public AjaxResult moveOutBox(String archIds) {
        int count = 0;
        String[] split = archIds.split(",");
        AmsArchives archives = amsArchivesService.selectAmsArchivesById(split[0]);
        String boxCode = archives.getBoxCode();
        AmsArchivesVO archives1 = new AmsArchivesVO();
        archives1.setBoxCode(boxCode);
        List<AmsArchives> amsArchives1 = amsArchivesService.selectAmsArchivesList(archives1);
        int boxArcNum = amsArchives1.size();
        for (String archId : split) {
            AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(archId);
            AmsBatch amsBatch = amsBatchService.selectAmsBatchById(amsArchives.getBatchId());
            amsBatch.setBoxId("");
            amsBatch.setBoxOpCode("");
            amsBatch.setBoxOpName("");
            amsBatch.setBoxOrgCode("");
            amsBatch.setBoxOrgName("");
            amsBatch.setStatus(Constants.WAIT_PUT_BOX);
            int i = amsBatchService.updateAmsBatch(amsBatch);
            count += i;
        }
        amsArchivesService.deleteAmsArchivesByIds(archIds);
        if (boxArcNum < 1) {
            AmsBox amsBox = new AmsBox();
            amsBox.setId(boxCode);
            amsBox.setStatus(Constants.AVAILABLE_BOX);
            amsBoxService.updateAmsBox(amsBox);
        }
        return toAjax(count);
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
     * 导出档案类型列表
     */
    @RequiresPermissions("archManage:archBox:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBoxVO amsBox) {
        List<AmsBox> list = amsBoxService.selectAmsBoxList(amsBox);
        ExcelUtil<AmsBox> util = new ExcelUtil<>(AmsBox.class);
        return util.exportExcel(list, "archBox");
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
     * 打印预览
     *
     * @return"
     */
    @GetMapping("/printLook/{id}")
    public String printLook(@PathVariable String id, ModelMap mmap) {

        SysUser loginUser = ShiroUtils.getSysUser();
        List<AmsArchives> list = amsArchivesService.selectAmsArchivesByIds(id);
        mmap.put("printList", list);
        mmap.put("loginUser", loginUser);
        return prefix + "/printLook";
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
    @RequiresPermissions("archManage:archBox:add")
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
        mmap.put("amsBill", amsBox);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案类型
     */
    @RequiresPermissions("archManage:archBox:edit")
    @Log(title = "档案类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBox amsBox) {
        return toAjax(amsBoxService.updateAmsBox(amsBox));
    }

    /**
     * 删除档案类型
     */
    @RequiresPermissions("archManage:archBox:remove")
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
//        if (roleList != null && !roleList.isEmpty()) {
//            Integer[] arr = new Integer[roleList.size()];
//            for (int i = 0; i < roleList.size(); i++) {
//                arr[i] = roleList.get(i).getRoleId().intValue();
//            }
//            Arrays.sort(arr);
//            //获取最高权限角色Id
//            Integer sysRoleId = arr[(roleList.size() - 1)];
//            //角色Id
//            roleId = Long.valueOf(sysRoleId.longValue());
//        }
        return roleId;
    }

    /**
     * 查询是否为空档案盒
     *
     * @param ids
     * @return
     */
    @PostMapping("/queryArchiveByBoxId")
    @ResponseBody
    public AjaxResult queryArchiveByBoxId(String id) {
        AmsBox amsBox = amsBoxService.selectAmsBoxById(id);
        Integer num = 0;
        if (amsBox != null) {
            AmsArchivesVO amsArchives = new AmsArchivesVO();
            amsArchives.setBoxCode(amsBox.getId());
            List<AmsArchives> list = amsArchivesService.selectAmsArchivesList(amsArchives);
            num = list.size();
        }
        return AjaxResult.success("查询结果", num);
    }
}
