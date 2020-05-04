package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.WorkItem;
import net.northking.iacmp.imp.mapper.WorkItemMapper;
import net.northking.iacmp.imp.service.IWorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 识别任务 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WorkItemServiceImpl implements IWorkItemService {
    @Autowired
    private WorkItemMapper workItemMapper;

    /**
     * 查询识别任务信息
     *
     * @param id 识别任务ID
     * @return 识别任务信息
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public WorkItem selectWorkItemById(String id) {
        return workItemMapper.selectWorkItemById(id);
    }

    /**
     * 查询识别任务列表
     *
     * @param workItem 识别任务信息
     * @return 识别任务集合
     */
    @Override
    public List<WorkItem> selectWorkItemList(WorkItem workItem) {
        return workItemMapper.selectWorkItemList(workItem);
    }

    /**
     * 新增识别任务
     *
     * @param workItem 识别任务信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertWorkItem(@RequestBody WorkItem workItem) {
        return workItemMapper.insertWorkItem(workItem);
    }

    /**
     * 修改识别任务
     *
     * @param workItem 识别任务信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int updateWorkItem(@RequestBody WorkItem workItem) {
        return workItemMapper.updateWorkItem(workItem);
    }

    /**
     * 删除识别任务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkItemByIds(String ids) {
        return workItemMapper.deleteWorkItemByIds(Convert.toStrArray(ids));
    }

}
