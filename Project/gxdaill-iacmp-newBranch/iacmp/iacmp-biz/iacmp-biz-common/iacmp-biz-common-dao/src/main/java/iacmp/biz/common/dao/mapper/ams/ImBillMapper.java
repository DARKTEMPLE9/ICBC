package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImBill;

import java.util.List;

/**
 * 文件类型 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImBillMapper {
    /**
     * 查询文件类型信息
     *
     * @param id 文件类型ID
     * @return 文件类型信息
     */
    ImBill selectImBillById(String id);

    /**
     * 查询文件类型列表
     *
     * @param imBill 文件类型信息
     * @return 文件类型集合
     */
    List<ImBill> selectImBillList(ImBill imBill);

    /**
     * 新增文件类型
     *
     * @param imBill 文件类型信息
     * @return 结果
     */
    int insertImBill(ImBill imBill);

    /**
     * 修改文件类型
     *
     * @param imBill 文件类型信息
     * @return 结果
     */
    int updateImBill(ImBill imBill);

    /**
     * 删除文件类型
     *
     * @param id 文件类型ID
     * @return 结果
     */
    int deleteImBillById(String id);

    /**
     * 批量删除文件类型
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImBillByIds(String[] ids);

}