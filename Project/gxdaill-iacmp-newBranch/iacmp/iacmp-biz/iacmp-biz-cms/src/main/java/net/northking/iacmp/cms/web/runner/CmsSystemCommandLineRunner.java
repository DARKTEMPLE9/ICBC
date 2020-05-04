package net.northking.iacmp.cms.web.runner;

import net.northking.iacmp.cms.service.ICmsSystemService;
import net.northking.iacmp.cms.web.util.CmsInitParamsUtil;
import net.northking.iacmp.common.bean.domain.cms.CmsSystem;
import net.northking.iacmp.framework.runner.SysConfigCommandLineRunner;
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
public class CmsSystemCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CmsSystemCommandLineRunner.class);

    @Autowired
    private ICmsSystemService cmsSystemService;

    private static ICmsSystemService cmsSystemStaticService;

    @Override
    public void run(String... args) throws Exception {

        List<CmsSystem> cmsSystemList = cmsSystemService.selectCmsSystemList(new CmsSystem());
        Map<String, CmsSystem> cmsSystem = cmsSystemList.stream().collect(Collectors.toMap(CmsSystem::getSysCode, Function.identity()));

        CmsInitParamsUtil.getCmsInitParamsUtil().setCmsSystemMap(cmsSystem);

        if (cmsSystemStaticService == null) {
            cmsSystemStaticService = cmsSystemService;
        }

    }

    /**
     * 刷新缓存
     */
    public static synchronized void refreshCache() {
        logger.info("-------开始刷新cms_system缓存-------");
        CmsInitParamsUtil.getCmsInitParamsUtil().getCmsBillMap().clear();
        List<CmsSystem> cmsSystemList = cmsSystemStaticService.selectCmsSystemList(new CmsSystem());
        Map<String, CmsSystem> cmsSystem = cmsSystemList.stream().collect(Collectors.toMap(CmsSystem::getSysCode, Function.identity()));

        CmsInitParamsUtil.getCmsInitParamsUtil().setCmsSystemMap(cmsSystem);
        logger.info("-------刷新cms_system缓存完毕-------");

    }


}
