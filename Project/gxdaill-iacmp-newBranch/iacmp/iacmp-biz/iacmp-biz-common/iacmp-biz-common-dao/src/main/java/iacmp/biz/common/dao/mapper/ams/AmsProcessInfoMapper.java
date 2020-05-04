package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsProcessInfo;
import net.northking.iacmp.common.bean.vo.ams.AmsProcessInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 审批 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsProcessInfoMapper {
    /**
     * 查询审批信息
     *
     * @param id 审批ID
     * @return 审批信息
     */
    AmsProcessInfo selectAmsProcessInfoById(String id);

    /**
     * 查询审批信息VO对象
     *
     * @param id
     * @return
     */
    AmsProcessInfoVO selectAmsProcessVOInfoById(String id);

    /**
     * 查询审批列表
     *
     * @param amsProcessInfo 审批信息
     * @return 审批集合
     */
    List<AmsProcessInfoVO> selectAmsProcessInfoList(AmsProcessInfoVO amsProcessInfo);

    /**
     * 导出
     *
     * @param amsProcessInfo
     * @return
     */
    List<AmsProcessInfo> selectAmsProcessInfoList(AmsProcessInfo amsProcessInfo);

    /**
     * 查询已审批列表
     *
     * @param amsProcessInfo
     * @return
     */
    List<AmsProcessInfo> selectAlreadyProcessInfoList(AmsProcessInfo amsProcessInfo);

    /**
     * 新增审批
     *
     * @param amsProcessInfo 审批信息
     * @return 结果
     */
    int insertAmsProcessInfo(AmsProcessInfo amsProcessInfo);

    /**
     * 修改审批
     *
     * @param amsProcessInfo 审批信息
     * @return 结果
     */
    int updateAmsProcessInfo(AmsProcessInfo amsProcessInfo);


    /**
     * 删除审批
     *
     * @param id 审批ID
     * @return 结果
     */
    int deleteAmsProcessInfoById(String id);

    /**
     * 批量删除审批
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsProcessInfoByIds(String[] ids);

    /**
     * 根据审批编号查询全部审批信息
     */
    List<AmsProcessInfo> selectAmsProcessInfoByExaAppId(@Param("exaAppId") String exaAppId);

}