package net.northking.iacmp.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.ImBatchMapper;
import net.northking.iacmp.common.bean.domain.ams.ImBatch;
import net.northking.iacmp.server.service.IImBatchService;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

/**
 * 影像批次 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImBatchServiceImpl implements IImBatchService {
    @Autowired
    private ImBatchMapper imBatchMapper;

    /**
     * 查询影像批次信息
     *
     * @param id 影像批次ID
     * @return 影像批次信息
     */
    @Override
    public ImBatch selectImBatchById(String id) {
        return imBatchMapper.selectImBatchById(id);
    }

    /**
     * 查询影像批次列表
     *
     * @param imBatch 影像批次信息
     * @return 影像批次集合
     */
    @Override
    public List<ImBatch> selectImBatchList(ImBatch imBatch) {
        return imBatchMapper.selectImBatchList(imBatch);
    }

    /**
     * 新增影像批次
     *
     * @param imBatch 影像批次信息
     * @return 结果
     */
    @Override
    public int insertImBatch(ImBatch imBatch) {
        return imBatchMapper.insertImBatch(imBatch);
    }

    /**
     * 修改影像批次
     *
     * @param imBatch 影像批次信息
     * @return 结果
     */
    @Override
    public int updateImBatch(ImBatch imBatch) {
        return imBatchMapper.updateImBatch(imBatch);
    }

    /**
     * 删除影像批次对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImBatchByIds(String ids) {
        return imBatchMapper.deleteImBatchByIds(Convert.toStrArray(ids));
    }

}
