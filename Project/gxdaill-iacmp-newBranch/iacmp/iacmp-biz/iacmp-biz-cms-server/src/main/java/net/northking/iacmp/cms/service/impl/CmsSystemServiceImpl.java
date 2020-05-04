package net.northking.iacmp.cms.service.impl;

import iacmp.biz.common.dao.mapper.cms.CmsSystemMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsSystemService;
import net.northking.iacmp.common.bean.domain.cms.CmsSystem;
import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.system.domain.SysConfig;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 接入系统 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsSystemServiceImpl implements ICmsSystemService {
    @Autowired
    private CmsSystemMapper cmsSystemMapper;

    /**
     * 查询接入系统信息
     *
     * @param id 接入系统ID
     * @return 接入系统信息
     */
    @Override
    public CmsSystem selectCmsSystemById(Long id) {
        return cmsSystemMapper.selectCmsSystemById(id);
    }

    /**
     * 查询接入系统列表
     *
     * @param cmsSystem 接入系统信息
     * @return 接入系统集合
     */
    @Override
    public List<CmsSystem> selectCmsSystemList(CmsSystem cmsSystem) {
        return cmsSystemMapper.selectCmsSystemList(cmsSystem);
    }

    /**
     * 新增接入系统
     *
     * @param cmsSystem 接入系统信息
     * @return 结果
     */
    @Override
    public int insertCmsSystem(CmsSystem cmsSystem) {
        return cmsSystemMapper.insertCmsSystem(cmsSystem);
    }

    /**
     * 修改接入系统
     *
     * @param cmsSystem 接入系统信息
     * @return 结果
     */
    @Override
    public int updateCmsSystem(CmsSystem cmsSystem) {
        return cmsSystemMapper.updateCmsSystem(cmsSystem);
    }

    /**
     * 删除接入系统对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsSystemByIds(String ids) {
        return cmsSystemMapper.deleteCmsSystemByIds(Convert.toStrArray(ids));
    }

    @Override
    public CmsSystem selectCmsSystemByCode(String sysCode) {
        return cmsSystemMapper.selectCmsSystemByCode(sysCode);
    }

    /**
     * 校验系统编码唯一
     *
     * @param cmsSystem
     * @return
     */
    @Override
    public String checkSystemKeyUnique(CmsSystem cmsSystem) {
        Long systemId = StringUtils.isNull(cmsSystem.getId()) ? -1L : cmsSystem.getId();
        SysConfig info = cmsSystemMapper.checkSystemKeyUnique(cmsSystem.getSysCode());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != systemId.longValue()) {
            return UserConstants.CONFIG_KEY_NOT_UNIQUE;
        }
        return UserConstants.CONFIG_KEY_UNIQUE;
    }

}
