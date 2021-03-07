package com.qing.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置feign日志开启级别
 *
 * @author: sunQB
 * @Date: 2021-03-07 18:06
 * @Since: JDK-
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
