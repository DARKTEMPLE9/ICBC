package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImBill;

import java.util.HashMap;
import java.util.List;

/**
 * 影像分类 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImBillMapper {
    /**
     * 查询影像分类信息
     *
     * @param id 影像分类ID
     * @return 影像分类信息
     */
    ImBill selectImBillById(String id);

    List<ImBill> selectImBillListAll(HashMap map);

    Integer selectImBillCount(HashMap map);

    Integer checkDistingct(HashMap map);

    HashMap imBillById(String imBillId);

    /**
     * 查询影像分类列表
     *
     * @param imBill 影像分类信息
     * @return 影像分类集合
     */
    List<ImBill> selectImBillList(ImBill imBill);

    /**
     * 新增影像分类
     *
     * @param imBill 影像分类信息
     * @return 结果
     */
    Integer insertImBill(HashMap imBill);

    /**
     * 修改影像分类
     *
     * @param imBill 影像分类信息
     * @return 结果
     */
    Integer updateImBill(HashMap imBill);

    /**
     * 删除影像分类
     *
     * @param id 影像分类ID
     * @return 结果
     */
    Integer deleteImBillById(String billId);

    /**
     * 批量删除影像分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImBillByIds(String[] ids);

}