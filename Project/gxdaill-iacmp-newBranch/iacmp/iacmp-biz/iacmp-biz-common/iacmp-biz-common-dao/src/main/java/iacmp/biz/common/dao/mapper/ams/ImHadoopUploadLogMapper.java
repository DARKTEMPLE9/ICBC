package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImHadoopUploadLog;

import java.util.List;

/**
 * 视频上传日志 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImHadoopUploadLogMapper {
    /**
     * 查询视频上传日志信息
     *
     * @param id 视频上传日志ID
     * @return 视频上传日志信息
     */
    ImHadoopUploadLog selectImHadoopUploadLogById(String id);

    /**
     * 查询视频上传日志列表
     *
     * @param imHadoopUploadLog 视频上传日志信息
     * @return 视频上传日志集合
     */
    List<ImHadoopUploadLog> selectImHadoopUploadLogList(ImHadoopUploadLog imHadoopUploadLog);

    /**
     * 新增视频上传日志
     *
     * @param imHadoopUploadLog 视频上传日志信息
     * @return 结果
     */
    int insertImHadoopUploadLog(ImHadoopUploadLog imHadoopUploadLog);

    /**
     * 修改视频上传日志
     *
     * @param imHadoopUploadLog 视频上传日志信息
     * @return 结果
     */
    int updateImHadoopUploadLog(ImHadoopUploadLog imHadoopUploadLog);

    /**
     * 删除视频上传日志
     *
     * @param id 视频上传日志ID
     * @return 结果
     */
    int deleteImHadoopUploadLogById(String id);

    /**
     * 批量删除视频上传日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImHadoopUploadLogByIds(String[] ids);

}