package net.northking.iacmp.ams.web.controller.mytask;


import lombok.Getter;
import lombok.Setter;
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
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysDeptService;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.bean.BeanUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 我的利用------>档案接收管理
 * 操作表————————>ams_batch
 *
 * @author wxy
 * @date 2019-08-07
 */
@Controller
@RequestMapping("/myTask/archReceive")
public class ArchReceiveController extends BaseController {
    private String prefix = "myTask/archReceive";

    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private IImImageService imageService;
    @Autowired
    private IImFileService fileService;
    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IAmsBoxService amsBoxService;
    @Setter
    @Getter
    private Integer yongYinTotal;
    @Autowired
    private IAmsCabinetService amsCabinetService;
    @Autowired
    private IAmsDepotService amsDepotService;
    @Autowired
    private IAmsBillService amsBillService;

    @Autowired
    private IAmsArcRegService amsArcRegService;

    @RequiresPermissions("myTask:archReceive:view")
    @GetMapping()
    public String amsBatch() {
        return prefix + "/amsBatch";
    }

    /**
     * 查询档案接收管理列表
     */
    @RequiresPermissions("myTask:archReceive:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBatch amsBatch) {
        SysUser sysUser = ShiroUtils.getSysUser();
        amsBatch.setReceiveNo(sysUser.getUserId().toString());
        List<AmsBatch> list;
        if (null == amsBatch.getStatus() || amsBatch.getStatus().isEmpty()) {
            amsBatch.setStatus("2");
        } else {
            amsBatch.setStatus(amsBatch.getStatus());
        }
        startPage();
        list = amsBatchService.selectarchReceiveList(amsBatch);
        return getDataTable(list);
    }


    /**
     * 导出档案著录列表
     */
    @RequiresPermissions("myTask:archReceive:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBatch amsBatch) {
        List<AmsBatch> list = amsBatchService.selectAmsBatchList(amsBatch);
        ExcelUtil<AmsBatch> util = new ExcelUtil<>(AmsBatch.class);
        return util.exportExcel(list, "amsBatch");
    }

    /**
     * 新增档案著录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案著录
     */
    @RequiresPermissions("myTask:archReceive:add")
    @Log(title = "档案著录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBatch amsBatch) {
        return toAjax(amsBatchService.insertAmsBatch(amsBatch));
    }

    /**
     * 打印标签
     */
    @GetMapping("/printlable/{id}")
    public String printLable(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/printlable";
    }

    /**
     * 查看档案详细
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("myTask:amsBatch:detail")
    public String detail(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        String arcBillCode = amsBatch.getArcBillDeptCode();
        AmsBill amsBill = amsBillService.selectAmsBillById(arcBillCode);
        mmap.put("mouldStr", amsBill.getMouldStr());
        mmap.put("amsBatch", amsBatch);
        return prefix + "/detail";
    }

    /**
     * ajax通过id取值
     *
     * @param id
     * @return
     */
    @GetMapping("/getDetail/{id}")
    @ResponseBody
    @RequiresPermissions("myTask:amsBatch:detail")
    public AmsBatch getDetail(@PathVariable("id") String id) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        return amsBatch;
    }

    /**
     * 档案接收打印预览
     *
     * @return"
     */
    @RequiresPermissions("archCollection:archReceive:printLook")
    @GetMapping("/printLook")
    public String printLook(String ids, ModelMap mmap) {

        SysUser loginUser = ShiroUtils.getSysUser();
        List<AmsBatch> list = amsBatchService.selectAmsBatchByIds(ids);
        mmap.put("printList", list);
        mmap.put("loginUser", loginUser);
        return prefix + "/printLook";
    }

    /**
     * 删除档案著录
     */
    @RequiresPermissions("myTask:archReceive:remove")
    @Log(title = "档案著录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBatchService.deleteAmsBatchByIds(ids));
    }

    /**
     * 档案批量接收
     */
    @RequiresPermissions("myTask:archReceive:edit")
    @PostMapping("/bathReceive")
    @ResponseBody
    public AjaxResult bathReceive(String ids) throws Exception {
        int num = 0;
        // 批量接收的bath id
        String[] bathIds = ids.split(",");
        //遍历amsBatch
        for (int i = 0; i < bathIds.length; i++) {
            AmsBatch amsBatch = amsBatchService.selectAmsBatchById(bathIds[i]);
            //实物/声像档案处理
            if (amsBatch.getArcBillCode().equals("11100") || amsBatch.getArcBillCode().equals("12100")) {
                //实物档案入库
                num = storeEntityArchives(amsBatch);
            } else {
                num = amsBatchService.updateAmsBatchByIds(amsBatch.getId());
            }
        }
        return toAjax(num);
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
     * 调转到显示档案类型树页面
     *
     * @return
     */
    @GetMapping("/arcBillTwoTree")
    public String arcBillTwoTree() {
        return prefix + "/arcBillTwoTree";
    }

    /**
     * 修改档案著录
     */
    @RequiresPermissions("myTask:amsBatch:batchedit")
    @GetMapping("/batchedit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/edit";
    }

    /**
     * 编辑-接收页面
     */
    @GetMapping("/myTaskReceive/{id}")
    public String myEditReceive(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/receive";
    }

    /**
     * 编辑-退回页面
     */
    @RequiresPermissions("myTask:myArchReceive:edit")
    @GetMapping("/editBack/{id}")
    public String editBack(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/editBack";
    }

    /**
     * 退回方法
     */
    @Log(title = "档案退回", businessType = BusinessType.UPDATE)
    @PostMapping("/backOp")
    @ResponseBody
    public AjaxResult editSaveReceive(AmsBatch amsBatch, HttpServletRequest request) {
        //设置接收人为档案移交人
        amsBatch.setReceiveName(amsBatch.getCrtName());
        amsBatch.setReceiveNo(amsBatch.getCrtNo());

        amsBatch.setStatus("6");
        return toAjax(amsBatchService.updateAmsBatch(amsBatch));
    }

    /**
     * 接收方法
     */
    @Log(title = "档案接收", businessType = BusinessType.UPDATE)
    @PostMapping("/receiveOp")
    @ResponseBody
    public AjaxResult editSave(AmsBatch amsBatch) throws Exception {
        SysUser sysUser = ShiroUtils.getSysUser();
        int num = 0;
        AmsBatch amsBatch1 = new AmsBatch();
        BeanUtils.copyProperties(amsBatch, amsBatch1);
        amsBatchService.updateAmsBatch(amsBatch1);
        AmsBatch saveObj = amsBatchService.selectAmsBatchById(amsBatch1.getId());
        saveObj.setStatus("3");
        saveObj.setModName(sysUser.getUserName());
        saveObj.setModNo(sysUser.getUserId().toString());
        saveObj.setModTime(new Date());
        saveObj.setReceiveTime(new Date());
        saveObj.setSaveAddress(amsBatch1.getSaveAddress());
        saveObj.setReceivingOpinion(amsBatch1.getReceivingOpinion());

        // 判断是否电子化
        if ("10".equals(saveObj.getArcFormat())) {
            // 是
            List<ImFile> imFileList = fileService.selectImFilesByBatchId(saveObj.getBatchNo());
            if (imFileList == null || imFileList.isEmpty()) {
                List<ImImage> imImageList = imageService.selectImImagesByBatchId(saveObj.getBatchNo());
                if (imImageList == null || imImageList.isEmpty()) {
                    return AjaxResult.error("电子档案请先上传文件");
                }
            }
        }

        //实物/声像档案处理
        if (saveObj.getArcBillCode() != null) {
            if (saveObj.getArcBillCode().equals("11100") || saveObj.getArcBillCode().equals("12100")) {
                //实物档案入库，并将状态修改为已入库
                num = storeEntityArchives(saveObj);
            } else {
                num = amsBatchService.updateAmsBatch(saveObj);
            }
        } else {
            return AjaxResult.error("著录档案类型编码为空");
        }

        return toAjax(num);
    }

    /**
     * 此方法根据全宗号、档案类型、所属年度、保管期限、件号，生成一个唯一档号
     *
     * @param arcBillCode
     * @param valPeriod
     * @param boxNum
     * @param arcCreTime
     * @return
     * @throws Exception
     */
    private String getArcNo(String arcBillCode, String valPeriod, String boxNum, Date arcCreTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(arcCreTime);
        String[] a = date.split("-");
        String no = "000" + boxNum;
        String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        return "0000" + "-" + substring + a[0] + "-" + valPeriod + "-" + no;
    }

    /**
     * 获取6位随机数
     *
     * @return
     */
    private String getRandom() {
        int n = (int) ((Math.random() * 9 + 1) * 100000);
        return String.valueOf(n);
    }

    /**
     * 新建库柜
     *
     * @param sysUser
     * @param depot
     */
    private AmsCabinet createCabinet(SysUser sysUser, AmsDepot depot) throws NullPointerException {
        AmsCabinet cabinet = new AmsCabinet();
        cabinet.setId(UUID.randomUUID().toString().replace("-", ""));
        cabinet.setCode("BX_SW_KG_" + depot.getOrgNo());
        cabinet.setName("实物档案柜");
        cabinet.setStatus("0");
        cabinet.setDepId(depot.getId());
        cabinet.setDepCode(depot.getCode());
        cabinet.setDepName(depot.getName());
        cabinet.setDepStatus(depot.getStatus());
        cabinet.setDepType("20");
        cabinet.setDutyMan(sysUser.getUserName());
        cabinet.setArcType("");
        cabinet.setDepStatus(depot.getStatus());
        int i = amsCabinetService.insertAmsCabinet(cabinet);
        List<AmsCabinet> list = new ArrayList<AmsCabinet>();
        if (i > 0) {
            list = amsCabinetService.selectAmsCabinetList(cabinet);
        }
        AmsCabinet obj = null;
        if (list.size() > 0) {
            obj = list.get(0);
        }
        return obj;
    }

    /**
     * 新建库房
     *
     * @param sysUser
     */
    private AmsDepot createDepot(String orgCode) throws NullPointerException {
        SysUser sysUser = ShiroUtils.getSysUser();
        AmsDepot depot = new AmsDepot();
        depot.setId(UUID.randomUUID().toString().replace("-", ""));
        depot.setName("实物档案库");
        depot.setCode("BX_SW_KF_" + orgCode);
        //库房所属部门
        depot.setOrgNo(orgCode);
        SysDept dept = sysDeptService.selectDeptById(Long.valueOf(orgCode));
        depot.setOrgName(dept.getDeptName());
        depot.setAllNum(99);
        depot.setStatus("0");
        depot.setDepotType("20");
        depot.setAdmin(sysUser.getUserName());
        int i = amsDepotService.insertAmsDepot(depot);
        List<AmsDepot> list = new ArrayList<AmsDepot>();
        if (i > 0) {
            list = amsDepotService.selectAmsDepotList(depot);
        }
        AmsDepot obj = null;
        if (list.size() > 0) {
            obj = list.get(0);
        }
        return obj;
    }

    /**
     * 实物档案入库
     *
     * @param amsBatch
     * @param amsBatch
     * @return
     * @throws Exception
     */
    private int storeEntityArchives(AmsBatch amsBatch) throws Exception {
        SysUser sysUser = ShiroUtils.getSysUser();
        int num;
        AmsArchivesVO amsArchives = new AmsArchivesVO();
        amsArchives.setBatchId(amsBatch.getId());
        List<AmsArchives> archivesList = amsArchivesService.selectAmsArchivesList(amsArchives);
        // 查询档案是否存在
        if (!archivesList.isEmpty()) {
            amsArchives.setId(archivesList.get(0).getId());
            AmsArchives amsArchives1 = new AmsArchives();
            BeanUtils.copyProperties(amsArchives, amsArchives1);
            int up1 = amsArchivesService.updateAmsArchives(amsArchives1);

            amsBatch.setStatus(Constants.ALREADY_PUT_STORAGE); //ALREADY_PUT_STORAGE
            int up2 = amsBatchService.updateAmsBatch(amsBatch);

            //数据更新成功
            if (up1 > 0 && up2 > 0) {
                num = 1;
            } else {
                throw new Exception("批量接收实物档案错误");
            }
        } else {
            SysDept sysDept = sysDeptService.selectDeptById(sysUser.getDeptId());

            String boxNum = getRandom();
            amsArchives.setId(UUID.randomUUID().toString().replace("-", ""));
            amsArchives.setManaDepNo(String.valueOf(sysDept.getDeptId()));
            amsArchives.setManaDepName(sysDept.getDeptName());
            amsArchives.setBatchNo(amsBatch.getBatchNo());
            amsArchives.setViewPath(amsBatch.getViewPath());

            //档案盒Id
            String boxId = UUID.randomUUID().toString().replace("-", "");
            amsArchives.setBoxCode(boxId);

            // 查询库房
            AmsDepot dp = new AmsDepot();
            dp.setName("实物档案库");
            dp.setDepotType("20");
            AmsArcReg arcReg = amsArcRegService.selectAmsArcRegById(amsBatch.getArcNo());
            //移交登记机构代码
            String orgCode = arcReg.getRegOrgCode();
            //库房所属部门
            dp.setOrgNo(orgCode);
            List<AmsDepot> listDepot = amsDepotService.selectAmsDepotList(dp);
            //判断是否已有【实物档案库】
            if (listDepot != null && listDepot.size() > 0) {
                AmsDepot depot = listDepot.get(0);
                // 查询库柜
                AmsCabinet cabinet = new AmsCabinet();
                cabinet.setDepId(depot.getId());
                List<AmsCabinet> cabinetList = amsCabinetService.selectAmsCabinetList(cabinet);
                //库柜
                AmsCabinet amsCab = null;
                //判断库柜列表是否为空
                if (cabinetList != null && cabinetList.size() > 0) {
                    amsCab = cabinetList.get(0);
                } else {
                    //新建库柜
                    amsCab = createCabinet(sysUser, depot);
                    if (amsCab == null) {
                        throw new NullPointerException("新建库柜失败！");
                    }
                }

                //设置档案的库房/库柜
                amsArchives.setDepotId(amsCab.getDepId());
                amsArchives.setDepotNo(amsCab.getDepCode());
                amsArchives.setDepotName(amsCab.getName());
                amsArchives.setCabintId(amsCab.getId());
                amsArchives.setCabintNo(amsCab.getCode());
                amsArchives.setCabintName(amsCab.getName());
                amsArchives.setDepotType(amsCab.getDepType());

            } else {
                //新建库房
                AmsDepot amsDepot = createDepot(orgCode);
                if (null == amsDepot) {
                    throw new NullPointerException("新建库房失败!");
                }
                //新建库柜
                AmsCabinet amsCab = createCabinet(sysUser, amsDepot);
                if (null == amsCab) {
                    throw new NullPointerException("新建库柜失败!");
                }
                //设置档案的库房/库柜
                amsArchives.setDepotId(amsCab.getDepId());
                amsArchives.setDepotNo(amsCab.getDepCode());
                amsArchives.setDepotName(amsCab.getName());
                amsArchives.setCabintId(amsCab.getId());
                amsArchives.setCabintNo(amsCab.getCode());
                amsArchives.setCabintName(amsCab.getName());
                amsArchives.setDepotType(amsCab.getDepType());
            }
            //档案编号
            String arcNo = getArcNo(amsBatch.getArcBillCode(), amsBatch.getValPeriod(), boxNum, amsBatch.getArcCreTime());
            amsArchives.setArcNo(arcNo);

            amsArchives.setOpDepNo(amsBatch.getOrgCode());
            amsArchives.setOpDepName(amsBatch.getOrgName());
            amsArchives.setBatchId(amsBatch.getId());
            amsArchives.setName(amsBatch.getArcName());
            amsArchives.setArcNum(amsBatch.getArcNum());
            amsArchives.setArcPageNum(amsBatch.getArcPageNum());
            amsArchives.setArcLevel(amsBatch.getArcLevel());
            amsArchives.setArcCreTime(amsBatch.getArcCreTime());
            amsArchives.setFilingTime(new Timestamp(System.currentTimeMillis()));
            amsArchives.setArcType(amsBatch.getArcFormat());
            amsArchives.setStatus(Constants.ALREADY_PUT_STORAGE);
            amsArchives.setAdminNo(String.valueOf(sysUser.getUserId()));
            amsArchives.setAdminName(sysUser.getLoginName());
            amsArchives.setFilingDepa(String.valueOf(sysDept.getDeptId()));
            amsArchives.setFilingDepaName(sysDept.getDeptName());
            amsArchives.setArcBillCode(amsBatch.getArcBillCode());
            amsArchives.setArcBillName(amsBatch.getArcBill());
            amsArchives.setValPeriod(amsBatch.getValPeriod());
            amsArchives.setOriginMode(amsBatch.getOriginMode());
            amsArchives.setBill(amsBatch.getBill());
            amsArchives.setServiceType(amsBatch.getServiceType());
            amsArchives.setCarrier(amsBatch.getCarrier());
            amsArchives.setPractType(amsBatch.getPractType());
            amsArchives.setExpenseInvolve(amsBatch.getExpenseInvolve());
            amsArchives.setRespDepaName(amsBatch.getRespDepaName());
            amsArchives.setRespOpName(amsBatch.getRespOpName());
            amsArchives.setTurnInTime(amsBatch.getTurnInTime());
            //  档案件号
            amsArchives.setBoxNum(Integer.valueOf(boxNum));
            amsArchives.setFileNo(amsBatch.getArcCode());
            // 添加部门档案类型
            amsArchives.setArcBillDept(amsBatch.getArcBillDept());
            amsArchives.setArcBillDeptCode(amsBatch.getArcBillDeptCode());
            //根据部门/行档案 设置接收时间
            if ("0".equals(amsBatch.getArcHasMoveBank())) {
                amsArchives.setReceiveTime(amsBatch.getReceiveTime());
            } else if ("1".equals(amsBatch.getArcHasMoveBank())) {
                amsArchives.setReceiveTime(amsBatch.getReceivingTime());
            }
            //档案存放地址
            amsArchives.setStorageLocation(amsBatch.getSaveAddress());
            AmsArchives amsArchives1 = new AmsArchives();
            BeanUtils.copyProperties(amsArchives, amsArchives1);
            int up1 = amsArchivesService.insertAmsArchives(amsArchives1);

            //  更新著录批次表
            amsBatch.setArcNo(arcNo);
            amsBatch.setBoxId(boxId);
            amsBatch.setFilingDepa(sysDept.getDeptName());
            amsBatch.setFilingDepaCode(String.valueOf(sysDept.getDeptId()));
            amsBatch.setFilingTime(new Timestamp(System.currentTimeMillis()));
            amsBatch.setBoxOpCode(String.valueOf(sysUser.getUserId()));
            amsBatch.setBoxOpName(sysUser.getUserName());
            amsBatch.setBoxOrgCode(String.valueOf(sysDept.getDeptId()));
            amsBatch.setBoxOrgName(sysDept.getDeptName());
            amsBatch.setStatus(Constants.ALREADY_PUT_STORAGE); //ALREADY_PUT_STORAGE
            int up2 = amsBatchService.updateAmsBatch(amsBatch);

            //数据更新成功
            if (up1 > 0 && up2 > 0) {
                num = 1;
            } else {
                throw new Exception("批量接收实物档案错误");
            }
        }
        return num;
    }
}
