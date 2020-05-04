package net.northking.iacmp.framework.runner;

import net.northking.iacmp.system.domain.SysConfig;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.system.service.ISysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author：Yanqingyu
 * @ClassName:SysConfigCommandLineRunner
 * @Description：将sys_config加载至缓存中
 * @Date：Create in 9:07 AM2019/9/12
 * @Modified by:
 * @Version:1.0
 */
@Component
@Order(value = 1)
public class SysConfigCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysConfigCommandLineRunner.class);


    @Autowired
    private ISysConfigService sysConfigService;

    private static ISysConfigService sysConfigServiceStatic;

    @Override
    public void run(String... args) throws Exception {

        //查找sys_config表将config_key作为key，config_value作为value添加至map中
        List<SysConfig> sysConfigList = sysConfigService.selectConfigList(new SysConfig());
        Map<String, String> sysConfig = sysConfigList.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));

        SysConfigInitParamsUtils.getSysConfigInitParamsUtils().setSysConfigMap(sysConfig);

        if (sysConfigServiceStatic == null) {
            sysConfigServiceStatic = sysConfigService;
        }

    }


    /**
     * 刷新缓存
     */
    public static synchronized void refreshCache() {
        logger.info("-------开始刷新sys_config缓存-------");
        SysConfigInitParamsUtils.getSysConfigInitParamsUtils().getSysConfigMap().clear();
        List<SysConfig> sysConfigList = sysConfigServiceStatic.selectConfigList(new SysConfig());
        Map<String, String> sysConfig = sysConfigList.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));

        SysConfigInitParamsUtils.getSysConfigInitParamsUtils().setSysConfigMap(sysConfig);
        logger.info("-------刷新sys_config缓存完毕-------");
    }


}
