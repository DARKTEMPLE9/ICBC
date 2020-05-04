package net.northking.iacmp.cms.service;

import net.northking.iacmp.common.bean.domain.cms.CmsImage;

import java.util.List;

/**
 * 影像 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
public interface ICmsImageService {
    /**
     * 查询影像信息
     *
     * @param id 影像ID
     * @return 影像信息
     */
    CmsImage selectCmsImageById(Long id);

    /**
     * 查询影像信息
     *
     * @param id 影像ID
     * @return 影像信息
     */
    CmsImage selectCmsImageByImageId(Long id);

    /**
     * 查询影像列表
     *
     * @param cmsImage 影像信息
     * @return 影像集合
     */
    List<CmsImage> selectCmsImageList(CmsImage cmsImage);

    /**
     * 新增影像
     *
     * @param cmsImage 影像信息
     * @return 结果
     */
    int insertCmsImage(CmsImage cmsImage);

    /**
     * 修改影像
     *
     * @param cmsImage 影像信息
     * @return 结果
     */
    int updateCmsImage(CmsImage cmsImage);

    /**
     * 删除影像信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsImageByIds(String ids);

    List<CmsImage> selectCmsImageByIds(List<Long> imageIds);

    List<CmsImage> selectCmsImageByIdArray(Long[] imageIds);

    int selectCountCmsImageByMd5(String md5);

    /**
     * 通过batchId,modelId,billId查询文件
     *
     * @param batchId
     * @param billId
     * @return
     */
    List<CmsImage> selectImageByCondition(Long batchId, Long billId);

    /**
     * 通过md5值删除影像信息
     *
     * @param md5s 需要删除md5值
     * @return 结果
     */
    int deleteCmsImageByMD5s(String md5s);

    List<CmsImage> selectCmsImageByMd5(String md5, Integer status);


    CmsImage selectOneImageByMd5(String md5);

    int deleteCmsImageByCondition(Long batchId, Integer billId, List<String> imageNames);
}
