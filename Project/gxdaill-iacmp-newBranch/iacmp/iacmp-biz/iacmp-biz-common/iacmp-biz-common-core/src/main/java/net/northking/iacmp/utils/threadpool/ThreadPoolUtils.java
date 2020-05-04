package net.northking.iacmp.utils.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Description:调用线程池异步执行方法,已注册在容器中，调用时使用自动注入调用。
 * @Author: weizhe.fan
 * @CreateDate: 2019/10/31
 */
@Slf4j
@Component
public class ThreadPoolUtils {


    /**
     * 在@Async注解中指定线程池名
     */
    @Async("taskExecutor")
    public void function() {
        log.info("啥玩意啊");
        log.info("咋回事啊");
        log.info("那咋整啊");
        log.info("百度哇百度哇百度哇！我C你TM不会百度吗");
    }
}
