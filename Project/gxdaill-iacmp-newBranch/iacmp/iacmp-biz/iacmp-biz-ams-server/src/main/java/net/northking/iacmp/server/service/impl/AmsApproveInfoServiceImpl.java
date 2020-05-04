package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.AmsApproveInfoMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.AmsApproveInfo;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IAmsApproveInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审批意见 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsApproveInfoServiceImpl implements IAmsApproveInfoService {
    @Autowired
    private AmsApproveInfoMapper amsApproveInfoMapper;

    /**
     * 查询审批意见信息
     *
     * @param id 审批意见ID
     * @return 审批意见信息
     */
    @Override
    public AmsApproveInfo selectAmsApproveInfoById(String id) {
        return amsApproveInfoMapper.selectAmsApproveInfoById(id);
    }

    /**
     * 查询审批记录
     *
     * @param exaappid
     * @return
     */
    @Override
    public List<AmsApproveInfo> selectAmsApproveInfoByexaAppId(String exaappid) {
        return amsApproveInfoMapper.selectAmsApproveInfoByexaAppId(exaappid);
    }

    /**
     * 查询审批意见列表
     *
     * @param amsApproveInfo 审批意见信息
     * @return 审批意见集合
     */
    @Override
    public List<AmsApproveInfo> selectAmsApproveInfoList(AmsApproveInfo amsApproveInfo) {
        return amsApproveInfoMapper.selectAmsApproveInfoList(amsApproveInfo);
    }

    /**
     * 新增审批意见
     *
     * @param amsApproveInfo 审批意见信息
     * @return 结果
     */
    @Override
    public int insertAmsApproveInfo(AmsApproveInfo amsApproveInfo) {
        return amsApproveInfoMapper.insertAmsApproveInfo(amsApproveInfo);
    }

    /**
     * 修改审批意见
     *
     * @param amsApproveInfo 审批意见信息
     * @return 结果
     */
    @Override
    public int updateAmsApproveInfo(AmsApproveInfo amsApproveInfo) {
        return amsApproveInfoMapper.updateAmsApproveInfo(amsApproveInfo);
    }

    /**
     * 删除审批意见对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsApproveInfoByIds(String ids) {
        return amsApproveInfoMapper.deleteAmsApproveInfoByIds(Convert.toStrArray(ids));
    }

}
