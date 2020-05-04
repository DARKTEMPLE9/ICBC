package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.AmsArcRegMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.AmsArcReg;
import net.northking.iacmp.common.bean.vo.ams.AmsArcRegVO;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IAmsArcRegService;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 档案 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsArcRegServiceImpl implements IAmsArcRegService {
    @Autowired
    private AmsArcRegMapper amsArcRegMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询档案信息
     *
     * @param id 档案ID
     * @return 档案信息
     */
    @Override
    public AmsArcReg selectAmsArcRegById(String id) {
        return amsArcRegMapper.selectAmsArcRegById(id);
    }

    /**
     * 查询档案列表
     *
     * @param amsArcReg 档案信息
     * @param userId
     * @return 档案集合
     */
    @Override
    public List<AmsArcReg> selectAmsArcRegList(AmsArcRegVO amsArcReg, Long userId) {
        if (SysUser.isAdmin(userId)) {
            return amsArcRegMapper.selectAmsArcRegList(amsArcReg);
        } else {
            return amsArcRegMapper.selectAmsArcRegListByDeptId(amsArcReg);
        }
    }

    /**
     * 查询档案列表（包含辅部门）
     *
     * @param amsArcReg 档案信息
     * @param userId
     * @return 档案集合
     */
    @Override
    public List<AmsArcReg> selectAmsArcList(AmsArcRegVO amsArcReg, Long userId, List<String> deptList) {
        if (SysUser.isAdmin(userId)) {
            return amsArcRegMapper.selectAmsArcRegList(amsArcReg);
        } else {
            return amsArcRegMapper.selectAmsArcRegListByDeptIds(amsArcReg, deptList);
        }
    }

    /**
     * 新增档案
     *
     * @param amsArcReg 档案信息
     * @return 结果
     */
    @Override
    public int insertAmsArcReg(AmsArcReg amsArcReg) {
        return amsArcRegMapper.insertAmsArcReg(amsArcReg);
    }

    /**
     * 修改档案
     *
     * @param amsArcReg 档案信息
     * @return 结果
     */
    @Override
    public int updateAmsArcReg(AmsArcReg amsArcReg) {
        return amsArcRegMapper.updateAmsArcReg(amsArcReg);
    }

    /**
     * 删除档案对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsArcRegByIds(String ids) {
        return amsArcRegMapper.deleteAmsArcRegByIds(Convert.toStrArray(ids));
    }

    /**
     * 批量查找档案信息
     *
     * @param ids 需要获取到档案的数据ID
     * @return
     */
    @Override
    public List<AmsArcReg> selectAmsArcRegByIds(String ids) {
        return amsArcRegMapper.selectAmsArcRegByIds(Convert.toStrArray(ids));
    }

}
