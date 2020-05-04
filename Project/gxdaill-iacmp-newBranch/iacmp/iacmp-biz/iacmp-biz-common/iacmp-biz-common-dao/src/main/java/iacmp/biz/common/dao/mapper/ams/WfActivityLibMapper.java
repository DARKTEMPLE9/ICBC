package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.WfActivityLib;

import java.util.List;

/**
 * 流程节点关系 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface WfActivityLibMapper {
    /**
     * 查询流程节点关系信息
     *
     * @param id 流程节点关系ID
     * @return 流程节点关系信息
     */
    WfActivityLib selectWfActivityLibById(String id);

    /**
     * 查询流程节点关系列表
     *
     * @param wfActivityLib 流程节点关系信息
     * @return 流程节点关系集合
     */
    List<WfActivityLib> selectWfActivityLibList(WfActivityLib wfActivityLib);

    /**
     * 新增流程节点关系
     *
     * @param wfActivityLib 流程节点关系信息
     * @return 结果
     */
    int insertWfActivityLib(WfActivityLib wfActivityLib);

    /**
     * 修改流程节点关系
     *
     * @param wfActivityLib 流程节点关系信息
     * @return 结果
     */
    int updateWfActivityLib(WfActivityLib wfActivityLib);

    /**
     * 删除流程节点关系
     *
     * @param id 流程节点关系ID
     * @return 结果
     */
    int deleteWfActivityLibById(String id);

    /**
     * 批量删除流程节点关系
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWfActivityLibByIds(String[] ids);

}