package net.northking.iacmp.cms.service;

import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.domain.cms.CmsUserRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.domain.SysUserRole;

import java.util.List;

/**
 * 用户和角色关联 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-28
 */
public interface ICmsUserRoleService {
    /**
     * 查询用户和角色关联信息
     *
     * @param userId 用户和角色关联ID
     * @return 用户和角色关联信息
     */
    CmsUserRole selectCmsUserRoleById(Long userId);

    /**
     * 查询用户和角色关联列表
     *
     * @param cmsUserRole 用户和角色关联信息
     * @return 用户和角色关联集合
     */
    List<CmsUserRole> selectCmsUserRoleList(CmsUserRole cmsUserRole);

    /**
     * 新增用户和角色关联
     *
     * @param cmsUserRole 用户和角色关联信息
     * @return 结果
     */
    int insertCmsUserRole(CmsUserRole cmsUserRole);

    /**
     * 修改用户和角色关联
     *
     * @param cmsUserRole 用户和角色关联信息
     * @return 结果
     */
    int updateCmsUserRole(CmsUserRole cmsUserRole);

    /**
     * 删除用户和角色关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsUserRoleByIds(String ids);


    /**
     * 查询未分配的角色
     *
     * @return
     */
    List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 查询已分配的角色
     *
     * @return
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 查询出该用户的所有数据角色id
     *
     * @param userId
     * @return
     */
    List<CmsRole> selectDataRoleByUserId(Long userId);

    List<Long> selectDataRoleIdsByUserId(Long userId);

    /**
     * 取消授权
     *
     * @param cmsUserRole
     * @return
     */
    int deleteAuthUser(CmsUserRole cmsUserRole);

    /**
     * 批量取消授权
     *
     * @param roleId
     * @param userIds
     * @return
     */
    int deleteAuthUsers(Long roleId, String userIds);

    /**
     * 批量选择用户授权
     *
     * @param roleId
     * @param userIds
     * @return
     */
    int insertAuthUsers(Long roleId, String userIds);

}
