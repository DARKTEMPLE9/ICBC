package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImAnnotation;
import net.northking.iacmp.imp.mapper.ImAnnotationMapper;
import net.northking.iacmp.imp.service.IImAnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图片批注 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImAnnotationServiceImpl implements IImAnnotationService {
    @Autowired
    private ImAnnotationMapper imAnnotationMapper;

    /**
     * 查询图片批注信息
     *
     * @param id 图片批注ID
     * @return 图片批注信息
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public ImAnnotation selectImAnnotationById(String id) {
        return imAnnotationMapper.selectImAnnotationById(id);
    }

    /**
     * 查询图片批注列表
     *
     * @param imAnnotation 图片批注信息
     * @return 图片批注集合
     */
    @Override
    public List<ImAnnotation> selectImAnnotationList(ImAnnotation imAnnotation) {
        return imAnnotationMapper.selectImAnnotationList(imAnnotation);
    }

    /**
     * 新增图片批注
     *
     * @param imAnnotation 图片批注信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImAnnotation(ImAnnotation imAnnotation) {
        return imAnnotationMapper.insertImAnnotation(imAnnotation);
    }

    /**
     * 修改图片批注
     *
     * @param imAnnotation 图片批注信息
     * @return 结果
     */
    @Override
    public int updateImAnnotation(ImAnnotation imAnnotation) {
        return imAnnotationMapper.updateImAnnotation(imAnnotation);
    }

    /**
     * 删除图片批注对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImAnnotationByIds(String ids) {
        return imAnnotationMapper.deleteImAnnotationByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过imageIds获取注解
     *
     * @param ids
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImAnnotation> selectImAnnotationByIds(String ids) {
        return imAnnotationMapper.selectImAnnotationByIds(Convert.toStrArray(ids));
    }

}
