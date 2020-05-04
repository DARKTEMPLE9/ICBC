package net.northking.iacmp.system.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.system.domain.Agent;
import net.northking.iacmp.system.mapper.AgentMapper;
import net.northking.iacmp.system.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作代理 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AgentServiceImpl implements IAgentService {
    @Autowired
    private AgentMapper agentMapper;

    /**
     * 查询操作代理信息
     *
     * @param id 操作代理ID
     * @return 操作代理信息
     */
    @Override
    public Agent selectAmsAgentById(String id) {
        return agentMapper.selectAmsAgentById(id);
    }

    /**
     * 查询操作代理列表
     *
     * @param amsAgent 操作代理信息
     * @return 操作代理集合
     */
    @Override
    public List<Agent> selectAmsAgentList(Agent amsAgent) {
        return agentMapper.selectAmsAgentList(amsAgent);
    }

    /**
     * 新增操作代理
     *
     * @param amsAgent 操作代理信息
     * @return 结果
     */
    @Override
    public int insertAmsAgent(Agent amsAgent) {
        return agentMapper.insertAmsAgent(amsAgent);
    }

    /**
     * 修改操作代理
     *
     * @param amsAgent 操作代理信息
     * @return 结果
     */
    @Override
    public int updateAmsAgent(Agent amsAgent) {
        return agentMapper.updateAmsAgent(amsAgent);
    }

    /**
     * 删除操作代理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsAgentByIds(String ids) {
        return agentMapper.deleteAmsAgentByIds(Convert.toStrArray(ids));
    }

}
