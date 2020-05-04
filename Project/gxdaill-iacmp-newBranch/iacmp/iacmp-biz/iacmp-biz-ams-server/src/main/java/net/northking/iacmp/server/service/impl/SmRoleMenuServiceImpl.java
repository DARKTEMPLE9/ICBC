package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.SmRoleMenuMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.SmRoleMenu;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.ISmRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色菜单 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmRoleMenuServiceImpl implements ISmRoleMenuService {
    @Autowired
    private SmRoleMenuMapper smRoleMenuMapper;

    /**
     * 查询角色菜单信息
     *
     * @param menuId 角色菜单ID
     * @return 角色菜单信息
     */
    @Override
    public SmRoleMenu selectSmRoleMenuById(String menuId) {
        return smRoleMenuMapper.selectSmRoleMenuById(menuId);
    }

    /**
     * 查询角色菜单列表
     *
     * @param smRoleMenu 角色菜单信息
     * @return 角色菜单集合
     */
    @Override
    public List<SmRoleMenu> selectSmRoleMenuList(SmRoleMenu smRoleMenu) {
        return smRoleMenuMapper.selectSmRoleMenuList(smRoleMenu);
    }

    /**
     * 新增角色菜单
     *
     * @param smRoleMenu 角色菜单信息
     * @return 结果
     */
    @Override
    public int insertSmRoleMenu(SmRoleMenu smRoleMenu) {
        return smRoleMenuMapper.insertSmRoleMenu(smRoleMenu);
    }

    /**
     * 修改角色菜单
     *
     * @param smRoleMenu 角色菜单信息
     * @return 结果
     */
    @Override
    public int updateSmRoleMenu(SmRoleMenu smRoleMenu) {
        return smRoleMenuMapper.updateSmRoleMenu(smRoleMenu);
    }

    /**
     * 删除角色菜单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmRoleMenuByIds(String ids) {
        return smRoleMenuMapper.deleteSmRoleMenuByIds(Convert.toStrArray(ids));
    }

}
