package net.northking.iacmp.ams.web.controller.outinterface;

import com.alibaba.druid.support.json.JSONParser;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import net.northking.iacmp.common.bean.domain.ams.*;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.server.service.IAmsBillService;
import net.northking.iacmp.server.service.IImBatchService;
import net.northking.iacmp.server.service.IImBillService;
import net.northking.iacmp.server.service.IOutInterfaceService;
import net.northking.iacmp.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 档案著录处理json报文接口
 */
@Controller
@RequestMapping("/outInterface/amsBatchRecord")
public class AmsBatchRecordController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AmsBatchRecordController.class);

    @Autowired
    private IOutInterfaceService outInterfaceService;
    @Autowired
    private IImBatchService imBatchService;
    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private IImBillService imBillService;

    /**
     * 接收并处理报文
     */
    @PostMapping("/record")
    @ResponseBody
    public void dealProcess(@RequestParam("json") String json) {
        long start = System.currentTimeMillis();

        try {
            //校验接收的报文是否符合json规范
            if (!validate(json.toString())) {
                throw new IllegalArgumentException("报文不符合JSON规范!");
            }
            log.debug("接收到报文信息：" + json.toString());

            JSONObject jsonObj = JSONObject.fromObject(json.toString());
            String arcBillCode = String.valueOf(jsonObj.get("arcBillCode"));
            if (arcBillCode == null || "".equals(arcBillCode)) {
                throw new IllegalArgumentException("报文必填项为空!");
            }

            String result = "";
            Map<String, String> resultInfo = null;
            //解析并保存报文信息
            resultInfo = outInterfaceService.saveFiles(jsonObj);
        } catch (Exception e) {
            String transCode = e.getMessage();
            log.error(transCode, e.fillInStackTrace());
        } finally {
            long end = System.currentTimeMillis();
            log.debug("交易结束，耗时：" + (end - start) + "毫秒");
        }
    }

    /**
     * 报文json规范校验
     *
     * @param json
     * @return
     */
    public boolean validate(String json) {
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            log.error("json格式错误");
            return false;
        }
    }

    /**
     * 返回接口信息
     *
     * @param request
     * @param response
     * @param tradeResult
     * @param tradeDesc
     */
    private void sendMessage(HttpServletResponse response, Map<String, String> resultInfo) {
        StringBuilder resultBuffer = new StringBuilder();
        JSONObject json = new JSONObject();
        for (Map.Entry<String, String> entry : resultInfo.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }
        resultBuffer.append(json.toString());
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(resultBuffer.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 保存图片信息到im_image表
     */
    @PostMapping("/saveImage")
    @ResponseBody
    public void saveImageProcess(@RequestParam("json") String json) {
        long start = System.currentTimeMillis();

        try {
            //校验接收的报文是否符合json规范
            if (!validate(json)) {
                throw new IllegalArgumentException("报文不符合JSON规范!");
            }
            log.debug("接收到报文信息：" + json);

            JSONParser jsonParser = new JSONParser(json);
            com.alibaba.fastjson.JSONObject jsonObj = new com.alibaba.fastjson.JSONObject(jsonParser.parseMap());
            String filename = null;
            String size = null;
            String batchId = null;
            String transfilepath = null;
            String billId = null;
            String remark = null;
            String createUser = null;
            if (jsonObj.get("filename") != null && !"".equals(jsonObj.get("filename"))) {
                filename = String.valueOf(jsonObj.get("filename"));
            }
            if (jsonObj.get("size") != null && !"".equals(jsonObj.get("size"))) {
                size = String.valueOf(jsonObj.get("size"));
            }
            if (jsonObj.get("arcNo") != null && !"".equals(jsonObj.get("arcNo"))) {
                batchId = String.valueOf(jsonObj.get("arcNo"));
            }
            if (jsonObj.get("transfilepath") != null && !"".equals(jsonObj.get("transfilepath"))) {
                transfilepath = String.valueOf(jsonObj.get("transfilepath"));
            }
            if (jsonObj.get("billId") != null && !"".equals(jsonObj.get("billId"))) {
                billId = String.valueOf(jsonObj.get("billId"));
            }
            if (jsonObj.get("remark") != null && !"".equals(jsonObj.get("remark"))) {
                remark = String.valueOf(jsonObj.get("remark"));
            }
            if (jsonObj.get("createuser") != null && !"".equals(jsonObj.get("createuser"))) {
                createUser = String.valueOf(jsonObj.get("createuser"));
            }
            AmsBatch amsBatch = new AmsBatch();
            amsBatch.setArcNo(String.valueOf(jsonObj.get("arcNo")));
            List<AmsBatch> list = amsBatchService.selectAmsBatchList(amsBatch);
            AmsBatch batch = new AmsBatch();
            if (list.size() > 0) {
                batch = list.get(0);
            }
            ImBill imBill = imBillService.selectImBillById(billId);
            if (imBill == null) {
                imBill = new ImBill();
                imBill.setId("noClassify");
            }
            Map<String, String> resultInfo = new HashMap<String, String>();
            //保存图片信息
            ImImage imImage = outInterfaceService.saveImage(filename, size, batch, transfilepath, imBill, remark, createUser);
            if (imImage != null) {
                resultInfo.put("ret", "Success");

            }
        } catch (Exception e) {
            String transCode = e.getMessage();
            log.error(transCode, e.fillInStackTrace());
        } finally {
            long end = System.currentTimeMillis();
            log.debug("交易结束，耗时：" + (end - start) + "毫秒");
        }
    }

    /**
     * 保存文件信息到im_file表
     */
    @PostMapping("/saveFiles")
    @ResponseBody
    public void saveFilesProcess(@RequestParam("json") String json) {
        long start = System.currentTimeMillis();

        try {
            //校验接收的报文是否符合json规范
            if (!validate(json)) {
                throw new IllegalArgumentException("报文不符合JSON规范!");
            }
            log.debug("接收到报文信息：" + json);

            JSONParser jsonParser = new JSONParser(json);
            com.alibaba.fastjson.JSONObject jsonObj = new com.alibaba.fastjson.JSONObject(jsonParser.parseMap());

            String filename = null;
            int size = 0;
            String batchId = null;
            String transfilepath = null;
            String billId = null;
            String remark = null;
            String createUser = null;
            if (jsonObj.get("filename") != null && !"".equals(jsonObj.get("filename"))) {
                filename = String.valueOf(jsonObj.get("filename"));
            }
            if (jsonObj.get("size") != null && !"".equals(jsonObj.get("size"))) {
                size = Integer.valueOf(String.valueOf(jsonObj.get("size")));
            }
            if (jsonObj.get("arcNo") != null && !"".equals(jsonObj.get("arcNo"))) {
                batchId = String.valueOf(jsonObj.get("arcNo"));
            }
            if (jsonObj.get("transfilepath") != null && !"".equals(jsonObj.get("transfilepath"))) {
                transfilepath = String.valueOf(jsonObj.get("transfilepath"));
            }
            if (jsonObj.get("billId") != null && !"".equals(jsonObj.get("billId"))) {
                billId = String.valueOf(jsonObj.get("billId"));
            }
            if (jsonObj.get("remark") != null && !"".equals(jsonObj.get("remark"))) {
                remark = String.valueOf(jsonObj.get("remark"));
            }
            if (jsonObj.get("createuser") != null && !"".equals(jsonObj.get("createuser"))) {
                createUser = String.valueOf(jsonObj.get("createuser"));
            }

            AmsBatch amsBatch = new AmsBatch();
            amsBatch.setArcNo(String.valueOf(jsonObj.get("arcNo")));
            List<AmsBatch> list = amsBatchService.selectAmsBatchList(amsBatch);
            AmsBatch batch = new AmsBatch();
            if (list.size() > 0) {
                batch = list.get(0);
            }
            ImBill imBill = imBillService.selectImBillById(billId);
            if (imBill == null) {
                imBill = new ImBill();
                imBill.setId("noClassify");
            }

            Map<String, String> resultInfo = new HashMap<String, String>();
            //保存文件信息
            ImFile imFile = outInterfaceService.saveFile(filename, size, batch, imBill, remark, transfilepath, createUser);
            if (imFile != null) {
                resultInfo.put("ret", "Success");
            }
        } catch (Exception e) {
            String transCode = e.getMessage();
            log.error(transCode, e.fillInStackTrace());
        } finally {
            long end = System.currentTimeMillis();
            log.debug("交易结束，耗时：" + (end - start) + "毫秒");
        }
    }
}
