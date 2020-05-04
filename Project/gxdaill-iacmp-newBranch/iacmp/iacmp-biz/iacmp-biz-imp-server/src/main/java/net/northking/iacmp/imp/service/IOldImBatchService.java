package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.OldImBatch;

import java.util.List;

/**
 * 影像流水 服务层
 *
 * @author wei.chen
 * @date 2019-10-22
 */
public interface IOldImBatchService {
    /**
     * 查询影像流水信息
     *
     * @param id 影像流水ID
     * @return 影像流水信息
     */
    public OldImBatch selectImBatchById(String id);

    /**
     * 查询影像流水列表
     *
     * @param imBatch 影像流水信息
     * @return 影像流水集合
     */
    public List<OldImBatch> selectImBatchList(OldImBatch imBatch);

    /**
     * 新增影像流水
     *
     * @param imBatch 影像流水信息
     * @return 结果
     */
    public int insertImBatch(OldImBatch imBatch);

    /**
     * 修改影像流水
     *
     * @param imBatch 影像流水信息
     * @return 结果
     */
    public int updateImBatch(OldImBatch imBatch);

    /**
     * 删除影像流水信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImBatchByIds(String ids);

}
