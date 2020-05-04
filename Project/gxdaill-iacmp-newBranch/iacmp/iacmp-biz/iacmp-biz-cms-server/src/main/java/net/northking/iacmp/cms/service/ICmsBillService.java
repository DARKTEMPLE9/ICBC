package net.northking.iacmp.cms.service;


import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.CmsModel;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.vo.cms.CmsBillVO;
import net.northking.iacmp.common.bean.vo.cms.CmsFileImageVO;
import net.northking.iacmp.core.domain.Ztree;

import java.util.List;
import java.util.Map;

/**
 * 分类 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface ICmsBillService {
    /**
     * 查询分类信息
     *
     * @param id 分类ID
     * @return 分类信息
     */
    CmsBill selectCmsBillById(Long id);

    /**
     * 查询子分类id
     *
     * @param parentId
     */
    List<String> selectIdByParentId(Long parentId);

    /**
     * 查询分类列表
     *
     * @param cmsBill 分类信息
     * @return 分类集合
     */
    List<CmsBill> selectCmsBillList(CmsBill cmsBill, Long userId);

    /**
     * 新增分类
     *
     * @param cmsBill 分类信息
     * @return 结果
     */
    int insertCmsBill(CmsBill cmsBill);

    /**
     * 修改分类
     *
     * @param cmsBill 分类信息
     * @return 结果
     */
    int updateCmsBill(CmsBill cmsBill);

    /**
     * 修改子分类
     */
    int updateSubBillDisplay(CmsBill cmsBill);

    /**
     * 删除分类信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsBillByIds(String ids);


    /**
     * 加载所有分类列表树
     *
     * @param role
     * @param userId
     * @return
     */
    List<Ztree> roleBillTreeData(CmsRole role, Long userId);

    /**
     * 查询分类集合
     *
     * @return 所有分类信息
     */
    List<CmsBill> selectBillAll(Long userId);

    /**
     * 加载模型分类列表树
     *
     * @param model
     * @param userId
     * @return
     */
    List<Ztree> modelBillTreeData(CmsModel model, Long userId);

    /**
     * 通过父分类Id获取子分类数
     *
     * @param billId
     * @return
     */
    int selectCountBillByParentId(Long billId);

    /**
     * 通过分类Id查找分类角色关联记录数
     *
     * @param billId
     * @return
     */
    int selectCountRoleBillByBillId(Long billId);

    /**
     * 通过分类Id删除分类
     *
     * @param billId
     * @return
     */
    int deleteCmsBillById(Long billId);

    /**
     * 查询集合Vo
     *
     * @return 所有分类信息
     */
    List<CmsBillVO> selectBillVo(Long userId);


    CmsBill selectCmsBillByCode(String billCode);

    /**
     * 核对分类名称是否唯一
     *
     * @param cmsBill
     * @return
     */
    String checkBillNameUnique(CmsBill cmsBill);

    /**
     * 核对分类编码是否唯一
     *
     * @param cmsBill
     * @return
     */
    String checkBillCodeUnique(CmsBill cmsBill);


    List<String> selectBillIdOfPairing();

    List<Long> selectBillIdOfUpload();


    List<Long> selectBillIdOfHistory();

    int updateManualUpload(List<String> billIds, String targetManual);

    /**
     * 通过角色ID查找分类集合
     *
     * @param roleId
     * @return
     */
    List<CmsBill> selectCmsBillsByRoleId(Long roleId);

    List<CmsFileImageVO> selectFileImageOfBill(Long billId, Long batchId);

    List<CmsFileImageVO> selectFileByBatchId(Long batchId);

    /**
     * 通过分类ID查找模型分类表
     *
     * @param billId
     * @return
     */
    int selectCountModelBillByBillId(Long billId);
}
