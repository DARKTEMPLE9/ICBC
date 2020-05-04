package net.northking.iacmp.cms;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 启动程序
 *
 * @author wxy
 */
@Slf4j
@MapperScan({"iacmp.biz.common.dao.mapper.cms"})
@EnableTransactionManagement
@EnableFeignClients("net.northking.iacmp.common.bean.feign.cms")
//@EnableElasticsearchRepositories("net.northking.iacmp.elasticsearch.repository")
@EnableEurekaClient
@EnableHystrix                // 开启断路器
@EnableHystrixDashboard
@SpringBootApplication(scanBasePackages = {"net.northking.iacmp"}, exclude = {DataSourceAutoConfiguration.class})
//@ImportResource(locations= {"classpath:application-bean.xml"})
public class CmsApplication {
    public static void main(String[] args) {
//        log.info("CMS Application is starting...");
        SpringApplication.run(CmsApplication.class, args);
    }
}