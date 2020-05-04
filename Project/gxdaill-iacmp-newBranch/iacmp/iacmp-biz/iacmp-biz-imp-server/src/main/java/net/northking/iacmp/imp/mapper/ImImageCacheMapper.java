package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImImageCache;

import java.util.HashMap;
import java.util.List;

/**
 * 异步影像 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImImageCacheMapper {
    /**
     * 查询异步影像信息
     *
     * @param id 异步影像ID
     * @return 异步影像信息
     */
    ImImageCache selectImImageCacheById(Long id);

    List<ImImageCache> selectByImageId(String imageId);

    List<ImImageCache> scanLookAtImageCache(HashMap map);

    /**
     * 查询异步影像列表
     *
     * @param imImageCache 异步影像信息
     * @return 异步影像集合
     */
    List<ImImageCache> selectImImageCacheList(ImImageCache imImageCache);

    /**
     * 新增异步影像
     *
     * @param imImageCache 异步影像信息
     * @return 结果
     */
    int insertImImageCache(ImImageCache imImageCache);

    /**
     * 修改异步影像
     *
     * @param imImageCache 异步影像信息
     * @return 结果
     */
    int updateImImageCache(ImImageCache imImageCache);

    /**
     * 删除异步影像
     *
     * @param id 异步影像ID
     * @return 结果
     */
    int deleteImImageCacheById(Long id);

    /**
     * 批量删除异步影像
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImImageCacheByIds(String[] ids);

}