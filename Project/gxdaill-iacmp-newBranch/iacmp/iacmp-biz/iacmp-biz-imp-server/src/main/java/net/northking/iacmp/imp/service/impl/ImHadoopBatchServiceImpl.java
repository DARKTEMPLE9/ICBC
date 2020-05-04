package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImHadoopBatch;
import net.northking.iacmp.imp.mapper.ImHadoopBatchMapper;
import net.northking.iacmp.imp.service.IImHadoopBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 大数据批次 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImHadoopBatchServiceImpl implements IImHadoopBatchService {
    @Autowired
    private ImHadoopBatchMapper imHadoopBatchMapper;

    /**
     * 查询大数据批次信息
     *
     * @param id 大数据批次ID
     * @return 大数据批次信息
     */
    @Override
    public ImHadoopBatch selectImHadoopBatchById(String id) {
        return imHadoopBatchMapper.selectImHadoopBatchById(id);
    }

    /**
     * 查询大数据批次列表
     *
     * @param imHadoopBatch 大数据批次信息
     * @return 大数据批次集合
     */
    @Override
    public List<ImHadoopBatch> selectImHadoopBatchList(HashMap imHadoopBatch) {
        return imHadoopBatchMapper.selectImHadoopBatchList(imHadoopBatch);
    }

    @Override
    public Integer count(HashMap map) {
        return imHadoopBatchMapper.count(map);
    }

    @Override
    public List<ImHadoopBatch> selectByRegno(String regno) {
        return imHadoopBatchMapper.selectByRegno(regno);
    }

    /**
     * 新增大数据批次
     *
     * @param imHadoopBatch 大数据批次信息
     * @return 结果
     */
    @Override
    public Integer insertImHadoopBatch(HashMap imHadoopBatch) {
        return imHadoopBatchMapper.insertImHadoopBatch(imHadoopBatch);
    }

    /**
     * 修改大数据批次
     *
     * @param imHadoopBatch 大数据批次信息
     * @return 结果
     */
    @Override
    public int updateImHadoopBatch(ImHadoopBatch imHadoopBatch) {
        return imHadoopBatchMapper.updateImHadoopBatch(imHadoopBatch);
    }

    /**
     * 删除大数据批次对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImHadoopBatchByIds(String ids) {
        return imHadoopBatchMapper.deleteImHadoopBatchByIds(Convert.toStrArray(ids));
    }

}
