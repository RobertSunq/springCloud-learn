package com.qing.cloudalibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.qing.springcloud.entities.CommonResult;
import com.qing.springcloud.entities.Payment;
import jdk.nashorn.internal.ir.Block;

/**
 * 自定义全局兜底方法
 * @author: sunQB
 * @Date: 2021-04-14 21:50
 * @Since: JDK-
 */
public class CustomerBlockHandler {

    public static CommonResult<?> handlerException(BlockException exception){
        return new CommonResult<>(4444,"按客户自定义,global handlerException ---------------1");
    }
    public static CommonResult<?> handlerException2(BlockException exception){
        return new CommonResult<>(4444,"按客户自定义,global handlerException ------------2");
    }
}
