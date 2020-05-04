package net.northking.iacmp.pms.web.controller;

import net.northking.iacmp.cms.service.ICmsBillService;
import net.northking.iacmp.cms.service.ICmsRoleBillService;
import net.northking.iacmp.cms.service.IPmsBatchService;
import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.CmsRoleBill;
import net.northking.iacmp.common.bean.domain.cms.PmsBatch;
import net.northking.iacmp.common.bean.vo.cms.PmsBatchVO;
import net.northking.iacmp.constant.RoleConstants;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.system.domain.SysDictData;
import net.northking.iacmp.system.domain.SysMenu;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysMenuService;
import net.northking.iacmp.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Yanqingyu
 * @ClassName:DealPmsBatch
 * @Description：对项目进行处理，添加分类及权限信息
 * @Date：Create in 9:05 PM2019/12/20
 * @Modified by:
 * @Version:1.0
 */
@Component
public class DealPmsBatch {

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private IPmsBatchService pmsBatchService;

    @Autowired
    private ICmsBillService cmsBillService;

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 添加分类信息到项目
     *
     * @param pmsBatchVO
     * @return
     */
    private PmsBatchVO addCmsBillInfo(PmsBatchVO pmsBatchVO, Long roleId) {

        // 通过角色id获取分类信息
        List<CmsBill> cmsBills = cmsBillService.selectCmsBillsByRoleId(roleId);
        pmsBatchVO.setCmsBillList(cmsBills);
        cmsBills.stream().forEach(cmsBill -> addPermissionInfo(cmsBill, roleId));
        return pmsBatchVO;
    }

    /**
     * 添加权限信息到项目
     *
     * @param cmsBill
     * @return
     */
    private CmsBill addPermissionInfo(CmsBill cmsBill, Long roleId) {


        // 通过角色id获取权限信息
        List<SysMenu> sysMenus = sysMenuService.selectMenusByRole(roleId);
        cmsBill.setMenus(sysMenus);

        return cmsBill;
    }

    /**
     * 通过角色添加分类及权限信息到项目
     *
     * @param roleId
     * @return
     */
    private List<PmsBatchVO> getPmsBatchVOList(Long roleId, SysUser user) {

        SysRole role = sysRoleService.selectRoleById(roleId);
        String dataScope = role.getDataScope();

        PmsBatch pmsBatch = new PmsBatch();
        List<PmsBatchVO> pmsBatches = new ArrayList<>();
        if (RoleConstants.DATA_SCOPE_ALL.equalsIgnoreCase(dataScope)) {
            // 全部数据权限
            if (role.getRoleName() != null && role.getRoleName().contains("ITPMO")) {
                //TODO ITPMO查看全部数据权限（包含已废弃）
            }

        } else if (RoleConstants.DATA_SCOPE_DEPT.equalsIgnoreCase(dataScope)) {
            // 本部门数据权限（所在部门及辅部门）
            pmsBatch.setBuildDept(user.getDeptId().toString());
            // 用户有辅部门
            if (user.getAuxiliaryDept() != null && !"".equals(user.getAuxiliaryDept())) {
                pmsBatch.setAuxiliaryDeptList(Convert.toLongArray(user.getAuxiliaryDept()));
            }

        } else if (RoleConstants.DATA_SCOPE_DEPT_AND_CHILD.equalsIgnoreCase(dataScope)) {
            // 本部门及以下部门数据权限（所在部门及辅部门及其子部门）
            pmsBatch.setDataScope(role.getDataScope());
            pmsBatch.setDataScopeDept(user.getDeptId().toString());
            // 用户有辅部门
            if (user.getAuxiliaryDept() != null && !"".equals(user.getAuxiliaryDept())) {
                pmsBatch.setAuxiliaryDeptList(Convert.toLongArray(user.getAuxiliaryDept()));
            }

        } else if (RoleConstants.DATA_SCOPE_SELF.equalsIgnoreCase(dataScope)) {
            if (role.getRoleName() != null && role.getRoleName().contains("产品经理")) {
                // 产品经理
                pmsBatch.setProductManager(user.getUserId().toString());
            } else {
                // 项目经理所属数据权限
                pmsBatch.setProjectManager(user.getUserId().toString());
            }

        } else {
            // 自定义数据权限
            List<Long> deptIds = pmsBatchService.selectDeptIdsByRoleId(role.getRoleId());
            pmsBatch.setDeptIds(deptIds);
        }

        pmsBatches = pmsBatchService.selectPmsBatchVOBySysRoles(pmsBatch);

        return pmsBatches;
    }

    /**
     * 处理项目列表
     *
     * @param roleId
     * @param user
     * @return
     */
    public List<PmsBatchVO> dealPmsBatchs(Long roleId, SysUser user) {
        List<PmsBatchVO> pmsBatchVOS = getPmsBatchVOList(roleId, user);
        pmsBatchVOS.stream().forEach(pmsBatchVO -> {
            //添加分类信息到项目
            addCmsBillInfo(pmsBatchVO, roleId);
        });
        return pmsBatchVOS;
    }


}
