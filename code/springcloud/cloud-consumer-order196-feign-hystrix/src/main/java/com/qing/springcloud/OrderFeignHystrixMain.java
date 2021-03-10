package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: sunQB
 * @Date: 2021-03-09 20:32
 * @Since: JDK-
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients // 表示开启feign
@EnableHystrix
public class OrderFeignHystrixMain {
    public static void main(String[] args) {
            SpringApplication.run(OrderFeignHystrixMain.class,args);
        }
}
