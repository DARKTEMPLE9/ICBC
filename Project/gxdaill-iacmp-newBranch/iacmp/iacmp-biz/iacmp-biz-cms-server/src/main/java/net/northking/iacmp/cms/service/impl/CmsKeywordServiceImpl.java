package net.northking.iacmp.cms.service.impl;


import iacmp.biz.common.dao.mapper.cms.CmsKeywordMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsKeywordService;
import net.northking.iacmp.common.bean.domain.cms.CmsKeyword;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsKeywordServiceImpl implements ICmsKeywordService {
    @Autowired
    private CmsKeywordMapper cmsKeywordMapper;

    /**
     * 查询标签信息
     *
     * @param id 标签ID
     * @return 标签信息
     */
    @Override
    public CmsKeyword selectCmsKeywordById(Long id) {
        return cmsKeywordMapper.selectCmsKeywordById(id);
    }

    /**
     * 查询标签列表
     *
     * @param cmsKeyword 标签信息
     * @return 标签集合
     */
    @Override
    public List<CmsKeyword> selectCmsKeywordList(CmsKeyword cmsKeyword) {
        return cmsKeywordMapper.selectCmsKeywordList(cmsKeyword);
    }

    /**
     * 新增标签
     *
     * @param cmsKeyword 标签信息
     * @return 结果
     */
    @Override
    public int insertCmsKeyword(CmsKeyword cmsKeyword) {
        return cmsKeywordMapper.insertCmsKeyword(cmsKeyword);
    }

    /**
     * 修改标签
     *
     * @param cmsKeyword 标签信息
     * @return 结果
     */
    @Override
    public int updateCmsKeyword(CmsKeyword cmsKeyword) {
        return cmsKeywordMapper.updateCmsKeyword(cmsKeyword);
    }

    /**
     * 删除标签对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsKeywordByIds(String ids) {
        return cmsKeywordMapper.deleteCmsKeywordByIds(Convert.toStrArray(ids));
    }

}
