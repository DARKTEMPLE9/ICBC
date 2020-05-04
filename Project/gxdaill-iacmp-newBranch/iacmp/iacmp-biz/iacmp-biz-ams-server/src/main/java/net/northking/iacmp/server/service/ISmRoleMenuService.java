package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.SmRoleMenu;

import java.util.List;

/**
 * 角色菜单 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ISmRoleMenuService {
    /**
     * 查询角色菜单信息
     *
     * @param menuId 角色菜单ID
     * @return 角色菜单信息
     */
    public SmRoleMenu selectSmRoleMenuById(String menuId);

    /**
     * 查询角色菜单列表
     *
     * @param smRoleMenu 角色菜单信息
     * @return 角色菜单集合
     */
    public List<SmRoleMenu> selectSmRoleMenuList(SmRoleMenu smRoleMenu);

    /**
     * 新增角色菜单
     *
     * @param smRoleMenu 角色菜单信息
     * @return 结果
     */
    public int insertSmRoleMenu(SmRoleMenu smRoleMenu);

    /**
     * 修改角色菜单
     *
     * @param smRoleMenu 角色菜单信息
     * @return 结果
     */
    public int updateSmRoleMenu(SmRoleMenu smRoleMenu);

    /**
     * 删除角色菜单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmRoleMenuByIds(String ids);

}
