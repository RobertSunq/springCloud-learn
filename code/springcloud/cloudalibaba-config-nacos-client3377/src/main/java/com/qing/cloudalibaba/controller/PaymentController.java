package com.qing.cloudalibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sunQB
 * @Date: 2021-03-29 21:22
 * @Since: JDK-
 */
@RestController
@RefreshScope // 支持nacos的动态刷新功能
public class PaymentController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/nacos/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
