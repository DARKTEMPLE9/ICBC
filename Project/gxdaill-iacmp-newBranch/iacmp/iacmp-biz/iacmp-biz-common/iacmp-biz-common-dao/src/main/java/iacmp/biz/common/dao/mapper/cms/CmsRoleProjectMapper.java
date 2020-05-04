package iacmp.biz.common.dao.mapper.cms;

import net.northking.iacmp.common.bean.domain.cms.CmsRoleProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目权限关联 数据层
 *
 * @author qingyu.yan
 * @date 2019-11-20
 */
public interface CmsRoleProjectMapper {
    /**
     * 查询项目权限关联信息
     *
     * @param id 项目权限关联ID
     * @return 项目权限关联信息
     */
    CmsRoleProject selectCmsRoleProjectById(Long id);

    /**
     * 查询项目权限关联列表
     *
     * @param cmsRoleProject 项目权限关联信息
     * @return 项目权限关联集合
     */
    List<CmsRoleProject> selectCmsRoleProjectList(CmsRoleProject cmsRoleProject);

    /**
     * 新增项目权限关联
     *
     * @param cmsRoleProject 项目权限关联信息
     * @return 结果
     */
    int insertCmsRoleProject(CmsRoleProject cmsRoleProject);

    /**
     * 修改项目权限关联
     *
     * @param cmsRoleProject 项目权限关联信息
     * @return 结果
     */
    int updateCmsRoleProject(CmsRoleProject cmsRoleProject);

    /**
     * 删除项目权限关联
     *
     * @param id 项目权限关联ID
     * @return 结果
     */
    int deleteCmsRoleProjectById(Long id);

    /**
     * 批量删除项目权限关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsRoleProjectByIds(String[] ids);

    /**
     * 通过角色编号删除角色与项目关联记录
     *
     * @param id
     * @return
     */
    int deleteCmsRoleProjectByRoleId(@Param("id") Long id);

    /**
     * 批量添加角色项目关联记录
     *
     * @param list
     * @return
     */
    int batchRoleProject(List<CmsRoleProject> list);
}