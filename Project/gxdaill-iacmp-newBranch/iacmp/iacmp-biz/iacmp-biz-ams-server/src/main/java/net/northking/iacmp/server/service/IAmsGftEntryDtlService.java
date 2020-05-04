package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;
import net.northking.iacmp.common.bean.dto.ams.GftEntryDtlDto;

import java.util.List;

/**
 * 总账分录明细 服务层
 *
 * @author chenwei
 * @date 2019-09-05
 */
public interface IAmsGftEntryDtlService {
    /**
     * 查询总账分录明细信息
     *
     * @param pkEntryDtl 总账分录明细ID
     * @return 总账分录明细信息
     */
    GftEntryDtl selectGftEntryDtlById(String pkEntryDtl);

    /**
     * 查询总账分录明细列表
     *
     * @param gftEntryDtl 总账分录明细查询信息
     * @return 总账分录明细集合
     */
    List<GftEntryDtl> selectGftEntryDtlList(GftEntryDtlDto gftEntryDtl);

    /**
     * 查询总账分录明细总数
     *
     * @param gftEntryDtl 总账分录明细查询信息
     * @return 结果
     */
    Integer selectGftEntryDtlCount(GftEntryDtlDto gftEntryDtl);

    /**
     * 修改时间戳
     *
     * @param gftEntryDtl 时间戳信息
     * @return 结果
     */
    //public int updateGftEntryDtl(GftEntryDtl gftEntryDtl);

    /**
     * 删除时间戳信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    //public int deleteGftEntryDtlByIds(String ids);

}
