package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImHadoopBatchMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImHadoopBatch;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImHadoopBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频上传批次 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImHadoopBatchServiceImpl implements IImHadoopBatchService {
    @Autowired
    private ImHadoopBatchMapper imHadoopBatchMapper;

    /**
     * 查询视频上传批次信息
     *
     * @param id 视频上传批次ID
     * @return 视频上传批次信息
     */
    @Override
    public ImHadoopBatch selectImHadoopBatchById(String id) {
        return imHadoopBatchMapper.selectImHadoopBatchById(id);
    }

    /**
     * 查询视频上传批次列表
     *
     * @param imHadoopBatch 视频上传批次信息
     * @return 视频上传批次集合
     */
    @Override
    public List<ImHadoopBatch> selectImHadoopBatchList(ImHadoopBatch imHadoopBatch) {
        return imHadoopBatchMapper.selectImHadoopBatchList(imHadoopBatch);
    }

    /**
     * 新增视频上传批次
     *
     * @param imHadoopBatch 视频上传批次信息
     * @return 结果
     */
    @Override
    public int insertImHadoopBatch(ImHadoopBatch imHadoopBatch) {
        return imHadoopBatchMapper.insertImHadoopBatch(imHadoopBatch);
    }

    /**
     * 修改视频上传批次
     *
     * @param imHadoopBatch 视频上传批次信息
     * @return 结果
     */
    @Override
    public int updateImHadoopBatch(ImHadoopBatch imHadoopBatch) {
        return imHadoopBatchMapper.updateImHadoopBatch(imHadoopBatch);
    }

    /**
     * 删除视频上传批次对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImHadoopBatchByIds(String ids) {
        return imHadoopBatchMapper.deleteImHadoopBatchByIds(Convert.toStrArray(ids));
    }

}
