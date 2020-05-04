package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.domain.cms.CmsUserRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户和角色关联 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-28
 */
public interface CmsUserRoleMapper {
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
     * 删除用户和角色关联
     *
     * @param userId 用户和角色关联ID
     * @return 结果
     */
    int deleteCmsUserRoleById(Long userId);

    /**
     * 批量删除用户和角色关联
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsUserRoleByIds(String[] userIds);

    /**
     * 查询未分配的角色
     *
     * @return
     */
    List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 批量用户授权
     *
     * @param list
     * @return
     */
    int batchUserRole(List<CmsUserRole> list);

    /**
     * 查询已分配用户列表
     *
     * @param user
     * @return
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 取消授权
     *
     * @param userRole
     * @return
     */
    int deleteUserRoleInfo(CmsUserRole userRole);

    /**
     * 批量取消授权
     *
     * @param roleId
     * @param userIds
     * @return
     */
    int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId
     * @return
     */
    int countUserRoleByRoleId(Long roleId);

    /**
     * 查询出该用户的所有数据角色
     *
     * @param userId
     * @return
     */
    List<CmsRole> selectDataRoleByUserId(Long userId);

    /**
     * 查询出该用户的所有数据角色id
     *
     * @param userId
     * @return
     */
    List<Long> selectDataRoleIdsByUserId(Long userId);

    /**
     * 批量取消普通用户授权
     */
    int deleteUserRoles(@Param("roleId") Long roleId, @Param("sysUserRoles") List<CmsUserRole> sysUserRoles);


}