package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.AmsProcessHistoryMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsProcessHistory;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.server.service.IAmsProcessHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

import java.util.List;

/**
 * 审批历史 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsProcessHistoryServiceImpl implements IAmsProcessHistoryService {
    @Autowired
    private AmsProcessHistoryMapper amsProcessHistoryMapper;

    /**
     * 查询审批历史信息
     *
     * @param id 审批历史ID
     * @return 审批历史信息
     */
    @Override
    public AmsProcessHistory selectAmsProcessHistoryById(String id) {
        return amsProcessHistoryMapper.selectAmsProcessHistoryById(id);
    }

    /**
     * 查询审批历史列表
     *
     * @param amsProcessHistory 审批历史信息
     * @return 审批历史集合
     */
    @Override
    public List<AmsProcessHistory> selectAmsProcessHistoryList(AmsProcessHistory amsProcessHistory) {
        return amsProcessHistoryMapper.selectAmsProcessHistoryList(amsProcessHistory);
    }

    /**
     * 新增审批历史
     *
     * @param amsProcessHistory 审批历史信息
     * @return 结果
     */
    @Override
    public int insertAmsProcessHistory(AmsProcessHistory amsProcessHistory) {
        return amsProcessHistoryMapper.insertAmsProcessHistory(amsProcessHistory);
    }

    /**
     * 修改审批历史
     *
     * @param amsProcessHistory 审批历史信息
     * @return 结果
     */
    @Override
    public int updateAmsProcessHistory(AmsProcessHistory amsProcessHistory) {
        return amsProcessHistoryMapper.updateAmsProcessHistory(amsProcessHistory);
    }

    /**
     * 删除审批历史对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsProcessHistoryByIds(String ids) {
        return amsProcessHistoryMapper.deleteAmsProcessHistoryByIds(Convert.toStrArray(ids));
    }

}
