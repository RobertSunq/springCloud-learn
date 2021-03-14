package com.qing.springcloud;

import com.netflix.hystrix.HystrixMetrics;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author: sunQB
 * @Date: 2021-03-08 21:36
 * @Since: JDK-
 */

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker // 熔断器
public class ConsumerHystrixMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixMain.class, args);
    }

    /**
     * 此配置是为了服务监控而配置，与五福容错无关，springcloud升级后的问题
     * ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
     * 只要在自己的项目配置上下面的servlet就可以了
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
