package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsImage;
import net.northking.iacmp.common.bean.dto.cms.CmsImageDTO;
import net.northking.iacmp.common.bean.vo.cms.CmsImageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 影像 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
public interface CmsImageMapper {
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
     * @param id 影像逻辑主键
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
     * 删除影像
     *
     * @param id 影像ID
     * @return 结果
     */
    int deleteCmsImageById(Long id);

    /**
     * 批量删除影像
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsImageByIds(String[] ids);

    /**
     * 查询影像列表
     *
     * @param batchId 影响批次
     * @return 影像集合
     */
    List<CmsImage> selectImageByBatchId(@Param("batchId") Long batchId);

    List<CmsImage> selectImageHistory(@Param("batchId") Long batchId, @Param("billId") Integer billId, @Param("names") String[] names);

    List<CmsImage> selectCmsImageByIds(@Param("imageIds") List<Long> imageIds);

    List<CmsImage> selectCmsImageByIdArray(@Param("imageIds") Long[] imageIds);

    int selectCountCmsImageByMd5(@Param("md5") String md5);

    List<CmsImage> selectImageByCondition(@Param("batchId") Long batchId, @Param("billId") Long billId);

    List<CmsImage> selectImageListByCondition(@Param("batchId") Long batchId, @Param("billId") Long billId);

    Integer selectImageNumByCondition(@Param("batchId") Long batchId, @Param("billId") Long billId);

    /**
     * 通过md5值删除影像信息
     *
     * @param md5s 需要删除的md5值
     * @return 结果
     */
    int deleteCmsImageByMD5s(String[] md5s);

    List<CmsImage> selectCmsImageByMd5(@Param("md5") String md5, @Param("status") Integer status);

    CmsImage selectOneImageByMd5(@Param("md5") String md5);

    List<CmsImageDTO> selectCmsImageListByOpts(CmsImageVO cmsImage);

    List<CmsImageDTO> selectCmsImageListByBillCode(String billCode);

    /**
     * 批量更新
     *
     * @param originCmsImages
     * @return
     */
    int updateCmsImages(@Param("originCmsImages") List<CmsImage> originCmsImages);

    /**
     * 批量新增
     *
     * @param originCmsImages
     * @return
     */
    int insertCmsImages(@Param("originCmsImages") List<CmsImage> originCmsImages);

    /**
     * 批量条件删除
     *
     * @param batchId
     * @param billId
     * @param imageNames
     * @return
     */
    int deleteCmsImageByCondition(@Param("batchId") Long batchId, @Param("billId") Integer billId, @Param("imageNames") List<String> imageNames);

    int updateCmsImageBill(@Param("batchId") Long batchId, @Param("billIds") Integer[] billIds, @Param("imageNames") String[] imageNames,
                           @Param("targetBillId") Integer targetBillId, @Param("trg") String trg);

}