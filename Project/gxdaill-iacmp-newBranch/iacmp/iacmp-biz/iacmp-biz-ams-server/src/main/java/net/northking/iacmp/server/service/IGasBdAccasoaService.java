package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.vo.ams.GasBdAccasoaVO;
import net.northking.iacmp.common.bean.vo.ams.GasOrgOrgsVO;

import java.util.List;

/**
 * 会计科目 服务层
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public interface IGasBdAccasoaService {

    /**
     * 查询会计科目列表
     *
     * @param bdAccasoa 会计科目信息
     * @return 会计科目集合
     */
    public List<GasBdAccasoaVO> selectGasBdAccasoaList(GasBdAccasoaVO bdAccasoa);

}
