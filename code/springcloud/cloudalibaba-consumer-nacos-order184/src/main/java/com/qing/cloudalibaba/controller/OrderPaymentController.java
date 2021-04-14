package com.qing.cloudalibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: sunQB
 * @Date: 2021-03-29 21:22
 * @Since: JDK-
 */
@RestController
@Slf4j
@RequestMapping("/consumer/nacos")
public class OrderPaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public String getPaymentInfo(@PathVariable("id") String id) {
        return restTemplate.getForObject(serverURL+"/provider/nacos/"+id,String.class);
    }
}
