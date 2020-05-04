package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsSystem;
import net.northking.iacmp.system.domain.SysConfig;

import java.util.List;

/**
 * 接入系统 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface CmsSystemMapper {
    /**
     * 查询接入系统信息
     *
     * @param id 接入系统ID
     * @return 接入系统信息
     */
    CmsSystem selectCmsSystemById(Long id);

    /**
     * 查询接入系统列表
     *
     * @param cmsSystem 接入系统信息
     * @return 接入系统集合
     */
    List<CmsSystem> selectCmsSystemList(CmsSystem cmsSystem);

    /**
     * 新增接入系统
     *
     * @param cmsSystem 接入系统信息
     * @return 结果
     */
    int insertCmsSystem(CmsSystem cmsSystem);

    /**
     * 修改接入系统
     *
     * @param cmsSystem 接入系统信息
     * @return 结果
     */
    int updateCmsSystem(CmsSystem cmsSystem);

    /**
     * 删除接入系统
     *
     * @param id 接入系统ID
     * @return 结果
     */
    int deleteCmsSystemById(Long id);

    /**
     * 批量删除接入系统
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsSystemByIds(String[] ids);

    /**
     * 通过系统来源查询接入系统
     *
     * @param sysCode
     * @return
     */
    CmsSystem selectCmsSystemByCode(String sysCode);

    /**
     * 通过系统编码查找系统信息
     *
     * @param sysCode
     * @return
     */
    SysConfig checkSystemKeyUnique(String sysCode);
}