package net.northking.iacmp.imp.service;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImBill;

import java.util.HashMap;
import java.util.List;

/**
 * 影像分类 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IImBillService {
    /**
     * 查询影像分类信息
     *
     * @param id 影像分类ID
     * @return 影像分类信息
     */
    ImBill selectImBillById(String id);

    HashMap imBillById(String imBillId);

    /**
     * 查询影像分类列表
     *
     * @param imBill 影像分类信息
     * @return 影像分类集合
     */
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    List<ImBill> selectImBillList(ImBill imBill);

    List<ImBill> selectImBillListAll(HashMap map);

    Integer selectImBillCount(HashMap map);

    Integer checkDistingct(HashMap map);

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
     * 删除影像分类信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImBillByIds(String ids);

    Integer deleteImBillById(String billId);

}
