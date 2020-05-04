package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.SmMenu;

import java.util.List;

/**
 * 菜单 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ISmMenuService {
    /**
     * 查询菜单信息
     *
     * @param code 菜单ID
     * @return 菜单信息
     */
    public SmMenu selectSmMenuById(String code);

    /**
     * 查询菜单列表
     *
     * @param smMenu 菜单信息
     * @return 菜单集合
     */
    public List<SmMenu> selectSmMenuList(SmMenu smMenu);

    /**
     * 新增菜单
     *
     * @param smMenu 菜单信息
     * @return 结果
     */
    public int insertSmMenu(SmMenu smMenu);

    /**
     * 修改菜单
     *
     * @param smMenu 菜单信息
     * @return 结果
     */
    public int updateSmMenu(SmMenu smMenu);

    /**
     * 删除菜单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmMenuByIds(String ids);

}
