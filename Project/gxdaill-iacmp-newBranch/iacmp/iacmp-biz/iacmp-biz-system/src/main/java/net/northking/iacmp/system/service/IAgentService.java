package net.northking.iacmp.system.service;


import net.northking.iacmp.system.domain.Agent;

import java.util.List;

/**
 * 操作代理 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAgentService {
    /**
     * 查询操作代理信息
     *
     * @param id 操作代理ID
     * @return 操作代理信息
     */
    Agent selectAmsAgentById(String id);

    /**
     * 查询操作代理列表
     *
     * @param amsAgent 操作代理信息
     * @return 操作代理集合
     */
    List<Agent> selectAmsAgentList(Agent amsAgent);

    /**
     * 新增操作代理
     *
     * @param amsAgent 操作代理信息
     * @return 结果
     */
    int insertAmsAgent(Agent amsAgent);

    /**
     * 修改操作代理
     *
     * @param amsAgent 操作代理信息
     * @return 结果
     */
    int updateAmsAgent(Agent amsAgent);

    /**
     * 删除操作代理信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsAgentByIds(String ids);

}
