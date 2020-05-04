package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImImageLostLog;

import java.util.List;

/**
 * 影像缺失 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImImageLostLogMapper {
    /**
     * 查询影像缺失信息
     *
     * @param id 影像缺失ID
     * @return 影像缺失信息
     */
    ImImageLostLog selectImImageLostLogById(String id);

    /**
     * 查询影像缺失列表
     *
     * @param imImageLostLog 影像缺失信息
     * @return 影像缺失集合
     */
    List<ImImageLostLog> selectImImageLostLogList(ImImageLostLog imImageLostLog);

    /**
     * 新增影像缺失
     *
     * @param imImageLostLog 影像缺失信息
     * @return 结果
     */
    int insertImImageLostLog(ImImageLostLog imImageLostLog);

    /**
     * 修改影像缺失
     *
     * @param imImageLostLog 影像缺失信息
     * @return 结果
     */
    int updateImImageLostLog(ImImageLostLog imImageLostLog);

    /**
     * 删除影像缺失
     *
     * @param id 影像缺失ID
     * @return 结果
     */
    int deleteImImageLostLogById(String id);

    /**
     * 批量删除影像缺失
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImImageLostLogByIds(String[] ids);

}