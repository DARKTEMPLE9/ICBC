package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;

import java.util.List;

/**
 * 时间戳 数据层
 *
 * @author wxy
 * @date 2019-08-20
 */
public interface GftEntryDtlMapper {
    /**
     * 查询时间戳信息
     *
     * @param pkEntryDtl 时间戳ID
     * @return 时间戳信息
     */
    GftEntryDtl selectGftEntryDtlById(String pkEntryDtl);

    /**
     * 查询时间戳列表
     *
     * @param gftEntryDtl 时间戳信息
     * @return 时间戳集合
     */
    List<GftEntryDtl> selectGftEntryDtlList(GftEntryDtl gftEntryDtl);

    /**
     * 新增时间戳
     *
     * @param gftEntryDtl 时间戳信息
     * @return 结果
     */
    int insertGftEntryDtl(GftEntryDtl gftEntryDtl);

    /**
     * 修改时间戳
     *
     * @param gftEntryDtl 时间戳信息
     * @return 结果
     */
    int updateGftEntryDtl(GftEntryDtl gftEntryDtl);

    /**
     * 删除时间戳
     *
     * @param pkEntryDtl 时间戳ID
     * @return 结果
     */
    int deleteGftEntryDtlById(String pkEntryDtl);

    /**
     * 批量删除时间戳
     *
     * @param pkEntryDtls 需要删除的数据ID
     * @return 结果
     */
    int deleteGftEntryDtlByIds(String[] pkEntryDtls);

}