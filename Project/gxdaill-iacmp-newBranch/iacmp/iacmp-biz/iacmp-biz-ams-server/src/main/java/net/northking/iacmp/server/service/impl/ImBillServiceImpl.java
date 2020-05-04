package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImBillMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImBill;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件类型 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImBillServiceImpl implements IImBillService {
    @Autowired
    private ImBillMapper imBillMapper;

    /**
     * 查询文件类型信息
     *
     * @param id 文件类型ID
     * @return 文件类型信息
     */
    @Override
    public ImBill selectImBillById(String id) {
        return imBillMapper.selectImBillById(id);
    }

    /**
     * 查询文件类型列表
     *
     * @param imBill 文件类型信息
     * @return 文件类型集合
     */
    @Override
    public List<ImBill> selectImBillList(ImBill imBill) {
        return imBillMapper.selectImBillList(imBill);
    }

    /**
     * 新增文件类型
     *
     * @param imBill 文件类型信息
     * @return 结果
     */
    @Override
    public int insertImBill(ImBill imBill) {
        return imBillMapper.insertImBill(imBill);
    }

    /**
     * 修改文件类型
     *
     * @param imBill 文件类型信息
     * @return 结果
     */
    @Override
    public int updateImBill(ImBill imBill) {
        return imBillMapper.updateImBill(imBill);
    }

    /**
     * 删除文件类型对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImBillByIds(String ids) {
        return imBillMapper.deleteImBillByIds(Convert.toStrArray(ids));
    }

}
