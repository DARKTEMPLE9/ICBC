package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.WebService;

import java.util.List;

/**
 * 报文 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface WebServiceMapper {
    /**
     * 查询报文信息
     *
     * @param id 报文ID
     * @return 报文信息
     */
    WebService selectWebServiceById(String id);

    /**
     * 查询报文列表
     *
     * @param webService 报文信息
     * @return 报文集合
     */
    List<WebService> selectWebServiceList(WebService webService);

    /**
     * 新增报文
     *
     * @param webService 报文信息
     * @return 结果
     */
    int insertWebService(WebService webService);

    /**
     * 修改报文
     *
     * @param webService 报文信息
     * @return 结果
     */
    int updateWebService(WebService webService);

    /**
     * 删除报文
     *
     * @param id 报文ID
     * @return 结果
     */
    int deleteWebServiceById(String id);

    /**
     * 批量删除报文
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWebServiceByIds(String[] ids);

}