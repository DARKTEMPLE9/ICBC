package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.OldImImage;

import java.util.List;
import java.util.Map;

/**
 * 影像 数据层
 *
 * @author wei.chen
 * @date 2019-10-22
 */
public interface OldImImageMapper {
    /**
     * 查询影像信息
     *
     * @param id 影像ID
     * @return 影像信息
     */
    public OldImImage selectImImageById(String id);

    /**
     * 查询影像列表
     *
     * @param imImage 影像信息
     * @return 影像集合
     */
    public List<OldImImage> selectImImageList(OldImImage imImage);

    /**
     * 新增影像
     *
     * @param imImage 影像信息
     * @return 结果
     */
    public int insertImImage(OldImImage imImage);

    /**
     * 修改影像
     *
     * @param imImage 影像信息
     * @return 结果
     */
    public int updateImImage(OldImImage imImage);

    /**
     * 删除影像
     *
     * @param id 影像ID
     * @return 结果
     */
    public int deleteImImageById(String id);

    /**
     * 批量删除影像
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImImageByIds(String[] ids);

    /**
     * 根据参数查询影像列表
     *
     * @param paramMap 影像信息
     * @return 影像集合
     */
    public List<OldImImage> selectImImageByMap(Map<String, Object> paramMap);

}