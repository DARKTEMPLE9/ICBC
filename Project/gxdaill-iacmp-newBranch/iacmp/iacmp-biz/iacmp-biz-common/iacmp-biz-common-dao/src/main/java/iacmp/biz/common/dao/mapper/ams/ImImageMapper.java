package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImFile;
import net.northking.iacmp.common.bean.domain.ams.ImImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 上传影像 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImImageMapper {
    /**
     * 查询上传影像信息
     *
     * @param id 上传影像ID
     * @return 上传影像信息
     */
    ImImage selectImImageById(String id);

    /**
     * 查询上传影像列表
     *
     * @param imImage 上传影像信息
     * @return 上传影像集合
     */
    List<ImImage> selectImImageList(ImImage imImage);

    /**
     * 新增上传影像
     *
     * @param imImage 上传影像信息
     * @return 结果
     */
    int insertImImage(ImImage imImage);

    /**
     * 修改上传影像
     *
     * @param imImage 上传影像信息
     * @return 结果
     */
    int updateImImage(ImImage imImage);

    /**
     * 删除上传影像
     *
     * @param id 上传影像ID
     * @return 结果
     */
    int deleteImImageById(String id);

    /**
     * 批量删除上传影像
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImImageByIds(String[] ids);

    /**
     * 通过batchId查找相关影像
     *
     * @param batchId
     * @return
     */
    List<ImImage> selectImImagesByBatchId(String batchId);


    /**
     * 通过imageIds查找相关影像
     *
     * @param imageIds
     * @return
     */
    List<ImImage> selectImImageByIds(String[] imageIds);

    /**
     * 通过md5查找相关影像数量
     *
     * @param md5
     * @return
     */
    int selectImImageByMd5(String md5);

    /**
     * 通过全局流水号查找相关影像
     *
     * @param busiNo
     * @return
     */
    List<ImImage> selectImImagesByBusiNo(String busiNo);

    /**
     * 修改batchId
     */
    int updateBatchId(@Param("batchId") String batchId, @Param("regId") String regId);

    /**
     * 通过batchId集合查找相关影像
     *
     * @param batchIds
     * @return
     */
    List<ImImage> selectImImagesByBatchIdList(@Param("batchIds") List<String> batchIds);

}