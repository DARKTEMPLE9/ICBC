package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.ImTemplateBlockDef;

import java.util.List;

/**
 * 切片模板 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IImTemplateBlockDefService {
    /**
     * 查询切片模板信息
     *
     * @param id 切片模板ID
     * @return 切片模板信息
     */
    public ImTemplateBlockDef selectImTemplateBlockDefById(String id);

    /**
     * 查询切片模板列表
     *
     * @param imTemplateBlockDef 切片模板信息
     * @return 切片模板集合
     */
    public List<ImTemplateBlockDef> selectImTemplateBlockDefList(ImTemplateBlockDef imTemplateBlockDef);

    /**
     * 新增切片模板
     *
     * @param imTemplateBlockDef 切片模板信息
     * @return 结果
     */
    public int insertImTemplateBlockDef(ImTemplateBlockDef imTemplateBlockDef);

    /**
     * 修改切片模板
     *
     * @param imTemplateBlockDef 切片模板信息
     * @return 结果
     */
    public int updateImTemplateBlockDef(ImTemplateBlockDef imTemplateBlockDef);

    /**
     * 删除切片模板信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImTemplateBlockDefByIds(String ids);

}
