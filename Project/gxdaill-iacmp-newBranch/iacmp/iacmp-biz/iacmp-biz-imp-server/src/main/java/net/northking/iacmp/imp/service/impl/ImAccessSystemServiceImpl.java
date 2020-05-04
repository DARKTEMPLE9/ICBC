package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImAccessSystem;
import net.northking.iacmp.imp.mapper.ImAccessSystemMapper;
import net.northking.iacmp.imp.service.IImAccessSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 接入系统 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImAccessSystemServiceImpl implements IImAccessSystemService {
    @Autowired
    private ImAccessSystemMapper imAccessSystemMapper;

    /**
     * 查询接入系统信息
     *
     * @param id 接入系统ID
     * @return 接入系统信息
     */
    @Override
    public ImAccessSystem selectImAccessSystemById(String id) {
        return imAccessSystemMapper.selectImAccessSystemById(id);
    }

    @Override
    public HashMap querySystemById(String systemId) {
        return imAccessSystemMapper.querySystemById(systemId);
    }

    @Override
    public List<ImAccessSystem> queryBySysFlagInt(Integer sysFlagInt) {
        return imAccessSystemMapper.queryBySysFlagInt(sysFlagInt);
    }

    @Override
    public List<ImAccessSystem> queryBySysFlagInt2(HashMap map) {
        return imAccessSystemMapper.queryBySysFlagInt2(map);
    }

    /**
     * 查询接入系统列表
     *
     * @param imAccessSystem 接入系统信息
     * @return 接入系统集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImAccessSystem> selectImAccessSystemList(ImAccessSystem imAccessSystem) {
        return imAccessSystemMapper.selectImAccessSystemList(imAccessSystem);
    }

    @Override
    public List<ImAccessSystem> queryAllSystem(HashMap map) {
        return imAccessSystemMapper.queryAllSystem(map);
    }

    @Override
    public Integer selectSystemCount(HashMap map) {
        return imAccessSystemMapper.selectSystemCount(map);
    }

    /**
     * 新增接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    @Override
    public Integer insertImAccessSystem(HashMap map) {
        return imAccessSystemMapper.insertImAccessSystem(map);
    }

    /**
     * 修改接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    @Override
    public Integer updateById(HashMap map) {
        return imAccessSystemMapper.updateById(map);
    }

    /**
     * 删除接入系统对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Integer deleteImAccessSystemById(String id) {
        return imAccessSystemMapper.deleteImAccessSystemById(id);
    }

}
