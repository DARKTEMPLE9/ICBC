package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.SmMenu;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单目录 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface SmMenuMapper {
    /**
     * 查询菜单目录信息
     *
     * @param cODE 菜单目录ID
     * @return 菜单目录信息
     */
    SmMenu selectSmMenuById(String cODE);

    /**
     * 查询菜单目录列表
     *
     * @param smMenu 菜单目录信息
     * @return 菜单目录集合
     */
    List<HashMap> queryAllMenu();

    List<HashMap> queryTreeMenuByRole(String roleId);

    /**
     * 新增菜单目录
     *
     * @param smMenu 菜单目录信息
     * @return 结果
     */
    int insertSmMenu(SmMenu smMenu);

    /**
     * 修改菜单目录
     *
     * @param smMenu 菜单目录信息
     * @return 结果
     */
    int updateSmMenu(SmMenu smMenu);

    /**
     * 删除菜单目录
     *
     * @param cODE 菜单目录ID
     * @return 结果
     */
    int deleteSmMenuById(String cODE);

    /**
     * 批量删除菜单目录
     *
     * @param cODEs 需要删除的数据ID
     * @return 结果
     */
    int deleteSmMenuByIds(String[] cODEs);

}