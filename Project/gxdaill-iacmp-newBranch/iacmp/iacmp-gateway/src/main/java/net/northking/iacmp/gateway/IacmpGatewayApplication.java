package net.northking.iacmp.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author：Yanqingyu
 * @ClassName:IacmpGatewayApplication
 * @Description：网关路由模块启动类
 * @Date：Create in 9:38 AM2019/10/17
 * @Modified by:
 * @Version:1.0
 */
@Slf4j
@EnableZuulProxy
@SpringCloudApplication
public class IacmpGatewayApplication {
    public static void main(String[] args) {
        log.info("iacmp Gateway Application is Starting UP ...");
        SpringApplication application = new SpringApplication(IacmpGatewayApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);

    }
}
