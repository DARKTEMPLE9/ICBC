package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImAcsLog;
import net.northking.iacmp.imp.mapper.ImAcsLogMapper;
import net.northking.iacmp.imp.service.IImAcsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 日志 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImAcsLogServiceImpl implements IImAcsLogService {
    @Autowired
    private ImAcsLogMapper imAcsLogMapper;

    /**
     * 查询日志信息
     *
     * @param id 日志ID
     * @return 日志信息
     */
    @Override
    public HashMap imAcsLogById(String id) {
        return imAcsLogMapper.imAcsLogById(id);
    }

    /**
     * 查询日志列表
     *
     * @param imAcsLog 日志信息
     * @return 日志集合
     */
    @Override
    public List<ImAcsLog> selectImAcsLogList(HashMap map) {
        return imAcsLogMapper.selectImAcsLogList(map);
    }

    @Override
    public Integer selectAcsLogCount(HashMap map) {
        return imAcsLogMapper.selectAcsLogCount(map);
    }

    /**
     * 新增日志
     *
     * @param imAcsLog 日志信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImAcsLog(ImAcsLog imAcsLog) {
        return imAcsLogMapper.insertImAcsLog(imAcsLog);
    }

    /**
     * 修改日志
     *
     * @param imAcsLog 日志信息
     * @return 结果
     */
    @Override
    public int updateImAcsLog(ImAcsLog imAcsLog) {
        return imAcsLogMapper.updateImAcsLog(imAcsLog);
    }

    /**
     * 删除日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImAcsLogByIds(String ids) {
        return imAcsLogMapper.deleteImAcsLogByIds(Convert.toStrArray(ids));
    }

}
