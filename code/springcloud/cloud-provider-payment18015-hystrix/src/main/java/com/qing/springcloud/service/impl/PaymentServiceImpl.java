package com.qing.springcloud.service.impl;

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
        return "线程池："+Thread.currentThread().getName()+"\tpaymentInfo_OK,id:\t"+id+"O(∩_∩)O";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber=3;
        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"\tpaymentInfo_TimeOut,id:\t"+id+"┭┮﹏┭┮"+"\t耗时： "+ Integer.toString(timeNumber) +" 秒种！";
    }
}
