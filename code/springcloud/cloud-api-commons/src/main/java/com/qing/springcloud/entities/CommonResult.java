package com.qing.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回实体
 *
 * @author: sunQB
 * @Date: 2021-01-25 18:36
 * @Since: JDK-1.8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    /**
     * 编码（200、404、503）
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 实体，结果集
     */
    private T data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
