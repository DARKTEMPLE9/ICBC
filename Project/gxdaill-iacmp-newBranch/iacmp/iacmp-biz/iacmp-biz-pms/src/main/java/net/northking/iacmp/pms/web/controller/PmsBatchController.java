package net.northking.iacmp.pms.web.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.cms.service.*;
import net.northking.iacmp.common.bean.domain.cms.*;
import net.northking.iacmp.common.bean.dto.cms.PmsBatchDTO;
import net.northking.iacmp.common.bean.vo.cms.CmsFileImageVO;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.constant.PmsConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.domain.TreeNode;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.core.page.PageDomain;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.core.page.TableSupport;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.framework.aspectj.DataScopeAspect;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.result.ResultCode;
import net.northking.iacmp.system.domain.*;
import net.northking.iacmp.system.service.*;
import net.northking.iacmp.utils.NumConstants;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * 项目操作处理
 *
 * @author yin.rui
 * @date 2019-08-26
 */
@Controller
@RequestMapping("/pms/pmsBatch")
public class PmsBatchController extends BaseController {

    private String prefix = "pms/pmsBatch";

    @Autowired
    private IPmsBatchService pmsBatchService;
    @Autowired
    private ICmsSystemService cmsSystemService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ICmsModelService cmsModelService;
    @Autowired
    private ICmsBillService cmsBillService;
    @Autowired
    private ICmsUserRoleService cmsUserRoleService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private IHttpService httpService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ICmsModelBillService cmsModelBillService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ICmsFileService cmsFileService;
    @Autowired
    private ICmsImageService cmsImageService;
    /**
     * 最大上传大小
     */
    @Value("${maxUploadSize}")
    private String maxUploadSize;

    @RequiresPermissions("pms:pmsBatch:view")
    @GetMapping()
    public String pmsBatch(String index, ModelMap mmap) {
        mmap.put("projectManager", "");
        mmap.put("productManager", "");
        // 如果有管理员角色放入map
        for (SysRole role : ShiroUtils.getSysUser().getRoles()) {
            if (PmsConstants.ADMIN_ROLE_KEY.equals(role.getRoleKey())) {
                mmap.put("currentRole", role.getRoleKey());
            }
            if (PmsConstants.TECH_PROJECT_MANAGER.equals(role.getRoleKey())) {
                mmap.put("projectManager", ShiroUtils.getSysUser().getLoginName());
            }
            if (PmsConstants.PROUDUCT_MANAGER.equals(role.getRoleKey())) {
                mmap.put("productManager", ShiroUtils.getSysUser().getLoginName());
            }
        }
        mmap.put("index", index);
        return prefix + "/pmsBatch";
    }

    @GetMapping("initHtml")
    @RequiresPermissions("pms:pmsBatch:view")
    public String initHtml(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        mmap.put("user", sysUser);
        return prefix + "/initHtml";
    }


    /**
     * 监控视图页
     *
     * @return
     */
    @RequiresPermissions("pms:pmsBatch:view")
    @GetMapping("/monitorView")
    public String pmsBatchMonitor() {
        return prefix + "/monitorView";
    }

    /**
     * 查询项目列表
     */
    @RequiresPermissions("pms:pmsBatch:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsBatchDTO pmsBatchDto, String index) {
        PmsBatch pmsBatch = new PmsBatch();
        if (pmsBatchDto.getProjectManagerName() != null && !"".equals(pmsBatchDto.getProjectManagerName())) {
            List<SysUser> projectManagers = sysUserService.selectUserByUserNameList(pmsBatchDto.getProjectManagerName());
            pmsBatchDto.setProjectManager(projectManagers.get(0).getLoginName());
        }
        if (pmsBatchDto.getProductManagerName() != null && !"".equals(pmsBatchDto.getProductManagerName())) {
            List<SysUser> productManagers = sysUserService.selectUserByUserNameList(pmsBatchDto.getProductManagerName());
            pmsBatchDto.setProductManager(productManagers.get(0).getLoginName());
        }
        pmsBatch.setProjectManager(pmsBatchDto.getProjectManager());
        pmsBatch.setProductManager(pmsBatchDto.getProductManager());
        pmsBatch.setProjectName(pmsBatchDto.getProjectName());
        pmsBatch.setOperationCode(pmsBatchDto.getOperationCode());
        pmsBatch.setDeptName(pmsBatchDto.getDeptName());
        pmsBatch.setSysLevel(pmsBatchDto.getSysLevel());
        pmsBatch.setSysType(pmsBatchDto.getSysType());
        pmsBatch.setStatus(pmsBatchDto.getStatus());
        //查询有该分类的项目
        String cmsBillCode = pmsBatchDto.getCmsBillCode();
        List<String> modelIds = new ArrayList<>();//模型编号
        if (cmsBillCode != null && !"".equals(cmsBillCode)) {
            CmsModelBill cmsModelBill = new CmsModelBill();
            cmsModelBill.setBillId(Integer.valueOf(cmsBillCode));
            List<CmsModelBill> cmsModelBillList = cmsModelBillService.selectCmsModelBillList(cmsModelBill);
            for (CmsModelBill modelBill : cmsModelBillList) {
                modelIds.add(modelBill.getModelId().toString());
            }
        }
//        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        List<PmsBatch> totalPms = new ArrayList<>();
        List<SysDictData> SysDictDatas = sysDictDataService.selectDictDataByType("pms_batch_status");//查询全部状态
        /*部门权限控制查询*/
        List<SysRole> roles = sysUser.getRoles();
        // 去重后的项目list
        List<PmsBatch> pmsBatchDept = null;
        if (roles != null && roles.size() > 0) {
            List<PmsBatch> pmsBatches = new ArrayList<>();
            for (SysRole role : roles) {
                List<PmsBatch> batches = null;
                if (sysUser.getUserId() == 1L || PmsConstants.ADMIN_ROLE_KEY.equals(role.getRoleKey()) ||
                        role.getRoleName().contains(PmsConstants.ADMIN_ROLE_NAME)) {
                    if ("1".equals(index)) {
                        pmsBatch.setProjectManager(ShiroUtils.getSysUser().getLoginName());
                    }
                    // 管理员
                    batches = pmsBatchService.selectAllPmsBatch(pmsBatch);
                } else {
                    // 1-全部, 2-自定义, 3-本部门, 4-本部门及以下, 5-仅本人
                    if (DataScopeAspect.DATA_SCOPE_CUSTOM.equals(role.getDataScope())) {
                        List<Long> deptIds = pmsBatchService.selectDeptIdsByRoleId(role.getRoleId());
                        pmsBatch.setDeptIds(deptIds);
                    } else if (DataScopeAspect.DATA_SCOPE_DEPT.equals(role.getDataScope())) {
                        pmsBatch.setBuildDept(sysUser.getDeptId().toString());
                        // 用户有辅部门
                        if (sysUser.getAuxiliaryDept() != null && !"".equals(sysUser.getAuxiliaryDept())) {
                            pmsBatch.setAuxiliaryDeptList(Convert.toLongArray(sysUser.getAuxiliaryDept()));
                        }
                    } else if (DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD.equals(role.getDataScope())) {
                        pmsBatch.setDataScope(role.getDataScope());
                        pmsBatch.setDataScopeDept(sysUser.getDeptId().toString());
                        // 用户有辅部门
                        if (sysUser.getAuxiliaryDept() != null && !"".equals(sysUser.getAuxiliaryDept())) {
                            pmsBatch.setAuxiliaryDeptList(Convert.toLongArray(sysUser.getAuxiliaryDept()));
                        }
                    } else if (DataScopeAspect.DATA_SCOPE_SELF.equals(role.getDataScope())) {
                        if (role.getRoleName() != null && role.getRoleName().contains("产品经理")) {
                            pmsBatch.setProductManager(sysUser.getLoginName());
                        } else {
                            pmsBatch.setProjectManager(sysUser.getLoginName());
                        }
                    } else { //全部
                        if (role.getRoleName() != null && role.getRoleName().contains("ITPMO")) {
                            //ITPMO查看全部数据权限（包含已废弃）
                            if (pmsBatch.getStatus() == null || "".equals(pmsBatch.getStatus())) {
                                List<String> statusList = new ArrayList<>();//查询状态集合
                                if (SysDictDatas.size() > 0) {
                                    for (SysDictData sysDictData : SysDictDatas) {
                                        statusList.add(sysDictData.getDictValue());
                                    }
                                }
                                pmsBatch.setStatusList(statusList);
                            }
                        }
                    }
                    if (pmsBatch.getStatusList() == null || pmsBatch.getStatusList().size() <= 0) {
                        List<String> statusList = new ArrayList<>();//查询状态集合(不包含已废弃)
                        if (SysDictDatas.size() > 0) {
                            for (SysDictData sysDictData : SysDictDatas) {
                                if (!"1".equals(sysDictData.getDictValue())) {
                                    statusList.add(sysDictData.getDictValue());
                                }
                            }
                        }
                        pmsBatch.setStatusList(statusList);
                    }
                    if ("1".equals(index)) {
                        pmsBatch.setProjectManager(ShiroUtils.getSysUser().getLoginName());
                    }
                    // 权限的dataScope（默认不包含已废弃）
                    batches = pmsBatchService.selectPmsBatchBySysRoles(pmsBatch);
                    pmsBatch.setDeptId(null);
                    pmsBatch.setBuildDept(null);
                    pmsBatch.setDataScope(null);
                    pmsBatch.setDataScopeDept(null);
                    pmsBatch.setProjectManager(null);       //项目经理
                    pmsBatch.setProductManager(null);       //产品经理
                    pmsBatch.setStatusList(null);
                    pmsBatch.setAuxiliaryDeptList(null);
                    pmsBatch.setProjectManager(pmsBatchDto.getProjectManager());
                    pmsBatch.setProductManager(pmsBatchDto.getProductManager());
                    pmsBatch.setProjectName(pmsBatchDto.getProjectName());
                    pmsBatch.setOperationCode(pmsBatchDto.getOperationCode());
                    pmsBatch.setDeptName(pmsBatchDto.getDeptName());
                    pmsBatch.setSysLevel(pmsBatchDto.getSysLevel());
                    pmsBatch.setSysType(pmsBatchDto.getSysType());
                    pmsBatch.setStatus(pmsBatchDto.getStatus());
                }
                pmsBatches.addAll(batches);
            }
            //根据id属性去重
            pmsBatchDept = pmsBatches.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(PmsBatch::getId))), ArrayList::new));
        }
        if (pmsBatchDept != null && pmsBatchDept.size() > 0) {
            totalPms.addAll(pmsBatchDept);
        }

        /*项目及分类权限控制查询*/
        // 去重后的项目list
        List<PmsBatch> pmsBatchData = null;

        List<PmsBatch> dataRolePmsBatchs = new ArrayList<>();
        if (sysUser.getUserId() == PmsConstants.ADMIN_USER_ID) { // 系统管理员
            pmsBatchData = pmsBatchService.selectAllPmsBatch(pmsBatch);
        } else {
            // 获得当前登录用户所拥有的所有数据角色
            List<CmsRole> roleList = cmsUserRoleService.selectDataRoleByUserId(sysUser.getUserId());
            pmsBatch.setDataRoles(roleList);
            for (CmsRole cmsRole : roleList) {
                pmsBatch.setProjectManager(pmsBatchDto.getProjectManager());
                pmsBatch.setProductManager(pmsBatchDto.getProductManager());
                pmsBatch.setProjectName(pmsBatchDto.getProjectName());
                pmsBatch.setOperationCode(pmsBatchDto.getOperationCode());
                pmsBatch.setDeptName(pmsBatchDto.getDeptName());
                pmsBatch.setSysLevel(pmsBatchDto.getSysLevel());
                pmsBatch.setSysType(pmsBatchDto.getSysType());
                pmsBatch.setStatus(pmsBatchDto.getStatus());
                if (cmsRole.getRoleName().contains("项目经理")) {
                    pmsBatch.setProjectManager(sysUser.getLoginName());
                } else if (cmsRole.getRoleName().contains("产品经理")) {
                    pmsBatch.setProductManager(sysUser.getLoginName());
                } else if (DataScopeAspect.DATA_SCOPE_SELF.equals(cmsRole.getDataScope())) {
                    pmsBatch.setProjectManager(sysUser.getLoginName());
                }
                pmsBatch.setDataScope(cmsRole.getDataScope());
                List<PmsBatch> pmsBatchs = pmsBatchService.selectPmsBatchByCondition(pmsBatch);
                pmsBatch.setDataScope(null);
                pmsBatch.setProjectManager(null);
                pmsBatch.setProductManager(null);
                dataRolePmsBatchs.addAll(pmsBatchs);
                //根据id属性去重
                pmsBatchData = dataRolePmsBatchs.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(PmsBatch::getId))), ArrayList::new));
            }
        }

        if (pmsBatchData != null && pmsBatchData.size() > 0) {
            totalPms.addAll(pmsBatchData);
        }
        // 去重
        List<PmsBatch> uniTotalPms = totalPms.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(PmsBatch::getId))), ArrayList::new));
        List<PmsBatch> resultList = new ArrayList<>();
        if (pmsBatchDto.getCmsBillCode() != null && !"".equals(pmsBatchDto.getCmsBillCode())) {
            for (PmsBatch pBatch : uniTotalPms) {
                String modelList = pBatch.getModelList();
                if (modelIds.size() > 0) {
                    for (int i = 0; i < modelIds.size(); i++) {
                        if (modelList != null && !"".equals(modelList)) {
                            if (modelList.contains(modelIds.get(i))) {
                                resultList.add(pBatch);
                                break;
                            }
                        }
                    }
                } else {
                    //查询不到含有该分类的模型,返回空数据
                    return projectListPage(resultList);
                }

            }
            //返回按分类查询后的数据
            return projectListPage(resultList);
        }
        //返回不按分类查询的数据
        return projectListPage(uniTotalPms);
    }

    /**
     * 点击项目文档查询信息
     */
    @GetMapping("/projectDetail")
    public String projectDetail(ModelMap mmap, PmsBatch pmsBatch) {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roles = sysUser.getRoles();
        List<String> totalPmsIds = new ArrayList<>();
        List<SysDictData> SysDictDatas = sysDictDataService.selectDictDataByType("pms_batch_status");//查询全部状态
        /*部门权限控制查询*/
        // 去重后的项目list
        List<String> pmsBatchDept = null;
        if (roles != null && roles.size() > 0) {
            List<String> pmsBatches = new ArrayList<>();
            for (SysRole role : roles) {
                List<String> batchIds = null;
                if (sysUser.getUserId() == PmsConstants.ADMIN_USER_ID || PmsConstants.ADMIN_ROLE_KEY.equals(role.getRoleKey()) ||
                        role.getRoleName().contains(PmsConstants.ADMIN_ROLE_NAME)) {
                    // 如果有管理员角色放入map
                    mmap.put("currentRole", role.getRoleKey());
                    // 管理员
                    pmsBatch.setProjectManager(ShiroUtils.getSysUser().getLoginName());
                    batchIds = pmsBatchService.selectAllPmsId(pmsBatch);
                } else {
                    // 1-全部, 2-自定义, 3-本部门, 4-本部门及以下, 5-仅本人
                    if (DataScopeAspect.DATA_SCOPE_CUSTOM.equals(role.getDataScope())) {
                        List<Long> deptIds = pmsBatchService.selectDeptIdsByRoleId(role.getRoleId());
                        pmsBatch.setDeptIds(deptIds);
                    } else if (DataScopeAspect.DATA_SCOPE_DEPT.equals(role.getDataScope())) {
                        pmsBatch.setBuildDept(sysUser.getDeptId().toString());
                        // 用户有辅部门
                        if (sysUser.getAuxiliaryDept() != null && !"".equals(sysUser.getAuxiliaryDept())) {
                            pmsBatch.setAuxiliaryDeptList(Convert.toLongArray(sysUser.getAuxiliaryDept()));
                        }
                    } else if (DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD.equals(role.getDataScope())) {
                        pmsBatch.setDataScope(role.getDataScope());
                        pmsBatch.setDataScopeDept(sysUser.getDeptId().toString());
                        // 用户有辅部门
                        if (sysUser.getAuxiliaryDept() != null && !"".equals(sysUser.getAuxiliaryDept())) {
                            pmsBatch.setAuxiliaryDeptList(Convert.toLongArray(sysUser.getAuxiliaryDept()));
                        }
                    } else if (DataScopeAspect.DATA_SCOPE_SELF.equals(role.getDataScope())) {
                        if (role.getRoleName() != null && role.getRoleName().contains("产品经理")) {
                            // 产品经理
                            pmsBatch.setProductManager(sysUser.getLoginName());
                            mmap.put("productManagerRole", sysUser.getUserId());
                        } else {
                            pmsBatch.setProjectManager(sysUser.getLoginName());
                            mmap.put("projectManagerRole", sysUser.getUserId());
                        }
                    } else {//全部
                        if (role.getRoleName() != null && role.getRoleName().contains("ITPMO")) {
                            //ITPMO查看全部数据权限（包含已废弃）
                            if (pmsBatch.getStatus() == null || "".equals(pmsBatch.getStatus())) {
                                List<String> statusList = new ArrayList<>();//查询状态集合
                                if (SysDictDatas.size() > 0) {
                                    for (SysDictData sysDictData : SysDictDatas) {
                                        statusList.add(sysDictData.getDictValue());
                                    }
                                }
                                pmsBatch.setStatusList(statusList);
                            }
                        } else if (role.getRoleName() != null && role.getRoleName().contains("项目经理")) {
                            mmap.put("projectManagerRole", sysUser.getUserId());
                        } else if (role.getRoleName() != null && role.getRoleName().contains("产品经理")) {
                            mmap.put("productManagerRole", sysUser.getUserId());
                        }
                    }
                    if (pmsBatch.getStatusList() == null || pmsBatch.getStatusList().size() <= 0) {
                        List<String> statusList = new ArrayList<>();//查询状态集合(不包含已废弃)
                        if (SysDictDatas.size() > 0) {
                            for (SysDictData sysDictData : SysDictDatas) {
                                if (!"1".equals(sysDictData.getDictValue())) {
                                    statusList.add(sysDictData.getDictValue());
                                }
                            }
                        }
                        pmsBatch.setStatusList(statusList);
                    }
                    // 权限的dataScope
                    pmsBatch.setProjectManager(ShiroUtils.getSysUser().getLoginName());
                    batchIds = pmsBatchService.selectPmsIdListByRole(pmsBatch);
                    pmsBatch.setDeptId(null);
                    pmsBatch.setBuildDept(null);
                    pmsBatch.setDataScope(null);
                    pmsBatch.setDataScopeDept(null);
                    pmsBatch.setProjectManager(null);       //项目经理
                    pmsBatch.setProductManager(null);       //产品经理
                    pmsBatch.setAuxiliaryDeptList(null);
                }
                pmsBatches.addAll(batchIds);
            }
            //根据id属性去重
            pmsBatchDept = pmsBatches.stream().distinct().collect(Collectors.toList());
        }
        if (pmsBatchDept != null && pmsBatchDept.size() > 0) {
            totalPmsIds.addAll(pmsBatchDept);
        }

        /*项目及分类权限控制查询*/
        // 去重后的项目list
        List<String> pmsBatchData = null;

        List<String> dataRolePmsBatchs = new ArrayList<>();
        if (sysUser.getUserId() == 1L) { // 管理员
            pmsBatch.setProjectManager(ShiroUtils.getSysUser().getLoginName());
            pmsBatchData = pmsBatchService.selectPmsIdList(pmsBatch);
        } else {
            // 获得当前登录用户所拥有的所有数据角色
            List<CmsRole> roleList = cmsUserRoleService.selectDataRoleByUserId(sysUser.getUserId());
            pmsBatch.setDataRoles(roleList);
            for (CmsRole cmsRole : roleList) {
                if (cmsRole.getRoleName().contains("项目经理")) {
                    pmsBatch.setProjectManager(sysUser.getLoginName());
                } else if (cmsRole.getRoleName().contains("产品经理")) {
                    pmsBatch.setProductManager(sysUser.getLoginName());
                } else if (DataScopeAspect.DATA_SCOPE_SELF.equals(cmsRole.getDataScope())) {
                    pmsBatch.setProjectManager(sysUser.getLoginName());
                }
                pmsBatch.setDataScope(cmsRole.getDataScope());
                pmsBatch.setProjectManager(ShiroUtils.getSysUser().getLoginName());
                List<String> pmsBatchs = pmsBatchService.selectPmsIdList(pmsBatch);
                pmsBatch.setDataScope(null);
                dataRolePmsBatchs.addAll(pmsBatchs);
                //根据id属性去重
                pmsBatchData = dataRolePmsBatchs.stream().distinct().collect(Collectors.toList());
            }
        }

        if (pmsBatchData != null && pmsBatchData.size() > 0) {
            totalPmsIds.addAll(pmsBatchData);
        }
        // 去重
        List<String> uniTotalPmsId = totalPmsIds.stream().distinct().collect(Collectors.toList());
        String firstId = uniTotalPmsId.size() > 0 ? uniTotalPmsId.get(0) : "";
        mmap.put("pmsBatchId", firstId);
        String[] array = uniTotalPmsId.toArray(new String[uniTotalPmsId.size()]);
        mmap.put("pmsBatchIds", StringUtils.toStringOfArray(array));
        //获取到第一个项目的所有分类
        String[] pmsArray = {firstId};
        List<Long> dataRoleIds = cmsUserRoleService.selectDataRoleIdsByUserId(ShiroUtils.getSysUser().getUserId());
        List<TreeNode> projectTree = pmsBatchService.getProTreeByIds(pmsArray, dataRoleIds);
        mmap.put("firstProject", projectTree);
        if (projectTree != null && projectTree.size() > 0) {
            mmap.put("firstProjectManager", projectTree.get(1).getProjectManager());
            mmap.put("firstProductManager", projectTree.get(1).getProductManager());
        }
        String projectName = projectTree.size() > 0 ? projectTree.get(0).getName() : "暂无项目";
        mmap.put("projectName", projectName);
        mmap.put("serverAddress", getServerAddress());
        mmap.put("uploadBillIds", cmsBillService.selectBillIdOfUpload());
        mmap.put("pairBillIds", cmsBillService.selectBillIdOfPairing());
        mmap.put("maxUploadSize", maxUploadSize);
        return prefix + "/projectTree";
    }

    /**
     * 项目列表分页
     */
    public TableDataInfo projectListPage(List<PmsBatch> pmsBatchList) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        int formIndex = (pageSize * (pageNum - 1));
        int toIndex = formIndex + pageSize;
        int size = pmsBatchList.size();
        if (toIndex >= size) {
            toIndex = size;
        }
        List<PmsBatch> list = pmsBatchList.subList(formIndex, toIndex);

        if (pageDomain.getOrderByColumn() != null) {
            Collections.sort(list, new Comparator<PmsBatch>() {
                @Override
                public int compare(PmsBatch o1, PmsBatch o2) {
                    if (pageDomain.getIsAsc().equals("asc")) {
                        //主键
                        if (pageDomain.getOrderByColumn().equals("id")) {
                            return o1.getId().compareTo(o2.getId());
                        }
                        //项目名称
                        if (pageDomain.getOrderByColumn().equals("projectName")) {
                            return o1.getProjectName().compareTo(o2.getProjectName());
                        }
                        //项目编号
                        if (pageDomain.getOrderByColumn().equals("operationCode")) {
                            return o1.getOperationCode().compareTo(o2.getOperationCode());
                        }
                        //科技项目经理
                        if (pageDomain.getOrderByColumn().equals("projectManagerName")) {
                            return o1.getProjectManagerName().compareTo(o2.getProjectManagerName());
                        }
                        //产品经理
                        if (pageDomain.getOrderByColumn().equals("productManagerName")) {
                            return o1.getProductManagerName().compareTo(o2.getProductManagerName());
                        }
                        //承建部门
                        if (pageDomain.getOrderByColumn().equals("deptName")) {
                            return o1.getDeptName().compareTo(o2.getDeptName());
                        }
                        //状态
                        if (pageDomain.getOrderByColumn().equals("status")) {
                            return o1.getStatus().compareTo(o2.getStatus());
                        }
                        //系统级别
                        if (pageDomain.getOrderByColumn().equals("sysLevel")) {
                            return o1.getSysLevel().compareTo(o2.getSysLevel());
                        }
                        //系统类型
                        if (pageDomain.getOrderByColumn().equals("sysType")) {
                            return o1.getSysType().compareTo(o2.getSysType());
                        }
                    }

                    if (pageDomain.getIsAsc().equals("desc")) {
                        //主键
                        if (pageDomain.getOrderByColumn().equals("id")) {
                            return o2.getId().compareTo(o1.getId());
                        }
                        //项目名称
                        if (pageDomain.getOrderByColumn().equals("projectName")) {
                            return o2.getProjectName().compareTo(o1.getProjectName());
                        }
                        //项目编号
                        if (pageDomain.getOrderByColumn().equals("operationCode")) {
                            return o2.getOperationCode().compareTo(o1.getOperationCode());
                        }
                        //科技项目经理
                        if (pageDomain.getOrderByColumn().equals("projectManagerName")) {
                            return o2.getProjectManagerName().compareTo(o1.getProjectManagerName());
                        }
                        //产品经理
                        if (pageDomain.getOrderByColumn().equals("productManagerName")) {
                            return o2.getProductManagerName().compareTo(o1.getProductManagerName());
                        }
                        //承建部门
                        if (pageDomain.getOrderByColumn().equals("deptName")) {
                            return o2.getDeptName().compareTo(o1.getDeptName());
                        }
                        //状态
                        if (pageDomain.getOrderByColumn().equals("status")) {
                            return o2.getStatus().compareTo(o1.getStatus());
                        }
                        //系统级别
                        if (pageDomain.getOrderByColumn().equals("sysLevel")) {
                            return o2.getSysLevel().compareTo(o1.getSysLevel());
                        }
                        //系统类型
                        if (pageDomain.getOrderByColumn().equals("sysType")) {
                            return o2.getSysType().compareTo(o1.getSysType());
                        }
                    }
                    return 0;
                }
            });
        }
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(pmsBatchList).getTotal());
        return rspData;
    }

    /**
     * 导出影像批次列表
     */
    @RequiresPermissions("pms:pmsBatch:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsBatch pmsBatch) {
        List<PmsBatch> list = pmsBatchService.selectPmsBatchList(pmsBatch);
        ExcelUtil<PmsBatch> util = new ExcelUtil<PmsBatch>(PmsBatch.class);
        return util.exportExcel(list, "pmsBatch");
    }

    /**
     * 新增影像批次
     */
    @GetMapping("/add")
    public String add(ModelMap map) {
        CmsSystem cmsSystem = cmsSystemService.selectCmsSystemByCode(PmsConstants.PMO_SYSCODE);
        map.put("cmsSystem", cmsSystem);
        map.put("userName", ShiroUtils.getSysUser().getUserName());
        return prefix + "/add";
    }

    /**
     * 新增保存影像批次
     */
    @RequiresPermissions("pms:pmsBatch:add")
    @Log(title = "项目新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsBatch pmsBatch) {
        int num = pmsBatchService.insertPmsBatch(pmsBatch);
        return toAjax(num);
    }

    /**
     * 查询该项目是否可删除
     */
    @PostMapping("/editFlag")
    @ResponseBody
    public AjaxResult editFlag(String id) {
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(Long.valueOf(id));
        String status = pmsBatch.getStatus();
        if ("1".equals(status) || "2".equals(status)) {
            SysUser sysUser = ShiroUtils.getSysUser();
            List<SysRole> roles = sysUser.getRoles();
            if (roles != null && roles.size() > 0) {
                String adminKey = "";
                for (SysRole sysRole : roles) {
                    if (PmsConstants.ADMIN_ROLE_KEY.equals(sysRole.getRoleKey())) {
                        adminKey = sysRole.getRoleKey();
                    }
                }
                // 管理员可修改已结项的项目
                if ("2".equals(status) && PmsConstants.ADMIN_ROLE_KEY.equals(adminKey)) {
                    return new AjaxResult(AjaxResult.Type.SUCCESS, "");
                }
            }
            return new AjaxResult(AjaxResult.Type.ERROR, "已结项或已废弃项目无法修改！");
        }
        return new AjaxResult(AjaxResult.Type.SUCCESS, "");
    }

    /**
     * 修改影像批次
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(id);
        StringBuffer modelListName = new StringBuffer();
        // 获得模型的名称集合
        if (pmsBatch.getModelList() != null && !"".equals(pmsBatch.getModelList())) {
            String[] modelList = Convert.toStrArray(pmsBatch.getModelList());
            for (String modelId : modelList) {
                CmsModel cmsModel = cmsModelService.selectCmsModelById(Long.valueOf(modelId));
                if (cmsModel != null) {
                    if ("".equals(modelListName.toString())) {
                        modelListName.append(cmsModel.getModelName());
                    } else {
                        modelListName.append(",").append(cmsModel.getModelName());
                    }
                }
            }
            pmsBatch.setModelListName(modelListName.toString());
        }
        mmap.put("pmsBatch", pmsBatch);
        return prefix + "/edit";
    }

    /**
     * 修改保存影像批次
     */
    @RequiresPermissions("pms:pmsBatch:edit")
    @Log(title = "项目编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsBatch pmsBatch) {
        pmsBatch.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        pmsBatch.setUpdateTime(new Date());
        //准备项目同步参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tranCode", Constants.PRO_INFO_SYN);
        jsonObject.put("sysCode", PmsConstants.PMO_SYSCODE);
        jsonObject.put("authCode", PmsConstants.PMO_AUTHCODE);
        // 项目编号
        jsonObject.put("operationCode", pmsBatch.getOperationCode());
        // 承建部门
        jsonObject.put("buildDept", pmsBatch.getBuildDept());
        // 归属部门
        jsonObject.put("attriDept", pmsBatch.getAttriDept());
        // 预算编号
        jsonObject.put("budgetId", pmsBatch.getBudgetId());
        // 项目经理
        jsonObject.put("projectManager", pmsBatch.getProjectManager());
        // 产品经理
        jsonObject.put("productManager", pmsBatch.getProductManager());
        // 模型列表
        jsonObject.put("models", pmsBatch.getModelList());
        // 项目名称
        jsonObject.put("projectName", pmsBatch.getProjectName());
        // 操作员编号
        jsonObject.put("operator", ShiroUtils.getSysUser().getUserName());
        //系统级别
        jsonObject.put("sysLevel", pmsBatch.getSysLevel());
        //系统类型
        jsonObject.put("sysType", pmsBatch.getSysType());
        //项目状态
        jsonObject.put("status", pmsBatch.getStatus());
        // 是否关联项目
        jsonObject.put("projectBatch", PmsConstants.PMS_PROJECT_YES);
        //项目信息同步
        try {
            String isSuccess = uploadFiles(jsonObject);
            logger.info("信息同步" + isSuccess);
            return AjaxResult.success("操作成功");
        } catch (Exception e) {
            logger.error("操作失败", e);
            return AjaxResult.error("操作失败");
        }
    }

    /**
     * 删除影像批次
     */
    @RequiresPermissions("pms:pmsBatch:remove")
    @Log(title = "项目删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int flag = pmsBatchService.deletePmsBatchByIds(ids);
        if (flag == -1) {
            return new AjaxResult(AjaxResult.Type.ERROR, "抱歉，所选项目下有文件或影像，无法删除！", null);
        }
        if (flag == -2) {
            return new AjaxResult(AjaxResult.Type.ERROR, "已结项或已废弃项目无法删除！", null);
        }
        return toAjax(flag);
    }


    /**
     * 项目结构树展示页
     */
    @GetMapping("/detail")
    public String detail(String ids, ModelMap mmap) {
        //获取到第一个项目的所有分类
        String[] pmsArray = {Convert.toStrArray(ids)[0]};
        List<Long> dataRoleIds = cmsUserRoleService.selectDataRoleIdsByUserId(ShiroUtils.getSysUser().getUserId());
        List<TreeNode> projectTree = pmsBatchService.getProTreeByIds(pmsArray, dataRoleIds);
        mmap.put("firstProject", projectTree);
        mmap.put("projectName", projectTree.get(1).getName());
        mmap.put("pmsNodeId", projectTree.get(1).getId());
        mmap.put("pmsBatchId", projectTree.get(1).getId().split("_")[2]);
        mmap.put("pmsBatchIds", ids);
        if (projectTree.get(1).getId().split("_").length > 3) {
            mmap.put("modelId", projectTree.get(1).getId().split("_")[3]);
        }
        mmap.put("firstProjectManager", projectTree.get(1).getProjectManager());
        mmap.put("serverAddress", getServerAddress());
        mmap.put("uploadBillIds", cmsBillService.selectBillIdOfUpload());
        mmap.put("pairBillIds", cmsBillService.selectBillIdOfPairing());
        mmap.put("maxUploadSize", maxUploadSize);
        // 如果有管理员角色放入map
        for (SysRole role : ShiroUtils.getSysUser().getRoles()) {
            if (PmsConstants.ADMIN_ROLE_KEY.equals(role.getRoleKey())) {
                mmap.put("currentRole", role.getRoleKey());
            }
            if (role.getRoleName() != null && role.getRoleName().contains("项目经理")) {
                mmap.put("projectManagerRole", ShiroUtils.getUserId());
            }
            if (role.getRoleName() != null && role.getRoleName().contains("产品经理")) {
                mmap.put("productManagerRole", ShiroUtils.getUserId());
            }
        }
        return prefix + "/projectTree";
    }

    /**
     * 下载全部
     */
    @GetMapping("/downloadAll/{id}")
    public String downloadAll(@PathVariable("id") String id, ModelMap mmap) {
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(Long.valueOf(id));
        List<CmsFileImageVO> cmsFileImageVOList = cmsBillService.selectFileByBatchId(pmsBatch.getBatchId());
        mmap.put("cmsFileImageVOList", cmsFileImageVOList);
        mmap.put("listTile", pmsBatch.getProjectName() + "(" + pmsBatch.getOperationCode() + ")");
        mmap.put("listType", "download");
        mmap.put("pmsBatchId", pmsBatch.getId());
        return prefix + "/fileList";
    }

    /**
     * 跳转下载页
     */
    @GetMapping("/toDownload/{id}")
    public String toDownload(@PathVariable("id") String id, ModelMap mmap) {
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(Long.valueOf(id.split("_")[1]));
        CmsBill bill = cmsBillService.selectCmsBillById(Long.valueOf(id.split("_")[0]));
        List<CmsFileImageVO> cmsFileImageVOList = new ArrayList<>();
        getFileImageByBillId(cmsFileImageVOList, bill.getId(), pmsBatch.getBatchId());
        mmap.put("cmsFileImageVOList", cmsFileImageVOList);
        mmap.put("listTile", bill.getBillName());
        mmap.put("pmsBatchId", pmsBatch.getId());
        mmap.put("listType", "download");
        return prefix + "/fileList";
    }

    /**
     * 删除文件
     *
     * @param fileId
     * @return
     */
    @PostMapping("/fileRemove")
    @ResponseBody
    public AjaxResult fileRemove(String fileId) {
        CmsFile file = cmsFileService.selectCmsFileByFileId(Long.valueOf(fileId));
        String fileName = file != null ? file.getFileName() : "";
        Long batchId = file != null ? file.getBatchId() : null;
        Integer billId = file != null ? file.getBillId() : null;
        if (file == null) {
            CmsImage image = cmsImageService.selectCmsImageByImageId(Long.valueOf(fileId));
            fileName = image.getImageName();
            batchId = image.getBatchId();
            billId = image.getBillId();
        }
        List<String> nameList = new ArrayList<>();
        nameList.add(fileName);
        // 通过批次ID，分类ID，名称 删除对应的文件或影像(逻辑删除)
        int count = file != null ? cmsFileService.deleteCmsFileByCondition(batchId, billId, nameList) : cmsImageService.deleteCmsImageByCondition(batchId, billId, nameList);
        return success(count);
    }

    /**
     * 跳转文件历史页
     */
    @GetMapping("/toHistory/{id}/{fileName}")
    public String toHistory(@PathVariable("id") String id, @PathVariable("fileName") String fileName, ModelMap mmap) {
        Integer billId = Integer.valueOf(id.split("_")[0]);
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(Long.valueOf(id.split("_")[1]));
        String[] names = Convert.toStrArray(fileName);
        List<CmsFileImageVO> cmsFileImageVOList = pmsBatchService.selectFileImageHistory(pmsBatch.getBatchId(), billId, names);
        mmap.put("cmsFileImageVOList", cmsFileImageVOList);
        mmap.put("listTile", fileName);
        mmap.put("listType", "history");
        mmap.put("pmsBatchId", pmsBatch.getId());
        return prefix + "/fileList";
    }

    // 查询当前分类以及子分类下的所有文件
    private List<CmsFileImageVO> getFileImageByBillId(List<CmsFileImageVO> fileImageVOList,
                                                      Long billId, Long batchId) {
        CmsBill cmsBill = cmsBillService.selectCmsBillById(billId);
        List<CmsFileImageVO> fileImageVOS = cmsBillService.selectFileImageOfBill(billId, batchId);
        fileImageVOS.stream().forEach(vo -> {
            vo.setBillName(cmsBill.getBillName());
        });
        fileImageVOList.addAll(fileImageVOS);
        if (PmsConstants.LEFT.equals(cmsBill.getLeaf())) {
            return fileImageVOList;
        } else {
            List<String> subIds = cmsBillService.selectIdByParentId(billId);
            if (subIds != null && subIds.size() > 0) {
                for (String subId : subIds) {
                    getFileImageByBillId(fileImageVOList, Long.valueOf(subId), batchId);
                }
            }
        }
        return fileImageVOList;
    }

    /**
     * 加载项目列表树
     */
    @GetMapping("/initTree")
    @ResponseBody
    public List<TreeNode> initTree(@RequestParam(value = "ids") String ids, ModelMap mmap) {
        // 获取到id的数组
        String[] pmsIdArray = Convert.toStrArray(ids);
        List<Long> dataRoleIds = cmsUserRoleService.selectDataRoleIdsByUserId(ShiroUtils.getSysUser().getUserId());
        // 获得该模型下所有的子节点
        List<TreeNode> projectTree = pmsBatchService.getProTreeByIds(pmsIdArray, dataRoleIds);
        System.out.println(projectTree);
        return projectTree;
    }

    /**
     * 点击步骤条事件方法
     */
    @RequiresPermissions("pms:pmsBatch:list")
    @PostMapping("/voList")
    @ResponseBody
    public List<CmsBill> voList(String pmsBatchId, String modelId, String billId, Boolean billFlag) {
        List<Long> dataRoleIds = cmsUserRoleService.selectDataRoleIdsByUserId(ShiroUtils.getSysUser().getUserId());
        return pmsBatchService.selectBillVo(pmsBatchId, modelId, billId, billFlag, dataRoleIds);
    }

    /**
     * 查看文档历史版本列表
     */
    @PostMapping("/historyVersion")
    @ResponseBody
    public Map<String, Object> historyVersion(Long pmsBatchId, Integer billId, String fileImageNames) {
        Map<String, Object> responseMap = new HashMap<>(8);
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(pmsBatchId);
        String[] names = Convert.toStrArray(fileImageNames);
        responseMap.put("fileImageList", pmsBatchService.selectFileImageHistory(pmsBatch.getBatchId(), billId, names));
        return responseMap;
    }

    /**
     * 跳转文件或影像展示页
     */
    @GetMapping("/fileDetail/{id}")
    public String fileDetail(@PathVariable("id") String id, ModelMap mmap) {
        String pmsBatchId = id.split("_")[0]; // 项目id
        String modelId = id.split("_")[1];  // 模型id
        String billId = id.split("_")[2];   // 分类id
        String billName = id.split("_")[3]; // 分类名称
        List<CmsFileImageVO> cmsFileImageVOList = pmsBatchService.selectFileImage(pmsBatchId, modelId, billId);
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(Long.valueOf(pmsBatchId));
        mmap.put("projectManager", pmsBatch.getProjectManager());
        mmap.put("productManager", pmsBatch.getProductManager());
        mmap.put("cmsFileImageVOList", cmsFileImageVOList);
        mmap.put("billId", Long.valueOf(billId));
        mmap.put("billName", billName);
        mmap.put("nodeId", id);
        mmap.put("pmsBatchId", pmsBatchId);
        mmap.put("batchId", pmsBatch.getBatchId());
        mmap.put("modelId", modelId);
        mmap.put("serverAddress", getServerAddress());
        mmap.put("uploadBIllIds", cmsBillService.selectBillIdOfUpload());
        mmap.put("pairBillIds", cmsBillService.selectBillIdOfPairing());
        mmap.put("hisBillIds", cmsBillService.selectBillIdOfHistory());

        // 查询每个角色在分类管理自定义勾选的项目
        Map<Long, List<Long>> roleProMap = new HashMap<>(8);
        // 获得每个角色拥有的按钮 map
        Map<Long, List<SysMenu>> roleMap = new HashMap<>(8);
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (SysRole role : roles) {
            List<SysMenu> menus = sysMenuService.selectMenusByRole(role.getRoleId());
            roleMap.put(role.getRoleId(), menus);
            List<Long> roleProjects = pmsBatchService.selectPIdsByDataRole(role.getRoleId());
            roleProMap.put(role.getRoleId(), roleProjects);
        }
        // 如果有管理员角色放入map
        List<Long> rolePros = new ArrayList<>();
        for (SysRole role : ShiroUtils.getSysUser().getRoles()) {
            if (PmsConstants.ADMIN_ROLE_KEY.equals(role.getRoleKey())) {
                mmap.put("currentRole", role.getRoleKey());
            }
            if (role.getRoleName() != null && role.getRoleName().contains("项目经理")) {
                mmap.put("projectManagerRole", ShiroUtils.getUserId());
                List<Long> projects = roleProMap.get(role.getRoleId());
                if (projects != null) {
                    rolePros.addAll(projects);
                }
            }
            if (role.getRoleName() != null && role.getRoleName().contains("产品经理")) {
                mmap.put("productManagerRole", ShiroUtils.getUserId());
                List<Long> projects = roleProMap.get(role.getRoleId());
                if (projects != null) {
                    rolePros.addAll(projects);
                }
            }
        }
        mmap.put("roleProjects", rolePros);
        // 用户的部门
        SysDept sysDept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());
        // 用户部门的子部门
        List<String> subDept = sysDeptService.selectSubDeptById(sysDept.getDeptId());
        sysDept.setSubDept(subDept);
        // 如果有辅部门
        String auxiliaryDept = ShiroUtils.getSysUser().getAuxiliaryDept();
        List<String> auxiliaryDepts = new ArrayList<>();
        if (!"".equals(auxiliaryDept) && auxiliaryDept != null) {
            auxiliaryDepts = Arrays.asList(Convert.toStrArray(auxiliaryDept));
        }
        CmsBill cmsBill = cmsBillService.selectCmsBillById(Long.valueOf(billId));
        // 获得当前分类所拥有的按钮权限
        cmsBill = pmsBatchService.getButtonOfBill(cmsBill, roles, roleMap, pmsBatch, sysDept, auxiliaryDepts, roleProMap);
        mmap.put("cmsBill", cmsBill);
        // 叶子节点
        if (PmsConstants.LEFT.equals(cmsBill.getLeaf())) {
            // 默认归档数
            int onFileNum = 1;
            if (PmsConstants.DISPLAY_HISTORY.equals(cmsBill.getDisplay())) {
                Map<String, Integer> numMap = pmsBatchService.selectBillMonitorNum(Long.valueOf(pmsBatchId), Long.valueOf(billId));
                // 归档数
                onFileNum = numMap != null ? numMap.get("fileNum") : onFileNum;
            }
            mmap.put("onFileNum", onFileNum);
        }
        mmap.put("maxUploadSize", maxUploadSize);
        return prefix + "/detail";
    }


    /**
     * 上传完后刷新文件列表
     */
    @PostMapping("/refreshFiles")
    @ResponseBody
    public Map<String, Object> refreshFiles(String nodeId) {
        Map<String, Object> resMap = new HashMap<>(8);
        String pmsbatchId = nodeId.split("_")[0]; // 项目id
        String modelId = nodeId.split("_")[1];  // 模型id
        String billId = nodeId.split("_")[2];   // 分类id
        List<CmsFileImageVO> cmsFileImageVOList = pmsBatchService.selectFileImage(pmsbatchId, modelId, billId);
        resMap.put("newFileList", cmsFileImageVOList);
        return resMap;
    }

    /**
     * 跳转分类选择页
     */
    @GetMapping("/billSelect")
    public String billSelect(String pmsBatchId, String billId, String billIds, String fileImageNames, ModelMap mmap) {
        mmap.put("pmsBatchId", pmsBatchId);
        mmap.put("billId", billId);
        mmap.put("billIds", billIds);
        mmap.put("fileImageNames", fileImageNames);
        mmap.put("pairBillIds", cmsBillService.selectBillIdOfPairing());
        return prefix + "/billSelect";
    }

    /**
     * 跳转分类选择页
     */
    @PostMapping("/choiceBill")
    @ResponseBody
    public AjaxResult choiceBill(Long pmsBatchId, String billIds, String fileImageNames,
                                 Integer targetBillId, String trg) {
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(pmsBatchId);
        String[] names = Convert.toStrArray(fileImageNames);
        // 将选择的文件的分类更新成目标分类
        int num = pmsBatchService.updateFileImageBill(pmsBatch.getBatchId(), Convert.toIntArray(billIds), names, targetBillId, trg);
        return num > 0 ? success() : error();
    }

    /**
     * 加载分类选择结构树
     */
    @GetMapping("/initBillSelect")
    @ResponseBody
    public List<TreeNode> initBillSelect(@RequestParam(value = "pmsBatchId", required = false) String pmsBatchId) {
        // 获取到id的数组
        String[] pmsIdArray = Convert.toStrArray(pmsBatchId);
        List<Long> dataRoleIds = cmsUserRoleService.selectDataRoleIdsByUserId(ShiroUtils.getSysUser().getUserId());
        // 获得该模型下所有的子节点
        List<TreeNode> projectTree = pmsBatchService.getProBillNode(pmsIdArray[0], dataRoleIds);
        return projectTree;
    }

    /**
     * 获得新增批次所需的参数
     */
    @PostMapping("/getAddPmsParams")
    @ResponseBody
    public Map<String, Object> getAddPmsParams(PmsBatch pmsBatch) {
        Map<String, Object> resMap = new HashMap<>(8);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tranCode", Constants.PRO_INFO_SYN);
        jsonObject.put("sysCode", PmsConstants.PMO_SYSCODE);
        CmsSystem cmsSystem = cmsSystemService.selectCmsSystemByCode(PmsConstants.PMO_SYSCODE);
        if (cmsSystem == null) {
            throw new BusinessException("此系统尚未接入！");
        }
        jsonObject.put("authCode", cmsSystem.getAuthentInfo());
        // 项目编号
        jsonObject.put("operationCode", pmsBatch.getOperationCode());
        // 承建部门
        jsonObject.put("buildDept", pmsBatch.getBuildDept());
        // 归属部门
        jsonObject.put("attriDept", pmsBatch.getAttriDept());
        // 预算编号
        jsonObject.put("budgetId", pmsBatch.getBudgetId());
        // 项目经理
        jsonObject.put("projectManager", pmsBatch.getProjectManager());
        // 产品经理
        jsonObject.put("productManager", pmsBatch.getProductManager());
        // 模型列表
        jsonObject.put("models", pmsBatch.getModelList());
        // 项目名称
        jsonObject.put("projectName", pmsBatch.getProjectName());
        // 操作员编号
        jsonObject.put("operator", ShiroUtils.getSysUser().getUserName());
        // 系统级别
        jsonObject.put("sysLevel", pmsBatch.getSysLevel());
        // 系统类型
        jsonObject.put("sysType", pmsBatch.getSysType());
        // 项目状态
        jsonObject.put("status", pmsBatch.getStatus());
        // 是否关联项目
        jsonObject.put("projectBatch", PmsConstants.PMS_PROJECT_YES);
        resMap.put("jsonParam", jsonObject);
        return resMap;
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/select/userList")
    @ResponseBody
    public TableDataInfo userList(SysUser sysUser) {
        startPage();
        List<SysUser> list = userService.selectUsersBySysUser(sysUser);
        return getDataTable(list);
    }

    /**
     * 跳转选择用户页面
     *
     * @return
     */
    @GetMapping("/selectUser/{inputUserId}")
    public String selectUser(@PathVariable("inputUserId") String inputUserId, ModelMap mmap) {
        mmap.put("userId", ShiroUtils.getUserId());
        mmap.put("inputUserId", inputUserId);
        return prefix + "/selectUser";
    }

    /**
     * 查询模型列表
     */
    @PostMapping("/select/modelList")
    @ResponseBody
    public TableDataInfo modelList(CmsModel cmsModel) {
        startPage();
        List<CmsModel> list = cmsModelService.selectCmsModelList(cmsModel);
        return getDataTable(list);
    }

    /**
     * 模型选择页
     *
     * @return
     */
    @GetMapping("/selectModel/{modelInputId}")
    public String selectModel(@PathVariable("modelInputId") String modelInputId, ModelMap mmap) {
        mmap.put("modelInputId", modelInputId);
        return prefix + "/selectModel";
    }

    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") String deptId, ModelMap mmap) {
        mmap.put("inputDeptId", deptId);
        return prefix + "/deptTree";
    }

    /**
     * 返回页面
     *
     * @return
     */
    @GetMapping("/backToPage")
    public String backToPage(String backPage, String param, ModelMap map) {
        map.put("backPage", backPage);
        map.put("param", param);
        return prefix + "/" + backPage;
    }


    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        return deptService.selectDeptTree(new SysDept());
    }

    /**
     * 获得模型名称
     */
    @PostMapping("/getModelName")
    @ResponseBody
    public AjaxResult getModelName(String stringModel) {
        StringBuffer modelListName = new StringBuffer();
        // 获得模型的名称集合
        String[] modelList = Convert.toStrArray(stringModel);
        for (String modelId : modelList) {
            CmsModel cmsModel = cmsModelService.selectCmsModelById(Long.valueOf(modelId));
            if ("".equals(modelListName.toString())) {
                modelListName.append(cmsModel.getModelName());
            } else {
                modelListName.append(",").append(cmsModel.getModelName());
            }
        }
        return success(modelListName);
    }

    /**
     * 获取系统环境的ip
     *
     * @return
     * @author yinrui
     * @date 2019-10-31
     */
    private String getServerIp() {
        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_FILETRANSIP) : SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_IP);
    }

    // 获得服务器路径
    private String getServerAddress() {
        return "http://" + getServerIp() + ":" + SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PORT) + "/cms";
    }

    /**
     * 项目批量导入
     */
    @PostMapping("/importProject")
    @ResponseBody
    public AjaxResult importProject(MultipartFile file) throws Exception {
        String validMsg = "";
        try {
            ExcelUtil<PmsBatch> util = new ExcelUtil<>(PmsBatch.class);
            List<PmsBatch> pmsBatchList = util.importExcel(file.getInputStream());
            for (int k = 0; k < pmsBatchList.size(); k++) {
                PmsBatch pmsBatch = pmsBatchList.get(k);
                int i = pmsBatchList.indexOf(pmsBatch);
                if (pmsBatch.getProjectName() == null || "".equals(pmsBatch.getProjectName())) {
                    validMsg += "[" + (i + 1) + "]行 ";
                    continue;
                }
                if (pmsBatch.getOperationCode() == null || "".equals(pmsBatch.getOperationCode())) {
                    validMsg += "[" + (i + 1) + "]行 ";
                    continue;
                }
//                else {
//                    //校验项目编号唯一性
//                    String result = pmsBatchService.checkOperationCodeUnique(pmsBatch);
//                    if ("1".equals(result)) {
//                        return AjaxResult.error("第" + "[" + (i + 1) + "]行 " + "项目编号已存在，导入失败");
//                    }
//                }
//                if (pmsBatch.getBudgetId() == null || "".equals(pmsBatch.getBudgetId())) {
//                    validMsg += "[" + (i + 1) + "]行 ";
//                    continue;
//                }
//                else {
//                    //校验预算编号唯一性
//                    String result = pmsBatchService.checkBudgetIdUnique(pmsBatch);
//                    if ("1".equals(result)) {
//                        return AjaxResult.error("第" + "[" + (i + 1) + "]行 " + "预算编号已存在，导入失败");
//                    }
//                }
//                if (pmsBatch.getProjectManager() == null || "".equals(pmsBatch.getProjectManager())) {
//                    validMsg += "[" + (i + 1) + "]行 ";
//                    continue;
//                }
//                if (pmsBatch.getProductManager() == null || "".equals(pmsBatch.getProductManager())) {
//                    validMsg += "[" + (i + 1) + "]行 ";
//                    continue;
//                }
//                if (pmsBatch.getAttriDept() == null || "".equals(pmsBatch.getAttriDept())) {
//                    validMsg += "[" + (i + 1) + "]行 ";
//                    continue;
//                }
//                if (pmsBatch.getModelList() == null || "".equals(pmsBatch.getModelList())) {
//                    validMsg += "[" + (i + 1) + "]行 ";
//                    continue;
//                } else {
                //校验模型选择中是否含有中文
                if (pmsBatch.getModelList() != null && !"".equals(pmsBatch.getModelList())) {
                    Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                    Matcher m = p.matcher(pmsBatch.getModelList());
                    if (m.find()) {
                        return AjaxResult.error("第" + "[" + (i + 1) + "]行 " + "模型选择中只能填写模型编号，不能填写汉字");
                    }
                }

//                    else {
//                        //查询是否有该模型
//                        String[] modelList = pmsBatch.getModelList().split(",");
//                        for (String modelId : modelList) {
//                            CmsModel cmsModel = cmsModelService.selectCmsModelById(Long.valueOf(modelId));
//                            if (StringUtils.isNull(cmsModel)) {
//                                return AjaxResult.error("第" + "[" + (i + 1) + "]行 " + "模型不存在，导入失败");
//                            }
//                        }
//                    }
//                }
//                if (pmsBatch.getBuildDept() == null || "".equals(pmsBatch.getBuildDept())) {
//                    validMsg += "[" + (i + 1) + "]行 ";
//                    continue;
//                }
//                if (pmsBatch.getSysLevel() == null || "".equals(pmsBatch.getSysLevel())) {
//                    validMsg += "[" + (i + 1) + "]行 ";
//                    continue;
//                }
//                if (pmsBatch.getSysType() == null || "".equals(pmsBatch.getSysType())) {
//                    validMsg += "[" + (i + 1) + "]行 ";
//                    continue;
//                }
//                if (pmsBatch.getStatus() == null || "".equals(pmsBatch.getStatus())) {
//                    validMsg += "[" + (i + 1) + "]行 ";
//                    continue;
//                }


                //根据归属部门名称查询归属部门编号
                SysDept attriDept = sysDeptService.selectDeptByDeptName(pmsBatch.getAttriDept());
                if (attriDept != null) {
                    pmsBatch.setAttriDept(attriDept.getDeptId().toString());
                } else {
                    pmsBatch.setAttriDept(PmsConstants.PMS_UNKNOWN_DEPT);//0000为未知部门编码
                }
                //根据承建部门名称查询承建部门编号
                SysDept buildDept = sysDeptService.selectDeptByDeptName(pmsBatch.getBuildDept());
                if (buildDept != null) {
                    pmsBatch.setBuildDept(buildDept.getDeptId().toString());
                } else {
                    pmsBatch.setBuildDept(PmsConstants.PMS_UNKNOWN_DEPT);
                }
                //查询科技项目经理编码
                String[] prjectManagers = pmsBatch.getProjectManager().split(",");
                for (String loginName : prjectManagers) {
                    SysUser projectManager = sysUserService.selectUserByLoginName(loginName);
                    if (projectManager == null) {
                        throw new BusinessException("科技项目经理查无此人:" + loginName);
                    }
                }
//                pmsBatch.setProjectManager(projectManager.getUserId().toString());
                //查询产品项目经理编码
                String[] productManagers = pmsBatch.getProductManager().split(",");
                for (String loginName : productManagers) {
                    SysUser productManager = sysUserService.selectUserByLoginName(loginName);
                    if (productManager == null) {
                        throw new BusinessException("产品项目经理查无此人:" + loginName);
                    }
                }
//                pmsBatch.setProductManager(productManager.getUserId().toString());
                //创建人
                pmsBatch.setCreateBy(ShiroUtils.getSysUser().getUserName());
                String[] modelList = pmsBatch.getModelList().split(",");
                String models = "";
                for (int j = 0; j < modelList.length; j++) {
                    CmsModel cmsModel = cmsModelService.selectCmsModelByCode(modelList[j].trim());
                    if (cmsModel != null) {
                        if ("".equals(models)) {
                            models += cmsModel.getId();
                        } else {
                            models += "," + cmsModel.getId();
                        }
                    }
                }
                if (!"".equals(models)) {
                    pmsBatch.setModelList(models);
                }
                //获得新增批次所需参数
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("tranCode", Constants.PRO_INFO_SYN);
                jsonObject.put("sysCode", PmsConstants.PMO_SYSCODE);
                jsonObject.put("authCode", PmsConstants.PMO_AUTHCODE);
                // 项目编号
                jsonObject.put("operationCode", pmsBatch.getOperationCode());
                // 承建部门
                jsonObject.put("buildDept", pmsBatch.getBuildDept());
                // 归属部门
                jsonObject.put("attriDept", pmsBatch.getAttriDept());
                // 预算编号
                jsonObject.put("budgetId", pmsBatch.getBudgetId());
                // 项目经理
                jsonObject.put("projectManager", pmsBatch.getProjectManager());
                // 产品经理
                jsonObject.put("productManager", pmsBatch.getProductManager());
                // 模型列表
                jsonObject.put("models", pmsBatch.getModelList());
                // 项目名称
                jsonObject.put("projectName", pmsBatch.getProjectName());
                // 操作员编号
                jsonObject.put("operator", ShiroUtils.getSysUser().getUserName());
                //系统级别
                jsonObject.put("sysLevel", pmsBatch.getSysLevel());
                //系统类型
                jsonObject.put("sysType", pmsBatch.getSysType());
                //项目状态
                jsonObject.put("status", pmsBatch.getStatus());
                // 是否关联项目
                jsonObject.put("projectBatch", PmsConstants.PMS_PROJECT_YES);
                //项目信息同步
                String isSuccess = uploadFiles(jsonObject);
                logger.info("信息同步" + isSuccess);
            }

            //存在空值
            if (validMsg.length() > 0) {
                return AjaxResult.error("第" + validMsg + "存在空值，请重新导入该行数据");
            }
            return AjaxResult.success("导入成功");
        } catch (BusinessException e) {
            logger.error("导入失败", e);
            return AjaxResult.error("导入失败," + e.getMessage());

        } catch (Exception e) {
            logger.error("导入失败", e);
            return AjaxResult.error("请下载使用模板");
        }

    }

    /**
     * 项目信息同步
     */
    public String uploadFiles(JSONObject jsonObject) {
        if (jsonObject.get(CmsConstants.OPERATIONCODE) == null || "".equals(jsonObject.get(CmsConstants.OPERATIONCODE))) {
            JSONObject resultObject = new JSONObject(true);
            resultObject.put("totalResultCode", ResultCode.PROJECT_MISSING.code());
            resultObject.put("totalResultMsg", ResultCode.PROJECT_MISSING.msg());
            return "fail";
        }
        httpService.projectInfoSyncho(jsonObject);
        JSONObject resultObject = new JSONObject(true);
        resultObject.put("totalResultCode", ResultCode.SUCCESS.code());
        resultObject.put("totalResultMsg", ResultCode.SUCCESS.msg());
        return "success";
    }

    /**
     * 跳转至分类树
     */
    @GetMapping("/cmsBillTree")
    public String cmsBillTree() {
        return prefix + "/cmsBillTree";
    }

    /**
     * 获得全部分类(除叶子节点)
     */
    @GetMapping("/cmsBillTreeData")
    @ResponseBody
    public List<Ztree> cmsBillTreeData() {
        List<Ztree> ztreeList = pmsBatchService.getAllCmsBill();
        return ztreeList;
    }

    /**
     * 导出模板
     **/
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<PmsBatch> util = new ExcelUtil<PmsBatch>(PmsBatch.class);
        return util.importTemplateExcel("项目模板");
    }


    /**
     * getNewFileImage
     **/
    @PostMapping("/getNewFileImage")
    @ResponseBody
    public AjaxResult getNewFileImage(String pmsBatchId, Long billId) {
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(Long.valueOf(pmsBatchId));
        CmsBill fileImage = pmsBatchService.selectNewFileImage(pmsBatch.getBatchId(), billId);
        return AjaxResult.success(fileImage);
    }

    /**
     * 校验项目名唯一性
     *
     * @param pmsBatch
     * @return
     */
    @PostMapping("/checkProjectNameUnique")
    @ResponseBody
    public String checkProjectNameUnique(PmsBatch pmsBatch) {
        return pmsBatchService.checkProjectNameUnique(pmsBatch);
    }

    /**
     * 校验项目编号唯一性
     *
     * @param pmsBatch
     * @return
     */
    @PostMapping("/checkOperationCodeUnique")
    @ResponseBody
    public String checkOperationCodeUnique(PmsBatch pmsBatch) {
        return pmsBatchService.checkOperationCodeUnique(pmsBatch);
    }

    /**
     * 校验预算编号唯一性
     *
     * @param pmsBatch
     * @return
     */
    @PostMapping("/checkBudgetIdUnique")
    @ResponseBody
    public String checkBudgetIdUnique(PmsBatch pmsBatch) {
        return pmsBatchService.checkBudgetIdUnique(pmsBatch);
    }
}
