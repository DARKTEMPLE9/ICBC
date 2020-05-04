package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImImage;
import net.northking.iacmp.imp.dto.ImImageImUserDTO;
import net.northking.iacmp.imp.vo.ImBillVO;
import net.northking.iacmp.imp.vo.ImImageVO;

import java.util.List;
import java.util.Map;

/**
 * 影像 服务层
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
public interface IImImageService {
    /**
     * 查询影像信息
     *
     * @param imImage 影像
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
     * 删除影像信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImImageByIds(String ids);

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
     * 查询影像和用户信息
     *
     * @param imImage
     * @return
     */
    List<ImImageImUserDTO> selectImageUserInfo(ImImageVO imImage);

    /**
     * 通过影像id，查找影像信息
     *
     * @param ids
     * @param busino
     * @return
     */
    List<ImImage> selectImagesByIds(String ids, String busino);
}
