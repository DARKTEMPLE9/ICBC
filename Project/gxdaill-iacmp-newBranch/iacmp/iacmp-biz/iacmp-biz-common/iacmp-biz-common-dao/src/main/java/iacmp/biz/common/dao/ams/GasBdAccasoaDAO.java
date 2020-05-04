package iacmp.biz.common.dao.ams;

import net.northking.iacmp.common.bean.vo.ams.GasBdAccasoaVO;
import net.northking.iacmp.common.bean.vo.ams.GasOrgOrgsVO;

import java.util.List;

/**
 * 会计科目表 DAO层
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/23 17:36
 */
public interface GasBdAccasoaDAO {

    /**
     * 查询会计科目列表
     *
     * @param bdAccasoa 会计科目查询信息
     * @return 会计科目集合
     */
    List<GasBdAccasoaVO> selectGasBdAccasoaList(GasBdAccasoaVO bdAccasoa);
}
