package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.SmRole;

import java.util.HashMap;
import java.util.List;

/**
 * 用户权限 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface SmRoleMapper {

    List<SmRole> selectSmRoleList(HashMap map);

    Integer selectSmRoleCount(HashMap map);

    /**
     * 查询用户权限信息
     *
     * @param id 用户权限ID
     * @return 用户权限信息
     */
    SmRole selectSmRoleById(String id);

    /**
     * 查询用户权限列表
     *
     * @param smRole 用户权限信息
     * @return 用户权限集合
     */
    List<HashMap<String, Object>> queryRolesForUser();

    List<SmRole> queryRolesByUser(String userId);

    List<SmRole> queryRoleByCode(String code);

    HashMap queryRoleById(String roleId);

    /**
     * 新增用户权限
     *
     * @param smRole 用户权限信息
     * @return 结果
     */
    Integer addRole(HashMap map);

    /**
     * 修改用户权限
     *
     * @param smRole 用户权限信息
     * @return 结果
     */
    int updateSmRole(SmRole smRole);

    /**
     * 删除用户权限
     *
     * @param id 用户权限ID
     * @return 结果
     */
    Integer deleteSmRoleById(String id);

    /**
     * 批量删除用户权限
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmRoleByIds(String[] ids);

}