package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.WfWorkItem;

import java.util.List;

/**
 * 流程任务 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface WfWorkItemMapper {
    /**
     * 查询流程任务信息
     *
     * @param id 流程任务ID
     * @return 流程任务信息
     */
    WfWorkItem selectWfWorkItemById(String id);

    /**
     * 查询流程任务列表
     *
     * @param wfWorkItem 流程任务信息
     * @return 流程任务集合
     */
    List<WfWorkItem> selectWfWorkItemList(WfWorkItem wfWorkItem);

    /**
     * 新增流程任务
     *
     * @param wfWorkItem 流程任务信息
     * @return 结果
     */
    int insertWfWorkItem(WfWorkItem wfWorkItem);

    /**
     * 修改流程任务
     *
     * @param wfWorkItem 流程任务信息
     * @return 结果
     */
    int updateWfWorkItem(WfWorkItem wfWorkItem);

    /**
     * 删除流程任务
     *
     * @param id 流程任务ID
     * @return 结果
     */
    int deleteWfWorkItemById(String id);

    /**
     * 批量删除流程任务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWfWorkItemByIds(String[] ids);

}