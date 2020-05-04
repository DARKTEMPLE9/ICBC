package net.northking.iacmp.cms.web.runner;

import net.northking.iacmp.cms.service.ICmsBillService;
import net.northking.iacmp.cms.web.util.CmsInitParamsUtil;
import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author：Yanqingyu
 * @ClassName:SysConfigCommandLineRunner
 * @Description：将cms_bill加载至缓存中
 * @Date：Create in 9:07 AM2019/9/12
 * @Modified by:
 * @Version:1.0
 */
@Component
@Order(value = 2)
public class CmsBillCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CmsBillCommandLineRunner.class);


    @Autowired
    private ICmsBillService cmsBillService;

    private static ICmsBillService cmsBillStaticService;

    @Override
    public void run(String... args) throws Exception {

        List<CmsBill> cmsBillList = cmsBillService.selectCmsBillList(new CmsBill(), 1L);
        Map<String, CmsBill> cmsBill = cmsBillList.stream().collect(Collectors.toMap(CmsBill::getBillCode, Function.identity()));

        CmsInitParamsUtil.getCmsInitParamsUtil().setCmsBillMap(cmsBill);

        if (cmsBillStaticService == null) {
            cmsBillStaticService = cmsBillService;
        }

    }

    /**
     * 刷新缓存
     */
    public static synchronized void refreshCache() {
        logger.info("-------开始刷新cms_bill缓存-------");
        CmsInitParamsUtil.getCmsInitParamsUtil().getCmsBillMap().clear();
        List<CmsBill> cmsBillList = cmsBillStaticService.selectCmsBillList(new CmsBill(), 1L);
        Map<String, CmsBill> cmsBill = cmsBillList.stream().collect(Collectors.toMap(CmsBill::getBillCode, Function.identity()));
        CmsInitParamsUtil.getCmsInitParamsUtil().setCmsBillMap(cmsBill);
        logger.info("-------刷新cms_bill缓存完毕-------");
    }


}
