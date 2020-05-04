package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsProcessHistory;

import java.util.List;

/**
 * 审批历史 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsProcessHistoryService {
    /**
     * 查询审批历史信息
     *
     * @param id 审批历史ID
     * @return 审批历史信息
     */
    public AmsProcessHistory selectAmsProcessHistoryById(String id);

    /**
     * 查询审批历史列表
     *
     * @param amsProcessHistory 审批历史信息
     * @return 审批历史集合
     */
    public List<AmsProcessHistory> selectAmsProcessHistoryList(AmsProcessHistory amsProcessHistory);

    /**
     * 新增审批历史
     *
     * @param amsProcessHistory 审批历史信息
     * @return 结果
     */
    public int insertAmsProcessHistory(AmsProcessHistory amsProcessHistory);

    /**
     * 修改审批历史
     *
     * @param amsProcessHistory 审批历史信息
     * @return 结果
     */
    public int updateAmsProcessHistory(AmsProcessHistory amsProcessHistory);

    /**
     * 删除审批历史信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsProcessHistoryByIds(String ids);

}
