package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImTemplateDef;
import net.northking.iacmp.imp.mapper.ImTemplateDefMapper;
import net.northking.iacmp.imp.service.IImTemplateDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 票据模版定义 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImTemplateDefServiceImpl implements IImTemplateDefService {
    @Autowired
    private ImTemplateDefMapper imTemplateDefMapper;

    /**
     * 查询票据模版定义信息
     *
     * @param id 票据模版定义ID
     * @return 票据模版定义信息
     */
    @Override
    public HashMap selectById(String id) {
        return imTemplateDefMapper.selectImTemplateDefById(id);
    }

    /**
     * 查询票据模版定义列表
     *
     * @param imTemplateDef 票据模版定义信息
     * @return 票据模版定义集合
     */
    @Override
    public List<ImTemplateDef> selectImTemplateDefList(HashMap imTemplateDef) {
        return imTemplateDefMapper.selectImTemplateDefList(imTemplateDef);
    }

    @Override
    public Integer count(HashMap map) {
        return imTemplateDefMapper.count(map);
    }

    @Override
    public Integer checkBeforeUpdate(HashMap map) {
        return imTemplateDefMapper.checkBeforeUpdate(map);
    }


    /**
     * 新增票据模版定义
     *
     * @param imTemplateDef 票据模版定义信息
     * @return 结果
     */
    @Override
    public Integer insertImTemplateDef(HashMap imTemplateDef) {
        return imTemplateDefMapper.insertImTemplateDef(imTemplateDef);
    }

    /**
     * 修改票据模版定义
     *
     * @param imTemplateDef 票据模版定义信息
     * @return 结果
     */
    @Override
    public Integer updateImTemplateDef(HashMap imTemplateDef) {
        return imTemplateDefMapper.updateImTemplateDef(imTemplateDef);
    }

    /**
     * 删除票据模版定义对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImTemplateDefByIds(String ids) {
        return imTemplateDefMapper.deleteImTemplateDefByIds(Convert.toStrArray(ids));
    }

}
