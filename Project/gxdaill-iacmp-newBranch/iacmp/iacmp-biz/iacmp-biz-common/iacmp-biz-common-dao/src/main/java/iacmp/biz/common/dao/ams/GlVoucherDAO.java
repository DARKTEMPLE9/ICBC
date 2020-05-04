package iacmp.biz.common.dao.ams;

import net.northking.iacmp.common.bean.vo.ams.GlDetailVO;
import net.northking.iacmp.common.bean.vo.ams.GlVoucherVO;

import java.util.List;

/**
 * 凭证 DAO层
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/23 17:36
 */
public interface GlVoucherDAO {

    /**
     * 查询凭证信息
     *
     * @param pkVoucher 凭证ID
     * @return 凭证信息
     */
    List<GlVoucherVO> selectGlVoucherById(String pkVoucher);

    /**
     * 查询凭证列表
     *
     * @param glVoucher 凭证查询信息
     * @return 凭证集合
     */
    List<GlVoucherVO> selectGlVoucherList(GlVoucherVO glVoucher);

    /**
     * 查询凭证总数
     *
     * @param glVoucher 凭证查询信息
     * @return 结果
     */
    Integer selectGlVoucherCount(GlVoucherVO glVoucher);

    /**
     * 查询凭证明细列表
     *
     * @param pkVoucher 凭证ID
     * @return 凭证明细集合
     */
    List<GlDetailVO> selectGlDetailList(String pkVoucher);
}
