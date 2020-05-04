package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.SmRoleMenu;

import java.util.List;

/**
 * 角色菜单 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface SmRoleMenuMapper {
    /**
     * 查询角色菜单信息
     *
     * @param menuId 角色菜单ID
     * @return 角色菜单信息
     */
    SmRoleMenu selectSmRoleMenuById(String menuId);

    /**
     * 查询角色菜单列表
     *
     * @param smRoleMenu 角色菜单信息
     * @return 角色菜单集合
     */
    List<SmRoleMenu> selectSmRoleMenuList(SmRoleMenu smRoleMenu);

    /**
     * 新增角色菜单
     *
     * @param smRoleMenu 角色菜单信息
     * @return 结果
     */
    int insertSmRoleMenu(SmRoleMenu smRoleMenu);

    /**
     * 修改角色菜单
     *
     * @param smRoleMenu 角色菜单信息
     * @return 结果
     */
    int updateSmRoleMenu(SmRoleMenu smRoleMenu);

    /**
     * 删除角色菜单
     *
     * @param menuId 角色菜单ID
     * @return 结果
     */
    int deleteSmRoleMenuById(String menuId);

    /**
     * 批量删除角色菜单
     *
     * @param menuIds 需要删除的数据ID
     * @return 结果
     */
    int deleteSmRoleMenuByIds(String[] menuIds);

}