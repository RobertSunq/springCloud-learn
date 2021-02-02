package com.qing.springcloud.controller;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import com.qing.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: sunQB
 * @Date: 2021-01-26 9:28
 * @Since: JDK-
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/create")
    @ResponseBody
    public CommonResult<?> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("**********插入结果：" + result);
        if (result > 0) {
            return new CommonResult<Integer>(200, "插入数据库成功", result);
        } else {
            return new CommonResult<Payment>(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/getPaymentById")
    public CommonResult<Payment> getPaymentById(@RequestParam("id") String id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("**********插入结果：" + payment + "\n  O(∩_∩)O哈哈~");
        if (payment != null) {
            return new CommonResult<Payment>(200, "查询成功", payment);
        } else {
            return new CommonResult<Payment>(444, "没有对应记录，查询ID："+ id, null);
        }
    }

}
