package net.northking.iacmp.cms.service.impl;

import iacmp.biz.common.dao.mapper.cms.CmsUserBillTmpMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsUserBillTmpService;
import net.northking.iacmp.common.bean.domain.cms.CmsUserBillTmp;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 临时分类权限 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsUserBillTmpServiceImpl implements ICmsUserBillTmpService {
    @Autowired
    private CmsUserBillTmpMapper cmsUserBillTmpMapper;

    /**
     * 查询临时分类权限信息
     *
     * @param userId 临时分类权限ID
     * @return 临时分类权限信息
     */
    @Override
    public CmsUserBillTmp selectCmsUserBillTmpById(Long userId) {
        return cmsUserBillTmpMapper.selectCmsUserBillTmpById(userId);
    }

    /**
     * 查询临时分类权限列表
     *
     * @param cmsUserBillTmp 临时分类权限信息
     * @return 临时分类权限集合
     */
    @Override
    public List<CmsUserBillTmp> selectCmsUserBillTmpList(CmsUserBillTmp cmsUserBillTmp) {
        return cmsUserBillTmpMapper.selectCmsUserBillTmpList(cmsUserBillTmp);
    }

    /**
     * 新增临时分类权限
     *
     * @param cmsUserBillTmp 临时分类权限信息
     * @return 结果
     */
    @Override
    public int insertCmsUserBillTmp(CmsUserBillTmp cmsUserBillTmp) {
        return cmsUserBillTmpMapper.insertCmsUserBillTmp(cmsUserBillTmp);
    }

    /**
     * 修改临时分类权限
     *
     * @param cmsUserBillTmp 临时分类权限信息
     * @return 结果
     */
    @Override
    public int updateCmsUserBillTmp(CmsUserBillTmp cmsUserBillTmp) {
        return cmsUserBillTmpMapper.updateCmsUserBillTmp(cmsUserBillTmp);
    }

    /**
     * 删除临时分类权限对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsUserBillTmpByIds(String ids) {
        return cmsUserBillTmpMapper.deleteCmsUserBillTmpByIds(Convert.toStrArray(ids));
    }

}
