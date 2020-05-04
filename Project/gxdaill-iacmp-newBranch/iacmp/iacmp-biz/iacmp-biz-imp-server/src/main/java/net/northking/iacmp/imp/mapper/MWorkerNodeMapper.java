package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.MWorkerNode;

import java.util.List;

/**
 * DB WorkerID Assigner for UID Generator 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface MWorkerNodeMapper {
    /**
     * 查询DB WorkerID Assigner for UID Generator信息
     *
     * @param id DB WorkerID Assigner for UID GeneratorID
     * @return DB WorkerID Assigner for UID Generator信息
     */
    MWorkerNode selectMWorkerNodeById(Long id);

    /**
     * 查询DB WorkerID Assigner for UID Generator列表
     *
     * @param mWorkerNode DB WorkerID Assigner for UID Generator信息
     * @return DB WorkerID Assigner for UID Generator集合
     */
    List<MWorkerNode> selectMWorkerNodeList(MWorkerNode mWorkerNode);

    /**
     * 新增DB WorkerID Assigner for UID Generator
     *
     * @param mWorkerNode DB WorkerID Assigner for UID Generator信息
     * @return 结果
     */
    int insertMWorkerNode(MWorkerNode mWorkerNode);

    /**
     * 修改DB WorkerID Assigner for UID Generator
     *
     * @param mWorkerNode DB WorkerID Assigner for UID Generator信息
     * @return 结果
     */
    int updateMWorkerNode(MWorkerNode mWorkerNode);

    /**
     * 删除DB WorkerID Assigner for UID Generator
     *
     * @param id DB WorkerID Assigner for UID GeneratorID
     * @return 结果
     */
    int deleteMWorkerNodeById(Long id);

    /**
     * 批量删除DB WorkerID Assigner for UID Generator
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteMWorkerNodeByIds(String[] ids);

}