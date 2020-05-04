package iacmp.biz.common.dao.ams;

import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;
import net.northking.iacmp.common.bean.dto.ams.GftEntryDtlDto;

import java.util.List;

/**
 * 总账分录明细 DAO层
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/12 17:36
 */
public interface GftEntryDtlDAO {

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
}
