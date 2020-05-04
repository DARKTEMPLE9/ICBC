package net.northking.iacmp.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.GftEntryDtlMapper;
import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;
import net.northking.iacmp.server.service.IGftEntryDtlService;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

/**
 * 时间戳 服务层实现
 *
 * @author wxy
 * @date 2019-08-20
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class GftEntryDtlServiceImpl implements IGftEntryDtlService {
    @Autowired
    private GftEntryDtlMapper gftEntryDtlMapper;

    /**
     * 查询时间戳信息
     *
     * @param pkEntryDtl 时间戳ID
     * @return 时间戳信息
     */
    @Override
    public GftEntryDtl selectGftEntryDtlById(String pkEntryDtl) {
        return gftEntryDtlMapper.selectGftEntryDtlById(pkEntryDtl);
    }

    /**
     * 查询时间戳列表
     *
     * @param gftEntryDtl 时间戳信息
     * @return 时间戳集合
     */
    @Override
    public List<GftEntryDtl> selectGftEntryDtlList(GftEntryDtl gftEntryDtl) {
        return gftEntryDtlMapper.selectGftEntryDtlList(gftEntryDtl);
    }

    /**
     * 新增时间戳
     *
     * @param gftEntryDtl 时间戳信息
     * @return 结果
     */
    @Override
    public int insertGftEntryDtl(GftEntryDtl gftEntryDtl) {
        return gftEntryDtlMapper.insertGftEntryDtl(gftEntryDtl);
    }

    /**
     * 修改时间戳
     *
     * @param gftEntryDtl 时间戳信息
     * @return 结果
     */
    @Override
    public int updateGftEntryDtl(GftEntryDtl gftEntryDtl) {
        return gftEntryDtlMapper.updateGftEntryDtl(gftEntryDtl);
    }

    /**
     * 删除时间戳对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGftEntryDtlByIds(String ids) {
        return gftEntryDtlMapper.deleteGftEntryDtlByIds(Convert.toStrArray(ids));
    }

}
