package net.northking.iacmp.imp.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImBatch;
import net.northking.iacmp.imp.mapper.ImBatchMapper;
import net.northking.iacmp.imp.service.IImBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 批次 服务层实现
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImBatchServiceImpl implements IImBatchService {
    @Autowired
    private ImBatchMapper imBatchMapper;

    /**
     * 查询批次信息
     *
     * @param busino 批次
     * @return 批次信息
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public ImBatch selectImBatchById(String busino) {
        return imBatchMapper.selectImBatchById(busino);
    }

    /**
     * 查询批次列表
     *
     * @param imBatch 批次信息
     * @return 批次集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImBatch> selectImBatchList(ImBatch imBatch) {
        return imBatchMapper.selectImBatchList(imBatch);
    }

    /**
     * 历史影像查询
     */
    @Override
    public List<ImBatch> listWithUserCode(HashMap map) {
        return imBatchMapper.listWithUserCode(map);
    }

    @Override
    public List<ImBatch> listAll(HashMap map) {
        return imBatchMapper.listAll(map);
    }

    @Override
    public Integer countWithUserCode(HashMap map) {
        return imBatchMapper.countWithUserCode(map);
    }

    @Override
    public Integer count(HashMap map) {
        return imBatchMapper.count(map);
    }

    /**
     * 历史轨迹查询
     */
    @Override
    public List<HashMap> historyList1(String operationcode) {
        return imBatchMapper.historyList1(operationcode);
    }

    @Override
    public List<HashMap> historyList2(String operationcode) {
        return imBatchMapper.historyList2(operationcode);
    }

    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public ImBatch selectImBatchByOpCode(String operationcode) {
        return imBatchMapper.selectImBatchByOpCode(operationcode);
    }

    /**
     * 新增批次
     *
     * @param ImBatch 批次信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public int insertImBatch(ImBatch ImBatch) {
        return imBatchMapper.insertImBatch(ImBatch);
    }

    /**
     * 修改批次
     *
     * @param ImBatch 批次信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public int updateImBatch(ImBatch ImBatch) {
        return imBatchMapper.updateImBatch(ImBatch);
    }

    /**
     * 删除批次对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public int deleteImBatchByIds(String ids) {
        return imBatchMapper.deleteImBatchByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过流水号查找流水信息
     *
     * @param operationCode
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public ImBatch selectImBatchByOperationCode(String operationCode) {
        return imBatchMapper.selectImBatchByOperationCode(operationCode);
    }

}
