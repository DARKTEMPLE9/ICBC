package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImBatch;

import java.util.List;

/**
 * 影像批次 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImBatchMapper {
    /**
     * 查询影像批次信息
     *
     * @param id 影像批次ID
     * @return 影像批次信息
     */
    ImBatch selectImBatchById(String id);

    /**
     * 查询影像批次列表
     *
     * @param imBatch 影像批次信息
     * @return 影像批次集合
     */
    List<ImBatch> selectImBatchList(ImBatch imBatch);

    /**
     * 新增影像批次
     *
     * @param imBatch 影像批次信息
     * @return 结果
     */
    int insertImBatch(ImBatch imBatch);

    /**
     * 修改影像批次
     *
     * @param imBatch 影像批次信息
     * @return 结果
     */
    int updateImBatch(ImBatch imBatch);

    /**
     * 删除影像批次
     *
     * @param id 影像批次ID
     * @return 结果
     */
    int deleteImBatchById(String id);

    /**
     * 批量删除影像批次
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImBatchByIds(String[] ids);

}