package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImHadoopVideoMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImHadoopVideo;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImHadoopVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 上传视频 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImHadoopVideoServiceImpl implements IImHadoopVideoService {
    @Autowired
    private ImHadoopVideoMapper imHadoopVideoMapper;

    /**
     * 查询上传视频信息
     *
     * @param id 上传视频ID
     * @return 上传视频信息
     */
    @Override
    public ImHadoopVideo selectImHadoopVideoById(String id) {
        return imHadoopVideoMapper.selectImHadoopVideoById(id);
    }

    /**
     * 查询上传视频列表
     *
     * @param imHadoopVideo 上传视频信息
     * @return 上传视频集合
     */
    @Override
    public List<ImHadoopVideo> selectImHadoopVideoList(ImHadoopVideo imHadoopVideo) {
        return imHadoopVideoMapper.selectImHadoopVideoList(imHadoopVideo);
    }

    /**
     * 新增上传视频
     *
     * @param imHadoopVideo 上传视频信息
     * @return 结果
     */
    @Override
    public int insertImHadoopVideo(ImHadoopVideo imHadoopVideo) {
        return imHadoopVideoMapper.insertImHadoopVideo(imHadoopVideo);
    }

    /**
     * 修改上传视频
     *
     * @param imHadoopVideo 上传视频信息
     * @return 结果
     */
    @Override
    public int updateImHadoopVideo(ImHadoopVideo imHadoopVideo) {
        return imHadoopVideoMapper.updateImHadoopVideo(imHadoopVideo);
    }

    /**
     * 删除上传视频对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImHadoopVideoByIds(String ids) {
        return imHadoopVideoMapper.deleteImHadoopVideoByIds(Convert.toStrArray(ids));
    }

}
