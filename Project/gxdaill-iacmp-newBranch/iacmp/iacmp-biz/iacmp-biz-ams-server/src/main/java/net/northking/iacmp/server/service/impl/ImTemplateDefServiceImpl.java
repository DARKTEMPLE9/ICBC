package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImTemplateDefMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImTemplateDef;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImTemplateDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 切片模板 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImTemplateDefServiceImpl implements IImTemplateDefService {
    @Autowired
    private ImTemplateDefMapper imTemplateDefMapper;

    /**
     * 查询切片模板信息
     *
     * @param id 切片模板ID
     * @return 切片模板信息
     */
    @Override
    public ImTemplateDef selectImTemplateDefById(String id) {
        return imTemplateDefMapper.selectImTemplateDefById(id);
    }

    /**
     * 查询切片模板列表
     *
     * @param imTemplateDef 切片模板信息
     * @return 切片模板集合
     */
    @Override
    public List<ImTemplateDef> selectImTemplateDefList(ImTemplateDef imTemplateDef) {
        return imTemplateDefMapper.selectImTemplateDefList(imTemplateDef);
    }

    /**
     * 新增切片模板
     *
     * @param imTemplateDef 切片模板信息
     * @return 结果
     */
    @Override
    public int insertImTemplateDef(ImTemplateDef imTemplateDef) {
        return imTemplateDefMapper.insertImTemplateDef(imTemplateDef);
    }

    /**
     * 修改切片模板
     *
     * @param imTemplateDef 切片模板信息
     * @return 结果
     */
    @Override
    public int updateImTemplateDef(ImTemplateDef imTemplateDef) {
        return imTemplateDefMapper.updateImTemplateDef(imTemplateDef);
    }

    /**
     * 删除切片模板对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImTemplateDefByIds(String ids) {
        return imTemplateDefMapper.deleteImTemplateDefByIds(Convert.toStrArray(ids));
    }

}
