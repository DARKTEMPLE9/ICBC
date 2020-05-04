package net.northking.iacmp.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.ImAcsLogMapper;
import net.northking.iacmp.common.bean.domain.ams.ImAcsLog;
import net.northking.iacmp.server.service.IImAcsLogService;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

/**
 * 接入日志 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImAcsLogServiceImpl implements IImAcsLogService {
    @Autowired
    private ImAcsLogMapper imAcsLogMapper;

    /**
     * 查询接入日志信息
     *
     * @param id 接入日志ID
     * @return 接入日志信息
     */
    @Override
    public ImAcsLog selectImAcsLogById(String id) {
        return imAcsLogMapper.selectImAcsLogById(id);
    }

    /**
     * 查询接入日志列表
     *
     * @param imAcsLog 接入日志信息
     * @return 接入日志集合
     */
    @Override
    public List<ImAcsLog> selectImAcsLogList(ImAcsLog imAcsLog) {
        return imAcsLogMapper.selectImAcsLogList(imAcsLog);
    }

    /**
     * 新增接入日志
     *
     * @param imAcsLog 接入日志信息
     * @return 结果
     */
    @Override
    public int insertImAcsLog(ImAcsLog imAcsLog) {
        return imAcsLogMapper.insertImAcsLog(imAcsLog);
    }

    /**
     * 修改接入日志
     *
     * @param imAcsLog 接入日志信息
     * @return 结果
     */
    @Override
    public int updateImAcsLog(ImAcsLog imAcsLog) {
        return imAcsLogMapper.updateImAcsLog(imAcsLog);
    }

    /**
     * 删除接入日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImAcsLogByIds(String ids) {
        return imAcsLogMapper.deleteImAcsLogByIds(Convert.toStrArray(ids));
    }

}
