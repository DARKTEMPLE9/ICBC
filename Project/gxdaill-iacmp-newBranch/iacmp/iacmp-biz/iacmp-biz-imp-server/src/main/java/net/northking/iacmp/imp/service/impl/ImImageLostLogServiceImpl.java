package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImImageLostLog;
import net.northking.iacmp.imp.mapper.ImImageLostLogMapper;
import net.northking.iacmp.imp.service.IImImageLostLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 影像缺失 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImImageLostLogServiceImpl implements IImImageLostLogService {
    @Autowired
    private ImImageLostLogMapper imImageLostLogMapper;

    /**
     * 查询影像缺失信息
     *
     * @param id 影像缺失ID
     * @return 影像缺失信息
     */
    @Override
    public ImImageLostLog selectImImageLostLogById(String id) {
        return imImageLostLogMapper.selectImImageLostLogById(id);
    }

    /**
     * 查询影像缺失列表
     *
     * @param imImageLostLog 影像缺失信息
     * @return 影像缺失集合
     */
    @Override
    public List<ImImageLostLog> selectImImageLostLogList(ImImageLostLog imImageLostLog) {
        return imImageLostLogMapper.selectImImageLostLogList(imImageLostLog);
    }

    /**
     * 新增影像缺失
     *
     * @param imImageLostLog 影像缺失信息
     * @return 结果
     */
    @Override
    public int insertImImageLostLog(ImImageLostLog imImageLostLog) {
        return imImageLostLogMapper.insertImImageLostLog(imImageLostLog);
    }

    /**
     * 修改影像缺失
     *
     * @param imImageLostLog 影像缺失信息
     * @return 结果
     */
    @Override
    public int updateImImageLostLog(ImImageLostLog imImageLostLog) {
        return imImageLostLogMapper.updateImImageLostLog(imImageLostLog);
    }

    /**
     * 删除影像缺失对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImImageLostLogByIds(String ids) {
        return imImageLostLogMapper.deleteImImageLostLogByIds(Convert.toStrArray(ids));
    }

}
