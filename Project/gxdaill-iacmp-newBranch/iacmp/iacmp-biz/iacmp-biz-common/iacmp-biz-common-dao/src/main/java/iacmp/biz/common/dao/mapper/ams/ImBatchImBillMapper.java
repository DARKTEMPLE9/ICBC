package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImBatchImBill;

import java.util.List;

/**
 * 影像批次类型 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImBatchImBillMapper {
    /**
     * 查询影像批次类型信息
     *
     * @param id 影像批次类型ID
     * @return 影像批次类型信息
     */
    ImBatchImBill selectImBatchImBillById(String id);

    /**
     * 查询影像批次类型列表
     *
     * @param imBatchImBill 影像批次类型信息
     * @return 影像批次类型集合
     */
    List<ImBatchImBill> selectImBatchImBillList(ImBatchImBill imBatchImBill);

    /**
     * 新增影像批次类型
     *
     * @param imBatchImBill 影像批次类型信息
     * @return 结果
     */
    int insertImBatchImBill(ImBatchImBill imBatchImBill);

    /**
     * 修改影像批次类型
     *
     * @param imBatchImBill 影像批次类型信息
     * @return 结果
     */
    int updateImBatchImBill(ImBatchImBill imBatchImBill);

    /**
     * 删除影像批次类型
     *
     * @param id 影像批次类型ID
     * @return 结果
     */
    int deleteImBatchImBillById(String id);

    /**
     * 批量删除影像批次类型
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImBatchImBillByIds(String[] ids);

}