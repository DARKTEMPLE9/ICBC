package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WfProcessActMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WfProcessAct;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWfProcessActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WfProcessActServiceImpl implements IWfProcessActService {
    @Autowired
    private WfProcessActMapper wfProcessActMapper;

    /**
     * 查询流程信息
     *
     * @param id 流程ID
     * @return 流程信息
     */
    @Override
    public WfProcessAct selectWfProcessActById(String id) {
        return wfProcessActMapper.selectWfProcessActById(id);
    }

    /**
     * 查询流程列表
     *
     * @param wfProcessAct 流程信息
     * @return 流程集合
     */
    @Override
    public List<WfProcessAct> selectWfProcessActList(WfProcessAct wfProcessAct) {
        return wfProcessActMapper.selectWfProcessActList(wfProcessAct);
    }

    /**
     * 新增流程
     *
     * @param wfProcessAct 流程信息
     * @return 结果
     */
    @Override
    public int insertWfProcessAct(WfProcessAct wfProcessAct) {
        return wfProcessActMapper.insertWfProcessAct(wfProcessAct);
    }

    /**
     * 修改流程
     *
     * @param wfProcessAct 流程信息
     * @return 结果
     */
    @Override
    public int updateWfProcessAct(WfProcessAct wfProcessAct) {
        return wfProcessActMapper.updateWfProcessAct(wfProcessAct);
    }

    /**
     * 删除流程对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWfProcessActByIds(String ids) {
        return wfProcessActMapper.deleteWfProcessActByIds(Convert.toStrArray(ids));
    }

}
