package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.ImImageLostLog;

import java.util.List;

/**
 * 影像缺失记录 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IImImageLostLogService {
    /**
     * 查询影像缺失记录信息
     *
     * @param id 影像缺失记录ID
     * @return 影像缺失记录信息
     */
    public ImImageLostLog selectImImageLostLogById(String id);

    /**
     * 查询影像缺失记录列表
     *
     * @param imImageLostLog 影像缺失记录信息
     * @return 影像缺失记录集合
     */
    public List<ImImageLostLog> selectImImageLostLogList(ImImageLostLog imImageLostLog);

    /**
     * 新增影像缺失记录
     *
     * @param imImageLostLog 影像缺失记录信息
     * @return 结果
     */
    public int insertImImageLostLog(ImImageLostLog imImageLostLog);

    /**
     * 修改影像缺失记录
     *
     * @param imImageLostLog 影像缺失记录信息
     * @return 结果
     */
    public int updateImImageLostLog(ImImageLostLog imImageLostLog);

    /**
     * 删除影像缺失记录信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImImageLostLogByIds(String ids);

}
