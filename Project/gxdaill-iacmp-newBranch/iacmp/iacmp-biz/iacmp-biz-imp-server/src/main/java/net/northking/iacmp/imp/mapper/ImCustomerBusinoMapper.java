package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImCustomerBusino;

import java.util.List;

/**
 * 客户影像流水反向索引 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImCustomerBusinoMapper {
    /**
     * 查询客户影像流水反向索引信息
     *
     * @param id 客户影像流水反向索引ID
     * @return 客户影像流水反向索引信息
     */
    ImCustomerBusino selectImCustomerBusinoById(Long id);

    /**
     * 查询客户影像流水反向索引列表
     *
     * @return 客户影像流水反向索引集合
     */
    List<String> selectRegbillglidenos(String userCode);

    /**
     * 新增客户影像流水反向索引
     *
     * @param imCustomerBusino 客户影像流水反向索引信息
     * @return 结果
     */
    int insertImCustomerBusino(ImCustomerBusino imCustomerBusino);

    /**
     * 修改客户影像流水反向索引
     *
     * @param imCustomerBusino 客户影像流水反向索引信息
     * @return 结果
     */
    int updateImCustomerBusino(ImCustomerBusino imCustomerBusino);

    /**
     * 删除客户影像流水反向索引
     *
     * @param id 客户影像流水反向索引ID
     * @return 结果
     */
    int deleteImCustomerBusinoById(Long id);

    /**
     * 批量删除客户影像流水反向索引
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImCustomerBusinoByIds(String[] ids);

    /**
     * 根据客户号查询客户影像流水反向索引列表
     *
     * @param userCodes 客户号
     * @return 客户影像流水反向索引集合
     */
    List<ImCustomerBusino> selectImCustomerBusinoByUserCodes(String[] userCodes);

}