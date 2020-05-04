package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.WebService;

import java.util.List;

/**
 * 服务 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IWebServiceService {
    /**
     * 查询服务信息
     *
     * @param id 服务ID
     * @return 服务信息
     */
    public WebService selectWebServiceById(String id);

    /**
     * 查询服务列表
     *
     * @param webService 服务信息
     * @return 服务集合
     */
    public List<WebService> selectWebServiceList(WebService webService);

    /**
     * 新增服务
     *
     * @param webService 服务信息
     * @return 结果
     */
    public int insertWebService(WebService webService);

    /**
     * 修改服务
     *
     * @param webService 服务信息
     * @return 结果
     */
    public int updateWebService(WebService webService);

    /**
     * 删除服务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWebServiceByIds(String ids);

}
