package net.northking.iacmp.ams.web.controller.amsarcreport;


import com.github.pagehelper.PageHelper;
import com.netflix.discovery.converters.Auto;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.common.bean.dto.ams.AmsArchivesDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.PageDomain;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.core.page.TableSupport;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.framework.util.UploadUtil;
import net.northking.iacmp.server.service.*;

import net.northking.iacmp.system.domain.SysRole;

import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysRoleService;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import net.northking.iacmp.utils.sql.SqlUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 档案登记 信息操作处理
 *
 * @author ty
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/amsArcReportcontroller/amsArchives")
public class AmsArchivesReportController extends BaseController {
    private static final String AMS_ARCHIVES = "amsArchives";
    private String prefix = "amsArcReportcontroller/amsArchives";
    private static final String AMS_BATCH = "amsBatch";

    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private IAmsCollectionService amsCollectionService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private IAmsBorrowInfoService amsBorrowInfoService;
    @Autowired
    private IAmsBillService amsBillService;
    @Autowired
    private IImImageService imageService;
    @Autowired
    private IImFileService imFileService;

    @RequiresPermissions("amsArcReportcontroller:amsArchives:view")
    @GetMapping()
    public String amsArchives(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        List listrole = new ArrayList();
        for (SysRole o : roleList) {
            listrole.add(o.getRoleId());
        }
        String roleId = Collections.max(listrole).toString();
        mmap.put("roleId", roleId);
        mmap.put("userName", sysUser.getUserName());
        return prefix + "/amsArchives";
    }

    /**
     * 查询档案登记列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsArchives:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsArchivesVO amsArchives) {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<AmsArchivesDTO> list;
        //获取该用户所有角色信息
        List<SysRole> roleList = sysUser.getRoles();
        List listrole = new ArrayList();
        for (SysRole o : roleList) {
            listrole.add(o.getRoleId());
        }
        listrole.remove(23L);
        String roleId = Collections.max(listrole).toString();
        String deptId = ShiroUtils.getSysUser().getDeptId().toString();
        String loginName = ShiroUtils.getSysUser().getUserName();
        String respOpName = sysUser.getUserName();
        //查询当前档案类型下全部子节点
        List<String> treeNodeList = new ArrayList<>();
        if (!"".equals(amsArchives.getArcBillCode()) && amsArchives.getArcBillCode() != null) {
            treeNodeList = amsBillService.allSonTreeNode(amsArchives.getArcBillCode());
        }
        startPage();

        if ("1".equals(roleId) || "4".equals(roleId)) {
            list = amsArchivesService.selectAmsArchivesListAllForAdmin(amsArchives, treeNodeList);
        } else {
            List<String> deptIds = new ArrayList<>();
            //部门
            deptIds.add(deptId);
            //副部门
            String auxDeptId = ShiroUtils.getSysUser().getAuxiliaryDept();
            if (null != auxDeptId && !"".equals(auxDeptId)) {
                String[] ids = auxDeptId.split(",");
                if (ids.length > 0) {
                    for (int i = 0; i < ids.length; i++) {
                        deptIds.add(ids[i]);
                    }
                }
            }
            amsArchives.setRespOpName(respOpName);
            //部门经理
            if ("14".equals(roleId)) {
                list = amsArchivesService.selectAmsArchivesBydeptId(deptIds, amsArchives, treeNodeList);
            } else { //普通用户/部门档案管理员
                list = amsArchivesService.selectAmsArchivesListNew(deptId, amsArchives, loginName, treeNodeList);
            }
        }
//        amsArchivesList=list;
        return getDataTable(list);
    }


    /**
     * 首页查询共享文档
     */
//	@RequiresPermissions("amsArcReportcontroller:amsArchives:list")
    @PostMapping("/mainList")
    @ResponseBody
    public TableDataInfo mainList(AmsArchivesVO amsArchives) {
        //查询当前档案类型下全部子节点
        List<String> treeNodeList = new ArrayList<>();
        if (!"".equals(amsArchives.getArcBillCode()) && amsArchives.getArcBillCode() != null) {
            treeNodeList = amsBillService.allSonTreeNode(amsArchives.getArcBillCode());
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = 1;
        Integer pageSize = 10;
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
        SysUser sysUser = ShiroUtils.getSysUser();
        List<AmsArchivesDTO> list;
        //获取该用户所有角色信息
        List<SysRole> roleList = sysUser.getRoles();
        List listrole = new ArrayList();
        for (SysRole o : roleList) {
            listrole.add(o.getRoleId());
        }
        listrole.remove(23L);
        String roleId = Collections.max(listrole).toString();
        String deptId = ShiroUtils.getSysUser().getDeptId().toString();
        String loginName = ShiroUtils.getSysUser().getUserName();
        String respOpName = sysUser.getUserName();
        if ("1".equals(roleId) || "4".equals(roleId)) {
            list = amsArchivesService.selectAmsArchivesListAllForAdmin(amsArchives, treeNodeList);
        } else {
            List<String> deptIds = new ArrayList<>();
            //部门
            deptIds.add(deptId);
            //副部门
            String auxDeptId = ShiroUtils.getSysUser().getAuxiliaryDept();
            if (null != auxDeptId && !"".equals(auxDeptId)) {
                String[] ids = auxDeptId.split(",");
                if (ids.length > 0) {
                    for (int i = 0; i < ids.length; i++) {
                        deptIds.add(ids[i]);
                    }
                }
            }

            amsArchives.setRespOpName(respOpName);
            //部门经理
            if ("14".equals(roleId)) {
                list = amsArchivesService.selectAmsArchivesBydeptId(deptIds, amsArchives, treeNodeList);
            } else { //普通用户/部门档案管理员
                list = amsArchivesService.selectAmsArchivesListNew(deptId, amsArchives, loginName, treeNodeList);
            }
        }

        return getDataTable(list);
    }


    /**
     * 导出档案登记列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsArchives:export")
    @PostMapping("/exportById")
    @ResponseBody
//	public AjaxResult export(AmsArchivesVO amsArchives)
    public AjaxResult exportById(String ids) {
        List<AmsArchives> list = amsArchivesService.selectAmsArchivesByIds(ids);
        ExcelUtil<AmsArchives> util = new ExcelUtil<>(AmsArchives.class);
        return util.exportExcel(list, AMS_ARCHIVES);
    }

    @RequiresPermissions("amsArcReportcontroller:amsArchives:export")
    @PostMapping("/exportAll")
    @ResponseBody
    public AjaxResult exportAll(AmsArchivesVO amsArchives) {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<AmsArchivesDTO> list;
        //获取该用户所有角色信息
        List<SysRole> roleList = sysUser.getRoles();
        List listrole = new ArrayList();
        for (SysRole o : roleList) {
            listrole.add(o.getRoleId());
        }
        listrole.remove(23L);
        String roleId = Collections.max(listrole).toString();
        String deptId = ShiroUtils.getSysUser().getDeptId().toString();
        String loginName = ShiroUtils.getSysUser().getUserName();
        String respOpName = sysUser.getUserName();
        //查询当前档案类型下全部子节点
        List<String> treeNodeList = new ArrayList<>();
        if (!"".equals(amsArchives.getArcBillCode()) && amsArchives.getArcBillCode() != null) {
            treeNodeList = amsBillService.allSonTreeNode(amsArchives.getArcBillCode());
        }
        startPage();
        if ("1".equals(roleId) || "4".equals(roleId)) {
            list = amsArchivesService.selectAmsArchivesListAllForAdmin(amsArchives, treeNodeList);
        } else {
            List<String> deptIds = new ArrayList<>();
            //部门
            deptIds.add(deptId);
            //副部门
            String auxDeptId = ShiroUtils.getSysUser().getAuxiliaryDept();
            if (null != auxDeptId && !"".equals(auxDeptId)) {
                String[] ids = auxDeptId.split(",");
                if (ids.length > 0) {
                    for (int i = 0; i < ids.length; i++) {
                        deptIds.add(ids[i]);
                    }
                }
            }

            amsArchives.setRespOpName(respOpName);
            //部门经理
            if ("14".equals(roleId)) {
                list = amsArchivesService.selectAmsArchivesBydeptId(deptIds, amsArchives, treeNodeList);
            } else { //普通用户/部门档案管理员
                list = amsArchivesService.selectAmsArchivesListNew(deptId, amsArchives, loginName, treeNodeList);
            }
        }

        ExcelUtil<AmsArchivesDTO> util = new ExcelUtil<>(AmsArchivesDTO.class);
        return util.exportExcel(list, AMS_ARCHIVES);
    }

    /**
     * 新增档案登记
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案登记
     */
    @RequiresPermissions("amsArcReportcontroller:amsArchives:add")
    @Log(title = "档案登记", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsArchives amsArchives) {
        return toAjax(amsArchivesService.insertAmsArchives(amsArchives));
    }

    /**
     * 申请利用
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("amsArcReportcontroller:amsArchives:view")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(id);
        SysUser sysUser = ShiroUtils.getSysUser();
        //获取该用户所有角色信息
        List<SysRole> roleList = sysUser.getRoles();
        List list = new ArrayList();
        for (SysRole o : roleList) {
            list.add(o.getRoleId());
        }
        list.remove(23L);
        String roleId = Collections.max(list).toString();
        //档案所属部门号
        String deptId = amsArchives.getOpDepNo();
        //用户部门号
        String userDeptId = sysUser.getDeptId().toString();
        List<SysUser> user;
        if ("4".equals(roleId) || "1".equals(roleId)) {
            //判断档案是部门档案还是行档案
            String arcNo = amsArchives.getArcNo();
            AmsBatch amsBatch = amsBatchService.selectAmsBatchByarcId(arcNo);
            String hasMoveBank = amsBatch.getArcHasMoveBank();
            if ("0".equals(hasMoveBank)) {
                //部门档案
                //如果是行档案管理员或是admin借阅部门档案就提交档案所属部门部门经理
                user = sysUserService.selectNextUserByroleId("14", deptId);//档案所属部门经理
                //其他有权管理该部门的部门经理
                List<SysUser> managers = sysUserService.selectNextUserByrole("14");//全部部门经理
                for (SysUser manager : managers) {
                    String auxiliaryDept = manager.getAuxiliaryDept();
                    if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//该部门经理有辅部门
                        //查询辅部门中是否有当前档案所属部门
                        String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                        for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                            if (deptId.equals(auxiliaryDeptArr[i])) {
                                user.add(manager);
                            }
                        }
                    }
                }

            } else {
                //如果是行档案管理员或是admin借阅行档案就提交至行档案管理员
                user = sysUserService.selectNextUserByrole("4");
            }

        } else {
            //普通用户，部门档案管理员，部门经理无论申请是否是本部门的档案都先提交至本部门经理
            user = sysUserService.selectNextUserByroleId("14", userDeptId);
            //其他有权管理该部门的部门经理
            List<SysUser> managers = sysUserService.selectNextUserByrole("14");//全部部门经理
            for (SysUser manager : managers) {
                String auxiliaryDept = manager.getAuxiliaryDept();
                if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//该部门经理有辅部门
                    //查询辅部门中是否有当前档案所属部门
                    String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                    for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                        if (userDeptId.equals(auxiliaryDeptArr[i])) {
                            user.add(manager);
                        }
                    }
                }
            }
        }
        //申请利用的档案编号
        String arcNo = amsArchives.getArcNo();
        AmsBorrowInfo amsBorrowInfo = new AmsBorrowInfo();
        amsBorrowInfo.setArcNo(arcNo);
        //查询该档案全部借阅信息(借阅中)
        List<AmsBorrowInfo> borrowList = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo);
        //该档案的实体档案是否已借阅出去
        String entityBorrowed = "false";
        if (!borrowList.isEmpty()) {
            for (int i = 0; i < borrowList.size(); i++) {
                String status = borrowList.get(i).getStatus();
                if ("1".equals(status) || "2".equals(status)) {
                    //借阅或待审批中
                    String borType = borrowList.get(i).getBorType();
                    if ("20".equals(borType)) {
                        //该档案的实体档案已经借出
                        entityBorrowed = "true";
                        break;
                    }
                }
            }
        }
        mmap.put("entityBorrowed", entityBorrowed);
        mmap.put(AMS_ARCHIVES, amsArchives);
        mmap.put("user", user);
        mmap.put("sysUser", sysUser);
        return prefix + "/applyBorrow";
    }

    /**
     * 修改保存档案登记
     */
    @RequiresPermissions("amsArcReportcontroller:amsArchives:edit")
    @Log(title = "档案登记", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsArchives amsArchives) {
        return toAjax(amsArchivesService.updateAmsArchives(amsArchives));
    }

    /**
     * 删除档案登记
     */
    @RequiresPermissions("amsArcReportcontroller:amsArchives:remove")
    @Log(title = "档案登记", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsArchivesService.deleteAmsArchivesByIds(ids));
    }

    @RequiresPermissions("amsArcReportcontroller:amsArchives:add")
    @PostMapping("/addcollection")
    @ResponseBody
    public AjaxResult addcollection(String ids) {
        SysUser sysUser = ShiroUtils.getSysUser();
        String[] idArr = ids.split(",");
        AmsArchives amsArchives;
        //是否添加成功
        boolean isSuccess = false;
        for (int i = 0; i < idArr.length; i++) {
            amsArchives = amsArchivesService.selectAmsArchivesById(idArr[i]);
            AmsCollection asmCollection = new AmsCollection();
            asmCollection.setName(amsArchives.getName());
            asmCollection.setArchivesId(amsArchives.getId());
            asmCollection.setSearcher(sysUser.getLoginName());
            asmCollection.setFLAG(0);
            asmCollection.setStatus(1);
            //添加收藏前先查询是否已经收藏
            List list = amsCollectionService.selectAmsCollectionList(asmCollection);
            if (!list.isEmpty()) {
                //该档案已经添加收藏，不需要再次收藏
                return toAjax(1);
            }
            int result = amsCollectionService.insertAmsCollection(asmCollection);
            if (result == 1) {
                isSuccess = true;
            }
        }
        if (isSuccess) {
            return toAjax(1);
        } else {
            return toAjax(0);
        }

    }

    /**
     * 档案详情
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("amsArcReportcontroller:amsArchives:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap) {
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(id);
        AmsBatch amsBatch = amsBatchService.selectAmsBatchByarcId(amsArchives.getArcNo());
        mmap.put("amsBatch", amsBatch);
        mmap.put(AMS_ARCHIVES, amsArchives);
        return prefix + "/view";
    }

    @GetMapping("/batchdetail/{id}")
    @RequiresPermissions("amsArcReportcontroller:amsArchives:detail")
    public String batchdetail(@PathVariable("id") String id, ModelMap mmap) {
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(id);
        AmsBatch amsBatch = amsBatchService.selectAmsBatchByarcId(amsArchives.getArcNo());
        //二级类目编号
        String arcBillDeptCode = amsBatch.getArcBillDeptCode();
        AmsBill amsBill = amsBillService.selectAmsBillById(arcBillDeptCode);
        String mouldStr = amsBill.getMouldStr();
        mmap.put("mouldStr", mouldStr);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/detail";
    }

    /**
     * 调转到显示部门树页面
     *
     * @return
     */
    @GetMapping("/deptTree")
    public String deptTree() {
        return prefix + "/tree";
    }

    /**
     * 调转到显示档案类型树页面
     *
     * @return
     */
    @GetMapping("/arcBillTree")
    public String arcBillTree() {
        return prefix + "/arcBillTree";
    }

    /**
     * 制度发文一键下载
     */
    @RequestMapping("/downZip")
    @ResponseBody
    public String downZip(String arcName, String arcLevel, String valPeriod, String arcBillCode, String opDepNo, HttpServletResponse response) throws Exception {
        AmsArchivesVO amsArchivesVO = new AmsArchivesVO();
        amsArchivesVO.setName(arcName);
        amsArchivesVO.setArcLevel(arcLevel);
        amsArchivesVO.setValPeriod(valPeriod);
        amsArchivesVO.setArcBillCode(arcBillCode);
        amsArchivesVO.setOpDepNo(opDepNo);
        SysUser sysUser = ShiroUtils.getSysUser();
        List<AmsArchivesDTO> list;
        //获取该用户所有角色信息
        List<SysRole> roleList = sysUser.getRoles();
        List listrole = new ArrayList();
        for (SysRole o : roleList) {
            listrole.add(o.getRoleId());
        }
        listrole.remove(23L);
        String roleId = Collections.max(listrole).toString();
        String deptId = ShiroUtils.getSysUser().getDeptId().toString();
        String loginName = ShiroUtils.getSysUser().getUserName();
        String respOpName = sysUser.getUserName();
        //查询当前档案类型下全部子节点
        List<String> treeNodeList = new ArrayList<>();
        if (!"".equals(amsArchivesVO.getArcBillCode()) && amsArchivesVO.getArcBillCode() != null) {
            treeNodeList = amsBillService.allSonTreeNode(amsArchivesVO.getArcBillCode());
        }
        startPage();
        if ("1".equals(roleId) || "4".equals(roleId)) {
            list = amsArchivesService.selectAmsArchivesListAllForAdmin(amsArchivesVO, treeNodeList);
        } else {
            List<String> deptIds = new ArrayList<>();
            //部门
            deptIds.add(deptId);
            //副部门
            String auxDeptId = ShiroUtils.getSysUser().getAuxiliaryDept();
            if (null != auxDeptId && !"".equals(auxDeptId)) {
                String[] ids = auxDeptId.split(",");
                if (ids.length > 0) {
                    for (int i = 0; i < ids.length; i++) {
                        deptIds.add(ids[i]);
                    }
                }
            }

            amsArchivesVO.setRespOpName(respOpName);
            //部门经理
            if ("14".equals(roleId)) {
                list = amsArchivesService.selectAmsArchivesBydeptId(deptIds, amsArchivesVO, treeNodeList);
            } else { //普通用户/部门档案管理员
                list = amsArchivesService.selectAmsArchivesListNew(deptId, amsArchivesVO, loginName, treeNodeList);
            }
        }
        if (list.size() > 0) {
            String batchId = "";
            String fileIds = "";
            Integer fileNum = 0;
            List<ImImage> images = new ArrayList<>();
            List<ImFile> files = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                batchId = list.get(i).getBatchNo();
                //查询该batchId下全部影像和文件
                List<ImImage> imageList = imageService.selectImImagesByBatchId(batchId);
                List<ImFile> fileList = imFileService.selectImFilesByBatchId(batchId);
                if (imageList.size() > 0) {
                    for (int j = 0; j < imageList.size(); j++) {
                        fileNum++;
                        images.add(imageList.get(j));
                    }
                }
                if (fileList.size() > 0) {
                    for (int j = 0; j < fileList.size(); j++) {
                        fileNum++;
                        files.add(fileList.get(j));
                    }
                }
            }
            //开始批量下载
//			BufferedInputStream bis = null;
            /**
             * 临时zip名称
             */
            String zipName = "files.zip";
//			FileOutputStream fis = new FileOutputStream(getServerPath() + zipName);
            try (FileOutputStream fis = new FileOutputStream(getServerPath() + zipName)) {
                // 文件类型
                for (int i = 0; i < files.size(); i++) {
                    boolean b = UploadUtil.downloadTrans(getServerPath(), files.get(i).getFileName(), files.get(i).getFilePath());
                    if (!b) {
                        return "从HDFS下载到服务器失败";
                    }
                    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(getServerPath() + files.get(i).getFileName()))) {
                        ZipOutputStream zip = new ZipOutputStream(fis);
//					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(getServerPath() + files.get(i).getFileName()));
                        String fileName = files.get(i).getFileName();
                        if (i > 0 && fileName.equals(files.get(i - 1).getFileName())) {
                            String[] split = files.get(i).getFileName().split("\\.");
                            fileName = split[0] + "(" + i + ")." + split[1];
                        }
                        zip.putNextEntry(new ZipEntry(fileName));

                        byte[] bytes = new byte[1024];
                        int length = 0;
                        while ((length = bis.read(bytes)) != -1) {
                            zip.write(bytes, 0, length);
                        }
                    } catch (IOException e) {
                        throw new IOException(e.getMessage(), e);
                    }
                }
                if (fis != null && images.size() == 0) {
                    fis.close();
                }
                // 图片类型
                if (files.size() < fileNum) {
                    for (int i = 0; i < images.size(); i++) {
                        boolean b = UploadUtil.downloadTrans(getServerPath(), images.get(i).getImageName(), images.get(i).getImagePath());
                        if (!b) {
                            return "从HDFS下载到服务器失败";
                        }
                        try (FileInputStream fis1 = new FileInputStream(getServerPath() + images.get(i).getImageName())) {
                            ZipOutputStream zip = new ZipOutputStream(fis);
//						FileInputStream fis1 = new FileInputStream(getServerPath() + images.get(i).getImageName());
                            BufferedInputStream bis = new BufferedInputStream(fis1);
                            String fileName = images.get(i).getImageName();
                            if (i > 0 && fileName.equals(images.get(i - 1).getImageName())) {
                                String[] split = images.get(i).getImageName().split("\\.");
                                fileName = split[0] + "(" + i + ")." + split[1];
                            }
                            zip.putNextEntry(new ZipEntry(fileName));

                            byte[] bytes = new byte[1024];
                            int length = 0;
                            while ((length = bis.read(bytes)) != -1) {
                                zip.write(bytes, 0, length);
                            }
                        } catch (Exception e) {
                            throw new Exception(e.getMessage(), e);
                        }
                    }
                }
            } catch (IOException e) {
                throw new IOException(e.getMessage(), e);
            }

            File file = null;
            file = new File(getServerPath() + zipName);
            response.setHeader("Content-Disposition", "attachment;filename=\"fwz.zip\"");
            response.setContentType("application/zip");
            try (InputStream ins = new FileInputStream(getServerPath() + zipName)) {
//				InputStream ins = new FileInputStream(getServerPath() + zipName);
                OutputStream outs = response.getOutputStream();
                //写文件
                int byteRead = 0;
                byte[] buffer = new byte[8192];
                //开始向网络传输文件流
                while ((byteRead = ins.read(buffer)) > 0) {
                    outs.write(buffer, 0, byteRead);
                }
                outs.flush();
            } catch (IOException e) {
                throw new IOException(e.getMessage(), e);
            }
            //删除临时创建的压缩包
            if (!file.delete()) {
                throw new FileNotFoundException("文件删除失败");
            }
            return "下载成功";

        } else {//没有制度发文的档案下载
            return "没有要下载的文件";
        }

    }


    // 获得本地系统路径 add by yr
    private String getServerPath() {
        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_SERVER_PATH) : SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH);
    }

    /**
     * 跳转修改档案著录
     */
//	@RequiresPermissions("amsArcReportcontroller:amsArchives:view")
    @GetMapping("/editBatch/{id}")
    public String editBatch(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        mmap.put(AMS_BATCH, amsBatch);
        return prefix + "/editBatch";
    }

    /**
     * 修改档案著录
     *
     * @param amsBatch
     * @return
     */
    @PostMapping("/editBatch")
    @ResponseBody
    public AjaxResult editBatchSave(AmsBatch amsBatch) {
        amsBatch.setModTime(new Date());
        if ("10".equals(amsBatch.getArcFormat())) {
            // 电子化档案
            List<ImFile> imFileList = imFileService.selectImFilesByBatchId(amsBatch.getBatchNo());
            if (imFileList == null || imFileList.size() == 0) {
                List<ImImage> imImageList = imageService.selectImImagesByBatchId(amsBatch.getBatchNo());
                if (imImageList == null || imImageList.size() == 0) {
                    return AjaxResult.error("电子档案请先上传文件");
                }
            }
        }
        //更新著录
        int i = amsBatchService.updateAmsBatch(amsBatch);
        //更新档案
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesByBatchId(amsBatch.getId());
        amsArchives.setArcBillName(amsBatch.getArcBill());
        amsArchives.setArcBillCode(amsBatch.getArcBillCode());
        amsArchives.setArcBillDept(amsBatch.getArcBillDept());
        amsArchives.setArcBillDeptCode(amsBatch.getArcBillDeptCode());
        amsArchives.setValPeriod(amsBatch.getValPeriod());
        amsArchives.setArcLevel(amsBatch.getArcLevel());
        amsArchives.setExpenseInvolve(amsBatch.getExpenseInvolve());
//        amsArchives.setArcPageNum(amsBatch.getArcPageNum());
//        amsArchives.setArcNum(amsBatch.getArcNum());
        amsArchives.setOriginMode(amsBatch.getOriginMode());
        amsArchives.setBill(amsBatch.getBill());
        amsArchives.setCarrier(amsBatch.getCarrier());
        //判断著录是否电子化
        if ("10".equals(amsBatch.getArcFormat())) {
            //档案形态: 电子
            amsArchives.setArcType("10");
        } else {
            //档案形态: 实物
            amsArchives.setArcType("20");
        }
        int j = amsArchivesService.updateAmsArchives(amsArchives);

        return toAjax((i + j));
    }
}
