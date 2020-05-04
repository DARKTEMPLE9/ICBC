package net.northking.iacmp.imp.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.OldImBatch;
import net.northking.iacmp.imp.mapper.OldImBatchMapper;
import net.northking.iacmp.imp.service.IOldImBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 影像流水 服务层实现
 *
 * @author wei.chen
 * @date 2019-10-22
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class OldImBatchServiceImpl implements IOldImBatchService {
    @Autowired
    private OldImBatchMapper imBatchMapper;

    /**
     * 查询影像流水信息
     *
     * @param id 影像流水ID
     * @return 影像流水信息
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public OldImBatch selectImBatchById(String id) {
        return imBatchMapper.selectImBatchById(id);
    }

    /**
     * 查询影像流水列表
     *
     * @param imBatch 影像流水信息
     * @return 影像流水集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<OldImBatch> selectImBatchList(OldImBatch imBatch) {
        return imBatchMapper.selectImBatchList(imBatch);
    }

    /**
     * 新增影像流水
     *
     * @param imBatch 影像流水信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImBatch(@RequestBody OldImBatch imBatch) {
        return imBatchMapper.insertImBatch(imBatch);
    }

    /**
     * 修改影像流水
     *
     * @param imBatch 影像流水信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int updateImBatch(@RequestBody OldImBatch imBatch) {
        return imBatchMapper.updateImBatch(imBatch);
    }

    /**
     * 删除影像流水对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int deleteImBatchByIds(String ids) {
        return imBatchMapper.deleteImBatchByIds(Convert.toStrArray(ids));
    }

}
