package net.northking.iacmp.cms.service;


import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.domain.cms.PmsBatch;
import net.northking.iacmp.common.bean.vo.cms.CmsFileImageVO;
import net.northking.iacmp.common.bean.vo.cms.PmsBatchVO;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.domain.TreeNode;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysMenu;
import net.northking.iacmp.system.domain.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 影像批次 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
public interface IPmsBatchService {

    /**
     * 查询所有项目
     */
    List<PmsBatch> selectAllPmsBatch(PmsBatch pmsBatch);

    /**
     * 条件查询所有当前用户所有权限的项目
     */
    List<PmsBatch> selectPmsBatchBySysRoles(PmsBatch pmsBatch);

    /**
     * 条件查询所有当前用户所有权限的项目
     */
    List<Long> selectDeptIdsByRoleId(Long roleId);

    /**
     * 条件查询所有项目
     */
    List<PmsBatch> selectPmsBatchByCondition(PmsBatch pmsBatch);

    /**
     * 查询影像批次信息
     *
     * @param id 影像批次ID
     * @return 影像批次信息
     */
    PmsBatch selectPmsBatchById(Long id);

    /**
     * 查询影像批次列表
     *
     * @param pmsBatch 影像批次信息
     * @return 影像批次集合
     */
    List<PmsBatch> selectPmsBatchList(PmsBatch pmsBatch);

    /**
     * 新增影像批次
     *
     * @param pmsBatch 影像批次信息
     * @return 结果
     */
    int insertPmsBatch(PmsBatch pmsBatch);

    /**
     * 修改影像批次
     *
     * @param pmsBatch 影像批次信息
     * @return 结果
     */
    int updatePmsBatch(PmsBatch pmsBatch);

    /**
     * 删除影像批次信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePmsBatchByIds(String ids);

    /**
     * 通过项目的id获得结构树
     *
     * @param pmsIds
     * @return
     */
    List<TreeNode> getProTreeByIds(String[] pmsIds, List<Long> dataRoleIds);

    /**
     * 获得项目下的所有分类节点
     *
     * @param pmsId
     * @return
     */
    List<TreeNode> getProBillNode(String pmsId, List<Long> dataRoleIds);

    /**
     * 获得模型下的所有分类
     *
     * @param modelList
     * @param dataRoleIds
     * @return
     */
    List<CmsBill> getBillList(String modelList, List<Long> dataRoleIds);


    PmsBatch selectPmsBatchByOpt(String regbillglideNo);

    /**
     * 查询集合Vo
     *
     * @return 所有分类信息
     */
    List<CmsBill> selectBillVo(String pmsbatchId, String modelId, String billId, Boolean billFlag, List<Long> dataRoleIds);

    /**
     * 通过项目id，模型id，分类id查询文件或者影像
     *
     * @return 所有文件或影像
     */
    List<CmsFileImageVO> selectFileImage(String pmsbatchId, String modelId, String billId);

    List<String> selectAllPmsId(PmsBatch pmsBatch);

    /**
     * 通过条件查询项目id集合
     *
     * @return 所有符合条件的项目的id
     */
    List<String> selectPmsIdList(PmsBatch pmsBatch);

    /**
     * 通过条件查询项目id集合
     *
     * @return 所有符合条件的项目的id
     */
    List<String> selectPmsIdListByRole(PmsBatch pmsBatch);

    /**
     * 通过文件编号查询文件集合
     *
     * @param names
     * @return
     */
    List<CmsFileImageVO> selectFileImageHistory(Long batchId, Integer billId, String[] names);


    /**
     * 创建项目目录树
     *
     * @return
     */
    List<Ztree> selectAllPmsBatchToTree(PmsBatch pmsBatch);

    /**
     * 对象转项目树
     *
     * @param pmsBatchList    项目列表
     * @param roleProjectList 角色已存在项目列表
     * @return 树结构列表
     */
    List<Ztree> initZtree(List<PmsBatch> pmsBatchList, List<String> roleProjectList);

    /**
     * 根据角色ID查询项目（数据权限）
     *
     * @param role 角色对象
     * @return 项目列表（数据权限）
     */
    List<Ztree> roleProjectTreeData(CmsRole role);

    /**
     * 将文件更新成目标分类文件
     *
     * @param batchId
     * @param targetBillId
     * @param fileImageNames
     * @return
     */
    int updateFileImageBill(Long batchId, Integer[] billIds, String[] fileImageNames, Integer targetBillId, String trg);

    PmsBatch selectPmsBatchByBatchId(Long batchId);

    /**
     * 获得全部分类(除叶子节点)
     */
    List<Ztree> getAllCmsBill();

    CmsBill selectNewFileImage(Long batchId, Long billId);

    /**
     * 查询指定了监控数量的分类ID
     *
     * @param pmsBatchId
     * @return
     */
    List<Long> selectBillIdByPmsId(Long pmsBatchId);

    /**
     * 查询分类的监控数量
     *
     * @param pmsBatchId
     * @param billId
     */
    Map<String, Integer> selectBillMonitorNum(Long pmsBatchId, Long billId);

    /**
     * 修改分类的监控数量
     *
     * @param pmsBatchId
     * @param billId
     */
    void updateBillMonitorNum(Long pmsBatchId, Long billId, Integer fileNum);

    /**
     * 插入分类的监控数量
     *
     * @param pmsBatchId
     * @param billId
     */
    void insertBillMonitorNum(Long pmsBatchId, Long billId, Integer fileNum);

    /**
     * 校验项目名唯一性
     *
     * @param pmsBatch
     * @return
     */
    String checkProjectNameUnique(PmsBatch pmsBatch);

    /**
     * 校验项目编号唯一性
     *
     * @param pmsBatch
     * @return
     */
    String checkOperationCodeUnique(PmsBatch pmsBatch);

    /**
     * 校验预算编号唯一性
     *
     * @param pmsBatch
     * @return
     */
    String checkBudgetIdUnique(PmsBatch pmsBatch);

    /**
     * 查询项目
     *
     * @param pmsBatch
     * @return
     */
    List<PmsBatchVO> selectPmsBatchVOBySysRoles(PmsBatch pmsBatch);

    CmsBill getButtonOfBill(CmsBill cmsBill, List<SysRole> roles, Map<Long, List<SysMenu>> roleMap,
                            PmsBatch pmsBatch, SysDept sysDept, List<String> auxiliaryDepts, Map<Long, List<Long>> roleProMap);

    /**
     * 查询角色自定义勾选的项目
     *
     * @param roleId
     * @return
     */
    List<Long> selectPIdsByDataRole(Long roleId);

}
