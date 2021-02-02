package com.qing.springcloud.service;


import com.qing.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author: sunQB
 * @Date: 2021-01-26 9:22
 * @Since: JDK-
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") String id);
}
