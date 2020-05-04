package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsUserBillTmp;

import java.util.List;

/**
 * 临时分类权限 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface CmsUserBillTmpMapper {
    /**
     * 查询临时分类权限信息
     *
     * @param userId 临时分类权限ID
     * @return 临时分类权限信息
     */
    CmsUserBillTmp selectCmsUserBillTmpById(Long userId);

    /**
     * 查询临时分类权限列表
     *
     * @param cmsUserBillTmp 临时分类权限信息
     * @return 临时分类权限集合
     */
    List<CmsUserBillTmp> selectCmsUserBillTmpList(CmsUserBillTmp cmsUserBillTmp);

    /**
     * 新增临时分类权限
     *
     * @param cmsUserBillTmp 临时分类权限信息
     * @return 结果
     */
    int insertCmsUserBillTmp(CmsUserBillTmp cmsUserBillTmp);

    /**
     * 修改临时分类权限
     *
     * @param cmsUserBillTmp 临时分类权限信息
     * @return 结果
     */
    int updateCmsUserBillTmp(CmsUserBillTmp cmsUserBillTmp);

    /**
     * 删除临时分类权限
     *
     * @param userId 临时分类权限ID
     * @return 结果
     */
    int deleteCmsUserBillTmpById(Long userId);

    /**
     * 批量删除临时分类权限
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsUserBillTmpByIds(String[] userIds);

}