package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.CmsRoleBill;

import java.util.List;

/**
 * 角色与分类关联 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface CmsRoleBillMapper {
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
     * 删除角色与分类关联
     *
     * @param roleId 角色与分类关联ID
     * @return 结果
     */
    int deleteCmsRoleBillById(Long roleId);

    /**
     * 批量删除角色与分类关联
     *
     * @param roleIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsRoleBillByIds(String[] roleIds);

    /**
     * 批量新增角色菜单信息
     *
     * @param roleBillsList
     * @return
     */
    int batchRoleMenu(List<CmsRoleBill> roleBillsList);

    /**
     * 通过分类角色ID获取分类列表
     *
     * @param roleId
     * @return
     */
    List<String> selectRoleBillTree(Long roleId);

    /**
     * 通过分类角色ID获取分类列表
     *
     * @param roleId
     * @return
     */
    List<CmsBill> selectBillsByRoleId(Long roleId);

    /**
     * 通过角色id 删除分类关联数据
     *
     * @param id
     * @return
     */
    int deleteCmsRoleBillByRoleId(Long id);

    /**
     * 通过分类Id查找分类角色关联记录数
     *
     * @param billId
     * @return
     */
    int selectCountRoleBillByBillId(Long billId);

}