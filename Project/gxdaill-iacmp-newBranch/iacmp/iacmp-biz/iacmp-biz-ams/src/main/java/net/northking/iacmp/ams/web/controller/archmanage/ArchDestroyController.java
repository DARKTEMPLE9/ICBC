package net.northking.iacmp.ams.web.controller.archmanage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.common.bean.dto.ams.AmsArchivesDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBoxVO;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.framework.util.UploadUtil;
import net.northking.iacmp.server.service.*;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * 档案销毁
 *
 * @author weizhe.fan
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/archManage/archDestroy")
public class ArchDestroyController extends BaseController {

    private String prefix = "archManage/archDestroy";
    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private IAmsBoxService amsBoxService;
    @Autowired
    private IAmsBatchService batchService;
    @Autowired
    private IAmsArcRegService amsArcRegService;
    @Autowired
    private IAmsDestroyService amsDestroyService;
    @Autowired
    private IImImageService imageService;
    @Autowired
    private IImFileService fileService;
    @Autowired
    private IImFileService imFileService;
    /**
     * 服务器Ip
     */
    private String serverIp;
    /**
     * 应用服务器文件地址
     */
    private String serverPath;

    @RequiresPermissions("archManage:archDestroy:view")
    @GetMapping()
    public String amsBox() {
        return prefix + "/archDestroy";
    }

    /**
     * 查询全部档案销毁列表 待销毁 peroid!=1 status =9  已销毁 peroid =1
     */
    @RequiresPermissions("archManage:archDestroy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsArchivesVO amsArchives) {

        List<String> deptList = new ArrayList<>();//当前用户管理的全部部门
        //获得当前登陆人权限
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roleList = sysUser.getRoles();
        List<Long> roles = new ArrayList();
        for (SysRole o : roleList) {
            roles.add(o.getRoleId());
        }
        roles.remove(23L);
        //获取用户最高权限角色
        String roleId = Collections.max(roles).toString();
        if ("4".equals(roleId)) {//行档案管理员
            //有权销毁移交行的档案
            amsArchives.setHasMoveBank("1");
            if (amsArchives.getOpDepNo() != null && !"".equals(amsArchives.getOpDepNo())) {
                deptList.add(amsArchives.getOpDepNo());
            }
        }
        if ("3".equals(roleId)) {//部门档案管理员
            //有权销毁本部门档案
            amsArchives.setHasMoveBank("0");
//			String deptId = sysUser.getDeptId().toString();
//			amsArchives.setOpDepNo(deptId);
            if (amsArchives.getOpDepNo() != null && !"".equals(amsArchives.getOpDepNo())) {
                deptList.add(amsArchives.getOpDepNo());
            } else {
                //查询当前用户管理全部部门
                deptList.add(sysUser.getDeptId().toString());
                String auxiliaryDept = sysUser.getAuxiliaryDept();
                if (auxiliaryDept != null && !"".equals(auxiliaryDept)) {
                    String[] auxiliaryDeptArr = auxiliaryDept.split(",");
                    for (int i = 0; i < auxiliaryDeptArr.length; i++) {
                        deptList.add(auxiliaryDeptArr[i]);
                    }
                }
            }
        }
        startPage();
        List<AmsArchivesDTO> list = null;
        list = amsArchivesService.selectArchDestroyList(amsArchives, deptList);
        return getDataTable(list);
    }

    /**
     * 销毁清册打印模版列表查询
     *
     * @return
     */
    @PostMapping("/printList")
    @ResponseBody
    public String queryTabs(AmsArchivesVO archivesVo) {
        List list = new ArrayList();
        for (String tid : archivesVo.getTids()) {
            AmsArchivesVO amsArchives = new AmsArchivesVO();
            amsArchives.setId(tid);
            List<AmsArchives> archivesList = amsArchivesService.selectAmsArchivesList(amsArchives);
            if (!archivesList.isEmpty()) {
                list.add(archivesList.get(0));
            }
        }
        ObjectMapper om = new ObjectMapper();
        String json = "";
        try {
            json = om.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            logger.error("Json数据格式化失败", e.getMessage());
        }
        return json;
    }

    /**
     * @Author: weizhe.fan
     * @Description:保留
     * @CreateDate: 16:30.2019/8/6
     */
    @RequiresPermissions("archManage:archDestroy:stay")
    @PostMapping("/stay")
    @ResponseBody
    public AjaxResult persist(String ids, String valPeriod) {
        String[] split = ids.split(",");
        int num = 0;
        for (int i = 0; i < split.length; i++) {
            AmsArchives amsArchives = new AmsArchives();
            amsArchives.setId(split[i]);
            amsArchives.setStatus("5");
            amsArchives.setPersistTime(new java.sql.Timestamp(System.currentTimeMillis()));
            amsArchives.setPersistPeriod(valPeriod);
            amsArchives.setPeriod("1");
            amsArchives.setFilingTime(new java.sql.Timestamp(System.currentTimeMillis()));
            amsArchives.setValPeriod(valPeriod);
            amsArchivesService.updateAmsArchives(amsArchives);
            AmsBatch amsBatch = new AmsBatch();
            amsBatch.setBatchNo(amsArchives.getBatchNo());
            List<AmsBatch> batchList = batchService.selectAmsBatchList(amsBatch);
            AmsBatch batch = batchList.get(0);
            batch.setStatus("5");

            num = batchService.updateAmsBatch(batch);
        }
        return toAjax(num);
    }

    /**
     * 重新选择保留期限页面
     */
    @GetMapping("/toStay")
    public String toStay() {
        return prefix + "/toStay";
    }


    /**
     * @Author: weizhe.fan
     * @Description:档案销毁
     * @CreateDate: 16:39.2019/8/6
     */
    @RequiresPermissions("archManage:archDestroy:destroy")
    @PostMapping("/destroy")
    @ResponseBody
    public AjaxResult destroy(String ids, String destroyReason) {
        String[] idArr = ids.split(",");
        SysUser sysUser = ShiroUtils.getSysUser();
        Date date = new Date();
        String uuid;
        for (int i = 0; i < idArr.length; i++) {
            AmsDestroy amsDestroy = new AmsDestroy();
            AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(idArr[i]);
            uuid = UUID.randomUUID().toString().replaceAll("-", "");
            amsDestroy.setId(uuid);
            amsDestroy.setAmsArcId(idArr[i]);
            amsDestroy.setAmsArcName(amsArchives.getName());
            amsDestroy.setArchivesNum(amsArchives.getArcNum());
            amsDestroy.setOpOrgCode(sysUser.getDeptId().toString());
            amsDestroy.setOpOrgName(sysUser.getDept().getDeptName());
            amsDestroy.setOpUserCode(sysUser.getUserId().toString());
            amsDestroy.setOpUserName(sysUser.getUserName());
            amsDestroy.setDestroyReason(destroyReason);
            amsDestroy.setOpDate(date);
            amsDestroyService.insertAmsDestroy(amsDestroy);
        }
        //实体档案销毁是已废弃，电子档案销毁是已销毁
        int updateElec = amsArchivesService.updateAmsArchivesByIds(ids);
        int updateEntity = amsArchivesService.updateEntityAmsArchivesByIds(ids);
        int rows = updateElec + updateEntity;
        return toAjax(rows);

    }

    /**
     * 打印预览
     *
     * @return"
     */
    @RequiresPermissions("archManage:archDestroy:printLook")
    @GetMapping("/printLook")
    public String printLook(String ids, ModelMap mmap) {

        SysUser loginUser = ShiroUtils.getSysUser();

        List<AmsArchives> list = amsArchivesService.selectPrintDestoryList(ids);
        mmap.put("printList", list);
        mmap.put("loginUser", loginUser);
        return prefix + "/printLook";
    }

    /**
     * 导出档案销毁列表
     */
    @RequiresPermissions("archManage:archDestroy:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBoxVO amsBox) {
        List<AmsBox> list = amsBoxService.selectAmsBoxList(amsBox);
        ExcelUtil<AmsBox> util = new ExcelUtil<>(AmsBox.class);
        return util.exportExcel(list, "archDestroy");
    }

    /**
     * 新增档案销毁
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案销毁
     */
    @RequiresPermissions("archManage:archDestroy:add")
    @Log(title = "档案销毁", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBox amsBox) {
        return toAjax(amsBoxService.insertAmsBox(amsBox));
    }

    /**
     * 修改档案销毁
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBox amsBox = amsBoxService.selectAmsBoxById(id);
        mmap.put("amsBox", amsBox);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案销毁
     */
    @RequiresPermissions("archManage:archDestroy:edit")
    @Log(title = "档案销毁", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBox amsBox) {
        return toAjax(amsBoxService.updateAmsBox(amsBox));
    }

    /**
     * 删除档案销毁
     */
    @RequiresPermissions("archManage:archDestroy:remove")
    @Log(title = "档案销毁", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBoxService.deleteAmsBoxByIds(ids));
    }

    /**
     * 档案销毁 填写销毁原因
     */
    @GetMapping("/remark/{id}")
    public String remark(@PathVariable String id, ModelMap mmap) {
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(id);
        String status = amsArchives.getStatus();
        //是否可以上传附件  1：可以  0：不可以
        String isUpload = "0";
        //待废弃的实体档案销毁时可上传附件
        if ("9".equals(status)) {
            //10:电子档案  20：实体档案
            String arcType = amsArchives.getArcType();
            if ("20".equals(arcType)) {
                //实体档案
                isUpload = "1";
            } else {
                String carrier = amsArchives.getCarrier();
                if (!"03".equals(carrier)) {
                    //实体，电子并存
                    isUpload = "1";
                }
            }
        }
        mmap.put("id", id);
        mmap.put("isUpload", isUpload);
        return prefix + "/remark";
    }

    /**
     * 跳转上传页面
     *
     * @param arcId
     * @param permission
     * @param mmap
     * @return
     */
    @GetMapping("/toUpload/{arcId}/{permission}")
    public String toUpload(@PathVariable("arcId") String arcId, @PathVariable("permission") String permission, ModelMap mmap) {
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(arcId);
        mmap.put("batchId", amsArchives.getBatchId());
        mmap.put("permission", permission);
        // 用于上传完文件后，可能会刷新回显
        List<ImImage> imImageList = imageService.selectImImagesByBatchId(amsArchives.getBatchId());
        List<ImFile> imFileList = fileService.selectImFilesByBatchId(amsArchives.getBatchId());
        mmap.put("imageList", imImageList);
        mmap.put("fileList", imFileList);
        mmap.put("serverAddress", "http://" + getServerIp() + ":" + Constants.AMS_PORT);
        return prefix + "/sweepImage";
    }

    /**
     * 调转到显示部门树页面
     *
     * @return
     */
    @GetMapping("/deptTree")
    public String deptTree() {
        return "archManage/archDestroy/tree";
    }

    /**
     * 调转到显示档案类型树页面
     *
     * @return
     */
    @GetMapping("/arcBillTree")
    public String arcBillTree() {
        return "archManage/archDestroy/arcBillTree";
    }

    /**
     * 获取系统环境的ip
     *
     * @return
     * @author yinrui
     * @date 2019-10-31
     */
    private String getServerIp() {
        String system = System.getProperty("os.name").toLowerCase();
        if (StringUtils.isEmpty(serverIp)) {
            if (system.indexOf("windows") >= 0) {
                serverIp = SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_FILETRANSIP);
            } else {
                serverIp = SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSIP);
            }
        }
        return serverIp;
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
        logger.info("getServerPath()=" + getServerPath());
        boolean b = UploadUtil.downloadTrans(getServerPath(), fileName, path);
        if (!b) {
            return "从HDFS下载到服务器失败";
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/force-download");
        try (FileInputStream fis = new FileInputStream(new File(getServerPath(), fileName))) {
            response.setHeader("Content-disposition ", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
//			FileInputStream fis = new FileInputStream(new File(getServerPath(), fileName));
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] bytes = new byte[1024];
            int lenth = 0;
            while ((lenth = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, lenth);
                bos.flush();
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage(), e);
        }
        return "下载成功";
    }

    private String getServerPath() {
        String system = System.getProperty("os.name").toLowerCase();
        if (StringUtils.isEmpty(serverPath)) {
            if (system.indexOf("windows") >= 0) {
                serverPath = SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_SERVER_PATH);
            } else {
                serverPath = SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH);
            }
        }
        return serverPath;
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
}
