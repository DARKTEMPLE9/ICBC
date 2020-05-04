package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.ImHadoopVideo;

import java.util.List;

/**
 * 上传视频 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IImHadoopVideoService {
    /**
     * 查询上传视频信息
     *
     * @param id 上传视频ID
     * @return 上传视频信息
     */
    public ImHadoopVideo selectImHadoopVideoById(String id);

    /**
     * 查询上传视频列表
     *
     * @param imHadoopVideo 上传视频信息
     * @return 上传视频集合
     */
    public List<ImHadoopVideo> selectImHadoopVideoList(ImHadoopVideo imHadoopVideo);

    /**
     * 新增上传视频
     *
     * @param imHadoopVideo 上传视频信息
     * @return 结果
     */
    public int insertImHadoopVideo(ImHadoopVideo imHadoopVideo);

    /**
     * 修改上传视频
     *
     * @param imHadoopVideo 上传视频信息
     * @return 结果
     */
    public int updateImHadoopVideo(ImHadoopVideo imHadoopVideo);

    /**
     * 删除上传视频信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImHadoopVideoByIds(String ids);

}
