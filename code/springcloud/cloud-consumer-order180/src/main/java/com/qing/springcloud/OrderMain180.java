package com.qing.springcloud;

import com.qing.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: sunQB
 * @Date: 2021-01-28 21:10
 * @Since: JDK-
 * *@RibbonClient() 再启动的该微服务的时候去加载我们自定义的Ribbon配置类。
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.qing.springcloud","com.qing.lb"})
//@RibbonClient(name = "CLOUD-PROVIDER-SERVICE",configuration = MySelfRule.class)
public class OrderMain180 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain180.class,args);
    }
}
