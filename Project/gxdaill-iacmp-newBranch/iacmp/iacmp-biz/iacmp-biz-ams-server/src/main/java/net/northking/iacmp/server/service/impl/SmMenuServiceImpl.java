package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.SmMenuMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.SmMenu;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.ISmMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmMenuServiceImpl implements ISmMenuService {
    @Autowired
    private SmMenuMapper smMenuMapper;

    /**
     * 查询菜单信息
     *
     * @param code 菜单ID
     * @return 菜单信息
     */
    @Override
    public SmMenu selectSmMenuById(String code) {
        return smMenuMapper.selectSmMenuById(code);
    }

    /**
     * 查询菜单列表
     *
     * @param smMenu 菜单信息
     * @return 菜单集合
     */
    @Override
    public List<SmMenu> selectSmMenuList(SmMenu smMenu) {
        return smMenuMapper.selectSmMenuList(smMenu);
    }

    /**
     * 新增菜单
     *
     * @param smMenu 菜单信息
     * @return 结果
     */
    @Override
    public int insertSmMenu(SmMenu smMenu) {
        return smMenuMapper.insertSmMenu(smMenu);
    }

    /**
     * 修改菜单
     *
     * @param smMenu 菜单信息
     * @return 结果
     */
    @Override
    public int updateSmMenu(SmMenu smMenu) {
        return smMenuMapper.updateSmMenu(smMenu);
    }

    /**
     * 删除菜单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmMenuByIds(String ids) {
        return smMenuMapper.deleteSmMenuByIds(Convert.toStrArray(ids));
    }

}
