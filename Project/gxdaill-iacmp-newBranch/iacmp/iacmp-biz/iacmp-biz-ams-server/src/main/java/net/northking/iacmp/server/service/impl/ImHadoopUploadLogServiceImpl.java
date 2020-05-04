package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImHadoopUploadLogMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImHadoopUploadLog;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImHadoopUploadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频上传日志 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImHadoopUploadLogServiceImpl implements IImHadoopUploadLogService {
    @Autowired
    private ImHadoopUploadLogMapper imHadoopUploadLogMapper;

    /**
     * 查询视频上传日志信息
     *
     * @param id 视频上传日志ID
     * @return 视频上传日志信息
     */
    @Override
    public ImHadoopUploadLog selectImHadoopUploadLogById(String id) {
        return imHadoopUploadLogMapper.selectImHadoopUploadLogById(id);
    }

    /**
     * 查询视频上传日志列表
     *
     * @param imHadoopUploadLog 视频上传日志信息
     * @return 视频上传日志集合
     */
    @Override
    public List<ImHadoopUploadLog> selectImHadoopUploadLogList(ImHadoopUploadLog imHadoopUploadLog) {
        return imHadoopUploadLogMapper.selectImHadoopUploadLogList(imHadoopUploadLog);
    }

    /**
     * 新增视频上传日志
     *
     * @param imHadoopUploadLog 视频上传日志信息
     * @return 结果
     */
    @Override
    public int insertImHadoopUploadLog(ImHadoopUploadLog imHadoopUploadLog) {
        return imHadoopUploadLogMapper.insertImHadoopUploadLog(imHadoopUploadLog);
    }

    /**
     * 修改视频上传日志
     *
     * @param imHadoopUploadLog 视频上传日志信息
     * @return 结果
     */
    @Override
    public int updateImHadoopUploadLog(ImHadoopUploadLog imHadoopUploadLog) {
        return imHadoopUploadLogMapper.updateImHadoopUploadLog(imHadoopUploadLog);
    }

    /**
     * 删除视频上传日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImHadoopUploadLogByIds(String ids) {
        return imHadoopUploadLogMapper.deleteImHadoopUploadLogByIds(Convert.toStrArray(ids));
    }

}
