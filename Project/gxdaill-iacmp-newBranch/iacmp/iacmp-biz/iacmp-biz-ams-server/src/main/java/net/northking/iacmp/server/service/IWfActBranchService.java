package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.WfActBranch;

import java.util.List;

/**
 * 流程节点项参数 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IWfActBranchService {
    /**
     * 查询流程节点项参数信息
     *
     * @param id 流程节点项参数ID
     * @return 流程节点项参数信息
     */
    public WfActBranch selectWfActBranchById(String id);

    /**
     * 查询流程节点项参数列表
     *
     * @param wfActBranch 流程节点项参数信息
     * @return 流程节点项参数集合
     */
    public List<WfActBranch> selectWfActBranchList(WfActBranch wfActBranch);

    /**
     * 新增流程节点项参数
     *
     * @param wfActBranch 流程节点项参数信息
     * @return 结果
     */
    public int insertWfActBranch(WfActBranch wfActBranch);

    /**
     * 修改流程节点项参数
     *
     * @param wfActBranch 流程节点项参数信息
     * @return 结果
     */
    public int updateWfActBranch(WfActBranch wfActBranch);

    /**
     * 删除流程节点项参数信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWfActBranchByIds(String ids);

}
