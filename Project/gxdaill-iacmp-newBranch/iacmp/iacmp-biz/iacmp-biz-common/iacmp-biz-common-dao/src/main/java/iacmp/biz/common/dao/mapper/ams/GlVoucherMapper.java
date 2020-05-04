package iacmp.biz.common.dao.mapper.ams;

import net.northking.iacmp.common.bean.domain.ams.GlVoucher;
import net.northking.iacmp.common.bean.vo.ams.GlVoucherVO;

import java.util.List;

/**
 * 凭证 数据层
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public interface GlVoucherMapper {
    /**
     * 查询凭证信息
     *
     * @param pkVoucher 凭证ID
     * @return 凭证信息
     */
    public GlVoucherVO selectGlVoucherById(String pkVoucher);

    /**
     * 查询凭证列表
     *
     * @param glVoucher 凭证信息
     * @return 凭证集合
     */
    public List<GlVoucherVO> selectGlVoucherList(GlVoucherVO glVoucher);

    /**
     * 新增凭证
     *
     * @param glVoucher 凭证信息
     * @return 结果
     */
    public int insertGlVoucher(GlVoucher glVoucher);

    /**
     * 修改凭证
     *
     * @param glVoucher 凭证信息
     * @return 结果
     */
    public int updateGlVoucher(GlVoucher glVoucher);

    /**
     * 删除凭证
     *
     * @param pkVoucher 凭证ID
     * @return 结果
     */
    public int deleteGlVoucherById(String pkVoucher);

    /**
     * 批量删除凭证
     *
     * @param pkVouchers 需要删除的数据ID
     * @return 结果
     */
    public int deleteGlVoucherByIds(String[] pkVouchers);

}