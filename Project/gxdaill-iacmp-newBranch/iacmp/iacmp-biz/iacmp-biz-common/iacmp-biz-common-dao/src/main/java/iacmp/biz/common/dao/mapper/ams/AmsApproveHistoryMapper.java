package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsApproveHistory;

import java.util.List;

/**
 * 审批意见历史 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsApproveHistoryMapper {
    /**
     * 查询审批意见历史信息
     *
     * @param id 审批意见历史ID
     * @return 审批意见历史信息
     */
    AmsApproveHistory selectAmsApproveHistoryById(String id);

    /**
     * 查询审批意见历史列表
     *
     * @param amsApproveHistory 审批意见历史信息
     * @return 审批意见历史集合
     */
    List<AmsApproveHistory> selectAmsApproveHistoryList(AmsApproveHistory amsApproveHistory);

    /**
     * 新增审批意见历史
     *
     * @param amsApproveHistory 审批意见历史信息
     * @return 结果
     */
    int insertAmsApproveHistory(AmsApproveHistory amsApproveHistory);

    /**
     * 修改审批意见历史
     *
     * @param amsApproveHistory 审批意见历史信息
     * @return 结果
     */
    int updateAmsApproveHistory(AmsApproveHistory amsApproveHistory);

    /**
     * 删除审批意见历史
     *
     * @param id 审批意见历史ID
     * @return 结果
     */
    int deleteAmsApproveHistoryById(String id);

    /**
     * 批量删除审批意见历史
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsApproveHistoryByIds(String[] ids);

}