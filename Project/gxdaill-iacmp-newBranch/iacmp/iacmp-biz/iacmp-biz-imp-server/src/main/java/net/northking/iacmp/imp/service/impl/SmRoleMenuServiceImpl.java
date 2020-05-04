package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmRoleMenu;
import net.northking.iacmp.imp.mapper.SmRoleMenuMapper;
import net.northking.iacmp.imp.service.ISmRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户权限菜单关联 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmRoleMenuServiceImpl implements ISmRoleMenuService {
    @Autowired
    private SmRoleMenuMapper smRoleMenuMapper;

    /**
     * 查询用户权限菜单关联信息
     *
     * @param menuId 用户权限菜单关联ID
     * @return 用户权限菜单关联信息
     */
    @Override
    public SmRoleMenu selectSmRoleMenuById(String menuId) {
        return smRoleMenuMapper.selectSmRoleMenuById(menuId);
    }

    /**
     * 查询用户权限菜单关联列表
     *
     * @param smRoleMenu 用户权限菜单关联信息
     * @return 用户权限菜单关联集合
     */
    @Override
    public List<SmRoleMenu> selectSmRoleMenuList(SmRoleMenu smRoleMenu) {
        return smRoleMenuMapper.selectSmRoleMenuList(smRoleMenu);
    }

    /**
     * 新增用户权限菜单关联
     *
     * @param smRoleMenu 用户权限菜单关联信息
     * @return 结果
     */
    @Override
    public int insertSmRoleMenu(String id, String roleId, String menuId) {
        return smRoleMenuMapper.insertSmRoleMenu(id, roleId, menuId);
    }

    /**
     * 修改用户权限菜单关联
     *
     * @param smRoleMenu 用户权限菜单关联信息
     * @return 结果
     */
    @Override
    public int updateSmRoleMenu(SmRoleMenu smRoleMenu) {
        return smRoleMenuMapper.updateSmRoleMenu(smRoleMenu);
    }

    /**
     * 删除用户权限菜单关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Integer deleteMenuByRole(String roleId) {
        return smRoleMenuMapper.deleteMenuByRole(roleId);
    }

}
