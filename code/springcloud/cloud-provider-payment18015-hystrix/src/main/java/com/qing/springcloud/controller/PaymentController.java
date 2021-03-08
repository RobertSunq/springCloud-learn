package com.qing.springcloud.controller;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.service.PaymentService;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: sunQB
 * @Date: 2021-03-08 21:47
 * @Since: JDK-
 */
@RestController
@Slf4j
@RequestMapping("/provider/hytrix")
public class PaymentController {

    @Resource
    private  PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/ok/{id}")
    public CommonResult<String> paymentInfo_OK(@PathVariable("id") Integer id){

        String result = paymentService.paymentInfo_OK(id);
        log.info("********result:\t"+result);
        return new CommonResult<String>(200,result);

    }

    @GetMapping(value = "/timeout/{id}")
    public CommonResult<String> paymentInfo_TimeOut(@PathVariable("id")Integer id){
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("********result:\t"+result);
        return new CommonResult<String>(200,result);
    }
}
