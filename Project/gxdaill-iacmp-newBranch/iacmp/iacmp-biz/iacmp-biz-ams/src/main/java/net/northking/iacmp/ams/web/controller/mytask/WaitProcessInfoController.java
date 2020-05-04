package net.northking.iacmp.ams.web.controller.mytask;


import net.northking.iacmp.ams.web.enums.ArcCode;
import net.northking.iacmp.ams.web.utils.SendMailUtil;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.common.bean.vo.ams.AmsProcessInfoVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.*;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 我的任务------>待审批
 * 操作表-------->ams_process_info
 *
 * @author yqy
 * @date 2019-08-07
 */
@Controller
@RequestMapping("/myTask/waitProcessInfo")
public class WaitProcessInfoController extends BaseController {
    private String prefix = "myTask/waitProcessInfo";

    @Autowired
    /**
     * 审批服务
     */
    private IAmsProcessInfoService amsProcessInfoService;
    @Autowired
    /**
     * 档案著录服务
     */
    private IAmsBatchService amsBatchService;
    @Autowired
    /**
     * 档案借阅历史服务
     */
    private IAmsBorrowInfoService amsBorrowInfoService;
    @Autowired
    /**
     * 审批意见服务
     */
    private IAmsApproveInfoService amsApproveInfoService;
    @Autowired
    /**
     * 用户服务
     */
    private ISysUserService sysUserService;
    @Autowired
    /**
     * 档案登记服务
     */
    private IAmsArchivesService amsArchivesService;


    @Autowired
    private IAmsSendEmailService amsSendEmailService;

    @RequiresPermissions("myTask:waitProcessInfo:view")
    @GetMapping()
    public String amsProcessInfo() {
        return prefix + "/amsProcessInfo";
    }

    /**
     * 查询审批列表
     */
    @RequiresPermissions("myTask:waitProcessInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsProcessInfoVO amsProcessInfo) {
        SysUser sysUser = ShiroUtils.getSysUser();
        //获取该用户的所有角色信息
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> list = new ArrayList();
        for (SysRole o : roleList) {
            list.add(o.getRoleId());
        }
        //获取用户最高权限角色
        String roleId = Collections.max(list).toString();
        List<AmsProcessInfoVO> resultList = new ArrayList<>();
        if (!("2".equals(roleId))) {
            amsProcessInfo.setNowDispOpNo(sysUser.getUserId().toString());
            //汇总审批状态 设置不为空
            amsProcessInfo.setFoSearch("1");
            startPage();
            if (null == amsProcessInfo.getExaAppStatus() || amsProcessInfo.getExaAppStatus().isEmpty()) {
                //获取审批状态 若exaAppStatus状态字为空 则设置为审批中/审批拒绝状态 否则获取当前审批状态
                //审批中
                amsProcessInfo.setExaAppStatus("20");
                List<AmsProcessInfoVO> list1 = amsProcessInfoService.selectAmsProcessInfoList(amsProcessInfo);
                for (AmsProcessInfoVO obj : list1) {
                    resultList.add(obj);
                }
            } else {
                amsProcessInfo.setExaAppStatus(amsProcessInfo.getExaAppStatus());
                resultList = amsProcessInfoService.selectAmsProcessInfoList(amsProcessInfo);
            }
        }
        return getDataTable(resultList);
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
     * 导出审批列表
     */
    @RequiresPermissions("myTask:waitProcessInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsProcessInfo amsProcessInfo) {
        List<AmsProcessInfo> list = amsProcessInfoService.selectAmsProcessInfoList(amsProcessInfo);
        ExcelUtil<AmsProcessInfo> util = new ExcelUtil<>(AmsProcessInfo.class);
        return util.exportExcel(list, "amsProcessInfo");
    }

    /**
     * 新增审批
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存审批
     */
    @RequiresPermissions("myTask:waitProcessInfo:add")
    @Log(title = "审批", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsProcessInfo amsProcessInfo) {
        return toAjax(amsProcessInfoService.insertAmsProcessInfo(amsProcessInfo));
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
        AmsProcessInfo amsProcessInfo = amsProcessInfoService.selectAmsProcessInfoById(id);
        String exaappid = amsProcessInfo.getExaAppId();
        List<AmsApproveInfo> amsApproveInfolist = amsApproveInfoService.selectAmsApproveInfoByexaAppId(exaappid);
        mmap.put("amsApproveInfolist", amsApproveInfolist);
        return prefix + "/apprrovalHistory";
    }

    /**
     * 档案退回，修改该档案状态为退回
     */
    @Log(title = "档案", businessType = BusinessType.UPDATE)
    @PostMapping("/returnProcess/{id}")
    @ResponseBody
    public AjaxResult returnArch(@PathVariable("id") String id, AmsProcessInfo amsProcessInfo1) {

        AmsProcessInfo amsProcessInfo = amsProcessInfoService.selectAmsProcessInfoById(amsProcessInfo1.getId());
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoByexaAppId(amsProcessInfo.getExaAppId());
        SysUser nowSmUser = ShiroUtils.getSysUser();
        AmsApproveInfo amsApproveInfo = new AmsApproveInfo();
        amsApproveInfo.setExaAppTime(new java.sql.Timestamp(System.currentTimeMillis()));
        amsApproveInfo.setExaAppId(amsProcessInfo.getExaAppId());
        amsApproveInfo.setExaAppOpNo(nowSmUser.getUserId().toString());
        amsApproveInfo.setExaAppOpName(nowSmUser.getUserName());
        amsApproveInfo.setExaAppOrgNo(nowSmUser.getDeptId().toString());
        amsApproveInfo.setExaAppOrgName(nowSmUser.getDept().getDeptName());
        amsApproveInfo.setExaAppOpnion("不同意，退回！！");
        amsApproveInfo.setExaAppResult("20");
        amsApproveInfo.setExaBack("20");
        amsApproveInfo.setAgentOpNo(amsProcessInfo.getAppOpNo());
        amsApproveInfo.setAgentOpName(nowSmUser.getUserName());
        amsApproveInfo.setAgentFlag("0");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        amsApproveInfo.setId(uuid);
        amsApproveInfoService.insertAmsApproveInfo(amsApproveInfo);

        //更新借阅信息的借阅状态为审批拒绝
        amsBorrowInfo.setStatus("3");
        //更新借阅信息的档案借阅状态为已入库
        amsBorrowInfo.setArcStatus("5");
        amsBorrowInfoService.updateAmsBorrowInfo(amsBorrowInfo);

        //更新档案登记表状态为入库
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(amsBorrowInfo.getArchiveId());
        if (amsArchives != null) {
            amsArchives.setStatus("5");
        }
        amsArchivesService.updateAmsArchives(amsArchives);

        amsProcessInfo.setExaAppResult("20");
        amsProcessInfo.setExaBack("20");
        //审批拒绝
        amsProcessInfo.setExaAppStatus("40");
        amsProcessInfo.setNowDispOpNo(amsBorrowInfo.getAppOpCode());
        amsProcessInfo.setNowDispOpName(amsBorrowInfo.getAppOpName());
        amsProcessInfo.setNowDispOrgNo(amsBorrowInfo.getAppDepCode());
        amsProcessInfo.setNowDispOrgName(amsBorrowInfo.getAppDepName());
        amsProcessInfo.setNowDispOpnion("不同意，退回！！");
        String exaapptype = amsProcessInfo.getExaAppType();
        amsProcessInfo.setExaAppType("电子调阅".equals(exaapptype) ? "10" : "20");
        amsProcessInfo.setNowDispTime(new java.sql.Timestamp(System.currentTimeMillis()));
        return toAjax(amsProcessInfoService.updateAmsProcessInfo(amsProcessInfo));

    }

    /**
     * 审批处理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        //通过审批服务（通过id查询审批信息方法拿到 审批信息对象
        AmsProcessInfo amsProcessInfo = amsProcessInfoService.selectAmsProcessInfoById(id);
        //通过档案借阅历史服务（通过档案编号查询方法）获取 档案借阅信息
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoByexaAppId(amsProcessInfo.getExaAppId());
        //通过档案登记服务获取 档案登记表
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesByBatchId(amsProcessInfo.getBrachId());
        //档案登记
        if (amsBorrowInfo == null) {
            amsProcessInfo.setBorrowId("");
        } else {
            amsProcessInfo.setBorrowId(amsBorrowInfo.getAppReason());
        }
        //审批意见
        amsProcessInfo.setNowDispOpnion("");
        SysUser sysUser = ShiroUtils.getSysUser();
        //获取该用户所有角色信息
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> list = new ArrayList();

        for (SysRole o : roleList) {
            list.add(o.getRoleId());
        }
        list.remove(23L);
        //根据角色id和部门id查询下级审批人
        String roleId = Collections.max(list).toString();
        //用户部门号
        String deptId = sysUser.getDeptId().toString();
        //档案所属部门号
        String opdepId = amsArchives.getOpDepNo();
        //著录Id
        String banchId = amsArchives.getBatchId();
        //著录信息
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(banchId);
        List<SysUser> user = new ArrayList<>();
        if ("2".equals(roleId)) {
            user = sysUserService.selectNextUserByroleId("14", deptId);
            //查询其他有权审批的部门经理
            String auxiliaryDept = sysUser.getAuxiliaryDept();
            List<SysUser> managers = sysUserService.selectNextUserByrole("14");
            for (SysUser manager : managers) {
                auxiliaryDept = manager.getAuxiliaryDept();
                if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {
                    String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                    for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                        if (deptId.equals(auxiliaryDeptArr[i])) {
                            user.add(manager);
                        }
                    }
                }

            }
        } else if (("3".equals(roleId) || "14".equals(roleId)) && !deptId.equals(opdepId)) {//不是本部门档案
            boolean flag = false;//是否能审批
            //查询是否是辅部门档案（即自己是否有权结束审批）
            String auxiliaryDept = sysUser.getAuxiliaryDept();
            if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {
                String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                    if (opdepId.equals(auxiliaryDeptArr[i])) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {//当前用户可以审批（最终节点）
                user = sysUserService.selectNextUserByuserId(sysUser.getUserId());
                for (SysUser o : user) {
                    o.setUserName("end");
                }
            } else {//查询上级审批人员
                user = sysUserService.selectNextUserByroleId("14", opdepId);
                //查询其他有权审批的部门经理
                List<SysUser> managers = sysUserService.selectNextUserByrole("14");
                for (SysUser manager : managers) {
                    auxiliaryDept = manager.getAuxiliaryDept();
                    if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {
                        String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                        for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                            if (opdepId.equals(auxiliaryDeptArr[i])) {
                                user.add(manager);
                            }
                        }
                    }

                }
            }
        } else if ("3".equals(roleId) || "14".equals(roleId)) {
            //判断是否为行档案
            if ("1".equals(amsBatch.getArcHasMoveBank())) {
                //行档案提交行档案管理员
                user = sysUserService.selectNextUserByrole("4");
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
        mmap.put("amsBorrowInfo", amsBorrowInfo);
        mmap.put("amsProcessInfo", amsProcessInfo);

        mmap.put("user", user);
        mmap.put("sysUserId", sysUser.getUserId());
        return prefix + "/processApproval";
    }

    /**
     * 保存审批处理
     */
    @RequiresPermissions("myTask:waitProcessInfo:edit")
    @Log(title = "审批", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsProcessInfo amsProcessInfo) {
        SysUser nowSmUser = ShiroUtils.getSysUser();

        AmsApproveInfo amsApproveInfo = new AmsApproveInfo();
        amsApproveInfo.setExaAppTime(new java.sql.Timestamp(System.currentTimeMillis()));
        amsApproveInfo.setExaAppId(amsProcessInfo.getExaAppId());
        amsApproveInfo.setExaAppOpNo(nowSmUser.getUserId().toString());
        amsApproveInfo.setExaAppOpName(nowSmUser.getUserName());
        amsApproveInfo.setExaAppOrgNo(nowSmUser.getDeptId().toString());
        amsApproveInfo.setExaAppOrgName(nowSmUser.getDept().getDeptName());
        amsApproveInfo.setExaAppOpnion(amsProcessInfo.getNowDispOpnion());
        amsApproveInfo.setExaAppResult("10");
        amsApproveInfo.setExaBack("10");
        amsApproveInfo.setAgentOpNo(amsProcessInfo.getAppOpNo());
        amsApproveInfo.setAgentOpName(nowSmUser.getUserName());
        amsApproveInfo.setAgentFlag("0");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        amsApproveInfo.setId(uuid);
        amsApproveInfoService.insertAmsApproveInfo(amsApproveInfo);
        AmsProcessInfo amsProcessInfo1 = amsProcessInfoService.selectAmsProcessInfoById(amsProcessInfo.getId());
        //添加代理人
        amsProcessInfo1.setPreDispOpNo(ShiroUtils.getUserId().toString());
        amsProcessInfo1.setPreDispOpName(ShiroUtils.getSysUser().getUserName());
        amsProcessInfo1.setPreDispOrgNo(ShiroUtils.getSysUser().getDeptId().toString());
        amsProcessInfo1.setPreDispOrgName(ShiroUtils.getSysUser().getDept().getDeptName());
        amsProcessInfo1.setPreDispOpnion(amsProcessInfo.getNowDispOpnion());
        amsProcessInfo1.setPreDispTime(new Date());
        amsProcessInfo1.setExaAppStatus(ArcCode.PROCESSING.getCode());
        amsProcessInfo1.setNowProcessId(UUID.randomUUID().toString().replaceAll("-", ""));
        amsProcessInfo1.setFoSearch(ArcCode.FORSEARCH.getCode());
        //查询下一审批节点人
        SysUser nextSysuer = sysUserService.selectUserById(Long.valueOf(amsProcessInfo.getNowDispOpNo()));
        amsProcessInfo1.setNowDispOpNo(nextSysuer.getUserId().toString());
        amsProcessInfo1.setNowDispOpName(nextSysuer.getUserName());
        amsProcessInfo1.setExaBack("10");
        String exaapptype = amsProcessInfo.getExaAppType();
        amsProcessInfo1.setExaAppType("电子调阅".equals(exaapptype) ? "10" : "20");
        // 添加下一审批节点人
        amsProcessInfo1.setNowDispOrgNo(nextSysuer.getDeptId().toString());
        amsProcessInfo1.setNowDispOrgName(nextSysuer.getDept().getDeptName());
        amsProcessInfo1.setNowDispOpnion(amsProcessInfo.getNowDispOpnion());
        amsProcessInfo1.setNowDispTime(new java.sql.Timestamp(System.currentTimeMillis()));
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoByexaAppId(amsProcessInfo1.getExaAppId());


        //
        //通过档案登记服务获取 档案登记表
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesByBatchId(amsProcessInfo1.getBrachId());
        //著录Id
        String banchId = amsArchives.getBatchId();
        //著录信息
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(banchId);
        //档案所属部门号
        String opdepId = amsArchives.getOpDepNo();
        //用户部门号
        String deptId = nowSmUser.getDeptId().toString();
        //

        //获取该用户最高级别角色
        List<SysRole> roleList = nowSmUser.getRoles();
        List list = new ArrayList();
        for (SysRole o : roleList) {
            list.add(o.getRoleId());
        }
        list.remove(23L);
        String roleId = Collections.max(list).toString();
        //获得当前借阅档案是电子档案还是实体档案，或是电子实体并存
        //20：实体档案    10：电子档案
        String arcType = amsArchives.getArcType();
        //03：电子档案
        String carrier = amsArchives.getCarrier();
        if ("4".equals(roleId)) {
            //改审批状态为已审批
            amsProcessInfo1.setExaAppStatus("30");
            //改审批结果为同意
            amsProcessInfo1.setExaAppResult("10");
            amsProcessInfo1.setExaAppEndTime(new java.sql.Timestamp(System.currentTimeMillis()));
            //改借阅状态为审批通过
            amsBorrowInfo.setStatus("2");
            if ("10".equals(arcType) && "03".equals(carrier)) {
                //纯电子
                //档案状态 已入库
                amsArchives.setStatus("5");
            } else {
                //电子实体并存
                //查询该档案的实体档案是否已经借阅
                String arcNo = amsArchives.getArcNo();
                AmsBorrowInfo borrow = new AmsBorrowInfo();
                borrow.setArcNo(arcNo);
                //申请实体借阅
                borrow.setBorType("20");
                //借阅状态：审批中
                borrow.setStatus("1");
                List borrowList = amsBorrowInfoService.selectAmsBorrowInfoList(borrow);
                if (borrowList.isEmpty()) {
                    //审批通过
                    borrow.setStatus("2");
                    borrowList = amsBorrowInfoService.selectAmsBorrowInfoList(borrow);
                    if (borrowList.isEmpty()) {
                        //该档案实体未被借阅
                        amsArchives.setStatus("5");
                    }
                }
            }
        } else if ("14".equals(roleId)) {
            //判断是否是行档案
            if ("1".equals(amsBatch.getArcHasMoveBank())) {
                //行档案标记审批状态为审批中
                //审批状态审批中
                amsProcessInfo1.setExaAppStatus("20");
                //审批结果同意
                amsProcessInfo1.setExaAppResult("10");
                amsProcessInfo1.setExaAppEndTime(new java.sql.Timestamp(System.currentTimeMillis()));
                //借阅状态:审批中
                amsBorrowInfo.setStatus("1");
                if (!"7".equals(amsBorrowInfo.getArcStatus())) {
                    //档案状态：借阅申请中
                    amsBorrowInfo.setArcStatus("2");
                }
            } else {
                //部门档案
                List<String> deptList = new ArrayList<>();
                deptList.add(deptId);
                String auxiliaryDept = nowSmUser.getAuxiliaryDept();
                if (auxiliaryDept != null || !"".equals(auxiliaryDept)) {
                    String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                    for (String auxiliaryDeptId : auxiliaryDeptArr) {
                        deptList.add(auxiliaryDeptId);
                    }
                }
                if (deptList.contains(opdepId)) {
                    //是本部门或者辅部门档案流程结束
                    //审批状态：已审批
                    amsProcessInfo1.setExaAppStatus("30");
                    //审批结果：同意
                    amsProcessInfo1.setExaAppResult("10");
                    amsProcessInfo1.setExaAppEndTime(new java.sql.Timestamp(System.currentTimeMillis()));
                    //借阅状态：审批通过
                    amsBorrowInfo.setStatus("2");
                    if (!"7".equals(amsBorrowInfo.getArcStatus())) {
                        //档案状态：借阅申请中
                        amsBorrowInfo.setArcStatus("2");
                    }
                    if ("10".equals(arcType) && "03".equals(carrier)) {
                        //纯电子
                        //档案状态 已入库
                        amsArchives.setStatus("5");
                    } else {
                        //电子实体并存
                        //查询该档案的实体档案是否已经借阅
                        String arcNo = amsArchives.getArcNo();
                        AmsBorrowInfo borrow = new AmsBorrowInfo();
                        borrow.setArcNo(arcNo);
                        //申请实体借阅
                        borrow.setBorType("20");
                        //借阅状态：审批中
                        borrow.setStatus("1");
                        List borrowList = amsBorrowInfoService.selectAmsBorrowInfoList(borrow);
                        if (borrowList.isEmpty()) {
                            //审批通过
                            borrow.setStatus("2");
                            borrowList = amsBorrowInfoService.selectAmsBorrowInfoList(borrow);
                            if (borrowList.isEmpty()) {
                                //该档案实体未被借阅
                                amsArchives.setStatus("5");
                            }
                        }
                    }
                } else {
                    //非本部门档案
                    //审批状态：审批中
                    amsProcessInfo1.setExaAppStatus("20");
                    //审批结果：同意
                    amsProcessInfo1.setExaAppResult("10");
                    amsProcessInfo1.setExaAppEndTime(new java.sql.Timestamp(System.currentTimeMillis()));
                    //借阅状态：审批中
                    amsBorrowInfo.setStatus("1");
                    if (!"7".equals(amsBorrowInfo.getArcStatus())) {
                        //档案状态：借阅申请中
                        amsBorrowInfo.setArcStatus("2");
                    }
                }
            }
        }

        amsBorrowInfoService.updateAmsBorrowInfo(amsBorrowInfo);
        amsArchivesService.updateAmsArchives(amsArchives);
        int rows = amsProcessInfoService.updateAmsProcessInfo(amsProcessInfo1);
        return toAjaxNew(rows, amsProcessInfo1.getId());
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
     * 删除审批
     */
    @RequiresPermissions("myTask:waitProcessInfo:remove")
    @Log(title = "审批", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsProcessInfoService.deleteAmsProcessInfoByIds(ids));
    }

    @RequiresPermissions("myTask:waitProcessInfo:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmp) {
        AmsProcessInfoVO amsProcessInfo = amsProcessInfoService.selectAmsProcessVOInfoById(id);
        mmp.put("amsProcessInfo", amsProcessInfo);
        return prefix + "/detail";
    }

    /**
     * 通过邮件处理审批
     */
    @GetMapping("/editByEmail")
    public String editByEmail(HttpServletRequest request, ModelMap mmap) {
        String id = request.getParameter("id");
        //通过审批服务（通过id查询审批信息方法拿到 审批信息对象
        AmsProcessInfo amsProcessInfo = amsProcessInfoService.selectAmsProcessInfoById(id);
        //通过档案借阅历史服务（通过档案编号查询方法）获取 档案借阅信息
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoByexaAppId(amsProcessInfo.getExaAppId());
        //通过档案登记服务获取 档案登记表
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesByBatchId(amsProcessInfo.getBrachId());
        //档案登记
        if (amsBorrowInfo == null) {
            amsProcessInfo.setBorrowId("");
        } else {
            amsProcessInfo.setBorrowId(amsBorrowInfo.getAppReason());
        }
        //审批意见
        amsProcessInfo.setNowDispOpnion("");
        SysUser sysUser = ShiroUtils.getSysUser();
        //获取该用户所有角色信息
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> list = new ArrayList();
        for (SysRole o : roleList) {
            list.add(o.getRoleId());
        }
        list.remove(23L);
        //根据角色id和部门id查询下级审批人
        String roleId = Collections.max(list).toString();
        //用户部门号
        String deptId = sysUser.getDeptId().toString();
        //档案所属部门号
        String opdepId = amsArchives.getOpDepNo();
        //著录Id
        String banchId = amsArchives.getBatchId();
        //载体形式 20：实体档案    10：电子档案
        String arcType = amsArchives.getArcType();
        //档案类型 03：电子档案
        String carrier = amsArchives.getCarrier();
        //著录信息
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(banchId);
        List<SysUser> user = new ArrayList<>();

        /* 审批判断提交下一节点审批人逻辑 */
        if ("2".equals(roleId) || "3".equals(roleId)) {
            //普通用户和部门档案管理员都先向本部门部门经理提交申请
            user = sysUserService.selectNextUserByroleId("14", deptId);
            //查询其他有权审批的部门经理
            String auxiliaryDept = sysUser.getAuxiliaryDept();
            List<SysUser> managers = sysUserService.selectNextUserByrole("14");
            for (SysUser manager : managers) {
                auxiliaryDept = manager.getAuxiliaryDept();
                if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {
                    String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                    for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                        if (deptId.equals(auxiliaryDeptArr[i])) {
                            user.add(manager);
                        }
                    }
                }

            }
        } else if ("14".equals(roleId)) {
            //判断档案是否属自己部门
            if (!(deptId.equals(opdepId))) {
                //不是自己部门档案提交至档案所在部门部门经理
                boolean flag = false;//是否能审批
                //查询是否是辅部门档案（即自己是否有权结束审批）
                String auxiliaryDept = sysUser.getAuxiliaryDept();
                if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {
                    String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                    for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                        if (opdepId.equals(auxiliaryDeptArr[i])) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {//当前用户可以审批（最终节点）
                    user = sysUserService.selectNextUserByuserId(sysUser.getUserId());
                    for (SysUser o : user) {
                        o.setUserName("end");
                    }
                } else {//查询上级审批人员
                    user = sysUserService.selectNextUserByroleId("14", opdepId);
                    //查询其他有权审批的部门经理
                    List<SysUser> managers = sysUserService.selectNextUserByrole("14");
                    for (SysUser manager : managers) {
                        auxiliaryDept = manager.getAuxiliaryDept();
                        if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {
                            String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                            for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                                if (opdepId.equals(auxiliaryDeptArr[i])) {
                                    user.add(manager);
                                }
                            }
                        }

                    }
                }
            } else {
                //判断是否为行档案
                if ("1".equals(amsBatch.getArcHasMoveBank())) {
                    //行档案提交行档案管理员
                    user = sysUserService.selectNextUserByrole("4");
                } else {
                    //部门经理标记为审批流程最终节点
                    user = sysUserService.selectNextUserByuserId(sysUser.getUserId());
                    for (SysUser o : user) {
                        o.setUserName("end");
                    }
                }
            }
        } else if ("4".equals(roleId)) {
            if (!("10".equals(arcType)) && !("03".equals(carrier))) {
                //不是纯电子档案（即含有实物形式）行档案管理员的下一节点审批人为档案所在部门部门管理员
                user = sysUserService.selectNextUserByroleId("14", opdepId);
            } else {
                //纯电子档案 行档案管理员标记为最终节点
                user = sysUserService.selectNextUserByuserId(sysUser.getUserId());
                for (SysUser o : user) {
                    o.setUserName("end");
                }
            }
        }
        mmap.put("amsBorrowInfo", amsBorrowInfo);
        mmap.put("amsProcessInfo", amsProcessInfo);

        mmap.put("user", user);
        mmap.put("sysUserId", sysUser.getUserId());
        return prefix + "/processApprovalByEmail";

    }

    /**
     * 邮件登录
     */
    @GetMapping("/loginByEmail")
    public String loginByEmail(HttpServletRequest request, ModelMap mmap) {
        String id = request.getParameter("id");
        mmap.put("id", id);
        return "/loginByEmail";
    }
}
