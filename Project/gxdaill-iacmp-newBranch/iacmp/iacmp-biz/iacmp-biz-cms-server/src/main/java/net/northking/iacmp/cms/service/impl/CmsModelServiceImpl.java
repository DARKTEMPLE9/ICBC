package net.northking.iacmp.cms.service.impl;


import iacmp.biz.common.dao.mapper.cms.CmsModelBillMapper;
import iacmp.biz.common.dao.mapper.cms.CmsModelMapper;
import net.northking.iacmp.annotation.DataScope;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsModelService;
import net.northking.iacmp.cms.service.IPmsBatchService;
import net.northking.iacmp.common.bean.domain.cms.CmsModel;
import net.northking.iacmp.common.bean.domain.cms.CmsModelBill;
import net.northking.iacmp.common.bean.domain.cms.PmsBatch;
import net.northking.iacmp.common.bean.dto.cms.CmsModelBillDTO;
import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模型 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsModelServiceImpl implements ICmsModelService {

    @Autowired
    private CmsModelMapper cmsModelMapper;

    @Autowired
    private CmsModelBillMapper cmsModelBillMapper;

    @Autowired
    private IPmsBatchService pmsBatchService;

    /**
     * 查询模型信息
     *
     * @param id 模型ID
     * @return 模型信息
     */
    @Override
    public CmsModel selectCmsModelById(Long id) {
        return cmsModelMapper.selectCmsModelById(id);
    }

    @Override
    public List<CmsModelBillDTO> selectCmsModelDTO(Long id) {
        return cmsModelMapper.selectCmsModelDTO(id);
    }

    /**
     * 查询模型列表
     *
     * @param cmsModel 模型信息
     * @return 模型集合
     */
    @Override
    @DataScope()
    public List<CmsModel> selectCmsModelList(CmsModel cmsModel) {
        return cmsModelMapper.selectCmsModelList(cmsModel);
    }

    /**
     * 新增模型
     *
     * @param cmsModel 模型信息
     * @return 结果
     */
    @Override
    public int insertCmsModel(CmsModel cmsModel) {
        cmsModelMapper.insertCmsModel(cmsModel);
        return insertCmsBills(cmsModel);
    }

    /**
     * 保存分类信息
     *
     * @param cmsModel
     * @return
     */
    public int insertCmsBills(CmsModel cmsModel) {
        int rows = 1;

        List<CmsModelBill> cmsModelBills = new ArrayList<>();
        Long[] billIds = cmsModel.getBills();
        if (StringUtils.isNull(billIds)) {
            return rows;
        }
        Arrays.stream(billIds).forEach(bill -> {
            CmsModelBill cmsModelBill = new CmsModelBill();
            cmsModelBill.setModelId(Integer.valueOf(cmsModel.getId().toString()));
            cmsModelBill.setBillId(bill.intValue());
            cmsModelBill.setFileNum(cmsModel.getFileNum());
            cmsModelBills.add(cmsModelBill);
        });

        if (!cmsModelBills.isEmpty()) {
            rows = cmsModelBillMapper.batchModelBill(cmsModelBills);
        }
        return rows;
    }

    /**
     * 修改模型
     *
     * @param cmsModel 模型信息
     * @return 结果
     */
    @Override
    public int updateCmsModel(CmsModel cmsModel) {
        //修改当前模型信息
        cmsModelMapper.updateCmsModel(cmsModel);
        //删除模型分类关联信息
        cmsModelBillMapper.deleteCmsModelBillByModelId(Long.valueOf(cmsModel.getId()));
        //新建模型分类关联信息
        return insertCmsBills(cmsModel);
    }

    /**
     * 删除模型对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsModelByIds(String ids) {
        //删除模型分类关联信息
        cmsModelBillMapper.deleteCmsModelBillByModelIds(Convert.toLongArray(ids));
        return cmsModelMapper.deleteCmsModelByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据模型编码查询模型信息
     */
    @Override
    public CmsModel selectCmsModelByCode(String modelCode) {
        return cmsModelMapper.selectCmsModelByCode(modelCode);
    }

    /**
     * 校验模型名称
     */
    @Override
    public String checkModelNameUnique(CmsModel model) {
        Long id = StringUtils.isNull(model.getId()) ? -1L : model.getId();
        CmsModel info = cmsModelMapper.selectCmsModelByName(model.getModelName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return UserConstants.MODEL_NAME_NOT_UNIQUE;
        }
        return UserConstants.MODEL_NAME_UNIQUE;
    }

    /**
     * 校验模型编码
     */
    public String checkModelCodeUnique(CmsModel model) {
        Long id = StringUtils.isNull(model.getId()) ? -1L : model.getId();
        CmsModel info = cmsModelMapper.selectCmsModelByCode(model.getModelCode());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return UserConstants.MODEL_CODE_NOT_UNIQUE;
        }
        return UserConstants.MODEL_CODE_UNIQUE;
    }

    /**
     * 通过模型编号查找使用项目
     *
     * @param ids
     * @return
     */
    @Override
    public int selectCountProjectByModelId(String ids) {
        List<PmsBatch> pmsBatches = pmsBatchService.selectAllPmsBatch(new PmsBatch());
        Long a = Arrays.stream(ids.split(",")).filter(s ->
                pmsBatches.stream().map(pmsBatch -> pmsBatch.getModelList()).filter(str -> str == null || !Arrays.asList(str.split(",")).contains(s)).count() > 0L
        ).count();

        return a.intValue();
    }
}
