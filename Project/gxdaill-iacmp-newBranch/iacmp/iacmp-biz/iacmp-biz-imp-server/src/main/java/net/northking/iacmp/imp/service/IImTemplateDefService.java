package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImTemplateDef;

import java.util.HashMap;
import java.util.List;

/**
 * 票据模版定义 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IImTemplateDefService {
    /**
     * 查询票据模版定义信息
     *
     * @return 票据模版定义信息
     */
    HashMap selectById(String id);

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
     * 删除票据模版定义信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImTemplateDefByIds(String ids);

}
