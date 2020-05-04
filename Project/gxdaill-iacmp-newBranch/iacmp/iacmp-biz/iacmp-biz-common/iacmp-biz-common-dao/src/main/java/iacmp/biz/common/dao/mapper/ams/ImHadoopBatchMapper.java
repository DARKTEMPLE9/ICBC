package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImHadoopBatch;

import java.util.List;

/**
 * 视频上传批次 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImHadoopBatchMapper {
    /**
     * 查询视频上传批次信息
     *
     * @param id 视频上传批次ID
     * @return 视频上传批次信息
     */
    ImHadoopBatch selectImHadoopBatchById(String id);

    /**
     * 查询视频上传批次列表
     *
     * @param imHadoopBatch 视频上传批次信息
     * @return 视频上传批次集合
     */
    List<ImHadoopBatch> selectImHadoopBatchList(ImHadoopBatch imHadoopBatch);

    /**
     * 新增视频上传批次
     *
     * @param imHadoopBatch 视频上传批次信息
     * @return 结果
     */
    int insertImHadoopBatch(ImHadoopBatch imHadoopBatch);

    /**
     * 修改视频上传批次
     *
     * @param imHadoopBatch 视频上传批次信息
     * @return 结果
     */
    int updateImHadoopBatch(ImHadoopBatch imHadoopBatch);

    /**
     * 删除视频上传批次
     *
     * @param id 视频上传批次ID
     * @return 结果
     */
    int deleteImHadoopBatchById(String id);

    /**
     * 批量删除视频上传批次
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImHadoopBatchByIds(String[] ids);

}