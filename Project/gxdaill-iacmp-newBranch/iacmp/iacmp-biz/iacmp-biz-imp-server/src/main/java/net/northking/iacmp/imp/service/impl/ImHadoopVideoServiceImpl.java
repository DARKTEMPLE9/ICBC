package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImHadoopVideo;
import net.northking.iacmp.imp.mapper.ImHadoopVideoMapper;
import net.northking.iacmp.imp.service.IImHadoopVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 大数据视频 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImHadoopVideoServiceImpl implements IImHadoopVideoService {
    @Autowired
    private ImHadoopVideoMapper imHadoopVideoMapper;

    /**
     * 查询大数据视频信息
     *
     * @param id 大数据视频ID
     * @return 大数据视频信息
     */
    @Override
    public ImHadoopVideo selectImHadoopVideoById(String id) {
        return imHadoopVideoMapper.selectImHadoopVideoById(id);
    }

    /**
     * 查询大数据视频列表
     *
     * @param imHadoopVideo 大数据视频信息
     * @return 大数据视频集合
     */
    @Override
    public List<ImHadoopVideo> selectImHadoopVideoList(HashMap imHadoopVideo) {
        return imHadoopVideoMapper.selectImHadoopVideoList(imHadoopVideo);
    }

    @Override
    public Integer count(HashMap imHadoopVideo) {
        return imHadoopVideoMapper.count(imHadoopVideo);
    }

    @Override
    public List<ImHadoopVideo> selectByBatchId(HashMap batchId) {
        return imHadoopVideoMapper.selectByBatchId(batchId);
    }

    @Override
    public List<ImHadoopVideo> queryAll() {
        return imHadoopVideoMapper.queryAll();
    }

    /**
     * 新增大数据视频
     *
     * @param imHadoopVideo 大数据视频信息
     * @return 结果
     */
    @Override
    public Integer insertImHadoopVideo(ImHadoopVideo imHadoopVideo) {
        return imHadoopVideoMapper.insertImHadoopVideo(imHadoopVideo);
    }

    /**
     * 修改大数据视频
     *
     * @param imHadoopVideo 大数据视频信息
     * @return 结果
     */
    @Override
    public int updateImHadoopVideo(ImHadoopVideo imHadoopVideo) {
        return imHadoopVideoMapper.updateImHadoopVideo(imHadoopVideo);
    }

    /**
     * 删除大数据视频对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImHadoopVideoByIds(String ids) {
        return imHadoopVideoMapper.deleteImHadoopVideoByIds(Convert.toStrArray(ids));
    }

}
