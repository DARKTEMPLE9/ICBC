package net.northking.iacmp.server.service.impl;

import java.util.List;

import net.northking.iacmp.common.bean.vo.ams.AmsProcessInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsProcessInfoMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsProcessInfo;
import net.northking.iacmp.server.service.IAmsProcessInfoService;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

/**
 * 审批 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsProcessInfoServiceImpl implements IAmsProcessInfoService {
    @Autowired
    private AmsProcessInfoMapper amsProcessInfoMapper;

    /**
     * 查询审批信息
     *
     * @param id 审批ID
     * @return 审批信息
     */
    @Override
    public AmsProcessInfo selectAmsProcessInfoById(String id) {
        AmsProcessInfo amsProcessInfo = amsProcessInfoMapper.selectAmsProcessInfoById(id);
        return amsProcessInfo;
    }

    /**
     * 查询审批信息
     *
     * @param id 审批ID
     * @return 审批信息
     */
    @Override
    public AmsProcessInfoVO selectAmsProcessVOInfoById(String id) {
        return amsProcessInfoMapper.selectAmsProcessVOInfoById(id);
    }

    /**
     * 查询审批列表
     *
     * @param amsProcessInfo 审批信息
     * @return 审批集合
     */
    @Override
    public List<AmsProcessInfoVO> selectAmsProcessInfoList(AmsProcessInfoVO amsProcessInfo) {
        //多数据源修改--ll
        List<AmsProcessInfoVO> amsProcessInfoVOS = amsProcessInfoMapper.selectAmsProcessInfoList(amsProcessInfo);
        return amsProcessInfoVOS;
    }

    /**
     * 查询审批列表 导出
     *
     * @param amsProcessInfo 审批信息
     * @return 审批集合
     */
    @Override
    public List<AmsProcessInfo> selectAmsProcessInfoList(AmsProcessInfo amsProcessInfo) {
        return amsProcessInfoMapper.selectAmsProcessInfoList(amsProcessInfo);
    }

    /**
     * 查询已审批列表
     *
     * @param amsProcessInfo 审批信息
     * @return
     */
    @Override
    public List<AmsProcessInfo> selectAlreadyProcessInfoList(AmsProcessInfo amsProcessInfo) {
        List<AmsProcessInfo> amsProcessInfos = amsProcessInfoMapper.selectAmsProcessInfoList(amsProcessInfo);
        return amsProcessInfos;
    }

    /**
     * 新增审批
     *
     * @param amsProcessInfo 审批信息
     * @return 结果
     */
    @Override
    public int insertAmsProcessInfo(AmsProcessInfo amsProcessInfo) {
        return amsProcessInfoMapper.insertAmsProcessInfo(amsProcessInfo);
    }

    /**
     * 修改审批
     *
     * @param amsProcessInfo 审批信息
     * @return 结果
     */
    @Override
    public int updateAmsProcessInfo(AmsProcessInfo amsProcessInfo) {
        return amsProcessInfoMapper.updateAmsProcessInfo(amsProcessInfo);
    }


    /**
     * 删除审批对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsProcessInfoByIds(String ids) {
        return amsProcessInfoMapper.deleteAmsProcessInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据审批编号查询全部审批信息
     */
    @Override
    public List<AmsProcessInfo> selectAmsProcessInfoByExaAppId(@Param("exaAppId") String exaAppId) {
        List<AmsProcessInfo> amsProcessInfos = amsProcessInfoMapper.selectAmsProcessInfoByExaAppId(exaAppId);
        return amsProcessInfos;
    }

    ;
}
