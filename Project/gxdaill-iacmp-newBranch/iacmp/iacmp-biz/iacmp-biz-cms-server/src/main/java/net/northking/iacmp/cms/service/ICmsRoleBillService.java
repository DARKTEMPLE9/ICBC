package net.northking.iacmp.cms.service;


import net.northking.iacmp.common.bean.domain.cms.CmsRoleBill;

import java.util.List;

/**
 * 角色与分类关联 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface ICmsRoleBillService {
    /**
     * 查询角色与分类关联信息
     *
     * @param roleId 角色与分类关联ID
     * @return 角色与分类关联信息
     */
    CmsRoleBill selectCmsRoleBillById(Long roleId);

    /**
     * 查询角色与分类关联列表
     *
     * @param cmsRoleBill 角色与分类关联信息
     * @return 角色与分类关联集合
     */
    List<CmsRoleBill> selectCmsRoleBillList(CmsRoleBill cmsRoleBill);

    /**
     * 新增角色与分类关联
     *
     * @param cmsRoleBill 角色与分类关联信息
     * @return 结果
     */
    int insertCmsRoleBill(CmsRoleBill cmsRoleBill);

    /**
     * 修改角色与分类关联
     *
     * @param cmsRoleBill 角色与分类关联信息
     * @return 结果
     */
    int updateCmsRoleBill(CmsRoleBill cmsRoleBill);

    /**
     * 删除角色与分类关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsRoleBillByIds(String ids);

}
