package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.WorkItem;

import java.util.List;

/**
 * 识别任务 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IWorkItemService {
    /**
     * 查询识别任务信息
     *
     * @param iD 识别任务ID
     * @return 识别任务信息
     */
    WorkItem selectWorkItemById(String iD);

    /**
     * 查询识别任务列表
     *
     * @param workItem 识别任务信息
     * @return 识别任务集合
     */
    List<WorkItem> selectWorkItemList(WorkItem workItem);

    /**
     * 新增识别任务
     *
     * @param workItem 识别任务信息
     * @return 结果
     */
    int insertWorkItem(WorkItem workItem);

    /**
     * 修改识别任务
     *
     * @param workItem 识别任务信息
     * @return 结果
     */
    int updateWorkItem(WorkItem workItem);

    /**
     * 删除识别任务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWorkItemByIds(String ids);

}
