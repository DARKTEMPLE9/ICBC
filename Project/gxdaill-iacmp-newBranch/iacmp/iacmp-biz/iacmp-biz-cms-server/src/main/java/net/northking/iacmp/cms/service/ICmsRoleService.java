package net.northking.iacmp.cms.service;


import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.domain.cms.CmsUserRole;
import net.northking.iacmp.system.domain.CmsUserRoleVO;
import net.northking.iacmp.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类角色 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-28
 */
public interface ICmsRoleService {
    /**
     * 查询分类角色信息
     *
     * @param roleId 分类角色ID
     * @return 分类角色信息
     */
    CmsRole selectCmsRoleById(Long roleId);

    /**
     * 查询分类角色列表
     *
     * @param cmsRole 分类角色信息
     * @return 分类角色集合
     */
    List<CmsRole> selectCmsRoleList(CmsRole cmsRole);

    /**
     * 新增分类角色
     *
     * @param cmsRole 分类角色信息
     * @return 结果
     */
    int insertCmsRole(CmsRole cmsRole);

    /**
     * 修改分类角色
     *
     * @param cmsRole 分类角色信息
     * @return 结果
     */
    int updateCmsRole(CmsRole cmsRole);

    /**
     * 删除分类角色信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsRoleByIds(String ids);

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    String checkRoleNameUnique(CmsRole role);

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    String checkRoleKeyUnique(CmsRole role);

    /**
     * 批量选择用户授权
     *
     * @param roleId
     * @param userIds
     * @return
     */
    int insertAuthUsers(Long roleId, String userIds);

    /**
     * 取消授权
     *
     * @param userRole
     * @return
     */
    int deleteAuthUser(CmsUserRole userRole);

    /**
     * 批量取消授权
     *
     * @param roleId
     * @param userIds
     * @return
     */
    int deleteAuthUsers(Long roleId, String userIds);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int countUserRoleByRoleId(Long roleId);

    /**
     * 分配数据权限
     *
     * @param role
     * @return
     */
    int authDataScope(CmsRole role);

    /**
     * 用户关联表信息
     *
     * @param userList
     * @return
     */

    List<CmsUserRole> selectUserRoleByUserInfo(List<CmsUserRoleVO> userList);

    /**
     * 批量导入授权用户
     *
     * @param userList
     * @return
     */
    int importUserRole(List<CmsUserRole> userList);

    /**
     * 批量取消普通用户授权
     */
    int deleteUserRoles(Long roleId, List<CmsUserRole> sysUserRoles);

    /**
     * 修改角色状态
     *
     * @param cmsRole
     * @return
     */
    int changeStatus(CmsRole cmsRole);
}
