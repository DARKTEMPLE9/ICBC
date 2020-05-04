package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmMenu;
import net.northking.iacmp.imp.mapper.SmMenuMapper;
import net.northking.iacmp.imp.service.ISmMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单目录 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmMenuServiceImpl implements ISmMenuService {
    @Autowired
    private SmMenuMapper smMenuMapper;

    /**
     * 查询菜单目录信息
     *
     * @param cODE 菜单目录ID
     * @return 菜单目录信息
     */
    @Override
    public SmMenu selectSmMenuById(String cODE) {
        return smMenuMapper.selectSmMenuById(cODE);
    }

    /**
     * 查询菜单目录列表
     *
     * @param smMenu 菜单目录信息
     * @return 菜单目录集合
     */
    @Override
    public List<HashMap> queryAllMenu() {
        return smMenuMapper.queryAllMenu();
    }

    @Override
    public List<HashMap> queryTreeMenuByRole(String roleId) {
        return smMenuMapper.queryTreeMenuByRole(roleId);
    }

    /**
     * 新增菜单目录
     *
     * @param smMenu 菜单目录信息
     * @return 结果
     */
    @Override
    public int insertSmMenu(SmMenu smMenu) {
        return smMenuMapper.insertSmMenu(smMenu);
    }

    /**
     * 修改菜单目录
     *
     * @param smMenu 菜单目录信息
     * @return 结果
     */
    @Override
    public int updateSmMenu(SmMenu smMenu) {
        return smMenuMapper.updateSmMenu(smMenu);
    }

    /**
     * 删除菜单目录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmMenuByIds(String ids) {
        return smMenuMapper.deleteSmMenuByIds(Convert.toStrArray(ids));
    }

}
