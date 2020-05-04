package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImTemplateBlockDefMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImTemplateBlockDef;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImTemplateBlockDefService;
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
public class ImTemplateBlockDefServiceImpl implements IImTemplateBlockDefService {
    @Autowired
    private ImTemplateBlockDefMapper imTemplateBlockDefMapper;

    /**
     * 查询切片模板信息
     *
     * @param id 切片模板ID
     * @return 切片模板信息
     */
    @Override
    public ImTemplateBlockDef selectImTemplateBlockDefById(String id) {
        return imTemplateBlockDefMapper.selectImTemplateBlockDefById(id);
    }

    /**
     * 查询切片模板列表
     *
     * @param imTemplateBlockDef 切片模板信息
     * @return 切片模板集合
     */
    @Override
    public List<ImTemplateBlockDef> selectImTemplateBlockDefList(ImTemplateBlockDef imTemplateBlockDef) {
        return imTemplateBlockDefMapper.selectImTemplateBlockDefList(imTemplateBlockDef);
    }

    /**
     * 新增切片模板
     *
     * @param imTemplateBlockDef 切片模板信息
     * @return 结果
     */
    @Override
    public int insertImTemplateBlockDef(ImTemplateBlockDef imTemplateBlockDef) {
        return imTemplateBlockDefMapper.insertImTemplateBlockDef(imTemplateBlockDef);
    }

    /**
     * 修改切片模板
     *
     * @param imTemplateBlockDef 切片模板信息
     * @return 结果
     */
    @Override
    public int updateImTemplateBlockDef(ImTemplateBlockDef imTemplateBlockDef) {
        return imTemplateBlockDefMapper.updateImTemplateBlockDef(imTemplateBlockDef);
    }

    /**
     * 删除切片模板对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImTemplateBlockDefByIds(String ids) {
        return imTemplateBlockDefMapper.deleteImTemplateBlockDefByIds(Convert.toStrArray(ids));
    }

}
