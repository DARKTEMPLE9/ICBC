package net.northking.iacmp.cms.service.impl;


import iacmp.biz.common.dao.mapper.cms.*;
import net.northking.iacmp.annotation.DataScope;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.IPmsBatchService;
import net.northking.iacmp.common.bean.domain.cms.*;
import net.northking.iacmp.common.bean.vo.cms.CmsFileImageVO;
import net.northking.iacmp.common.bean.vo.cms.PmsBatchVO;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.PmsConstants;
import net.northking.iacmp.core.domain.TreeNode;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.framework.aspectj.DataScopeAspect;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.framework.util.UploadUtil;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysMenu;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.mapper.SysDeptMapper;
import net.northking.iacmp.system.mapper.SysMenuMapper;
import net.northking.iacmp.system.mapper.SysRoleDeptMapper;
import net.northking.iacmp.system.service.ISysDeptService;
import net.northking.iacmp.utils.NumConstants;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toCollection;

/**
 * 影像批次 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class PmsBatchServiceImpl implements IPmsBatchService {

    @Autowired
    private PmsBatchMapper pmsBatchMapper;
    @Autowired
    private CmsFileMapper cmsFileMapper;
    @Autowired
    private CmsImageMapper cmsImageMapper;
    @Autowired
    private CmsBillMapper cmsBillMapper;
    @Autowired
    private CmsModelBillMapper cmsModelBillMapper;
    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<PmsBatch> selectAllPmsBatch(PmsBatch pmsBatch) {
        return pmsBatchMapper.selectAllPmsBatch(pmsBatch);
    }

    @Override
    public List<PmsBatch> selectPmsBatchBySysRoles(PmsBatch pmsBatch) {
        return pmsBatchMapper.selectPmsBatchBySysRoles(pmsBatch);
    }

    @Override
    public List<Long> selectDeptIdsByRoleId(Long roleId) {
        return sysRoleDeptMapper.selectDeptIdsByRoleId(roleId);
    }

    @Override
    public List<PmsBatch> selectPmsBatchByCondition(PmsBatch pmsBatch) {
        return pmsBatchMapper.selectPmsBatchByCondition(pmsBatch);
    }

    /**
     * 查询影像批次信息
     *
     * @param id 影像批次ID
     * @return 影像批次信息
     */
    @Override
    public PmsBatch selectPmsBatchById(Long id) {
        return pmsBatchMapper.selectPmsBatchById(id);
    }

    /**
     * 查询影像批次列表
     *
     * @param pmsBatch 影像批次信息
     * @return 影像批次集合
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<PmsBatch> selectPmsBatchList(PmsBatch pmsBatch) {
        return pmsBatchMapper.selectPmsBatchList(pmsBatch);

    }

    /**
     * 新增影像批次
     *
     * @param pmsBatch 影像批次信息
     * @return 结果
     */
    @Override
    public int insertPmsBatch(PmsBatch pmsBatch) {
        return pmsBatchMapper.insertPmsBatch(pmsBatch);
    }

    /**
     * 修改影像批次
     *
     * @param pmsBatch 影像批次信息
     * @return 结果
     */
    @Override
    public int updatePmsBatch(PmsBatch pmsBatch) {
        return pmsBatchMapper.updatePmsBatch(pmsBatch);
    }

    /**
     * 删除影像批次对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePmsBatchByIds(String ids) {
        String[] idArray = Convert.toStrArray(ids);
        for (int i = 0; i < idArray.length; i++) {
            PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchById(Long.valueOf(idArray[i]));
            String status = pmsBatch.getStatus();
            if ("1".equals(status) || "2".equals(status)) {//已结项，已废弃
                return CmsConstants.DELETE_STATUS_FAIL;
            }
            // 查看要删除的项目系下是否有文件，若有，则不能删除
            List<CmsFile> fileList = cmsFileMapper.selectFileByBatchId(pmsBatch.getBatchId());
            List<CmsImage> imageList = cmsImageMapper.selectImageByBatchId(pmsBatch.getBatchId());
            if ((fileList != null && fileList.size() > 0) || (imageList != null && imageList.size() > 0)) {
                return CmsConstants.DELETE_STATUS_ERROR;
            }
        }
        return pmsBatchMapper.deletePmsBatchByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过项目的id获得结构树
     *
     * @param pmsIds
     * @return
     */
    @Override
    public List<TreeNode> getProTreeByIds(String[] pmsIds, List<Long> dataRoleIds) {
        // 准备一个客户结构树ArrayList
        List<TreeNode> projectTree = new ArrayList<>();
        // 部门的map，用于临时存放部门节点，判断多个项目所属同一个部门
        Map<String, Object> deptMap = new HashMap<>(16);
        List<Long> roleIdParam = new ArrayList<>();
        for (Long id : dataRoleIds) {
            roleIdParam.add(id);
        }
        // 查询每个角色在分类管理自定义勾选的项目
        Map<Long, List<Long>> roleProMap = new HashMap<>(8);
        for (SysRole role : ShiroUtils.getSysUser().getRoles()) {
            List<Long> roleProjects = pmsBatchMapper.selectPIdsByDataRole(role.getRoleId());
            roleProMap.put(role.getRoleId(), roleProjects);
        }

        for (String pmsId : pmsIds) {
            if (pmsId != null && !"".equals(pmsId)) {
                dataRoleIds.clear();
                dataRoleIds.addAll(roleIdParam);
                // 项目
                PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchById(Long.valueOf(pmsId));
                if (PmsConstants.PMS_UNKNOWN_DEPT.equals(pmsBatch.getBuildDept()) ||
                        pmsBatch.getBuildDept() == null) {
                    pmsBatch.setBuildDeptName(PmsConstants.PMS_UNKNOWN_DEPTNAME);
                }
                if (pmsBatch.getProjectName() == null || "".equals(pmsBatch.getProjectName())) {
                    pmsBatch.setProjectName(PmsConstants.PMS_UNKNOWN_PROJECTNAME);
                }
                // 该项目所有修改修改过监控数的分类id
                List<Long> updateBillIds = cmsBillMapper.selectBillIdByPmsId(Long.valueOf(pmsId));

                // 过滤角色查询条件
                checkRoleParam(ShiroUtils.getSysUser(), dataRoleIds, pmsBatch, roleProMap);
                // 获得取并集后的分类列表
                List<CmsBill> billList = getBillList(pmsBatch.getModelList(), dataRoleIds);
                // 项目没有模型
                if (pmsBatch.getModelList() == null || "".equals(pmsBatch.getModelList())) {
                    pmsBatch.setModelList("0");
                }
                // 先放入该模型所属的部门和项目接节
                // 部门节点
                TreeNode deptNode;
                if (deptMap.containsKey(pmsBatch.getBuildDept())) {
                    // 已有项目所属部门
                    deptNode = (TreeNode) deptMap.get(pmsBatch.getBuildDept());
                } else {
                    // 增加部门节点并放入部门节点的map
                    deptNode = new TreeNode();
                    deptNode.setId("dept_" + pmsBatch.getBuildDept());
                    deptNode.setName(pmsBatch.getBuildDeptName());
                    deptNode.setTitle(deptNode.getName());
                    deptNode.setpId("0");
                    deptMap.put(pmsBatch.getBuildDept(), deptNode);
                    projectTree.add(deptNode);
                }

                // 项目节点
                TreeNode pmsBatchNode = new TreeNode();
                pmsBatchNode.setId(deptNode.getId() + "_" + pmsBatch.getId().toString() + "_" + pmsBatch.getModelList());
                pmsBatchNode.setpId(deptNode.getId().toString());
                pmsBatchNode.setName(pmsBatch.getProjectName() + " (" + pmsBatch.getOperationCode() + ") ");
                pmsBatchNode.setTitle(pmsBatchNode.getName());
                pmsBatchNode.setStatus(pmsBatch.getStatus());
                pmsBatchNode.setProjectManager(pmsBatch.getProjectManager());
                pmsBatchNode.setProductManager(pmsBatch.getProductManager());
                projectTree.add(pmsBatchNode);

                if (billList != null && billList.size() > 0) {
                    // 获得所有分类的Map
                    Map<Long, CmsBill> billMap = billList.stream().collect(Collectors.toMap(b -> b.getId(), b -> b));

                    for (CmsBill cmsBill : billList) {
                        if ("0".equals(cmsBill.getLeaf()) && !"noClassify".equals(cmsBill.getBillCode())) {
                            if (cmsBill.getAllPath().contains(",")) {
                                // 祖籍分类 (立项、采购、交付、结项)
                                String billId = cmsBill.getAllPath().split(",")[1];
                                CmsBill bill = billMap.get(Long.valueOf(billId));
                                // 监控数
                                if (bill.getFileNum() == null) {
                                    bill.setFileNum(0);
                                }
                                // 实际数
                                if (bill.getActualFileNum() == null) {
                                    bill.setActualFileNum(0);
                                }
                                // 查询该分类下的文件数
                                Integer num = cmsFileMapper.selectFileImageNumByCondition(pmsBatch.getBatchId(), Long.valueOf(cmsBill.getId()));
                                // 默认监控数为1
                                int monitorNum = 1;
                                if (PmsConstants.DISPLAY_HISTORY.equals(cmsBill.getDisplay())) {
                                    if (updateBillIds.contains(cmsBill.getId())) {
                                        Map<String, Integer> numMap = cmsBillMapper.selectBillMonitorNum(pmsBatch.getId(), cmsBill.getId());
                                        monitorNum = numMap != null ? numMap.get("fileNum") : monitorNum;
                                    }
                                }
                                // 如果分类已停用，不统计监控数
                                if (PmsConstants.PMS_BILL_OFF.equals(cmsBill.getStatus())) {
                                    monitorNum = 0;
                                }
                                bill.setFileNum(bill.getFileNum() + monitorNum);
                                if (num != null && num > 0) {
                                    if (bill.getActualFileNum() == null) {
                                        bill.setActualFileNum(0);
                                    }
                                    if (num >= monitorNum) {
                                        bill.setActualFileNum(bill.getActualFileNum() + 1);
                                    }
                                }
                            } else {
                                // 无祖籍节点的叶子节点
                                Integer num = cmsFileMapper.selectFileNumByCondition(pmsBatch.getBatchId(), Long.valueOf(cmsBill.getId()));
                                cmsBill.setActualFileNum(num);
                            }
                        } else {
                            cmsBill.setFileNum(0);
                            cmsBill.setActualFileNum(0);
                        }
                    }

                    // 将该模型下所有的分类节点放入projectTree
                    for (CmsBill cmsBill : billList) {
                        // 找出非叶子节点
                        if ("1".equals(cmsBill.getLeaf()) || "noClassify".equals(cmsBill.getBillCode())) {
                            TreeNode billNode = new TreeNode();
                            billNode.setId(pmsBatchNode.getId() + "_" + cmsBill.getId().toString());
                            if (0 == cmsBill.getParentId()) {
                                billNode.setpId(pmsBatchNode.getId());
                                billNode.setFileNum(billMap.get(cmsBill.getId()).getFileNum());
                                billNode.setActualFileNum(billMap.get(cmsBill.getId()).getActualFileNum());
                            } else {
                                billNode.setpId(pmsBatchNode.getId() + "_" + cmsBill.getParentId().toString());
                            }
                            billNode.setName(cmsBill.getBillName());
                            billNode.setTitle(billNode.getName());
                            projectTree.add(billNode);
                        } else if ("0".equals(cmsBill.getLeaf()) && !cmsBill.getAllPath().contains(",")) {
                            // 无祖籍节点的叶子节点
                            TreeNode node = new TreeNode();
                            node.setId(pmsBatchNode.getId() + "_" + cmsBill.getId().toString());
                            node.setpId(pmsBatchNode.getId());
                            node.setActualFileNum(cmsBill.getActualFileNum());
                            node.setFileNum(node.getActualFileNum() > 0 ? 1 : 0);
                            node.setName(cmsBill.getBillName());
                            node.setTitle(node.getName());
                            projectTree.add(node);
                        }
                    }
                }
            }
        }
        return projectTree;
    }

    // 过滤权限查询条件
    private void checkRoleParam(SysUser user, List<Long> dataRoleIds, PmsBatch pmsBatch, Map<Long, List<Long>> roleProMap) {
        // 取出当前用户的所有角色
        List<SysRole> roles = user.getRoles();
        String proDept = pmsBatch.getBuildDept();       // 项目的承建部门
        String userDept = user.getDeptId().toString();  // 用户的部门
        // 辅部门
        String auxiliaryDept = user.getAuxiliaryDept();
        List<String> auxiliaryDepts = new ArrayList<>();
        if (!"".equals(auxiliaryDept) && auxiliaryDept != null) {
            auxiliaryDepts = Arrays.asList(Convert.toStrArray(auxiliaryDept));
        }
        for (SysRole role : roles) {
            if (role.getRoleName().contains("项目经理")) {
                if (!ShiroUtils.getLoginName().equals(pmsBatch.getProjectManager())) {
                    dataRoleIds.remove(role.getRoleId());
                }
            } else if (role.getRoleName().contains("产品经理")) {
                if (!ShiroUtils.getLoginName().equals(pmsBatch.getProductManager())) {
                    dataRoleIds.remove(role.getRoleId());
                }
            } else if (DataScopeAspect.DATA_SCOPE_DEPT.equals(role.getDataScope())) {
                // 本部门
                if (proDept != null && !proDept.equals(userDept) && !auxiliaryDepts.contains(proDept)) {
                    dataRoleIds.remove(role.getRoleId());
                }
            } else if (DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD.equals(role.getDataScope())) {
                // 本部门及以下
                List<String> subDept = sysDeptMapper.selectSubDeptById(user.getDeptId());
                if (subDept == null) {
                    subDept = new ArrayList<>();
                }
                if (auxiliaryDepts.size() > 0) {
                    for (String auDept : auxiliaryDepts) {
                        // 辅部门
                        subDept.add(auDept);
                        List<String> depts = sysDeptMapper.selectSubDeptById(Long.valueOf(auDept));
                        subDept.addAll(depts);
                    }
                }
                if (proDept != null) {
                    if (!proDept.equals(userDept) && !subDept.contains(proDept)) {
                        dataRoleIds.remove(role.getRoleId());
                    }
                }
            } else if (DataScopeAspect.DATA_SCOPE_CUSTOM.equals(role.getDataScope())) {
                // 自定义数据权限
                List<Long> deptIds = sysRoleDeptMapper.selectDeptIdsByRoleId(role.getRoleId());
                if (!deptIds.contains(Long.valueOf(proDept))) {
                    dataRoleIds.remove(role.getRoleId());
                }
            } else if (DataScopeAspect.DATA_SCOPE_SELF.equals(role.getDataScope())) {
                // 仅本人
                if (!ShiroUtils.getLoginName().equals(pmsBatch.getProjectManager())) {
                    dataRoleIds.remove(role.getRoleId());
                }
            }
            // 如果项目为该角色额外勾选的项目
            List<Long> proIds = roleProMap.get(role.getRoleId());
            if (proIds != null && proIds.contains(pmsBatch.getBatchId())) {
                dataRoleIds.add(role.getRoleId());
            }
        }
    }

    @Override
    public List<TreeNode> getProBillNode(String pmsId, List<Long> dataRoleIds) {
        // 准备一个客户结构树ArrayList
        List<TreeNode> projectTree = new ArrayList<>();
        // 项目
        PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchById(Long.valueOf(pmsId));
        // dept_部门id_项目id_模型List_分类id
        String pmsNodeId = "dept_" + pmsBatch.getBuildDept() + "_" + pmsBatch.getId() + "_" + pmsBatch.getModelList() + "_";
        // 获得取并集后的分类列表
        List<CmsBill> billList = getBillList(pmsBatch.getModelList(), dataRoleIds);
        for (CmsBill cmsBill : billList) {
            if (!"noClassify".equals(cmsBill.getBillCode())) {
                TreeNode node = new TreeNode();
                node.setId(pmsNodeId + cmsBill.getId());
                node.setpId(pmsNodeId + cmsBill.getParentId());
                node.setName(cmsBill.getBillName());
                projectTree.add(node);
            }
        }
        return projectTree;
    }

    @Override
    public PmsBatch selectPmsBatchByOpt(String regbillglideNo) {
        PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchByOpt(regbillglideNo);
        return pmsBatch;
    }


    /**
     * 通过多个模型的分类取出并集
     *
     * @return
     */
    @Override
    public List<CmsBill> getBillList(String modelList, List<Long> dataRoleIds) {
        // 如果没有模型，展示所有
        if (modelList == null || "".equals(modelList)) {
            List<CmsBill> uniqueList = cmsBillMapper.selectAllCmsBills(dataRoleIds);
            if (uniqueList != null && uniqueList.size() > 0) {
                // 根据id去重
                uniqueList = uniqueList.stream().collect(collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(CmsBill::getId))), ArrayList::new));
            }
            return uniqueList;
        }
        String[] modelIds = Convert.toStrArray(modelList);
        // 查询出模型停用的分类
        List<Long> offBill = cmsBillMapper.selectBillOffOfModel(modelIds);
        List<CmsBill> newBillList = new ArrayList<>();
        for (String id : modelIds) {
            List<CmsBill> billList = null;
            if (ShiroUtils.getUserId() == 1L) {
                billList = cmsModelBillMapper.selectAllBillByCmsModelId(Long.valueOf(id));
            } else {
                billList = cmsModelBillMapper.selectBillListByCmsModelId(Long.valueOf(id), dataRoleIds);
            }
            newBillList.addAll(billList);
        }
        // 根据id去重
        newBillList = newBillList.stream().collect(collectingAndThen(
                toCollection(() -> new TreeSet<>(Comparator.comparing(CmsBill::getId))), ArrayList::new));

        for (CmsBill bill : newBillList) {
            if (offBill.contains(bill.getId())) {
                // 停用
                bill.setStatus(PmsConstants.PMS_BILL_OFF);
            }
        }
        return newBillList;
    }


    /**
     * 查找部门树
     *
     * @param pmsBatch
     * @return
     */
    @Override
    public List<Ztree> selectAllPmsBatchToTree(PmsBatch pmsBatch) {
        List<PmsBatch> pmsBatches = selectPmsBatchList(pmsBatch);
        List<Ztree> ztrees = initZtree(pmsBatches);
        return ztrees;
    }

    /**
     * 根据角色ID查询项目（数据权限）
     *
     * @param role 角色对象
     * @return 项目列表（数据权限）
     */
    @Override
    public List<Ztree> roleProjectTreeData(CmsRole role) {
        Long roleId = role.getId();
        List<Ztree> ztrees;
        List<PmsBatch> pmsBatchList = selectPmsBatchList(new PmsBatch());
        if (StringUtils.isNotNull(roleId)) {
            List<String> roleProjectList = pmsBatchMapper.selectRoleProjectTree(roleId);
            ztrees = initZtree(pmsBatchList, roleProjectList);
        } else {
            ztrees = initZtree(pmsBatchList);
        }
        return ztrees;
    }

    /**
     * 将文件更新成目标分类文件
     *
     * @param batchId
     * @param targetBillId
     * @param fileImageNames
     * @return
     */
    @Override
    public int updateFileImageBill(Long batchId, Integer[] billIds, String[] fileImageNames, Integer targetBillId, String trg) {
        int fileNum = cmsFileMapper.updateCmsFileBill(batchId, billIds, fileImageNames, targetBillId, trg);
        int imageNum = cmsImageMapper.updateCmsImageBill(batchId, billIds, fileImageNames, targetBillId, trg);
        return fileNum + imageNum;
    }

    @Override
    public PmsBatch selectPmsBatchByBatchId(Long batchId) {
        return pmsBatchMapper.selectPmsBatchByBatchId(batchId);
    }

    /**
     * 对象转部门树
     *
     * @param pmsBatchList 项目列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<PmsBatch> pmsBatchList) {
        return initZtree(pmsBatchList, null);
    }

    /**
     * 对象转项目树
     *
     * @param pmsBatchList    项目列表
     * @param roleProjectList 角色已存在项目列表
     * @return 树结构列表
     */
    @Override
    public List<Ztree> initZtree(List<PmsBatch> pmsBatchList, List<String> roleProjectList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleProjectList);
        for (PmsBatch pmsBatch : pmsBatchList) {
            if (!PmsConstants.PMO_STATUS_DISCARD.equals(pmsBatch.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(pmsBatch.getId());
                ztree.setName(pmsBatch.getProjectName());
                ztree.setTitle(pmsBatch.getProjectName());
                if (isCheck) {
                    ztree.setChecked(roleProjectList.contains(pmsBatch.getId() + pmsBatch.getProjectName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }


    /**
     * 查询分类集合
     *
     * @return 所有分类信息
     */
    @Override
    public List<CmsBill> selectBillVo(String pmsbatchId, String modelId, String billId,
                                      Boolean billFlag, List<Long> dataRoleIds) {
        // 获得每个角色拥有的按钮 map
        Map<Long, List<SysMenu>> roleMap = new HashMap<>(8);
        // 查询每个角色在分类管理自定义勾选的项目
        Map<Long, List<Long>> roleProMap = new HashMap<>(8);
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (SysRole role : roles) {
            List<SysMenu> menus = sysMenuMapper.selectMenusByRole(role.getRoleId());
            List<Long> roleProjects = pmsBatchMapper.selectPIdsByDataRole(role.getRoleId());
            roleMap.put(role.getRoleId(), menus);
            roleProMap.put(role.getRoleId(), roleProjects);
        }
        // 用户的部门
        SysDept userDept = sysDeptMapper.selectDeptById(ShiroUtils.getSysUser().getDeptId());
        // 用户部门的子部门
        List<String> subDept = sysDeptMapper.selectSubDeptById(userDept.getDeptId());
        userDept.setSubDept(subDept);
        // 如果有辅部门
        String auxiliaryDept = ShiroUtils.getSysUser().getAuxiliaryDept();
        List<String> auxiliaryDepts = new ArrayList<>();
        if (!"".equals(auxiliaryDept) && auxiliaryDept != null) {
            auxiliaryDepts = Arrays.asList(Convert.toStrArray(auxiliaryDept));
        }
        // 要展示的CmsList
        List<CmsBill> voCmsList = new ArrayList<>();
        if (pmsbatchId != null && !"".equals(pmsbatchId)) {
            // 项目
            PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchById(Long.valueOf(pmsbatchId));
            // 过滤权限查询条件
            checkRoleParam(ShiroUtils.getSysUser(), dataRoleIds, pmsBatch, roleProMap);
            // 获得该模型分类节点List
            List<CmsBill> billList = getBillList(pmsBatch.getModelList(), dataRoleIds);
            // 分类的List转为有序的LinkedHashMap
            LinkedHashMap<Long, CmsBill> billMap = new LinkedHashMap<>(64);
            for (CmsBill bill : billList) {
                if (PmsConstants.PMS_BILL_OFF.equals(bill.getStatus())) {
                    bill.setBillName(bill.getBillName() + "（已停用）");
                }
                // 获得当前分类的按钮权限
                getButtonOfBill(bill, roles, roleMap, pmsBatch, userDept, auxiliaryDepts, roleProMap);
                billMap.put(bill.getId(), bill);
            }
            List<Long> pairBillIds = cmsBillMapper.selectBillIdsOfPairing();
            // 筛选出该分类节点下的所有分类节点
            if (billList != null && billList.size() > 0) {
                // billFlag 判断是否点击的是步骤条节点 是 true 否 null
                if (billFlag == null) {
                    for (int i = 0; i < billList.size(); i++) {
                        if (PmsConstants.LEFT.equals(billList.get(i).getLeaf())) {
                            // 最新和历史
                            getDisplay12VoList(billMap, voCmsList, billList, i, pmsBatch, pairBillIds);
                        } else if (PmsConstants.DISPLAY_PAIRING.equals(billList.get(i).getDisplay())) {
                            // 按日期成对
                            getDisplay3VoList(billMap, voCmsList, billList, i, pmsBatch);
                        } else {
                            voCmsList.add(billList.get(i));
                        }
                    }
                } else {
                    // 点击的是步骤条的节点
                    for (int i = 0; i < billList.size(); i++) {
                        if (billList.get(i).getAllPath().contains(billId) && !PmsConstants.DISPLAY_PAIRING.equals(billList.get(i).getDisplay())) {
                            getDisplay12VoList(billMap, voCmsList, billList, i, pmsBatch, pairBillIds);
                        } else if (PmsConstants.DISPLAY_PAIRING.equals(billList.get(i).getDisplay()) && billList.get(i).getAllPath().contains(billId)) {
                            getDisplay3VoList(billMap, voCmsList, billList, i, pmsBatch);
                        }
                    }
                }
            }
        }
        return voCmsList;
    }

    // 根据当前登录用户拥有的角色获得该分类拥有的按钮
    @Override
    public CmsBill getButtonOfBill(CmsBill cmsBill, List<SysRole> roles, Map<Long, List<SysMenu>> roleMap,
                                   PmsBatch pmsBatch, SysDept sysDept, List<String> auxiliaryDepts, Map<Long, List<Long>> roleProMap) {
        // 用户部门
        String userDept = sysDept.getDeptId().toString();
        // 项目部门
        String proDept = pmsBatch.getBuildDept();
        for (SysRole role : roles) {
            // 当前角色具有的按钮
            List<SysMenu> menus = roleMap.get(role.getRoleId());
            if (role.getRoleName().contains("项目经理")) {
                if (ShiroUtils.getLoginName().equals(pmsBatch.getProjectManager())) {
                    if (cmsBill.getMenus() == null) {
                        cmsBill.setMenus(menus);
                    } else {
                        cmsBill.getMenus().addAll(menus);
                    }
                }
            } else if (role.getRoleName().contains("产品经理")) {
                if (ShiroUtils.getLoginName().equals(pmsBatch.getProductManager())) {
                    if (cmsBill.getMenus() == null) {
                        cmsBill.setMenus(menus);
                    } else {
                        cmsBill.getMenus().addAll(menus);
                    }
                }
            } else if (DataScopeAspect.DATA_SCOPE_DEPT.equals(role.getDataScope())) {
                // 本部门
                if (proDept.equals(userDept)) {
                    if (cmsBill.getMenus() == null) {
                        cmsBill.setMenus(menus);
                    } else {
                        cmsBill.getMenus().addAll(menus);
                    }
                } else if (auxiliaryDepts.contains(proDept)) {
                    if (cmsBill.getMenus() == null) {
                        cmsBill.setMenus(menus);
                    } else {
                        cmsBill.getMenus().addAll(menus);
                    }
                }
            } else if (DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD.equals(role.getDataScope())) {
                // 本部门及以下
                if (proDept != null) {
                    if (!proDept.equals(userDept) && !sysDept.getSubDept().contains(proDept)) {
                        if (cmsBill.getMenus() == null) {
                            cmsBill.setMenus(menus);
                        } else {
                            cmsBill.getMenus().addAll(menus);
                        }
                    }
                    if (auxiliaryDepts.size() > 0) {
                        for (String auDept : auxiliaryDepts) {
                            List<String> depts = sysDeptMapper.selectSubDeptById(Long.valueOf(auDept));
                            if (depts.contains(proDept)) {
                                if (cmsBill.getMenus() == null) {
                                    cmsBill.setMenus(menus);
                                } else {
                                    cmsBill.getMenus().addAll(menus);
                                }
                            }
                        }
                    }
                }
            } else if (DataScopeAspect.DATA_SCOPE_CUSTOM.equals(role.getDataScope())) {
                // 自定义数据权限
                List<Long> deptIds = sysRoleDeptMapper.selectDeptIdsByRoleId(role.getRoleId());
                if (!deptIds.contains(Long.valueOf(proDept))) {
                    if (cmsBill.getMenus() == null) {
                        cmsBill.setMenus(menus);
                    } else {
                        cmsBill.getMenus().addAll(menus);
                    }
                }
            } else if (DataScopeAspect.DATA_SCOPE_SELF.equals(role.getDataScope())) {
                // 仅本人
                if (ShiroUtils.getLoginName().equals(pmsBatch.getProjectManager())) {
                    if (cmsBill.getMenus() == null) {
                        cmsBill.setMenus(menus);
                    } else {
                        cmsBill.getMenus().addAll(menus);
                    }
                }
            } else {
                // 全部
                if (cmsBill.getMenus() == null) {
                    cmsBill.setMenus(menus);
                } else {
                    cmsBill.getMenus().addAll(menus);
                }
            }
            // 如果项目为该角色额外勾选的项目
            List<Long> proIds = roleProMap.get(role.getRoleId());
            if (proIds != null && proIds.contains(pmsBatch.getBatchId())) {
                if (cmsBill.getMenus() == null) {
                    cmsBill.setMenus(menus);
                } else {
                    cmsBill.getMenus().addAll(menus);
                }
            }
        }
        List<SysMenu> billMenus = cmsBill.getMenus();
        List<SysMenu> unique = null;
        if (billMenus != null) {
            // 根据id去重
            unique = billMenus.stream().collect(collectingAndThen(
                    toCollection(() -> new TreeSet<>(comparingLong(SysMenu::getMenuId))), ArrayList::new));
        }
        cmsBill.setMenus(unique);
        return cmsBill;
    }

    @Override
    public List<Long> selectPIdsByDataRole(Long roleId) {
        return pmsBatchMapper.selectPIdsByDataRole(roleId);
    }


    // 获取页面要展示的BillList
    private List<CmsBill> getDisplay12VoList(LinkedHashMap<Long, CmsBill> billMap, List<CmsBill> voCmsList,
                                             List<CmsBill> billList, int i, PmsBatch pmsBatch,
                                             List<Long> pairBillIds) {
        CmsBill currentBill = billList.get(i);                                      // 当前分类
        CmsBill parentBill = billMap.get(Long.valueOf(currentBill.getParentId()));  // 当前分类父分类
        // 先查询当前分类下的文件数量
        getFileActualNum(pmsBatch, billMap, currentBill);
        if (parentBill != null && !PmsConstants.DISPLAY_PAIRING.equals(parentBill.getDisplay())) {
            voCmsList.add(currentBill);
        } else if (!"noClassify".equals(currentBill.getBillCode())) {
            if (!pairBillIds.contains(currentBill.getId())) {
                // 无祖籍节点的叶子节点
                voCmsList.add(currentBill);
            }
        } else if ("noClassify".equals(currentBill.getBillCode())) {
            voCmsList.add(currentBill);
        }

        // 如果分类启用并有文件
        if (currentBill.getMonitorNum() > 0 && PmsConstants.DISPLAY_HISTORY.equals(currentBill.getDisplay())) {
            // 分类是展示历史形式
            getFilesByBill(voCmsList, pmsBatch, currentBill);
        } else if (currentBill.getMonitorNum() == 0 && currentBill.getActualFileNum() > 0 &&
                PmsConstants.DISPLAY_HISTORY.equals(currentBill.getDisplay())) {
            // 分类停用但是分类下还有文件
            getFilesByBill(voCmsList, pmsBatch, currentBill);
        } else if (currentBill.getMonitorNum() == 0 && currentBill.getActualFileNum() == 0) {
            voCmsList.remove(currentBill);
        }
        return voCmsList;
    }

    private void getFilesByBill(List<CmsBill> voCmsList, PmsBatch pmsBatch, CmsBill currentBill) {
        List<CmsBill> fileImageBills = cmsBillMapper.selectFileImageBills(pmsBatch.getBatchId(), currentBill.getId());
        if (fileImageBills != null && fileImageBills.size() > 0) {
            for (CmsBill cmsBill : fileImageBills) {
                cmsBill.setParentId(Long.valueOf(currentBill.getId()));
                cmsBill.setBillOrder(currentBill.getBillOrder() + 1);
                cmsBill.setServerUrl(UploadUtil.downloadAPI(cmsBill.getFileImageId()));
                cmsBill.setIsFile("1");
                cmsBill.setMenus(currentBill.getMenus());
                cmsBill.setDisplay(currentBill.getDisplay());
                cmsBill.setStatus(currentBill.getStatus());
            }
            voCmsList.addAll(fileImageBills);
        }
    }

    // 获取按日期成对出现的BillList
    private void getDisplay3VoList(LinkedHashMap<Long, CmsBill> billMap, List<CmsBill> voCmsList,
                                   List<CmsBill> billList, int i, PmsBatch pmsBatch) {
        CmsBill currentBill = billList.get(i);
        voCmsList.add(currentBill);
        getFileActualNum(pmsBatch, billMap, billList.get(i));
        // 查询出按照日期两两展示的所有文件或影像
        // 获得所有文件组成分类List
        List<CmsBill> fileImages = getBillFileImages(pmsBatch.getBatchId(), currentBill.getId());

        if (currentBill.getMonitorNum() > 0 || (currentBill.getMonitorNum() == 0 && fileImages.size() > 0)) {
            // 按照标签排序
            Collections.sort(fileImages, Comparator.comparing(CmsBill::getTrg));
            String trg = "";
            CmsBill trgBill = null;         // 标签分类
            if (fileImages != null && fileImages.size() > 0) {
                for (CmsBill fileImage : fileImages) {
                    fileImage.setIsFile("1");
                    fileImage.setMenus(currentBill.getMenus());
                    fileImage.setDisplay(currentBill.getDisplay());
                    fileImage.setStatus(currentBill.getStatus());
                    // 放日期标签
                    if ("".equals(trg) || !trg.equals(fileImage.getTrg())) {
                        trg = fileImage.getTrg();
                        // 若trg不相等，说明不是同一天上传的文件，再新建一个trg分类节点
                        trgBill = new CmsBill();
                        trgBill.setId((long) (Math.random() * 10000000));
                        trgBill.setParentId(Long.valueOf(currentBill.getId()));
                        trgBill.setBillName(trg);
                        trgBill.setTrgNode("1");
                        voCmsList.add(trgBill);
                    }
                    // 放文件
                    if (trgBill != null) {
                        CmsBill bill = billMap.get(fileImage.getFileImageBillId());
                        if (bill != null) {
                            fileImage.setBillName(fileImage.getBillName() + "-(" + bill.getBillName() + ")");
                            fileImage.setParentId(trgBill.getId());
                            voCmsList.add(fileImage);
                        }
                    }
                }
            }
        } else if (currentBill.getMonitorNum() == 0 && fileImages.size() == 0) {
            voCmsList.remove(currentBill);
        }
    }

    /**
     * 通过batchId和parentId查询文件并组成分类列表
     *
     * @return
     */
    private List<CmsBill> getBillFileImages(Long batchId, Long parentId) {
        List<CmsBill> fileImages = new ArrayList<>();
        List<CmsBill> billFiles = cmsBillMapper.selectFileBillsByFileParentId(batchId, parentId);
        fileImages.addAll(billFiles);
        List<CmsBill> billImages = cmsBillMapper.selectFileBillsByImageParentId(batchId, parentId);
        fileImages.addAll(billImages);
        // 拼接serverUrl
        if (fileImages.size() > 0) {
            for (CmsBill bill : fileImages) {
                if (bill.getTrg() == null) {
                    bill.setTrg("无日期标签");
                }
                bill.setServerUrl(UploadUtil.downloadAPI(bill.getFileImageId()));
            }
        }
        return fileImages;
    }

    private void getFileActualNum(PmsBatch pmsBatch, Map<Long, CmsBill> billMap, CmsBill cmsBill) {
        // Map里的该文件分类的父分类
        CmsBill parentBill = null;
        if (!"noClassify".equals(cmsBill.getBillCode()) && !PmsConstants.DISPLAY_PAIRING.equals(cmsBill.getDisplay())) {
            parentBill = billMap.get(cmsBill.getParentId());
        } else {
            parentBill = cmsBill;
        }
        // 分类下的文件数量
        int fileNum = getBillFileNum(pmsBatch.getBatchId(), cmsBill);
        // 监控数量默认为1
        int monitorNum = 1;
        // 查询是否指定过监控数量
        if (PmsConstants.DISPLAY_HISTORY.equals(cmsBill.getDisplay())) {
            Map<String, Integer> numMap = cmsBillMapper.selectBillMonitorNum(pmsBatch.getId(), cmsBill.getId());
            monitorNum = numMap != null ? numMap.get("fileNum") : monitorNum;
        }

        if (PmsConstants.PMS_BILL_ON.equals(cmsBill.getStatus())) {
            cmsBill.setMonitorNum(monitorNum);
        } else {
            cmsBill.setMonitorNum(NumConstants.NUM_0);
        }
        cmsBill.setActualFileNum(fileNum);
        // 如果有父分类且分类是启用的，统计分类的监控数
        if (parentBill != null && PmsConstants.PMS_BILL_ON.equals(cmsBill.getStatus())) {
            if (parentBill.getActualFileNum() == null || parentBill.getActualFileNum() == NumConstants.NUM_0) {
                parentBill.setActualFileNum(fileNum);
            } else {
                parentBill.setActualFileNum(parentBill.getActualFileNum() + fileNum);
            }
            parentBill.setMonitorNum(parentBill.getMonitorNum() == null ? monitorNum : parentBill.getMonitorNum() + monitorNum);
        }
    }

    /**
     * 通过项目id，模型id，分类id查询文件或者影像
     *
     * @param pmsbatchId
     * @param modelId
     * @param billId
     * @return 所有分类信息
     */
    @Override
    public List<CmsFileImageVO> selectFileImage(String pmsbatchId, String modelId, String billId) {
        List<CmsFileImageVO> cmsFileImageVOList = new ArrayList<>();
        PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchById(Long.valueOf(pmsbatchId));
        CmsBill cmsBill = cmsBillMapper.selectCmsBillById(Long.valueOf(billId));
        List<CmsFile> fileList = null;
        List<CmsImage> imageList = null;
        if (!"noClassify".equals(cmsBill.getBillCode())) {
            // 查询出该类型下所属模型的所有文件
            fileList = cmsFileMapper.selectFilesByCondition(pmsBatch.getBatchId(), Long.valueOf(billId));
            // 查询出该类型下所属模型的所有影像
            imageList = cmsImageMapper.selectImageByCondition(pmsBatch.getBatchId(), Long.valueOf(billId));
        } else {
            // 查询出未分类下的所有文件
            fileList = cmsFileMapper.selectFileListByCondition(pmsBatch.getBatchId(), Long.valueOf(billId));
            // 查询出未分类下的所有影像
            imageList = cmsImageMapper.selectImageListByCondition(pmsBatch.getBatchId(), Long.valueOf(billId));
        }
        // 转换为前台页面需要的VO
        fileImageToVo(cmsFileImageVOList, fileList, imageList);
        return cmsFileImageVOList;
    }

    @Override
    public List<String> selectAllPmsId(PmsBatch pmsBatch) {
        return pmsBatchMapper.selectAllPmsId(pmsBatch);
    }

    /**
     * 通过条件查询项目id集合
     *
     * @param pmsBatch
     * @return 所有符合条件的项目的id
     */
    @Override
    public List<String> selectPmsIdList(PmsBatch pmsBatch) {
        return pmsBatchMapper.selectPmsIdList(pmsBatch);
    }

    @Override
    public List<String> selectPmsIdListByRole(PmsBatch pmsBatch) {
        return pmsBatchMapper.selectPmsIdListByRole(pmsBatch);
    }

    /**
     * 通过文件或影像名称查询出所有历史版本
     *
     * @param names
     * @return
     */
    @Override
    public List<CmsFileImageVO> selectFileImageHistory(Long batchId, Integer billId, String[] names) {
        List<CmsFile> fileList = cmsFileMapper.selectCmsFileHistory(batchId, billId, names);
        List<CmsImage> imageList = cmsImageMapper.selectImageHistory(batchId, billId, names);
        List<CmsFileImageVO> voList = new ArrayList<>();
        fileImageToVo(voList, fileList, imageList);
        return voList;
    }

    /**
     * 条件查询文件资料的数量
     *
     * @param batchId
     * @param cmsBill
     * @return
     */
    public int getBillFileNum(Long batchId, CmsBill cmsBill) {
        // 查询出该类型下所属模型的该文件
        Integer fileNum = cmsFileMapper.selectFileNumByCondition(batchId, cmsBill.getId());
        if (fileNum > 0) {
            List<CmsFile> files = cmsFileMapper.selectFileByCondition(batchId, cmsBill.getId());
            cmsBill.setServerUrl(UploadUtil.downloadAPI(files.get(0).getFileId()));
            cmsBill.setFileImageId(files.get(0).getId());
        }
        // 如果该文件是影像类查询出该影像文件
        Integer imageNum = cmsImageMapper.selectImageNumByCondition(batchId, cmsBill.getId());
        if (imageNum != null && imageNum > 0) {
            List<CmsImage> images = cmsImageMapper.selectImageListByCondition(batchId, cmsBill.getId());
            cmsBill.setServerUrl(UploadUtil.downloadAPI(images.get(0).getImageId()));
            cmsBill.setFileImageId(images.get(0).getId());
        }
        return fileNum + imageNum;
    }


    /**
     * 将file或image转化为页面需要的VO
     *
     * @param voList
     * @param files
     * @param images
     * @return
     */
    private List<CmsFileImageVO> fileImageToVo(List<CmsFileImageVO> voList, List<CmsFile> files, List<CmsImage> images) {
        // 文件转
        if (files != null && files.size() > 0) {
            for (CmsFile file : files) {
                CmsFileImageVO vo = new CmsFileImageVO();
                vo.setId(file.getId());
                vo.setStringId(file.getFileId().toString());
                vo.setFileImageNo(file.getFileNo());
                vo.setMd5(file.getMd5());
                vo.setBatchId(file.getBatchId());
                vo.setVoName(file.getFileName());
                vo.setFileImageName(file.getFileName());
                vo.setBillId(file.getBillId());
                vo.setBillName(file.getBillName());
                vo.setFilePath(file.getFilePath());
                vo.setCreateTime(file.getCreateTime());
                vo.setType(file.getFileType());
                vo.setFileUrl(UploadUtil.downloadAPI(file.getFileId()));
                voList.add(vo);
            }
        }
        // 影像转
        if (images != null && images.size() > 0) {
            for (CmsImage image : images) {
                CmsFileImageVO vo = new CmsFileImageVO();
                vo.setId(image.getId());
                vo.setStringId(image.getImageId().toString());
                vo.setFileImageNo(image.getImageNo());
                vo.setMd5(image.getMd5());
                vo.setBatchId(image.getBatchId());
                vo.setVoName(image.getImageName());
                vo.setFileImageName(image.getImageName());
                vo.setBillId(image.getBillId());
                vo.setBillName(image.getBillName());
                vo.setFilePath(image.getImagePath());
                vo.setFileUrl(UploadUtil.downloadAPI(image.getImageId()));
                vo.setCreateTime(image.getCreateTime());
                vo.setType(image.getOcrType());
                voList.add(vo);
            }
        }
        return voList;
    }

    /**
     * 获得全部分类(除叶子节点)
     */
    @Override
    public List<Ztree> getAllCmsBill() {
        List<CmsBill> cmsBillList = cmsBillMapper.selectAllCmsBill();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (CmsBill cmsBill : cmsBillList) {
            Ztree ztree = new Ztree();
            ztree.setId(Long.valueOf(cmsBill.getId()));
            ztree.setpId(Long.valueOf(cmsBill.getParentId()));
            ztree.setName(cmsBill.getBillName());
            ztree.setTitle(cmsBill.getBillCode());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    /**
     * 接口，返回最新的文件
     *
     * @param batchId
     * @param billId
     * @return
     */
    @Override
    public CmsBill selectNewFileImage(Long batchId, Long billId) {
        CmsBill fileBill = cmsBillMapper.selectNewFileByBatchIdBillId(batchId, billId);
        CmsBill imageBill = cmsBillMapper.selectNewByImageBatchIdBillId(batchId, billId);
        CmsBill bill = null;
        if (fileBill != null && imageBill != null) {
            bill = fileBill.getCreateTime().compareTo(imageBill.getCreateTime()) > 0 ? fileBill : imageBill;
        } else if (fileBill != null && imageBill == null) {
            bill = fileBill;
        } else if (fileBill == null && imageBill != null) {
            bill = imageBill;
        } else {
            return null;
        }
        bill.setServerUrl(UploadUtil.downloadAPI(bill.getFileImageId()));
        return bill;
    }

    /**
     * 校验项目名唯一性
     *
     * @param pmsBatch
     * @return
     */
    @Override
    public String checkProjectNameUnique(PmsBatch pmsBatch) {
        if (pmsBatch == null || pmsBatch.getProjectName() == null) {
            throw new BusinessException("项目名为空，请重新输入");
        }
        PmsBatch pb = pmsBatchMapper.checkProjectNameUnique(pmsBatch.getProjectName());
        if (StringUtils.isNotNull(pb)) {
            return PmsConstants.PROJECT_NAME_NOT_UNIQUE;
        }
        return PmsConstants.PROJECT_NAME_UNIQUE;
    }

    /**
     * 校验项目编号唯一性
     *
     * @param pmsBatch
     * @return
     */
    @Override
    public String checkOperationCodeUnique(PmsBatch pmsBatch) {
        if (pmsBatch == null || pmsBatch.getOperationCode() == null) {
            throw new BusinessException("项目编号为空，请重新输入");
        }
        PmsBatch pb = pmsBatchMapper.checkOperationCodeUnique(pmsBatch.getOperationCode());
        if (StringUtils.isNotNull(pb)) {
            return PmsConstants.OPERATIONCODE_NAME_NOT_UNIQUE;
        }
        return PmsConstants.OPERATIONCODE_NAME_UNIQUE;
    }

    /**
     * 校验预算编号唯一性
     *
     * @param pmsBatch
     * @return
     */
    @Override
    public String checkBudgetIdUnique(PmsBatch pmsBatch) {
        if (pmsBatch == null || pmsBatch.getBudgetId() == null) {
            throw new BusinessException("预算编号为空，请重新输入");
        }
        PmsBatch pb = pmsBatchMapper.checkBudgetIdUnique(pmsBatch.getBudgetId());
        if (StringUtils.isNotNull(pb)) {
            return PmsConstants.BUDGETID_NAME_NOT_UNIQUE;
        }
        return PmsConstants.BUDGETID_NAME_UNIQUE;
    }

    /**
     * 查询项目
     *
     * @param pmsBatch
     * @return
     */
    @Override
    public List<PmsBatchVO> selectPmsBatchVOBySysRoles(PmsBatch pmsBatch) {
        return pmsBatchMapper.selectPmsBatchVOBySysRoles(pmsBatch);
    }

    @Override
    public List<Long> selectBillIdByPmsId(Long pmsBatchId) {
        return cmsBillMapper.selectBillIdByPmsId(pmsBatchId);
    }

    @Override
    public Map<String, Integer> selectBillMonitorNum(Long pmsBatchId, Long billId) {
        return cmsBillMapper.selectBillMonitorNum(pmsBatchId, billId);
    }

    @Override
    public void updateBillMonitorNum(Long pmsBatchId, Long billId, Integer fileNum) {
        cmsBillMapper.updateBillMonitorNum(pmsBatchId, billId, fileNum);
    }

    @Override
    public void insertBillMonitorNum(Long pmsBatchId, Long billId, Integer fileNum) {
        cmsBillMapper.insertBillMonitorNum(pmsBatchId, billId, fileNum);
    }

}
