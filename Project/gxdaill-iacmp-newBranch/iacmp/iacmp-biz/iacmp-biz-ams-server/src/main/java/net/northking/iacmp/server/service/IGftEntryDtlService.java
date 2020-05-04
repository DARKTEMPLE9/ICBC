package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;

import java.util.List;

/**
 * 时间戳 服务层
 *
 * @author wxy
 * @date 2019-08-20
 */
public interface IGftEntryDtlService {
    /**
     * 查询时间戳信息
     *
     * @param pkEntryDtl 时间戳ID
     * @return 时间戳信息
     */
    public GftEntryDtl selectGftEntryDtlById(String pkEntryDtl);

    /**
     * 查询时间戳列表
     *
     * @param gftEntryDtl 时间戳信息
     * @return 时间戳集合
     */
    public List<GftEntryDtl> selectGftEntryDtlList(GftEntryDtl gftEntryDtl);

    /**
     * 新增时间戳
     *
     * @param gftEntryDtl 时间戳信息
     * @return 结果
     */
    public int insertGftEntryDtl(GftEntryDtl gftEntryDtl);

    /**
     * 修改时间戳
     *
     * @param gftEntryDtl 时间戳信息
     * @return 结果
     */
    public int updateGftEntryDtl(GftEntryDtl gftEntryDtl);

    /**
     * 删除时间戳信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGftEntryDtlByIds(String ids);

}
