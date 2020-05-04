package net.northking.iacmp.cms.service.impl;

import iacmp.biz.common.dao.mapper.cms.CmsBillMapper;
import iacmp.biz.common.dao.mapper.cms.CmsModelBillMapper;
import iacmp.biz.common.dao.mapper.cms.CmsRoleBillMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsBillService;
import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.CmsModel;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.vo.cms.CmsBillVO;
import net.northking.iacmp.common.bean.vo.cms.CmsFileImageVO;
import net.northking.iacmp.constant.CmsBillContants;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 分类 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsBillServiceImpl implements ICmsBillService {
    @Autowired
    private CmsBillMapper cmsBillMapper;

    @Autowired
    private CmsRoleBillMapper cmsRoleBillMapper;

    @Autowired
    private CmsModelBillMapper cmsModelBillMapper;

    /**
     * 查询分类信息
     *
     * @param id 分类ID
     * @return 分类信息
     */
    @Override
    public CmsBill selectCmsBillById(Long id) {
        return cmsBillMapper.selectCmsBillById(id);
    }

    /**
     * 查询子分类id
     *
     * @param parentId
     */
    @Override
    public List<String> selectIdByParentId(Long parentId) {
        return cmsBillMapper.selectIdByParentId(parentId);
    }

    /**
     * 查询分类列表
     *
     * @param cmsBill 分类信息
     * @return 分类集合
     */
    @Override
    public List<CmsBill> selectCmsBillList(CmsBill cmsBill, Long userId) {
        List<CmsBill> billList = null;
        if (SysUser.isAdmin(userId)) {
            billList = cmsBillMapper.selectCmsBillList(cmsBill);
        } else {
            cmsBill.setUserId(userId);
            billList = cmsBillMapper.selectBillListByUserId(cmsBill);
        }
        return billList;
    }

    /**
     * 新增分类
     *
     * @param cmsBill 分类信息
     * @return 结果
     */
    @Override
    public int insertCmsBill(CmsBill cmsBill) {
        return cmsBillMapper.insertCmsBill(cmsBill);
    }

    /**
     * 修改分类
     *
     * @param cmsBill 分类信息
     * @return 结果
     */
    @Override
    public int updateCmsBill(CmsBill cmsBill) {
        return cmsBillMapper.updateCmsBill(cmsBill);
    }

    @Override
    public int updateSubBillDisplay(CmsBill cmsBill) {
        return cmsBillMapper.updateSubBillDisplay(cmsBill);
    }

    /**
     * 删除分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsBillByIds(String ids) {
        return cmsBillMapper.deleteCmsBillByIds(Convert.toStrArray(ids));
    }

    /**
     * 对象转分类树
     *
     * @param billList
     * @param strings
     * @return
     */
    public List<Ztree> initZtree(List<CmsBill> billList, List<String> strings) {
        List<Ztree> list = new ArrayList<>();
        if (StringUtils.isNull(billList) || billList.size() == 0) {
            return list;
        }
        boolean isCheck = StringUtils.isNotEmpty(strings);
        billList.stream().forEach(cmsBill -> {
            Ztree ztree = new Ztree();
            ztree.setId(Long.valueOf(cmsBill.getId()));
            ztree.setpId(cmsBill.getParentId());
            ztree.setName(cmsBill.getBillName());
            ztree.setTitle(cmsBill.getBillName());
            if (isCheck) {
                ztree.setChecked(strings.contains(cmsBill.getId() + cmsBill.getBillName()));
            }
            list.add(ztree);
        });
        return list;
    }

    /**
     * 加载角色分类列表树
     *
     * @param role
     * @param userId
     * @return
     */
    @Override
    public List<Ztree> roleBillTreeData(CmsRole role, Long userId) {
        Long roleId = role.getId();
        List<Ztree> ztrees;
        List<CmsBill> billList = selectBillAll(userId);
        if (StringUtils.isNotNull(roleId)) {
            List<String> roleBillList = cmsBillMapper.selectBillTree(roleId);
            ztrees = initZtree(billList, roleBillList);
        } else {
            ztrees = initZtree(billList, null);
        }
        return ztrees;
    }

    /**
     * 查询分类集合
     *
     * @return 所有分类信息
     */
    @Override
    public List<CmsBill> selectBillAll(Long userId) {
        List<CmsBill> billList = null;
        if (SysUser.isAdmin(userId)) {
            billList = cmsBillMapper.selectBillAll();
        } else {
            billList = cmsBillMapper.selectBillAllByUserId(userId);
        }
        return billList;
    }

    /**
     * 加载模型分类列表树
     *
     * @param model
     * @param userId
     * @return
     */
    public List<Ztree> modelBillTreeData(CmsModel model, Long userId) {
        Long modelId = model.getId();
        List<Ztree> ztrees;
        List<CmsBill> billList = selectBillAll(userId);
        if (StringUtils.isNotNull(modelId)) {

            List<String> modelBillList = cmsBillMapper.selectModelBillTree(Long.valueOf(modelId));
            ztrees = initZtree(billList, modelBillList);
        } else {
            ztrees = initZtree(billList, null);
        }
        return ztrees;
    }

    /**
     * 通过父分类Id获取子分类数
     *
     * @param billId
     * @return
     */
    @Override
    public int selectCountBillByParentId(Long billId) {

        return cmsBillMapper.selectCountBillByParentId(billId);
    }

    /**
     * 通过分类Id查找分类角色关联记录数
     *
     * @param billId
     * @return
     */
    @Override
    public int selectCountRoleBillByBillId(Long billId) {
        return cmsRoleBillMapper.selectCountRoleBillByBillId(billId);
    }

    /**
     * 通过分类Id删除分类
     *
     * @param billId
     * @return
     */
    @Override
    public int deleteCmsBillById(Long billId) {
        if (billId == null) {
            throw new NullPointerException("billId 为空");
        }
        return cmsBillMapper.deleteCmsBillById(billId);
    }

    /**
     * 查询分类集合
     *
     * @return 所有分类信息
     */
    @Override
    public List<CmsBillVO> selectBillVo(Long userId) {
        List<CmsBillVO> billList = null;
        if (SysUser.isAdmin(userId)) {
            billList = cmsBillMapper.selectBillAllVo();
        } else {
            billList = cmsBillMapper.selectBillAllVoByUserId(userId);
        }

        return billList;
    }

    /**
     * 通过分类编号查找分类
     *
     * @param billCode
     * @return
     */
    @Override
    public CmsBill selectCmsBillByCode(String billCode) {
        return cmsBillMapper.selectCmsBillByCode(billCode);
    }

    /**
     * 核对分类名称是否唯一
     *
     * @param cmsBill
     * @return
     */
    @Override
    public String checkBillNameUnique(CmsBill cmsBill) {
        Long id = StringUtils.isNull(cmsBill.getId()) ? -1L : cmsBill.getId();
        CmsBill info = cmsBillMapper.checkRoleNameUnique(cmsBill.getBillName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return CmsBillContants.BILL_NAME_NOT_UNIQUE;
        }
        return CmsBillContants.BILL_NAME_UNIQUE;
    }

    /**
     * 核对分类编码是否唯一
     *
     * @param cmsBill
     * @return
     */
    @Override
    public String checkBillCodeUnique(CmsBill cmsBill) {
        Long id = StringUtils.isNull(cmsBill.getId()) ? -1L : cmsBill.getId();
        CmsBill info = cmsBillMapper.checkBillCodeUnique(cmsBill.getBillCode());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return CmsBillContants.BILL_CODE_NOT_UNIQUE;
        }
        return CmsBillContants.BILL_CODE_UNIQUE;
    }

    @Override
    public List<String> selectBillIdOfPairing() {
        return cmsBillMapper.selectBillIdOfPairing();
    }

    @Override
    public List<Long> selectBillIdOfUpload() {
        return cmsBillMapper.selectBillIdOfUpload();
    }

    @Override
    public List<Long> selectBillIdOfHistory() {
        return cmsBillMapper.selectBillIdOfHistory();
    }

    @Override
    public int updateManualUpload(List<String> billIds, String targetManual) {
        return cmsBillMapper.updateManualUpload(billIds, targetManual);
    }

    /**
     * 通过角色id查找分类集合
     *
     * @param roleId
     * @return
     */
    @Override
    public List<CmsBill> selectCmsBillsByRoleId(Long roleId) {
        return cmsBillMapper.selectCmsBillsByRoleId(roleId);
    }

    /**
     * 查询当前分类下的所有文件
     *
     * @param billId
     * @param batchId
     * @return
     */
    @Override
    public List<CmsFileImageVO> selectFileImageOfBill(Long billId, Long batchId) {
        return cmsBillMapper.selectFileImageOfBill(billId, batchId);
    }

    @Override
    public List<CmsFileImageVO> selectFileByBatchId(Long batchId) {
        return cmsBillMapper.selectFileByBatchId(batchId);
    }

    /**
     * 通过分类ID查找模型分类表
     *
     * @param billId
     * @return
     */
    @Override
    public int selectCountModelBillByBillId(Long billId) {
        return cmsModelBillMapper.selectCountModelBillByBillId(billId);
    }
}
