package com.qing.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: sunQB
 * @Date: 2021-03-29 21:16
 * @Since: JDK-
 */

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class CloudAlibabaConsumerMain184 {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaConsumerMain184.class,args);
    }
    
}
