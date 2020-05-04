package net.northking.iacmp.cms.service.impl;


import iacmp.biz.common.dao.mapper.cms.CmsModelBillMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsModelBillService;
import net.northking.iacmp.common.bean.domain.cms.CmsModelBill;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模型与分类关联 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsModelBillServiceImpl implements ICmsModelBillService {
    @Autowired
    private CmsModelBillMapper cmsModelBillMapper;

    /**
     * 查询模型与分类关联信息
     *
     * @param modelId 模型与分类关联ID
     * @return 模型与分类关联信息
     */
    @Override
    public CmsModelBill selectCmsModelBillById(Integer modelId) {
        return cmsModelBillMapper.selectCmsModelBillById(modelId);
    }

    /**
     * 查询模型与分类关联列表
     *
     * @param cmsModelBill 模型与分类关联信息
     * @return 模型与分类关联集合
     */
    @Override
    public List<CmsModelBill> selectCmsModelBillList(CmsModelBill cmsModelBill) {
        return cmsModelBillMapper.selectCmsModelBillList(cmsModelBill);
    }

    /**
     * 新增模型与分类关联
     *
     * @param cmsModelBill 模型与分类关联信息
     * @return 结果
     */
    @Override
    public int insertCmsModelBill(CmsModelBill cmsModelBill) {
        return cmsModelBillMapper.insertCmsModelBill(cmsModelBill);
    }

    /**
     * 修改模型与分类关联
     *
     * @param cmsModelBill 模型与分类关联信息
     * @return 结果
     */
    @Override
    public int updateCmsModelBill(CmsModelBill cmsModelBill) {
        return cmsModelBillMapper.updateCmsModelBill(cmsModelBill);
    }

    /**
     * 删除模型与分类关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsModelBillByIds(String ids) {
        return cmsModelBillMapper.deleteCmsModelBillByIds(Convert.toStrArray(ids));
    }

}
