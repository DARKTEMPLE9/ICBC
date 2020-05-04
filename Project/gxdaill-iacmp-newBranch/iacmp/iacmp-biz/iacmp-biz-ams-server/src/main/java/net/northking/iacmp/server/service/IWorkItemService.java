package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.WorkItem;

import java.util.List;

/**
 * 业务流程 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IWorkItemService {
    /**
     * 查询业务流程信息
     *
     * @param id 业务流程ID
     * @return 业务流程信息
     */
    public WorkItem selectWorkItemById(String id);

    /**
     * 查询业务流程列表
     *
     * @param workItem 业务流程信息
     * @return 业务流程集合
     */
    public List<WorkItem> selectWorkItemList(WorkItem workItem);

    /**
     * 新增业务流程
     *
     * @param workItem 业务流程信息
     * @return 结果
     */
    public int insertWorkItem(WorkItem workItem);

    /**
     * 修改业务流程
     *
     * @param workItem 业务流程信息
     * @return 结果
     */
    public int updateWorkItem(WorkItem workItem);

    /**
     * 删除业务流程信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkItemByIds(String ids);

}
