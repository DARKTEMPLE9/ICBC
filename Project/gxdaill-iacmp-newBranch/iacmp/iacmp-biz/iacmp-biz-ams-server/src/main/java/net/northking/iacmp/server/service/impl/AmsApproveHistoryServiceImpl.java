package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.AmsApproveHistoryMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.AmsApproveHistory;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IAmsApproveHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审批意见历史 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsApproveHistoryServiceImpl implements IAmsApproveHistoryService {
    @Autowired
    private AmsApproveHistoryMapper amsApproveHistoryMapper;

    /**
     * 查询审批意见历史信息
     *
     * @param id 审批意见历史ID
     * @return 审批意见历史信息
     */
    @Override
    public AmsApproveHistory selectAmsApproveHistoryById(String id) {
        return amsApproveHistoryMapper.selectAmsApproveHistoryById(id);
    }

    /**
     * 查询审批意见历史列表
     *
     * @param amsApproveHistory 审批意见历史信息
     * @return 审批意见历史集合
     */
    @Override
    public List<AmsApproveHistory> selectAmsApproveHistoryList(AmsApproveHistory amsApproveHistory) {
        return amsApproveHistoryMapper.selectAmsApproveHistoryList(amsApproveHistory);
    }

    /**
     * 新增审批意见历史
     *
     * @param amsApproveHistory 审批意见历史信息
     * @return 结果
     */
    @Override
    public int insertAmsApproveHistory(AmsApproveHistory amsApproveHistory) {
        return amsApproveHistoryMapper.insertAmsApproveHistory(amsApproveHistory);
    }

    /**
     * 修改审批意见历史
     *
     * @param amsApproveHistory 审批意见历史信息
     * @return 结果
     */
    @Override
    public int updateAmsApproveHistory(AmsApproveHistory amsApproveHistory) {
        return amsApproveHistoryMapper.updateAmsApproveHistory(amsApproveHistory);
    }

    /**
     * 删除审批意见历史对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsApproveHistoryByIds(String ids) {
        return amsApproveHistoryMapper.deleteAmsApproveHistoryByIds(Convert.toStrArray(ids));
    }

}
