package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: sunQB
 * @Date: 2021-03-24 21:29
 * @Since: JDK-
 */

@SpringBootApplication
@EnableEurekaClient
public class StreamConsumerMain18802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerMain18802.class,args);
    }

}
