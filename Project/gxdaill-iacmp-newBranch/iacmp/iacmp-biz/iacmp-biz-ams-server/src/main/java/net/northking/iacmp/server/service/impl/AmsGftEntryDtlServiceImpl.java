package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.ams.GftEntryDtlDAO;
import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;
import net.northking.iacmp.server.service.IAmsGftEntryDtlService;
import net.northking.iacmp.common.bean.dto.ams.GftEntryDtlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

import java.util.List;

/**
 * 总账分录明细 服务层实现
 *
 * @author chenwei
 * @date 2019-09-05
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsGftEntryDtlServiceImpl implements IAmsGftEntryDtlService {
    @Autowired
    GftEntryDtlDAO gftEntryDtlDAO;

    @Override
    public GftEntryDtl selectGftEntryDtlById(String pkEntryDtl) {
        return gftEntryDtlDAO.selectGftEntryDtlById(pkEntryDtl);
    }

    @Override
    public List<GftEntryDtl> selectGftEntryDtlList(GftEntryDtlDto gftEntryDtl) {
        return gftEntryDtlDAO.selectGftEntryDtlList(gftEntryDtl);
    }

    @Override
    public Integer selectGftEntryDtlCount(GftEntryDtlDto gftEntryDtl) {
        return gftEntryDtlDAO.selectGftEntryDtlCount(gftEntryDtl);
    }

    /**
     * 查询时间戳列表
     *
     * @param gftEntryDtl 时间戳信息
     * @return 时间戳集合
     */
	/*@Override
	public List<GftEntryDtl> selectGftEntryDtlList(GftEntryDtl gftEntryDtl)
	{
	    return gftEntryDtlMapper.selectGftEntryDtlList(gftEntryDtl);
	}*/

    /**
     * 新增时间戳
     *
     * @param gftEntryDtl 时间戳信息
     * @return 结果
     */
	/*@Override
	public int insertGftEntryDtl(GftEntryDtl gftEntryDtl)
	{
	    return gftEntryDtlMapper.insertGftEntryDtl(gftEntryDtl);
	}*/

    /**
     * 修改时间戳
     *
     * @param gftEntryDtl 时间戳信息
     * @return 结果
     */
	/*@Override
	public int updateGftEntryDtl(GftEntryDtl gftEntryDtl)
	{
	    return gftEntryDtlMapper.updateGftEntryDtl(gftEntryDtl);
	}*/

    /**
     * 删除时间戳对象
     *
     * @param
     * @return 结果
     */
	/*@Override
	public int deleteGftEntryDtlByIds(String ids)
	{
		return gftEntryDtlMapper.deleteGftEntryDtlByIds(Convert.toStrArray(ids));
	}*/
}
