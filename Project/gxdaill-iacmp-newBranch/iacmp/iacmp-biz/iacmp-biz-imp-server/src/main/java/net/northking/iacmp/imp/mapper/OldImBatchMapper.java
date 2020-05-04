package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.OldImBatch;

import java.util.List;

/**
 * 影像流水 数据层
 *
 * @author wei.chen
 * @date 2019-10-22
 */
public interface OldImBatchMapper {
    /**
     * 查询影像流水信息
     *
     * @param iD 影像流水ID
     * @return 影像流水信息
     */
    public OldImBatch selectImBatchById(String iD);

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
     * 删除影像流水
     *
     * @param id 影像流水ID
     * @return 结果
     */
    public int deleteImBatchById(String id);

    /**
     * 批量删除影像流水
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImBatchByIds(String[] ids);

}