package com.qing.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: sunQB
 * @Date: 2021-03-07 13:01
 * @Since: JDK-
 * 为何要分包创建自定义的负载规则?
 *      原因在于：在官方文档中具有明确警告，自定义的配置类不能放在@ComPonentScar所扫描的当前包及其子包下，
 *                  否则自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊定制的目的了。
 *                  而在注解@SpringBootApplication中就使用了注解（@ComPonentScar）来指定扫描当前包
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        // 自定义随机
        return new RandomRule();
    }
}
