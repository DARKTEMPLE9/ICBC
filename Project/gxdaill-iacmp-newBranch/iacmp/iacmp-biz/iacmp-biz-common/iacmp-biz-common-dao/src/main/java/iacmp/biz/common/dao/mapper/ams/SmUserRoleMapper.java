package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.SmUserRole;

import java.util.List;

/**
 * 用户角色 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface SmUserRoleMapper {
    /**
     * 查询用户角色信息
     *
     * @param userId 用户角色ID
     * @return 用户角色信息
     */
    SmUserRole selectSmUserRoleById(String userId);

    /**
     * 查询用户角色列表
     *
     * @param smUserRole 用户角色信息
     * @return 用户角色集合
     */
    List<SmUserRole> selectSmUserRoleList(SmUserRole smUserRole);

    /**
     * 新增用户角色
     *
     * @param smUserRole 用户角色信息
     * @return 结果
     */
    int insertSmUserRole(SmUserRole smUserRole);

    /**
     * 修改用户角色
     *
     * @param smUserRole 用户角色信息
     * @return 结果
     */
    int updateSmUserRole(SmUserRole smUserRole);

    /**
     * 删除用户角色
     *
     * @param userId 用户角色ID
     * @return 结果
     */
    int deleteSmUserRoleById(String userId);

    /**
     * 批量删除用户角色
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    int deleteSmUserRoleByIds(String[] userIds);

}