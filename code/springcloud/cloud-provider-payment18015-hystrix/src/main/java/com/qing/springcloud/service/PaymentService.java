package com.qing.springcloud.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: sunQB
 * @Date: 2021-03-08 21:38
 * @Since: JDK-
 */
public interface PaymentService {


    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);

    /**
     * 对paymentInfo_TimeOut方法出现问题时，进行兜底补救。（服务降级）
     *
     * @param id id
     * @return String
     */
    public String paymentInfo_TimeOutHandler(Integer id);

    public String paymentCircuitBreaker(@PathVariable("id") Integer id);

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id);
}
