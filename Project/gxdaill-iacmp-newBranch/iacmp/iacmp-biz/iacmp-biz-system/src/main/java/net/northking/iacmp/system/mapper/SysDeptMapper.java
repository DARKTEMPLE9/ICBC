package net.northking.iacmp.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.northking.iacmp.system.domain.SysDept;

/**
 * 部门管理 数据层
 *
 * @author wxy
 */
public interface SysDeptMapper {
    /**
     * 查询部门人数
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int selectDeptCount(SysDept dept);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<SysDept> depts);

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
     * @param deptName     部门名称
     * @param parentDeptId 父部门ID
     * @return 结果
     */
    public SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentDeptId") Long parentDeptId);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId 角色ID
     * @return 部门列表
     */
    public List<String> selectRoleDeptTree(Long roleId);

    /**
     * 修改所在部门的父级部门状态
     *
     * @param dept 部门
     */
    public void updateDeptStatus(SysDept dept);

    /**
     * 根据ID查询所有子部门
     *
     * @param deptId 部门ID
     * @return 部门列表
     */
    public List<SysDept> selectChildrenDeptById(Long deptId);

    /**
     * 初始化部门信息
     *
     * @param dept
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
     * 通过部门名称查找部门信息
     *
     * @return
     */
    List<String> selectDeptName();

    /**
     * 查找用户副部门树
     *
     * @param deptIds
     * @return
     */
    List<String> selectUserDeptTree(String[] deptIds);

    /**
     * 根据部门ID查询下属部门
     */
    List<String> selectChildNodes(@Param("deptId") String deptId);

    SysDept selectDeptByDeptNameOrDeptId(@Param("attriDept") String attriDept);
}
