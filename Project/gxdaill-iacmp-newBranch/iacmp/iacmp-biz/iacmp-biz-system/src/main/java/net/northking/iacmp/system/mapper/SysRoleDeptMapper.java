package net.northking.iacmp.system.mapper;

import java.util.List;

import net.northking.iacmp.system.domain.SysRoleDept;

/**
 * 角色与部门关联表 数据层
 *
 * @author wxy
 */
public interface SysRoleDeptMapper {
    /**
     * 通过角色ID删除角色和部门关联
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleDeptByRoleId(Long roleId);

    /**
     * 批量删除角色部门关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleDept(Long[] ids);

    /**
     * 查询部门使用数量
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int selectCountRoleDeptByDeptId(Long deptId);

    /**
     * 批量新增角色部门信息
     *
     * @param roleDeptList 角色部门列表
     * @return 结果
     */
    public int batchRoleDept(List<SysRoleDept> roleDeptList);

    /**
     * 通过用户id查询角色已有部门数据权限
     *
     * @param userId
     * @return
     */
    List<String> selectRoleDeptByUserId(Long userId);

    /**
     * 通过角色id查询出所涉及到的部门id集合
     *
     * @param roleId
     * @return
     */
    List<Long> selectDeptIdsByRoleId(Long roleId);
}