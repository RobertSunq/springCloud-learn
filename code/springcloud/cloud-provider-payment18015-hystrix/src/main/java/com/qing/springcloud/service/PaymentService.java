package com.qing.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @author: sunQB
 * @Date: 2021-03-08 21:38
 * @Since: JDK-
 */
public interface PaymentService {


    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);
}
