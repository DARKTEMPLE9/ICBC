package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.AmsPeriodMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsPeriod;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.server.service.IAmsPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

import java.util.List;
import java.util.UUID;

/**
 * 档案期限 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsPeriodServiceImpl implements IAmsPeriodService {
    @Autowired
    private AmsPeriodMapper amsPeriodMapper;

    /**
     * 查询档案期限信息
     *
     * @param id 档案期限ID
     * @return 档案期限信息
     */
    @Override
    public AmsPeriod selectAmsPeriodById(String id) {
        return amsPeriodMapper.selectAmsPeriodById(id);
    }

    /**
     * 查询档案期限列表
     *
     * @param amsPeriod 档案期限信息
     * @return 档案期限集合
     */
    @Override
    public List<AmsPeriod> selectAmsPeriodList(AmsPeriod amsPeriod) {
        return amsPeriodMapper.selectAmsPeriodList(amsPeriod);
    }

    /**
     * 新增档案期限
     *
     * @param amsPeriod 档案期限信息
     * @return 结果
     */
    @Override
    public int insertAmsPeriod(AmsPeriod amsPeriod) {
        //添加档案参数主键
        if (amsPeriod.getId() == null || amsPeriod.getId().equals("")) {
            String id = UUID.randomUUID().toString().replace("-", "");
            amsPeriod.setId(id);
        }
        return amsPeriodMapper.insertAmsPeriod(amsPeriod);
    }

    /**
     * 修改档案期限
     *
     * @param amsPeriod 档案期限信息
     * @return 结果
     */
    @Override
    public int updateAmsPeriod(AmsPeriod amsPeriod) {
        return amsPeriodMapper.updateAmsPeriod(amsPeriod);
    }

    /**
     * 删除档案期限对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsPeriodByIds(String ids) {
        return amsPeriodMapper.deleteAmsPeriodByIds(Convert.toStrArray(ids));
    }

}
