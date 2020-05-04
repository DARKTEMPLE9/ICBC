package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.AmsAgentMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.AmsAgent;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IAmsAgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 操作代理 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsAgentServiceImpl implements IAmsAgentService {
    @Autowired
    private AmsAgentMapper amsAgentMapper;
    private static Logger logger = LoggerFactory.getLogger(AmsAgentServiceImpl.class);

    /**
     * 查询操作代理信息
     *
     * @param id 操作代理ID
     * @return 操作代理信息
     */
    @Override
    public AmsAgent selectAmsAgentById(String id) {
        return amsAgentMapper.selectAmsAgentById(id);
    }

    /**
     * 查询操作代理列表
     *
     * @param amsAgent 操作代理信息
     * @return 操作代理集合
     */
    @Override
    public List<AmsAgent> selectAmsAgentList(AmsAgent amsAgent) {
        return amsAgentMapper.selectAmsAgentList(amsAgent);
    }

    /**
     * 新增操作代理
     *
     * @param amsAgent 操作代理信息
     * @return 结果
     */
    @Override
    public int insertAmsAgent(AmsAgent amsAgent) {
        if (amsAgent.getId() == null || amsAgent.getId().equals("")) {
            try {
                String id = UUID.randomUUID().toString().replace("-", "");
                amsAgent.setId(id);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return amsAgentMapper.insertAmsAgent(amsAgent);
    }

    /**
     * 修改操作代理
     *
     * @param amsAgent 操作代理信息
     * @return 结果
     */
    @Override
    public int updateAmsAgent(AmsAgent amsAgent) {
        return amsAgentMapper.updateAmsAgent(amsAgent);
    }

    /**
     * 删除操作代理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsAgentByIds(String ids) {
        return amsAgentMapper.deleteAmsAgentByIds(Convert.toStrArray(ids));
    }

}
