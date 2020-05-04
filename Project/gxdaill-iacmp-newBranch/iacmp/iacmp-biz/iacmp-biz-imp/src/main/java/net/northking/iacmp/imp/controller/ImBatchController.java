package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.constant.ImpConstants;
import net.northking.iacmp.imp.domain.ImBatch;
import net.northking.iacmp.imp.domain.ImCustomerBusino;
import net.northking.iacmp.imp.domain.ImFile;
import net.northking.iacmp.imp.domain.ImImage;
import net.northking.iacmp.imp.domain.ImTransactionBusino;
import net.northking.iacmp.imp.service.IImBatchService;
import net.northking.iacmp.imp.service.IImCustomerBusinoService;
import net.northking.iacmp.imp.service.IImFileService;
import net.northking.iacmp.imp.service.IImImageService;
import net.northking.iacmp.imp.service.IImTransactionBusinoService;
import net.northking.iacmp.imp.service.IOldImBatchService;
import net.northking.iacmp.imp.service.IOldImFileService;
import net.northking.iacmp.imp.service.IOldImImageService;
import net.northking.iacmp.imp.service.ISmParamService;
import net.northking.iacmp.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批次 信息操作处理
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
@RestController
@RequestMapping("/uip/imBatch")
public class ImBatchController extends BaseController {

    @Autowired
    private IImBatchService imBatchService;

    @Autowired
    private IImCustomerBusinoService imCustomerBusinoService;

    @Autowired
    private IImTransactionBusinoService imTransactionBusinoService;

    @Autowired
    private IImFileService imFileService;

    @Autowired
    private IImImageService imImageService;

    @Autowired
    private ISmParamService smParamService;

    @Autowired
    private IOldImBatchService oldImBatchService;

    @Autowired
    private IOldImFileService oldImFileService;

    @Autowired
    private IOldImImageService oldImImageService;

    /**
     * 查询批次列表
     */
    @PostMapping("/list")
    @ResponseBody
    public List<ImBatch> list(@RequestBody ImBatch imBatch) {
        // 如果userCode不为空，则先查关联表
        if (StringUtils.isNotEmpty(imBatch.getUserCode())) {
            ImCustomerBusino imCustomerBusino = new ImCustomerBusino();
            imCustomerBusino.setUserCode(imBatch.getUserCode());
            List<ImCustomerBusino> imCustomerBusinoList =
                    imCustomerBusinoService.selectImCustomerBusinoByUserCodes(
                            imBatch.getUserCode());
            List<ImBatch> imBatchList = new ArrayList<>();
            if (null != imCustomerBusinoList && !imCustomerBusinoList.isEmpty()) {
                imCustomerBusino = imCustomerBusinoList.get(0);
                String busino = imCustomerBusino.getBusino();
                String[] businos = busino.split(",");
                for (String busino1 : businos) {
                    imBatch.setRegbillglideno(busino1);
                    List<ImBatch> imBatchList1 = imBatchService.selectImBatchList(imBatch);
                    imBatchList.addAll(imBatchList1);
                }
            }
            return imBatchList;
        } else {
            return imBatchService.selectImBatchList(imBatch);
        }
    }

    /**
     * 历史影像查询
     */
    @PostMapping("/listAll")
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImBatch> listAll(@RequestBody HashMap map) {
        List<ImBatch> list = null;
        if (map.containsKey("businoList")) {
            list = imBatchService.listWithUserCode(map);
        } else {
            list = imBatchService.listAll(map);
        }
        return list;
    }

    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public Integer count(@RequestBody HashMap map) {
        Integer res = 0;
        if (map.containsKey("businoList")) {
            res = imBatchService.countWithUserCode(map);
        } else {
            res = imBatchService.count(map);
        }
        return res;
    }

    /**
     * 历史轨迹查询
     */
    @PostMapping("/historyList1")
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<HashMap> historyList1(@RequestBody String operationcode) {
        List<HashMap> list = imBatchService.historyList1(operationcode);
        return list;
    }

    @PostMapping("/historyList2")
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<HashMap> historyList2(@RequestBody String operationcode) {
        List<HashMap> list = imBatchService.historyList2(operationcode);
        return list;
    }

    @PostMapping("/selectImBatchByOpCode")
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public ImBatch selectImBatchById(@RequestBody String operationcode) {
        ImBatch imBatch = imBatchService.selectImBatchByOpCode(operationcode);
        return imBatch;
    }

    /**
     * 通过流水号查询批次信息
     *
     * @param operationCode
     * @return
     */
    @GetMapping("/queryImBatchByOperationCode")
    public ImBatch queryImBatchByOperationCode(String operationCode) {
        ImBatch imBatch = imBatchService.selectImBatchByOperationCode(operationCode);
        return imBatch;
    }

    /**
     * 通过流水号查询批次信息
     *
     * @param busino
     * @return
     */
    @GetMapping("/queryImBatchByBatchId")
    public ImBatch queryImBatchByBatchId(String busino) {
        ImBatch imBatch = imBatchService.selectImBatchById(busino);
        return imBatch;
    }

    /**
     * 新增保存批次
     */
    @Log(title = "批次", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ImBatch imBatch) {
        int num = imBatchService.insertImBatch(imBatch);
        if (num > 0) {
            // 如果有userCode，关联表更新
            if (StringUtils.isNotEmpty(imBatch.getUserCode())) {
                ImCustomerBusino imCustomerBusino = new ImCustomerBusino();
                imCustomerBusino.setUserCode(imBatch.getUserCode());
                List<ImCustomerBusino> imCustomerBusinoList =
                        imCustomerBusinoService.selectImCustomerBusinoByUserCodes(
                                imBatch.getUserCode());
                if (null != imCustomerBusinoList && !imCustomerBusinoList.isEmpty()) {
                    imCustomerBusino = imCustomerBusinoList.get(0);
                    String busino = imCustomerBusino.getBusino();
                    if (busino.indexOf(imBatch.getRegbillglideno()) == -1) {
                        busino += "," + imBatch.getRegbillglideno();
                        imCustomerBusino.setBusino(busino);
                        imCustomerBusinoService.updateImCustomerBusino(imCustomerBusino);
                    }
                } else {
                    imCustomerBusino.setBusino(imBatch.getRegbillglideno());
                    imCustomerBusinoService.insertImCustomerBusino(imCustomerBusino);
                }
            }
        }
        return toAjax(num);
    }

    /**
     * 修改保存批次
     */
    @Log(title = "批次", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody ImBatch imBatch) {
        int num = imBatchService.updateImBatch(imBatch);
        // 如果有userCode，关联表更新
        if (StringUtils.isNotEmpty(imBatch.getUserCode())) {
            ImCustomerBusino imCustomerBusino = new ImCustomerBusino();
            imCustomerBusino.setUserCode(imBatch.getUserCode());
            List<ImCustomerBusino> imCustomerBusinoList =
                    imCustomerBusinoService.selectImCustomerBusinoByUserCodes(
                            imBatch.getUserCode());
            if (null != imCustomerBusinoList && !imCustomerBusinoList.isEmpty()) {
                imCustomerBusino = imCustomerBusinoList.get(0);
                String busino = imCustomerBusino.getBusino();
                if (busino.indexOf(imBatch.getRegbillglideno()) == -1) {
                    busino += "," + imBatch.getRegbillglideno();
                    imCustomerBusino.setBusino(busino);
                    imCustomerBusinoService.updateImCustomerBusino(imCustomerBusino);
                }
            } else {
                imCustomerBusino.setBusino(imBatch.getRegbillglideno());
                imCustomerBusinoService.insertImCustomerBusino(imCustomerBusino);
            }
        }
        return toAjax(num);
    }

    /**
     * 删除批次
     */
    @Log(title = "批次", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(imBatchService.deleteImBatchByIds(ids));
    }

    /**
     * 获取影像接口
     */
    @PostMapping("/listFiles")
    @ResponseBody
    public Map<String, List<Object[]>> listFiles(@RequestBody Map<String, String> paramMap) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Map<String, Object> paramsMap = new HashMap<>();
        String busiNos = "";
        // 根据客户号查询影像流水号
        String userCodes = paramMap.get(ImpConstants.USERCODE);
        if (StringUtils.isNotEmpty(userCodes)) {
            List<ImCustomerBusino> imCustomerBusinos =
                    imCustomerBusinoService.selectImCustomerBusinoByUserCodes(userCodes);
            if (null != imCustomerBusinos) {
                StringBuilder sb = new StringBuilder();
                for (ImCustomerBusino imCustomerBusino : imCustomerBusinos) {
                    sb.append(imCustomerBusino.getBusino());
                    sb.append(",");
                }
                busiNos = sb.toString();
                if (busiNos.endsWith(",")) {
                    busiNos = busiNos.substring(0, busiNos.length() - 1);
                }
            }
            paramsMap.put("busiNos", Convert.toStrArray(busiNos));
        }
        // 根据关联业务流水号查询影像流水号
        String sysBusiNo = paramMap.get(ImpConstants.SYSBUSINO);
        if (StringUtils.isNotEmpty(sysBusiNo)) {
            List<ImTransactionBusino> imTransactionBusinos =
                    imTransactionBusinoService.selectImTransactionBusinoBySysBusiNo(sysBusiNo);
            if (null != imTransactionBusinos) {
                StringBuilder sb = new StringBuilder();
                for (ImTransactionBusino imTransactionBusino : imTransactionBusinos) {
                    sb.append(imTransactionBusino.getBusino());
                    sb.append(",");
                }
                busiNos = sb.toString();
                if (busiNos.endsWith(",")) {
                    busiNos = busiNos.substring(0, busiNos.length() - 1);
                }
            }
            paramsMap.put("busiNos", Convert.toStrArray(busiNos));
        }
        String busiNo = paramMap.get(ImpConstants.BUSINO);
        if (StringUtils.isNotEmpty(busiNo)) {
            paramsMap.put(ImpConstants.BUSINO, busiNo);
        }
        String billIds = paramMap.get(ImpConstants.BILLTYPE);
        if (StringUtils.isNotEmpty(billIds)) {
            paramsMap.put("billIds", Convert.toStrArray(billIds));
        }
        String fileId = paramMap.get(ImpConstants.FILEID);
        if (StringUtils.isNotEmpty(fileId)) {
            paramsMap.put(ImpConstants.ID, fileId);
        }
        paramsMap.put(ImpConstants.STATUS, ImpConstants.STATUS_NORMAL);
        List<ImFile> fileList = imFileService.selectImFileByMap(paramsMap);
        List<Object[]> returnFileList = new ArrayList<>();
        Object[] fileObject;
        if (null != fileList) {
            for (ImFile imFile : fileList) {
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
        List<ImImage> imageList = imImageService.selectImImageByMap(paramsMap);
        List<Object[]> returnImageList = new ArrayList<>();
        if (null != imageList) {
            for (ImImage imImage : imageList) {
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

        List<Object[]> returnList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        List<ImBatch> batchList = imBatchService.selectImBatchList(imBatch);
        Object[] batchObject;
        if (null != batchList) {
            for (ImBatch imBatch1 : batchList) {
                batchObject = new Object[2];
                batchObject[0] = sdf.format(imBatch1.getCreateTime());
                batchObject[1] = imBatch1.getTellerNo();
                returnList.add(batchObject);
            }
        }

        return returnList;
    }
}
