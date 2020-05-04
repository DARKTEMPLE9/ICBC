package net.northking.iacmp.pms.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.northking.iacmp.cms.service.*;
import net.northking.iacmp.common.bean.domain.cms.*;
import net.northking.iacmp.constant.PmsConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.utils.NumConstants;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件操作处理
 *
 * @author yin.rui
 * @date 2019-09-10
 */
@Controller
@RequestMapping("/file/pms/pmsBatch")
public class PmsFileHandleController extends BaseController {

    @Autowired
    private ICmsFileService cmsFileService;
    @Autowired
    private ICmsImageService cmsImageService;
    @Autowired
    private ICmsBillService cmsBillService;
    @Autowired
    private IPmsBatchService pmsBatchService;
    @Autowired
    private ICmsSystemService cmsSystemService;

    /**
     * 获得上传文件所需要的参数
     *
     * @param files
     * @param nodeId
     * @return
     */
    @PostMapping("/getUploadParams")
    @ResponseBody
    /**
     * 组装上传的需要的参数并返回
     */
    public Map<String, Object> getUploadParams(@RequestParam("files") List<MultipartFile> files, String nodeId) {
        Map<String, Object> repMap = new HashMap<>(16);
        // 项目id
        String pmsbatchId = nodeId.split("_")[0];
        // 分类id
        String billId = nodeId.split("_")[2];
        CmsSystem cmsSystem = cmsSystemService.selectCmsSystemByCode(PmsConstants.PMO_SYSCODE);
        PmsBatch pmsBatch = pmsBatchService.selectPmsBatchById(Long.valueOf(pmsbatchId));
        CmsBill cmsBill = cmsBillService.selectCmsBillById(Long.valueOf(billId));
        //组装发送报文
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tranCode", "cms_3001");
        jsonObject.put("authCode", cmsSystem.getSysCode());
        jsonObject.put("sysCode", PmsConstants.PMO_SYSCODE);
        jsonObject.put("operationCode", pmsBatch.getOperationCode());
        JSONArray jsonArray = new JSONArray();
        files.stream().forEach(file -> {
            JSONObject object = new JSONObject();
            object.put("projectId", pmsBatch.getOperationCode());
            object.put("creater", ShiroUtils.getLoginName());
            object.put("createUser", ShiroUtils.getLoginName());
            object.put("operationCode", pmsBatch.getOperationCode());
            try {
                object.put("Md5", DigestUtils.md5Hex(file.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException("获取md5值失败", e.fillInStackTrace());
            }
            String pathName = file.getOriginalFilename();
            String fileName = "";
            if (pathName.contains("/")) {
                fileName = pathName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
            } else if (fileName.contains("\\")) {
                fileName = pathName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
            }
            object.put("name", fileName);
            object.put("billId", billId);
            object.put("billCode", cmsBill.getBillCode());
            object.put("batchId", pmsBatch.getBatchId());
            object.put("keyword", cmsBill.getBillCode());
            object.put("deptName", pmsBatch.getDeptName());
            jsonArray.add(object);
        });
        jsonObject.put("fileList", jsonArray);
        repMap.put("jsonParam", jsonObject);
        return repMap;
    }

    /**
     * 转化非PDF文件为PDF并在线预览，如果没有文件则需要下载
     *
     * @return
     */
    @RequestMapping("/toPdfFile")
    @ResponseBody
    public void toPdfFile(HttpServletResponse response, String fileId) {
//        CmsFile cmsFile = cmsFileService.selectCmsFileById(Long.valueOf(fileId));
//        FileConversionUtils.fileToPdf(response, cmsFile.getFilePath(), cmsFile.getFileType(),
//                cmsFile.getRandomName(), cmsFile.getHadoopType());
    }

    /**
     * 逻辑批量删除文件或影像
     *
     * @param fileImageIds
     * @param nodeId
     * @return
     */
    @RequestMapping("/fileRemove")
    @ResponseBody
    public AjaxResult fileRemove(String fileImageIds, String nodeId) {
        Long[] ids = Convert.toLongArray(fileImageIds);
        // 文件的名称集合
        List<String> nameList = new ArrayList<>();
        // 批次id
        Long batchId = null;
        for (Long id : ids) {
            CmsFile file = cmsFileService.selectCmsFileByFileId(id);
            if (file != null) {
                nameList.add(file.getFileName());
                batchId = batchId == null ? file.getBatchId() : batchId;
            } else {
                CmsImage image = cmsImageService.selectCmsImageByImageId(id);
                nameList.add(image.getImageName());
                batchId = batchId == null ? image.getBatchId() : batchId;
            }
        }
        // 通过批次ID，分类ID，名称集合删除对应的文件或影像
        int j = cmsFileService.deleteCmsFileByCondition(batchId, Integer.valueOf(nodeId.split("_")[2]), nameList);
        int k = cmsImageService.deleteCmsImageByCondition(batchId, Integer.valueOf(nodeId.split("_")[2]), nameList);
        if ((j + k) < 1) {
            return AjaxResult.error("操作失败");
        }
        return AjaxResult.success("操作成功");
    }


    /**
     * 项目经理指定分类的监控数量
     *
     * @param pmsBatchId 项目ID
     * @param billId     分类ID
     * @return
     */
    @RequestMapping("/updateMonitorNum")
    @ResponseBody
    public AjaxResult updateMonitorNum(Long pmsBatchId, Long billId, Integer onFileNum) {
        Map<String, Integer> numMap = pmsBatchService.selectBillMonitorNum(pmsBatchId, billId);
        int count = numMap != null ? numMap.get("fileNum") : NumConstants.NUM_0;
        if (count > 0) {
            pmsBatchService.updateBillMonitorNum(pmsBatchId, billId, onFileNum);
        } else {
            pmsBatchService.insertBillMonitorNum(pmsBatchId, billId, onFileNum);
        }
        return success(onFileNum);
    }
}
