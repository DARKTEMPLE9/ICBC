package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.SmUserOrganMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.SmUserOrgan;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.ISmUserOrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户机构 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmUserOrganServiceImpl implements ISmUserOrganService {
    @Autowired
    private SmUserOrganMapper smUserOrganMapper;

    /**
     * 查询用户机构信息
     *
     * @param id 用户机构ID
     * @return 用户机构信息
     */
    @Override
    public SmUserOrgan selectSmUserOrganById(String id) {
        return smUserOrganMapper.selectSmUserOrganById(id);
    }

    /**
     * 查询用户机构列表
     *
     * @param smUserOrgan 用户机构信息
     * @return 用户机构集合
     */
    @Override
    public List<SmUserOrgan> selectSmUserOrganList(SmUserOrgan smUserOrgan) {
        return smUserOrganMapper.selectSmUserOrganList(smUserOrgan);
    }

    /**
     * 新增用户机构
     *
     * @param smUserOrgan 用户机构信息
     * @return 结果
     */
    @Override
    public int insertSmUserOrgan(SmUserOrgan smUserOrgan) {
        return smUserOrganMapper.insertSmUserOrgan(smUserOrgan);
    }

    /**
     * 修改用户机构
     *
     * @param smUserOrgan 用户机构信息
     * @return 结果
     */
    @Override
    public int updateSmUserOrgan(SmUserOrgan smUserOrgan) {
        return smUserOrganMapper.updateSmUserOrgan(smUserOrgan);
    }

    /**
     * 删除用户机构对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmUserOrganByIds(String ids) {
        return smUserOrganMapper.deleteSmUserOrganByIds(Convert.toStrArray(ids));
    }

}
