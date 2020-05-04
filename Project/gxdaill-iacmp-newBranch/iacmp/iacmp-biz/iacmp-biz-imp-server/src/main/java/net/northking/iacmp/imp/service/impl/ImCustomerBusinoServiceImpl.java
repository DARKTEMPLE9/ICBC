package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImCustomerBusino;
import net.northking.iacmp.imp.mapper.ImCustomerBusinoMapper;
import net.northking.iacmp.imp.service.IImCustomerBusinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户影像流水反向索引 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImCustomerBusinoServiceImpl implements IImCustomerBusinoService {
    @Autowired
    private ImCustomerBusinoMapper imCustomerBusinoMapper;

    /**
     * 查询客户影像流水反向索引信息
     *
     * @param id 客户影像流水反向索引ID
     * @return 客户影像流水反向索引信息
     */
    @Override
    public ImCustomerBusino selectImCustomerBusinoById(Long id) {
        return imCustomerBusinoMapper.selectImCustomerBusinoById(id);
    }

    /**
     * 查询客户影像流水反向索引列表
     *
     * @param imCustomerBusino 客户影像流水反向索引信息
     * @return 客户影像流水反向索引集合
     */
    @Override
    public List<String> selectRegbillglidenos(String userCode) {
        return imCustomerBusinoMapper.selectRegbillglidenos(userCode);
    }

    /**
     * 新增客户影像流水反向索引
     *
     * @param imCustomerBusino 客户影像流水反向索引信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImCustomerBusino(ImCustomerBusino imCustomerBusino) {
        return imCustomerBusinoMapper.insertImCustomerBusino(imCustomerBusino);
    }

    /**
     * 修改客户影像流水反向索引
     *
     * @param imCustomerBusino 客户影像流水反向索引信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int updateImCustomerBusino(ImCustomerBusino imCustomerBusino) {
        return imCustomerBusinoMapper.updateImCustomerBusino(imCustomerBusino);
    }

    /**
     * 删除客户影像流水反向索引对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImCustomerBusinoByIds(String ids) {
        return imCustomerBusinoMapper.deleteImCustomerBusinoByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据客户号查询客户影像流水反向索引列表
     *
     * @param userCodes 客户号
     * @return 客户影像流水反向索引集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImCustomerBusino> selectImCustomerBusinoByUserCodes(String userCodes) {
        return imCustomerBusinoMapper.selectImCustomerBusinoByUserCodes(Convert.toStrArray(userCodes));
    }

}
