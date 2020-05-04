package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImBatchImBill;
import net.northking.iacmp.imp.mapper.ImBatchImBillMapper;
import net.northking.iacmp.imp.service.IImBatchImBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 影像类型流水关联 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImBatchImBillServiceImpl implements IImBatchImBillService {
    @Autowired
    private ImBatchImBillMapper imBatchImBillMapper;

    /**
     * 查询影像类型流水关联信息
     *
     * @param id 影像类型流水关联ID
     * @return 影像类型流水关联信息
     */
    @Override
    public ImBatchImBill selectImBatchImBillById(String id) {
        return imBatchImBillMapper.selectImBatchImBillById(id);
    }

    /**
     * 查询影像类型流水关联列表
     *
     * @param imBatchImBill 影像类型流水关联信息
     * @return 影像类型流水关联集合
     */
    @Override
    public List<ImBatchImBill> selectImBatchImBillList(ImBatchImBill imBatchImBill) {
        return imBatchImBillMapper.selectImBatchImBillList(imBatchImBill);
    }

    /**
     * 新增影像类型流水关联
     *
     * @param imBatchImBill 影像类型流水关联信息
     * @return 结果
     */
    @Override
    public int insertImBatchImBill(ImBatchImBill imBatchImBill) {
        return imBatchImBillMapper.insertImBatchImBill(imBatchImBill);
    }

    /**
     * 修改影像类型流水关联
     *
     * @param imBatchImBill 影像类型流水关联信息
     * @return 结果
     */
    @Override
    public int updateImBatchImBill(ImBatchImBill imBatchImBill) {
        return imBatchImBillMapper.updateImBatchImBill(imBatchImBill);
    }

    /**
     * 删除影像类型流水关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImBatchImBillByIds(String ids) {
        return imBatchImBillMapper.deleteImBatchImBillByIds(Convert.toStrArray(ids));
    }

}
