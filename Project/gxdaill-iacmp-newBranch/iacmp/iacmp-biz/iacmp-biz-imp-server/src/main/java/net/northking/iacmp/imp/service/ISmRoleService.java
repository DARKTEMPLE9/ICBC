package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.SmRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户权限 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ISmRoleService {
    /**
     * 查询所有角色
     *
     * @param smRole
     * @return
     */
    List<SmRole> selectSmRoleList(HashMap map);

    Integer selectSmRoleCount(HashMap map);

    /**
     * 查询用户权限信息
     *
     * @param iD 用户权限ID
     * @return 用户权限信息
     */
    SmRole selectSmRoleById(String iD);

    /**
     * 查询用户权限列表
     *
     * @param smRole 用户权限信息
     * @return 用户权限集合
     */
    List<HashMap<String, Object>> queryRolesForUser();

    List<SmRole> queryRolesByUser(String userId);

    /**
     * 新增时查询code是否已存在
     */
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
     * 删除用户权限信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Integer deleteSmRoleById(String roleId);

    int deleteSmRoleByIds(String ids);

}
