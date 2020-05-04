package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.WebServiceMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.WebService;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IWebServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WebServiceServiceImpl implements IWebServiceService {
    @Autowired
    private WebServiceMapper webServiceMapper;

    /**
     * 查询服务信息
     *
     * @param id 服务ID
     * @return 服务信息
     */
    @Override
    public WebService selectWebServiceById(String id) {
        return webServiceMapper.selectWebServiceById(id);
    }

    /**
     * 查询服务列表
     *
     * @param webService 服务信息
     * @return 服务集合
     */
    @Override
    public List<WebService> selectWebServiceList(WebService webService) {
        return webServiceMapper.selectWebServiceList(webService);
    }

    /**
     * 新增服务
     *
     * @param webService 服务信息
     * @return 结果
     */
    @Override
    public int insertWebService(WebService webService) {
        return webServiceMapper.insertWebService(webService);
    }

    /**
     * 修改服务
     *
     * @param webService 服务信息
     * @return 结果
     */
    @Override
    public int updateWebService(WebService webService) {
        return webServiceMapper.updateWebService(webService);
    }

    /**
     * 删除服务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWebServiceByIds(String ids) {
        return webServiceMapper.deleteWebServiceByIds(Convert.toStrArray(ids));
    }

}
