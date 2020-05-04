package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsAgent;

import java.util.List;

/**
 * 操作代理 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsAgentService {
    /**
     * 查询操作代理信息
     *
     * @param id 操作代理ID
     * @return 操作代理信息
     */
    AmsAgent selectAmsAgentById(String id);

    /**
     * 查询操作代理列表
     *
     * @param amsAgent 操作代理信息
     * @return 操作代理集合
     */
    List<AmsAgent> selectAmsAgentList(AmsAgent amsAgent);

    /**
     * 新增操作代理
     *
     * @param amsAgent 操作代理信息
     * @return 结果
     */
    int insertAmsAgent(AmsAgent amsAgent);

    /**
     * 修改操作代理
     *
     * @param amsAgent 操作代理信息
     * @return 结果
     */
    int updateAmsAgent(AmsAgent amsAgent);

    /**
     * 删除操作代理信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsAgentByIds(String ids);

}
