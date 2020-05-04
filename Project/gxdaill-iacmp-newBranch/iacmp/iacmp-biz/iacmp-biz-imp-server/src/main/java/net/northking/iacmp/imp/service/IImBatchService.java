package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImBatch;

import java.util.HashMap;
import java.util.List;

/**
 * 批次 服务层
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
public interface IImBatchService {
    /**
     * 查询批次信息
     *
     * @param id 批次ID
     * @return 批次信息
     */
    ImBatch selectImBatchById(String id);

    /**
     * 查询批次列表
     *
     * @param imBatch 批次信息
     * @return 批次集合
     */
    List<ImBatch> selectImBatchList(ImBatch imBatch);

    List<ImBatch> listWithUserCode(HashMap map);

    List<ImBatch> listAll(HashMap map);

    Integer countWithUserCode(HashMap map);

    Integer count(HashMap map);

    List<HashMap> historyList1(String operationcode);

    List<HashMap> historyList2(String operationcode);

    ImBatch selectImBatchByOpCode(String operationcode);

    /**
     * 新增批次
     *
     * @param imBatch 批次信息
     * @return 结果
     */
    int insertImBatch(ImBatch imBatch);

    /**
     * 修改批次
     *
     * @param imBatch 批次信息
     * @return 结果
     */
    int updateImBatch(ImBatch imBatch);

    /**
     * 删除批次信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImBatchByIds(String ids);

    /**
     * 通过流水号查找批次信息
     *
     * @param operationCode
     * @return
     */
    ImBatch selectImBatchByOperationCode(String operationCode);
}
