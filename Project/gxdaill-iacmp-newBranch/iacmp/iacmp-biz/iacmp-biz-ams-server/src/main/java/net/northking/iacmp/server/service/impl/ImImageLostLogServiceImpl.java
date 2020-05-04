package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImImageLostLogMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImImageLostLog;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImImageLostLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 影像缺失记录 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImImageLostLogServiceImpl implements IImImageLostLogService {
    @Autowired
    private ImImageLostLogMapper imImageLostLogMapper;

    /**
     * 查询影像缺失记录信息
     *
     * @param id 影像缺失记录ID
     * @return 影像缺失记录信息
     */
    @Override
    public ImImageLostLog selectImImageLostLogById(String id) {
        return imImageLostLogMapper.selectImImageLostLogById(id);
    }

    /**
     * 查询影像缺失记录列表
     *
     * @param imImageLostLog 影像缺失记录信息
     * @return 影像缺失记录集合
     */
    @Override
    public List<ImImageLostLog> selectImImageLostLogList(ImImageLostLog imImageLostLog) {
        return imImageLostLogMapper.selectImImageLostLogList(imImageLostLog);
    }

    /**
     * 新增影像缺失记录
     *
     * @param imImageLostLog 影像缺失记录信息
     * @return 结果
     */
    @Override
    public int insertImImageLostLog(ImImageLostLog imImageLostLog) {
        return imImageLostLogMapper.insertImImageLostLog(imImageLostLog);
    }

    /**
     * 修改影像缺失记录
     *
     * @param imImageLostLog 影像缺失记录信息
     * @return 结果
     */
    @Override
    public int updateImImageLostLog(ImImageLostLog imImageLostLog) {
        return imImageLostLogMapper.updateImImageLostLog(imImageLostLog);
    }

    /**
     * 删除影像缺失记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImImageLostLogByIds(String ids) {
        return imImageLostLogMapper.deleteImImageLostLogByIds(Convert.toStrArray(ids));
    }

}
