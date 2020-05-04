package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImTemplateBlockDef;
import net.northking.iacmp.imp.mapper.ImTemplateBlockDefMapper;
import net.northking.iacmp.imp.service.IImTemplateBlockDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 模版碎片定义 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImTemplateBlockDefServiceImpl implements IImTemplateBlockDefService {
    @Autowired
    private ImTemplateBlockDefMapper imTemplateBlockDefMapper;

    /**
     * 查询模版碎片定义信息
     *
     * @param id 模版碎片定义ID
     * @return 模版碎片定义信息
     */
    @Override
    public HashMap selectById(String id) {
        return imTemplateBlockDefMapper.selectById(id);
    }

    /**
     * 查询模版碎片定义列表
     *
     * @param imTemplateBlockDef 模版碎片定义信息
     * @return 模版碎片定义集合
     */
    @Override
    public List<ImTemplateBlockDef> selectImTemplateBlockDefList(HashMap imTemplateBlockDef) {
        return imTemplateBlockDefMapper.selectImTemplateBlockDefList(imTemplateBlockDef);
    }

    @Override
    public Integer count(HashMap map) {
        return imTemplateBlockDefMapper.count(map);
    }

    /**
     * 新增模版碎片定义
     *
     * @param imTemplateBlockDef 模版碎片定义信息
     * @return 结果
     */
    @Override
    public Integer insertImTemplateBlockDef(HashMap imTemplateBlockDef) {
        return imTemplateBlockDefMapper.insertImTemplateBlockDef(imTemplateBlockDef);
    }

    /**
     * 修改模版碎片定义
     *
     * @param imTemplateBlockDef 模版碎片定义信息
     * @return 结果
     */
    @Override
    public Integer updateById(HashMap map) {
        return imTemplateBlockDefMapper.updateById(map);
    }

    /**
     * 删除模版碎片定义对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImTemplateBlockDefByIds(String ids) {
        return imTemplateBlockDefMapper.deleteImTemplateBlockDefByIds(Convert.toStrArray(ids));
    }

}
