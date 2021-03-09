package com.qing.springcloud.service;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: sunQB
 * @Date: 2021-03-07 17:12
 * @Since: JDK-
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT18015-HYSTRIX") // 表示使用该服务
@RequestMapping("/provider/hytrix")
public interface PaymentHystrixService {


    @GetMapping(value = "/ok/{id}")
    public CommonResult<String> paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping(value = "/timeout/{id}")
    public CommonResult<String> paymentInfo_TimeOut(@PathVariable("id")Integer id);

}
