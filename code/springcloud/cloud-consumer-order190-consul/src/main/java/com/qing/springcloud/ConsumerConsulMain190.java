package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: sunQB
 * @Date: 2021-03-03 21:11
 * @Since: JDK-
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerConsulMain190 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerConsulMain190.class,args);
    }
}
