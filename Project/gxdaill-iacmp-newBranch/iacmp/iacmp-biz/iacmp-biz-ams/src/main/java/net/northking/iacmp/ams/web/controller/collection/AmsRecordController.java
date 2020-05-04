package net.northking.iacmp.ams.web.controller.collection;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.northking.iacmp.ams.web.enums.AmsArcRegStatus;
import net.northking.iacmp.ams.web.enums.AmsBatchStatus;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.common.bean.dto.ams.AmsBatchDTO4Impt;
import net.northking.iacmp.common.bean.feign.ams.FileUploadFeignClient;
import net.northking.iacmp.common.bean.vo.ams.AmsArcRegVO;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBatchVO;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.constant.OuterInterfaceConstants;
import net.northking.iacmp.constant.PmsConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.framework.util.UploadUtil;
import net.northking.iacmp.http.chunk.client.UploadClient;
import net.northking.iacmp.http.chunk.domain.HttpResult;
import net.northking.iacmp.result.FileAndImageRetMsg;
import net.northking.iacmp.server.service.*;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysDeptService;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.SnowFlakeUtils;
import net.northking.iacmp.utils.bean.BeanUtils;
import net.northking.iacmp.utils.file.FileUploadUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 档案收集------>档案著录
 * 操作表-------->ams_batch
 *
 * @author wxy
 * @date 2019-08-05
 */
@Controller
@RequestMapping("/archCollection/amsRecord")
public class AmsRecordController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(AmsRecordController.class);
    private static final String AMS_BATCH = "amsBatch";
    private String prefix = "archCollection/amsRecord";

    /**
     * 允许上传文件类型
     */
    private static final String[] allowedFileExtension = {"doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "pdf", "rar", "zip", "mp3", "mp4", "avi", "mpeg", "rmvb"};
    /**
     * 允许上传影像类型
     */
    private static final String[] allowedImageExtension = {"jpg", "png", "bmp", "tif", "gif"};

    /**
     * 最大上传大小
     */
    @Value("${maxUploadSize}")
    private String maxUploadSize;
    /**
     * 机器标识0~31
     */
    private static Long workerId = 5L;
    /**
     * 数据中心0~31
     */
    private static Long datacenterId = 5L;
    private static SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils(workerId, datacenterId);

    // 时间格式化 yyyyMMddHHmmss
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    // 时间格式化 yyyy/MMdd/
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MMdd/");

    // 档案类型
    private static final String AMSBILLNAME = "会计档案";

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private IAmsBillService amsBillService;
    @Autowired
    private IAmsArcRegService amsArcRegService;
    @Autowired
    private IImFileService fileService;
    @Autowired
    private IImImageService imageService;
    @Autowired
    private IImFileService imFileService;
    @Resource
    private FileUploadFeignClient fileUploadFeignClient;
    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private IAmsParamService amsParamService;
    @Autowired
    private IAmsCabinetService amsCabinetService;
    @Autowired
    private IAmsDepotService amsDepotService;

    //解压zip文件中的文件列表
    List<File> fileList = new ArrayList<>();

    @RequiresPermissions("archCollection:amsRecord:view")
    @GetMapping()
    public String amsBatch() {
        return prefix + "/amsBatch";
    }

    /**
     * 我的档案著录列表
     *
     * @return
     */
    @RequiresPermissions("archCollection:amsRecord:view")
    @GetMapping("/myAmsBatch")
    public String myArchRecord() {
        return prefix + "/myAmsBatch";
    }

    /**
     * 查询档案著录列表
     */
    @RequiresPermissions("archCollection:amsRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsArcRegVO amsArcReg) {
        amsArcReg.setDeptId(ShiroUtils.getSysUser().getDeptId());
        //当前用户管理的全部部门
        List<String> deptList = new ArrayList<>();
        deptList.add(ShiroUtils.getSysUser().getDeptId().toString());
        amsArcReg.setStatus(new BigDecimal(AmsArcRegStatus.JIESHOU.getValue()));
        List<AmsArcReg> list = new ArrayList<>();
        //查询当前用户辅部门
        String auxiliaryDept = ShiroUtils.getSysUser().getAuxiliaryDept();
        if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//当前用户有辅部门
            String[] auxiliaryDeptArr = auxiliaryDept.split(",");
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
     * 查询我的档案著录档案列表
     */
    @RequiresPermissions("archCollection:myAmsBatch:list")
    @PostMapping("/myAmsBatchList")
    @ResponseBody
    public TableDataInfo myAmsBatchList(AmsBatchVO amsBatchVO) {
        amsBatchVO.setOrgCode(ShiroUtils.getSysUser().getDeptId().toString());
        amsBatchVO.setCrtNo(ShiroUtils.getSysUser().getUserId().toString());
        List<AmsBatch> list = new ArrayList<>();
        List<String> deptList = new ArrayList<>();//用户管理全部部门
        deptList.add(ShiroUtils.getSysUser().getDeptId().toString());
        //查询当前用户辅部门
        String auxiliaryDept = ShiroUtils.getSysUser().getAuxiliaryDept();
        if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {//当前用户有辅部门
            String[] auxiliaryDeptArr = auxiliaryDept.split(",");
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
            list = amsBatchService.selectAmsBatchVOList(amsBatchVO, deptList);
        } else {
            startPage();
            list = amsBatchService.selectAmsBatchVOList(amsBatchVO);
        }
        return getDataTable(list);
    }

    /**
     * 导出档案列表
     */
    @RequiresPermissions("archCollection:amsRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBatch amsBatch) {
        List<AmsBatch> list = amsBatchService.selectAmsBatchList(amsBatch);
        ExcelUtil<AmsBatch> util = new ExcelUtil<>(AmsBatch.class);
        return util.exportExcel(list, AMS_BATCH);
    }

    /**
     * 新增档案
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案著录
     */
    @Log(title = "档案著录", businessType = BusinessType.INSERT)
    @PostMapping("/addBatch")
    @ResponseBody
    public AjaxResult addSave(AmsBatch amsBatch) throws Exception {
        List<ImFile> imFileList = new ArrayList<>();//著录时上传文件
        List<ImImage> imImageList = new ArrayList<>();//著录时上传图片
        List<ImFile> fileListReg = new ArrayList<>();//移交登记时上传文件
        List<ImImage> imageListReg = new ArrayList<>();//移交登记时上传图片
        AmsArcReg arcReg = amsArcRegService.selectAmsArcRegById(amsBatch.getArcNo());
        String regId = arcReg.getId();
        if ("10".equals(amsBatch.getArcFormat())) {
            // 电子化档案
            imFileList = fileService.selectImFilesByBatchId(amsBatch.getBatchNo());
            if (imFileList == null || imFileList.isEmpty()) {
                imImageList = imageService.selectImImagesByBatchId(amsBatch.getBatchNo());
                if (imImageList == null || imImageList.isEmpty()) {
                    //著录时没有上传文件，可查询移交登记时是否上传文件
                    fileListReg = fileService.selectImFilesByBatchId(regId);
                    if (fileListReg == null || fileListReg.isEmpty()) {
                        imageListReg = imageService.selectImImagesByBatchId(regId);
                        if (imageListReg == null || imageListReg.isEmpty()) {
                            return AjaxResult.error("电子档案请先上传文件");
                        }
                    }
                }
            }
            if (fileListReg != null && fileListReg.size() > 0) {
                fileService.updateBatchId(amsBatch.getBatchNo(), regId);
            }
            if (imageListReg != null && imageListReg.size() > 0) {
                imageService.updateBatchId(amsBatch.getBatchNo(), regId);
            }
        }

        SysUser loginUser = ShiroUtils.getSysUser();
        amsBatch.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//        amsBatch.setOrgCode(loginUser.getDeptId().toString());
//        amsBatch.setOrgName(loginUser.getDept().getDeptName());
        amsBatch.setOrgCode(arcReg.getRegOrgCode());
        amsBatch.setOrgName(arcReg.getRegOrgName());
        amsBatch.setCrtTime(new java.sql.Timestamp(System.currentTimeMillis()));
        amsBatch.setCrtNo(loginUser.getUserId().toString());
        amsBatch.setCrtName(loginUser.getUserName());
        //责任部门
        SysUser sysUser = sysUserService.selectUserByUserName(amsBatch.getRespOpName());
        SysDept sysDept = sysDeptService.selectDeptById(sysUser.getDeptId());
        amsBatch.setRespDepaName(sysDept.getDeptName());
        //归行档案员管理
        if ("1".equalsIgnoreCase(amsBatch.getArcHasMoveBank())) {
            amsBatch.setStatus(String.valueOf(AmsBatchStatus.YIZHURU.getValue()));
            // 移交行档案室时间
            amsBatch.setReceivingTime(new Date());
            //设置接收人（当前操作档案著录用户）
            amsBatch.setReceiveNo(String.valueOf(ShiroUtils.getUserId()));
            amsBatch.setReceiveName(ShiroUtils.getSysUser().getUserName());
            //设置移交人
            amsBatch.setArcTransfer(ShiroUtils.getSysUser().getUserName());
        } else {
            // 归部门管理
            amsBatch.setReceiveNo(String.valueOf(ShiroUtils.getUserId()));
            amsBatch.setReceiveName(ShiroUtils.getSysUser().getUserName());

            amsBatch.setArcTransfer(ShiroUtils.getSysUser().getUserName());
            amsBatch.setStatus(Constants.WAIT_SUBMIT);
            amsBatch.setTurnInNo(String.valueOf(ShiroUtils.getUserId()));
            amsBatch.setTurnInName(ShiroUtils.getSysUser().getUserName());
            amsBatch.setTurnInTime(new java.sql.Timestamp(System.currentTimeMillis()));
        }

        AmsArcReg amsArcReg = amsArcRegService.selectAmsArcRegById(amsBatch.getArcNo());

        if (amsArcReg != null) {
            // 如果选择的是机密或者是绝密档案，则退回
            if ("01".equals(amsBatch.getArcLevel()) || "02".equals(amsBatch.getArcLevel())) {
                amsArcReg.setStatus(new BigDecimal(AmsArcRegStatus.TUIHUI.getValue()));
                return toAjax(amsArcRegService.updateAmsArcReg(amsArcReg));
            } else {
                amsArcReg.setStatus(new BigDecimal(AmsArcRegStatus.RECORD.getValue()));
            }
            amsArcRegService.updateAmsArcReg(amsArcReg);
            // 接受时间 接收时间
            amsBatch.setReceiveTime(amsArcReg.getRecTime());
        }

        //新增著录
        int num = amsBatchService.insertAmsBatch(amsBatch);
        //著录档案归部门管理
        if ("0".equalsIgnoreCase(amsBatch.getArcHasMoveBank())) {
            //实物/声像档案处理
            if (amsBatch.getArcBillCode().equals("11100") || amsBatch.getArcBillCode().equals("12100")) {
                num = storeEntityArchives(amsBatch);
            }
        }
        return toAjax(num);
    }

    /**
     * 修改档案著录
     */
    @RequiresPermissions("archCollection:amsRecordEdit:view")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        mmap.put(AMS_BATCH, amsBatch);
        return prefix + "/edit";
    }

    /**
     * 新增著录
     */
    @RequiresPermissions("archCollection:amsRecordAdd:view")
    @GetMapping("/record/{id}")
    public String record(@PathVariable(value = "id", required = false) String id, ModelMap mmap) {
        AmsArcReg amsArcReg = amsArcRegService.selectAmsArcRegById(id);
        mmap.put("amsArcReg", amsArcReg);
        String nowTime = new SimpleDateFormat(Constants.DATE_TYPE_EIGHT).format(new Date());
        // 生成一个临时的batchId
        mmap.put("batchId", nowTime + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16));
        return prefix + "/amsRecord";
    }

    /**
     * 查看我的档案著录详细信息
     */
    @RequiresPermissions("archCollection:amsRecordDetail:view")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        String arcBillCode = amsBatch.getArcBillDeptCode();
        AmsBill amsBill = amsBillService.selectAmsBillById(arcBillCode);
        mmap.put("mouldStr", amsBill.getMouldStr());
        mmap.put(AMS_BATCH, amsBatch);
        return prefix + "/detail";
    }

    /**
     * 修改保存档案著录
     */
    @Log(title = "档案著录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBatch amsBatch) {
        amsBatch.setModTime(new Date());
        if ("10".equals(amsBatch.getArcFormat())) {
            // 电子化档案
            List<ImFile> imFileList = fileService.selectImFilesByBatchId(amsBatch.getBatchNo());
            if (imFileList == null || imFileList.size() == 0) {
                List<ImImage> imImageList = imageService.selectImImagesByBatchId(amsBatch.getBatchNo());
                if (imImageList == null || imImageList.size() == 0) {
                    return AjaxResult.error("电子档案请先上传文件");
                }
            }
        }
        return toAjax(amsBatchService.updateAmsBatch(amsBatch));
    }

    /**
     * 删除档案著录
     */
    @RequiresPermissions("archCollection:amsRecord:remove")
    @Log(title = "档案著录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBatchService.deleteAmsBatchByIds(ids));
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        List<Ztree> ztrees = amsBillService.selectAmsBillTree(new AmsBill());
        return ztrees;
    }

    /**
     * 加载部门列表树(只查询一级类目)
     */
    @GetMapping("/treeDataOneLevel")
    @ResponseBody
    public List<Ztree> treeDataOneLevel() {
        List<Ztree> ztrees = amsBillService.selectAmsBillTreeOneLevel(new AmsBill());
        return ztrees;
    }

    /**
     * 加载实物类型列表树
     */
    @GetMapping("/treeDataMatterType")
    @ResponseBody
    public List<Ztree> treeDataMatterType() {
        List<Ztree> ztrees = amsBillService.selectAmsBillMatterType(new AmsBill());
        return ztrees;
    }

    /**
     * 跳转到影像调阅页面
     *
     * @return
     */
    @GetMapping("/toUpload/{batchId}/{permission}")
    public String toUpload(@PathVariable("batchId") String batchId, @PathVariable("permission") String permission, ModelMap mmap) {
        mmap.put("batchId", batchId);
        mmap.put("permission", permission);
        // 用于上传完文件后，可能会刷新回显
        List<ImImage> imImageList = imageService.selectImImagesByBatchId(batchId);
        List<ImFile> imFileList = fileService.selectImFilesByBatchId(batchId);
        mmap.put("imageList", imImageList);
        mmap.put("fileList", imFileList);
        mmap.put("maxUploadSize", maxUploadSize);
        mmap.put("serverAddress", getServerAddress());
        return prefix + "/sweepImage";
    }

    /**
     * 档案著录跳转到影像调阅页面
     *
     * @return
     */
    @GetMapping("/toUploadBatch/{batchId}/{regId}/{permission}")
    public String toUploadBatch(@PathVariable("batchId") String batchId, @PathVariable("regId") String regId, @PathVariable("permission") String permission, ModelMap mmap) {
        mmap.put("batchId", batchId);
        mmap.put("permission", permission);
        // 用于上传完文件后，可能会刷新回显
        List batchIds = new ArrayList();
        batchIds.add(batchId);
        batchIds.add(regId);
        List<ImImage> imImageList = imageService.selectImImagesByBatchIdList(batchIds);
        List<ImFile> imFileList = fileService.selectImFilesByBatchIdList(batchIds);
        // 用于上传完文件后，可能会刷新回显
//        List<ImImage> imImageList = imageService.selectImImagesByBatchId(batchId);
//        List<ImFile> imFileList = fileService.selectImFilesByBatchId(batchId);
        mmap.put("imageList", imImageList);
        mmap.put("fileList", imFileList);
        mmap.put("maxUploadSize", maxUploadSize);
        mmap.put("serverAddress", getServerAddress());
        return prefix + "/sweepImage";
    }

    @GetMapping("/toScan/{batchId}")
    public String toScan(@PathVariable("batchId") String batchId, ModelMap mmap) {
        mmap.put("batchId", batchId);
        mmap.put("uuid", UUID.randomUUID().toString().replace("-", ""));
        mmap.put("httpfiletransIp", SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSIP));
        mmap.put("httpfiletransPort", SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT));
        mmap.put("serverAddress", getServerAddress());
        return prefix + "/scan";
    }


    /**
     * 查看影像
     *
     * @return
     */
    @GetMapping("/showImageAndFile/{batchId}/{permission}")
    public String showImageAndFile(@PathVariable("batchId") String batchId, @PathVariable("permission") String permission, ModelMap mmap) {

        List<ImImage> imImageList = imageService.selectImImagesByBatchId(batchId);

        List<ImFile> imFileList = fileService.selectImFilesByBatchId(batchId);

        mmap.put("imageList", imImageList);
        mmap.put("fileList", imFileList);
        mmap.put("batchId", batchId);
        mmap.put("permission", permission);
        mmap.put("maxUploadSize", maxUploadSize);
        mmap.put("serverAddress", getServerAddress());
        return prefix + "/sweepImage";

    }

    /**
     * 返回档案类型具体信息
     *
     * @return
     */
    @GetMapping("/findAmsBill/{id}")
    @ResponseBody
    public AmsBill findAmsBill(@PathVariable("id") String billId) {
        AmsBill amsBill = amsBillService.selectAmsBillById(billId);
        return amsBill;
    }


    /**
     * 上传文件至内管
     *
     * @param request
     * @param batchId
     * @return
     * @throws IOException
     */
    @PostMapping("/upload/{batchId}")
    @ResponseBody
    public AjaxResult addSave(HttpServletRequest request, @PathVariable("batchId") String batchId) {
        log.info("进入addSave方法");
        try {
            //获取著录信息
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartRequest.getFiles("file_data");
            MultipartFile[] fileArray = files.toArray(new MultipartFile[files.size()]);
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            log.info("---------------------准备数上传文件参数-------------------");
            files.stream().forEach(file -> {
                //组装发送报文
                jsonObject.put("tranCode", Constants.CMS_FILES_UPLOAD);
                jsonObject.put("authCode", SysConfigInitParamsUtils.getConfig(Constants.CMS_UPLOAD_SYSCODE));
                jsonObject.put("sysCode", SysConfigInitParamsUtils.getConfig(Constants.CMS_UPLOAD_SYSCODE_KEY));
                JSONObject object = new JSONObject();
                object.put("operationCode", sdf.format(System.currentTimeMillis()));
                object.put("fileName", file.getOriginalFilename());
                object.put("branchName", ShiroUtils.getSysUser().getDeptId());
                object.put("creater", ShiroUtils.getSysUser().getUserName());
                object.put("createUser", ShiroUtils.getSysUser().getUserName());
                object.put("batchId", batchId);
                object.put("projectBatch", PmsConstants.PMS_PROJECT_NO);
                object.put("orderNum", 1);
                try {
                    object.put("Md5", DigestUtils.md5Hex(file.getInputStream()));
                } catch (IOException e) {
                    logger.error("获取md5值失败", e.getMessage());
                    throw new RuntimeException("获取md5值失败", e.fillInStackTrace());
                }
                jsonArray.add(object);
            });
            log.info("---------------------准备数上传文件参数完毕-------------------");
            // 组装参数
            jsonObject.put("fileList", jsonArray);

            //通过Feign上传内管 并获取返回报文
            log.info("---------------------调用feign开始-------------------");
            log.info("************jsonObject=" + jsonObject.toJSONString());
            log.info("************fileArray=" + Arrays.toString(fileArray));
            String json = fileUploadFeignClient.uploadFiles(jsonObject.toString(), fileArray);
            log.info("---------------------调用feign结束-------------------");

            // 解析返回报文
            Map<String, Object> retJson = packageReturnJson(json);
            log.info("retJson=" + retJson.toString());
            // 保存影像及文件信息,返回上传失败文件
            Map<String, Object> returnMap = saveFileAndImage(retJson, files, batchId);
            log.info("returnMap=" + returnMap.toString());
            List<MultipartFile> multipartFileList = (List<MultipartFile>) returnMap.get("multipartFileList");
            List<String> fileIds = (List<String>) returnMap.get("fileIds");
            return multipartFileList.size() == 0 ? success(fileIds) : error("有上传文件失败");
        } catch (Exception e) {
            logger.error("有文件上传失败", e.getMessage());
            throw new RuntimeException("有文件上传失败", e.fillInStackTrace());
        }
    }

    /**
     * 通过文件的Md5值判断是否上传过
     *
     * @return
     */
    @PostMapping("/checkFilesMd5")
    @ResponseBody
    public AjaxResult checkFilesMd5(String filesJson) {
        JSONArray jsonArray = JSON.parseArray(filesJson);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            // 判断文件是否上传过
            int count = checkUploadFileIsFile(object.getString("fileType")) ? imFileService.selectImFileByMd5(object.getString("md5")) : imageService.selectImImageByMd5(object.getString("md5"));
            if (count > 0) {
                object.put("exists", "true");
            } else {
                object.put("exists", "false");
            }
        }
        return success(jsonArray);
    }


    /**
     * 保存影像信息
     *
     * @param msg
     * @param file
     * @return
     * @throws IOException
     */
    private String saveImage(FileAndImageRetMsg msg, MultipartFile file) throws IOException {
        ImImage imImage = new ImImage();
        imImage.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        imImage.setImageId(snowFlakeUtils.nextId());
        imImage.setImageName(msg.getFileName());
        imImage.setStatus(Constants.NORMAL_IMAGE.toString());
        imImage.setBatchId(msg.getBatchNo());
        imImage.setCreateTime(new Date());
        imImage.setHadoopType(msg.getHadoopType());
        imImage.setMd5(msg.getMd5());
        imImage.setImageServerUrl(msg.getFileUrl());
        imImage.setOcrType(msg.getFileType());
        imImage.setImagePath(msg.getWillPath());
        BigDecimal bd = new BigDecimal(msg.getFileSize());
        BigDecimal kb = new BigDecimal(1024);
        imImage.setImageSize(bd.divide(kb, 2, BigDecimal.ROUND_UP));
        imImage.setCreateUser(ShiroUtils.getUserId().toString());
        imImage.setSysFlagInt(1);
        imImage.setImageRealId(Long.valueOf(msg.getFileId()));
        imageService.insertImImage(imImage);
        return imImage.getId();
    }

    /**
     * 保存文件信息
     *
     * @param msg
     * @param file
     * @return
     */
    private String saveFile(FileAndImageRetMsg msg, MultipartFile file) {
        // 保存文件
        ImFile imFile = new ImFile();
        imFile.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        imFile.setFileId(snowFlakeUtils.nextId());
        imFile.setFileName(msg.getFileName());
        imFile.setStatus(Constants.NORMAL_IMAGE.toString());
        imFile.setBatchId(msg.getBatchNo());
        imFile.setCreateTime(new Date());
        imFile.setHadoopType(msg.getHadoopType());
        imFile.setSysFlagInt(1);
        imFile.setMd5(msg.getMd5());
        imFile.setFilePath(msg.getWillPath());
        imFile.setFileServerUrl(msg.getFileUrl());
        BigDecimal bd = new BigDecimal(msg.getFileSize());
        BigDecimal kb = new BigDecimal(1024);
        imFile.setFileSize(bd.divide(kb, 2, BigDecimal.ROUND_UP));
        imFile.setFileType(msg.getFileType());
        imFile.setCreateUser(ShiroUtils.getUserId().toString());
        imFile.setFileRealId(Long.valueOf(msg.getFileId()));
        imFileService.insertImFile(imFile);
        return imFile.getId();
    }

    /**
     * 保存文件及影像信息
     *
     * @param retJson
     * @param files
     * @param batchId
     * @return
     * @throws IOException
     */
    private Map<String, Object> saveFileAndImage(Map<String, Object> retJson, List<MultipartFile> files, String batchId) throws IOException {
        Map<String, Object> returnMap = new HashMap<>(8);
        //整合返回报文及文件信息
        List<FileAndImageRetMsg> fileMsgList = conformityRetJsonAndFile(retJson, files, batchId);
        log.info("fileMsgList=" + fileMsgList.toString());
        List<MultipartFile> multipartFileList = new ArrayList<>();
        List<String> fileIds = new ArrayList<>();
        for (int i = 0; i < fileMsgList.size(); i++) {
            if (OuterInterfaceConstants.OUTER_UPLOAD_SUCCESS.equals(fileMsgList.get(i).getUploadSuccess())) {
                //文件上传成功
                if (checkUploadFileIsImage(fileMsgList.get(i).getFileType().toLowerCase())) {
                    // 图片
                    fileIds.add(saveImage(fileMsgList.get(i), files.get(i)));
                } else {
                    // 文件
                    fileIds.add(saveFile(fileMsgList.get(i), files.get(i)));
                }
            } else {
                //文件上传失败,重传
                multipartFileList.add(files.get(Integer.valueOf(fileMsgList.get(i).getOrderNum())));
                log.info("multipartFileList=" + multipartFileList.toString());
            }
        }
        returnMap.put("multipartFileList", multipartFileList);
        returnMap.put("fileIds", fileIds);
        return returnMap;
    }


    /**
     * 验证文件是否为图片
     *
     * @param extension
     */
    private Boolean checkUploadFileIsImage(String extension) {
        // 后缀是图像,保存到cms_image
        for (int i = 0; i < allowedImageExtension.length; i++) {
            if (extension.equals(allowedImageExtension[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证文件是否为文件
     *
     * @param extension
     */
    private Boolean checkUploadFileIsFile(String extension) {
        // 后缀是图像,保存到cms_image
        for (int i = 0; i < allowedFileExtension.length; i++) {
            if (extension.equals(allowedFileExtension[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 整合返回报文及文件信息
     *
     * @param retJson 返回报文信息
     * @param files   文件列表
     * @param batchId 著录信息
     * @return
     */
    private List<FileAndImageRetMsg> conformityRetJsonAndFile(Map<String, Object> retJson, List<MultipartFile> files, String batchId) {
        List<FileAndImageRetMsg> fileMsgList = new ArrayList<>();
        JSONArray jsonArray = (JSONArray) retJson.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_TOTALRESULTDATA);
        if ((files.size() > 0 && jsonArray == null) || (jsonArray.size() != files.size())) {
            throw new RuntimeException("内管返回报文文件列表与应上传文件数不一致");
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            FileAndImageRetMsg fileMsg = new FileAndImageRetMsg();
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if ("".equals(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_RETURNCODE)) || jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_RETURNCODE) == null) {
                throw new RuntimeException("resultCode字段未返回");
            }
            fileMsg.setUploadSuccess(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_RETURNCODE).toString());
            if (!"".equals(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_FILEID)) || jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_FILEID) != null) {
                fileMsg.setFileId(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_FILEID).toString());
            }
            if (!"".equals(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_FILEURL)) || jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_FILEURL) != null) {
                fileMsg.setFileUrl(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_FILEURL).toString());
            }
            if (!"".equals(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_MD5)) && jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_MD5) != null) {
                fileMsg.setMd5(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_MD5).toString());
            }
            if (!"".equals(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_HADOOPTYPE)) && jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_HADOOPTYPE) != null) {
                fileMsg.setHadoopType(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_HADOOPTYPE).toString());
            }
            if (!"".equals(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_WILLPATH)) && jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_WILLPATH) != null) {
                fileMsg.setWillPath(jsonObject.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_WILLPATH).toString());
            }
            MultipartFile multipartFile = files.get(i);
            fileMsg.setFileName(multipartFile.getOriginalFilename());
            fileMsg.setFileType(FileUploadUtils.getExtension(multipartFile));
            fileMsg.setFileSize(String.valueOf(multipartFile.getSize()));
            fileMsg.setBatchNo(batchId);
            fileMsgList.add(fileMsg);
        }
        return fileMsgList;
    }


    /**
     * 组装返回报文
     *
     * @param json
     * @return
     */
    private Map<String, Object> packageReturnJson(String json) throws RuntimeException {
        log.info("packageReturnJson方法-------json=" + json);
        Map<String, Object> retMap = new HashMap<>();
        JSONObject object = JSON.parseObject(json);
        if ("".equals(object.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_SUCCESS)) || object.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_SUCCESS) != null) {
            throw new RuntimeException("返回报文有误");
        }
        retMap.put(OuterInterfaceConstants.OUTER_UPLOAD_TOTAL_RETURNCODE, object.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_SUCCESS));
        retMap.put(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_TOTALRESULTDATA, object.get(OuterInterfaceConstants.OUTER_UPLOAD_INTERFACE_TOTALRESULTDATA));
        return retMap;
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
     * 调转到视频音频播放页
     *
     * @return
     */
    @GetMapping("/toVideo")
    public String toVideo(String fileId, ModelMap mmap) {
        ImFile imFile = imFileService.selectImFileById(fileId);
        mmap.put("videoUrl", imFile.getFileServerUrl());
        return prefix + "/video";
    }


    /**
     * 上传图片文件
     *
     * @param request
     * @return
     */
    @PostMapping("/uploadImg")
    @ResponseBody
    public AjaxResult uploadImg(HttpServletRequest request) {
        // 已经上传完大数据后存储结构化记录
        try {
            String batchId = request.getParameter("batchId");
            String SDKPath = request.getParameter("SDKPath");
            String imageFileName = request.getParameter("scanFileName");
            String[] imageFileNames = imageFileName.split(",");
            // 结构化数据存储
            for (int i = 0; i < imageFileNames.length; i++) {
                String filePath = SDKPath + "/" + imageFileNames[i].replaceAll("JPG", "jpg");
                ImImage imImage = new ImImage();
                imImage.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                imImage.setImageId(snowFlakeUtils.nextId());
                imImage.setImageName(imageFileNames[i]);
                imImage.setStatus(Constants.NORMAL_IMAGE.toString());
                imImage.setBatchId(batchId);
                imImage.setCreateTime(new Date());
                imImage.setOcrType("jpg");
                imImage.setImageServerUrl("http://" + SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSIP) + ":" +
                        SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT) + SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHDOWN) + filePath + "&hadoopType=0");
                imImage.setImagePath(filePath);
                imImage.setHadoopType("0");
                imImage.setCreateUser(ShiroUtils.getUserId().toString());
                imImage.setSysFlagInt(1);
                imageService.insertImImage(imImage);
            }
            return success();
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败", e.fillInStackTrace());
        }

    }

    /**
     * 删除文件
     *
     * @param deleteFileId
     * @return
     */
    @Log(title = "删除文件", businessType = BusinessType.DELETE)
    @GetMapping("/deleteFile/{deleteFileId}")
    @ResponseBody
    public AjaxResult deleteFile(@PathVariable("deleteFileId") String deleteFileId) {
        int row = fileService.deleteImFileByIds(deleteFileId);
        return row > 0 ? toAjax(row) : toAjax(imageService.deleteImImageByIds(deleteFileId));
    }

    /**
     * 下载文件(返回文件)
     * add by yinrui 2019-09-11
     */
    @RequestMapping("/downloadFileGet")
    @ResponseBody
    public String downloadFileGet(String fileId, HttpServletResponse response) throws IOException {
        // 临时本地服务器地址
        ImFile imFile = imFileService.selectImFileById(fileId);
        ImImage imImage = null;
        if (imFile == null) {
            imImage = imageService.selectImImageById(fileId);
            if (imImage == null) {
                return "无该文件或影像";
            }
        }
        String path = imFile != null ? imFile.getFilePath() : imImage.getImagePath();
        String fileName = imFile != null ? imFile.getFileName() : imImage.getImageName();
        String hadoopType = imFile != null ? imFile.getHadoopType() : imImage.getHadoopType();
        log.info("getServerPath()=" + getServerPath());
        boolean b = UploadUtil.downloadTrans(getServerPath(), fileName, path + "&hadoopType=" + hadoopType);
        if (!b) {
            return "从HDFS下载到服务器失败";
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/force-download");
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try (FileInputStream fis = new FileInputStream(new File(getServerPath(), fileName))) {
            response.setHeader("Content-disposition ", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            bos = new BufferedOutputStream(response.getOutputStream());
            bis = new BufferedInputStream(fis);
            byte[] bytes = new byte[1024];
            int lenth = 0;
            while ((lenth = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, lenth);
                bos.flush();
            }
        } catch (IOException e) {
            log.error("从应用服务器下载文件失败！");
        } finally {
            if (null != bos) {
                bos.close();
            }
            if (null != bis) {
                bis.close();
            }
        }

        return "下载成功";
    }

    // 返回download路径，
    public static String downloadAPI(String fileId) {
        return "http://" +
                SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_IP) + ":" +
                SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PORT) +
                "/ams/archCollection/amsRecord/downloadFileGet?fileId=" + fileId;
    }

    /**
     * 批量下载文件(get方式)
     * add by yinrui 2019-09-20
     */
    @RequestMapping("/downloadFilesGet")
    @ResponseBody
    public String downloadFilesGet(String fileIds, Integer fileSum, HttpServletResponse response) throws Exception {
        List<ImFile> imFiles = imFileService.selectCmsFileByIds(Convert.toStrArray(fileIds));
        List<ImImage> imImages = imageService.selectImImageByIds(Convert.toStrArray(fileIds));
        BufferedInputStream bis = null;
        /**
         * 临时zip名称
         */
        String zipName = "files.zip";
        ZipOutputStream zip = null;
        try (FileOutputStream fis = new FileOutputStream(getServerPath() + zipName)) {
            zip = new ZipOutputStream(fis);
            // 文件类型
            for (int i = 0; i < imFiles.size(); i++) {
                boolean b = UploadUtil.downloadTrans(getServerPath(), imFiles.get(i).getFileName(), imFiles.get(i).getFilePath() + "&hadoopType=" + imFiles.get(i).getHadoopType());
                if (!b) {
                    return "从HDFS下载到服务器失败";
                }
                try (FileInputStream fisNew = new FileInputStream(getServerPath() + imFiles.get(i).getFileName())) {
                    bis = new BufferedInputStream(fisNew);
                    String fileName = imFiles.get(i).getFileName();
                    if (i > 0 && fileName.equals(imFiles.get(i - 1).getFileName())) {
                        String[] split = imFiles.get(i).getFileName().split("\\.");
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
                } finally {
                    if (zip != null && imImages.size() == 0) {
                        zip.closeEntry();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null && imImages.size() == 0 && i == imFiles.size() - 1) {
                        fis.close();
                    }
                }
            }
            // 图片类型
            if (imFiles.size() < fileSum) {
                for (int i = 0; i < imImages.size(); i++) {
                    boolean b = UploadUtil.downloadTrans(getServerPath(), imImages.get(i).getImageName(), imImages.get(i).getImagePath() + "&hadoopType=" + imImages.get(i).getHadoopType());
                    if (!b) {
                        return "从HDFS下载到服务器失败";
                    }
                    try (FileInputStream fis1 = new FileInputStream(getServerPath() + imImages.get(i).getImageName())) {
                        bis = new BufferedInputStream(fis1);
                        String fileName = imImages.get(i).getImageName();
                        if (i > 0 && fileName.equals(imImages.get(i - 1).getImageName())) {
                            String[] split = imImages.get(i).getImageName().split("\\.");
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
                    } finally {
                        if (zip != null) {
                            zip.closeEntry();
                        }
                        if (fis != null && i == imImages.size() - 1) {
                            fis.close();
                        }
                        if (bis != null) {
                            bis.close();
                        }
                    }
                }
            }
        } finally {
            if (null != zip) {
                zip.closeEntry();
            }
        }

        File file = null;
        InputStream ins = null;
        OutputStream outs = null;
        file = new File(getServerPath() + zipName);
        response.setHeader("Content-Disposition", "attachment;filename=\"fwz.zip\"");
        response.setContentType("application/zip");
        try {
            ins = new FileInputStream(getServerPath() + zipName);
            outs = response.getOutputStream();
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
        } finally {
            if (ins != null) {
                ins.close();
            }
            if (outs != null) {
                outs.close();
            }
        }
        //删除临时创建的压缩包
        if (!file.delete()) {
            throw new FileNotFoundException("文件删除失败");
        }
        return "下载成功";
    }

    /**
     * 文件下载
     *
     * @param request  页面请求
     * @param response 响应
     * @return 结果
     */
    @RequestMapping("/downloadHelp")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置文件路径
        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            log.error("获取路径失败", e);
        }
        // 设置文件名
        String fileName = "help.docx";
        String realPath = path + "static/file";
        File file = new File(realPath, fileName);
        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = null;
            FileInputStream fis = null;
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
                log.error("下载失败", e);
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }
        }
    }

    // 获得本地系统路径 add by yr
    private String getServerPath() {
        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_SERVER_PATH) : SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH);
    }

    // 获取系统环境的ip add by yr
    private String getServerIp() {
        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_FILETRANSIP) : SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_IP);
    }

    // 获得服务器地址
    private String getServerAddress() {
        return "http://" + getServerIp() + ":" + SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PORT) + "/ams";
    }


    /**
     * 会计档案著录批量导入
     *
     * @param file
     * @return
     */
    @RequiresPermissions("archCollection:amsRecord:import")
    @PostMapping("/batchUpload")
    @ResponseBody
    public AjaxResult batchArcRegUpload(MultipartFile file, ModelMap mmap) throws Exception {
        Set<String> validMsg = new HashSet<String>();
        Map<String, Object> erroMsg = new HashMap<String, Object>();
        List<Object> errMsgList = new ArrayList<Object>();
        int insertNum = 0;
        if (null == file) {
            return null;
        }
        try {
            ExcelUtil<AmsBatch> util = new ExcelUtil<>(AmsBatch.class);
            List<AmsBatch> amsBatchList = util.importExcel(file.getInputStream());
            SysUser loginUser = ShiroUtils.getSysUser();

            for (AmsBatch amsBatch : amsBatchList) {
                int i = amsBatchList.indexOf(amsBatch);
                //题名
                if (amsBatch.getArcName() == null || "".equals(amsBatch.getArcName())) {
                    validMsg.add(" 第【" + (i + 1) + "】行 " + "【题名】");
                    continue;
                } else {
                    if (amsBatch.getArcName().length() > 255) {
                        erroMsg.put("arcNameErr", " 第【" + (i + 1) + "】行 " + "【题名】超出最大长度255个字符");
                        continue;
                    }
                }
                //保管人
                if (amsBatch.getRespOpName() == null || "".equals(amsBatch.getRespOpName())) {
                    validMsg.add(" 第【" + (i + 1) + "】行 " + " 【保管人】");
                    continue;
                } else {
                    if (amsBatch.getRespOpName().length() > 255) {
                        erroMsg.put("opNameErr", " 第【" + (i + 1) + "】行 " + "【保管人】超出最大长度255个字符");
                        continue;
                    } else {
                        SysUser sysUser = sysUserService.selectUserByUserName(amsBatch.getRespOpName());
                        if (sysUser == null) {
                            erroMsg.put("opNameErr", " 第【" + (i + 1) + "】行 【保管人】 " + amsBatch.getRespOpName() + " 不在用户表中，请联系行档案管理员");
                            continue;
                        }
                    }
                }
                //文件编号
                if (amsBatch.getArcCode() == null || "".equals(amsBatch.getArcCode())) {
                    validMsg.add(" 第【" + (i + 1) + "】行 " + " 【文件编号】");
                    continue;
                } else {
                    if (amsBatch.getArcCode().length() > 255) {
                        erroMsg.put("arcCodeErr", " 第【" + (i + 1) + "】行 " + "【文件编号】超出最大长度255个字符");
                        continue;
                    }
                }
                //装订日期
                if (amsBatch.getArcCreTime() == null) {
                    validMsg.add(" 第【" + (i + 1) + "】行 " + " 【装订日期】");
                    continue;
                }
                //笔数
                if (amsBatch.getTotalTransactions() == null || "".equals(amsBatch.getTotalTransactions())) {
                    validMsg.add(" 第【" + (i + 1) + "】行 " + " 【笔数】");
                    continue;
                } else {
                    if (amsBatch.getTotalTransactions().length() > 255) {
                        erroMsg.put("totalErr", " 第【" + (i + 1) + "】行 " + "【笔数】超出最大长度255个字符");
                        continue;
                    }
                }
                //档案类型
                if (amsBatch.getArcBill() == null || "".equals(amsBatch.getArcBill())) {
                    validMsg.add(" 第【" + (i + 1) + "】行 " + " 【档案类型】");
                    continue;
                } else if (!AMSBILLNAME.equals(amsBatch.getArcBill())) {
                    erroMsg.put("billErr", " 第【" + (i + 1) + "】行 " + "不是会计档案，不允许著录");
                    continue;
                }
                //二级类目
                if (amsBatch.getArcBillDept() == null || "".equals(amsBatch.getArcBillDept())) {
                    validMsg.add(" 第【" + (i + 1) + "】行 " + " 【二级类目】");
                    continue;
                } else {
                    AmsBill amsBill = new AmsBill();
                    amsBill.setName(amsBatch.getArcBill());
                    List<AmsBill> list = amsBillService.selectAmsBillList(amsBill);
                    //档案子类型(二级类目)
                    List<String> billDeptNames = amsBillService.queryArcBillDept(list.get(0).getId());
                    for (int n = 0; n < billDeptNames.size(); n++) {
                        String name = billDeptNames.get(n);
                        int start = name.indexOf(",") + 1;
                        int len = billDeptNames.get(n).length() - 1;
                        if (!amsBatch.getArcBillDept().equals(name.substring(start, len).trim())) {
                            erroMsg.put("billDeptErr", " 第【" + (i + 1) + "】行 " + "会计档案没有此【二级类目】，请联系行档案管理员");
                            continue;
                        } else {
                            erroMsg.put("billDeptErr", "");
                            break;
                        }
                    }
                }
                //保管期限
                if (amsBatch.getValPeriod() == null || "".equals(amsBatch.getValPeriod())) {
                    validMsg.add(" 第【" + (i + 1) + "】行 " + " 【保管期限】");
                    continue;
                } else {
                    if (amsBatch.getValPeriod().length() > 255) {
                        erroMsg.put("periodErr", " 第【" + (i + 1) + "】行 " + "【保管期限】超出最大长度255个字符");
                        continue;
                    } else {
                        AmsParam amsParam = new AmsParam();
                        amsParam.setParamSpeciesCode("VAL_PERIOD");
                        List<AmsParam> periodList = amsParamService.selectAmsParamList(amsParam);
                        for (int n = 0; n < periodList.size(); n++) {
                            if (!amsBatch.getValPeriod().equals(periodList.get(n).getParamCode())) {
                                erroMsg.put("notFoundPeriod", " 第【" + (i + 1) + "】行 " + "没有此保管期限，请联系行档案管理员");
                                continue;
                            } else {
                                erroMsg.put("notFoundPeriod", "");
                                break;
                            }
                        }
                    }
                }
                //密级(默认为内部公开)
                if (amsBatch.getArcLevel() == null || "".equals(amsBatch.getArcLevel())) {
                    validMsg.add(" 第【" + (i + 1) + "】行 " + " 【密级】");
                    continue;
                } else {
                    AmsParam amsParam = new AmsParam();
                    amsParam.setParamSpeciesCode("ARC_LEVEL");
                    List<AmsParam> levelList = amsParamService.selectAmsParamList(amsParam);
                    for (int n = 0; n < levelList.size(); n++) {
                        if (!amsBatch.getArcLevel().equals(levelList.get(n).getParamCode())) {
                            erroMsg.put("notFoundLevel", " 第【" + (i + 1) + "】行 " + "没有此密级，请联系行档案管理员");
                            continue;
                        } else {
                            //绝密、机密
                            if ("01".equals(amsBatch.getArcLevel()) || "02".equals(amsBatch.getArcLevel())) {
                                erroMsg.put("levelErr", " 第【" + (i + 1) + "】行 " + "机密、绝密档案不允许著录，请联系行档案管理员");
                                continue;
                            } else if ("1".equals(amsBatch.getArcLevel()) || "2".equals(amsBatch.getArcLevel())) {
                                erroMsg.put("levelErr", " 第【" + (i + 1) + "】行 " + "机密、绝密档案不允许著录，请联系行档案管理员");
                                continue;
                            } else {
                                erroMsg.put("levelErr", "");
                                erroMsg.put("notFoundLevel", "");
                                break;
                            }
                        }
                    }
                }
                //附件
                if (amsBatch.getETC() != null && !"".equals(amsBatch.getETC())) {
                    if (amsBatch.getETC().length() > 255) {
                        erroMsg.put("etcErr", " 第【" + (i + 1) + "】行 " + "【附件】超出最大长度255个字符");
                        continue;
                    }
                }
                //备注信息
                if (amsBatch.getRemark() != null && !"".equals(amsBatch.getRemark())) {
                    if (amsBatch.getRemark().length() > 255) {
                        erroMsg.put("remarkErr", " 第【" + (i + 1) + "】行 " + "【备注信息】超出最大长度255个字符");
                        continue;
                    }
                }
                //摘要
                if (amsBatch.getArcAbstract() != null && !"".equals(amsBatch.getArcAbstract())) {
                    if (amsBatch.getArcAbstract().length() > 255) {
                        erroMsg.put("abstractErr", " 第【" + (i + 1) + "】行 " + "【摘要】超出最大长度255个字符");
                        continue;
                    }
                }

                if (validMsg.size() > 0) {
                    return AjaxResult.error("必输项为空", new ArrayList<>(validMsg));
                }
                if (erroMsg.size() > 0) {
                    Set<Map.Entry<String, Object>> entry = erroMsg.entrySet();
                    for (Map.Entry<String, Object> obj : entry) {
                        if (!"".equals(obj.getValue())) {
                            errMsgList.add(obj.getValue());
                        }
                    }
                    if (errMsgList.size() > 0) {
                        return AjaxResult.error("输入项错误", errMsgList);
                    }
                }

                amsBatch.setId(UUID.randomUUID().toString().replace("-", ""));
                String nowTime = new SimpleDateFormat(Constants.DATE_TYPE_EIGHT).format(new Date());
                //注入批次号
                amsBatch.setBatchNo(nowTime + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16));
                AmsBill amsBill = new AmsBill();
                amsBill.setName(amsBatch.getArcBill());
                List<AmsBill> billList;
                billList = amsBillService.selectAmsBillList(amsBill);
                if (billList.size() > 0) {
                    //档案类型编号
                    amsBatch.setArcBillCode(billList.get(0).getCode());
                }
                amsBill.setParentId(amsBatch.getArcBillCode());
                amsBill.setName(amsBatch.getArcBillDept());
                billList = amsBillService.selectAmsBillList(amsBill);
                if (billList.size() > 0) {
                    //档案二级类目编号
                    amsBatch.setArcBillDeptCode(billList.get(0).getCode());
                }
                //状态 待入盒:3
                amsBatch.setStatus("3");
                //是否移交行档室 否:0, 只属于部门档案
                amsBatch.setArcHasMoveBank("0");

                //责任部门
                SysUser sysUser = sysUserService.selectUserByUserName(amsBatch.getRespOpName());
                SysDept sysDept = sysDeptService.selectDeptById(sysUser.getDeptId());
                amsBatch.setRespDepaName(sysDept.getDeptName());

                //创建时间
                amsBatch.setCrtTime(new Date());
                //创建人号
                amsBatch.setCrtNo(loginUser.getUserId().toString());
                //创建人名称
                amsBatch.setCrtName(loginUser.getUserName());
                //部门代码
                amsBatch.setOrgCode(loginUser.getDept().getDeptId().toString());
                //部门名称
                amsBatch.setOrgName(loginUser.getDept().getDeptName());

                //接收人号
                amsBatch.setReceiveNo(loginUser.getUserId().toString());
                //接收人名称
                amsBatch.setReceiveName(loginUser.getUserName());
                //移交人员
                amsBatch.setArcTransfer(loginUser.getUserName());
                //接收日期
                amsBatch.setReceiveTime(new Date());

                //档案形态
                if (null == amsBatch.getArcFormat1() || "".equals(amsBatch.getArcFormat1())) {
                    //实物形态
                    amsBatch.setArcFormat("20");
                } else {
                    if (amsBatch.getArcFormat1().equals("1")) {
                        //电子形态
                        amsBatch.setArcFormat("10");
                    } else {
                        //实物形态
                        amsBatch.setArcFormat("20");
                    }
                }

                //归档部门
                amsBatch.setFilingDepa(loginUser.getDept().getDeptName());
                //归档部门编号
                amsBatch.setFilingDepaCode(loginUser.getDept().getDeptId().toString());
                //归档日期
                amsBatch.setFilingTime(new Date());

                insertNum = amsBatchService.insertAmsBatch(amsBatch);
            }

        } catch (Exception e) {
            return AjaxResult.error("导入失败");
        }
        if (validMsg.size() > 0) {
            return AjaxResult.error("必输项为空", new ArrayList<>(validMsg));
        }
        if (erroMsg.size() > 0) {
            Set<Map.Entry<String, Object>> entry = erroMsg.entrySet();
            for (Map.Entry<String, Object> obj : entry) {
                if (!"".equals(obj.getValue())) {
                    errMsgList.add(obj.getValue());
                }
            }
            if (errMsgList.size() > 0) {
                return AjaxResult.error("输入项错误", errMsgList);
            }
        }
        return AjaxResult.success("导入成功", insertNum);
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
        cabinet.setDepType(depot.getDepotType());
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
        depot.setDepotType("10");
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
            num = amsArchivesService.updateAmsArchives(amsArchives1);
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
            dp.setDepotType("10");
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

    /**
     * 导出模板
     **/
    @GetMapping("/importTemplate/{id}")
    @ResponseBody
    public AjaxResult importTemplate(@PathVariable String id) {
        if (id.equals("acc")) {
            ExcelUtil<AmsBatch> util = new ExcelUtil<AmsBatch>(AmsBatch.class);
            return util.importTemplateExcel("会计档案模板");
        } else if (id.equals("imp")) {
            ExcelUtil<AmsBatchDTO4Impt> utilImp = new ExcelUtil<AmsBatchDTO4Impt>(AmsBatchDTO4Impt.class);
            return utilImp.importTemplateExcel("导入模板");
        }
        return toAjax(0);
    }


    @PostMapping("/getFileImageById")
    @ResponseBody
    public AjaxResult getFileImageById(String id) {
        ImFile imFile = imFileService.selectImFileById(id);
        return imFile != null ? AjaxResult.success(imFile) : AjaxResult.success(imageService.selectImImageById(id));
    }

    /**
     * @param data
     * @param mmap
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadBatchRecord")
    @ResponseBody
    public AjaxResult uploadBatchRecord(@RequestBody String data, ModelMap mmap) throws Exception {
        Integer insertNum = 0;
        if (null == data) {
            return null;
        }

        try {
            SysUser loginUser = ShiroUtils.getSysUser();

            JSONParser jsonParser = new JSONParser(data);
            Map<String, Object> map = jsonParser.parseMap();
            //AmsBatch的Excel著录对象
            AmsBatchDTO4Impt amsBatch = new AmsBatchDTO4Impt();

            //Excel导入字段设置为属性值
            if (amsBatch != null) {
                //题名
                amsBatch.setArcName(map.get("题名(必输)").toString());
                //责任者
                amsBatch.setRespOpName(map.get("责任者(必输)").toString());
                if (map.containsKey("形成日期")) {
                    //日期字符串
                    String date = map.get("形成日期").toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
                    //形成日期
                    amsBatch.setArcCreTime(sdf.parse(date));
                } else {
                    //形成日期为空 设置为默认时间 1970/01/01
                    String date = "1/1/1970";
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    //形成日期
                    amsBatch.setArcCreTime(sdf.parse(date));
                }

                //档案类型
                amsBatch.setArcBill(map.get("档案类型(必输)").toString());
                //二级类目
                amsBatch.setArcBillDept(map.get("二级类目(必输)").toString());
                //文件编号
                amsBatch.setArcCode(map.get("文件编号(必输)").toString());
                if (map.containsKey("页数")) {
                    if (!"".equals(map.get("页数").toString())) {
                        Integer arcPageNum = Integer.valueOf(map.get("页数").toString());
                        //页数
                        amsBatch.setArcPageNum(arcPageNum);
                    }
                }
                if (map.containsKey("份数")) {
                    if (!"".equals(map.get("份数").toString())) {
                        Integer arcNum = Integer.valueOf(map.get("份数").toString());
                        //份数
                        amsBatch.setArcNum(arcNum);
                    }
                }
                String valPeriod = map.get("保管期限(10年、30年、永久)(必输)").toString();
                if (valPeriod.equals("永久")) {
                    valPeriod = "99";
                }
                //保管期限
                amsBatch.setValPeriod(valPeriod);
                String arcLevel = map.get("密级(必输，默认内部公开)").toString();
                if (arcLevel.equals("内部公开")) {
                    arcLevel = "04";
                }
                if (arcLevel.equals("秘密")) {
                    arcLevel = "03";
                }
                //密级
                amsBatch.setArcLevel(arcLevel);
                if (map.containsKey("是否涉及费用")) {
                    if (!"".equals(map.get("是否涉及费用").toString())) {
                        String expenseInvolve = map.get("是否涉及费用").toString();
                        if (expenseInvolve.equals("是")) {
                            expenseInvolve = "1";
                        }
                        if (expenseInvolve.equals("否")) {
                            expenseInvolve = "0";
                        }
                        //是否涉及费用
                        amsBatch.setExpenseInvolve(expenseInvolve);
                    }
                }
                if (map.containsKey("是否移交行档室")) {
                    if (!"".equals(map.get("是否移交行档室").toString())) {
                        String arcHasMoveBank = map.get("是否涉及费用").toString();
                        if (arcHasMoveBank.equals("是")) {
                            arcHasMoveBank = "1";
                        }
                        if (arcHasMoveBank.equals("否")) {
                            arcHasMoveBank = "0";
                        }
                        //是否移交行档室
                        amsBatch.setArcHasMoveBank(arcHasMoveBank);
                    }
                }
                if (map.containsKey("文件路径")) {
                    if (!"".equals(map.get("文件路径").toString())) {
                        //文件路径
                        amsBatch.setBatchFileUrl(map.get("文件路径").toString());
                    }
                }

                amsBatch.setId(UUID.randomUUID().toString().replace("-", ""));
                String nowTime = new SimpleDateFormat(Constants.DATE_TYPE_EIGHT).format(new Date());
                //注入批次号
                amsBatch.setBatchNo(nowTime + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16));
                AmsBill amsBill = new AmsBill();
                amsBill.setName(amsBatch.getArcBill());
                List<AmsBill> billList;
                billList = amsBillService.selectAmsBillList(amsBill);
                if (billList.size() > 0) {
                    //档案类型编号
                    amsBatch.setArcBillCode(billList.get(0).getCode());
                }
                amsBill.setParentId(amsBatch.getArcBillCode());
                amsBill.setName(amsBatch.getArcBillDept());
                billList = amsBillService.selectAmsBillList(amsBill);
                if (billList.size() > 0) {
                    //档案二级类目编号
                    amsBatch.setArcBillDeptCode(billList.get(0).getCode());
                }
                //状态 待入盒:3
                amsBatch.setStatus("3");
                //是否移交行档室 否:0, 只属于部门档案
                amsBatch.setArcHasMoveBank("0");

                //责任部门
                SysUser sysUser = sysUserService.selectUserByUserName(amsBatch.getRespOpName());
                SysDept sysDept = sysDeptService.selectDeptById(sysUser.getDeptId());
                amsBatch.setRespDepaName(sysDept.getDeptName());

                //创建时间
                amsBatch.setCrtTime(new Date());
                //创建人号
                amsBatch.setCrtNo(loginUser.getUserId().toString());
                //创建人名称
                amsBatch.setCrtName(loginUser.getUserName());
                //部门代码
                amsBatch.setOrgCode(loginUser.getDept().getDeptId().toString());
                //部门名称
                amsBatch.setOrgName(loginUser.getDept().getDeptName());

                //接收人号
                amsBatch.setReceiveNo(loginUser.getUserId().toString());
                //接收人名称
                amsBatch.setReceiveName(loginUser.getUserName());
                //移交人员
                amsBatch.setArcTransfer(loginUser.getUserName());
                //接收日期
                amsBatch.setReceiveTime(new Date());

                //档案形态
                //默认电子形态
                amsBatch.setArcFormat("10");
                amsBatch.setCarrier("03");
//                if (null == amsBatch.getArcFormat1() || "".equals(amsBatch.getArcFormat1())) {
//                    //实物形态
//                    amsBatch.setArcFormat("20");
//                } else {
//                    if (amsBatch.getArcFormat1().equals("1")) {
//                        //电子形态
//                        amsBatch.setArcFormat("10");
//                    } else {
//                        //实物形态
//                        amsBatch.setArcFormat("20");
//                    }
//                }

                //归档部门
                amsBatch.setFilingDepa(loginUser.getDept().getDeptName());
                //归档部门编号
                amsBatch.setFilingDepaCode(loginUser.getDept().getDeptId().toString());
                //归档日期
                amsBatch.setFilingTime(new Date());

                //上传文件
                String filePath = amsBatch.getBatchFileUrl();
                if (!"".equals(filePath)) {
                    saveFileAndImage4Batch(amsBatch.getBatchNo(), filePath);
                }

                AmsBatch amsBatch1 = new AmsBatch();
                BeanUtils.copyProperties(amsBatch, amsBatch1);

                //新增著录数据
                insertNum = amsBatchService.insertAmsBatch(amsBatch1);

                //入库导入的著录数据
//                num = storeEntityArchives(amsBatch1);
            }

        } catch (Exception e) {
            return AjaxResult.error("导入失败");
        }
        return AjaxResult.success("导入成功", insertNum);
    }

    /**
     * File流和MultipartFile流转换
     *
     * @param file
     * @return
     */
    public static MultipartFile getMulFileByFile(File file) {
        FileItem fileItem = createFileItem(file.getPath(), file.getName());
        MultipartFile mfile = new CommonsMultipartFile(fileItem);
        return mfile;
    }

    public static FileItem createFileItem(String filePath, String fileName) {
        String fieldName = "file";
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem(fieldName, "text/plain", false, fileName);
        File newfile = new File(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try (FileInputStream fis = new FileInputStream(newfile)) {
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return item;
    }

    /**
     * 上传文件/图片
     *
     * @param batchId
     * @param filePath
     */
    public void saveFileAndImage4Batch(String batchId, String filePath) {
        // 上传文件路径
        File f = new File(filePath);
        MultipartFile mFile = getMulFileByFile(f);

        try {
            //获取著录信息
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = new ArrayList<>();//multipartRequest.getFiles("file");
            //加入上传文件
            files.add(mFile);
            MultipartFile[] fileArray = files.toArray(new MultipartFile[files.size()]);
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            log.info("---------------------准备数上传文件参数-------------------");
            files.stream().forEach(file -> {
                //组装发送报文
                jsonObject.put("tranCode", Constants.CMS_FILES_UPLOAD);
                jsonObject.put("authCode", SysConfigInitParamsUtils.getConfig(Constants.CMS_UPLOAD_SYSCODE));
                jsonObject.put("sysCode", Constants.CMS_UPLOAD_SYSCODE);
                JSONObject object = new JSONObject();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                object.put("operationCode", df.format(System.currentTimeMillis()));
                object.put("fileName", file.getOriginalFilename());
                object.put("branchName", ShiroUtils.getSysUser().getDeptId());
                object.put("creater", ShiroUtils.getSysUser().getUserName());
                object.put("createUser", ShiroUtils.getSysUser().getUserName());
                object.put("batchId", batchId);
                object.put("orderNum", 1);
                try {
                    object.put("Md5", DigestUtils.md5Hex(file.getInputStream()));
                } catch (IOException e) {
                    logger.error("获取md5值失败", e.getMessage());
                    throw new RuntimeException("获取md5值失败", e.fillInStackTrace());
                }
                jsonArray.add(object);
            });
            log.info("---------------------准备数上传文件参数完毕-------------------");
            // 组装参数
            jsonObject.put("fileList", jsonArray);

            //通过Feign上传内管 并获取返回报文
            log.info("---------------------调用feign开始-------------------");
            log.info("************jsonObject=" + jsonObject.toJSONString());
            log.info("************fileArray=" + Arrays.toString(fileArray));
            String json = fileUploadFeignClient.uploadFiles(jsonObject.toString(), fileArray);
            log.info("---------------------调用feign结束-------------------");

            // 解析返回报文
            Map<String, Object> retJson = packageReturnJson(json);
            log.info("retJson=" + retJson.toString());
            // 保存影像及文件信息,返回上传失败文件
            Map<String, Object> returnMap = saveFileAndImage(retJson, files, batchId);
            log.info("returnMap=" + returnMap.toString());
        } catch (Exception e) {
            logger.error("有文件上传失败", e.getMessage());
            throw new RuntimeException("有文件上传失败", e.fillInStackTrace());
        }
    }

    /**
     * 责任者用户真实性校验
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/queryRespOpeartor")
    @ResponseBody
    public AjaxResult queryRespOpeartor(SysUser sysUser) {
        SysUser user = sysUserService.selectUserByUserName(sysUser.getUserName());
        Integer num = 0;
        if (user != null) {
            num = 1;
        }
        return AjaxResult.success("查询结果", num);
    }
}
