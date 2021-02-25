package com.qing.springcloud.controller;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import com.qing.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: sunQB
 * @Date: 2021-01-26 9:28
 * @Since: JDK-
 */
@RestController
@Slf4j
@RequestMapping("/provider/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource

    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    @ResponseBody
    public CommonResult<?> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("**********插入结果：" + result);
        if (result > 0) {
            return new CommonResult<Integer>(200, "插入数据库成功,serverPort: " + serverPort, result);
        } else {
            return new CommonResult<Payment>(444, "插入数据库失败,serverPort: " + serverPort, null);
        }
    }

    @GetMapping(value = "/getPaymentById")
    public CommonResult<Payment> getPaymentById(@RequestParam("id") String id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("**********插入结果：" + payment + "\n  O(∩_∩)O哈哈~\n ,serverPort: " + serverPort);
        if (payment != null) {
            return new CommonResult<Payment>(200, "查询成功,serverPort: " + serverPort, payment);
        } else {
            return new CommonResult<Payment>(444, "没有对应记录，查询ID：" + id + " ,serverPort: " + serverPort, null);
        }
    }

    /**
     * 服务清单列表
     * @return Object
     */
    @GetMapping(value = "/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        // 快捷输入 iter
        for (String service : services) {
            log.info("***** service : "+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

}
