package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImTemplateBlockDef;

import java.util.List;

/**
 * 切片模板 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImTemplateBlockDefMapper {
    /**
     * 查询切片模板信息
     *
     * @param id 切片模板ID
     * @return 切片模板信息
     */
    ImTemplateBlockDef selectImTemplateBlockDefById(String id);

    /**
     * 查询切片模板列表
     *
     * @param imTemplateBlockDef 切片模板信息
     * @return 切片模板集合
     */
    List<ImTemplateBlockDef> selectImTemplateBlockDefList(ImTemplateBlockDef imTemplateBlockDef);

    /**
     * 新增切片模板
     *
     * @param imTemplateBlockDef 切片模板信息
     * @return 结果
     */
    int insertImTemplateBlockDef(ImTemplateBlockDef imTemplateBlockDef);

    /**
     * 修改切片模板
     *
     * @param imTemplateBlockDef 切片模板信息
     * @return 结果
     */
    int updateImTemplateBlockDef(ImTemplateBlockDef imTemplateBlockDef);

    /**
     * 删除切片模板
     *
     * @param id 切片模板ID
     * @return 结果
     */
    int deleteImTemplateBlockDefById(String id);

    /**
     * 批量删除切片模板
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImTemplateBlockDefByIds(String[] ids);

}