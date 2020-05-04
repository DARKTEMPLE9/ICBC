package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImTemplateDef;

import java.util.List;

/**
 * 切片模板 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImTemplateDefMapper {
    /**
     * 查询切片模板信息
     *
     * @param id 切片模板ID
     * @return 切片模板信息
     */
    ImTemplateDef selectImTemplateDefById(String id);

    /**
     * 查询切片模板列表
     *
     * @param imTemplateDef 切片模板信息
     * @return 切片模板集合
     */
    List<ImTemplateDef> selectImTemplateDefList(ImTemplateDef imTemplateDef);

    /**
     * 新增切片模板
     *
     * @param imTemplateDef 切片模板信息
     * @return 结果
     */
    int insertImTemplateDef(ImTemplateDef imTemplateDef);

    /**
     * 修改切片模板
     *
     * @param imTemplateDef 切片模板信息
     * @return 结果
     */
    int updateImTemplateDef(ImTemplateDef imTemplateDef);

    /**
     * 删除切片模板
     *
     * @param id 切片模板ID
     * @return 结果
     */
    int deleteImTemplateDefById(String id);

    /**
     * 批量删除切片模板
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImTemplateDefByIds(String[] ids);

}