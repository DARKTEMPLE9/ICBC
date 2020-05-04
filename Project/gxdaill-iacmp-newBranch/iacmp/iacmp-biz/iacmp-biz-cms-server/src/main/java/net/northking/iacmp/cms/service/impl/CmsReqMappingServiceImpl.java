package net.northking.iacmp.cms.service.impl;

import java.util.List;

import iacmp.biz.common.dao.mapper.cms.CmsReqMappingMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.common.bean.domain.cms.CmsReqMapping;
import net.northking.iacmp.cms.service.ICmsReqMappingService;
import net.northking.iacmp.core.text.Convert;

/**
 * 接入系统参数映射 服务层实现
 *
 * @author wei.chen
 * @date 2019-09-16
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsReqMappingServiceImpl implements ICmsReqMappingService {
    @Autowired
    private CmsReqMappingMapper cmsReqMappingMapper;

    /**
     * 查询接入系统参数映射信息
     *
     * @param id 接入系统参数映射ID
     * @return 接入系统参数映射信息
     */
    @Override
    public CmsReqMapping selectCmsReqMappingById(Long id) {
        return cmsReqMappingMapper.selectCmsReqMappingById(id);
    }

    /**
     * 查询接入系统参数映射列表
     *
     * @param cmsReqMapping 接入系统参数映射信息
     * @return 接入系统参数映射集合
     */
    @Override
    public List<CmsReqMapping> selectCmsReqMappingList(CmsReqMapping cmsReqMapping) {
        return cmsReqMappingMapper.selectCmsReqMappingList(cmsReqMapping);
    }

    /**
     * 新增接入系统参数映射
     *
     * @param cmsReqMapping 接入系统参数映射信息
     * @return 结果
     */
    @Override
    public int insertCmsReqMapping(CmsReqMapping cmsReqMapping) {
        return cmsReqMappingMapper.insertCmsReqMapping(cmsReqMapping);
    }

    /**
     * 修改接入系统参数映射
     *
     * @param cmsReqMapping 接入系统参数映射信息
     * @return 结果
     */
    @Override
    public int updateCmsReqMapping(CmsReqMapping cmsReqMapping) {
        return cmsReqMappingMapper.updateCmsReqMapping(cmsReqMapping);
    }

    /**
     * 删除接入系统参数映射对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsReqMappingByIds(String ids) {
        return cmsReqMappingMapper.deleteCmsReqMappingByIds(Convert.toStrArray(ids));
    }

}
