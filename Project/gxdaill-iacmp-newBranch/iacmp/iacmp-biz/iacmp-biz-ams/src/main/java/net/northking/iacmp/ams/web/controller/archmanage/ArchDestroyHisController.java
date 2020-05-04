package net.northking.iacmp.ams.web.controller.archmanage;

import net.northking.iacmp.annotation.Log;

import net.northking.iacmp.common.bean.domain.ams.AmsParam;
import net.northking.iacmp.common.bean.dto.ams.AmsArchivesDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IAmsArchivesService;
import net.northking.iacmp.server.service.IAmsParamService;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;

import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 档案销毁历史
 *
 * @author weizhe.fan
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/archManage/archDestroyHis")
public class ArchDestroyHisController extends BaseController {
    private String prefix = "archManage/archDestroyHis";

    @Autowired
    private IAmsArchivesService amsArchivesService;

    @Autowired
    private IAmsParamService amsParamService;

    @RequiresPermissions("archManage:archDestroyHis:view")
    @GetMapping()
    public String amsParam() {
        return prefix + "/archDestroyHis";
    }

    /**
     * 查询档案参数列表
     */
    @RequiresPermissions("archManage:archDestroyHis:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsArchivesVO archivesVO) {
        //当前用户管理的全部部门
        List<String> deptList = new ArrayList<>();
        //获得当前登陆人权限
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> roles = new ArrayList();
        for (SysRole o : roleList) {
            roles.add(o.getRoleId());
        }
        roles.remove(23L);
        //获取用户最高权限角色
        String roleId = Collections.max(roles).toString();
        if ("4".equals(roleId)) {
            //行档案管理员有权查看归属行的档案销毁历史
            archivesVO.setHasMoveBank("1");
            if (archivesVO.getOpDepNo() != null && !"".equals(archivesVO.getOpDepNo())) {
                deptList.add(archivesVO.getOpDepNo());
            }
        }
        if ("3".equals(roleId)) {
            //部门管理员有权查看本部门档案销毁历史
            archivesVO.setHasMoveBank("0");
//            String deptId = sysUser.getDeptId().toString();
//            archivesVO.setOpDepNo(deptId);
            if (archivesVO.getOpDepNo() != null && !"".equals(archivesVO.getOpDepNo())) {
                deptList.add(archivesVO.getOpDepNo());
            } else {//默认查询所属部门以及辅部门
                deptList.add(sysUser.getDeptId().toString());
                String auxiliaryDept = sysUser.getAuxiliaryDept();
                if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//当前用户有辅部门
                    String[] AuxiliaryDeptArr = auxiliaryDept.split(",");
                    for (int i = 0; i < AuxiliaryDeptArr.length; i++) {
                        deptList.add(AuxiliaryDeptArr[i]);
                    }
                }

            }

        }
        startPage();
        List<AmsArchivesDTO> list = amsArchivesService.selectArchDestroyHisList(archivesVO, deptList);
        return getDataTable(list);
    }


    /**
     * 导出档案参数列表
     */
    @RequiresPermissions("archManage:archDestroyHis:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsParam amsParam) {
        List<AmsParam> list = amsParamService.selectAmsParamList(amsParam);
        ExcelUtil<AmsParam> util = new ExcelUtil<>(AmsParam.class);
        return util.exportExcel(list, "archDestroyHis");
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
     * 新增档案参数
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案参数
     */
    @RequiresPermissions("archManage:archDestroyHis:add")
    @Log(title = "档案参数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsParam amsParam) {
        return toAjax(amsParamService.insertAmsParam(amsParam));
    }

    /**
     * 修改档案参数
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsParam amsParam = amsParamService.selectAmsParamById(id);
        mmap.put("amsParam", amsParam);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案参数
     */
    @RequiresPermissions("archManage:archDestroyHis:edit")
    @Log(title = "档案参数", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsParam amsParam) {
        return toAjax(amsParamService.updateAmsParam(amsParam));
    }

    /**
     * 删除档案参数
     */
    @RequiresPermissions("archManage:archDestroyHis:remove")
    @Log(title = "档案参数", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsParamService.deleteAmsParamByIds(ids));
    }

}
