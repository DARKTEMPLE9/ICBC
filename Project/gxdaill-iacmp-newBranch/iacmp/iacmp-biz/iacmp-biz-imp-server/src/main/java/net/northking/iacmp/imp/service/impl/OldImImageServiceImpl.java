package net.northking.iacmp.imp.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.OldImImage;
import net.northking.iacmp.imp.mapper.OldImImageMapper;
import net.northking.iacmp.imp.service.IOldImImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 影像 服务层实现
 *
 * @author wei.chen
 * @date 2019-10-22
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class OldImImageServiceImpl implements IOldImImageService {
    @Autowired
    private OldImImageMapper imImageMapper;

    /**
     * 查询影像信息
     *
     * @param id 影像ID
     * @return 影像信息
     */
    @Override
    public OldImImage selectImImageById(String id) {
        return imImageMapper.selectImImageById(id);
    }

    /**
     * 查询影像列表
     *
     * @param imImage 影像信息
     * @return 影像集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<OldImImage> selectImImageList(OldImImage imImage) {
        return imImageMapper.selectImImageList(imImage);
    }

    /**
     * 新增影像
     *
     * @param imImage 影像信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImImage(OldImImage imImage) {
        return imImageMapper.insertImImage(imImage);
    }

    /**
     * 修改影像
     *
     * @param imImage 影像信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int updateImImage(OldImImage imImage) {
        return imImageMapper.updateImImage(imImage);
    }

    /**
     * 删除影像对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImImageByIds(String ids) {
        return imImageMapper.deleteImImageByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据参数查询影像列表
     *
     * @param paramMap 影像信息
     * @return 影像集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<OldImImage> selectImImageByMap(Map<String, Object> paramMap) {
        return imImageMapper.selectImImageByMap(paramMap);
    }
}
