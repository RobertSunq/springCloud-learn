package com.qing.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.qing.cloudalibaba.feign.PaymentServiceFeign;
import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.swing.text.DefaultEditorKit;

/**
 * @author: sunQB
 * @Date: 2021-04-14 22:24
 * @Since: JDK-
 */

@RestController
@RequestMapping("/consumer/nacostwo")
public class CircleBreakerController {

    private static final String SERVICE_URL = "http://nacos-provider";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentServiceFeign paymentServiceFeign;

    @GetMapping("/fallback/{id}")
//    @SentinelResource(value = "fallback") // 没有配置
//    @SentinelResource(value = "fallback" , fallback = "handlerFallback") // fallback只负责业务异常
//    @SentinelResource(value = "fallback" , blockHandler = "blockFallback") // blockFallback只负责sentinel控制台配置的违规
    // 两个都配置情况下，则被限流降级而抛出BlockException时只会进入blockHandler处理逻辑
//    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockFallback")
    // 放出IllegalArgumentException类型的错误不做降级处理
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockFallback",exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<?> fallback(@PathVariable("id") String id) {
        CommonResult<?> result = restTemplate.getForObject(SERVICE_URL + "/provider/nacos/" + id, CommonResult.class);
        if ("4".equals(id)) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常...");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;

    }

    @GetMapping("/feign/{id}")
    public CommonResult<?> getPayment(@PathVariable("id") String id) {
        if (id.equals("4")) {
            throw new IllegalArgumentException("非法参数异常...");
        } else {
            return paymentServiceFeign.getPayment(id);
        }
    }

    // 本例是fallback
    public CommonResult<?> handlerFallback(@PathVariable("id") String id, Throwable e) {
        return new CommonResult<>(444, "兜底异常handlerFallback，exception内容   " + e.getMessage(), new Payment(id, null));
    }

    // 本例是fallback
    public CommonResult<?> blockFallback(@PathVariable("id") String id, BlockException e) {
        return new CommonResult<>(445, "BlockException限流，无此流水，BlockException内容   " + e.getMessage(), new Payment(id, null));
    }
}
