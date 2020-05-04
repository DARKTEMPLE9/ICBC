package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.SmRoleMenu;

import java.util.List;

/**
 * 用户权限菜单关联 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ISmRoleMenuService {
    /**
     * 查询用户权限菜单关联信息
     *
     * @param menuId 用户权限菜单关联ID
     * @return 用户权限菜单关联信息
     */
    SmRoleMenu selectSmRoleMenuById(String menuId);

    /**
     * 查询用户权限菜单关联列表
     *
     * @param smRoleMenu 用户权限菜单关联信息
     * @return 用户权限菜单关联集合
     */
    List<SmRoleMenu> selectSmRoleMenuList(SmRoleMenu smRoleMenu);

    /**
     * 新增用户权限菜单关联
     *
     * @param smRoleMenu 用户权限菜单关联信息
     * @return 结果
     */
    int insertSmRoleMenu(String id, String roleId, String menuId);

    /**
     * 修改用户权限菜单关联
     *
     * @param smRoleMenu 用户权限菜单关联信息
     * @return 结果
     */
    int updateSmRoleMenu(SmRoleMenu smRoleMenu);

    /**
     * 删除用户权限菜单关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Integer deleteMenuByRole(String roleId);

}
