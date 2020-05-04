package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.SmMenu;

import java.util.List;

/**
 * 菜单 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface SmMenuMapper {
    /**
     * 查询菜单信息
     *
     * @param code 菜单ID
     * @return 菜单信息
     */
    SmMenu selectSmMenuById(String code);

    /**
     * 查询菜单列表
     *
     * @param smMenu 菜单信息
     * @return 菜单集合
     */
    List<SmMenu> selectSmMenuList(SmMenu smMenu);

    /**
     * 新增菜单
     *
     * @param smMenu 菜单信息
     * @return 结果
     */
    int insertSmMenu(SmMenu smMenu);

    /**
     * 修改菜单
     *
     * @param smMenu 菜单信息
     * @return 结果
     */
    int updateSmMenu(SmMenu smMenu);

    /**
     * 删除菜单
     *
     * @param code 菜单ID
     * @return 结果
     */
    int deleteSmMenuById(String code);

    /**
     * 批量删除菜单
     *
     * @param codes 需要删除的数据ID
     * @return 结果
     */
    int deleteSmMenuByIds(String[] codes);

}