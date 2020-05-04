package net.northking.iacmp.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.ImAnnotationMapper;
import net.northking.iacmp.common.bean.domain.ams.ImAnnotation;
import net.northking.iacmp.server.service.IImAnnotationService;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

/**
 * 影像注释 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImAnnotationServiceImpl implements IImAnnotationService {
    @Autowired
    private ImAnnotationMapper imAnnotationMapper;

    /**
     * 查询影像注释信息
     *
     * @param id 影像注释ID
     * @return 影像注释信息
     */
    @Override
    public ImAnnotation selectImAnnotationById(String id) {
        return imAnnotationMapper.selectImAnnotationById(id);
    }

    /**
     * 查询影像注释列表
     *
     * @param imAnnotation 影像注释信息
     * @return 影像注释集合
     */
    @Override
    public List<ImAnnotation> selectImAnnotationList(ImAnnotation imAnnotation) {
        return imAnnotationMapper.selectImAnnotationList(imAnnotation);
    }

    /**
     * 新增影像注释
     *
     * @param imAnnotation 影像注释信息
     * @return 结果
     */
    @Override
    public int insertImAnnotation(ImAnnotation imAnnotation) {
        return imAnnotationMapper.insertImAnnotation(imAnnotation);
    }

    /**
     * 修改影像注释
     *
     * @param imAnnotation 影像注释信息
     * @return 结果
     */
    @Override
    public int updateImAnnotation(ImAnnotation imAnnotation) {
        return imAnnotationMapper.updateImAnnotation(imAnnotation);
    }

    /**
     * 删除影像注释对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImAnnotationByIds(String ids) {
        return imAnnotationMapper.deleteImAnnotationByIds(Convert.toStrArray(ids));
    }

}
