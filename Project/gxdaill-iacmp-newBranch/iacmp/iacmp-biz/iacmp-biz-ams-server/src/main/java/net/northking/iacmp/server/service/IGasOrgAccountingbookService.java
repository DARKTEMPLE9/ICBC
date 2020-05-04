package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.vo.ams.GasOrgAccountingbookVO;

import java.util.List;

/**
 * 账簿 服务层
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public interface IGasOrgAccountingbookService {

    /**
     * 查询账簿列表
     *
     * @param orgAccountingbook 账簿信息
     * @return 账簿集合
     */
    public List<GasOrgAccountingbookVO> selectGasOrgAccountingbookList(GasOrgAccountingbookVO orgAccountingbook);

}
