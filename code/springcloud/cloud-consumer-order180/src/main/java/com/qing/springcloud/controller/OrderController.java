package com.qing.springcloud.controller;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: sunQB
 * @Date: 2021-02-01 18:10
 * @Since: JDK-
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {
    /**
     * 单体版写死无影响
     * public static final String PAYMENT_UTL = "http://localhost:18001";
     *
     * 集群版服务提供者的地址应该为 Eureka上的服务名，且无需端口号
     */
    public static final String PAYMENT_UTL = "http://CLOUD-PROVIDER-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/create")
    @ResponseBody
    public CommonResult<Payment> create( Payment payment){
        return restTemplate.postForObject(PAYMENT_UTL+"/provider/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") String id){
        return restTemplate.getForObject(PAYMENT_UTL+"/provider/payment/getPaymentById?id="+id,CommonResult.class);
    }
}
