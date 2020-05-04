package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WfAgentMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WfAgent;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWfAgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 流程代理 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WfAgentServiceImpl implements IWfAgentService {
    @Autowired
    private WfAgentMapper wfAgentMapper;
    private static Logger logger = LoggerFactory.getLogger(WfAgentServiceImpl.class);

    /**
     * 查询流程代理信息
     *
     * @param id 流程代理ID
     * @return 流程代理信息
     */
    @Override
    public WfAgent selectWfAgentById(String id) {
        return wfAgentMapper.selectWfAgentById(id);
    }

    /**
     * 查询流程代理列表
     *
     * @param wfAgent 流程代理信息
     * @return 流程代理集合
     */
    @Override
    public List<WfAgent> selectWfAgentList(WfAgent wfAgent) {
        return wfAgentMapper.selectWfAgentList(wfAgent);
    }

    /**
     * 新增流程代理
     *
     * @param wfAgent 流程代理信息
     * @return 结果
     */
    @Override
    public int insertWfAgent(WfAgent wfAgent) {
        if (wfAgent.getId() == null || wfAgent.getId().equals("")) {
            try {
                String id = UUID.randomUUID().toString().replace("-", "");
                wfAgent.setId(id);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return wfAgentMapper.insertWfAgent(wfAgent);
    }

    /**
     * 修改流程代理
     *
     * @param wfAgent 流程代理信息
     * @return 结果
     */
    @Override
    public int updateWfAgent(WfAgent wfAgent) {
        return wfAgentMapper.updateWfAgent(wfAgent);
    }

    /**
     * 删除流程代理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWfAgentByIds(String ids) {
        return wfAgentMapper.deleteWfAgentByIds(Convert.toStrArray(ids));
    }

}
