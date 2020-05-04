package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WfActBranchMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WfActBranch;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWfActBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程节点项参数 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WfActBranchServiceImpl implements IWfActBranchService {
    @Autowired
    private WfActBranchMapper wfActBranchMapper;

    /**
     * 查询流程节点项参数信息
     *
     * @param id 流程节点项参数ID
     * @return 流程节点项参数信息
     */
    @Override
    public WfActBranch selectWfActBranchById(String id) {
        return wfActBranchMapper.selectWfActBranchById(id);
    }

    /**
     * 查询流程节点项参数列表
     *
     * @param wfActBranch 流程节点项参数信息
     * @return 流程节点项参数集合
     */
    @Override
    public List<WfActBranch> selectWfActBranchList(WfActBranch wfActBranch) {
        return wfActBranchMapper.selectWfActBranchList(wfActBranch);
    }

    /**
     * 新增流程节点项参数
     *
     * @param wfActBranch 流程节点项参数信息
     * @return 结果
     */
    @Override
    public int insertWfActBranch(WfActBranch wfActBranch) {
        return wfActBranchMapper.insertWfActBranch(wfActBranch);
    }

    /**
     * 修改流程节点项参数
     *
     * @param wfActBranch 流程节点项参数信息
     * @return 结果
     */
    @Override
    public int updateWfActBranch(WfActBranch wfActBranch) {
        return wfActBranchMapper.updateWfActBranch(wfActBranch);
    }

    /**
     * 删除流程节点项参数对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWfActBranchByIds(String ids) {
        return wfActBranchMapper.deleteWfActBranchByIds(Convert.toStrArray(ids));
    }

}
