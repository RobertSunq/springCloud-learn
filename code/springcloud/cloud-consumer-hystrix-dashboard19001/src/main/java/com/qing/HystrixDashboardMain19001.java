package com.qing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author: sunQB
 * @Date: 2021-03-14 14:28
 * @Since: JDK-
 */

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain19001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain19001.class, args);
    }

}
