package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.ams.GasOrgOrgsDAO;
import net.northking.iacmp.common.bean.vo.ams.GasOrgOrgsVO;
import net.northking.iacmp.server.service.IGasOrgOrgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

import java.util.List;

/**
 * 部门 服务层实现
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class GasOrgOrgsServiceImpl implements IGasOrgOrgsService {

    @Autowired
    private GasOrgOrgsDAO gasOrgOrgsDAO;

    @Override
    public List<GasOrgOrgsVO> selectGasOrgOrgsList(GasOrgOrgsVO orgOrgs) {
        return gasOrgOrgsDAO.selectGasOrgOrgsList(orgOrgs);
    }

}
