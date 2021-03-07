package com.qing.springcloud.controller;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import com.qing.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: sunQB
 * @Date: 2021-03-07 17:18
 * @Since: JDK-
 */

@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") String id){
        return  paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/timeout")
    public String paymentTimeout(){
        // openfeign-ribbon, 客户端一般默认等待一秒钟
        return paymentFeignService.paymentTimeout();
    }
}
