package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.WfProcAct2SmRole;

import java.util.List;

/**
 * 流程节点 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IWfProcAct2SmRoleService {
    /**
     * 查询流程节点信息
     *
     * @param id 流程节点ID
     * @return 流程节点信息
     */
    public WfProcAct2SmRole selectWfProcAct2SmRoleById(String id);

    /**
     * 查询流程节点列表
     *
     * @param wfProcAct2SmRole 流程节点信息
     * @return 流程节点集合
     */
    public List<WfProcAct2SmRole> selectWfProcAct2SmRoleList(WfProcAct2SmRole wfProcAct2SmRole);

    /**
     * 新增流程节点
     *
     * @param wfProcAct2SmRole 流程节点信息
     * @return 结果
     */
    public int insertWfProcAct2SmRole(WfProcAct2SmRole wfProcAct2SmRole);

    /**
     * 修改流程节点
     *
     * @param wfProcAct2SmRole 流程节点信息
     * @return 结果
     */
    public int updateWfProcAct2SmRole(WfProcAct2SmRole wfProcAct2SmRole);

    /**
     * 删除流程节点信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWfProcAct2SmRoleByIds(String ids);

}
