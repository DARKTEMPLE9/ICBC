package net.northking.iacmp.cms.web.controller.file;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import net.northking.iacmp.cms.service.ICmsFileService;
import net.northking.iacmp.common.bean.dto.cms.CmsFileDTO;
import net.northking.iacmp.common.bean.vo.cms.CmsFileVO;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.result.ResultCode;
import net.northking.iacmp.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 内管文件 信息操作处理
 *
 * @author chenwei
 * @date 2019-11-23
 */
@Controller
@RequestMapping("/cms/cmsFile")
public class CmsFileController extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CmsFileController.class);

    @Autowired
    private ICmsFileService cmsFileService;

    /**
     * 根据条件查询文件和影像信息
     *
     * @param json
     * @return
     */
    @RequestMapping("/listByOpts")
    @ResponseBody
    public JSONObject selectCmsFileListByOpts(@RequestParam("json") String json) {
        long start = System.currentTimeMillis();

        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        result.put(CmsConstants.FILELIST, jsonArray);
        try {
            //校验接收的报文是否符合json规范
            if (!validate(json)) {
                result.put(CmsConstants.TOTALRESULTCODE, ResultCode.MSG_NULL.code());
                result.put(CmsConstants.TOTALRESULTMSG, ResultCode.MSG_NULL.msg());
                throw new IllegalArgumentException("报文不符合JSON规范!");
            }
            logger.debug("接收到报文信息：" + json);

            JSONObject jsonObj = JSONObject.parseObject(json);
            String operationCode = String.valueOf(jsonObj.get(CmsConstants.OPERATIONCODE));
            if (operationCode == null || "".equals(operationCode)) {
                result.put(CmsConstants.TOTALRESULTCODE, ResultCode.PROJECT_MISSING.code());
                result.put(CmsConstants.TOTALRESULTMSG, ResultCode.PROJECT_MISSING.msg());
                throw new IllegalArgumentException("报文必填项为空!");
            }
            CmsFileVO cmsFileVO = new CmsFileVO();
            cmsFileVO.setOperationCode(operationCode);
            List<CmsFileDTO> cmsFileDTOList = cmsFileService.selectCmsFileListByOpts(cmsFileVO);
            JSONObject jsonObject;
            if (null != cmsFileDTOList && !cmsFileDTOList.isEmpty()) {
                for (CmsFileDTO cmsFileDTO : cmsFileDTOList) {
                    jsonObject = new JSONObject();
                    jsonObject.put(CmsConstants.FILENAME, cmsFileDTO.getFName());
                    jsonObject.put(CmsConstants.SYSCODE, cmsFileDTO.getFSysCode());
                    jsonObject.put(CmsConstants.BILLCODE, cmsFileDTO.getFBillCode());
                    jsonObject.put(CmsConstants.FILEPATH, getRealPath(cmsFileDTO.getFPath()));
                    jsonArray.add(jsonObject);
                }
            }
            result.put(CmsConstants.TOTALRESULTCODE, ResultCode.SUCCESS.code());
            result.put(CmsConstants.TOTALRESULTMSG, ResultCode.SUCCESS.msg());
            return result;
        } catch (Exception e) {
            String transCode = e.getMessage();
            logger.error(transCode, e.fillInStackTrace());
            return result;
        } finally {
            long end = System.currentTimeMillis();
            logger.debug("交易结束，耗时：" + (end - start) + "毫秒");
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
            logger.error("json格式错误");
            return false;
        }
    }

    /**
     * 获取系统环境的ip
     *
     * @return
     * @author chenwei
     * @date 2019-11-22
     */
    private String getServerIp() {
        String system = System.getProperty("os.name").toLowerCase();
        if (system.indexOf("windows") >= 0) {
            return SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_FILETRANSIP);
        } else {
            return SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSIP);
        }
    }

    /**
     * 获取完整路径
     *
     * @return
     * @author chenwei
     * @date 2019-11-22
     */
    private String getRealPath(String filePath) {
        return "http://" + getServerIp() + ":" +
                SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPORT) +
                SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSPATHDOWN) +
                filePath;
    }
}
