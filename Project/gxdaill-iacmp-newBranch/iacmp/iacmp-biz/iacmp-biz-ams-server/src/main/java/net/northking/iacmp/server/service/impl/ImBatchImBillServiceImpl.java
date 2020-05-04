package net.northking.iacmp.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.ImBatchImBillMapper;
import net.northking.iacmp.common.bean.domain.ams.ImBatchImBill;
import net.northking.iacmp.server.service.IImBatchImBillService;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

/**
 * 影像批次类型 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImBatchImBillServiceImpl implements IImBatchImBillService {
    @Autowired
    private ImBatchImBillMapper imBatchImBillMapper;

    /**
     * 查询影像批次类型信息
     *
     * @param id 影像批次类型ID
     * @return 影像批次类型信息
     */
    @Override
    public ImBatchImBill selectImBatchImBillById(String id) {
        return imBatchImBillMapper.selectImBatchImBillById(id);
    }

    /**
     * 查询影像批次类型列表
     *
     * @param imBatchImBill 影像批次类型信息
     * @return 影像批次类型集合
     */
    @Override
    public List<ImBatchImBill> selectImBatchImBillList(ImBatchImBill imBatchImBill) {
        return imBatchImBillMapper.selectImBatchImBillList(imBatchImBill);
    }

    /**
     * 新增影像批次类型
     *
     * @param imBatchImBill 影像批次类型信息
     * @return 结果
     */
    @Override
    public int insertImBatchImBill(ImBatchImBill imBatchImBill) {
        return imBatchImBillMapper.insertImBatchImBill(imBatchImBill);
    }

    /**
     * 修改影像批次类型
     *
     * @param imBatchImBill 影像批次类型信息
     * @return 结果
     */
    @Override
    public int updateImBatchImBill(ImBatchImBill imBatchImBill) {
        return imBatchImBillMapper.updateImBatchImBill(imBatchImBill);
    }

    /**
     * 删除影像批次类型对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImBatchImBillByIds(String ids) {
        return imBatchImBillMapper.deleteImBatchImBillByIds(Convert.toStrArray(ids));
    }

}
