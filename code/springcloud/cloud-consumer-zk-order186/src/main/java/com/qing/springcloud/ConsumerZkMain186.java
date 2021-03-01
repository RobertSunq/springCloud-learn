package com.qing.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: sunQB
 * @Date: 2021-03-01 21:54
 * @Since: JDK-
 */

@SpringBootApplication
@EnableDiscoveryClient // 该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class ConsumerZkMain186 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerZkMain186.class,args);
    }
}
