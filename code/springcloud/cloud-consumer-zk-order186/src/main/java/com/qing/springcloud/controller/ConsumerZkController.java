package com.qing.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: sunQB
 * @Date: 2021-03-01 22:02
 * @Since: JDK-
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class ConsumerZkController {
    public static final String INVOKE_URL="http://cloud-provider-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/zk")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk",String.class);
        return result;
    }
}
