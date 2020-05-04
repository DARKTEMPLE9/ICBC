package iacmp.biz.common.dao.ams;

import net.northking.iacmp.common.bean.vo.ams.GasOrgAccountingbookVO;

import java.util.List;

/**
 * 账簿表 DAO层
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/23 17:36
 */
public interface GasOrgAccountingbookDAO {

    /**
     * 查询账簿列表
     *
     * @param orgAccountingbook 账簿查询信息
     * @return 账簿集合
     */
    List<GasOrgAccountingbookVO> selectGasOrgAccountingbookList(GasOrgAccountingbookVO orgAccountingbook);
}
