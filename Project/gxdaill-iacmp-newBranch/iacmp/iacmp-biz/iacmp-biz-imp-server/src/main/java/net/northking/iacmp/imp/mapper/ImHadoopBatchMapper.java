package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImHadoopBatch;

import java.util.HashMap;
import java.util.List;

/**
 * 大数据批次 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImHadoopBatchMapper {
    /**
     * 查询大数据批次信息
     *
     * @param id 大数据批次ID
     * @return 大数据批次信息
     */
    ImHadoopBatch selectImHadoopBatchById(String id);

    /**
     * 查询大数据批次列表
     *
     * @param imHadoopBatch 大数据批次信息
     * @return 大数据批次集合
     */
    List<ImHadoopBatch> selectImHadoopBatchList(HashMap imHadoopBatch);

    Integer count(HashMap map);

    List<ImHadoopBatch> selectByRegno(String regno);

    /**
     * 新增大数据批次
     *
     * @param imHadoopBatch 大数据批次信息
     * @return 结果
     */
    Integer insertImHadoopBatch(HashMap imHadoopBatch);

    /**
     * 修改大数据批次
     *
     * @param imHadoopBatch 大数据批次信息
     * @return 结果
     */
    int updateImHadoopBatch(ImHadoopBatch imHadoopBatch);

    /**
     * 删除大数据批次
     *
     * @param id 大数据批次ID
     * @return 结果
     */
    int deleteImHadoopBatchById(String id);

    /**
     * 批量删除大数据批次
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImHadoopBatchByIds(String[] ids);

}