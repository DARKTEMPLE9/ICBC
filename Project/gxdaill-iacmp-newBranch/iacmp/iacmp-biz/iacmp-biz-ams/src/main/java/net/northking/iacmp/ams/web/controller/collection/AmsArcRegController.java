package net.northking.iacmp.ams.web.controller.collection;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import net.northking.iacmp.ams.web.enums.AmsArcRegStatus;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsArcReg;
import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.common.bean.domain.ams.ImFile;
import net.northking.iacmp.common.bean.domain.ams.ImImage;
import net.northking.iacmp.common.bean.vo.ams.AmsArcRegVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBatchVO;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.PageDomain;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.core.page.TableSupport;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.server.service.IAmsArcRegService;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.server.service.IImFileService;
import net.northking.iacmp.server.service.IImImageService;
import net.northking.iacmp.server.service.impl.ImImageServiceImpl;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysDeptService;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import net.northking.iacmp.utils.sql.SqlUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 档案收集----->档案登记及档案接收
 *
 * @author yanqingyu
 * @date 2019-08-05
 */
@Controller
@RequestMapping("/archCollection/amsArcReg")
public class AmsArcRegController extends BaseController {
    private static final String AMS_ARC_REG = "amsArcReg";
    private String prefix = "archCollection/amsArcReg";
    @Autowired
    private IAmsArcRegService amsArcRegService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private IImFileService iImFileService;
    @Autowired
    private IImImageService iImImageService;
    @Autowired
    private ISysDeptService sysDeptService;


    /**
     * 档案登记页面
     *
     * @return
     */
    @GetMapping("/reg")
    @RequiresPermissions("archCollection:archReg:view")
    public String archReg(ModelMap mmap, SysUser user) {
        List<SysUser> userList = userService.selectUsersBySysUser(user);
        mmap.put("UserName", ShiroUtils.getSysUser().getUserName());
        mmap.put("userList", userList);
        return prefix + "/reg";
    }

    /**
     * 菜单栏--->档案接收页面
     *
     * @return
     */
    @RequiresPermissions("archCollection:amsArcReg:view")
    @GetMapping()
    public String amsArcReg() {
        return prefix + "/amsArcReg";
    }

    /**
     * 我的档案登记列表页面
     *
     * @return
     */
    @RequiresPermissions("archCollection:myArchReg:view")
    @GetMapping("/myArchList")
    public String myArchList() {
        return prefix + "/myArchReg";
    }

    /**
     * 我的档案接收列表页面
     *
     * @return
     */
    @RequiresPermissions("archCollection:myArchReceive:view")
    @GetMapping("/myReceiveList")
    public String myReceiveList() {
        return prefix + "/myAmsArcReg";
    }

    /**
     * 档案接收模块----->档案接收按钮点击页面
     */
    @RequiresPermissions("archCollection:archReceive:view")
    @Log(title = "档案接收", businessType = BusinessType.INSERT)
    @GetMapping("/receive/{id}")
    public String receive(@PathVariable("id") String id, ModelMap mmap) {
        AmsArcReg amsArcReg = amsArcRegService.selectAmsArcRegById(id);
        mmap.put(AMS_ARC_REG, amsArcReg);
        return prefix + "/receive";
    }

    /**
     * 我的档案接收模块--->编辑档案页面
     *
     * @return
     */
    @RequiresPermissions("archCollection:myArchReceiveEdit:view")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsArcReg amsArcReg = amsArcRegService.selectAmsArcRegById(id);
        mmap.put(AMS_ARC_REG, amsArcReg);
        return prefix + "/edit";
    }

    /**
     * 我的档案移交登记模块--->编辑档案页面
     *
     * @return
     */
    @RequiresPermissions("archCollection:myArchReceiveEdit:view")
    @Log(title = "编辑已保存档案", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editArch(AmsArcReg amsArcReg) {
        amsArcReg.setModTime(new Date());
        return toAjax(amsArcRegService.updateAmsArcReg(amsArcReg));
    }

    /**
     * 档案预览
     *
     * @return"
     */
    @RequiresPermissions("archCollection:amsArcRegPrintLook:view")
    @Log(title = "打印预览", businessType = BusinessType.OTHER)
    @GetMapping("/printLook")
    public String printLook(String ids, ModelMap mmap) {
        SysUser loginUser = ShiroUtils.getSysUser();
        List<AmsArcReg> list = amsArcRegService.selectAmsArcRegByIds(ids);
        mmap.put("printList", list);
        mmap.put("loginUser", loginUser);
        return prefix + "/printLook";
    }

    /**
     * 查看我的档案详细页面
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("archCollection:amsArcRegDetail:view")
    public String archDetail(@PathVariable("id") String id, ModelMap mmap) {
        AmsArcReg amsArcReg = amsArcRegService.selectAmsArcRegById(id);
        if (amsArcReg != null) {
            mmap.put(AMS_ARC_REG, amsArcReg);
        } else {
            AmsBatch amsBatch = new AmsBatch();
            amsBatch.setId(id);
            List<AmsBatch> amsBatches = amsBatchService.selectAmsBatchList(amsBatch);

            //组装返回档案接收信息对象
            if (!amsBatches.isEmpty()) {
                AmsArcReg arcReg = new AmsArcReg();
                for (AmsBatch batch : amsBatches) {
                    //主键
                    arcReg.setId(batch.getId());
                    //档案名称
                    arcReg.setName(batch.getArcName());
                    //登记份数
                    arcReg.setRegArcNum(batch.getArcNum());
                    //登记页数
                    arcReg.setRecPageNum(batch.getArcPageNum());
                    //登记人名称
                    arcReg.setRegOpName(batch.getRespOpName());
                    //是否移交行档室
                    arcReg.setHasMoveBank(batch.getArcHasMoveBank());
                    //登记时间
                    arcReg.setRegTime(batch.getReceiveTime());
                    //接收时间
                    arcReg.setRecTime(batch.getTurnInTime());
                    //状态
                    arcReg.setStatus(new BigDecimal(batch.getStatus()));
                    //文件编号
                    arcReg.setArcCode(batch.getArcCode());
                    //接收人名称
                    arcReg.setRecOpName(batch.getReceiveName());
                    //接收人机构名称
                    arcReg.setRecOrgName(batch.getOrgName());
                    //修改时间
                    arcReg.setModTime(batch.getModTime());
                    //接收备注
                    arcReg.setRecRemark(batch.getRemark());
                    //登记备注
                    arcReg.setRegRemark("");
                }

                mmap.put(AMS_ARC_REG, arcReg);
            }
        }

        return prefix + "/detail";
    }

    /**
     * 档案接收列表
     */
    @RequiresPermissions("archCollection:amsArcReg:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsArcRegVO amsArcReg) {
        List<AmsArcReg> list = new ArrayList<>();
        amsArcReg.setDeptId(ShiroUtils.getSysUser().getDeptId());
        //当前用户管理的全部部门
        List<String> deptList = new ArrayList<>();
        deptList.add(ShiroUtils.getSysUser().getDeptId().toString());
        amsArcReg.setStatus(new BigDecimal(AmsArcRegStatus.TIJIAO.getValue()));
        //查询当前用户辅部门
        if (ShiroUtils.getSysUser().getAuxiliaryDept() != null && !"".equals(ShiroUtils.getSysUser().getAuxiliaryDept())) {
            String[] auxiliaryDeptArr = ShiroUtils.getSysUser().getAuxiliaryDept().split(",");
            for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                deptList.add(auxiliaryDeptArr[i]);
            }
            //查询全部子部门
            if (deptList != null && deptList.size() > 0) {
                for (String deptId : deptList) {
                    List<String> deptIds = sysDeptService.selectChildNodes(deptId);
                    if (deptIds != null && deptIds.size() > 0) {
                        for (String id : deptIds) {
                            deptList.add(id);
                        }
                    }
                }
            }
            startPage();
            list = amsArcRegService.selectAmsArcList(amsArcReg, ShiroUtils.getUserId(), deptList);
        } else {
            startPage();
            list = amsArcRegService.selectAmsArcRegList(amsArcReg, ShiroUtils.getUserId());
        }
        return getDataTable(list);
    }

    /**
     * 我的档案接收列表
     */
    @RequiresPermissions("archCollection:myRrcReceive:list")
    @PostMapping("/myArchReceiveList")
    @ResponseBody
    public TableDataInfo myArchReceiveList(AmsArcRegVO amsArcReg) {

        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        int formIndex = (pageSize * (pageNum - 1));
        int toIndex = formIndex + pageSize;
        //部门Id
        Long deptId = ShiroUtils.getSysUser().getDeptId();
        //返回结果集
        List<AmsArcReg> list;
        //著录信息列表
        List<AmsBatch> amsBatches = new ArrayList<>();
        //接收信息列表
        List<AmsArcReg> amsArcRegs = new ArrayList<>();
        //合并列表
        List<AmsArcReg> unionList = new ArrayList<>();
        amsArcReg.setRecOpId(ShiroUtils.getSysUser().getUserId().toString());
        amsArcReg.setDeptId(deptId);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("condition", "myArcReceive");
        amsArcReg.setParams(paramMap);
        //当前用户管理的全部部门
        String auxiliaryDept = ShiroUtils.getSysUser().getAuxiliaryDept();//辅部门
        List<String> deptList = new ArrayList<>();
        if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//当前用户有辅部门
            deptList.add(ShiroUtils.getSysUser().getDeptId().toString());
            //查询当前用户辅部门
            String[] auxiliaryDeptArr = auxiliaryDept.split(",");
            for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                deptList.add(auxiliaryDeptArr[i]);
            }
            //查询全部子部门
            if (deptList != null && deptList.size() > 0) {
                for (String deptid : deptList) {
                    List<String> deptIds = sysDeptService.selectChildNodes(deptid);
                    if (deptIds != null && deptIds.size() > 0) {
                        for (String id : deptIds) {
                            deptList.add(id);
                        }
                    }
                }
            }
            amsArcRegs = amsArcRegService.selectAmsArcList(amsArcReg, ShiroUtils.getUserId(), deptList);
        } else {
            amsArcRegs = amsArcRegService.selectAmsArcRegList(amsArcReg, ShiroUtils.getUserId());
        }
        for (AmsArcReg obj : amsArcRegs) {
            unionList.add(obj);
        }

        AmsBatchVO amsBatchVO = new AmsBatchVO();
        amsBatchVO.setOrgCode(String.valueOf(deptId));
        //设置查询参数
        //档案名称
        if (!"".equals(amsArcReg.getName())) {
            amsBatchVO.setArcName(amsArcReg.getName());
        }
        //登记人名称
        if (!"".equals(amsArcReg.getRegOpName())) {
            amsBatchVO.setRespOpName(amsArcReg.getRegOpName());
        }
        //登记时间
        if (null != amsArcReg.getRegTimeGte()) {
            amsBatchVO.setRegTimeGte(amsArcReg.getRegTimeGte());
        }
        if (null != amsArcReg.getRegTimeLte()) {
            amsBatchVO.setRegTimeLte(amsArcReg.getRegTimeLte());
        }
        //接收时间
        if (null != amsArcReg.getRecTimeGte()) {
            amsBatchVO.setRecTimeGte(amsArcReg.getRecTimeGte());
        }
        if (null != amsArcReg.getRecTimeLte()) {
            amsBatchVO.setRecTimeLte(amsArcReg.getRecTimeLte());
        }
        //接收人名称
        amsBatchVO.setReceiveNo(ShiroUtils.getSysUser().getUserId().toString());
        //状态判断 已入盒/已入库
        if (amsArcReg.getStatus() != null) {
            if (amsArcReg.getStatus().compareTo(new BigDecimal("4")) == 0) {
                amsBatchVO.setOrgCode(String.valueOf(deptId));
                //已入盒
                amsBatchVO.setStatus("4");
            } else if (amsArcReg.getStatus().compareTo(new BigDecimal("5")) == 0) {
                amsBatchVO.setOrgCode(String.valueOf(deptId));
                //已入库
                amsBatchVO.setStatus("5");
            }
            if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//当前用户有辅部门
                amsBatches = amsBatchService.selectAmsBatchVOList(amsBatchVO, deptList);
            } else {
                amsBatches = amsBatchService.selectAmsBatchVOList(amsBatchVO);
            }
        } else {
            //已入盒
            amsBatchVO.setStatus("4");
            List<AmsBatch> inToBox = new ArrayList<>();
            if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//当前用户有辅部门
                inToBox = amsBatchService.selectAmsBatchVOList(amsBatchVO, deptList);
            } else {
                inToBox = amsBatchService.selectAmsBatchVOList(amsBatchVO);
            }
            for (AmsBatch obj : inToBox) {
                amsBatches.add(obj);
            }
            //已入库
            amsBatchVO.setStatus("5");
            List<AmsBatch> inToDepot = new ArrayList<>();
            if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//当前用户有辅部门
                inToDepot = amsBatchService.selectAmsBatchVOList(amsBatchVO, deptList);
            } else {
                inToDepot = amsBatchService.selectAmsBatchVOList(amsBatchVO);
            }
            for (AmsBatch obj : inToDepot) {
                amsBatches.add(obj);
            }
        }

        //组装返回档案接收信息对象
        if (!amsBatches.isEmpty()) {
            for (AmsBatch batch : amsBatches) {
                AmsArcReg arcReg = new AmsArcReg();
                //主键
                arcReg.setId(batch.getId());
                //档案名称
                arcReg.setName(batch.getArcName());
                //登记份数
                arcReg.setRegArcNum(batch.getArcNum());
                //登记页数
                arcReg.setRecPageNum(batch.getArcPageNum());
                //登记人名称
                arcReg.setRegOpName(batch.getRespOpName());
                //是否移交行档室
                arcReg.setHasMoveBank(batch.getArcHasMoveBank());
                //登记时间
                arcReg.setRegTime(batch.getReceiveTime());
                //接收时间
                arcReg.setRecTime(batch.getTurnInTime());
                //状态
                arcReg.setStatus(new BigDecimal(batch.getStatus()));

                unionList.add(arcReg);
            }
        }
        int size = unionList.size();
        if (toIndex >= size) {
            toIndex = size;
        }
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
        }
        list = unionList.subList(formIndex, toIndex);

        if (pageDomain.getOrderByColumn() != null) {
            Collections.sort(list, new Comparator<AmsArcReg>() {
                @Override
                public int compare(AmsArcReg o1, AmsArcReg o2) {
                    if (pageDomain.getIsAsc().equals("asc")) {
                        //档案名称
                        if (pageDomain.getOrderByColumn().equals("name")) {
                            return o1.getName().compareTo(o2.getName());
                        }
                        //登记份数
                        if (pageDomain.getOrderByColumn().equals("regArcNum")) {
                            return o1.getRegArcNum().compareTo(o2.getRegArcNum());
                        }
                        //登记页数
                        if (pageDomain.getOrderByColumn().equals("recPageNum")) {
                            return o1.getRecPageNum().compareTo(o2.getRecPageNum());
                        }
                        //登记人名称
                        if (pageDomain.getOrderByColumn().equals("regOpName")) {
                            return o1.getRegOpName().compareTo(o2.getRegOpName());
                        }
                        //是否移交行档室
                        if (pageDomain.getOrderByColumn().equals("hasMoveBank")) {
                            return o1.getHasMoveBank().compareTo(o2.getHasMoveBank());
                        }
                        //登记时间
                        if (pageDomain.getOrderByColumn().equals("regTime")) {
                            return o1.getRegTime().compareTo(o2.getRegTime());
                        }
                        //接收时间
                        if (pageDomain.getOrderByColumn().equals("recTime")) {
                            return o1.getRecTime().compareTo(o2.getRecTime());
                        }
                        //状态
                        if (pageDomain.getOrderByColumn().equals("status")) {
                            return o1.getStatus().toString().compareTo(o2.getStatus().toString());
                        }
                    }

                    if (pageDomain.getIsAsc().equals("desc")) {
                        //档案名称
                        if (pageDomain.getOrderByColumn().equals("name")) {
                            return o2.getName().compareTo(o1.getName());
                        }
                        //登记份数
                        if (pageDomain.getOrderByColumn().equals("regArcNum")) {
                            return o2.getRegArcNum().compareTo(o1.getRegArcNum());
                        }
                        //登记页数
                        if (pageDomain.getOrderByColumn().equals("recPageNum")) {
                            return o2.getRecPageNum().compareTo(o1.getRecPageNum());
                        }
                        //登记人名称
                        if (pageDomain.getOrderByColumn().equals("regOpName")) {
                            return o2.getRegOpName().compareTo(o1.getRegOpName());
                        }
                        //是否移交行档室
                        if (pageDomain.getOrderByColumn().equals("hasMoveBank")) {
                            return o2.getHasMoveBank().compareTo(o1.getHasMoveBank());
                        }
                        //登记时间
                        if (pageDomain.getOrderByColumn().equals("regTime")) {
                            return o2.getRegTime().compareTo(o1.getRegTime());
                        }
                        //接收时间
                        if (pageDomain.getOrderByColumn().equals("recTime")) {
                            return o2.getRecTime().compareTo(o1.getRecTime());
                        }
                        //状态
                        if (pageDomain.getOrderByColumn().equals("status")) {
                            return o2.getStatus().toString().compareTo(o1.getStatus().toString());
                        }
                    }
                    return 0;
                }
            });
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(unionList).getTotal());
        return rspData;
    }

    /**
     * 查询我的档案登记列表
     */
    @RequiresPermissions("archCollection:myArcReg:list")
    @PostMapping("/myArchList")
    @ResponseBody
    public TableDataInfo myArchList(AmsArcRegVO amsArcReg) {
        SysUser loginUser = ShiroUtils.getSysUser();
        amsArcReg.setRegOpId(loginUser.getUserId().toString());
        startPage();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("condition", "myArcReceive");
        amsArcReg.setParams(paramMap);
        List<AmsArcReg> list = amsArcRegService.selectAmsArcRegList(amsArcReg, ShiroUtils.getUserId());
        return getDataTable(list);
    }


    /**
     * 查询档案移交登记历史列表
     */
    @RequiresPermissions("archCollection:myArcReg:list")
    @PostMapping("/arcTransferHistory")
    @ResponseBody
    public TableDataInfo arcTransferHistory(AmsArcRegVO amsArcReg) {
        SysUser loginUser = ShiroUtils.getSysUser();
        amsArcReg.setRegOpId(loginUser.getUserId().toString());
        startPage();
        List<AmsArcReg> list = amsArcRegService.selectAmsArcRegList(amsArcReg, ShiroUtils.getUserId());
        return getDataTable(list);
    }


    /**
     * 导出档案列表
     */
    @RequiresPermissions("archCollection:amsArcReg:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsArcRegVO amsArcReg) {
        List<AmsArcReg> list = amsArcRegService.selectAmsArcRegList(amsArcReg, ShiroUtils.getUserId());
        ExcelUtil<AmsArcReg> util = new ExcelUtil<>(AmsArcReg.class);
        return util.exportExcel(list, AMS_ARC_REG);
    }

    /**
     * 新增保存档案
     */
    @Log(title = "档案提交", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ResponseBody
    public AjaxResult addSave(@RequestParam(value = "amsArcRegList") String regList, @RequestParam(defaultValue = "0") String isSave) {
        int i = 0;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, AmsArcReg.class);

            List<AmsArcReg> amsArcRegList = mapper.readValue(regList, javaType);
            SysUser loginUser = ShiroUtils.getSysUser();
            List<AmsArcReg> list = new ArrayList<>();
            for (AmsArcReg amsArcReg : amsArcRegList) {
                //根据regId查询电子化档案是否上传文件
                String arcFormat = amsArcReg.getArcFormat();
                String regId = amsArcReg.getId();
                if (arcFormat != null && "10".equals(arcFormat)) {//电子化档案
                    //是否上传文件
                    List<ImFile> fileList = iImFileService.selectImFilesByBatchId(regId);
                    List<ImImage> imagelist = iImImageService.selectImImagesByBatchId(regId);
                    if (fileList.size() <= 0 && imagelist.size() <= 0) {
                        return AjaxResult.error("电子化档案请先上传文件！");
                    }
                }
                if (amsArcReg.getArcCreTime() == null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = "1970-01-01";//默认时间
                    amsArcReg.setArcCreTime(sdf.parse(date));
                }
                amsArcReg.setRegOpName(loginUser.getUserName() != null ? loginUser.getUserName() : null);
                amsArcReg.setRegOpId(loginUser.getUserId() != null ? loginUser.getUserId().toString() : null);
                amsArcReg.setRegOrgCode(loginUser.getDeptId() != null ? loginUser.getDeptId().toString() : null);
                amsArcReg.setRegOrgName(loginUser.getDept() != null ? loginUser.getDept().getDeptName() : null);
                amsArcReg.setRegTime(new Timestamp(System.currentTimeMillis()));
                amsArcReg.setStatus("0".equals(isSave) ? new BigDecimal(AmsArcRegStatus.TIJIAO.getValue()) : new BigDecimal(AmsArcRegStatus.BAOCUN.getValue()));
                amsArcReg.setRepeatMark("0");
                list.add(amsArcReg);
//                i += amsArcRegService.insertAmsArcReg(amsArcReg);
            }

            for (AmsArcReg amsArcReg : list) {
                i += amsArcRegService.insertAmsArcReg(amsArcReg);
            }
        } catch (Exception e) {
            throw new RuntimeException("档案提交失败，请求参数：" + regList, e.fillInStackTrace());
        }
        return AjaxResult.success("操作成功", i);
    }

    @Log(title = "档案提交", businessType = BusinessType.INSERT)
    @PostMapping("/addTest")
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ResponseBody
    public AjaxResult addTest(@RequestParam(value = "testa") String testa) {
        System.out.println("test");
        return AjaxResult.success("操作成功");
    }

    /**
     * 档案接收
     */
    @Log(title = "档案接收", businessType = BusinessType.UPDATE)
    @PostMapping("/receive")
    @ResponseBody
    public AjaxResult receiveSave(AmsArcReg amsArcReg) {
        SysUser loginUser = ShiroUtils.getSysUser();
        if ("1".equals(amsArcReg.getRepeatMark())) {
            amsArcReg.setRepeatMark("0");
        }
        amsArcReg.setRecOpName(loginUser.getUserName());
        amsArcReg.setRecOpId(loginUser.getUserId().toString());
        amsArcReg.setRecOrgCode(loginUser.getDeptId().toString());
        amsArcReg.setRecOrgName(loginUser.getDept().getDeptName());
        amsArcReg.setRecTime(new Date());
        amsArcReg.setStatus(new BigDecimal(AmsArcRegStatus.JIESHOU.getValue()));

        return toAjax(amsArcRegService.updateAmsArcReg(amsArcReg));
    }

    /**
     * 档案退回，修改该档案状态为退回
     */
    @Log(title = "档案", businessType = BusinessType.DELETE)
    @GetMapping("/returnArch/{id}")
    @ResponseBody
    public AjaxResult returnArch(@PathVariable("id") String id) {

        AmsArcReg amsArcReg = amsArcRegService.selectAmsArcRegById(id);
        if (AmsArcRegStatus.TIJIAO.equalss(amsArcReg.getStatus().toString())) {
            amsArcReg.setStatus(new BigDecimal(AmsArcRegStatus.TUIHUI.getValue()));
            return toAjax(amsArcRegService.updateAmsArcReg(amsArcReg));
        }
        return toAjax(-1);
    }

    /**
     * 删除档案，修改档案状态为已删除
     */
    @RequiresPermissions("archCollection:amsArcReg:remove")
    @Log(title = "档案", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        List<AmsArcReg> amsArcRegList = amsArcRegService.selectAmsArcRegByIds(ids);
        amsArcRegList.stream().forEach(amsArcReg -> {
            amsArcReg.setStatus(new BigDecimal(AmsArcRegStatus.DELETE.getValue()));
            amsArcRegService.updateAmsArcReg(amsArcReg);
        });
        return toAjax(1);
    }

    /**
     * 档案登记
     *
     * @return
     */
    @RequiresPermissions("archCollection:amsArcReg:reg")
    @Log(title = "档案登记", businessType = BusinessType.INSERT)
    @PostMapping("/reg")
    @ResponseBody
    public AjaxResult archRegSave(AmsArcReg amsArcReg) {

        return toAjax(amsArcRegService.insertAmsArcReg(amsArcReg));
    }

    /**
     * 将保存档案信息，提交
     */
    @Log(title = "档案提交", businessType = BusinessType.UPDATE)
    @GetMapping("/editCommit/{id}")
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ResponseBody
    public AjaxResult editCommit(@PathVariable("id") String id) {
        AmsArcReg amsArcReg = new AmsArcReg();
        amsArcReg.setId(id);
        amsArcReg.setStatus(new BigDecimal(AmsArcRegStatus.TIJIAO.getValue()));
        return toAjax(amsArcRegService.updateAmsArcReg(amsArcReg));
    }

    /**
     * 档案登记，批量导入
     *
     * @param file
     * @return
     */
    @RequiresPermissions("archCollection:amsArcReg:batchUpload")
    @PostMapping("/batchUpload")
    @ResponseBody
    public AjaxResult batchArcRegUpload(MultipartFile file, boolean updateSupport, ModelMap mmap) throws Exception {

        /* 批量注入解决同名用户问题 注：重名人多时性能较差*/
        //获取系统注册所有用户信息
        List<SysUser> allUsers = userService.selectAllUserList();
        //存储所有存在重名用户信息
        List<String> doubleUserName = new ArrayList<>();
        if (!allUsers.isEmpty()) {
            for (int i = 0; i < allUsers.size(); i++) {
                for (int j = i + 1; j < allUsers.size(); j++) {
                    if (allUsers.get(i).getUserName().equals(allUsers.get(j).getUserName())) {
                        doubleUserName.add(allUsers.get(i).getUserName());
                        break;
                    }
                }
            }
        }

        if (null == file) {
            return null;
        }
        try {
            ExcelUtil<AmsArcReg> util = new ExcelUtil<>(AmsArcReg.class);
            List<AmsArcReg> amsArcRegList = util.importExcel(file.getInputStream());
            for (AmsArcReg amsArcReg : amsArcRegList) {
                String regId = UUID.randomUUID().toString().replaceAll("-", "");
                amsArcReg.setId(regId);
                Date creTime = amsArcReg.getArcCreTime();
                if (creTime == null) {//没有导入形成时间
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = "1970-01-01";//默认时间
                    amsArcReg.setArcCreTime(sdf.parse(date));
                }
                for (int i = 0; i < doubleUserName.size(); i++) {
                    if (amsArcReg.getRespOpName().equals(doubleUserName.get(i))) {
                        amsArcReg.setRespOpName("");
                        break;
                    }
                }
            }
            mmap.put("regList", amsArcRegList);
            return AjaxResult.success("导入成功", amsArcRegList);

        } catch (Exception e) {
            return AjaxResult.error("请下载使用模板");
        }

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
     * 调转到显示部门树页面
     *
     * @return
     */
    @GetMapping("/deptTree")
    public String deptTree() {
        return prefix + "/tree";
    }

    /**
     * 跳转选择用户页面
     *
     * @return
     */
    @GetMapping("/selectUser")
    public String selectUser(ModelMap mmap) {
        mmap.put("userId", ShiroUtils.getUserId());
        mmap.put("deptId", ShiroUtils.getSysUser().getDeptId());
        return prefix + "/selectUser";
    }

    /**
     * 导出档案登记模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<AmsArcReg> util = new ExcelUtil<AmsArcReg>(AmsArcReg.class);
        return util.importTemplateExcel("移交登记");
    }

    /**
     * 获得regId
     */
    @GetMapping("/getRegId")
    @ResponseBody
    public String getRegId() {
        String regId = UUID.randomUUID().toString().replaceAll("-", "");
        return regId;
    }

}
