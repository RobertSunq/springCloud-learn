package com.qing.cloudalibaba.feign;

import com.qing.cloudalibaba.feign.impl.PaymentFallbackServiceFeignImpl;
import com.qing.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: sunQB
 * @Date: 2021-04-15 21:48
 * @Since: JDK-
 */

@FeignClient(value = "nacos-provider" , fallback = PaymentFallbackServiceFeignImpl.class)// 表示使用该服务  fallback  =>  指定调用服务失败的时候调用类
public interface PaymentServiceFeign {

    @GetMapping(value = "/provider/nacos/{id}")
    public CommonResult<?> getPayment(@PathVariable("id") String id);
}
