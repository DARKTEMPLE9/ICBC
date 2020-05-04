package net.northking.iacmp.cms.service.impl;

import java.util.List;

import iacmp.biz.common.dao.mapper.cms.CmsRoleProjectMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsRoleProjectService;
import net.northking.iacmp.common.bean.domain.cms.CmsRoleProject;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目权限关联 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-11-20
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsRoleProjectServiceImpl implements ICmsRoleProjectService {
    @Autowired
    private CmsRoleProjectMapper cmsRoleProjectMapper;

    /**
     * 查询项目权限关联信息
     *
     * @param id 项目权限关联ID
     * @return 项目权限关联信息
     */
    @Override
    public CmsRoleProject selectCmsRoleProjectById(Long id) {
        return cmsRoleProjectMapper.selectCmsRoleProjectById(id);
    }

    /**
     * 查询项目权限关联列表
     *
     * @param cmsRoleProject 项目权限关联信息
     * @return 项目权限关联集合
     */
    @Override
    public List<CmsRoleProject> selectCmsRoleProjectList(CmsRoleProject cmsRoleProject) {
        return cmsRoleProjectMapper.selectCmsRoleProjectList(cmsRoleProject);
    }

    /**
     * 新增项目权限关联
     *
     * @param cmsRoleProject 项目权限关联信息
     * @return 结果
     */
    @Override
    public int insertCmsRoleProject(CmsRoleProject cmsRoleProject) {
        return cmsRoleProjectMapper.insertCmsRoleProject(cmsRoleProject);
    }

    /**
     * 修改项目权限关联
     *
     * @param cmsRoleProject 项目权限关联信息
     * @return 结果
     */
    @Override
    public int updateCmsRoleProject(CmsRoleProject cmsRoleProject) {
        return cmsRoleProjectMapper.updateCmsRoleProject(cmsRoleProject);
    }

    /**
     * 删除项目权限关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsRoleProjectByIds(String ids) {
        return cmsRoleProjectMapper.deleteCmsRoleProjectByIds(Convert.toStrArray(ids));
    }

}
