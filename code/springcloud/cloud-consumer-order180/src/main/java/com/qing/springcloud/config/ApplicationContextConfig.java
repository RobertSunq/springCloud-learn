package com.qing.springcloud.config;

import org.springframework.beans.factory.annotation.Configurable;
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

    @Bean
    public RestTemplate getRestTemplate(){
        // applicationContext.xml <bean id="" class="" >
        return new RestTemplate();
    }
}
