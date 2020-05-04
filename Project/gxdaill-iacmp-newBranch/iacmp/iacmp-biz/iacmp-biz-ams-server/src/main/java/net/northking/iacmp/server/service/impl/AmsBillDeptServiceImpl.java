package net.northking.iacmp.server.service.impl;

import java.util.List;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsBillDeptMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsBillDept;
import net.northking.iacmp.server.service.IAmsBillDeptService;
import net.northking.iacmp.core.text.Convert;

/**
 * 部门档案配置 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsBillDeptServiceImpl implements IAmsBillDeptService {
    @Autowired
    private AmsBillDeptMapper amsBillDeptMapper;

    /**
     * 查询部门档案配置信息
     *
     * @param id 部门档案配置ID
     * @return 部门档案配置信息
     */
    @Override
    public AmsBillDept selectAmsBillDeptById(String id) {
        return amsBillDeptMapper.selectAmsBillDeptById(id);
    }

    /**
     * 查询部门档案配置列表
     *
     * @param amsBillDept 部门档案配置信息
     * @return 部门档案配置集合
     */
    @Override
    public List<AmsBillDept> selectAmsBillDeptList(AmsBillDept amsBillDept) {
        return amsBillDeptMapper.selectAmsBillDeptList(amsBillDept);
    }

    /**
     * 新增部门档案配置
     *
     * @param amsBillDept 部门档案配置信息
     * @return 结果
     */
    @Override
    public int insertAmsBillDept(AmsBillDept amsBillDept) {
        return amsBillDeptMapper.insertAmsBillDept(amsBillDept);
    }

    /**
     * 修改部门档案配置
     *
     * @param amsBillDept 部门档案配置信息
     * @return 结果
     */
    @Override
    public int updateAmsBillDept(AmsBillDept amsBillDept) {
        return amsBillDeptMapper.updateAmsBillDept(amsBillDept);
    }

    /**
     * 删除部门档案配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsBillDeptByIds(String ids) {
        return amsBillDeptMapper.deleteAmsBillDeptByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除部门档案配置信息
     *
     * @param organCode 需要删除的数据机构ID
     * @return
     */
    @Override
    public int deleteAmsBillDeptBySysId(String organCode) {
        return amsBillDeptMapper.deleteAmsBillDeptBySysId(organCode);
    }
}
