package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.constant.ImpServiceConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImAccessSystem;
import net.northking.iacmp.imp.service.IImAccessSystemService;
import net.northking.iacmp.imp.util.HttpFileTransUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.annotation.Configurations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.OperationsException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * httpfiletrans操作
 *
 * @author chenwei
 * @date 2019-11-05
 */
@RestController
@RequestMapping("/uip/fileTrans")
public class HttpFileTransController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(HttpFileTransController.class);

    /**
     * 上传
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(@RequestBody Map<String, String> paramMap) {
        String base64 = paramMap.get(ImpServiceConstants.BASE64);
        String localFilePath = paramMap.get(ImpServiceConstants.LOCALFILEPATH);
        String transFilePath = paramMap.get(ImpServiceConstants.TRANSFILEPATH);
        String tempFileName = paramMap.get(ImpServiceConstants.TEMPFILENAME);
        boolean genSuccess = HttpFileTransUtil.generateImage(base64, localFilePath);
        if (!genSuccess) {
            return toAjax(false);
        }
        //调用trans 上传原图
        boolean uploadFlag = HttpFileTransUtil.uploadTrans(localFilePath, tempFileName, transFilePath);
        if (!uploadFlag) {
            return toAjax(false);
        }

        return toAjax(true);
    }
}
