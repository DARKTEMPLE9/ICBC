package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImBusino;
import net.northking.iacmp.imp.mapper.ImBusinoMapper;
import net.northking.iacmp.imp.service.IImBusinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 异常流水 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImBusinoServiceImpl implements IImBusinoService {
    @Autowired
    private ImBusinoMapper imBusinoMapper;

    /**
     * 查询异常流水信息
     *
     * @param id 异常流水ID
     * @return 异常流水信息
     */
    @Override
    public ImBusino selectImBusinoById(Long id) {
        return imBusinoMapper.selectImBusinoById(id);
    }

    /**
     * 查询异常流水列表
     *
     * @param imBusino 异常流水信息
     * @return 异常流水集合
     */
    @Override
    public List<ImBusino> selectImBusinoList(ImBusino imBusino) {
        return imBusinoMapper.selectImBusinoList(imBusino);
    }

    /**
     * 新增异常流水
     *
     * @param imBusino 异常流水信息
     * @return 结果
     */
    @Override
    public int insertImBusino(ImBusino imBusino) {
        return imBusinoMapper.insertImBusino(imBusino);
    }

    /**
     * 修改异常流水
     *
     * @param imBusino 异常流水信息
     * @return 结果
     */
    @Override
    public int updateImBusino(ImBusino imBusino) {
        return imBusinoMapper.updateImBusino(imBusino);
    }

    /**
     * 删除异常流水对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImBusinoByIds(String ids) {
        return imBusinoMapper.deleteImBusinoByIds(Convert.toStrArray(ids));
    }

}
