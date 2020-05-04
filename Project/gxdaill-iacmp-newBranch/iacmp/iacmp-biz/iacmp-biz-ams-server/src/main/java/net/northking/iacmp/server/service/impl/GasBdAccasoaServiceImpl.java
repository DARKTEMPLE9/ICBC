package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.ams.GasBdAccasoaDAO;
import net.northking.iacmp.common.bean.vo.ams.GasBdAccasoaVO;
import net.northking.iacmp.server.service.IGasBdAccasoaService;
import net.northking.iacmp.server.service.IGasBdAccasoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

import java.util.List;

/**
 * 会计科目 服务层实现
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class GasBdAccasoaServiceImpl implements IGasBdAccasoaService {

    @Autowired
    private GasBdAccasoaDAO gasBdAccasoaDAO;

    @Override
    public List<GasBdAccasoaVO> selectGasBdAccasoaList(GasBdAccasoaVO bdAccasoa) {
        return gasBdAccasoaDAO.selectGasBdAccasoaList(bdAccasoa);
    }

}
