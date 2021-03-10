package com.qing.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qing.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: sunQB
 * @Date: 2021-03-08 21:39
 * @Since: JDK-
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    @Override
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"    paymentInfo_OK,id:  "+id+"  O(∩_∩)O";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000") // 设置线程超时等待时间（单位:ms）
    }) // 方法超时或者报错，设置兜底，指定补救方法
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber=5;
        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"    paymentInfo_TimeOut,id:   "+id+"┭┮﹏┭┮"+"    耗时： "+ Integer.toString(timeNumber) +" 秒种！";
    }
    @Override
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"    paymentInfo_TimeOutHandler,id:    "+id+"    系统繁忙或运行错误，请稍后再试 ≧ ﹏ ≦";
    }
}
