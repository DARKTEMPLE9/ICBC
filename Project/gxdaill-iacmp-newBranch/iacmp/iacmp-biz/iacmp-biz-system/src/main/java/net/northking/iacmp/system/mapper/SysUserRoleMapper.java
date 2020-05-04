package net.northking.iacmp.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.northking.iacmp.system.domain.SysUserRole;
import org.springframework.stereotype.Service;

/**
 * 用户表 数据层
 *
 * @author wxy
 */
@Service
public interface SysUserRoleMapper {
    /**
     * 通过用户ID删除用户和角色关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserRoleByUserId(Long userId);

    /**
     * 批量删除用户和角色关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserRole(Long[] ids);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * 批量新增用户角色信息
     *
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    public int batchUserRole(List<SysUserRole> userRoleList);

    /**
     * 删除用户和角色关联信息
     *
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    public int deleteUserRoleInfo(SysUserRole userRole);

    /**
     * 批量取消授权用户角色
     *
     * @param roleId  角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);

    /**
     * 批量删除普通用户
     */
    int deleteUserRoles(@Param("roleId") Long roleId, @Param("sysUserRoles") List<SysUserRole> sysUserRoles);

    /**
     * 批量导入角色中已存在的
     */
    int deleteUserRolesExist(@Param("sysUserRoles") List<SysUserRole> sysUserRoles);

    /**
     * 根据用户ID和角色ID查询是否存在该角色用户
     */
    List<SysUserRole> selectUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 新增用户角色
     */
    int insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
