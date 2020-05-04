package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WfProcAct2SmRoleMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WfProcAct2SmRole;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWfProcAct2SmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程节点 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WfProcAct2SmRoleServiceImpl implements IWfProcAct2SmRoleService {
    @Autowired
    private WfProcAct2SmRoleMapper wfProcAct2SmRoleMapper;

    /**
     * 查询流程节点信息
     *
     * @param id 流程节点ID
     * @return 流程节点信息
     */
    @Override
    public WfProcAct2SmRole selectWfProcAct2SmRoleById(String id) {
        return wfProcAct2SmRoleMapper.selectWfProcAct2SmRoleById(id);
    }

    /**
     * 查询流程节点列表
     *
     * @param wfProcAct2SmRole 流程节点信息
     * @return 流程节点集合
     */
    @Override
    public List<WfProcAct2SmRole> selectWfProcAct2SmRoleList(WfProcAct2SmRole wfProcAct2SmRole) {
        return wfProcAct2SmRoleMapper.selectWfProcAct2SmRoleList(wfProcAct2SmRole);
    }

    /**
     * 新增流程节点
     *
     * @param wfProcAct2SmRole 流程节点信息
     * @return 结果
     */
    @Override
    public int insertWfProcAct2SmRole(WfProcAct2SmRole wfProcAct2SmRole) {
        return wfProcAct2SmRoleMapper.insertWfProcAct2SmRole(wfProcAct2SmRole);
    }

    /**
     * 修改流程节点
     *
     * @param wfProcAct2SmRole 流程节点信息
     * @return 结果
     */
    @Override
    public int updateWfProcAct2SmRole(WfProcAct2SmRole wfProcAct2SmRole) {
        return wfProcAct2SmRoleMapper.updateWfProcAct2SmRole(wfProcAct2SmRole);
    }

    /**
     * 删除流程节点对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWfProcAct2SmRoleByIds(String ids) {
        return wfProcAct2SmRoleMapper.deleteWfProcAct2SmRoleByIds(Convert.toStrArray(ids));
    }

}
