package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.WfWorkItem;

import java.util.List;

/**
 * 流程任务 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IWfWorkItemService {
    /**
     * 查询流程任务信息
     *
     * @param id 流程任务ID
     * @return 流程任务信息
     */
    public WfWorkItem selectWfWorkItemById(String id);

    /**
     * 查询流程任务列表
     *
     * @param wfWorkItem 流程任务信息
     * @return 流程任务集合
     */
    public List<WfWorkItem> selectWfWorkItemList(WfWorkItem wfWorkItem);

    /**
     * 新增流程任务
     *
     * @param wfWorkItem 流程任务信息
     * @return 结果
     */
    public int insertWfWorkItem(WfWorkItem wfWorkItem);

    /**
     * 修改流程任务
     *
     * @param wfWorkItem 流程任务信息
     * @return 结果
     */
    public int updateWfWorkItem(WfWorkItem wfWorkItem);

    /**
     * 删除流程任务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWfWorkItemByIds(String ids);

}
