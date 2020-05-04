package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.WfProcessAct;

import java.util.List;

/**
 * 流程 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IWfProcessActService {
    /**
     * 查询流程信息
     *
     * @param id 流程ID
     * @return 流程信息
     */
    public WfProcessAct selectWfProcessActById(String id);

    /**
     * 查询流程列表
     *
     * @param wfProcessAct 流程信息
     * @return 流程集合
     */
    public List<WfProcessAct> selectWfProcessActList(WfProcessAct wfProcessAct);

    /**
     * 新增流程
     *
     * @param wfProcessAct 流程信息
     * @return 结果
     */
    public int insertWfProcessAct(WfProcessAct wfProcessAct);

    /**
     * 修改流程
     *
     * @param wfProcessAct 流程信息
     * @return 结果
     */
    public int updateWfProcessAct(WfProcessAct wfProcessAct);

    /**
     * 删除流程信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWfProcessActByIds(String ids);

}
