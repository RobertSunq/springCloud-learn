package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: sunQB
 * @Date: 2021-03-02 22:06
 * @Since: JDK-
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulMain18010 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulMain18010.class,args);
    }
}
