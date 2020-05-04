package net.northking.iacmp.ams.web.controller.amsarcreport;

import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.ams.web.enums.ArcCode;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;

import net.northking.iacmp.common.bean.vo.ams.AmsProcessInfoVO;
import net.northking.iacmp.common.bean.vo.ams.AmsReportVO;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.*;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysUserService;

import net.northking.iacmp.utils.MyExcelUtil;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import net.northking.iacmp.ams.web.utils.SendMailUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 档案借阅 信息操作处理
 *
 * @author wxy
 * @date 2019-08-07
 */
@Controller
@RequestMapping("/amsArcReportcontroller/amsBorrowInfo")
@Setter
@Getter
public class AmsBorrowInfoController extends BaseController {
    private static final String FILLING_TIME_GT = "fillingTime_gt";
    private static final String FILLING_TIME_LT = "fillingTime_lt";
    private static final String ORG_NAME_SEARCH = "orgNameSearch";
    private static final String ORG_CODE_SEARCH = "orgCodeSearch";
    private static final String ARC_BILL_NAME_SEARCH = "arcBillNameSearch";
    private static final String ARC_BILL_CODE_SEARCH = "arcBillCodeSearch";
    private String prefix = "amsArcReportcontroller/amsBorrowInfo";

    @Autowired
    private IAmsBorrowInfoService amsBorrowInfoService;
    @Autowired
    private IAmsBillService amsBillService;
    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IAmsProcessInfoService amsProcessInfoService;
    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private IAmsApproveInfoService amsApproveInfoService;

    @Autowired
    private IAmsSendEmailService amsSendEmailService;

    /**
     * 导出Excel数据
     */
    List rows;

    public String clearSpace(String str) {


        String newStr = str.replaceAll(" ", "");

        return newStr;
    }

    @RequiresPermissions("amsArcReportController:amsBorrowInfo:view")
    @GetMapping()
    public String amsBorrowInfo() {
        return prefix + "/amsBorrowInfo";
    }

    /**
     * 查询档案借阅列表
     */
    @RequiresPermissions("amsArcReportController:amsBorrowInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBorrowInfo amsBorrowInfo) {
        startPage();
        List<AmsBorrowInfo> list = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo);
        return getDataTable(list);
    }

    @RequiresPermissions("amsArcReportcontroller:amsBorrowTatol:view")
    @GetMapping("/prequeryResultForm")
    public String prequeryResultForm() {
        return prefix + "/borrowReportTotal";
    }

    /**
     * 查询档案利用统计表
     */
    @PostMapping("/queryResultFormTable")
    @ResponseBody
    public Map queryResultFormTable(AmsReportVO amsReportVO) {
        String fillingTimeGt = amsReportVO.getFillingTimeGt();
        String fillingTimeLt = amsReportVO.getFillingTimeLt();
        String orgNameSearch = amsReportVO.getOrgName();
        String orgCodeSearch = amsReportVO.getOrgCode();
        String arcBillNameSearch = amsReportVO.getArcBill();
        String arcBillCodeSearch = amsReportVO.getArcBillCode();
        List resultList = new LinkedList<>();
        List<String> arcBillCodeSortList = new ArrayList<>();
        Map map = new HashMap();

        arcBillCodeSearch = arcBillCodeSearch.replaceAll(" ", "");

//        第一步去空
        orgNameSearch = orgNameSearch.replaceAll(" ", "");
        orgCodeSearch = orgCodeSearch.replaceAll(" ", "");

//        第二步判断并拼listNameCodeOrgan
        List<String> listNameCodeOrgan;
        if (!orgCodeSearch.equals("")) {
            String temp = "[" + orgNameSearch + ", " + orgCodeSearch + "]";

            listNameCodeOrgan = new LinkedList<>();
            listNameCodeOrgan.add(temp);

        } else {
            //查询所有部门的名字和code
            listNameCodeOrgan = amsBillService.queryOrganNameAndCode();
        }


        //查询所有档案类型的名字和code
        List<String> listArcBillandCode;
        if (!"".equals(arcBillCodeSearch)) {
            String temp = "[" + arcBillCodeSearch + ", " + arcBillNameSearch + "]";

            listArcBillandCode = new LinkedList<>();
            listArcBillandCode.add(temp);
        } else {
            listArcBillandCode = amsBillService.queryArcBillAndCode();
        }


        List resultListByOneOrgan;
        //全部部门名称
        List<String> orgNameList = new ArrayList<>();
        List orgCodeList = new ArrayList();//全部部门
        for (int i = 0; i < listNameCodeOrgan.size(); i++) {
            //保存所有档案类型的数组
            String[] organArr = listNameCodeOrgan.get(i).substring(1, listNameCodeOrgan.get(i).length() - 1).split(",");
            organArr[1] = organArr[1].replaceAll(" ", "");
            orgCodeList.add(organArr[1]);
            orgNameList.add(organArr[0]);
        }
        for (int j = 0; j < listArcBillandCode.size(); j++) {
            resultListByOneOrgan = new ArrayList<>();
            Map<String, Integer> mapOrgArcBillCount = new HashMap<>();
            //查询该档案类型下的全部子目录
            String[] arcBillCodeArr = listArcBillandCode.get(j).substring(1, listArcBillandCode.get(j).length() - 1).split(",");
            arcBillCodeArr[0] = arcBillCodeArr[0].replaceAll(" ", "");
            resultListByOneOrgan.add(arcBillCodeArr[0]);
            resultListByOneOrgan.add(arcBillCodeArr[1]);
            List<String> treeNodeList = amsBillService.allSonTreeNode(arcBillCodeArr[0]);
            //查询该类型下各个部门档案数量（包含子目录档案）
            List<String> orgArcBillCountList = amsBorrowInfoService.queryBorTypeByOneOrgan(fillingTimeGt, fillingTimeLt, arcBillCodeArr[0], treeNodeList, orgCodeList);
//                mapArcBillCodeToNumber.put(arcBillCodeArr[0],count);
            //各部门档案的各个档案类型默认数量0
            for (int i = 0; i < listNameCodeOrgan.size(); i++) {
                int count = 0;
                //保存所有档案类型的数组
                String[] organArr = listNameCodeOrgan.get(i).substring(1, listNameCodeOrgan.get(i).length() - 1).split(",");
                organArr[0] = organArr[0].replaceAll(" ", "");
                organArr[1] = organArr[1].replaceAll(" ", "");
                mapOrgArcBillCount.put(organArr[1] + ",read", count);
                mapOrgArcBillCount.put(organArr[1] + ",masterCopy", count);
                //正确的arcBillCode的顺序
                arcBillCodeSortList.add(organArr[1] + ",read");
                arcBillCodeSortList.add(organArr[1] + ",masterCopy");
            }
            for (int i = 0; i < orgArcBillCountList.size(); i++) {
                String[] orgArcBillArr = orgArcBillCountList.get(i).substring(1, orgArcBillCountList.get(i).length() - 1).split(",");
                //总量
                orgArcBillArr[0] = orgArcBillArr[0].replaceAll(" ", "");
                //借阅类型
                orgArcBillArr[1] = orgArcBillArr[1].replaceAll(" ", "");
                //部门编号
                orgArcBillArr[2] = orgArcBillArr[2].replaceAll(" ", "");
                String key = orgArcBillArr[0] + "," + orgArcBillArr[2];
                mapOrgArcBillCount.put(key, Integer.parseInt(orgArcBillArr[1]));
            }
            resultListByOneOrgan.add(mapOrgArcBillCount);
            resultList.add(resultListByOneOrgan);
        }

        map.put("listArcBillandCode", listArcBillandCode);
        map.put(ARC_BILL_CODE_SEARCH, arcBillCodeSearch);
        map.put("arcBillCodeSortList", arcBillCodeSortList);

        map.put(ARC_BILL_NAME_SEARCH, arcBillNameSearch);
        map.put(FILLING_TIME_GT, fillingTimeGt);
        map.put(FILLING_TIME_LT, fillingTimeLt);
        map.put(ORG_CODE_SEARCH, orgCodeSearch);
        map.put(ORG_NAME_SEARCH, orgNameSearch);
        map.put("resultList", resultList);
        map.put("orgNameList", orgNameList);
        return map;
    }

    /**
     * 查询二级档案类型
     */
    @PostMapping("/queryArcBillDept")
    @ResponseBody
    public Map queryArcBillDept(AmsReportVO amsReportVO) {
        String fillingTimeGt = amsReportVO.getFillingTimeGt();
        String fillingTimeLt = amsReportVO.getFillingTimeLt();
        String orgNameSearch = amsReportVO.getOrgName();
        String orgCodeSearch = amsReportVO.getOrgCode();
        String arcBillNameSearch = amsReportVO.getArcBill();
        String arcBillCodeSearch = amsReportVO.getSelectArcCode();
//        String arcBillNameSearch = "";
        List<String> arcBillCodeSortList = new ArrayList<>();
        List resultList = new LinkedList<>();
        //key:部门map（部门名，部门编号） value:档案类型map(【档案类型，类型编码】，总量)
        Map map = new HashMap();
        arcBillCodeSearch = arcBillCodeSearch.replaceAll(" ", "");
//        第一步去空
        orgNameSearch = orgNameSearch.replaceAll(" ", "");
        orgCodeSearch = orgCodeSearch.replaceAll(" ", "");
//        第二步判断并拼listNameCodeOrgan
        List<String> listNameCodeOrgan;
        if (!orgCodeSearch.equals("")) {
            String temp = "[" + orgNameSearch + ", " + orgCodeSearch + "]";
            listNameCodeOrgan = new LinkedList<>();
            listNameCodeOrgan.add(temp);
        } else {
            //查询所有部门的名字和code
            listNameCodeOrgan = amsBillService.queryOrganNameAndCode();
        }
        //查询当前档案类型下全部二级类型
        List<String> listArcBillandCode = amsBillService.queryArcBillDept(arcBillCodeSearch);
        List resultListByOneOrgan;
//全部部门名称
        List<String> orgNameList = new ArrayList<>();
        List orgCodeList = new ArrayList();//全部部门
        for (int i = 0; i < listNameCodeOrgan.size(); i++) {
            //保存所有档案类型的数组
            String[] organArr = listNameCodeOrgan.get(i).substring(1, listNameCodeOrgan.get(i).length() - 1).split(",");
            organArr[1] = organArr[1].replaceAll(" ", "");
            orgCodeList.add(organArr[1]);
            orgNameList.add(organArr[0]);
        }
        for (int j = 0; j < listArcBillandCode.size(); j++) {
//            arcBillCodeSortList = new ArrayList<>();
            resultListByOneOrgan = new ArrayList<>();
            Map<String, Integer> mapOrgArcBillCount = new HashMap<>();
            String arcBillCode = "";
            //查询该档案类型下的全部子目录
            String[] arcBillCodeArr = listArcBillandCode.get(j).substring(1, listArcBillandCode.get(j).length() - 1).split(",");
            arcBillCodeArr[0] = arcBillCodeArr[0].replaceAll(" ", "");
            arcBillCodeArr[1] = arcBillCodeArr[1].replaceAll(" ", "");
            resultListByOneOrgan.add(arcBillCodeArr[0]);
            resultListByOneOrgan.add(arcBillCodeArr[1]);
            if (arcBillCodeArr.length > 2) {//有子目录
                arcBillCode = arcBillCodeArr[1];
            } else {//该档案类型是叶子节点
                arcBillCode = arcBillCodeArr[0];
            }
            List<String> treeNodeList = amsBillService.allSonTreeNode(arcBillCode);
            //查询该类型下各个部门档案数量（包含子目录档案）
            List<String> orgArcBillCountList = amsBillService.queryNumberArcBySecondOrganBorrow(fillingTimeGt, fillingTimeLt, treeNodeList, orgCodeList);
//                mapArcBillCodeToNumber.put(arcBillCodeArr[0],count);
            //各部门档案的各个档案类型默认数量0
            for (int i = 0; i < listNameCodeOrgan.size(); i++) {
                int count = 0;
                //保存所有档案类型的数组
                String[] organArr = listNameCodeOrgan.get(i).substring(1, listNameCodeOrgan.get(i).length() - 1).split(",");
                organArr[0] = organArr[0].replaceAll(" ", "");
                organArr[1] = organArr[1].replaceAll(" ", "");
                mapOrgArcBillCount.put(organArr[1] + ",read", count);
                mapOrgArcBillCount.put(organArr[1] + ",masterCopy", count);
                //正确的arcBillCode的顺序
                arcBillCodeSortList.add(organArr[1] + ",read");
                arcBillCodeSortList.add(organArr[1] + ",masterCopy");
            }
            for (int i = 0; i < orgArcBillCountList.size(); i++) {
                String[] orgArcBillArr = orgArcBillCountList.get(i).substring(1, orgArcBillCountList.get(i).length() - 1).split(",");
                //总量
                orgArcBillArr[0] = orgArcBillArr[0].replaceAll(" ", "");
                //借阅类型
                orgArcBillArr[1] = orgArcBillArr[1].replaceAll(" ", "");
                //部门编号
                orgArcBillArr[2] = orgArcBillArr[2].replaceAll(" ", "");
                String key = orgArcBillArr[0] + "," + orgArcBillArr[2];
                mapOrgArcBillCount.put(key, Integer.parseInt(orgArcBillArr[1]));
            }
            resultListByOneOrgan.add(mapOrgArcBillCount);
            resultList.add(resultListByOneOrgan);
        }
        map.put("listArcBillandCode", listArcBillandCode);
        map.put(ARC_BILL_CODE_SEARCH, arcBillCodeSearch);
        map.put("arcBillCodeSortList", arcBillCodeSortList);
        map.put(ARC_BILL_NAME_SEARCH, arcBillNameSearch);
        map.put(FILLING_TIME_GT, fillingTimeGt);
        map.put(FILLING_TIME_LT, fillingTimeLt);
        map.put(ORG_CODE_SEARCH, orgCodeSearch);
        map.put(ORG_NAME_SEARCH, orgNameSearch);
        map.put("resultList", resultList);
        map.put("orgNameList", orgNameList);
        return map;
    }

    /**
     * 导出档案借阅列表
     */
    @RequiresPermissions("amsArcReportController:amsBorrowInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBorrowInfo amsBorrowInfo) {
        List<AmsBorrowInfo> list = amsBorrowInfoService.selectAmsBorrowInfoList(amsBorrowInfo);
        ExcelUtil<AmsBorrowInfo> util = new ExcelUtil<>(AmsBorrowInfo.class);
        return util.exportExcel(list, "amsBorrowInfo");
    }

    /**
     * 新增档案借阅
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案借阅
     */
    @RequiresPermissions("amsArcReportController:amsBorrowInfo:add")
    @Log(title = "档案借阅", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBorrowInfo amsBorrowInfo) {
        AmsProcessInfo amsProcessInfo = new AmsProcessInfo();
        amsBorrowInfo.setAppTime(new java.sql.Timestamp(System.currentTimeMillis()));
        amsBorrowInfo.setStatus("1");
        AmsArchivesVO amsArchives = new AmsArchivesVO();
        amsArchives.setArcNo(amsBorrowInfo.getArcNo());
        List<AmsArchives> amsArchivesList = amsArchivesService.selectAmsArchivesList(amsArchives);
        //档案状态
        String arcStatus = amsArchivesList.get(0).getStatus();
        if (!"7".equals(arcStatus)) {
            //如果该档案是已出库状态，则申请电子借阅时，档案状态不变
            amsArchivesList.get(0).setStatus("2");
            //档案借阅状态为借阅申请中
            amsBorrowInfo.setArcStatus("2");
        } else {
            //档案借阅状态为借阅申请中
            amsBorrowInfo.setArcStatus("7");
        }

        //申请人姓名
        amsBorrowInfo.setAppOpName(ShiroUtils.getSysUser().getUserName());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        amsBorrowInfo.setId(uuid);
        String exaAppId = "";
        if (Constants.AMS_APPLY_ELE.equals(amsBorrowInfo.getBorType())) {
            //电子档案借阅
            amsProcessInfo = this.addProcess(amsBorrowInfo.getAgentCode(), amsBorrowInfo.getBorType(), amsBorrowInfo.getArchiveId(), ArcCode.ARCH_ELE_CONSULT.getCode());
        } else if (Constants.AMS_APPLY_ENTITY.equals(amsBorrowInfo.getBorType())) {
            //实物档案借阅
            amsProcessInfo = this.addProcess(amsBorrowInfo.getAgentCode(), amsBorrowInfo.getBorType(), amsBorrowInfo.getArchiveId(), ArcCode.ARCH_OBJECT_CONSULT.getCode());
        }
        exaAppId = amsProcessInfo.getExaAppId();
//        newAmsProcessInfo = amsProcessInfo;
        amsBorrowInfo.setExaAppInfoId(exaAppId);
        // 添加二级类目
        amsBorrowInfo.setArcBillDeptCode(amsBorrowInfo.getArcBillDeptCode());
        amsBorrowInfo.setArcBillDeptName(amsBorrowInfo.getArcBillDeptName());
        int row = amsBorrowInfoService.insertAmsBorrowInfo(amsBorrowInfo);
        if (row > 0) {
            //保存成功，发送email，失败则不发送
            //借阅申请成功提交后，修改档案状态、
            amsArchivesService.updateAmsArchives(amsArchivesList.get(0));
        }
        return toAjaxNew(row, amsProcessInfo.getId());
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
     * 新建待审批流程
     *
     * @param nowDisOpNo 下一节点处理人编号
     * @param exaType    (流程编码,遵循数据库存储定义:电子档案借阅流程为10;实物档案借阅为20;)
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
        return amsProcessInfo;
    }

    /**
     * 审批提交时新增意见表
     *
     * @param exAppId
     * @param opnion
     * @return
     * @throws Exception
     */
    public synchronized void submitInfo(boolean startNode, String exAppId, String opnion) {
        boolean agentFlag = false;
        SysUser loginUser = ShiroUtils.getSysUser();
        SysUser exaUser;
        if (startNode) {
            exaUser = loginUser;
            agentFlag = false;
        } else {
            exaUser = this.getIfAgent(loginUser, exAppId);
            if (loginUser != exaUser) {
                agentFlag = true;
            }
        }

        AmsApproveInfo amsApproveInfo = new AmsApproveInfo();
        amsApproveInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        amsApproveInfo.setExaAppId(exAppId);
        amsApproveInfo.setExaAppOpName(exaUser.getUserName());
        amsApproveInfo.setExaAppOpNo(exaUser.getUserId().toString());
        amsApproveInfo.setExaAppOpnion(opnion);
        amsApproveInfo.setExaAppOrgName(exaUser.getDept().getDeptName());
        amsApproveInfo.setExaAppOrgNo(exaUser.getDeptId().toString());
        amsApproveInfo.setExaAppResult("10");
        amsApproveInfo.setExaBack("10");
        amsApproveInfo.setExaAppTime(new java.sql.Timestamp(System.currentTimeMillis()));
        if (agentFlag) {
            amsApproveInfo.setAgentFlag("1");
            amsApproveInfo.setAgentOpNo(exaUser.getUserId() + "(" + loginUser.getUserId() + ")");
            amsApproveInfo.setAgentOpName(exaUser.getUserName() + "（" + loginUser.getUserName() + "代）");
        } else {
            amsApproveInfo.setAgentFlag("0");
            amsApproveInfo.setAgentOpNo(exaUser.getUserId().toString());
            amsApproveInfo.setAgentOpName(exaUser.getUserName());
        }
        amsApproveInfoService.insertAmsApproveInfo(amsApproveInfo);
    }

    /**
     * 获取当前处理人是否是流程代理人
     *
     * @param smUser
     * @param exaAppId
     * @return
     */
    public SysUser getIfAgent(SysUser smUser, String exaAppId) {
        SysUser preSmSuer = new SysUser();
        AmsProcessInfoVO amsProcessInfo = new AmsProcessInfoVO();
        amsProcessInfo.setExaAppId(exaAppId);
        amsProcessInfo = this.amsProcessInfoService.selectAmsProcessInfoList(amsProcessInfo).get(0);
        amsProcessInfo.getNowDispOpNo();
        if (smUser.getUserId().equals(Long.valueOf(amsProcessInfo.getNowDispOpNo()))) {
            return smUser;
        } else {
            preSmSuer.setUserId(Long.valueOf(amsProcessInfo.getNowDispOpNo()));
            preSmSuer = this.sysUserService.selectUserList(preSmSuer).get(0);
            return preSmSuer;
        }
    }

    /**
     * 修改档案借阅
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoById(id);
        mmap.put("amsBorrowInfo", amsBorrowInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案借阅
     */
    @RequiresPermissions("amsArcReportController:amsBorrowInfo:edit")
    @Log(title = "档案借阅", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBorrowInfo amsBorrowInfo) {
        return toAjax(amsBorrowInfoService.updateAmsBorrowInfo(amsBorrowInfo));
    }

    /**
     * 删除档案借阅
     */
    @RequiresPermissions("amsArcReportController:amsBorrowInfo:remove")
    @Log(title = "档案借阅", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBorrowInfoService.deleteAmsBorrowInfoByIds(ids));
    }


    /**
     * 导出档案移交统计表
     */
    @PostMapping("/exportExcel")
    @ResponseBody
    public AjaxResult exportExcel(@RequestBody Map<String, Object> rowsList) {
        rows = (List) rowsList.get("rowsList");
        return toAjax(1);
    }

    @RequestMapping("/startExport")
    public void startExport(HttpServletResponse response) {
        if (rows == null) {
            return;
        }
        MyExcelUtil myExcelUtil = new MyExcelUtil();
        String title = "档案利用统计表";
        HSSFWorkbook workbook = myExcelUtil.getHSSFWorkbookGroupByBorType(rows, title);
        String fileName = "档案利用统计表.xls";
        myExcelUtil.exportExc(response, workbook, fileName);
    }

}
