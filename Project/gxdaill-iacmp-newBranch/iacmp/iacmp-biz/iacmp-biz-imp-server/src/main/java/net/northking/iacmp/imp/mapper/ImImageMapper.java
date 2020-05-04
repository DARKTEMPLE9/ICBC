package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImImage;
import net.northking.iacmp.imp.dto.ImImageDTO;
import net.northking.iacmp.imp.vo.ImBillVO;
import net.northking.iacmp.imp.vo.ImImageVO;

import java.util.List;
import java.util.Map;

/**
 * 影像 数据层
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
public interface ImImageMapper {
    /**
     * 查询影像信息
     *
     * @param imImage 影像ID
     * @return 影像信息
     */
    ImImage selectImImageById(ImImage imImage);

    Integer selectByBillId(String billId);

    /**
     * 查询影像列表
     *
     * @param imImage 影像信息
     * @return 影像集合
     */
    List<ImImage> selectImImageList(ImImage imImage);

    List<ImImage> selectImagesByBusino(String busino);

    /**
     * 新增影像
     *
     * @param imImage 影像信息
     * @return 结果
     */
    int insertImImage(ImImage imImage);

    /**
     * 修改影像
     *
     * @param imImage 影像信息
     * @return 结果
     */
    int updateImImage(ImImage imImage);

    /**
     * 删除影像
     *
     * @param id 影像ID
     * @return 结果
     */
    int deleteImImageById(String id);

    /**
     * 批量删除影像
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImImageByIds(String[] ids);

    /**
     * 按分类分组查找影像
     *
     * @param imImage
     * @return
     */
    List<ImBillVO> selectImImageGroupByBill(ImImage imImage);

    /**
     * 根据影像获取接口查询影像列表
     *
     * @param paramMap 接口参数map
     * @return 影像集合
     */
    List<ImImage> selectImImageByMap(Map<String, Object> paramMap);


    /**
     * 查找列表
     *
     * @param imImage
     * @return
     */
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    List<ImImage> selectImImageVOList(ImImageVO imImage);

    /**
     * 通过影像id及流水号查找影像信息
     *
     * @param imImageDTO
     * @return
     */
    List<ImImage> selectImagesByIds(ImImageDTO imImageDTO);
}