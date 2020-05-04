package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmUserOrgan;
import net.northking.iacmp.imp.mapper.SmUserOrganMapper;
import net.northking.iacmp.imp.service.ISmUserOrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户机构关联 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmUserOrganServiceImpl implements ISmUserOrganService {
    @Autowired
    private SmUserOrganMapper smUserOrganMapper;

    /**
     * 查询用户机构关联信息
     *
     * @param id 用户机构关联ID
     * @return 用户机构关联信息
     */
    @Override
    public SmUserOrgan selectSmUserOrganById(String id) {
        return smUserOrganMapper.selectSmUserOrganById(id);
    }

    /**
     * 查询用户机构关联列表
     *
     * @param smUserOrgan 用户机构关联信息
     * @return 用户机构关联集合
     */
    @Override
    public List<SmUserOrgan> selectSmUserOrganList(SmUserOrgan smUserOrgan) {
        return smUserOrganMapper.selectSmUserOrganList(smUserOrgan);
    }

    /**
     * 新增用户机构关联
     *
     * @param smUserOrgan 用户机构关联信息
     * @return 结果
     */
    @Override
    public int insertSmUserOrgan(SmUserOrgan smUserOrgan) {
        return smUserOrganMapper.insertSmUserOrgan(smUserOrgan);
    }

    /**
     * 修改用户机构关联
     *
     * @param smUserOrgan 用户机构关联信息
     * @return 结果
     */
    @Override
    public int updateSmUserOrgan(SmUserOrgan smUserOrgan) {
        return smUserOrganMapper.updateSmUserOrgan(smUserOrgan);
    }

    /**
     * 删除用户机构关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmUserOrganByIds(String ids) {
        return smUserOrganMapper.deleteSmUserOrganByIds(Convert.toStrArray(ids));
    }

}
