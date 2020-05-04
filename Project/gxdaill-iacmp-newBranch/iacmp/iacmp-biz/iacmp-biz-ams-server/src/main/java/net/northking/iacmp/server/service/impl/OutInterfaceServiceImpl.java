package net.northking.iacmp.server.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBoxVO;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.server.service.*;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysConfigService;
import net.northking.iacmp.system.service.ISysDeptService;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.Base64Util;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.bean.BeanUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.OperationsException;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 综合档案著录接口
 *
 * @author zhy
 * @date 2019-09-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class OutInterfaceServiceImpl implements IOutInterfaceService {

    private static Logger log = LoggerFactory.getLogger(OutInterfaceServiceImpl.class);
    private static final String REPOSITORY = System.getProperty(
            "java.io.tmpdir", File.separator + "tmp" + File.separator
                    + "upload-repository");
    @Autowired
    private IImBillService imBillService;
    @Autowired
    private IImFileService imFileService;
    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private IAmsBillService amsBillService;
    @Autowired
    private IImAccessSystemService imAccessSystemService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private IImImageService imImageService;
    @Autowired
    private IImBatchService iImBatchService;
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private IAmsBoxService amsBoxService;
    @Autowired
    private IAmsCabinetService amsCabinetService;
    @Autowired
    private IAmsDepotService amsDepotService;

    @Override
    public Map<String, String> saveFiles(JSONObject json) {
        try {
            //参数校验
            if (!validateParam(json)) {
                throw new NullPointerException("报文必填项为空!");
            }
            Map<String, String> reportInfo = packReportInfo(json);
            List<Map<String, String>> returnFileInfo = new LinkedList<>();
            //根据档案编号查询档案
            String arcNo = "";
            if (json.containsKey("arcNo") && !StringUtils.isEmpty(json.getString("arcNo"))) {
                arcNo = json.getString("arcNo");
            }
            AmsArchivesVO amsArchives = new AmsArchivesVO();
            amsArchives.setArcNo(arcNo);
            List<AmsArchives> list = amsArchivesService.selectAmsArchivesList(amsArchives);
            if (list.size() > 0) {
                return sendMessage("00000000000", "档案已存在!", returnFileInfo);
            }
            //保存报文信息
            saveAmsBatch(reportInfo, json);
            return sendMessage("00000000000", "交易成功!", returnFileInfo);

        } catch (Exception e) {
            log.error(e.getMessage());
            return sendMessage(e.getMessage(), "交易失败!", null);
        }
    }

    /**
     * 文件列表合规校验
     *
     * @param fileList
     */
    private void validateImgList(JSONArray fileList) {
        //获取文件阈值
        int maxFileSize = Integer.parseInt(SysConfigInitParamsUtils.getConfig(Constants.MAX_BASE64_FILE_SIZE)) * (1024 * 1024);
        //校验上传文件大小是否超限,如果存在有超限的文件则直接返回错误提示
        for (int i = 0; i < fileList.size(); i++) {
            String fileCode = fileList.getJSONObject(i).getString("base64");
            int fileSize = Base64Util.getFileSizeByBase64(fileCode);
            if (StringUtils.isEmpty(fileCode) || "null".equalsIgnoreCase(fileCode)) {
                throw new NullPointerException("报文必填项为空!");
            }
            if (fileSize > maxFileSize) {
                throw new NullPointerException("文件大小超限!");
            }
            String fileType = fileList.getJSONObject(i).getString("fileType");
            if (StringUtils.isEmpty(fileType) || "null".equalsIgnoreCase(fileType)) {
                throw new NullPointerException("报文必填项为空!");
            }
        }
    }

    /**
     * 元数据报文校验
     *
     * @param dataList
     */
    private void validateDatatList(JSONObject dataList) {
        //档案类型校验
        String arcBillType = dataList.getString("arcBill");
        if (StringUtils.isEmpty(arcBillType) || "null".equalsIgnoreCase(arcBillType)) {
            throw new NullPointerException("报文必填项为空!");
        }
    }

    /**
     * 保存影像图片
     *
     * @param filename
     * @param start
     * @param batch
     * @param transfilepath
     * @param imbill
     * @param createuser
     * @param templateNo
     * @return
     * @throws OperationsException
     */
    @Override
    public ImImage saveImage(String filename, String start, AmsBatch batch, String transfilepath, ImBill imbill, String remark, String createuser) throws OperationsException {
        ImImage iimage = new ImImage();
        try {
            iimage.setBatchId(batch.getBatchNo());
            iimage.setImageName(filename);
            iimage.setStatus("0");
            iimage.setCreateTime(new Date());
            iimage.setImageSize(BigDecimal.valueOf(Long.valueOf(start)));
            iimage.setImageBillId(imbill.getId());
            iimage.setBillName(imbill.getName());
            iimage.setRemark(remark);
            iimage.setImagePath(transfilepath);
            iimage.setCreateUser(createuser);
            iimage.setImageSource("附件上传");
            iimage.setCreateUserName(createuser);
            ImAccessSystem imAccessSystem = new ImAccessSystem();
            iimage.setSystemFlag(imAccessSystemService.selectImAccessSystemList(imAccessSystem).get(0).getCode());
            iimage.setSysFlagInt(imAccessSystemService.selectImAccessSystemList(imAccessSystem).get(0).getSysFlagInt());
            String iimageId = UUID.randomUUID().toString().replaceAll("-", "");
            iimage.setId(iimageId);
            imImageService.insertImImage(iimage);
            return iimage;
        } catch (RuntimeException e) {
            log.error("保存影像信息失败", e.fillInStackTrace());
            return iimage;
        }

    }

    /**
     * 保存文件信息方法
     *
     * @param filename         文件名
     * @param start            文件大小
     * @param ibatch           批次信息
     * @param imbill           影像分类信息
     * @param transfilepath    文件URL
     * @param bz               备注信息
     * @param CREATE_USER      创建用户ID
     * @param CREATE_USER_NAME 创建用户名称
     * @param createTime       创建时间
     * @param subSysId
     * @return
     * @throws OperationsException
     */
    @Override
    public ImFile saveFile(String filename, int start, AmsBatch batch,
                           ImBill imbill, String remark, String transfilepath, String createUser) throws OperationsException {
        ImFile ifile = new ImFile();
        try {
            ifile.setBatchId(batch.getBatchNo());
            ifile.setFileName(filename);
            ifile.setStatus("0");
            ifile.setFileSize(BigDecimal.valueOf(start));
            ifile.setFileBillId(imbill.getId());
            ifile.setBillName(imbill.getName());
            ifile.setRemark(remark);
            ifile.setFilePath(transfilepath);
            ifile.setCreateUser(createUser);
            ifile.setCreateUserName(createUser);
            ifile.setFileSource("后台批量上传");
            ifile.setCreateTime(new Date());
            ImAccessSystem imAccessSystem = new ImAccessSystem();
            ifile.setSystemFlag(imAccessSystemService.selectImAccessSystemList(imAccessSystem).get(0).getCode());
            ifile.setSysFlagInt(imAccessSystemService.selectImAccessSystemList(imAccessSystem).get(0).getSysFlagInt());
            ifile.setFileType(filename.substring(filename.lastIndexOf('.') + 1));

            String ifileId = UUID.randomUUID().toString().replaceAll("-", "");
            ifile.setId(ifileId);
            imFileService.insertImFile(ifile);

            return ifile;
        } catch (RuntimeException e) {
            log.error("保存文件信息失败", e.fillInStackTrace());
            return ifile;
        }
    }

    /**
     * 组装报文
     *
     * @param json
     * @return
     */
    private Map<String, String> packReportInfo(JSONObject json) {
        Map<String, String> reportInfo = new HashMap<>();

        if (json.containsKey("deptNo") && !StringUtils.isEmpty(json.getString("deptNo"))) {
            reportInfo.put("deptNo", json.getString("deptNo"));
        }
        if (json.containsKey("arcBillCode") && !StringUtils.isEmpty(json.getString("arcBillCode"))) {
            reportInfo.put("arcBillCode", json.getString("arcBillCode"));
        }
        if (json.containsKey("operaId") && !StringUtils.isEmpty(json.getString("operaId"))) {
            reportInfo.put("operaId", json.getString("operaId"));
        }
        return reportInfo;
    }

    /**
     * 组装列表内报文信息
     *
     * @param jsonObj
     * @return
     */
    private Map<String, String> packSubReportInfo(JSONObject jsonObj) {
        Map<String, String> subReportInfo = new HashMap<>();
        subReportInfo.put("fileType", jsonObj.getString("fileType"));
        subReportInfo.put("fileName", UUID.randomUUID().toString().replaceAll("-", "") + "." + subReportInfo.get("fileType"));
        if (jsonObj.containsKey("fileName")) {
            String fileName = jsonObj.getString("fileName");
            if (!StringUtils.isEmpty(fileName) && !fileName.contains(".")) {
                subReportInfo.put("fileName", fileName + "." + subReportInfo.get("fileType"));
            } else {
                subReportInfo.put("fileName", fileName);
            }
        }
        if (jsonObj.containsKey("remark")) {
            subReportInfo.put("remark", jsonObj.getString("remark"));
        }
        subReportInfo.put("base64", jsonObj.getString("base64"));
        return subReportInfo;
    }

    /**
     * 接口参数校验
     *
     * @param json
     * @return
     */
    private boolean validateParam(JSONObject json) {
        //接口参数校验
        if (StringUtils.isEmpty(json.getString("deptNo")) || "null".equalsIgnoreCase(json.getString("deptNo"))) {
            return false;
        } else if (StringUtils.isEmpty(json.getString("arcBillCode")) || "null".equalsIgnoreCase(json.getString("arcBillCode"))) {
            return false;
        } else if (StringUtils.isEmpty(json.getString("arcBillDeptCode")) || "null".equalsIgnoreCase(json.getString("arcBillDeptCode"))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 保存报文信息
     *
     * @param reportInfo
     * @param json
     * @throws OperationsException
     */
    @SuppressWarnings("unchecked")
    private void saveAmsBatch(Map<String, String> reportInfo, JSONObject json) throws Exception {
        AmsBatch amsBatch = new AmsBatch();
        try {
            //清空base64中的文件内容
            JSONObject tempObj = new JSONObject();
            for (Iterator<String> keys = json.keys(); keys.hasNext(); ) {
                String key = keys.next();
                tempObj.put(key, json.get(key));
            }

            //保存报文信息
            amsBatch.setId(UUID.randomUUID().toString().replace("-", ""));
            String nowTime = new SimpleDateFormat(Constants.DATE_TYPE_EIGHT).format(new Date());
            //注入批次号
            amsBatch.setBatchNo(nowTime + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16));
            //档案类型编号
            String arcBillCode = tempObj.getString("arcBillCode");
            AmsBill amsBill = new AmsBill();
            amsBill.setCode(arcBillCode);
            amsBatch.setArcBillCode(arcBillCode);
            List<AmsBill> billList = amsBillService.selectAmsBillList(amsBill);
            if (billList.size() > 0) {
                amsBatch.setArcBill(billList.get(0).getName());
            } else {
                throw new OperationsException("档案类型为空!");
            }
            //档案二级类目编号
            String arcBillDeptCode = tempObj.getString("arcBillDeptCode");
            AmsBill amsBillDept = new AmsBill();
            amsBillDept.setCode(arcBillDeptCode);
            amsBatch.setArcBillDeptCode(arcBillDeptCode);
            List<AmsBill> billDeptList = amsBillService.selectAmsBillList(amsBillDept);
            if (billDeptList.size() > 0) {
                amsBatch.setArcBillDept(billDeptList.get(0).getName());
            } else {
                throw new OperationsException("档案二级类型为空!");
            }
            //部门代码
            String orgCode = tempObj.getString("deptNo");
            amsBatch.setOrgCode(orgCode);
            //部门名称
            if (orgCode != null && !"".equals(orgCode)) {
                amsBatch.setOrgName(sysDeptService.selectDeptById(Long.valueOf(orgCode)).getDeptName());
            }
            //档案编号
            amsBatch.setArcNo(tempObj.getString("arcNo"));
            //档案名称（档案名称）
            amsBatch.setArcName(tempObj.getString("arcName"));
            //文件编号
            amsBatch.setArcCode(tempObj.getString("arcCode"));
            //形成日期
            amsBatch.setArcCreTime(new Date());
            //责任者
            amsBatch.setRespOpName(tempObj.getString("respOpName"));
            //页数
            amsBatch.setArcPageNum(Integer.valueOf(tempObj.getString("arcPageNum")));
            //份数
            amsBatch.setArcNum(Integer.valueOf(tempObj.getString("arcNum")));
            //来源方式
            amsBatch.setOriginMode(tempObj.getString("originMode"));
            //保管期限
            amsBatch.setValPeriod(tempObj.getString("valPeriod"));
            //密级
            amsBatch.setArcLevel(tempObj.getString("arcLevel"));
            //是否涉及费用
            amsBatch.setExpenseInvolve(tempObj.getString("expenseInvolve"));
            //状态: 1 已著录
            amsBatch.setStatus("1");
            //档案形态: 10 电子档案
            amsBatch.setArcFormat("10");
            //创建人
            if (tempObj.has("createUser")) {
                String uid = tempObj.getString("createUser");
                amsBatch.setCrtName(uid);
            }
            //创建时间
            amsBatch.setCrtTime(new Timestamp(new Date().getTime()));
            //是否移交行档室
            if (tempObj.has("deptOrBank")) {
                if ("1".equals(tempObj.getString("deptOrBank"))) {
                    //行档案
                    amsBatch.setArcHasMoveBank("1");
                } else {
                    //部门档案
                    amsBatch.setArcHasMoveBank("0");
                }
            } else {
                amsBatch.setArcHasMoveBank("0");
            }
            //全局流水号
            if (null != tempObj.getString("operationCode") && !"".equals(tempObj.getString("operationCode"))) {
                amsBatch.setBusiNo(tempObj.getString("operationCode"));
            }

            amsBatchService.insertAmsBatch(amsBatch);
            //档案入盒入库
            storeArchives(tempObj, amsBatch);
        } catch (Exception e) {
            log.error("保存报文信息失败!", e.fillInStackTrace());
        }
    }

    /**
     * 接口返回信息方法
     *
     * @param tradeResult
     * @param tradeDesc
     * @param fileList
     * @return
     */
    private Map<String, String> sendMessage(String tradeResult, String tradeDesc, List<Map<String, String>> fileList) {
        Map<String, String> resultInfo = new HashMap<>();
        resultInfo.put("tradeResult", tradeResult);
        resultInfo.put("tradeDesc", tradeDesc);
        if (fileList != null && !fileList.isEmpty()) {
            JSONArray jsonArr = new JSONArray();
            for (Map<String, String> map : fileList) {
                JSONObject obj = new JSONObject();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    obj.put(entry.getKey(), entry.getValue());
                }
                jsonArr.add(obj);
            }
            resultInfo.put("ret", jsonArr.toString());
        } else {
            resultInfo.put("ret", "[]");
        }
        return resultInfo;
    }

    /**
     * 新建档案盒
     *
     * @param amsBatch
     * @return
     */
    private AmsBox createBox(JSONObject jsonObj, AmsBatch amsBatch) {
        //档案盒Id
        String boxId = UUID.randomUUID().toString().replace("-", "");
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        AmsBox amsBox = new AmsBox();
        amsBox.setId(boxId);
        amsBox.setCode("BX_" + jsonObj.getString("sysCode"));
        amsBox.setName("BX_" + jsonObj.getString("sysCode") + "虚拟档案盒");
        amsBox.setOrgCode(amsBatch.getOrgCode());
        amsBox.setOrgName(amsBatch.getOrgName());
        amsBox.setBoxYear(sdf.format(amsBatch.getCrtTime()));
        amsBox.setArcType(amsBatch.getArcBill());
        amsBox.setArcTypeCode(amsBatch.getArcBillCode());
        amsBox.setChildType(amsBatch.getArcBillDept());
        amsBox.setChildTypeCode(amsBatch.getArcBillDeptCode());
        amsBox.setStatus(Constants.NOTMAX_BOX);
        amsBox.setValPeriod(amsBatch.getValPeriod());
        if (amsBatch.getArcHasMoveBank().equals("1")) {
            amsBox.setDepType("20");
        } else if (amsBatch.getArcHasMoveBank().equals("0")) {
            amsBox.setDepType("10");
        }
        int i = amsBoxService.insertAmsBox(amsBox);

        AmsBox box = null;
        if (i > 0) {
            box = amsBoxService.selectAmsBoxById(amsBox.getId());
        }
        return box;
    }

    /**
     * 新建库柜
     *
     * @param sysUser
     * @param depot
     */
    private AmsCabinet createCabinet(JSONObject jsonObj, SysUser sysUser, AmsDepot depot) throws NullPointerException {
        AmsCabinet cabinet = new AmsCabinet();
        cabinet.setId(UUID.randomUUID().toString().replace("-", ""));
        cabinet.setCode("BX_" + jsonObj.getString("sysCode") + "VirCabinet_" + depot.getOrgNo());
        cabinet.setName("BX_" + jsonObj.getString("sysCode") + "虚拟档案柜");
        cabinet.setStatus("0");
        cabinet.setDepId(depot.getId());
        cabinet.setDepCode(depot.getCode());
        cabinet.setDepName(depot.getName());
        cabinet.setDepStatus(depot.getStatus());
        cabinet.setDepType(depot.getDepotType());
        if (null != sysUser) {
            cabinet.setDutyMan(sysUser.getUserName());
        } else {
            cabinet.setDutyMan("中关村");
        }
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
    private AmsDepot createDepot(JSONObject jsonObj, SysUser sysUser, AmsBatch amsBatch) throws NullPointerException {
        AmsDepot depot = new AmsDepot();
        depot.setId(UUID.randomUUID().toString().replace("-", ""));
        depot.setName("BX_" + jsonObj.getString("sysCode") + "虚拟档案库");
        depot.setCode("BX_" + jsonObj.getString("sysCode") + "VirDepot_" + amsBatch.getOrgCode());
        depot.setAllNum(99);
        depot.setStatus("0");
        //库房类型
        if (amsBatch.getArcHasMoveBank().equals("1")) {
            depot.setDepotType("20");
        } else if (amsBatch.getArcHasMoveBank().equals("0")) {
            depot.setDepotType("10");
        }
        if (null != sysUser) {
            depot.setAdmin(sysUser.getUserName());
            depot.setOrgNo(amsBatch.getOrgCode());
            SysDept dept = sysDeptService.selectDeptById(Long.valueOf(Long.valueOf(amsBatch.getOrgCode())));
            depot.setOrgName(dept.getDeptName());
        } else {
            depot.setAdmin("中关村");
            depot.setOrgNo("1000");
            depot.setOrgName("消费金融事业部");
        }

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
     * 更新档案盒
     *
     * @param amsBox
     * @param cabinetList
     */
    private int updateBox(AmsBox amsBox, AmsCabinet amsCabinet) throws NullPointerException {
        int num = 0;
        amsBox.setDepId(amsCabinet.getDepId());
        amsBox.setDepName(amsCabinet.getDepName());
        amsBox.setCabId(amsCabinet.getId());
        amsBox.setCabName(amsCabinet.getName());
        // 入库时间
        amsBox.setIncabTime(new Timestamp(System.currentTimeMillis()));
        if ("10".equals(amsBox.getStatus())) {
            amsBox.setStatus(Constants.NOT_FULL_INTOCAB);
        }
        num = amsBoxService.updateAmsBox(amsBox);
        return num;
    }

    /**
     * 档案入库
     *
     * @param jsonObj
     * @param amsBatch
     * @return
     * @throws Exception
     */
    private int storeArchives(JSONObject jsonObj, AmsBatch amsBatch) throws Exception {
        SysUser user = new SysUser();
        user.setUserId(1L);
        List<SysUser> userList = sysUserService.selectUserList(user);
        SysUser sysUser = null;
        if (userList.size() > 0) {
            sysUser = userList.get(0);
        }
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
            //所属部门名称
            String deptName = jsonObj.getString("deptName");
            SysDept deptObj = new SysDept();
            deptObj.setDeptId(Long.valueOf(deptName));
            List<SysDept> sysDepts = sysDeptService.selectDeptList(deptObj);

            String boxNum = getRandom();
            amsArchives.setId(UUID.randomUUID().toString().replace("-", ""));
            amsArchives.setManaDepNo(String.valueOf(sysDepts.get(0).getDeptId()));
            amsArchives.setManaDepName(sysDepts.get(0).getDeptName());
            amsArchives.setBatchNo(amsBatch.getBatchNo());
            amsArchives.setViewPath(amsBatch.getViewPath());
            String arcNo = jsonObj.getString("arcNo");
            amsArchives.setArcNo(arcNo);
            amsBatch.setArcNo(arcNo);
            //档案盒编码
            String boxCode = "BX_" + jsonObj.getString("sysCode");
            AmsBoxVO amsBoxVo = new AmsBoxVO();
            amsBoxVo.setCode(boxCode);
            //查询档案盒
            List<AmsBox> listBox = amsBoxService.selectAmsBoxList(amsBoxVo);
            //档案盒Id
            String boxId = "";
            //档案盒名称
            String boxName = "";
            //库房Id
            String depotId = "";
            //档案盒对象
            AmsBox amsBox = null;
            if (listBox.size() > 0) {
                amsBox = listBox.get(0);
                boxId = listBox.get(0).getId();
                boxName = listBox.get(0).getName();
                depotId = listBox.get(0).getDepId();
            } else {
                //新建档案盒
                amsBox = createBox(jsonObj, amsBatch);
                if (amsBox == null) {
                    throw new NullPointerException("新建档案盒失败");
                }
                boxId = amsBox.getId();
                boxName = amsBox.getName();
            }
            amsArchives.setBoxCode(boxId);
            amsArchives.setBoxName(boxName);

            // 查询库房
            AmsDepot dp = new AmsDepot();
            //判断库房Id是否为空
            if (!"".equals(depotId)) {
                dp = amsDepotService.selectAmsDepotById(depotId);
            } else {
                //设置查询参数'库房编码'
                String dpCode = "BX_" + jsonObj.getString("sysCode") + "VirDepot_" + amsBatch.getOrgCode();
                dp.setCode(dpCode);
            }
            List<AmsDepot> listDepot = amsDepotService.selectAmsDepotList(dp);
            //判断是否已有此档案库
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
                    amsCab = createCabinet(jsonObj, sysUser, depot);
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

                //更新档案盒
                updateBox(amsBox, amsCab);
            } else {
                //新建库房
                AmsDepot amsDepot = createDepot(jsonObj, sysUser, amsBatch);
                if (null == amsDepot) {
                    throw new NullPointerException("新建库房失败!");
                }
                //新建库柜
                AmsCabinet amsCab = createCabinet(jsonObj, sysUser, amsDepot);
                if (amsCab == null) {
                    throw new NullPointerException("新建库柜失败！");
                }
                //设置档案的库房/库柜
                amsArchives.setDepotId(amsCab.getDepId());
                amsArchives.setDepotNo(amsCab.getDepCode());
                amsArchives.setDepotName(amsCab.getName());
                amsArchives.setCabintId(amsCab.getId());
                amsArchives.setCabintNo(amsCab.getCode());
                amsArchives.setCabintName(amsCab.getName());
                amsArchives.setDepotType(amsCab.getDepType());
                //更新档案盒
                updateBox(amsBox, amsCab);
            }

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
            if (sysUser != null) {
                amsArchives.setAdminNo(String.valueOf(sysUser.getUserId()));
                amsArchives.setAdminName(sysUser.getLoginName());
            } else {
                amsArchives.setAdminNo("1");
                amsArchives.setAdminName("中关村");
            }
            amsArchives.setFilingDepa(amsBatch.getFilingDepaCode());
            amsArchives.setFilingDepaName(amsBatch.getFilingDepa());
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
            amsBatch.setBoxId(boxId);
            amsBatch.setFilingDepa(sysDepts.get(0).getDeptName());
            amsBatch.setFilingDepaCode(String.valueOf(sysDepts.get(0).getDeptId()));
            amsBatch.setFilingTime(new Timestamp(System.currentTimeMillis()));
            if (sysUser != null) {
                amsBatch.setBoxOpCode(String.valueOf(sysUser.getUserId()));
                amsBatch.setBoxOpName(sysUser.getUserName());
            } else {
                //amsBatch创建人
                amsBatch.setBoxOpCode(amsBatch.getCrtNo());
                amsBatch.setBoxOpName(amsBatch.getCrtName());
            }
            amsBatch.setBoxOrgCode(amsBatch.getOrgCode());
            amsBatch.setBoxOrgName(amsBatch.getOrgName());
            amsBatch.setStatus(Constants.ALREADY_PUT_STORAGE); //ALREADY_PUT_STORAGE
            int up2 = amsBatchService.updateAmsBatch(amsBatch);

            //数据更新成功
            if (up1 > 0 && up2 > 0) {
                num = 1;
            } else {
                throw new Exception("接收实物档案错误");
            }
        }
        return num;
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
}
