package com.qing.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.qing.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;


/**
 * @author: sunQB
 * @Date: 2021-03-24 22:16
 * @Since: JDK-
 */

@Slf4j
@EnableBinding(Source.class) // 指信道channel和exchange绑定在一起,定义消息的推送管道
public class IMessageProviderImpl implements IMessageProvider {

    private MessageChannel output; // 消息发送管道


    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("****************************serial:"+serial);
        return serial;
    }
}
