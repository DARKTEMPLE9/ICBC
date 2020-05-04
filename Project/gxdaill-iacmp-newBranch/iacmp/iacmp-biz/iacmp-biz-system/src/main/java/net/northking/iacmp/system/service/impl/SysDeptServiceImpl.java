package net.northking.iacmp.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.northking.iacmp.annotation.DataScope;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.mapper.SysUserMapper;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.mapper.SysDeptMapper;
import net.northking.iacmp.system.service.ISysDeptService;

/**
 * 部门管理 服务实现
 *
 * @author wxy
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SysDeptServiceImpl implements ISysDeptService {
    @Autowired
    private SysDeptMapper deptMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysDept> selectDeptList(SysDept dept) {
        return deptMapper.selectDeptList(dept);
    }

    /**
     * 查询部门管理树
     *
     * @param dept 部门信息
     * @return 所有部门信息
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<Ztree> selectDeptTree(SysDept dept) {
        List<SysDept> deptList = deptMapper.selectDeptList(dept);
        List<Ztree> ztrees = initZtree(deptList);
        return ztrees;
    }

    /**
     * 根据角色ID查询部门（数据权限）
     *
     * @param role 角色对象
     * @return 部门列表（数据权限）
     */
    @Override
    public List<Ztree> roleDeptTreeData(SysRole role) {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees;
        List<SysDept> deptList = selectDeptList(new SysDept());
        if (StringUtils.isNotNull(roleId)) {
            List<String> roleDeptList = deptMapper.selectRoleDeptTree(roleId);
            ztrees = initZtree(deptList, roleDeptList);
        } else {
            ztrees = initZtree(deptList);
        }
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysDept> deptList) {
        return initZtree(deptList, null);
    }

    /**
     * 对象转部门树
     *
     * @param deptList     部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    @Override
    public List<Ztree> initZtree(List<SysDept> deptList, List<String> roleDeptList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (SysDept dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(dept.getDeptId());
                ztree.setpId(dept.getParentDeptId());
                ztree.setName(dept.getDeptName());
                ztree.setTitle(dept.getDeptName());
                if (isCheck) {
                    ztree.setChecked(roleDeptList.contains(dept.getDeptId() + dept.getDeptName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 查询部门人数
     *
     * @param parentId 部门ID
     * @return 结果
     */
    @Override
    public int selectDeptCount(Long parentId) {
        SysDept dept = new SysDept();
        dept.setParentDeptId(parentId);
        return deptMapper.selectDeptCount(dept);
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        int result = deptMapper.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId) {
        return deptMapper.deleteDeptById(deptId);
    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(SysDept dept) {
        SysDept info = deptMapper.selectDeptById(dept.getParentDeptId());
        if (info == null && dept.getParentDeptId() == 0L) {
            dept.setAncestors("0," + dept.getParentDeptId());
            return deptMapper.insertDept(dept);
        }
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (info != null) {
            if (!UserConstants.DEPT_NORMAL.equals(info.getStatus())) {
                throw new BusinessException("部门停用，不允许新增");
            }
            dept.setAncestors(info.getAncestors() + "," + dept.getParentDeptId());
        }
        return deptMapper.insertDept(dept);
    }

    /**
     * 初始化部门信息
     *
     * @param dept
     * @return
     */
    public int initDept(SysDept dept) {
        return deptMapper.insertDept(dept);
    }

    /**
     * 通过部门名称查找部门信息
     *
     * @param deptName
     * @return
     */
    @Override
    public SysDept selectDeptByDeptName(String deptName) {
        return deptMapper.selectDeptByDeptName(deptName);
    }

    /**
     * @param deptName
     * @return
     */
    @Override
    public int changeStatusBySysDeptName(String deptName) {

        return deptMapper.changeStatusBySysDeptName(deptName);
    }

    /**
     * 查找所有部门名称
     *
     * @return
     */
    @Override
    public List<String> selectDeptName() {
        return deptMapper.selectDeptName();
    }

    /**
     * 加载副部门数据权限
     *
     * @param user
     * @return
     */
    @Override
    public List<Ztree> userDeptTreeData(SysUser user) {

        List<Ztree> ztrees;
        List<SysDept> deptList = selectDeptList(new SysDept());
        SysUser sysUser = sysUserMapper.selectUserByLoginName(user.getLoginName());
        if (StringUtils.isNotNull(sysUser.getAuxiliaryDept())) {
            List<String> userDeptList = deptMapper.selectUserDeptTree(Convert.toStrArray(sysUser.getAuxiliaryDept()));
            ztrees = initZtree(deptList, userDeptList);
        } else {
            ztrees = initZtree(deptList);
        }
        return ztrees;
    }


    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDept(SysDept dept) {
        SysDept newParentDept = deptMapper.selectDeptById(dept.getParentDeptId());
        SysDept oldDept = selectDeptById(dept.getDeptId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)) {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        int result = deptMapper.updateDept(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        return result;
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SysDept dept) {
        String updateBy = dept.getUpdateBy();
        dept = deptMapper.selectDeptById(dept.getDeptId());
        dept.setUpdateBy(updateBy);
        deptMapper.updateDeptStatus(dept);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId       被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
        List<SysDept> children = deptMapper.selectChildrenDeptById(deptId);
        for (SysDept child : children) {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0) {
            deptMapper.updateDeptChildren(children);
        }
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public SysDept selectDeptById(Long deptId) {
        return deptMapper.selectDeptById(deptId);
    }

    @Override
    public List<String> selectSubDeptById(Long deptId) {
        return deptMapper.selectSubDeptById(deptId);
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(SysDept dept) {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        SysDept info = deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentDeptId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    /**
     * 根据部门ID查询下属部门
     */
    @Override
    public List<String> selectChildNodes(String deptId) {
        List<String> deptIds = deptMapper.selectChildNodes(deptId);
        return deptIds;
    }

    /**
     * 查询当前用户管理部门（所属部门，辅部门）
     */
    @Override
    public List<Ztree> selectManageDept(SysUser sysUser) {
        List<SysDept> deptList = new ArrayList<>();
        deptList.add(sysUser.getDept());
        String auxiliaryDept = sysUser.getAuxiliaryDept();
        if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {
            String[] auxiliaryDeptArr = auxiliaryDept.split(",");
            for (String auxiliaryDeptId : auxiliaryDeptArr) {
                SysDept dept = deptMapper.selectDeptById(Long.valueOf(auxiliaryDeptId));
                if (dept != null) {
                    deptList.add(dept);
                }
            }
        }
        List<Ztree> deptTree = initZtree(deptList);
        return deptTree;
    }
}
