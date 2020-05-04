package net.northking.iacmp.cms.service;


import net.northking.iacmp.common.bean.domain.cms.CmsModelBill;

import java.util.List;

/**
 * 模型与分类关联 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface ICmsModelBillService {
    /**
     * 查询模型与分类关联信息
     *
     * @param modelId 模型与分类关联ID
     * @return 模型与分类关联信息
     */
    CmsModelBill selectCmsModelBillById(Integer modelId);

    /**
     * 查询模型与分类关联列表
     *
     * @param cmsModelBill 模型与分类关联信息
     * @return 模型与分类关联集合
     */
    List<CmsModelBill> selectCmsModelBillList(CmsModelBill cmsModelBill);

    /**
     * 新增模型与分类关联
     *
     * @param cmsModelBill 模型与分类关联信息
     * @return 结果
     */
    int insertCmsModelBill(CmsModelBill cmsModelBill);

    /**
     * 修改模型与分类关联
     *
     * @param cmsModelBill 模型与分类关联信息
     * @return 结果
     */
    int updateCmsModelBill(CmsModelBill cmsModelBill);

    /**
     * 删除模型与分类关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsModelBillByIds(String ids);

}
