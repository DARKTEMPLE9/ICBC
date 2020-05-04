package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.ImAnnotation;

import java.util.List;

/**
 * 影像注释 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IImAnnotationService {
    /**
     * 查询影像注释信息
     *
     * @param id 影像注释ID
     * @return 影像注释信息
     */
    public ImAnnotation selectImAnnotationById(String id);

    /**
     * 查询影像注释列表
     *
     * @param imAnnotation 影像注释信息
     * @return 影像注释集合
     */
    public List<ImAnnotation> selectImAnnotationList(ImAnnotation imAnnotation);

    /**
     * 新增影像注释
     *
     * @param imAnnotation 影像注释信息
     * @return 结果
     */
    public int insertImAnnotation(ImAnnotation imAnnotation);

    /**
     * 修改影像注释
     *
     * @param imAnnotation 影像注释信息
     * @return 结果
     */
    public int updateImAnnotation(ImAnnotation imAnnotation);

    /**
     * 删除影像注释信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImAnnotationByIds(String ids);

}
