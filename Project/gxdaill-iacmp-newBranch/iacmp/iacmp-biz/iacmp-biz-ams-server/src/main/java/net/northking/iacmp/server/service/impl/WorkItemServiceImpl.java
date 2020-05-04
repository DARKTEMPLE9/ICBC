package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WorkItemMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WorkItem;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务流程 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WorkItemServiceImpl implements IWorkItemService {
    @Autowired
    private WorkItemMapper workItemMapper;

    /**
     * 查询业务流程信息
     *
     * @param id 业务流程ID
     * @return 业务流程信息
     */
    @Override
    public WorkItem selectWorkItemById(String id) {
        return workItemMapper.selectWorkItemById(id);
    }

    /**
     * 查询业务流程列表
     *
     * @param workItem 业务流程信息
     * @return 业务流程集合
     */
    @Override
    public List<WorkItem> selectWorkItemList(WorkItem workItem) {
        return workItemMapper.selectWorkItemList(workItem);
    }

    /**
     * 新增业务流程
     *
     * @param workItem 业务流程信息
     * @return 结果
     */
    @Override
    public int insertWorkItem(WorkItem workItem) {
        return workItemMapper.insertWorkItem(workItem);
    }

    /**
     * 修改业务流程
     *
     * @param workItem 业务流程信息
     * @return 结果
     */
    @Override
    public int updateWorkItem(WorkItem workItem) {
        return workItemMapper.updateWorkItem(workItem);
    }

    /**
     * 删除业务流程对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkItemByIds(String ids) {
        return workItemMapper.deleteWorkItemByIds(Convert.toStrArray(ids));
    }

}
