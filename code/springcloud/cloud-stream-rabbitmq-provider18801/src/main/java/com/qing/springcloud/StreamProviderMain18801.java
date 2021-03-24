package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: sunQB
 * @Date: 2021-03-24 21:32
 * @Since: JDK-
 */

@SpringBootApplication
@EnableEurekaClient
public class StreamProviderMain18801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamProviderMain18801.class,args);
    }

}
