package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.WfAgent;

import java.util.List;

/**
 * 流程代理 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface WfAgentMapper {
    /**
     * 查询流程代理信息
     *
     * @param id 流程代理ID
     * @return 流程代理信息
     */
    WfAgent selectWfAgentById(String id);

    /**
     * 查询流程代理列表
     *
     * @param wfAgent 流程代理信息
     * @return 流程代理集合
     */
    List<WfAgent> selectWfAgentList(WfAgent wfAgent);

    /**
     * 新增流程代理
     *
     * @param wfAgent 流程代理信息
     * @return 结果
     */
    int insertWfAgent(WfAgent wfAgent);

    /**
     * 修改流程代理
     *
     * @param wfAgent 流程代理信息
     * @return 结果
     */
    int updateWfAgent(WfAgent wfAgent);

    /**
     * 删除流程代理
     *
     * @param id 流程代理ID
     * @return 结果
     */
    int deleteWfAgentById(String id);

    /**
     * 批量删除流程代理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWfAgentByIds(String[] ids);

}