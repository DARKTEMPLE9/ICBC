package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImImageLostLog;

import java.util.List;

/**
 * 影像缺失记录 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImImageLostLogMapper {
    /**
     * 查询影像缺失记录信息
     *
     * @param id 影像缺失记录ID
     * @return 影像缺失记录信息
     */
    ImImageLostLog selectImImageLostLogById(String id);

    /**
     * 查询影像缺失记录列表
     *
     * @param imImageLostLog 影像缺失记录信息
     * @return 影像缺失记录集合
     */
    List<ImImageLostLog> selectImImageLostLogList(ImImageLostLog imImageLostLog);

    /**
     * 新增影像缺失记录
     *
     * @param imImageLostLog 影像缺失记录信息
     * @return 结果
     */
    int insertImImageLostLog(ImImageLostLog imImageLostLog);

    /**
     * 修改影像缺失记录
     *
     * @param imImageLostLog 影像缺失记录信息
     * @return 结果
     */
    int updateImImageLostLog(ImImageLostLog imImageLostLog);

    /**
     * 删除影像缺失记录
     *
     * @param id 影像缺失记录ID
     * @return 结果
     */
    int deleteImImageLostLogById(String id);

    /**
     * 批量删除影像缺失记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImImageLostLogByIds(String[] ids);

}