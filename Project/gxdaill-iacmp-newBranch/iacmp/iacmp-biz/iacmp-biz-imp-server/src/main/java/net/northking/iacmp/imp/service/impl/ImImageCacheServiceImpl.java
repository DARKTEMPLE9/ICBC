package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImImageCache;
import net.northking.iacmp.imp.mapper.ImImageCacheMapper;
import net.northking.iacmp.imp.service.IImImageCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 异步影像 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImImageCacheServiceImpl implements IImImageCacheService {
    @Autowired
    private ImImageCacheMapper imImageCacheMapper;

    /**
     * 查询异步影像信息
     *
     * @param id 异步影像ID
     * @return 异步影像信息
     */
    @Override
    public ImImageCache selectImImageCacheById(Long id) {
        return imImageCacheMapper.selectImImageCacheById(id);
    }

    @Override
    public List<ImImageCache> selectByImageId(String imageId) {
        return imImageCacheMapper.selectByImageId(imageId);
    }

    @Override
    public List<ImImageCache> scanLookAtImageCache(HashMap map) {
        return imImageCacheMapper.scanLookAtImageCache(map);
    }

    /**
     * 查询异步影像列表
     *
     * @param imImageCache 异步影像信息
     * @return 异步影像集合
     */
    @Override
    public List<ImImageCache> selectImImageCacheList(ImImageCache imImageCache) {
        return imImageCacheMapper.selectImImageCacheList(imImageCache);
    }

    /**
     * 新增异步影像
     *
     * @param imImageCache 异步影像信息
     * @return 结果
     */
    @Override
    public int insertImImageCache(ImImageCache imImageCache) {
        return imImageCacheMapper.insertImImageCache(imImageCache);
    }

    /**
     * 修改异步影像
     *
     * @param imImageCache 异步影像信息
     * @return 结果
     */
    @Override
    public int updateImImageCache(ImImageCache imImageCache) {
        return imImageCacheMapper.updateImImageCache(imImageCache);
    }

    /**
     * 删除异步影像对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImImageCacheByIds(String ids) {
        return imImageCacheMapper.deleteImImageCacheByIds(Convert.toStrArray(ids));
    }

}
