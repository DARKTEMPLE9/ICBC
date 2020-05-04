package net.northking.iacmp.cms.service;


import net.northking.iacmp.common.bean.domain.cms.CmsModel;
import net.northking.iacmp.common.bean.dto.cms.CmsModelBillDTO;

import java.util.List;

/**
 * 模型 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface ICmsModelService {
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
     * 删除模型信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsModelByIds(String ids);

    /**
     * 根据模型编码查询模型信息
     */
    CmsModel selectCmsModelByCode(String modelCode);

    /**
     * 校验模型名称
     */
    String checkModelNameUnique(CmsModel model);

    /**
     * 校验模型编码
     */
    String checkModelCodeUnique(CmsModel model);

    /**
     * 通过模型ids获取项目数量
     *
     * @param ids
     * @return
     */
    int selectCountProjectByModelId(String ids);
}
