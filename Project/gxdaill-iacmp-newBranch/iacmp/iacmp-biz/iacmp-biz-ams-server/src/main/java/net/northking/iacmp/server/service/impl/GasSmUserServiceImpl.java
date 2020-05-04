package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.ams.GasSmUserDAO;
import net.northking.iacmp.common.bean.vo.ams.GasSmUserVO;
import net.northking.iacmp.server.service.IGasSmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

import java.util.List;

/**
 * 用户 服务层实现
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class GasSmUserServiceImpl implements IGasSmUserService {

    @Autowired
    private GasSmUserDAO gasSmUserDAO;

    @Override
    public List<GasSmUserVO> selectGasSmUserList(GasSmUserVO smUser) {
        return gasSmUserDAO.selectGasSmUserList(smUser);
    }

}
