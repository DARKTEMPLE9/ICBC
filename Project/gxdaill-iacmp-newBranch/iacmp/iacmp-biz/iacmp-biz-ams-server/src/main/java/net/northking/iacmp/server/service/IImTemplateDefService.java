package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.ImTemplateDef;

import java.util.List;

/**
 * 切片模板 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IImTemplateDefService {
    /**
     * 查询切片模板信息
     *
     * @param id 切片模板ID
     * @return 切片模板信息
     */
    public ImTemplateDef selectImTemplateDefById(String id);

    /**
     * 查询切片模板列表
     *
     * @param imTemplateDef 切片模板信息
     * @return 切片模板集合
     */
    public List<ImTemplateDef> selectImTemplateDefList(ImTemplateDef imTemplateDef);

    /**
     * 新增切片模板
     *
     * @param imTemplateDef 切片模板信息
     * @return 结果
     */
    public int insertImTemplateDef(ImTemplateDef imTemplateDef);

    /**
     * 修改切片模板
     *
     * @param imTemplateDef 切片模板信息
     * @return 结果
     */
    public int updateImTemplateDef(ImTemplateDef imTemplateDef);

    /**
     * 删除切片模板信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImTemplateDefByIds(String ids);

}
