package com.qing.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: sunQB
 * @Date: 2021-01-19 20:59
 * @Since: JDK-1.8
 */

@SpringBootApplication
@EnableEurekaClient
public class PaymentMain18002 {

    public static void main(String[] args){
        SpringApplication.run(PaymentMain18002.class,args);
    }
}
