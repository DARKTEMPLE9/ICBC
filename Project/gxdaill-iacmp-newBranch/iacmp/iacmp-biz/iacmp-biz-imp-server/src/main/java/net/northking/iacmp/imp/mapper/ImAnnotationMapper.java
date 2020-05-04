package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImAnnotation;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 图片批注 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImAnnotationMapper {
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
     * 删除图片批注
     *
     * @param id 图片批注ID
     * @return 结果
     */
    int deleteImAnnotationById(String id);

    /**
     * 批量删除图片批注
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImAnnotationByIds(String[] ids);

    /**
     * 通过imageIds获取注解信息
     *
     * @param ids
     * @return
     */
    List<ImAnnotation> selectImAnnotationByIds(@PathVariable("ids") String[] ids);
}