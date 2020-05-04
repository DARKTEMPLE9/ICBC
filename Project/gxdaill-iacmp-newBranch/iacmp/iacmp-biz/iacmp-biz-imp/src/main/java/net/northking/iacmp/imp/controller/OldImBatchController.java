package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.constant.ImpConstants;
import net.northking.iacmp.imp.domain.ImBatch;

import net.northking.iacmp.imp.domain.OldImBatch;
import net.northking.iacmp.imp.domain.OldImFile;
import net.northking.iacmp.imp.domain.OldImImage;
import net.northking.iacmp.imp.service.IOldImBatchService;
import net.northking.iacmp.imp.service.IOldImFileService;
import net.northking.iacmp.imp.service.IOldImImageService;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 影像流水 信息操作处理
 *
 * @author wei.chen
 * @date 2019-10-22
 */
@Controller
@RequestMapping("/uip/oldImBatch")
public class OldImBatchController extends BaseController {

    @Autowired
    private IOldImBatchService oldImBatchService;

    @Autowired
    private IOldImImageService oldImImageService;

    @Autowired
    private IOldImFileService oldImFileService;

    /**
     * 查询影像流水列表
     */
    @PostMapping("/list")
    @ResponseBody
    public List<ImBatch> list(@RequestBody OldImBatch oldImBatch) {
        List<OldImBatch> oldImBatchList = oldImBatchService.selectImBatchList(oldImBatch);
        List<ImBatch> batchList = new ArrayList<>();
        if (null != oldImBatchList) {
            for (OldImBatch oldImBatch1 : oldImBatchList) {
                ImBatch imBatch1 = new ImBatch();
                imBatch1.setId(oldImBatch1.getId());
                imBatch1.setRegbillglideno(oldImBatch1.getRegbillglideno());
                imBatch1.setOperationCode(oldImBatch1.getOperationcode());
                imBatch1.setSystemFlag(oldImBatch1.getSystemflag());
                imBatch1.setSysFlagInt(oldImBatch1.getSysFlagInt());
                imBatch1.setUserId(oldImBatch1.getUserId());
                imBatch1.setUserName(oldImBatch1.getUserName());
                imBatch1.setUserCode(oldImBatch1.getUserCode());
                imBatch1.setIdCard(oldImBatch1.getIdCard());
                imBatch1.setCardType(oldImBatch1.getCardType());
                batchList.add(imBatch1);
            }
        }

        return batchList;
    }


    /**
     * 导出影像流水列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OldImBatch imBatch) {
        List<OldImBatch> list = oldImBatchService.selectImBatchList(imBatch);
        ExcelUtil<OldImBatch> util = new ExcelUtil<OldImBatch>(OldImBatch.class);
        return util.exportExcel(list, "imBatch");
    }

    /**
     * 新增保存影像流水
     */
    @Log(title = "批次", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody OldImBatch imBatch) {
        return toAjax(oldImBatchService.insertImBatch(imBatch));
    }

    /**
     * 修改保存影像流水
     */
    @Log(title = "批次", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody OldImBatch imBatch) {
        return toAjax(oldImBatchService.updateImBatch(imBatch));
    }

    /**
     * 删除影像流水
     */
    @Log(title = "批次", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(oldImBatchService.deleteImBatchByIds(ids));
    }

    /**
     * 获取影像接口
     */
    @PostMapping("/listFiles")
    @ResponseBody
    public Map<String, List<Object[]>> listFiles(@RequestBody Map<String, String> paramMap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(ImpConstants.STATUS, ImpConstants.STATUS_NORMAL);
        String userIds = paramMap.get(ImpConstants.USERID);
        if (StringUtils.isNotEmpty(userIds)) {
            paramsMap.put("userIds", Convert.toStrArray(userIds));
        }
        String sysBusiNos = paramMap.get(ImpConstants.SYSBUSINO);
        if (StringUtils.isNotEmpty(sysBusiNos)) {
            paramsMap.put(ImpConstants.TRANSACTIONNO, sysBusiNos);
        }
        String billIds = paramMap.get(ImpConstants.BILLTYPE);
        if (StringUtils.isNotEmpty(billIds)) {
            paramsMap.put("billIds", Convert.toStrArray(billIds));
        }
        String fileId = paramMap.get(ImpConstants.FILEID);
        if (StringUtils.isNotEmpty(fileId)) {
            paramsMap.put(ImpConstants.ID, fileId);
        }
        String busiNo = paramMap.get(ImpConstants.BUSINO);
        if (StringUtils.isNotEmpty(busiNo)) {
            paramsMap.put(ImpConstants.BUSINO, busiNo);
        }
        List<OldImFile> fileList = oldImFileService.selectImFileByMap(paramsMap);
        List<Object[]> returnFileList = new ArrayList<>();
        Object[] fileObject;
        if (null != fileList) {
            for (OldImFile imFile : fileList) {
                fileObject = new Object[6];
                fileObject[0] = imFile.getId();
                fileObject[1] = imFile.getFilePath();
                fileObject[2] = imFile.getRemark();
                fileObject[3] = imFile.getBillId();
                fileObject[4] = imFile.getCreateUser();
                fileObject[5] = sdf.format(imFile.getCreateTime());
                returnFileList.add(fileObject);
            }
        }
        List<OldImImage> imageList = oldImImageService.selectImImageByMap(paramsMap);
        List<Object[]> returnImageList = new ArrayList<>();
        if (null != imageList) {
            for (OldImImage imImage : imageList) {
                fileObject = new Object[6];
                fileObject[0] = imImage.getId();
                fileObject[1] = imImage.getImagePath();
                fileObject[2] = imImage.getRemark();
                fileObject[3] = imImage.getBillId();
                fileObject[4] = imImage.getCreateUser();
                fileObject[5] = sdf.format(imImage.getCreateTime());
                returnImageList.add(fileObject);
            }
        }

        Map<String, List<Object[]>> resultMap = new HashMap<>();
        resultMap.put(ImpConstants.FILELIST, returnFileList);
        resultMap.put(ImpConstants.IMGLIST, returnImageList);
        return resultMap;
    }

    /**
     * 获取批次接口
     */
    @PostMapping("/listBatch")
    @ResponseBody
    public List<Object[]> listBatch(@RequestBody ImBatch imBatch) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        List<Object[]> returnList = new ArrayList<>();
        OldImBatch oldImBatch = new OldImBatch();
        oldImBatch.setRegbillglideno(imBatch.getRegbillglideno());
        oldImBatch.setSystemflag(imBatch.getSystemFlag());
        List<OldImBatch> batchList = oldImBatchService.selectImBatchList(oldImBatch);
        Object[] batchObject;
        if (null != batchList) {
            for (OldImBatch imBatch1 : batchList) {
                batchObject = new Object[2];
                batchObject[0] = sdf.format(imBatch1.getCreateTime());
                batchObject[1] = imBatch1.getTellerno();
                returnList.add(batchObject);
            }
        }

        return returnList;
    }

}
