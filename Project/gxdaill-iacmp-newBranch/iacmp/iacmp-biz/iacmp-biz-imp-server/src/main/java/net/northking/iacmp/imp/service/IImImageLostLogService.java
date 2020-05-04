package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImImageLostLog;

import java.util.List;

/**
 * 影像缺失 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IImImageLostLogService {
    /**
     * 查询影像缺失信息
     *
     * @param iD 影像缺失ID
     * @return 影像缺失信息
     */
    ImImageLostLog selectImImageLostLogById(String iD);

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
     * 删除影像缺失信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImImageLostLogByIds(String ids);

}
