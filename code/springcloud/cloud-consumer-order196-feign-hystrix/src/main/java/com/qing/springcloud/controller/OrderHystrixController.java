package com.qing.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")// 指定默认的服务降级兜底方法
@RequestMapping("/consumer/payment")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/ok/{id}")
    @HystrixCommand // 未指定时，调用全局默认方法
    public CommonResult<String> getPaymentById(@PathVariable("id") Integer id){
        return  paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500") // 进行了指定，调用指定方法。
    })
    public CommonResult<String> paymentTimeout(@PathVariable("id") Integer id){
        // openfeign-ribbon, 客户端一般默认等待一秒钟
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    /**
     * 自定义服务降级兜底方法
     *
     * @return String
     */
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者196，对方支付系统18015系统繁忙，请在10秒钟后再试或者通知管理员检查程序是否存在运行错误，/(ㄒoㄒ)/~~！    该消息由自定义兜底方法提供。";
    }

    /**
     * 全局服务降级兜底方法
     *
     * @return String
     */
    public String paymentGlobalFallbackMethod(){
        return "我是消费者196，对方支付系统18015系统繁忙，请在10秒钟后再试或者通知管理员检查程序是否存在运行错误，/(ㄒoㄒ)/~~！    该消息由全局兜底方法提供。";
    }
}
