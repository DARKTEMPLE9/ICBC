package net.northking.iacmp.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import net.northking.iacmp.elasticsearch.domain.CmsFileType;
import net.northking.iacmp.elasticsearch.service.ICmsFileEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author：Yanqingyu
 * @ClassName:CmsFileController
 * @Description：TODO
 * @Date：Create in 6:52 PM2019/9/15
 * @Modified by:
 * @Version:1.0
 */
@Controller
@RequestMapping("/es/file")
public class CmsFileEsController {

    @Autowired
    private ICmsFileEsService cmsFileEsService;

    @GetMapping("/add")
    @ResponseBody
    private String addCmsFile() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("other", "don't know");

        for (int i = 0; i < 100; i++) {
            CmsFileType cmsFileType = new CmsFileType();
            cmsFileType.setBatchId(Long.valueOf(i));
            cmsFileType.setId(Long.valueOf(i));
            cmsFileType.setBillCode("");
            cmsFileType.setDeptId("消费金融事业部");
            cmsFileType.setFileId(Long.valueOf(i));
            cmsFileType.setFileName("立项审批材料" + i);
            cmsFileType.setFileSource("pmo");
            cmsFileType.setSysCode("oa");
            cmsFileType.setFileType("txt");
            cmsFileType.setFileSize(1024.00);
            cmsFileType.setFileUploadBy("gaobo" + i);
            cmsFileType.setRemark("影像平台一期立项审批材料");
            cmsFileType.setMetaData(jsonObject);

            cmsFileEsService.saveCmsFile(cmsFileType);
        }


        return null;
    }
}
