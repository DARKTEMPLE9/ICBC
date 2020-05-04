package net.northking.iacmp.cms.service.impl;


import iacmp.biz.common.dao.mapper.cms.CmsRoleBillMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsRoleBillService;
import net.northking.iacmp.common.bean.domain.cms.CmsRoleBill;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色与分类关联 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsRoleBillServiceImpl implements ICmsRoleBillService {
    @Autowired
    private CmsRoleBillMapper cmsRoleBillMapper;

    /**
     * 查询角色与分类关联信息
     *
     * @param roleId 角色与分类关联ID
     * @return 角色与分类关联信息
     */
    @Override
    public CmsRoleBill selectCmsRoleBillById(Long roleId) {
        return cmsRoleBillMapper.selectCmsRoleBillById(roleId);
    }

    /**
     * 查询角色与分类关联列表
     *
     * @param cmsRoleBill 角色与分类关联信息
     * @return 角色与分类关联集合
     */
    @Override
    public List<CmsRoleBill> selectCmsRoleBillList(CmsRoleBill cmsRoleBill) {
        return cmsRoleBillMapper.selectCmsRoleBillList(cmsRoleBill);
    }

    /**
     * 新增角色与分类关联
     *
     * @param cmsRoleBill 角色与分类关联信息
     * @return 结果
     */
    @Override
    public int insertCmsRoleBill(CmsRoleBill cmsRoleBill) {
        return cmsRoleBillMapper.insertCmsRoleBill(cmsRoleBill);
    }

    /**
     * 修改角色与分类关联
     *
     * @param cmsRoleBill 角色与分类关联信息
     * @return 结果
     */
    @Override
    public int updateCmsRoleBill(CmsRoleBill cmsRoleBill) {
        return cmsRoleBillMapper.updateCmsRoleBill(cmsRoleBill);
    }

    /**
     * 删除角色与分类关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsRoleBillByIds(String ids) {
        return cmsRoleBillMapper.deleteCmsRoleBillByIds(Convert.toStrArray(ids));
    }

}
