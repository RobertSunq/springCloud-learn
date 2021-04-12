package com.qing.cloudalibaba.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sunQB
 * @Date: 2021-04-12 21:37
 * @Since: JDK-
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "_________testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "_________testB";
    }
}
