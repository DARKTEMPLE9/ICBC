package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.WorkItem;

import java.util.List;

/**
 * 业务流程 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface WorkItemMapper {
    /**
     * 查询业务流程信息
     *
     * @param id 业务流程ID
     * @return 业务流程信息
     */
    WorkItem selectWorkItemById(String id);

    /**
     * 查询业务流程列表
     *
     * @param workItem 业务流程信息
     * @return 业务流程集合
     */
    List<WorkItem> selectWorkItemList(WorkItem workItem);

    /**
     * 新增业务流程
     *
     * @param workItem 业务流程信息
     * @return 结果
     */
    int insertWorkItem(WorkItem workItem);

    /**
     * 修改业务流程
     *
     * @param workItem 业务流程信息
     * @return 结果
     */
    int updateWorkItem(WorkItem workItem);

    /**
     * 删除业务流程
     *
     * @param id 业务流程ID
     * @return 结果
     */
    int deleteWorkItemById(String id);

    /**
     * 批量删除业务流程
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWorkItemByIds(String[] ids);

}