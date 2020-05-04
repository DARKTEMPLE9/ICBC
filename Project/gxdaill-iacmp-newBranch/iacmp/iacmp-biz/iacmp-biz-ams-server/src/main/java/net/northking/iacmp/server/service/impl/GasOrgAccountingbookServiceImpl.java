package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.ams.GasOrgAccountingbookDAO;
import net.northking.iacmp.common.bean.vo.ams.GasOrgAccountingbookVO;
import net.northking.iacmp.server.service.IGasOrgAccountingbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

import java.util.List;

/**
 * 账簿 服务层实现
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class GasOrgAccountingbookServiceImpl implements IGasOrgAccountingbookService {

    @Autowired
    private GasOrgAccountingbookDAO gasOrgAccountingbookDAO;

    @Override
    public List<GasOrgAccountingbookVO> selectGasOrgAccountingbookList(GasOrgAccountingbookVO orgAccountingbook) {
        return gasOrgAccountingbookDAO.selectGasOrgAccountingbookList(orgAccountingbook);
    }

}
