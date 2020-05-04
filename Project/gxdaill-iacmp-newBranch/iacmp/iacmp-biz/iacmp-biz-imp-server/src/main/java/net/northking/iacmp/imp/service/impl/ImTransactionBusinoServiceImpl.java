package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImTransactionBusino;
import net.northking.iacmp.imp.mapper.ImTransactionBusinoMapper;
import net.northking.iacmp.imp.service.IImTransactionBusinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 全局流水影像流水反向索引 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImTransactionBusinoServiceImpl implements IImTransactionBusinoService {
    @Autowired
    private ImTransactionBusinoMapper imTransactionBusinoMapper;

    /**
     * 查询全局流水影像流水反向索引信息
     *
     * @param id 全局流水影像流水反向索引ID
     * @return 全局流水影像流水反向索引信息
     */
    @Override
    public ImTransactionBusino selectImTransactionBusinoById(String id) {
        return imTransactionBusinoMapper.selectImTransactionBusinoById(id);
    }

    /**
     * 查询全局流水影像流水反向索引列表
     *
     * @param imTransactionBusino 全局流水影像流水反向索引信息
     * @return 全局流水影像流水反向索引集合
     */
    @Override
    public List<ImTransactionBusino> selectImTransactionBusinoList(ImTransactionBusino imTransactionBusino) {
        return imTransactionBusinoMapper.selectImTransactionBusinoList(imTransactionBusino);
    }

    /**
     * 新增全局流水影像流水反向索引
     *
     * @param imTransactionBusino 全局流水影像流水反向索引信息
     * @return 结果
     */
    @Override
    public int insertImTransactionBusino(ImTransactionBusino imTransactionBusino) {
        return imTransactionBusinoMapper.insertImTransactionBusino(imTransactionBusino);
    }

    /**
     * 修改全局流水影像流水反向索引
     *
     * @param imTransactionBusino 全局流水影像流水反向索引信息
     * @return 结果
     */
    @Override
    public int updateImTransactionBusino(ImTransactionBusino imTransactionBusino) {
        return imTransactionBusinoMapper.updateImTransactionBusino(imTransactionBusino);
    }

    /**
     * 删除全局流水影像流水反向索引对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImTransactionBusinoByIds(String ids) {
        return imTransactionBusinoMapper.deleteImTransactionBusinoByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据全局流水号查询全局流水影像流水反向索引列表
     *
     * @param sysBusiNo 全局流水号
     * @return 全局流水影像流水反向索引集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImTransactionBusino> selectImTransactionBusinoBySysBusiNo(String sysBusiNo) {
        return imTransactionBusinoMapper.selectImTransactionBusinoBySysBusiNo(sysBusiNo);
    }

}
