package com.qing.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sunQB
 * @Date: 2021-04-12 21:37
 * @Since: JDK-
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "_________testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "_________testB";
    }

    /**
     * 热点限流
     * @param p1 1
     * @param p2 1
     * @return 1
     */
    @GetMapping("/testHotKey")
    /// 这个value是随意的值，并不和请求路径必须一致
    // 在填写热点限流的配置 资源名 这一项时，可以填 /testhotkey 或者是 @SentinelResource的value的值
    // 处理的是Sentinel控制台配置的违规情况，有blockHandler方法指定的配置进行兜底处理
    // 不指定兜底方法的时候，会将错误直接打印到前端 err page
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey") // 指定兜底方法
    public String testHotKey(@RequestParam(value = "p1" , required = false) String p1,
                             @RequestParam(value = "p2" , required = false) String p2){
        return "___________testHotKey";
    }

    public String deal_testHotKey(String p1, String pe , BlockException exception){
        return "--------------deal_textHotKey,/(ㄒoㄒ)/~~"; // 系统默认提示为：Blocked by Sentinel (flow limiting)
    }
}
