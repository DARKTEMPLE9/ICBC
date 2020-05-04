package net.northking.iacmp.cms.service;


import net.northking.iacmp.common.bean.domain.cms.CmsKeyword;

import java.util.List;

/**
 * 标签 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface ICmsKeywordService {
    /**
     * 查询标签信息
     *
     * @param id 标签ID
     * @return 标签信息
     */
    CmsKeyword selectCmsKeywordById(Long id);

    /**
     * 查询标签列表
     *
     * @param cmsKeyword 标签信息
     * @return 标签集合
     */
    List<CmsKeyword> selectCmsKeywordList(CmsKeyword cmsKeyword);

    /**
     * 新增标签
     *
     * @param cmsKeyword 标签信息
     * @return 结果
     */
    int insertCmsKeyword(CmsKeyword cmsKeyword);

    /**
     * 修改标签
     *
     * @param cmsKeyword 标签信息
     * @return 结果
     */
    int updateCmsKeyword(CmsKeyword cmsKeyword);

    /**
     * 删除标签信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsKeywordByIds(String ids);

}