package com.qing.springcloud.service.impl;

import com.qing.springcloud.dao.PaymentDao;
import com.qing.springcloud.entities.Payment;
import com.qing.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: sunQB
 * @Date: 2021-01-26 9:23
 * @Since: JDK-
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * // @Autowired :该注解为spring提供
     * // @Resource  :该注解由java自带
     */

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(String id) {
        return paymentDao.getPaymentById(id);
    }
}
