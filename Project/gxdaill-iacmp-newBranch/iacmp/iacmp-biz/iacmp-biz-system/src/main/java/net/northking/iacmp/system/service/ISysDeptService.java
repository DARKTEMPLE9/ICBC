package net.northking.iacmp.system.service;

import java.util.List;

import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;

/**
 * 部门管理 服务层
 *
 * @author wxy
 */
public interface ISysDeptService {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 查询部门管理树
     *
     * @param dept 部门信息
     * @return 所有部门信息
     */
    public List<Ztree> selectDeptTree(SysDept dept);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Ztree> roleDeptTreeData(SysRole role);

    List<Ztree> initZtree(List<SysDept> deptList, List<String> roleDeptList);

    /**
     * 查询部门人数
     *
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectDeptCount(Long parentId);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    public SysDept selectDeptById(Long deptId);


    public List<String> selectSubDeptById(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    public String checkDeptNameUnique(SysDept dept);

    /**
     * 初始化部门信息
     *
     * @param dept 部门信息
     * @return
     */
    public int initDept(SysDept dept);

    /**
     * 通过部门名称查找部门信息
     *
     * @param deptName
     * @return
     */
    SysDept selectDeptByDeptName(String deptName);

    /**
     * 通过部门名称修改部门状态
     *
     * @param deptName
     * @return
     */
    int changeStatusBySysDeptName(String deptName);

    /**
     * 查找所有部门名称
     *
     * @return
     */
    List<String> selectDeptName();

    /**
     * 加载副部门数据权限
     *
     * @param user
     * @return
     */
    List<Ztree> userDeptTreeData(SysUser user);

    /**
     * 根据部门ID查询下属部门
     */
    List<String> selectChildNodes(String deptId);

    /**
     * 查询当前用户管理部门（所属部门，辅部门）
     */
    List<Ztree> selectManageDept(SysUser sysUser);
}
