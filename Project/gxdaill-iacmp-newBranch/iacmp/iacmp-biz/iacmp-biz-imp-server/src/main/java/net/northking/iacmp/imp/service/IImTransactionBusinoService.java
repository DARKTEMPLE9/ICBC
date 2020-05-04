package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImTransactionBusino;

import java.util.List;

/**
 * 全局流水影像流水反向索引 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IImTransactionBusinoService {
    /**
     * 查询全局流水影像流水反向索引信息
     *
     * @param iD 全局流水影像流水反向索引ID
     * @return 全局流水影像流水反向索引信息
     */
    ImTransactionBusino selectImTransactionBusinoById(String iD);

    /**
     * 查询全局流水影像流水反向索引列表
     *
     * @param imTransactionBusino 全局流水影像流水反向索引信息
     * @return 全局流水影像流水反向索引集合
     */
    List<ImTransactionBusino> selectImTransactionBusinoList(ImTransactionBusino imTransactionBusino);

    /**
     * 新增全局流水影像流水反向索引
     *
     * @param imTransactionBusino 全局流水影像流水反向索引信息
     * @return 结果
     */
    int insertImTransactionBusino(ImTransactionBusino imTransactionBusino);

    /**
     * 修改全局流水影像流水反向索引
     *
     * @param imTransactionBusino 全局流水影像流水反向索引信息
     * @return 结果
     */
    int updateImTransactionBusino(ImTransactionBusino imTransactionBusino);

    /**
     * 删除全局流水影像流水反向索引信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImTransactionBusinoByIds(String ids);

    /**
     * 根据全局流水号查询全局流水影像流水反向索引列表
     *
     * @param sysBusiNo 全局流水号
     * @return 全局流水影像流水反向索引集合
     */
    List<ImTransactionBusino> selectImTransactionBusinoBySysBusiNo(String sysBusiNo);

}
