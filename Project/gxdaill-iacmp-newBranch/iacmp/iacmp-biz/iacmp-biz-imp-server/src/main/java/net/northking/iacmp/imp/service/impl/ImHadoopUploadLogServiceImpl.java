package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImHadoopUploadLog;
import net.northking.iacmp.imp.mapper.ImHadoopUploadLogMapper;
import net.northking.iacmp.imp.service.IImHadoopUploadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大数据上传日志 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImHadoopUploadLogServiceImpl implements IImHadoopUploadLogService {
    @Autowired
    private ImHadoopUploadLogMapper imHadoopUploadLogMapper;

    /**
     * 查询大数据上传日志信息
     *
     * @param id 大数据上传日志ID
     * @return 大数据上传日志信息
     */
    @Override
    public ImHadoopUploadLog selectImHadoopUploadLogById(String id) {
        return imHadoopUploadLogMapper.selectImHadoopUploadLogById(id);
    }

    /**
     * 查询大数据上传日志列表
     *
     * @param imHadoopUploadLog 大数据上传日志信息
     * @return 大数据上传日志集合
     */
    @Override
    public List<ImHadoopUploadLog> selectImHadoopUploadLogList(ImHadoopUploadLog imHadoopUploadLog) {
        return imHadoopUploadLogMapper.selectImHadoopUploadLogList(imHadoopUploadLog);
    }

    /**
     * 新增大数据上传日志
     *
     * @param imHadoopUploadLog 大数据上传日志信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImHadoopUploadLog(ImHadoopUploadLog imHadoopUploadLog) {
        return imHadoopUploadLogMapper.insertImHadoopUploadLog(imHadoopUploadLog);
    }

    /**
     * 修改大数据上传日志
     *
     * @param imHadoopUploadLog 大数据上传日志信息
     * @return 结果
     */
    @Override
    public int updateImHadoopUploadLog(ImHadoopUploadLog imHadoopUploadLog) {
        return imHadoopUploadLogMapper.updateImHadoopUploadLog(imHadoopUploadLog);
    }

    /**
     * 删除大数据上传日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImHadoopUploadLogByIds(String ids) {
        return imHadoopUploadLogMapper.deleteImHadoopUploadLogByIds(Convert.toStrArray(ids));
    }

}
