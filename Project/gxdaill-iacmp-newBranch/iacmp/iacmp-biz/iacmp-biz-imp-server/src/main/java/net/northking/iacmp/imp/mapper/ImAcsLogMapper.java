package net.northking.iacmp.imp.mapper;


import net.northking.iacmp.imp.domain.ImAcsLog;

import java.util.HashMap;
import java.util.List;

/**
 * 日志 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImAcsLogMapper {
    /**
     * 查询日志信息
     *
     * @param id 日志ID
     * @return 日志信息
     */
    HashMap imAcsLogById(String id);

    /**
     * 查询日志列表
     *
     * @param imAcsLog 日志信息
     * @return 日志集合
     */
    List<ImAcsLog> selectImAcsLogList(HashMap map);

    Integer selectAcsLogCount(HashMap map);

    /**
     * 新增日志
     *
     * @param imAcsLog 日志信息
     * @return 结果
     */
    int insertImAcsLog(ImAcsLog imAcsLog);

    /**
     * 修改日志
     *
     * @param imAcsLog 日志信息
     * @return 结果
     */
    int updateImAcsLog(ImAcsLog imAcsLog);

    /**
     * 删除日志
     *
     * @param id 日志ID
     * @return 结果
     */
    int deleteImAcsLogById(String id);

    /**
     * 批量删除日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImAcsLogByIds(String[] ids);

}