package net.northking.iacmp.cms.service.impl;


import iacmp.biz.common.dao.mapper.cms.CmsImageMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsImageService;
import net.northking.iacmp.common.bean.domain.cms.CmsImage;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 影像 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsImageServiceImpl implements ICmsImageService {
    @Autowired
    private CmsImageMapper cmsImageMapper;

    /**
     * 查询影像信息
     *
     * @param id 影像ID
     * @return 影像信息
     */
    @Override
    public CmsImage selectCmsImageById(Long id) {
        return cmsImageMapper.selectCmsImageById(id);
    }

    @Override
    public CmsImage selectCmsImageByImageId(Long id) {
        return cmsImageMapper.selectCmsImageByImageId(id);
    }

    /**
     * 查询影像列表
     *
     * @param cmsImage 影像信息
     * @return 影像集合
     */
    @Override
    public List<CmsImage> selectCmsImageList(CmsImage cmsImage) {
        return cmsImageMapper.selectCmsImageList(cmsImage);
    }

    /**
     * 新增影像
     *
     * @param cmsImage 影像信息
     * @return 结果
     */
    @Override
    public int insertCmsImage(CmsImage cmsImage) {
        return cmsImageMapper.insertCmsImage(cmsImage);
    }

    /**
     * 修改影像
     *
     * @param cmsImage 影像信息
     * @return 结果
     */
    @Override
    public int updateCmsImage(CmsImage cmsImage) {
        return cmsImageMapper.updateCmsImage(cmsImage);
    }

    /**
     * 删除影像对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsImageByIds(String ids) {
        return cmsImageMapper.deleteCmsImageByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<CmsImage> selectCmsImageByIds(List<Long> imageIds) {
        return cmsImageMapper.selectCmsImageByIds(imageIds);
    }

    @Override
    public List<CmsImage> selectCmsImageByIdArray(Long[] imageIds) {
        return cmsImageMapper.selectCmsImageByIdArray(imageIds);
    }

    @Override
    public int selectCountCmsImageByMd5(String md5) {
        return cmsImageMapper.selectCountCmsImageByMd5(md5);
    }

    /**
     * 通过batchId,modelId,billId查询文件
     *
     * @param batchId
     * @param billId
     * @return
     */
    @Override
    public List<CmsImage> selectImageByCondition(Long batchId, Long billId) {
        return cmsImageMapper.selectImageByCondition(batchId, billId);
    }

    /**
     * 通过md5值删除影像信息
     *
     * @param md5s 需要删除的md5值
     * @return 结果
     */
    @Override
    public int deleteCmsImageByMD5s(String md5s) {
        return cmsImageMapper.deleteCmsImageByMD5s(Convert.toStrArray(md5s));
    }

    @Override
    public List<CmsImage> selectCmsImageByMd5(String md5, Integer status) {
        return cmsImageMapper.selectCmsImageByMd5(md5, status);
    }

    @Override
    public CmsImage selectOneImageByMd5(String md5) {
        return cmsImageMapper.selectOneImageByMd5(md5);
    }

    @Override
    public int deleteCmsImageByCondition(Long batchId, Integer billId, List<String> imageNames) {
        return cmsImageMapper.deleteCmsImageByCondition(batchId, billId, imageNames);
    }

}
