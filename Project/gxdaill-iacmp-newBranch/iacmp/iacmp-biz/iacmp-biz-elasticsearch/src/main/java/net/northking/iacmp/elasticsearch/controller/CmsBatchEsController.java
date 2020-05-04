package net.northking.iacmp.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import net.northking.iacmp.cms.service.ICmsBillService;
import net.northking.iacmp.common.bean.domain.cms.CmsModel;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.elasticsearch.domain.CmsBatchType;
import net.northking.iacmp.elasticsearch.service.ICmsBatchEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author：Yanqingyu
 * @ClassName:PmsBatchController
 * @Description：TODO
 * @Date：Create in 6:52 PM2019/9/15
 * @Modified by:
 * @Version:1.0
 */
@Controller
@RequestMapping("/es")
public class CmsBatchEsController extends BaseController {

    @Resource
    private ICmsBatchEsService cmsBatchEsService;

    @Autowired
    private ICmsBillService cmsBillService;

    @GetMapping("/add")
    @ResponseBody
    public String addCmsBatch() {

        JSONObject metaData = new JSONObject();
        metaData.put("IDCard", "123432123443211234");
        metaData.put("projectName", "影像平台一期");

        CmsModel cmsModel = new CmsModel();
        cmsModel.setId(Long.valueOf(22));
        List<Ztree> ztrees = cmsBillService.modelBillTreeData(cmsModel, 1L);

        for (int i = 0; i < 30; i++) {
            CmsBatchType cmsBatchType = new CmsBatchType();
            cmsBatchType.setId(Long.valueOf(i));
            cmsBatchType.setBatchId(Long.valueOf(i));
            cmsBatchType.setBatchCreateBy("gaobo" + i);
            cmsBatchType.setDeptId("消费金融部");
            cmsBatchType.setSysCode("sys");
            cmsBatchType.setSysSource("pmo");
            cmsBatchType.setBatchCreateTime(new Date());
            cmsBatchType.setMetaData(metaData);
            cmsBatchType.setModel(ztrees);

            cmsBatchEsService.saveCmsBatch(cmsBatchType);

        }

        return null;
    }

}
