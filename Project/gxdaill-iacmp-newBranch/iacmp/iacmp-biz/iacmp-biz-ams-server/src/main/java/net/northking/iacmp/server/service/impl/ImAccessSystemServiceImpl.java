package net.northking.iacmp.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.ImAccessSystemMapper;
import net.northking.iacmp.common.bean.domain.ams.ImAccessSystem;
import net.northking.iacmp.server.service.IImAccessSystemService;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

/**
 * 接入系统 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
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

    /**
     * 查询接入系统列表
     *
     * @param imAccessSystem 接入系统信息
     * @return 接入系统集合
     */
    @Override
    public List<ImAccessSystem> selectImAccessSystemList(ImAccessSystem imAccessSystem) {
        return imAccessSystemMapper.selectImAccessSystemList(imAccessSystem);
    }

    /**
     * 新增接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    @Override
    public int insertImAccessSystem(ImAccessSystem imAccessSystem) {
        return imAccessSystemMapper.insertImAccessSystem(imAccessSystem);
    }

    /**
     * 修改接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    @Override
    public int updateImAccessSystem(ImAccessSystem imAccessSystem) {
        return imAccessSystemMapper.updateImAccessSystem(imAccessSystem);
    }

    /**
     * 删除接入系统对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImAccessSystemByIds(String ids) {
        return imAccessSystemMapper.deleteImAccessSystemByIds(Convert.toStrArray(ids));
    }

}
