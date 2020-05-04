package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImTemplateBlockDef;

import java.util.HashMap;
import java.util.List;

/**
 * 模版碎片定义 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImTemplateBlockDefMapper {
    /**
     * 查询模版碎片定义信息
     *
     * @param id 模版碎片定义ID
     * @return 模版碎片定义信息
     */
    HashMap selectById(String id);

    /**
     * 查询模版碎片定义列表
     *
     * @param imTemplateBlockDef 模版碎片定义信息
     * @return 模版碎片定义集合
     */
    List<ImTemplateBlockDef> selectImTemplateBlockDefList(HashMap imTemplateBlockDef);

    Integer count(HashMap map);

    /**
     * 新增模版碎片定义
     *
     * @param imTemplateBlockDef 模版碎片定义信息
     * @return 结果
     */
    Integer insertImTemplateBlockDef(HashMap imTemplateBlockDef);

    /**
     * 修改模版碎片定义
     *
     * @return 结果
     */
    Integer updateById(HashMap map);

    /**
     * 删除模版碎片定义
     *
     * @param id 模版碎片定义ID
     * @return 结果
     */
    int deleteImTemplateBlockDefById(String id);

    /**
     * 批量删除模版碎片定义
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImTemplateBlockDefByIds(String[] ids);

}