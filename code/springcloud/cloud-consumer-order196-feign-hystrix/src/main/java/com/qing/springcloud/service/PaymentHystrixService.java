package com.qing.springcloud.service;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import com.qing.springcloud.service.impl.PaymentFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign for CLOUD-PROVIDER-PAYMENT18015-HYSTRIX
 *
 * @author: sunQB
 * @Date: 2021-03-07 17:12
 * @Since: JDK-
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT18015-HYSTRIX" , fallback = PaymentFallbackServiceImpl.class) // 表示使用该服务  fallback  =>  指定调用服务失败的时候调用类
@RequestMapping("/provider/hytrix")
// 在Feign客户端定义的接口添加一个服务降级处理的实现类，进行解耦
public interface PaymentHystrixService {


    @GetMapping(value = "/ok/{id}")
    public CommonResult<String> paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping(value = "/timeout/{id}")
    public CommonResult<String> paymentInfo_TimeOut(@PathVariable("id")Integer id);

}
