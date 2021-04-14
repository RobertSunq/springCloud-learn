package com.qing.cloudalibaba.controller;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author: sunQB
 * @Date: 2021-03-29 21:22
 * @Since: JDK-
 */
@RestController
@Slf4j
@RequestMapping("/provider/nacos")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<String, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put("1",new Payment("1","sdafasdfasdfasdfasdfasdfasdfasdfs"));
        hashMap.put("2",new Payment("2","asgkpkdskdfopadkfoadkpofkasodfkoa"));
        hashMap.put("3",new Payment("3","piepramdfpjsdjflakjdflajsdfioajfd"));
    }

    @GetMapping("/{id}")
    public CommonResult<?> getPayment(@PathVariable("id") String id) {
        Payment payment = hashMap.get(id);
        return new CommonResult<Payment>(200,"from mysql,serverPort:    " + serverPort,payment);
    }
}
