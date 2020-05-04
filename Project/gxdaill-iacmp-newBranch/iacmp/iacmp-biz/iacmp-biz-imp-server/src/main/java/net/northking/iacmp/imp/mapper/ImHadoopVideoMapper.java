package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImHadoopVideo;

import java.util.HashMap;
import java.util.List;

/**
 * 大数据视频 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImHadoopVideoMapper {
    /**
     * 查询大数据视频信息
     *
     * @param id 大数据视频ID
     * @return 大数据视频信息
     */
    ImHadoopVideo selectImHadoopVideoById(String id);

    /**
     * 查询大数据视频列表
     *
     * @param imHadoopVideo 大数据视频信息
     * @return 大数据视频集合
     */
    List<ImHadoopVideo> selectImHadoopVideoList(HashMap imHadoopVideo);

    Integer count(HashMap imHadoopVideo);

    List<ImHadoopVideo> selectByBatchId(HashMap batchId);

    List<ImHadoopVideo> queryAll();

    /**
     * 新增大数据视频
     *
     * @param imHadoopVideo 大数据视频信息
     * @return 结果
     */
    Integer insertImHadoopVideo(ImHadoopVideo imHadoopVideo);

    /**
     * 修改大数据视频
     *
     * @param imHadoopVideo 大数据视频信息
     * @return 结果
     */
    int updateImHadoopVideo(ImHadoopVideo imHadoopVideo);

    /**
     * 删除大数据视频
     *
     * @param id 大数据视频ID
     * @return 结果
     */
    int deleteImHadoopVideoById(String id);

    /**
     * 批量删除大数据视频
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImHadoopVideoByIds(String[] ids);

}