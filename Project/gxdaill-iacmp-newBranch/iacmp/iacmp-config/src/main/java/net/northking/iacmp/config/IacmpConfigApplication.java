package net.northking.iacmp.config;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class IacmpConfigApplication {

    public static void main(String[] args) {
        log.info("IacmpConfigApplication startup main");
        SpringApplication application = new SpringApplication(IacmpConfigApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);

    }
}
