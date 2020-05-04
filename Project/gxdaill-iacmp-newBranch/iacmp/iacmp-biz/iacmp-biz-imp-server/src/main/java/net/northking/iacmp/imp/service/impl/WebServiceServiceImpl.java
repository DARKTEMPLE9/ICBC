package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.WebService;
import net.northking.iacmp.imp.mapper.WebServiceMapper;
import net.northking.iacmp.imp.service.IWebServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报文 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WebServiceServiceImpl implements IWebServiceService {
    @Autowired
    private WebServiceMapper webServiceMapper;

    /**
     * 查询报文信息
     *
     * @param id 报文ID
     * @return 报文信息
     */
    @Override
    public WebService selectWebServiceById(String id) {
        return webServiceMapper.selectWebServiceById(id);
    }

    /**
     * 查询报文列表
     *
     * @param webService 报文信息
     * @return 报文集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<WebService> selectWebServiceList(WebService webService) {
        return webServiceMapper.selectWebServiceList(webService);
    }

    /**
     * 新增报文
     *
     * @param webService 报文信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertWebService(WebService webService) {
        return webServiceMapper.insertWebService(webService);
    }

    /**
     * 修改报文
     *
     * @param webService 报文信息
     * @return 结果
     */
    @Override
    public int updateWebService(WebService webService) {
        return webServiceMapper.updateWebService(webService);
    }

    /**
     * 删除报文对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWebServiceByIds(String ids) {
        return webServiceMapper.deleteWebServiceByIds(Convert.toStrArray(ids));
    }

}
