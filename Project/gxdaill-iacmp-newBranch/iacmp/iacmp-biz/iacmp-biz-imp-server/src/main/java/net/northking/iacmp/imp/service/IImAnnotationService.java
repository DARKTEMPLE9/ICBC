package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImAnnotation;

import java.util.List;

/**
 * 图片批注 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IImAnnotationService {
    /**
     * 查询图片批注信息
     *
     * @param id 图片批注ID
     * @return 图片批注信息
     */
    ImAnnotation selectImAnnotationById(String id);

    /**
     * 查询图片批注列表
     *
     * @param imAnnotation 图片批注信息
     * @return 图片批注集合
     */
    List<ImAnnotation> selectImAnnotationList(ImAnnotation imAnnotation);

    /**
     * 新增图片批注
     *
     * @param imAnnotation 图片批注信息
     * @return 结果
     */
    int insertImAnnotation(ImAnnotation imAnnotation);

    /**
     * 修改图片批注
     *
     * @param imAnnotation 图片批注信息
     * @return 结果
     */
    int updateImAnnotation(ImAnnotation imAnnotation);

    /**
     * 删除图片批注信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImAnnotationByIds(String ids);

    /**
     * 通过imageIds获取注解
     */
    List<ImAnnotation> selectImAnnotationByIds(String ids);
}
