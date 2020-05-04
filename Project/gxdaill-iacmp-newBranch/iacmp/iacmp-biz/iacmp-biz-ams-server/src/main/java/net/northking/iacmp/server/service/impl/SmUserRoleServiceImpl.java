package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.SmUserRoleMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.SmUserRole;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.ISmUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmUserRoleServiceImpl implements ISmUserRoleService {
    @Autowired
    private SmUserRoleMapper smUserRoleMapper;

    /**
     * 查询用户角色信息
     *
     * @param userId 用户角色ID
     * @return 用户角色信息
     */
    @Override
    public SmUserRole selectSmUserRoleById(String userId) {
        return smUserRoleMapper.selectSmUserRoleById(userId);
    }

    /**
     * 查询用户角色列表
     *
     * @param smUserRole 用户角色信息
     * @return 用户角色集合
     */
    @Override
    public List<SmUserRole> selectSmUserRoleList(SmUserRole smUserRole) {
        return smUserRoleMapper.selectSmUserRoleList(smUserRole);
    }

    /**
     * 新增用户角色
     *
     * @param smUserRole 用户角色信息
     * @return 结果
     */
    @Override
    public int insertSmUserRole(SmUserRole smUserRole) {
        return smUserRoleMapper.insertSmUserRole(smUserRole);
    }

    /**
     * 修改用户角色
     *
     * @param smUserRole 用户角色信息
     * @return 结果
     */
    @Override
    public int updateSmUserRole(SmUserRole smUserRole) {
        return smUserRoleMapper.updateSmUserRole(smUserRole);
    }

    /**
     * 删除用户角色对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmUserRoleByIds(String ids) {
        return smUserRoleMapper.deleteSmUserRoleByIds(Convert.toStrArray(ids));
    }

}
