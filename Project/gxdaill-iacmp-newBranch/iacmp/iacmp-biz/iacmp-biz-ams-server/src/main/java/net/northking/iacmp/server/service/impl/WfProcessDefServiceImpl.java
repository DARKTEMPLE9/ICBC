package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WfProcessDefMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WfProcessDef;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWfProcessDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程实例 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WfProcessDefServiceImpl implements IWfProcessDefService {
    @Autowired
    private WfProcessDefMapper wfProcessDefMapper;

    /**
     * 查询流程实例信息
     *
     * @param id 流程实例ID
     * @return 流程实例信息
     */
    @Override
    public WfProcessDef selectWfProcessDefById(String id) {
        return wfProcessDefMapper.selectWfProcessDefById(id);
    }

    /**
     * 查询流程实例列表
     *
     * @param wfProcessDef 流程实例信息
     * @return 流程实例集合
     */
    @Override
    public List<WfProcessDef> selectWfProcessDefList(WfProcessDef wfProcessDef) {
        return wfProcessDefMapper.selectWfProcessDefList(wfProcessDef);
    }

    /**
     * 新增流程实例
     *
     * @param wfProcessDef 流程实例信息
     * @return 结果
     */
    @Override
    public int insertWfProcessDef(WfProcessDef wfProcessDef) {
        return wfProcessDefMapper.insertWfProcessDef(wfProcessDef);
    }

    /**
     * 修改流程实例
     *
     * @param wfProcessDef 流程实例信息
     * @return 结果
     */
    @Override
    public int updateWfProcessDef(WfProcessDef wfProcessDef) {
        return wfProcessDefMapper.updateWfProcessDef(wfProcessDef);
    }

    /**
     * 删除流程实例对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWfProcessDefByIds(String ids) {
        return wfProcessDefMapper.deleteWfProcessDefByIds(Convert.toStrArray(ids));
    }

}
