package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.SmOrganMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.SmOrgan;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.ISmOrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmOrganServiceImpl implements ISmOrganService {
    @Autowired
    private SmOrganMapper smOrganMapper;

    /**
     * 查询机构信息
     *
     * @param id 机构ID
     * @return 机构信息
     */
    @Override
    public SmOrgan selectSmOrganById(String id) {
        return smOrganMapper.selectSmOrganById(id);
    }

    /**
     * 查询机构列表
     *
     * @param smOrgan 机构信息
     * @return 机构集合
     */
    @Override
    public List<SmOrgan> selectSmOrganList(SmOrgan smOrgan) {
        return smOrganMapper.selectSmOrganList(smOrgan);
    }

    /**
     * 新增机构
     *
     * @param smOrgan 机构信息
     * @return 结果
     */
    @Override
    public int insertSmOrgan(SmOrgan smOrgan) {
        return smOrganMapper.insertSmOrgan(smOrgan);
    }

    /**
     * 修改机构
     *
     * @param smOrgan 机构信息
     * @return 结果
     */
    @Override
    public int updateSmOrgan(SmOrgan smOrgan) {
        return smOrganMapper.updateSmOrgan(smOrgan);
    }

    /**
     * 删除机构对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmOrganByIds(String ids) {
        return smOrganMapper.deleteSmOrganByIds(Convert.toStrArray(ids));
    }

}
