package com.qing.springcloud.controller;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.service.PaymentHystrixService;
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
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/ok/{id}")
    public CommonResult<String> getPaymentById(@PathVariable("id") Integer id){
        return  paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/timeout/{id}")
    public CommonResult<String> paymentTimeout(@PathVariable("id") Integer id){
        // openfeign-ribbon, 客户端一般默认等待一秒钟
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
}
