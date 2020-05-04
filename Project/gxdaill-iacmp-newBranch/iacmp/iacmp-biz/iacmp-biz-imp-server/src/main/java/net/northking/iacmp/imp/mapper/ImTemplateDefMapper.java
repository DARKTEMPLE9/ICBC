package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImTemplateDef;

import java.util.HashMap;
import java.util.List;

/**
 * 票据模版定义 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImTemplateDefMapper {
    /**
     * 查询票据模版定义信息
     *
     * @param id 票据模版定义ID
     * @return 票据模版定义信息
     */
    HashMap selectImTemplateDefById(String id);

    /**
     * 查询票据模版定义列表
     *
     * @param imTemplateDef 票据模版定义信息
     * @return 票据模版定义集合
     */
    List<ImTemplateDef> selectImTemplateDefList(HashMap imTemplateDef);

    Integer count(HashMap map);

    Integer checkBeforeUpdate(HashMap map);

    /**
     * 新增票据模版定义
     *
     * @param imTemplateDef 票据模版定义信息
     * @return 结果
     */
    Integer insertImTemplateDef(HashMap imTemplateDef);

    /**
     * 修改票据模版定义
     *
     * @param imTemplateDef 票据模版定义信息
     * @return 结果
     */
    Integer updateImTemplateDef(HashMap imTemplateDef);

    /**
     * 删除票据模版定义
     *
     * @param id 票据模版定义ID
     * @return 结果
     */
    int deleteImTemplateDefById(String id);

    /**
     * 批量删除票据模版定义
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImTemplateDefByIds(String[] ids);

}