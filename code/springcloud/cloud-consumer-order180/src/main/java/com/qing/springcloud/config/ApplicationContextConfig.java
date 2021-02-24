package com.qing.springcloud.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: sunQB
 * @Date: 2021-02-01 18:14
 * @Since: JDK-
 */

@Configuration
public class ApplicationContextConfig {

    /**
     * *使用注解 @LoadBalanced 赋予 RestTemplate 负载均衡的能力 (Ribbon负载均衡)
     * @return RestTemplate()
     */
    @Bean
    @LoadBalanced // 开启负载均衡
    public RestTemplate getRestTemplate(){
        // applicationContext.xml <bean id="" class="" >
        return new RestTemplate();
    }
}
