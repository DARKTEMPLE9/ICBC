package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.WfActBranch;

import java.util.List;

/**
 * 流程节点项参数 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface WfActBranchMapper {
    /**
     * 查询流程节点项参数信息
     *
     * @param id 流程节点项参数ID
     * @return 流程节点项参数信息
     */
    WfActBranch selectWfActBranchById(String id);

    /**
     * 查询流程节点项参数列表
     *
     * @param wfActBranch 流程节点项参数信息
     * @return 流程节点项参数集合
     */
    List<WfActBranch> selectWfActBranchList(WfActBranch wfActBranch);

    /**
     * 新增流程节点项参数
     *
     * @param wfActBranch 流程节点项参数信息
     * @return 结果
     */
    int insertWfActBranch(WfActBranch wfActBranch);

    /**
     * 修改流程节点项参数
     *
     * @param wfActBranch 流程节点项参数信息
     * @return 结果
     */
    int updateWfActBranch(WfActBranch wfActBranch);

    /**
     * 删除流程节点项参数
     *
     * @param id 流程节点项参数ID
     * @return 结果
     */
    int deleteWfActBranchById(String id);

    /**
     * 批量删除流程节点项参数
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWfActBranchByIds(String[] ids);

}