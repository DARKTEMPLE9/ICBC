package net.northking.iacmp.ams.web.controller.borrowmgr;

import net.northking.iacmp.ams.web.enums.ArcCode;
import net.northking.iacmp.ams.web.utils.SendMailUtil;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.*;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 档案借阅历史 信息操作处理
 *
 * @author wxy
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/borrow/amsBorrowInfo")
public class AmsBorInfoController extends BaseController {
    private static final String AMS_BORROW_INFO = "amsBorrowInfo";
    private String prefix = "borrow/amsBorrowInfo";

    @Autowired
    private IAmsBorrowInfoService amsBorrowInfoService;
    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IAmsProcessInfoService amsProcessInfoService;
    @Autowired
    private IAmsApproveInfoService amsApproveInfoService;

    @Autowired
    private IAmsSendEmailService amsSendEmailService;


    @RequiresPermissions("borrow:amsBorrowInfo:view")
    @GetMapping("/index/{id}")
    public String amsBorrowInfo(@PathVariable String id, ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        Long roleId = 0L;
        //部门档案管理员/普通用户 "申请人"查询项显示标识
        String deptId = "";
        String userId = "";
        if (roleList != null && !roleList.isEmpty()) {
            Integer[] arr = new Integer[roleList.size()];
            for (int i = 0; i < roleList.size(); i++) {
                arr[i] = roleList.get(i).getRoleId().intValue();
            }
            Arrays.sort(arr);
            //获取最高权限角色Id
            Integer sysRoleId = arr[(roleList.size() - 1)];
            //角色Id
            roleId = Long.valueOf(sysRoleId.longValue());
        }
        //角色判断
        if (roleId == 3L || roleId == 4L || roleId == 1L) {
            //部门档案管理员
            userId = sysUser.getUserId().toString();
            deptId = sysUser.getDeptId().toString();
        } else if (roleId == 2L) {
            //普通用户
            deptId = sysUser.getDeptId().toString();
            userId = sysUser.getUserId().toString();
        }
        mmap.put("roleId", String.valueOf(roleId));
        mmap.put("path", id);

        mmap.put("deptId", deptId);
        mmap.put("userId", userId);
        if (id.equals("elec")) {
            return prefix + "/manageElec";
        }
        return prefix + "/amsBorrowInfo";
    }

    /**
     * 查询档案借阅历史列表
     */
//	@RequiresPermissions("borrow:amsBorrowInfo:list")
    @PostMapping("/list/{pathType}")
    @ResponseBody
    public TableDataInfo list(@PathVariable String pathType, AmsBorrowInfo amsBorrowInfo) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (amsBorrowInfo != null) {
            if (amsBorrowInfo.getBorStaTimeStr() != null) {
                if (amsBorrowInfo.getBorEndTimeStr() == null || "".equals(amsBorrowInfo.getBorEndTimeStr())) {
                    amsBorrowInfo.setBorEndTimeStr(amsBorrowInfo.getBorEndTimeStr());
                }
            }
        }
        //查询当前用户是否有辅部门
        String auxiliaryDept = sysUser.getAuxiliaryDept();
        //用户管理的全部部门
        List<String> deptList = new ArrayList<>();
        deptList.add(sysUser.getDeptId().toString());
        if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//该用户有辅部门
            String[] auxiliaryDeptArr = auxiliaryDept.split(",");
            for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                deptList.add(auxiliaryDeptArr[i]);
            }
        }
        startPage();
        List<AmsBorrowInfo> list = null;
        //普通用户只能查看本人的借阅信息,部门档案管理员只能查看本部门的借阅信息,行档案管理员或管理员没有限制
        List<SysRole> roleList = sysUser.getRoles();
        Long roleId = 0L;
//		if(roleList != null && !roleList.isEmpty()){
//			Integer[] arr = new Integer[roleList.size()];
//			for(int i = 0; i < roleList.size(); i++){
//				arr[i] = roleList.get(i).getRoleId().intValue();
//			}
//			Arrays.sort(arr);
//			//获取最高权限角色Id
//			Integer sysRoleId = arr[(roleList.size()-1)];
//			//角色Id
//			roleId = Long.valueOf(sysRoleId.longValue());
//		}
        //该用户全部角色
        List<Long> roleIds = new ArrayList();
        for (SysRole sysRole : roleList) {
            roleIds.add(sysRole.getRoleId());
        }
        roleIds.remove(23L);
        roleId = Collections.max(roleIds);
        //角色判断
        if (roleId != 4L || roleId != 1L) {
            //部门档案管理员/领导
            if (roleId == 3L || roleId == 14L) {
                amsBorrowInfo.setAppDepCode(sysUser.getDeptId().toString());
            } else if (roleId == 2L) {
                //普通用户
                amsBorrowInfo.setAppDepCode(sysUser.getDeptId().toString());
                amsBorrowInfo.setAppOpCode(sysUser.getUserId().toString());
            }
        }

        if (("hst").equals(pathType)) {
            list = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo, deptList);
        } else if (("rtn").equals(pathType)) {
            //设置档案归还查询参数
            amsBorrowInfo.setStatus("2");
            amsBorrowInfo.setBorType("20");
            if (roleId == 4L) {
                //行档案
                amsBorrowInfo.setHasMoveBank("1");
            } else if (roleId == 3L || roleId == 14L) {
                //部门档案
                amsBorrowInfo.setHasMoveBank("0");
            }
            list = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo, deptList);
        } else if (("matter").equals(pathType)) {
            //borType 在线借阅 ：10 实体借阅：20
            if (StringUtils.isNotEmpty(amsBorrowInfo.getBorType())) {
                amsBorrowInfo.setBorType(amsBorrowInfo.getBorType());
            }
            list = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo);
        } else if (("elec").equals(pathType)) {
            //borType 在线借阅 ：10 实体借阅：20
            if (StringUtils.isNotEmpty(amsBorrowInfo.getBorType())) {
                amsBorrowInfo.setBorType(amsBorrowInfo.getBorType());
            }
            list = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo);
        } else if (("user").equals(pathType)) {
            //申请人编码
            amsBorrowInfo.setAppOpCode(sysUser.getUserId().toString());
            //个人借阅 查询申请人为登录用户的借阅数据
            list = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo);
        }
        return getDataTable(list);
    }

    /**
     * 个人该实体档案延续借阅次数
     */

    @PostMapping("/reBorrowCount")
    @ResponseBody
    public Integer reBorrowCount(HttpServletRequest request) {
        String arcNo = request.getParameter("arcNo");
        SysUser sysUser = ShiroUtils.getSysUser();
        AmsBorrowInfo amsBorrowInfo = new AmsBorrowInfo();
        amsBorrowInfo.setArcNo(arcNo);
        amsBorrowInfo.setAppOpCode(sysUser.getUserId().toString());
        amsBorrowInfo.setStatus("2");
        amsBorrowInfo.setBorType("20");
        List<AmsBorrowInfo> borrowInfoList = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo);
        int count = borrowInfoList.size();
        return count;
    }

    /**
     * 导出档案借阅历史列表
     */
    @RequiresPermissions("borrow:amsBorrowInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBorrowInfo amsBorrowInfo) {
        List<AmsBorrowInfo> list = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo);
        ExcelUtil<AmsBorrowInfo> util = new ExcelUtil<>(AmsBorrowInfo.class);
        return util.exportExcel(list, AMS_BORROW_INFO);
    }

    /**
     * 新增档案借阅历史
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案借阅历史
     */
    @RequiresPermissions("borrow:amsBorrowInfo:add")
    @Log(title = "档案借阅历史", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBorrowInfo amsBorrowInfo) {
        return toAjax(amsBorrowInfoService.insertAmsBorrowInfo(amsBorrowInfo));
    }

    /**
     * 修改档案借阅历史
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoById(id);
        mmap.put(AMS_BORROW_INFO, amsBorrowInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案借阅历史
     */
    @RequiresPermissions("borrow:amsBorrowInfo:edit")
    @Log(title = "档案借阅历史", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBorrowInfo amsBorrowInfo) {
        return toAjax(amsBorrowInfoService.updateAmsBorrowInfo(amsBorrowInfo));
    }

    /**
     * 删除档案借阅历史
     */
    @RequiresPermissions("borrow:amsBorrowInfo:remove")
    @Log(title = "档案借阅历史", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBorrowInfoService.deleteAmsBorrowInfoByIds(ids));
    }

    /**
     * 查看档案借阅历史详情
     */
    @GetMapping("/detail/{id}")
    public String historyDetail(@PathVariable("id") String id, ModelMap mmap) {
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoById(id);
        String batchId = amsBorrowInfo.getBatchId();
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(batchId);
        mmap.put(AMS_BORROW_INFO, amsBorrowInfo);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/viewBor";
    }

    /**
     * 查询审批记录
     *
     * @param id
     * @param mmap
     * @return
     */
    //@RequiresPermissions("myTask:waitProcessInfo:approvalHistory")
    @GetMapping("/approvalHistory/{id}")
    public String approvalHistory(@PathVariable("id") String id, ModelMap mmap) {
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoById(id);
        String exaappid = amsBorrowInfo.getExaAppInfoId();
        List<AmsApproveInfo> amsApproveInfolist = amsApproveInfoService.selectAmsApproveInfoByexaAppId(exaappid);
        mmap.put("amsApproveInfolist", amsApproveInfolist);
        return prefix + "/apprrovalHistory";
    }

    /**
     * 查询邮件发送记录
     *
     * @param id
     * @param mmap
     * @return
     */
    //@RequiresPermissions("myTask:waitProcessInfo:approvalHistory")
    @GetMapping("/emailHistory/{id}")
    public String emailHistory(@PathVariable("id") String id, ModelMap mmap) {
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoById(id);
        String exaAppId = amsBorrowInfo.getExaAppInfoId();
        AmsSendEmail amsSendEmail = new AmsSendEmail();
        amsSendEmail.setExaAppId(exaAppId);
        List<AmsSendEmail> amsSendEmailList = amsSendEmailService.selectAmsSendEmailList(amsSendEmail);
        mmap.put("amsSendEmailList", amsSendEmailList);
        return prefix + "/emailHistory";
    }

    /**
     * 查询档案相关信息（档案盒号/所属库柜/所属库房）
     *
     * @return
     * @parambatchId
     */
    @PostMapping("/queryAmsArchivesBybatchId")
    @ResponseBody
    public AmsArchives queryAmsArchivesBybatchId(HttpServletRequest request) {
        String batchId = request.getParameter("batchId");
        AmsArchivesVO amsArchives = new AmsArchivesVO();
        amsArchives.setBatchId(batchId);

        List<AmsArchives> list = amsArchivesService.selectAmsArchivesList(amsArchives);
        return list.get(0);
    }


    /**
     * 查询档案借阅历史记录
     */
    @PostMapping("/getById/{id}")
    @ResponseBody
    public AmsBorrowInfo getById(@PathVariable("id") String id) {
        return amsBorrowInfoService.selectAmsBorrowInfoById(id);
    }


    /**
     * 文件下载
     *
     * @param request  页面请求
     * @param response 响应
     * @return 结果
     */
    @RequestMapping("/downloadOnlineFile")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        // 设置文件名，根据业务需要替换成要下载的文件名
        String fileName = "File.doc";
        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            logger.error("文件未找到", e.getMessage());
        }
        String realPath = path + "static/file";
        File file = new File(realPath, fileName);
        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        logger.error("IO异常", e.getMessage());
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        logger.error("IO异常", e.getMessage());
                    }
                }
            }
        }
        return null;
    }

    /**
     * 档案出库 填写备注
     */
    @GetMapping("/remark")
    public String remark() {
        return prefix + "/remark";
    }

    /**
     * 执行出库
     */
    @RequiresPermissions("borrow:amsBorrowInfo:outArc")
    @PostMapping("/outArc")
    @ResponseBody
    public String outArc(HttpServletRequest request) {
        String borId = request.getParameter("borId");
        String remark = request.getParameter("arcRemark");
        AmsBorrowInfo amsBorrow = amsBorrowInfoService.selectAmsBorrowInfoById(borId);

        //判断状态是否为出库
        if (ArcCode.ARCH_ALREADY_EXIT_STORAGE.getCode().equals(amsBorrow.getArcStatus())) {
            return "error";
        } else {
            //实体档案出库时，该实体档案的全部借阅信息中的档案状态都应修改成已出库
            String arcNo = amsBorrow.getArcNo();
            AmsBorrowInfo borrow = new AmsBorrowInfo();
            borrow.setArcNo(arcNo);
            borrow.setStatus("2");
            borrow.setBorType("20");
            List<AmsBorrowInfo> borrowList1 = amsBorrowInfoService.selectAmsBorrowInfoList(borrow);
            for (int i = 0; i < borrowList1.size(); i++) {
                borrowList1.get(i).setArcStatus(ArcCode.ARCH_ALREADY_EXIT_STORAGE.getCode());
                borrowList1.get(i).setRemark(remark);
                amsBorrowInfoService.updateAmsBorrowInfo(borrowList1.get(i));
            }

            AmsBatch batch = new AmsBatch();
            batch.setId(amsBorrow.getBatchId());
            List<AmsBatch> amsBatchList = amsBatchService.selectAmsBatchList(batch);
            AmsBatch newAmsBatch = amsBatchList.get(0);
            newAmsBatch.setStatus(ArcCode.ARCH_ALREADY_EXIT_STORAGE.getCode());
            amsBatchService.updateAmsBatch(newAmsBatch);

            AmsArchivesVO arc = new AmsArchivesVO();
            arc.setId(amsBorrow.getArchiveId());
            List<AmsArchives> arcList = amsArchivesService.selectAmsArchivesList(arc);
            AmsArchives newArch = arcList.get(0);
            newArch.setStatus(ArcCode.ARCH_ALREADY_EXIT_STORAGE.getCode());
            amsArchivesService.updateAmsArchives(newArch);
        }

        return "success";
    }

    /**
     * 档案归还
     */
    @GetMapping("/revert/{id}")
    public String revert(@PathVariable("id") String id, ModelMap mmap) {
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoById(id);
        mmap.put(AMS_BORROW_INFO, amsBorrowInfo);
        return prefix + "/revert";
    }

    /**
     * 归还 档案
     *
     * @return
     */
    @RequiresPermissions("borrow:amsBorrowInfo:revert")
    @PostMapping("/revertArc")
    @ResponseBody
    public AjaxResult revertArc(AmsBorrowInfo amsBorrowInfo) {

        String arcId = amsBorrowInfo.getArchiveId();
        //归还操作时间
        amsBorrowInfo.setReturnOpTime(new java.sql.Timestamp(System.currentTimeMillis()));
        //借阅的实体档案归还时，把全部该实体档案的借阅状态都修改成已归还，档案状态修改成已入库
        //归还的档案
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(arcId);
        String arcNo = amsBorrowInfo.getArcNo();
        AmsBorrowInfo borrow = new AmsBorrowInfo();
        borrow.setArcNo(arcNo);
        borrow.setStatus("2");
        borrow.setBorType("20");
        //该档案的全部正在借阅的实体档案
        List borrowList = amsBorrowInfoService.selectAmsBorrowInfoList(borrow);
        int rows = 0;
        AmsBatch amsBatch = new AmsBatch();
        amsBatch.setId(amsBorrowInfo.getBatchId());
        for (int i = 0; i < borrowList.size(); i++) {
            AmsBorrowInfo borr = (AmsBorrowInfo) borrowList.get(i);
            borr.setStatus("4");
            //实际归还日期
            borr.setActReturnTime(new Date());
            //已归还
            if (amsBorrowInfo.getReturnOpType().equals("5")) {
                //档案状态 已入库
                borr.setArcStatus("5");
                if (amsArchives != null) {
                    amsArchives.setStatus(ArcCode.ARCH_ALREADY_PUT_STORAGE.getCode());
                    amsArchivesService.updateAmsArchives(amsArchives);
                }
            } else {
                //待废弃
                //档案状态 待废弃
                borr.setArcStatus("9");
                if (amsArchives != null) {
                    amsArchives.setStatus(ArcCode.ARCH_WAIT_DISCARD.getCode());
                    amsArchivesService.updateAmsArchives(amsArchives);
                }
            }
            rows += amsBorrowInfoService.updateAmsBorrowInfo(borr);
        }

        if (amsBorrowInfo.getReturnOpType().equals("5")) {
            //已入库
            amsBatch.setStatus("5");
            amsBatchService.updateAmsBatch(amsBatch);
        } else {//待废弃
            amsBatch.setStatus("9");
            amsBatchService.updateAmsBatch(amsBatch);
        }

        return toAjax(rows);
    }

    /**
     * 延续借阅
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("borrow:amsBorrowInfo:reApplyBor")
    @GetMapping("/reApplyBor/{id}")
    public String reApplyBor(@PathVariable("id") String id, ModelMap mmap) {
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoById(id);
        //档案所属部门号
        String deptId = amsBorrowInfo.getOpDepNo();
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> list = new ArrayList();
        for (SysRole o : roleList) {
            list.add(o.getRoleId());
        }
        //根据角色id和部门id查询下级审批人
        String roleId = Collections.max(list).toString();
        //用户部门号
        String userDeptId = sysUser.getDeptId().toString();
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(amsBorrowInfo.getArchiveId());
        //著录Id
        String banchId = amsArchives.getBatchId();
        //著录信息
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(banchId);

        List<SysUser> user = new ArrayList<>();

        if ("2".equals(roleId)) {
            user = sysUserService.selectNextUserByroleId("14", userDeptId);
        } else if (("3".equals(roleId) || "14".equals(roleId)) && !deptId.equals(userDeptId)) {
            user = sysUserService.selectNextUserByroleId("14", userDeptId);
        } else if ("3".equals(roleId) || "14".equals(roleId)) {
            //判断是否为行档案
            if ("1".equals(amsBatch.getArcHasMoveBank())) {
                //提交部门经理
                user = sysUserService.selectNextUserByroleId("14", userDeptId);
            } else {
                //部门档案提交部门管理员
                user = sysUserService.selectNextUserByuserId(sysUser.getUserId());
                for (SysUser o : user) {
                    o.setUserName("end");
                }
            }
        } else if ("1".equals(roleId)) {
            user = sysUserService.selectNextUserByrole("4");
        } else if ("4".equals(roleId)) {
            user = sysUserService.selectNextUserByuserId(sysUser.getUserId());
            for (SysUser o : user) {
                o.setUserName("end");
            }
        }

        mmap.put(AMS_BORROW_INFO, amsBorrowInfo);
        mmap.put("user", user);
        return prefix + "/reApplyBor";
    }

    /**
     * 申请利用
     *
     * @param amsBorrowInfo
     * @return
     */
    @PostMapping("/borrow")
    @ResponseBody
    public AjaxResult addborrow(AmsBorrowInfo amsBorrowInfo) {
        AmsBorrowInfo oldAmsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoById(amsBorrowInfo.getId());
        amsBorrowInfo.setAppTime(new java.sql.Timestamp(System.currentTimeMillis()));
        amsBorrowInfo.setStatus("1");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        amsBorrowInfo.setId(uuid);
        amsBorrowInfo.setArcStatus("2");
        amsBorrowInfo.setArcType(oldAmsBorrowInfo.getArcType());
        //重新计算借阅天数
        String borDays = getDiffDays(amsBorrowInfo.getBorEndTime(), amsBorrowInfo.getBorStaTime());
        amsBorrowInfo.setBorDays(borDays);
        String exaAppId = "";
        AmsProcessInfo amsProcessInfo = new AmsProcessInfo();
        try {
            if (Constants.AMS_APPLY_ELE.equals(amsBorrowInfo.getBorType())) {
                //电子档案借阅
                amsProcessInfo = this.addProcess(amsBorrowInfo.getAgentCode(), amsBorrowInfo.getBorType(), amsBorrowInfo.getArchiveId(), ArcCode.ARCH_ELE_CONSULT.getCode());
            } else if (Constants.AMS_APPLY_ENTITY.equals(amsBorrowInfo.getBorType())) {
                //实物档案借阅
                amsProcessInfo = this.addProcess(amsBorrowInfo.getAgentCode(), amsBorrowInfo.getBorType(), amsBorrowInfo.getArchiveId(), ArcCode.ARCH_OBJECT_CONSULT.getCode());
            }
            amsBorrowInfo.setExaAppInfoId(amsProcessInfo.getExaAppId());
            // 添加二级类目
            amsBorrowInfo.setArcBillDeptCode(amsBorrowInfo.getArcBillDeptCode());
            amsBorrowInfo.setArcBillDeptName(amsBorrowInfo.getArcBillDeptName());
            //置空
        } catch (Exception e) {
            return AjaxResult.error("借阅档案错误，请检查档案信息。");
        }
//		newAmsProcessInfo=amsProcessInfo;
        AmsArchivesVO amsArchivesVO = new AmsArchivesVO();
        amsArchivesVO.setArcNo(amsBorrowInfo.getArcNo());
        List<AmsArchives> amsArchList = amsArchivesService.selectAmsArchivesList(amsArchivesVO);
        amsArchList.get(0).setStatus("2");
        amsArchivesService.updateAmsArchives(amsArchList.get(0));
        int rows = amsBorrowInfoService.insertAmsBorrowInfo(amsBorrowInfo);
        //保存成功，发送email，失败则不发送
        if (rows > 0) {
            //借阅申请成功提交后，修改档案状态为借阅申请中
            String arcNo = amsBorrowInfo.getArcNo();
            AmsArchivesVO amsArchives = new AmsArchivesVO();
            amsArchives.setArcNo(arcNo);
            List<AmsArchives> amsArchivesList = amsArchivesService.selectAmsArchivesList(amsArchives);
            amsArchivesList.get(0).setStatus("2");
            amsArchivesService.updateAmsArchives(amsArchivesList.get(0));
        }
        return toAjaxNew(rows, amsProcessInfo.getId());
    }

    public AjaxResult toAjaxNew(int rows, String id) {
        if (rows > 0) {
            return AjaxResult.success("操作成功，邮件已发送", id);
        } else {
            return AjaxResult.error("操作失败", id);
        }

    }

    /**
     * 给下节点审批人发送邮件
     */
    @PostMapping("/sendEmailToNextUser")
    @ResponseBody
    public void sendEmailToNextUser(HttpServletRequest request) {
        Date nowDate = new Date();
        String nextUserNo = request.getParameter("nextUserNo");
        String processId = request.getParameter("processId");
        AmsProcessInfo newAmsProcessInfo = amsProcessInfoService.selectAmsProcessInfoById(processId);
        SysUser nextSysuer = sysUserService.selectUserById(Long.valueOf(nextUserNo));
        String nextSysuserEmail = nextSysuer.getEmail();
        String isSuccess = "";
        if (!"".equals(nextSysuserEmail) && nextSysuserEmail != null) {
            SendMailUtil sender = new SendMailUtil();
            isSuccess = sender.sendMail(nextSysuer, newAmsProcessInfo);
        } else {
            isSuccess = "发送失败";
        }
        AmsSendEmail amsSendEmail = new AmsSendEmail();
        amsSendEmail.setSendUser(newAmsProcessInfo.getAppOpName());
        amsSendEmail.setReviceUser(nextSysuer.getUserName());
        amsSendEmail.setReviceUserEmail(nextSysuserEmail);
        amsSendEmail.setStatus(isSuccess);
        amsSendEmail.setArcName(newAmsProcessInfo.getArcName());
        amsSendEmail.setExaAppId(newAmsProcessInfo.getExaAppId());
        amsSendEmail.setSendEmailTime(nowDate);
        amsSendEmailService.insertAmsSendEmail(amsSendEmail);
    }


    /**
     * 保存
     *
     * @param amsBorrowInfo
     * @return
     */
    @PostMapping("/toSave")
    @ResponseBody
    public AjaxResult toSave(AmsBorrowInfo amsBorrowInfo) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        amsBorrowInfo.setId(uuid);
        amsBorrowInfo.setAppTime(new java.sql.Timestamp(System.currentTimeMillis()));
        amsBorrowInfo.setStatus("0");
        // 添加二级类目
        amsBorrowInfo.setArcBillDeptCode(amsBorrowInfo.getArcBillDeptCode());
        amsBorrowInfo.setArcBillDeptName(amsBorrowInfo.getArcBillDeptName());

        return toAjax(amsBorrowInfoService.insertAmsBorrowInfo(amsBorrowInfo));
    }

    /**
     * 新建待审批流程
     *
     * @param nowDisOpNo 下一节点处理人编号
     * @param exaType    (流程编码,遵循数据库存储定义:上交流程为10;电子档案借阅流程为20;实物档案借阅流程为30;)
     * @param archId     (档案编号)
     * @param agentName  代理人姓名
     * @return
     */
    private AmsProcessInfo addProcess(String nowDisOpNo, String exaType, String archId, String agentName) {

        // 流程编号
        String processId = null;
        if (ArcCode.ARCH_OBJECT_HAND_IN.getCode().equals(exaType)) {
            processId = ArcCode.ARCH_HAND_IN_PROCESS.getCode();
        } else if (ArcCode.ARCH_ELE_CONSULT.getCode().equals(exaType)) {
            processId = ArcCode.ARCH_BORROW_PROCESS.getCode();
        } else if (ArcCode.ARCH_OBJECT_CONSULT.getCode().equals(exaType)) {
            processId = ArcCode.ARCH_BORROW_PROCESS.getCode();
        }
        //获取档案信息
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(archId);

        AmsProcessInfo amsProcessInfo = new AmsProcessInfo();
        // 添加申请人
        String exaAppId = UUID.randomUUID().toString().replaceAll("-", "");
        amsProcessInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        amsProcessInfo.setExaAppId(exaAppId);
        amsProcessInfo.setExaAppType(exaType);
        amsProcessInfo.setAppOpNo(ShiroUtils.getUserId().toString());
        amsProcessInfo.setAppOpName(ShiroUtils.getSysUser().getUserName());
        amsProcessInfo.setAppOrgNo(ShiroUtils.getSysUser().getDeptId().toString());
        amsProcessInfo.setAppOrgName(ShiroUtils.getSysUser().getDept().getDeptName());
        amsProcessInfo.setAppTime(new Date());
        //添加代理人
        amsProcessInfo.setAgentName(agentName);
        amsProcessInfo.setPreDispOpNo(ShiroUtils.getUserId().toString());
        amsProcessInfo.setPreDispOpName(ShiroUtils.getSysUser().getUserName());
        amsProcessInfo.setPreDispOrgNo(ShiroUtils.getSysUser().getDeptId().toString());
        amsProcessInfo.setPreDispOrgName(ShiroUtils.getSysUser().getDept().getDeptName());
        amsProcessInfo.setPreDispTime(new Date());
        amsProcessInfo.setExaAppStatus(ArcCode.PROCESSING.getCode());
        amsProcessInfo.setProcessId(processId);
        amsProcessInfo.setNowProcessId(UUID.randomUUID().toString().replaceAll("-", ""));
        amsProcessInfo.setFoSearch(ArcCode.FORSEARCH.getCode());
        // 添加下一节点人
        SysUser nextSysuer = sysUserService.selectUserById(Long.valueOf(nowDisOpNo));
        amsProcessInfo.setNowDispOpNo(nowDisOpNo);
        amsProcessInfo.setNowDispOpName(nextSysuer.getUserName());
        amsProcessInfo.setNowDispOrgNo(nextSysuer.getDeptId().toString());
        amsProcessInfo.setNowDispOrgName(nextSysuer.getDept().getDeptName());
        amsProcessInfo.setNowDispTime(new Date());
        // 添加档案及著录信息
        AmsBatch amsBatch = this.amsBatchService.selectAmsBatchById(amsArchives.getBatchId());
        amsProcessInfo.setBrachId(amsArchives.getBatchId());
        amsProcessInfo.setArcNo(amsBatch.getArcNo());
        amsProcessInfo.setArcName(amsBatch.getArcName());
        amsProcessInfo.setArcFormat(amsBatch.getArcFormat());

        this.amsProcessInfoService.insertAmsProcessInfo(amsProcessInfo);

        //修改档案状态
        if (Constants.AMS_APPLY_ENTITY.equals(exaType)) {
            amsArchives.setStatus(Constants.APPLYING);
            this.amsArchivesService.updateAmsArchives(amsArchives);
        }
        // 一个审批流程添加完成后，新增一条意见

        return amsProcessInfo;
    }

    /**
     * 跳转选择申请人页面
     *
     * @param flag
     * @param mmap
     * @return
     */
    @GetMapping("/selectAppUser/{deptId}/{userId}")
    public String selectAppUser(@PathVariable("deptId") String deptId, @PathVariable("userId") String userId, ModelMap mmap) {
        mmap.put("deptId", deptId);
        mmap.put("userId", userId);
        return prefix + "/selectAppUser";
    }

    /**
     * 查询申请人列表
     *
     * @param flag
     * @param mmap
     * @return
     */
    @PostMapping("/select/appUserList")
    @ResponseBody
    public TableDataInfo appUserList(SysUser sysUser) {
        SysUser loginUser = sysUserService.selectUserById(sysUser.getUserId());
        List<SysRole> roleList = loginUser.getRoles();
        List<Long> idList = new ArrayList();
        for (SysRole o : roleList) {
            idList.add(o.getRoleId());
        }
        //获取用户最高权限角色
        String roleId = Collections.max(idList).toString();
        if ("4".equals(roleId)) {
            sysUser.setDeptId(0L);
            sysUser.setUserId(0L);
        } else if ("3".equals(roleId)) {
            sysUser.setUserId(0L);
        }
        startPage();
        List<SysUser> list = amsBorrowInfoService.selectAppUsersBySysUser(sysUser);
        return getDataTable(list);
    }

    private String getDiffDays(Date end, Date start) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //先转成毫秒并求差
        long m = end.getTime() - start.getTime();
        // 转换为天数
        System.out.println("相差天数:" + (m / (1000 * 60 * 60 * 24)));
        long days = (m / (1000 * 60 * 60 * 24));
        return String.valueOf(days);
    }
}
