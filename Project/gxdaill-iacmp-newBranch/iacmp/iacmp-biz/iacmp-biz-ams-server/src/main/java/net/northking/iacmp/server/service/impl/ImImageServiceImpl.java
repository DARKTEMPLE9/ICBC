package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImImageMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImImage;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 上传影像 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImImageServiceImpl implements IImImageService {
    @Autowired
    private ImImageMapper imImageMapper;

    /**
     * 查询上传影像信息
     *
     * @param id 上传影像ID
     * @return 上传影像信息
     */
    @Override
    public ImImage selectImImageById(String id) {
        return imImageMapper.selectImImageById(id);
    }

    /**
     * 查询上传影像列表
     *
     * @param imImage 上传影像信息
     * @return 上传影像集合
     */
    @Override
    public List<ImImage> selectImImageList(ImImage imImage) {
        return imImageMapper.selectImImageList(imImage);
    }

    /**
     * 新增上传影像
     *
     * @param imImage 上传影像信息
     * @return 结果
     */
    @Override
    public int insertImImage(ImImage imImage) {
        return imImageMapper.insertImImage(imImage);
    }

    /**
     * 修改上传影像
     *
     * @param imImage 上传影像信息
     * @return 结果
     */
    @Override
    public int updateImImage(ImImage imImage) {
        return imImageMapper.updateImImage(imImage);
    }

    /**
     * 删除上传影像对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImImageByIds(String ids) {
        return imImageMapper.deleteImImageByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过batchId查找相关影像
     *
     * @param batchId
     * @return
     */
    @Override
    public List<ImImage> selectImImagesByBatchId(String batchId) {
        return imImageMapper.selectImImagesByBatchId(batchId);
    }

    @Override
    public List<ImImage> selectImImageByIds(String[] imageIds) {
        return imImageMapper.selectImImageByIds(imageIds);
    }

    @Override
    public int selectImImageByMd5(String md5) {
        return imImageMapper.selectImImageByMd5(md5);
    }

    /**
     * 通过全局流水号查找相关影像
     *
     * @param busiNo
     * @return
     */
    @Override
    public List<ImImage> selectImImagesByBusiNo(String busiNo) {
        return imImageMapper.selectImImagesByBusiNo(busiNo);
    }

    /**
     * 更新batchId
     */
    @Override
    public int updateBatchId(String batchId, String regId) {
        return imImageMapper.updateBatchId(batchId, regId);
    }

    /**
     * 通过batchId集合查找相关影像
     *
     * @param batchIds
     * @return
     */
    @Override
    public List<ImImage> selectImImagesByBatchIdList(List<String> batchIds) {
        return imImageMapper.selectImImagesByBatchIdList(batchIds);
    }
}
