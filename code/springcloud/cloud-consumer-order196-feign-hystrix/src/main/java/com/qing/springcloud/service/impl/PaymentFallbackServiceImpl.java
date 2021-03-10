package com.qing.springcloud.service.impl;

import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: sunQB
 * @Date: 2021-03-10 22:20
 * @Since: JDK-
 */

@Service
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public CommonResult<String> paymentInfo_OK(Integer id) {
        return new CommonResult<String>(200,"--------- PaymentFallbackServiceImpl fall back -- paymentInfo_OK !!! (^人^)");
    }

    @Override
    public CommonResult<String> paymentInfo_TimeOut(Integer id) {
        return new CommonResult<String>(200,"--------- PaymentFallbackServiceImpl fall back -- paymentInfo_TimeOut !!! (^人^)");
    }
}
