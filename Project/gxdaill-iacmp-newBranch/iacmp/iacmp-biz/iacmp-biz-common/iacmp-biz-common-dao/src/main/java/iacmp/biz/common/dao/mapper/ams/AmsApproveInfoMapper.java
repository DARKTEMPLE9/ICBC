package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsApproveInfo;

import java.util.List;

/**
 * 审批意见 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsApproveInfoMapper {
    /**
     * 查询审批意见信息
     *
     * @param id 审批意见ID
     * @return 审批意见信息
     */
    AmsApproveInfo selectAmsApproveInfoById(String id);

    /**
     * 查询审批记录
     *
     * @param exaappid
     * @return
     */
    List<AmsApproveInfo> selectAmsApproveInfoByexaAppId(String exaappid);

    /**
     * 查询审批意见列表
     *
     * @param amsApproveInfo 审批意见信息
     * @return 审批意见集合
     */
    List<AmsApproveInfo> selectAmsApproveInfoList(AmsApproveInfo amsApproveInfo);

    /**
     * 新增审批意见
     *
     * @param amsApproveInfo 审批意见信息
     * @return 结果
     */
    int insertAmsApproveInfo(AmsApproveInfo amsApproveInfo);

    /**
     * 修改审批意见
     *
     * @param amsApproveInfo 审批意见信息
     * @return 结果
     */
    int updateAmsApproveInfo(AmsApproveInfo amsApproveInfo);

    /**
     * 删除审批意见
     *
     * @param id 审批意见ID
     * @return 结果
     */
    int deleteAmsApproveInfoById(String id);

    /**
     * 批量删除审批意见
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsApproveInfoByIds(String[] ids);

}