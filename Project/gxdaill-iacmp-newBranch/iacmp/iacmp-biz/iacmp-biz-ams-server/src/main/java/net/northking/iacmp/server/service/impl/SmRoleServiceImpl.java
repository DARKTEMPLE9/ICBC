package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.SmRoleMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.SmRole;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.ISmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmRoleServiceImpl implements ISmRoleService {
    @Autowired
    private SmRoleMapper smRoleMapper;

    /**
     * 查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    @Override
    public SmRole selectSmRoleById(String id) {
        return smRoleMapper.selectSmRoleById(id);
    }

    /**
     * 查询角色列表
     *
     * @param smRole 角色信息
     * @return 角色集合
     */
    @Override
    public List<SmRole> selectSmRoleList(SmRole smRole) {
        return smRoleMapper.selectSmRoleList(smRole);
    }

    /**
     * 新增角色
     *
     * @param smRole 角色信息
     * @return 结果
     */
    @Override
    public int insertSmRole(SmRole smRole) {
        return smRoleMapper.insertSmRole(smRole);
    }

    /**
     * 修改角色
     *
     * @param smRole 角色信息
     * @return 结果
     */
    @Override
    public int updateSmRole(SmRole smRole) {
        return smRoleMapper.updateSmRole(smRole);
    }

    /**
     * 删除角色对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmRoleByIds(String ids) {
        return smRoleMapper.deleteSmRoleByIds(Convert.toStrArray(ids));
    }

}
