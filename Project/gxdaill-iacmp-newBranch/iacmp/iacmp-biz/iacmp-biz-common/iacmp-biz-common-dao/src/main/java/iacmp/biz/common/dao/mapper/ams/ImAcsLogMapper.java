package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImAcsLog;

import java.util.List;

/**
 * 接入日志 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImAcsLogMapper {
    /**
     * 查询接入日志信息
     *
     * @param id 接入日志ID
     * @return 接入日志信息
     */
    ImAcsLog selectImAcsLogById(String id);

    /**
     * 查询接入日志列表
     *
     * @param imAcsLog 接入日志信息
     * @return 接入日志集合
     */
    List<ImAcsLog> selectImAcsLogList(ImAcsLog imAcsLog);

    /**
     * 新增接入日志
     *
     * @param imAcsLog 接入日志信息
     * @return 结果
     */
    int insertImAcsLog(ImAcsLog imAcsLog);

    /**
     * 修改接入日志
     *
     * @param imAcsLog 接入日志信息
     * @return 结果
     */
    int updateImAcsLog(ImAcsLog imAcsLog);

    /**
     * 删除接入日志
     *
     * @param id 接入日志ID
     * @return 结果
     */
    int deleteImAcsLogById(String id);

    /**
     * 批量删除接入日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImAcsLogByIds(String[] ids);

}