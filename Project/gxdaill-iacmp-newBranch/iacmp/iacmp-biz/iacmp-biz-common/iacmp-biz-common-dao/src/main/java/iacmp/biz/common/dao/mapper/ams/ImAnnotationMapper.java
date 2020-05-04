package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImAnnotation;

import java.util.List;

/**
 * 影像注释 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImAnnotationMapper {
    /**
     * 查询影像注释信息
     *
     * @param id 影像注释ID
     * @return 影像注释信息
     */
    ImAnnotation selectImAnnotationById(String id);

    /**
     * 查询影像注释列表
     *
     * @param imAnnotation 影像注释信息
     * @return 影像注释集合
     */
    List<ImAnnotation> selectImAnnotationList(ImAnnotation imAnnotation);

    /**
     * 新增影像注释
     *
     * @param imAnnotation 影像注释信息
     * @return 结果
     */
    int insertImAnnotation(ImAnnotation imAnnotation);

    /**
     * 修改影像注释
     *
     * @param imAnnotation 影像注释信息
     * @return 结果
     */
    int updateImAnnotation(ImAnnotation imAnnotation);

    /**
     * 删除影像注释
     *
     * @param id 影像注释ID
     * @return 结果
     */
    int deleteImAnnotationById(String id);

    /**
     * 批量删除影像注释
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImAnnotationByIds(String[] ids);

}