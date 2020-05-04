package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmRole;
import net.northking.iacmp.imp.mapper.SmRoleMapper;
import net.northking.iacmp.imp.service.ISmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户权限 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmRoleServiceImpl implements ISmRoleService {
    @Autowired
    private SmRoleMapper smRoleMapper;

    /**
     * 查询所有角色
     */
    @Override
    public List<SmRole> selectSmRoleList(HashMap map) {
        return smRoleMapper.selectSmRoleList(map);
    }

    public Integer selectSmRoleCount(HashMap map) {
        return smRoleMapper.selectSmRoleCount(map);
    }

    /**
     * 查询用户权限信息
     *
     * @param id 用户权限ID
     * @return 用户权限信息
     */
    @Override
    public SmRole selectSmRoleById(String id) {
        return smRoleMapper.selectSmRoleById(id);
    }

    /**
     * 查询用户权限列表
     *
     * @param smRole 用户权限信息
     * @return 用户权限集合
     */
    public List<HashMap<String, Object>> queryRolesForUser() {
        return smRoleMapper.queryRolesForUser();
    }

    public List<SmRole> queryRolesByUser(String userId) {
        return smRoleMapper.queryRolesByUser(userId);
    }

    public List<SmRole> queryRoleByCode(String code) {
        return smRoleMapper.queryRoleByCode(code);
    }

    public HashMap queryRoleById(String roleId) {
        return smRoleMapper.queryRoleById(roleId);
    }

    /**
     * 新增用户权限
     *
     * @param smRole 用户权限信息
     * @return 结果
     */
    @Override
    public Integer addRole(HashMap map) {
        return smRoleMapper.addRole(map);
    }

    /**
     * 修改用户权限
     *
     * @param smRole 用户权限信息
     * @return 结果
     */
    @Override
    public int updateSmRole(SmRole smRole) {
        return smRoleMapper.updateSmRole(smRole);
    }

    /**
     * 删除用户权限对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Integer deleteSmRoleById(String roleId) {
        return smRoleMapper.deleteSmRoleById(roleId);
    }

    @Override
    public int deleteSmRoleByIds(String ids) {
        return smRoleMapper.deleteSmRoleByIds(Convert.toStrArray(ids));
    }

}
