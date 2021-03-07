package com.qing.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author: sunQB
 * @Date: 2021-03-07 15:36
 * @Since: JDK-
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
