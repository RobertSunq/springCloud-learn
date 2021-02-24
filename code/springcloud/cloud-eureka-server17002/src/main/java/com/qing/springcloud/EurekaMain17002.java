package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: sunQB
 * @Date: 2021-02-24 17:29
 * @Since: JDK-
 */

@SpringBootApplication
// 表示为Eureka的服务方
@EnableEurekaServer
public class EurekaMain17002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain17002.class, args);
    }
}
