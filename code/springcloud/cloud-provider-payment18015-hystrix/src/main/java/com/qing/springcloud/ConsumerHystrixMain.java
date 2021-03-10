package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: sunQB
 * @Date: 2021-03-08 21:36
 * @Since: JDK-
 */

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class ConsumerHystrixMain {
    public static void main(String[] args) {
            SpringApplication.run(ConsumerHystrixMain.class,args);
        }
}
