package com.qing.cloudalibaba.feign.impl;

import com.qing.cloudalibaba.feign.PaymentServiceFeign;
import com.qing.springcloud.entities.CommonResult;
import org.springframework.stereotype.Service;

/**
 * @author: sunQB
 * @Date: 2021-03-10 22:20
 * @Since: JDK-
 */

@Service
public class PaymentFallbackServiceFeignImpl implements PaymentServiceFeign {

    @Override
    public CommonResult<?> getPayment(String id) {
        return new CommonResult<String>(200,"服务降级 --------- PaymentFallbackServiceFeignImpl fall back -- getPayment !!! (^人^)");
    }
}
