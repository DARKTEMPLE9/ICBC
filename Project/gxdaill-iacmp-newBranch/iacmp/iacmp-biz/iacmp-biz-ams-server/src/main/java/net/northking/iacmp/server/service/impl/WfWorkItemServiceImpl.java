package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WfWorkItemMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WfWorkItem;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWfWorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程任务 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WfWorkItemServiceImpl implements IWfWorkItemService {
    @Autowired
    private WfWorkItemMapper wfWorkItemMapper;

    /**
     * 查询流程任务信息
     *
     * @param id 流程任务ID
     * @return 流程任务信息
     */
    @Override
    public WfWorkItem selectWfWorkItemById(String id) {
        return wfWorkItemMapper.selectWfWorkItemById(id);
    }

    /**
     * 查询流程任务列表
     *
     * @param wfWorkItem 流程任务信息
     * @return 流程任务集合
     */
    @Override
    public List<WfWorkItem> selectWfWorkItemList(WfWorkItem wfWorkItem) {
        return wfWorkItemMapper.selectWfWorkItemList(wfWorkItem);
    }

    /**
     * 新增流程任务
     *
     * @param wfWorkItem 流程任务信息
     * @return 结果
     */
    @Override
    public int insertWfWorkItem(WfWorkItem wfWorkItem) {
        return wfWorkItemMapper.insertWfWorkItem(wfWorkItem);
    }

    /**
     * 修改流程任务
     *
     * @param wfWorkItem 流程任务信息
     * @return 结果
     */
    @Override
    public int updateWfWorkItem(WfWorkItem wfWorkItem) {
        return wfWorkItemMapper.updateWfWorkItem(wfWorkItem);
    }

    /**
     * 删除流程任务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWfWorkItemByIds(String ids) {
        return wfWorkItemMapper.deleteWfWorkItemByIds(Convert.toStrArray(ids));
    }

}
