package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.domain.cms.CmsUserRole;

import java.util.List;

/**
 * 分类角色 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-28
 */
public interface CmsRoleMapper {
    /**
     * 查询分类角色信息
     *
     * @param id 分类角色ID
     * @return 分类角色信息
     */
    CmsRole selectCmsRoleById(Long id);

    CmsRole selectCmsRoleByName(String roleName);

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
     * 删除分类角色
     *
     * @param id 分类角色ID
     * @return 结果
     */
    int deleteCmsRoleById(Long id);

    /**
     * 批量删除分类角色
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsRoleByIds(String[] ids);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    CmsRole checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @return 角色信息
     */
    CmsRole checkRoleKeyUnique(String roleKey);

    /**
     * 根据角色ID删除角色
     *
     * @param ids
     * @return
     */
    int deleteRoleByIds(Long[] ids);

    /**
     * 批量新增用户角色信息
     *
     * @param list 用户角色列表
     * @return 结果
     */
    int batchUserRole(List<CmsUserRole> list);

}