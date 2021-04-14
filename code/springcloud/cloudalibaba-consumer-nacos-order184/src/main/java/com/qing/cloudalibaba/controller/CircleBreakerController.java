package com.qing.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: sunQB
 * @Date: 2021-04-14 22:24
 * @Since: JDK-
 */

@RequestMapping("/consumer/nacos")
public class CircleBreakerController {

    private static final String SERVICE_URL="http://nacos-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/fallback/{id}")
    @SentinelResource(value = "fallback")
    public CommonResult<Payment> fallback(@PathVariable("id") String id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/provider/nacos/" + id, CommonResult.class);
        if("4".equals(id)){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常...");
        }else if (result.getData() == null){
            throw  new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return  null;
    }

    @GetMapping("/get/{id}")
    public CommonResult<?> getPayment(@PathVariable("id")String id){
        if(id.equals("4")){
            throw new IllegalArgumentException("非法参数异常...");
        }else {
            return restTemplate.getForObject(SERVICE_URL + "/provider/nacos/" + id, CommonResult.class);
        }
    }
}
