package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.WfActivityLib;

import java.util.List;

/**
 * 流程节点关系 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IWfActivityLibService {
    /**
     * 查询流程节点关系信息
     *
     * @param id 流程节点关系ID
     * @return 流程节点关系信息
     */
    public WfActivityLib selectWfActivityLibById(String id);

    /**
     * 查询流程节点关系列表
     *
     * @param wfActivityLib 流程节点关系信息
     * @return 流程节点关系集合
     */
    public List<WfActivityLib> selectWfActivityLibList(WfActivityLib wfActivityLib);

    /**
     * 新增流程节点关系
     *
     * @param wfActivityLib 流程节点关系信息
     * @return 结果
     */
    public int insertWfActivityLib(WfActivityLib wfActivityLib);

    /**
     * 修改流程节点关系
     *
     * @param wfActivityLib 流程节点关系信息
     * @return 结果
     */
    public int updateWfActivityLib(WfActivityLib wfActivityLib);

    /**
     * 删除流程节点关系信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWfActivityLibByIds(String ids);

}
