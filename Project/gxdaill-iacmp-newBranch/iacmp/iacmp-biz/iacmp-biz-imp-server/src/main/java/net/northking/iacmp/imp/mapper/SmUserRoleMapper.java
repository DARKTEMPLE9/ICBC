package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.SmUserRole;

import java.util.HashMap;
import java.util.List;

/**
 * 用户角色关联 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface SmUserRoleMapper {
    /**
     * 查询用户角色关联信息
     *
     * @param userId 用户角色关联ID
     * @return 用户角色关联信息
     */
    SmUserRole selectSmUserRoleById(String userId);

    /**
     * 查询用户角色关联列表
     *
     * @param smUserRole 用户角色关联信息
     * @return 用户角色关联集合
     */
    List<SmUserRole> selectSmUserRoleList(SmUserRole smUserRole);

    /**
     * 新增用户角色关联
     *
     * @return 结果
     */
    int insertSmUserRole(HashMap map);

    /**
     * 修改用户角色关联
     *
     * @param smUserRole 用户角色关联信息
     * @return 结果
     */
    int updateSmUserRole(SmUserRole smUserRole);

    /**
     * 删除用户角色关联
     *
     * @param userId 用户角色关联ID
     * @return 结果
     */
    int deleteSmUserRoleById(String userId);

    /**
     * 批量删除用户角色关联
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    int deleteSmUserRoleByIds(String[] userIds);

    /**
     * 分配用户角色
     *
     * @param map
     * @return
     */
    Integer updateForRoles(HashMap map);

    Integer queryByRoleId(String roleId);

    String selectByUserId(String userId);

}