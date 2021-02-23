package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: sunQB
 * @Date: 2021-02-22 17:47
 * @Since: JDK-
 */


@SpringBootApplication
// 表示为Eureka的服务方
@EnableEurekaServer
public class EurekaMain17001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain17001.class,args);
    }
}
