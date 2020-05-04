package net.northking.iacmp.ams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportResource;

/**
 * 启动程序
 *
 * @author wxy
 */
@Slf4j
@MapperScan("iacmp.biz.common.dao.mapper.ams")
@EnableFeignClients("net.northking.iacmp.common.bean.feign.ams")
@EnableEurekaClient
@EnableHystrix                // 开启断路器
@EnableHystrixDashboard
@ServletComponentScan("net.northking.iacmp.server.servlet")
@SpringBootApplication(scanBasePackages = {"net.northking.iacmp",
        "iacmp.biz.common.dao.ams"}, exclude = {DataSourceAutoConfiguration.class})
//@ImportResource(locations= {"classpath:application-bean.xml"})
public class AmsApplication {
    public static void main(String[] args) {
//    	log.info("AMS Application is starting...");
        SpringApplication.run(AmsApplication.class, args);
    }
}