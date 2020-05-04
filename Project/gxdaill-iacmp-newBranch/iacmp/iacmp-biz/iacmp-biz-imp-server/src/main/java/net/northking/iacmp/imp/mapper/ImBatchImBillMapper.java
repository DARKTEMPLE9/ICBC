package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImBatchImBill;

import java.util.List;

/**
 * 影像类型流水关联 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImBatchImBillMapper {
    /**
     * 查询影像类型流水关联信息
     *
     * @param id 影像类型流水关联ID
     * @return 影像类型流水关联信息
     */
    ImBatchImBill selectImBatchImBillById(String id);

    /**
     * 查询影像类型流水关联列表
     *
     * @param imBatchImBill 影像类型流水关联信息
     * @return 影像类型流水关联集合
     */
    List<ImBatchImBill> selectImBatchImBillList(ImBatchImBill imBatchImBill);

    /**
     * 新增影像类型流水关联
     *
     * @param imBatchImBill 影像类型流水关联信息
     * @return 结果
     */
    int insertImBatchImBill(ImBatchImBill imBatchImBill);

    /**
     * 修改影像类型流水关联
     *
     * @param imBatchImBill 影像类型流水关联信息
     * @return 结果
     */
    int updateImBatchImBill(ImBatchImBill imBatchImBill);

    /**
     * 删除影像类型流水关联
     *
     * @param id 影像类型流水关联ID
     * @return 结果
     */
    int deleteImBatchImBillById(String id);

    /**
     * 批量删除影像类型流水关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImBatchImBillByIds(String[] ids);

}