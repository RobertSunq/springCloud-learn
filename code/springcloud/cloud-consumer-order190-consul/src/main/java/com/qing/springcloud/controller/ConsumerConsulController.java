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
public class ConsumerConsulController {
    public static final String INVOKE_URL="http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consul")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul",String.class);
        return result;
    }
}
