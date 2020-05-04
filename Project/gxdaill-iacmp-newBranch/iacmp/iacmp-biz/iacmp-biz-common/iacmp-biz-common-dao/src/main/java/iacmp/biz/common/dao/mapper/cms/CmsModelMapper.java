package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsModel;
import net.northking.iacmp.common.bean.dto.cms.CmsModelBillDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模型 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface CmsModelMapper {
    /**
     * 查询模型信息
     *
     * @param id 模型ID
     * @return 模型信息
     */
    CmsModel selectCmsModelById(Long id);

    List<CmsModelBillDTO> selectCmsModelDTO(Long id);

    /**
     * 查询模型列表
     *
     * @param cmsModel 模型信息
     * @return 模型集合
     */
    List<CmsModel> selectCmsModelList(CmsModel cmsModel);

    /**
     * 新增模型
     *
     * @param cmsModel 模型信息
     * @return 结果
     */
    int insertCmsModel(CmsModel cmsModel);

    /**
     * 修改模型
     *
     * @param cmsModel 模型信息
     * @return 结果
     */
    int updateCmsModel(CmsModel cmsModel);

    /**
     * 删除模型
     *
     * @param id 模型ID
     * @return 结果
     */
    int deleteCmsModelById(Long id);

    /**
     * 批量删除模型
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsModelByIds(String[] ids);

    /**
     * 根据模型编码查询模型信息
     */
    CmsModel selectCmsModelByCode(@Param("modelCode") String modelCode);

    /**
     * 根据模型名称查询模型信息
     */
    CmsModel selectCmsModelByName(@Param("modelName") String modelName);
}