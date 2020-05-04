package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.MWorkerNode;
import net.northking.iacmp.imp.mapper.MWorkerNodeMapper;
import net.northking.iacmp.imp.service.IMWorkerNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DB WorkerID Assigner for UID Generator 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class MWorkerNodeServiceImpl implements IMWorkerNodeService {
    @Autowired
    private MWorkerNodeMapper mWorkerNodeMapper;

    /**
     * 查询DB WorkerID Assigner for UID Generator信息
     *
     * @param id DB WorkerID Assigner for UID GeneratorID
     * @return DB WorkerID Assigner for UID Generator信息
     */
    @Override
    public MWorkerNode selectMWorkerNodeById(Long id) {
        return mWorkerNodeMapper.selectMWorkerNodeById(id);
    }

    /**
     * 查询DB WorkerID Assigner for UID Generator列表
     *
     * @param mWorkerNode DB WorkerID Assigner for UID Generator信息
     * @return DB WorkerID Assigner for UID Generator集合
     */
    @Override
    public List<MWorkerNode> selectMWorkerNodeList(MWorkerNode mWorkerNode) {
        return mWorkerNodeMapper.selectMWorkerNodeList(mWorkerNode);
    }

    /**
     * 新增DB WorkerID Assigner for UID Generator
     *
     * @param mWorkerNode DB WorkerID Assigner for UID Generator信息
     * @return 结果
     */
    @Override
    public int insertMWorkerNode(MWorkerNode mWorkerNode) {
        return mWorkerNodeMapper.insertMWorkerNode(mWorkerNode);
    }

    /**
     * 修改DB WorkerID Assigner for UID Generator
     *
     * @param mWorkerNode DB WorkerID Assigner for UID Generator信息
     * @return 结果
     */
    @Override
    public int updateMWorkerNode(MWorkerNode mWorkerNode) {
        return mWorkerNodeMapper.updateMWorkerNode(mWorkerNode);
    }

    /**
     * 删除DB WorkerID Assigner for UID Generator对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMWorkerNodeByIds(String ids) {
        return mWorkerNodeMapper.deleteMWorkerNodeByIds(Convert.toStrArray(ids));
    }

}
