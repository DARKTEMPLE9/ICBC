package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImBill;
import net.northking.iacmp.imp.mapper.ImBillMapper;
import net.northking.iacmp.imp.service.IImBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 影像分类 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImBillServiceImpl implements IImBillService {
    @Autowired
    private ImBillMapper imBillMapper;

    /**
     * 查询影像分类信息
     *
     * @param id 影像分类ID
     * @return 影像分类信息
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public ImBill selectImBillById(String id) {
        return imBillMapper.selectImBillById(id);
    }

    @Override
    public HashMap imBillById(String imBillId) {
        return imBillMapper.imBillById(imBillId);
    }

    /**
     * 查询影像分类列表
     *
     * @param imBill 影像分类信息
     * @return 影像分类集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImBill> selectImBillList(ImBill imBill) {
        return imBillMapper.selectImBillList(imBill);
    }

    @Override
    public List<ImBill> selectImBillListAll(HashMap map) {
        return imBillMapper.selectImBillListAll(map);
    }

    @Override
    public Integer selectImBillCount(HashMap map) {
        return imBillMapper.selectImBillCount(map);
    }

    @Override
    public Integer checkDistingct(HashMap map) {
        return imBillMapper.checkDistingct(map);
    }

    /**
     * 新增影像分类
     *
     * @param imBill 影像分类信息
     * @return 结果
     */
    @Override
    public Integer insertImBill(HashMap imBill) {
        return imBillMapper.insertImBill(imBill);
    }

    /**
     * 修改影像分类
     *
     * @param imBill 影像分类信息
     * @return 结果
     */
    @Override
    public Integer updateImBill(HashMap imBill) {
        return imBillMapper.updateImBill(imBill);
    }

    /**
     * 删除影像分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImBillByIds(String ids) {
        return imBillMapper.deleteImBillByIds(Convert.toStrArray(ids));
    }

    @Override
    public Integer deleteImBillById(String billId) {
        return imBillMapper.deleteImBillById(billId);
    }

}
