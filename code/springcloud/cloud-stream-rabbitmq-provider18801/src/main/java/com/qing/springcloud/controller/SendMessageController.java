package com.qing.springcloud.controller;

import com.qing.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: sunQB
 * @Date: 2021-03-24 22:24
 * @Since: JDK-
 */
@RequestMapping("/provider/stream")
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String SendMessage(){
        return messageProvider.send();
    }
}
