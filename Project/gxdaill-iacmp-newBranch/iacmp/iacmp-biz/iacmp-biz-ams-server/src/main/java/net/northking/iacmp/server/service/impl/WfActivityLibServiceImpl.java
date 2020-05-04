package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WfActivityLibMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WfActivityLib;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWfActivityLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程节点关系 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WfActivityLibServiceImpl implements IWfActivityLibService {
    @Autowired
    private WfActivityLibMapper wfActivityLibMapper;

    /**
     * 查询流程节点关系信息
     *
     * @param id 流程节点关系ID
     * @return 流程节点关系信息
     */
    @Override
    public WfActivityLib selectWfActivityLibById(String id) {
        return wfActivityLibMapper.selectWfActivityLibById(id);
    }

    /**
     * 查询流程节点关系列表
     *
     * @param wfActivityLib 流程节点关系信息
     * @return 流程节点关系集合
     */
    @Override
    public List<WfActivityLib> selectWfActivityLibList(WfActivityLib wfActivityLib) {
        return wfActivityLibMapper.selectWfActivityLibList(wfActivityLib);
    }

    /**
     * 新增流程节点关系
     *
     * @param wfActivityLib 流程节点关系信息
     * @return 结果
     */
    @Override
    public int insertWfActivityLib(WfActivityLib wfActivityLib) {
        return wfActivityLibMapper.insertWfActivityLib(wfActivityLib);
    }

    /**
     * 修改流程节点关系
     *
     * @param wfActivityLib 流程节点关系信息
     * @return 结果
     */
    @Override
    public int updateWfActivityLib(WfActivityLib wfActivityLib) {
        return wfActivityLibMapper.updateWfActivityLib(wfActivityLib);
    }

    /**
     * 删除流程节点关系对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWfActivityLibByIds(String ids) {
        return wfActivityLibMapper.deleteWfActivityLibByIds(Convert.toStrArray(ids));
    }

}
