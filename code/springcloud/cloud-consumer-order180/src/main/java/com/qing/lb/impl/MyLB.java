package com.qing.lb.impl;

import com.qing.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: sunQB
 * @Date: 2021-03-07 15:38
 * @Since: JDK-
 * 手动实现负载均衡算法
 */
@Slf4j
@Component //   标注一个类为Spring容器的Bean，（把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
public class MyLB implements LoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        // 自旋锁
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;

        } while (!this.atomicInteger.compareAndSet(current,next));
        log.info("******第几次访问，次数next = "+next);
        return next;
    }

    /**
     *
     * @param serviceInstanceList 目前活跃的服务器列表
     * @return 访问的服务器
     * rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标， 每次服务重启后rest接口计数从1 开始
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
