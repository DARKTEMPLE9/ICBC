package net.northking.iacmp.ams.web.controller.archmanage;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsArchives;
import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.common.bean.domain.ams.AmsBox;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBatchVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBoxVO;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.*;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysDeptService;
import net.northking.iacmp.utils.bean.BeanUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 整理组卷
 *
 * @author weizhe.fan
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/archManage/arrange")
public class ArrangeController extends BaseController {
    private String prefix = "archManage/arrange";

    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private IAmsBoxService amsBoxService;
    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private ISmUserService smUserService;
    @Autowired
    private ISmOrganService smOrganService;
    @Autowired
    private ISysDeptService sysDeptService;

    @RequiresPermissions("archManage:arrange:view")
    @GetMapping()
    public String amsBatch(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> list = new ArrayList();
        for (SysRole o : roleList) {
            list.add(o.getRoleId());
        }
        //获取用户最高权限角色
        String sysUserRole = Collections.max(list).toString();
        //当前用户权限
        mmap.put("role", sysUserRole);
        return prefix + "/arrange";
    }

    /**
     * 查询整理组卷列表
     */
    @RequiresPermissions("archManage:arrange:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBatchVO amsBatchVO) {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> roleIds = new ArrayList();
        for (SysRole o : roleList) {
            roleIds.add(o.getRoleId());
        }
        roleIds.remove(23L);
        //获取用户最高权限角色
        String roleId = Collections.max(roleIds).toString();
        //判断部门/行管理员
        if (roleId.equals("4")) {
            amsBatchVO.setArcHasMoveBank("1");
        } else if (roleId.equals("3")) {
            amsBatchVO.setArcHasMoveBank("0");
        }
        startPage();
        List<AmsBatch> list = amsBatchService.selectArrangeList(amsBatchVO, sysUser);
        return getDataTable(list);
    }

    /**
     * @param :parId:01,档案二级类目，保管期限，所属年份,
     * @Author: weizhe.fan
     * @Description:根据条件查询盒
     * @CreateDate: 16:34.2019/8/8
     */
    @PostMapping("/boxes")
    @ResponseBody
    public TableDataInfo boxes(String arcBillDept, String valPeriod, String crtTime) {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> list = new ArrayList();
        for (SysRole o : roleList) {
            list.add(o.getRoleId());
        }
        //获取用户最高权限角色
        String roleId = Collections.max(list).toString();

        // 根据条件查询盒子，反显
        AmsBoxVO amsBox = new AmsBoxVO();
        amsBox.setChildType(arcBillDept);
        amsBox.setValPeriod(valPeriod);
        amsBox.setBoxYear(crtTime.substring(0, 4));
        //库房类型判断 部门/总行
        if ("3".equals(roleId)) {
            amsBox.setDepType("10");
        } else if ("4".equals(roleId)) {
            amsBox.setDepType("20");
        }
        //查询当前用户管理的全部部门
        List<String> deptList = new ArrayList<>();
        deptList.add(sysUser.getDeptId().toString());
        String auxiliaryDept = sysUser.getAuxiliaryDept();
        if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//该用户有辅部门
            String[] AuxiliaryDeptArr = auxiliaryDept.split(",");
            for (int i = 0; i < AuxiliaryDeptArr.length; i++) {
                deptList.add(AuxiliaryDeptArr[i]);
            }
        }
        startPage();
        List<AmsBox> amsBoxes = amsBoxService.selectAmsBoxListByOpts(amsBox, deptList);
        return getDataTable(amsBoxes);
    }

    /**
     * @param :parId:01,档案二级类目，保管期限，所属年份,
     * @Author: weizhe.fan
     * @Description:跳转入盒界面
     * @CreateDate: 16:34.2019/8/8
     */
    @GetMapping("/jumpToIntoBox")
    public String jumpToIntoBox(String arcBillDept, String valPeriod, String crtTime, String ids, ModelMap modelMap) {
        modelMap.addAttribute("arcBillDept", arcBillDept);
        modelMap.addAttribute("valPeriod", valPeriod);
        modelMap.addAttribute("crtTime", crtTime);
        modelMap.addAttribute("ids", ids);
        String[] split = ids.split(",");
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(split[0]);
        String arcLevel = amsBatch.getArcLevel();
        modelMap.addAttribute("arcLevel", arcLevel);
        return prefix + "/boxes";
    }

    /**
     * @Author: weizhe.fan
     * @Description:确认页面
     * @CreateDate: 20:27.2019/8/9
     */
    @PostMapping("/affirmPage")
    @ResponseBody
    public TableDataInfo affirmPage(String ids) {
        startPage();
        List<AmsBatch> amsBatches = new ArrayList<>();
        String[] split = ids.split(",");
        for (String batchId : split) {
            AmsBatch amsBatch = amsBatchService.selectAmsBatchById(batchId);
//            //自动生成9位档案件号
//            int n = (int) ((Math.random() * 9 + 1) * 100000000);
//            String boxNum=String.valueOf(n);
//            amsBatch.setBoxNum(boxNum);
            amsBatches.add(amsBatch);
        }
        return getDataTable(amsBatches);
    }

    /**
     * @param :parId:01,档案二级类目，保管期限，所属年份,
     * @Author: weizhe.fan
     * @Description:跳转确认页面
     * @CreateDate: 16:34.2019/8/8
     */
    @GetMapping("/jumpToAffirm")
    public String affirm(String boxId, String ids, ModelMap modelMap) {
        modelMap.addAttribute("ids", ids);
        modelMap.addAttribute("boxId", boxId);
        //档案件号
        String boxNum = getArcSerialNum(boxId, ids);
        if (boxNum.equals("0")) {
            boxNum = "";
        }
        modelMap.addAttribute("boxNum", boxNum);
        return prefix + "/affirm";
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
     * @Author: weizhe.fan
     * @Description:入盒
     * @CreateDate: 16:28.2019/8/5
     */
    @RequiresPermissions("archManage:arrange:intoBox")
    @Log(title = "入盒", businessType = BusinessType.UPDATE)
    @PostMapping("/intoBox")
    @ResponseBody
    public AjaxResult intoBox(String batchIds, String boxId, String boxNum) throws Exception {

        int i = 0;
        String[] split = batchIds.split(",");
        AmsBox amsBox = amsBoxService.selectAmsBoxById(boxId);
        for (String batchId : split) {
            AmsBatch amsBatch = amsBatchService.selectAmsBatchById(batchId);
            // 点击入盒按钮,根据boxid查询盒子
            // 查询档案是否存在
            AmsArchivesVO amsArchives = new AmsArchivesVO();
            amsArchives.setBatchId(amsBatch.getId());
            List<AmsArchives> archivesList = amsArchivesService.selectAmsArchivesList(amsArchives);
            SysUser sysUser = ShiroUtils.getSysUser();
            SysDept sysDept = sysDeptService.selectDeptById(sysUser.getDeptId());
            //判断档案说档案盒是否属于同一部门
            if (!(amsBatch.getOrgCode()).equals(amsBox.getOrgCode())) {
                return new AjaxResult(AjaxResult.Type.ERROR, "档案与档案盒不属于同一部门，请重新选择！");
            }
            if (!archivesList.isEmpty()) {
                amsArchives.setId(archivesList.get(i).getId());
                amsArchives.setBoxCode(boxId);
                amsArchives.setBoxName(amsBox.getName());
                amsArchives.setBoxNum(Integer.valueOf(boxNum));
                AmsArchives amsArchives1 = new AmsArchives();
                BeanUtils.copyProperties(amsArchives, amsArchives1);
                amsArchivesService.updateAmsArchives(amsArchives1);
            } else {
                amsArchives.setId(UUID.randomUUID().toString().replace("-", ""));
                amsArchives.setManaDepNo(String.valueOf(sysDept.getDeptId()));
                amsArchives.setManaDepName(sysDept.getDeptName());
                amsArchives.setBatchNo(amsBatch.getBatchNo());
                amsArchives.setViewPath(amsBatch.getViewPath());
                String arcNo = getArcNo(amsBatch.getArcBillCode(), amsBatch.getValPeriod(), boxNum, amsBatch.getArcCreTime());
                amsArchives.setArcNo(arcNo);
                amsBatch.setArcNo(arcNo);

                //  档案盒号
                amsArchives.setBoxCode(boxId);
                amsArchives.setBoxName(amsBox.getName());
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
                amsArchives.setStatus(Constants.WAIT_PUT_STORAGE);
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
                AmsArchives amsArchives1 = new AmsArchives();
                BeanUtils.copyProperties(amsArchives, amsArchives1);
                amsArchivesService.insertAmsArchives(amsArchives1);
            }
            amsBox.setArcType(amsBatch.getArcBill());
            //  更新著录批次表
            amsBatch.setBoxId(boxId);
            amsBatch.setFilingDepa(sysDept.getDeptName());
            amsBatch.setFilingDepaCode(String.valueOf(sysDept.getDeptId()));
            amsBatch.setFilingTime(new Timestamp(System.currentTimeMillis()));
            amsBatch.setBoxOpCode(String.valueOf(sysUser.getUserId()));
            amsBatch.setBoxOpName(sysUser.getUserName());
            amsBatch.setBoxOrgCode(String.valueOf(sysDept.getDeptId()));
            amsBatch.setBoxOrgName(sysDept.getDeptName());
            amsBatch.setStatus(Constants.WAIT_PUT_STORAGE);
            amsBatchService.updateAmsBatch(amsBatch);
            amsBox.setStatus(Constants.NOTMAX_BOX);
            i = amsBoxService.updateAmsBox(amsBox);
        }
        return toAjax(i);
    }

    /**
     * @Author: weizhe.fan
     * @Description:查询详情
     * @CreateDate: 15:30.2019/8/5
     */
    @RequiresPermissions("archManage:arrange:detail")
    @GetMapping("/{id}")
    public String queryOne(@PathVariable String id, ModelMap modelMap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        modelMap.addAttribute("amsBatch", amsBatch);
        return prefix + "/detail";
    }

    /**
     * @Author: weizhe.fan
     * @Description:退回
     * @CreateDate: 16:28.2019/8/5
     */
    @RequiresPermissions("archManage:arrange:backup")
    @Log(title = "退回", businessType = BusinessType.UPDATE)
    @PostMapping("/backup")
    @ResponseBody
    public AjaxResult backUp(AmsBatch amsBatch, String ids) {
        // 根据batchid查询batch，update。
        int count = 0;
        String[] split = ids.split(",");
        for (String batchId : split) {
            AmsBatch newBatch = amsBatchService.selectAmsBatchById(batchId);
            newBatch.setReceivingOpinion(amsBatch.getReceivingOpinion());// 接收意见,未做，目前为批量退回
            newBatch.setStatus(Constants.SEND_BACK);
            int i = amsBatchService.updateAmsBatch(newBatch);
            count += i;
        }
        return toAjax(count);
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
    public String getArcNo(String arcBillCode, String valPeriod, String boxNum, Date arcCreTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(arcCreTime);
        String[] a = date.split("-");
        String no = "000" + boxNum;
        String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        return "0000" + "-" + substring + a[0] + "-" + valPeriod + "-" + no;
    }

    /**
     * 导出档案著录列表
     */
    @RequiresPermissions("archManage:arrange:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBatch amsBatch) {
        List<AmsBatch> list = amsBatchService.selectAmsBatchList(amsBatch);
        ExcelUtil<AmsBatch> util = new ExcelUtil<>(AmsBatch.class);
        return util.exportExcel(list, "arrange");
    }

    /**
     * 跳转到档案盒页面
     */
    @GetMapping("/add")
    public String add(@RequestParam(value = "ids") String ids, ModelMap modelMap) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(Convert.toStrArray(ids)[0]);
        String format = sf.format(amsBatch.getCrtTime()).substring(0, 4);
        AmsBox amsBox = new AmsBox();
        amsBox.setBoxYear(format);
        modelMap.addAttribute("amsBatch", amsBatch);
        modelMap.addAttribute("amsBox", amsBox);
        return prefix + "/add";
    }

    /**
     * 调转到显示树页面
     *
     * @return
     */
    @GetMapping("/deptTree")
    public String deptTree() {
        return prefix + "/tree";
    }


    /**
     * 创建档案盒
     */
    @RequiresPermissions("archManage:arrange:add")
    @Log(title = "创建档案盒", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBox amsbox) {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> list = new ArrayList();
        for (SysRole o : roleList) {
            list.add(o.getRoleId());
        }
        list.remove(23L);
        //获取用户最高权限角色
        String roleId = Collections.max(list).toString();
        //判断部门/行管理员
        if (roleId.equals("3")) {
            amsbox.setDepType("10");
        } else if (roleId.equals("4")) {
            amsbox.setDepType("20");
        }
        if (amsbox.getOrgCode() == null || "".equals(amsbox.getOrgCode())) {
            amsbox.setOrgName(sysUser.getDept().getDeptName());
            amsbox.setOrgCode(sysUser.getDeptId().toString());
        }
        amsbox.setId(UUID.randomUUID().toString().replace("-", ""));
        amsbox.setStatus(Constants.AVAILABLE_BOX);
        amsbox.setCrtTime(new Date());
        return toAjax(amsBoxService.insertAmsBox(amsbox));
    }


    /**
     * 修改档案著录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案著录
     */
    @RequiresPermissions("archManage:arrange:edit")
    @Log(title = "档案著录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBatch amsBatch) {
        return toAjax(amsBatchService.updateAmsBatch(amsBatch));
    }

    /**
     * 删除档案著录
     */
    @RequiresPermissions("archManage:arrange:remove")
    @Log(title = "档案著录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBatchService.deleteAmsBatchByIds(ids));
    }

    @PostMapping("/getArchivesByBoxId")
    @ResponseBody
    public AjaxResult getArchivesByBoxId(String boxId) {
        AmsArchivesVO amsArchives = new AmsArchivesVO();
        amsArchives.setBoxCode(boxId);
        List<AmsArchives> list = amsArchivesService.selectAmsArchivesList(amsArchives);
        String arcLevel = null;
        if (list.isEmpty()) {
            arcLevel = "0";
        } else {
            arcLevel = list.get(0).getArcLevel();
        }
        return AjaxResult.success(arcLevel);
    }

    /**
     * 获取当前档案数量
     *
     * @return
     */
    private String getArcSerialNum(String boxId, String ids) {
        AmsArchivesVO amsArchives = new AmsArchivesVO();
        amsArchives.setBoxCode(boxId);
        List<AmsArchives> list = amsArchivesService.selectAmsArchivesList(amsArchives);
        int n = 0;
        if (!list.isEmpty()) {
            Map<String, Integer> arcIds = new HashMap<>();
            for (AmsArchives archives : list) {
                arcIds.put(archives.getId(), archives.getBoxNum());
            }
            if (!arcIds.containsKey(ids)) {
                //档案盒内档案数量
                n = list.size() + 1;
            } else {
                n = arcIds.get(ids);
            }
        } else {
            AmsBox box = amsBoxService.selectAmsBoxById(boxId);
            if (box != null) {
                n = 1;
            }
        }
        return String.valueOf(n);
    }


}
