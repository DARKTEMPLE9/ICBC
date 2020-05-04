package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmUserRole;
import net.northking.iacmp.imp.mapper.SmUserRoleMapper;
import net.northking.iacmp.imp.service.ISmUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 用户角色关联 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmUserRoleServiceImpl implements ISmUserRoleService {
    @Autowired
    private SmUserRoleMapper smUserRoleMapper;

    /**
     * 查询用户角色关联信息
     *
     * @param userId 用户角色关联ID
     * @return 用户角色关联信息
     */
    @Override
    public SmUserRole selectSmUserRoleById(String userId) {
        return smUserRoleMapper.selectSmUserRoleById(userId);
    }

    /**
     * 查询用户角色关联列表
     *
     * @param smUserRole 用户角色关联信息
     * @return 用户角色关联集合
     */
    @Override
    public List<SmUserRole> selectSmUserRoleList(SmUserRole smUserRole) {
        return smUserRoleMapper.selectSmUserRoleList(smUserRole);
    }

    /**
     * 新增用户角色关联
     *
     * @return 结果
     */
    @Override
    public int insertSmUserRole(HashMap map) {
        return smUserRoleMapper.insertSmUserRole(map);
    }

    /**
     * 修改用户角色关联
     *
     * @param smUserRole 用户角色关联信息
     * @return 结果
     */
    @Override
    public int updateSmUserRole(SmUserRole smUserRole) {
        return smUserRoleMapper.updateSmUserRole(smUserRole);
    }

    /**
     * 删除用户角色关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmUserRoleByIds(String ids) {
        return smUserRoleMapper.deleteSmUserRoleByIds(Convert.toStrArray(ids));
    }

    @Override
    public Integer updateForRoles(HashMap map) {
        return smUserRoleMapper.updateForRoles(map);
    }

    ;

    @Override
    public Integer queryByRoleId(String roleId) {
        return smUserRoleMapper.queryByRoleId(roleId);
    }

    @Override
    public String selectByUserId(String userId) {
        return smUserRoleMapper.selectByUserId(userId);
    }
}
