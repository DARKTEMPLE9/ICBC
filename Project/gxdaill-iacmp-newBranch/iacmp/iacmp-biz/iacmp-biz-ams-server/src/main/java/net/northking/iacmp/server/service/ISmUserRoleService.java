package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.SmUserRole;

import java.util.List;

/**
 * 用户角色 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ISmUserRoleService {
    /**
     * 查询用户角色信息
     *
     * @param userId 用户角色ID
     * @return 用户角色信息
     */
    public SmUserRole selectSmUserRoleById(String userId);

    /**
     * 查询用户角色列表
     *
     * @param smUserRole 用户角色信息
     * @return 用户角色集合
     */
    public List<SmUserRole> selectSmUserRoleList(SmUserRole smUserRole);

    /**
     * 新增用户角色
     *
     * @param smUserRole 用户角色信息
     * @return 结果
     */
    public int insertSmUserRole(SmUserRole smUserRole);

    /**
     * 修改用户角色
     *
     * @param smUserRole 用户角色信息
     * @return 结果
     */
    public int updateSmUserRole(SmUserRole smUserRole);

    /**
     * 删除用户角色信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmUserRoleByIds(String ids);

}
