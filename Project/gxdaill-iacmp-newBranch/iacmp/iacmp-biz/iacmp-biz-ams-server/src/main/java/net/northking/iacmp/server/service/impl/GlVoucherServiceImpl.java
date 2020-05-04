package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.ams.GlVoucherDAO;
import net.northking.iacmp.common.bean.vo.ams.GlDetailVO;
import net.northking.iacmp.common.bean.vo.ams.GlVoucherVO;
import net.northking.iacmp.server.service.IGlVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

import java.util.List;

/**
 * 凭证 服务层实现
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class GlVoucherServiceImpl implements IGlVoucherService {

    @Autowired
    private GlVoucherDAO glVoucherDAO;

    @Override
    public List<GlVoucherVO> selectGlVoucherById(String pkVoucher) {
        return glVoucherDAO.selectGlVoucherById(pkVoucher);
    }

    @Override
    public List<GlVoucherVO> selectGlVoucherList(GlVoucherVO glVoucher) {
        return glVoucherDAO.selectGlVoucherList(glVoucher);
    }

    @Override
    public Integer selectGlVoucherCount(GlVoucherVO glVoucher) {
        return glVoucherDAO.selectGlVoucherCount(glVoucher);
    }

    /**
     * 查询凭证明细列表
     *
     * @param pkVoucher 凭证ID
     * @return 凭证明细集合
     */
    @Override
    public List<GlDetailVO> selectGlDetailList(String pkVoucher) {
        return glVoucherDAO.selectGlDetailList(pkVoucher);
    }
/**
 * 新增凭证
 *
 * @param glVoucher 凭证信息
 * @return 结果
 */
	/*@Override
	public int insertGlVoucher(GlVoucher glVoucher)
	{
	    return glVoucherMapper.insertGlVoucher(glVoucher);
	}*/

    /**
     * 修改凭证
     *
     * @param glVoucher 凭证信息
     * @return 结果
     */
	/*@Override
	public int updateGlVoucher(GlVoucher glVoucher)
	{
	    return glVoucherMapper.updateGlVoucher(glVoucher);
	}*/

    /**
     * 删除凭证对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	/*@Override
	public int deleteGlVoucherByIds(String ids)
	{
		return glVoucherMapper.deleteGlVoucherByIds(Convert.toStrArray(ids));
	}*/

}
