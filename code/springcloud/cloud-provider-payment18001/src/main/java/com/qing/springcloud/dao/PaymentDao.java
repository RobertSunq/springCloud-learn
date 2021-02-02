package com.qing.springcloud.dao;

import com.qing.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.swing.text.DefaultEditorKit;

/**
 * @author: sunQB
 * @Date: 2021-01-25 18:42
 * @Since: JDK-
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") String id);
}
