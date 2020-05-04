package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.WebService;

import java.util.List;

/**
 * 服务 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface WebServiceMapper {
    /**
     * 查询服务信息
     *
     * @param id 服务ID
     * @return 服务信息
     */
    WebService selectWebServiceById(String id);

    /**
     * 查询服务列表
     *
     * @param webService 服务信息
     * @return 服务集合
     */
    List<WebService> selectWebServiceList(WebService webService);

    /**
     * 新增服务
     *
     * @param webService 服务信息
     * @return 结果
     */
    int insertWebService(WebService webService);

    /**
     * 修改服务
     *
     * @param webService 服务信息
     * @return 结果
     */
    int updateWebService(WebService webService);

    /**
     * 删除服务
     *
     * @param id 服务ID
     * @return 结果
     */
    int deleteWebServiceById(String id);

    /**
     * 批量删除服务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWebServiceByIds(String[] ids);

}