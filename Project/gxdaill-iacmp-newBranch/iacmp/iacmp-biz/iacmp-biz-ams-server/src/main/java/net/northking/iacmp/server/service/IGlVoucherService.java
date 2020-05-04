package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.GlVoucher;
import net.northking.iacmp.common.bean.vo.ams.GlDetailVO;
import net.northking.iacmp.common.bean.vo.ams.GlVoucherVO;

import java.util.List;

/**
 * 凭证 服务层
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public interface IGlVoucherService {
    /**
     * 查询凭证信息
     *
     * @param pkVoucher 凭证ID
     * @return 凭证信息
     */
    public List<GlVoucherVO> selectGlVoucherById(String pkVoucher);

    /**
     * 查询凭证列表
     *
     * @param glVoucher 凭证信息
     * @return 凭证集合
     */
    public List<GlVoucherVO> selectGlVoucherList(GlVoucherVO glVoucher);

    /**
     * 查询凭证总数
     *
     * @param glVoucher 凭证信息
     * @return 凭证集合
     */
    public Integer selectGlVoucherCount(GlVoucherVO glVoucher);

    /**
     * 查询凭证明细列表
     *
     * @param pkVoucher 凭证ID
     * @return 凭证明细集合
     */
    List<GlDetailVO> selectGlDetailList(String pkVoucher);

    /**
     * 新增凭证
     *
     * @param glVoucher 凭证信息
     * @return 结果
     */
    //public int insertGlVoucher(GlVoucher glVoucher);

    /**
     * 修改凭证
     *
     * @param glVoucher 凭证信息
     * @return 结果
     */
    //public int updateGlVoucher(GlVoucher glVoucher);

    /**
     * 删除凭证信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    //public int deleteGlVoucherByIds(String ids);

}
