package com.qing.springcloud.service;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: sunQB
 * @Date: 2021-03-07 17:12
 * @Since: JDK-
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICE") // 表示使用该服务
public interface PaymentFeignService {


    @GetMapping(value = "/provider/payment/getPaymentById")
    public CommonResult<Payment> getPaymentById(@RequestParam("id") String id);

    @GetMapping(value = "/provider/payment/timeout")
    public String paymentTimeout();

}
