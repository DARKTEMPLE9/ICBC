package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WfProcessInstMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WfProcessInst;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWfProcessInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程节点项角色 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WfProcessInstServiceImpl implements IWfProcessInstService {
    @Autowired
    private WfProcessInstMapper wfProcessInstMapper;

    /**
     * 查询流程节点项角色信息
     *
     * @param id 流程节点项角色ID
     * @return 流程节点项角色信息
     */
    @Override
    public WfProcessInst selectWfProcessInstById(String id) {
        return wfProcessInstMapper.selectWfProcessInstById(id);
    }

    /**
     * 查询流程节点项角色列表
     *
     * @param wfProcessInst 流程节点项角色信息
     * @return 流程节点项角色集合
     */
    @Override
    public List<WfProcessInst> selectWfProcessInstList(WfProcessInst wfProcessInst) {
        return wfProcessInstMapper.selectWfProcessInstList(wfProcessInst);
    }

    /**
     * 新增流程节点项角色
     *
     * @param wfProcessInst 流程节点项角色信息
     * @return 结果
     */
    @Override
    public int insertWfProcessInst(WfProcessInst wfProcessInst) {
        return wfProcessInstMapper.insertWfProcessInst(wfProcessInst);
    }

    /**
     * 修改流程节点项角色
     *
     * @param wfProcessInst 流程节点项角色信息
     * @return 结果
     */
    @Override
    public int updateWfProcessInst(WfProcessInst wfProcessInst) {
        return wfProcessInstMapper.updateWfProcessInst(wfProcessInst);
    }

    /**
     * 删除流程节点项角色对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWfProcessInstByIds(String ids) {
        return wfProcessInstMapper.deleteWfProcessInstByIds(Convert.toStrArray(ids));
    }

}
