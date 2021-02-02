package com.qing.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 支付模块实体
 *
 * @author: sunQB
 * @Date: 2021-01-25 18:33
 * @Since: JDK-1.8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private String id;
    /**
     * 流水号
     */
    private String serial;
}
