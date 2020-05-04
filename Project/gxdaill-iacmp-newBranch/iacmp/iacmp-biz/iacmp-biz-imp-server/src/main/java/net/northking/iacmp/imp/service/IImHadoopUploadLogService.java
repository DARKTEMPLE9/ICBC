package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImHadoopUploadLog;

import java.util.List;

/**
 * 大数据上传日志 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IImHadoopUploadLogService {
    /**
     * 查询大数据上传日志信息
     *
     * @param id 大数据上传日志ID
     * @return 大数据上传日志信息
     */
    ImHadoopUploadLog selectImHadoopUploadLogById(String id);

    /**
     * 查询大数据上传日志列表
     *
     * @param imHadoopUploadLog 大数据上传日志信息
     * @return 大数据上传日志集合
     */
    List<ImHadoopUploadLog> selectImHadoopUploadLogList(ImHadoopUploadLog imHadoopUploadLog);

    /**
     * 新增大数据上传日志
     *
     * @param imHadoopUploadLog 大数据上传日志信息
     * @return 结果
     */
    int insertImHadoopUploadLog(ImHadoopUploadLog imHadoopUploadLog);

    /**
     * 修改大数据上传日志
     *
     * @param imHadoopUploadLog 大数据上传日志信息
     * @return 结果
     */
    int updateImHadoopUploadLog(ImHadoopUploadLog imHadoopUploadLog);

    /**
     * 删除大数据上传日志信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImHadoopUploadLogByIds(String ids);

}
