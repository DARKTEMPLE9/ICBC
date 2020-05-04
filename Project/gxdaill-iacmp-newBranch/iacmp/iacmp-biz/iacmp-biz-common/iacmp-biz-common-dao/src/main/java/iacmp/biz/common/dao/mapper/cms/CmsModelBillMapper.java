package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.CmsModelBill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模型与分类关联 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface CmsModelBillMapper {
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
     * 删除模型与分类关联
     *
     * @param modelId 模型与分类关联ID
     * @return 结果
     */
    int deleteCmsModelBillById(Integer modelId);

    /**
     * 批量删除模型与分类关联
     *
     * @param modelIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsModelBillByIds(String[] modelIds);

    /**
     * 通过模型id查找所有该模型下的分类
     *
     * @param modelId 模型id
     * @return
     */
    List<CmsBill> selectCmsBillsByCmsModelId(Long modelId);

    /**
     * 通过模型id查找所有该模型下的分类
     *
     * @param modelId 模型id
     * @return
     */
    List<CmsBill> selectBillListByCmsModelId(@Param("modelId") Long modelId, @Param("dataRoleIds") List<Long> dataRoleIds);

    List<CmsBill> selectAllBillByCmsModelId(Long modelId);

    /**
     * 批量添加模型与分类关联
     *
     * @param cmsModelBills
     * @return
     */
    int batchModelBill(List<CmsModelBill> cmsModelBills);

    /**
     * 通过模型id查找模型与分类关联
     *
     * @param modelId
     * @return
     */
    List<String> selectCmsModelBillByCmsModelId(Long modelId);

    /**
     * 通过模型ID删除与分类关联信息
     *
     * @param id 模型id
     * @return
     */
    int deleteCmsModelBillByModelId(Long id);

    /**
     * 通过模型id批量删除模型分类关联
     *
     * @param ids 模型ID
     * @return
     */
    int deleteCmsModelBillByModelIds(Long[] ids);

    /**
     * 通过分类ID查找模型分类表
     *
     * @param billId
     * @return
     */
    int selectCountModelBillByBillId(Long billId);
}