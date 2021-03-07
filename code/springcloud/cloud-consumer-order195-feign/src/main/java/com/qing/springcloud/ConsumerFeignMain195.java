package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: sunQB
 * @Date: 2021-03-07 17:06
 * @Since: JDK-
 */

@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients // 表示开启feign
public class ConsumerFeignMain195 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignMain195.class,args);
    }
}
