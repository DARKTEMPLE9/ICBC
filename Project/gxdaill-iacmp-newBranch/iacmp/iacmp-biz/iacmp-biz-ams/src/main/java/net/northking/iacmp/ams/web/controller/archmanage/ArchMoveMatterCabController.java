package net.northking.iacmp.ams.web.controller.archmanage;

import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.common.bean.dto.ams.AmsArchivesDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;

import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.*;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 实物类型档案移库
 */
@Controller
@RequestMapping("/archManage/moveMatterCab")
public class ArchMoveMatterCabController extends BaseController {

    private String prefix = "archManage/moveMatterCab";

    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private IAmsBoxService amsBoxService;
    @Autowired
    private IAmsCabinetService amsCabinetService;
    @Autowired
    private IAmsParamService amsParamService;
    @Autowired
    private IAmsBillService amsBillService;

    /**
     * @return
     */
    @RequiresPermissions("archManage:moveMatterCab:view")
    @GetMapping()
    public String moveMatterCab() {
        return prefix + "/moveMatterCab";
    }

    /**
     * 查询档案移库
     *
     * @param amsArchives
     * @return
     */
    @RequiresPermissions("archManage:moveMatterCab:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo queryMoveMatterCab(AmsArchivesVO amsArchivesVO) {
        SysUser sysUser = ShiroUtils.getSysUser();
        //查询当前用户管理的全部部门
        List<String> deptList = new ArrayList<>();
        if (amsArchivesVO.getOpDepNo() != null && !"".equals(amsArchivesVO.getOpDepNo())) {
            deptList.add(amsArchivesVO.getOpDepNo());
        } else {
            deptList.add(sysUser.getDeptId().toString());
            String auxiliaryDept = sysUser.getAuxiliaryDept();
            if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//该用户有辅部门
                String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                    deptList.add(auxiliaryDeptArr[i]);
                }
            }
        }
        startPage();
        //获取该用户所有角色信息
        List<SysRole> roleList = sysUser.getRoles();
        List idList = new ArrayList();
        for (SysRole o : roleList) {
            idList.add(o.getRoleId());
        }
        idList.remove(23L);
        String roleId = Collections.max(idList).toString();
        if ("3".equals(roleId)) {
            amsArchivesVO.setHasMoveBank("0");
        } else if ("4".equals(roleId)) {
            amsArchivesVO.setHasMoveBank("1");
        }
        List<AmsArchivesDTO> list = amsArchivesService.selectArchByMatterType(amsArchivesVO, deptList);
        return getDataTable(list);
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
     * 调转到显示部门树页面
     *
     * @return
     */
    @GetMapping("/deptTree")
    public String deptTree() {
        return prefix + "/tree";
    }


    /**
     * 调转到显示实物树页面
     *
     * @return
     */
    @GetMapping("/matterTypeTree")
    public String matterTypeTree() {
        return prefix + "/matterTypeTree";
    }

    /**
     * 加载实物类型列表树
     */
    @GetMapping("/treeDataMatterType")
    @ResponseBody
    public List<Ztree> treeDataMatterType() {

        AmsBill amsBill = new AmsBill();
        amsBill.setStatus("1");
        List<Ztree> ztrees = amsBillService.selectAmsBillMatterType(amsBill);
        return ztrees;
    }

    /**
     * 档案移库查看档案详情
     */
    /**
     * @Author: weizhe.fan
     * @Description:查询详情
     * @CreateDate: 15:30.2019/8/5
     */
    @RequiresPermissions("archManage:moveMatterCab:detail")
    @GetMapping("/{id}")
    public String queryOne(@PathVariable String id, ModelMap modelMap) {
        AmsArchivesVO amsArchives = new AmsArchivesVO();
        amsArchives.setId(id);
        List<AmsArchivesDTO> list = amsArchivesService.selectArchByMatterType(amsArchives);
        modelMap.addAttribute("amsArchives", list.get(0));
        return prefix + "/detail";
    }

    /**
     * 跳转到实物类型档案移库页面
     *
     * @param boxId
     * @param modelMap
     * @return
     */
    @GetMapping("/jumpToMatterCab")
    public String affirm(String arcId, ModelMap modelMap) {
        String[] ids = arcId.split(",");
        List<String> arcIds = new ArrayList<>(Arrays.asList(ids));
        modelMap.addAttribute("arcIds", arcIds);
        return prefix + "/boxWithMatterCab";
    }

    /**
     * 选择库房
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/selectDepot")
    public String selectDepot(ModelMap mmap) {
        return prefix + "/selectDepot";
    }

    /**
     * 选择库柜
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/selectCabinet/{id}")
    public String selectCabinet(@PathVariable("id") String id, ModelMap mmap) {
        mmap.put("depId", id);
        return prefix + "/selectCabinet";
    }

    @PostMapping("/moveToMatterCab")
    @ResponseBody
    public AjaxResult moveToMatterCab(String cabId, HttpServletRequest request) {
        String[] ids = request.getParameter("arcIds").replace("[", "").replace("]", "").split(",");
        int o = 0;
        for (int i = 0; i < ids.length; i++) {
            AmsCabinet cab = amsCabinetService.selectAmsCabinetById(cabId);
            AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(ids[i].trim());

            amsArchives.setDepotId(cab.getDepId());
            amsArchives.setDepotName(cab.getDepName());
            amsArchives.setDepotNo(cab.getDepCode());
            amsArchives.setCabintId(cab.getId());
            amsArchives.setCabintName(cab.getName());
            amsArchives.setCabintNo(cab.getCode());
            amsArchivesService.updateAmsArchives(amsArchives);

            AmsBox amsBox = amsBoxService.selectAmsBoxById(amsArchives.getBoxCode());
            amsBox.setCabId(cab.getId());
            amsBox.setCabName(cab.getName());
            amsBox.setDepId(cab.getDepId());
            amsBox.setDepName(cab.getDepName());
            o = amsBoxService.updateAmsBox(amsBox);
        }
        return toAjax(o);
    }
}