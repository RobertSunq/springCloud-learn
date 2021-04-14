package com.qing.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.qing.cloudalibaba.myhandler.CustomerBlockHandler;
import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sunQB
 * @Date: 2021-04-14 21:35
 * @Since: JDK-
 */
@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    //处理降级的方法名
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult<Payment> byResource() {
        return new CommonResult<>(200, "按照资源名限流测试0K", new Payment("2020", "serial001"));
    }

    //降级方法
    public CommonResult<String> handleException(BlockException e) {
        return new CommonResult<>(444, e.getClass().getCanonicalName() + "       服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult<?> byUrl() {
        return new CommonResult<>(200, "按url限流测试OK", new Payment("2020", "serial002"));
    }

    // CustomerBlockHandler
    @GetMapping("/rateLimit/customerBlockHandler")
    // 指定兜底类和兜底方法，注意如果方法带有参数，那么指定的降级兜底方法，除了BlockException参数外其他参数也要与该方法保持参数一致
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException2")
    public CommonResult<?> CustomerBlockHandler() {
        return new CommonResult<>(200, "按客户自定义", new Payment("2020", "serial003"));
    }

}
