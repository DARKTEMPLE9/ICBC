package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.WfProcessInst;

import java.util.List;

/**
 * 流程节点项角色 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface WfProcessInstMapper {
    /**
     * 查询流程节点项角色信息
     *
     * @param id 流程节点项角色ID
     * @return 流程节点项角色信息
     */
    WfProcessInst selectWfProcessInstById(String id);

    /**
     * 查询流程节点项角色列表
     *
     * @param wfProcessInst 流程节点项角色信息
     * @return 流程节点项角色集合
     */
    List<WfProcessInst> selectWfProcessInstList(WfProcessInst wfProcessInst);

    /**
     * 新增流程节点项角色
     *
     * @param wfProcessInst 流程节点项角色信息
     * @return 结果
     */
    int insertWfProcessInst(WfProcessInst wfProcessInst);

    /**
     * 修改流程节点项角色
     *
     * @param wfProcessInst 流程节点项角色信息
     * @return 结果
     */
    int updateWfProcessInst(WfProcessInst wfProcessInst);

    /**
     * 删除流程节点项角色
     *
     * @param id 流程节点项角色ID
     * @return 结果
     */
    int deleteWfProcessInstById(String id);

    /**
     * 批量删除流程节点项角色
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWfProcessInstByIds(String[] ids);

}