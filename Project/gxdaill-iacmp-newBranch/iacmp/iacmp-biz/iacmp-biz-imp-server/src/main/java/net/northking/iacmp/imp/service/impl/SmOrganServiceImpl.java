package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmOrgan;
import net.northking.iacmp.imp.mapper.SmOrganMapper;
import net.northking.iacmp.imp.service.ISmOrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

/**
 * 部门机构 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmOrganServiceImpl implements ISmOrganService {
    @Autowired
    private SmOrganMapper smOrganMapper;

    /**
     * 查询部门机构信息
     *
     * @param id 部门机构ID
     * @return 部门机构信息
     */
    @Override
    public SmOrgan selectSmOrganById(String id) {
        return smOrganMapper.selectSmOrganById(id);
    }

    /**
     * 查询部门机构列表
     *
     * @param smOrgan 部门机构信息
     * @return 部门机构集合
     */
    @Override
    public List<SmOrgan> selectSmOrganList(HashMap smOrgan) {
        return smOrganMapper.selectSmOrganList(smOrgan);
    }

    @Override
    public Integer count(HashMap map) {
        return smOrganMapper.count(map);
    }

    @Override
    public List<SmOrgan> findByOrganCode(String code) {
        return smOrganMapper.findByOrganCode(code);
    }

    /**
     * 新增部门机构
     *
     * @param smOrgan 部门机构信息
     * @return 结果
     */
    @Override
    public Integer insertSmOrgan(SmOrgan smOrgan) {
        return smOrganMapper.insertSmOrgan(smOrgan);
    }

    /**
     * 修改部门机构
     *
     * @param smOrgan 部门机构信息
     * @return 结果
     */
    @Override
    public int updateSmOrgan(SmOrgan smOrgan) {
        return smOrganMapper.updateSmOrgan(smOrgan);
    }

    /**
     * 删除部门机构对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmOrganByIds(String ids) {
        return smOrganMapper.deleteSmOrganByIds(Convert.toStrArray(ids));
    }

}
