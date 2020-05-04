package net.northking.iacmp.cms.service;

import net.northking.iacmp.common.bean.domain.cms.CmsReqMapping;

import java.util.List;

/**
 * 接入系统参数映射 服务层
 *
 * @author wei.chen
 * @date 2019-09-16
 */
public interface ICmsReqMappingService {
    /**
     * 查询接入系统参数映射信息
     *
     * @param iD 接入系统参数映射ID
     * @return 接入系统参数映射信息
     */
    public CmsReqMapping selectCmsReqMappingById(Long iD);

    /**
     * 查询接入系统参数映射列表
     *
     * @param cmsReqMapping 接入系统参数映射信息
     * @return 接入系统参数映射集合
     */
    public List<CmsReqMapping> selectCmsReqMappingList(CmsReqMapping cmsReqMapping);

    /**
     * 新增接入系统参数映射
     *
     * @param cmsReqMapping 接入系统参数映射信息
     * @return 结果
     */
    public int insertCmsReqMapping(CmsReqMapping cmsReqMapping);

    /**
     * 修改接入系统参数映射
     *
     * @param cmsReqMapping 接入系统参数映射信息
     * @return 结果
     */
    public int updateCmsReqMapping(CmsReqMapping cmsReqMapping);

    /**
     * 删除接入系统参数映射信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmsReqMappingByIds(String ids);

}
