package net.northking.iacmp.cms.service.impl;


import iacmp.biz.common.dao.mapper.cms.CmsUserRoleMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsUserRoleService;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.domain.cms.CmsUserRole;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.domain.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户和角色关联 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-28
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsUserRoleServiceImpl implements ICmsUserRoleService {
    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;

    /**
     * 查询用户和角色关联信息
     *
     * @param userId 用户和角色关联ID
     * @return 用户和角色关联信息
     */
    @Override
    public CmsUserRole selectCmsUserRoleById(Long userId) {
        return cmsUserRoleMapper.selectCmsUserRoleById(userId);
    }

    /**
     * 查询用户和角色关联列表
     *
     * @param cmsUserRole 用户和角色关联信息
     * @return 用户和角色关联集合
     */
    @Override
    public List<CmsUserRole> selectCmsUserRoleList(CmsUserRole cmsUserRole) {
        return cmsUserRoleMapper.selectCmsUserRoleList(cmsUserRole);
    }

    /**
     * 新增用户和角色关联
     *
     * @param cmsUserRole 用户和角色关联信息
     * @return 结果
     */
    @Override
    public int insertCmsUserRole(CmsUserRole cmsUserRole) {
        return cmsUserRoleMapper.insertCmsUserRole(cmsUserRole);
    }

    /**
     * 修改用户和角色关联
     *
     * @param cmsUserRole 用户和角色关联信息
     * @return 结果
     */
    @Override
    public int updateCmsUserRole(CmsUserRole cmsUserRole) {
        return cmsUserRoleMapper.updateCmsUserRole(cmsUserRole);
    }

    /**
     * 删除用户和角色关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsUserRoleByIds(String ids) {
        return cmsUserRoleMapper.deleteCmsUserRoleByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询未分配用户角色
     *
     * @param user
     * @return
     */
    @Override
    public List<SysUser> selectUnallocatedList(SysUser user) {
        return cmsUserRoleMapper.selectUnallocatedList(user);
    }


    /**
     * 查询已分配用户角色
     *
     * @param user
     * @return
     */
    @Override
    public List<SysUser> selectAllocatedList(SysUser user) {
        return cmsUserRoleMapper.selectAllocatedList(user);
    }

    @Override
    public List<CmsRole> selectDataRoleByUserId(Long userId) {
        return cmsUserRoleMapper.selectDataRoleByUserId(userId);
    }

    @Override
    public List<Long> selectDataRoleIdsByUserId(Long userId) {
        return cmsUserRoleMapper.selectDataRoleIdsByUserId(userId);
    }

    /**
     * 取消授权
     *
     * @param cmsUserRole
     * @return
     */
    @Override
    public int deleteAuthUser(CmsUserRole cmsUserRole) {
        return cmsUserRoleMapper.deleteUserRoleInfo(cmsUserRole);
    }

    /**
     * 批量取消授权
     *
     * @param roleId
     * @param userIds
     * @return
     */
    @Override
    public int deleteAuthUsers(Long roleId, String userIds) {
        return cmsUserRoleMapper.deleteUserRoleInfos(roleId, Convert.toLongArray(userIds));
    }

    /**
     * 批量选择用户授权
     *
     * @param roleId
     * @param userIds
     * @return
     */
    @Override
    public int insertAuthUsers(Long roleId, String userIds) {
        Long[] users = Convert.toLongArray(userIds);
        // 新增用户与角色管理
        List<CmsUserRole> list = new ArrayList<>();
        for (Long userId : users) {
            CmsUserRole ur = new CmsUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return cmsUserRoleMapper.batchUserRole(list);
    }


}
