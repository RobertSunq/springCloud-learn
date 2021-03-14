## Notes

### springCloud

![springCloud](.\static\picture\springCloud.png)



### Lifecycle——Maven生命周期

**注**：执行 Maven Project 中的生命周期的来管理项目时必须停止运行项目，否则无法对Maven项目进行生命周期的管理，同时注意一个重要的事情，Maven的生命周期在执行过程中有一个非常重要的特点，就是顺序依次自前向后执行，即如果执行compile,那么 clean 和 validate都已经自动执行了！！！

**具体的生命周期作用**

|              |      | 作用                                                         |
| ------------ | ---- | ------------------------------------------------------------ |
| **Clean**    | 清理 | 清空项目中的target目录，target是用来存放项目构建后的文件和目录、jar包、war包、编译的class文件，所有都是Maven构建时生成的。 |
| **Validate** | 验证 | 验证项目是否正确，所有必要的信息可用。                       |
| **Compile**  | 编译 | 编译Java文件。                                               |
| **Test**     | 测试 | 走单元测试的，报错信息处理，报错信息在target里面，console里不会报错。 |
| **Package**  | 打包 | 将会对项目进行打包。                                         |
| **verify**   | 验证 | 对集成测试的结果执行任何检查，以确保满足质量标准。           |
| **install**  | 安装 | 将打包过的jar包安装到本地Maven仓库，覆盖原来Maven本地仓库中的jar包，用作本地其他项目的依赖项。 |
| **site**     | 发布 | 生成项目站点文档，将项目站点发布到服务器。                   |
| **Deploy**   | 部署 | 在构建环境中完成，将最终的包复制到远程存储库以与其他开发人员和项目共享。 |





### Eureka的保护机制

**一句话概括为：某时刻某一个微服务不可用了，Eureka不会立即清理，依旧会对该微服务的信心进行保存** <u>（分布式CAP思想中的AP分支）</u>

![eureka_safe](.\static\picture\eureka_safe.png)



![eureka_safe00](.\static\picture\eureka_safe00.png)



![eureka_safe01](.\static\picture\eureka_safe01.png)

​		**再自我保护模式中，Eureka Server会保护服务注册表中的信息，不再注销任何服务实例**。他的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。一句话就是：好死不如赖活着。

​		**综上，自我保护模式是一种应对网络异常的安全保护措施。它的哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留）也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮、稳定**。



## zookeeper

注：**关于 zookeeper 的集群搭建，目前使用较少，而且在 yml 文件中的配置也是类似，以列表形式写入 zookeeper 的多个地址即可，而且zookeeper 集群，在 hadoop的笔记中也有记录。总而言之，只要配合zookeeper集群，以及yml文件的配置就能完成集群搭建**



## consul

```python
# 以开发模式启动：
consul agent -dev
# 启动后前端地址：http://localhost:8500/
```







## CAP

| 组件名    | 语言 | CAP  | 服务健康检查 | 对外暴露接口 | Spring Cloud集成 |
| --------- | ---- | ---- | ------------ | ------------ | ---------------- |
| Eureka    | Java | AP   | 可配支持     | HTTP         | 已集成           |
| Consul    | Go   | CP   | 支持         | HTTP/DNS     | 已集成           |
| Zookeeper | Java | CP   | 支持         | 客户端       | 已集成           |

![Theorem](.\static\picture\Theorem00.png)

**最多只能同时较好的满足两个。**

CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求，因此，根据CAP原理将NoSQL数据库分成了满足CA原则、满足CP原则、满足AP原则三大类：

~~**CA** - 单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大。~~

**CP** - 满足一致性，分区容忍性的系统，通常性能不是特别高。

**AP** - 满足可用性，分区容忍性的系统，通常可能对一致性要求低一点。

![CAP00](.\static\picture\CAP00.png)

![AP00](.\static\picture\AP00.png)



![CP](.\static\picture\CP00.png)





## Ribbon负载均衡

Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端   **负载均衡工具**。

简单的说，Ribbon是NetFlix发布的开源项目，主要功能是提供客户端的软件**负载均衡算法和服务调用**。Ribbon客户端组件提供一系列晚上的配置项如链接超时，重试登。简单的说，就是在配置文件中列出Load Balance（LB）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等）去连接这些机器。我们很容易使用Ribbon实现自定义的负载均衡算法。



![Rebbon00](.\static\picture\Rebbon00.png)



核心组件IRule：根据特定算法中从服务列表中选取一个要访问的服务：



```python
# com.netfilx.loadbalancer.RoundRobinRule	轮询
# com.netfilx.loadbalancer.RandomRule	随机
# com.netfilx.loadbalancer.RetryRule	先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用服务
# WeightedResponseTimeRule	对RoundRobinRule的扩展，相应速度越快的实列选择权重越大，越容易被选择
# BestAvailableRule	会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
# AvailabilityFilteringRule	先过滤故障实例，再选择并发较小的实例
# ZoneAvoidanceRule	默认规则，复合判断server所在区域的性能和server的可用性选择服务器
```



负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标， 每次服务重启后rest接口计数从1 开始

```python
List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
```



注：在（spring-cloud-starter-netflix-eureka-server）中已经集成引入了Ribbon。









## Hystrix服务降级

**服务降级、服务熔断、接近实时的监控**

![hystrix00](.\static\picture\hystrix00.png)



![hystrix01](.\static\picture\hystrix01.png)





#### 服务降级	fallback 

​		服务器忙，请稍后再试，不让客户端等待并立刻返回一个优化提示	||	正常流程运行存在问题，进行记录，之后根据记录数据进行补救。

​		哪些情况：程序运行异常、超时、服务熔断触发服务降级、线程池/信号量打满也会导致服务降级

#### 服务熔断	break 

​		当达到最大服务访问后，直接拒绝访问，然后调用服务降级的方法并返回友好提示

​		

| 类型     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 熔断打开 | 请求不再进行调用当前服务，内部设置时钟一般为MTTR（平均故障处理时间），当打开时长达到所设时钟则进入版熔断状态 |
| 熔断关闭 | 熔断关闭不会对服务进行熔断                                   |
| 熔断半开 | 部分请求根据规则调用当前服务，如果请求成功且符合规则认为当前服务恢复正常，关闭熔断 |

![hystrix02](.\static\picture\hystrix02.png)



![hystrix03](.\static\picture\hystrix03.png)



```java
@HystrixCommand(fallbackMethod = "str_fallbackMethod",
            groupKey = "strGroupCommand",
            commandKey = "strCommand",
            threadPoolKey = "strThreadPool",
            commandProperties = {
                    // 设置隔离策略，THREAD表示线程池   SEMAPHORE：信号池隔离
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    // 当隔离策略选择信号池隔离的时候，用来设置信号池的大小（最大并发数）
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    // 配置命令执行的超时时间
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10"),
                    // 是否启用超时时间
                    @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
                    // 设置超时的时候是否中断
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
                    // 执行被取消的时候是否中断
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnCancel", value = "true"),
                    // 允许方法执行的最大并发数
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    // 服务降级是否启用，是否执行回调函数
                    @HystrixProperty(name = "fallback.enabled", value = "true"),
                    // 是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    // 该属性用来设置在滚动时间窗种，断路器熔断的最小请求数，例如：默认该值是20的时候，
                    // 如果滚动时间窗（默认10秒）内仅收到了19个请求，即使这19个请求都失败了，断路器也不会打开
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                    // 该属性用来设置在滚动时间窗中，表示在滚动时间窗中，器请求数量超过circuitBreaker.requestVolumeThreshold 的情况下，
                    // 如果错误请求的百分比超过了50，就把断路器设置为“打开”状态，否则设置为“关闭”状态。
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    // 断路器强制打开
                    @HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
                    // 断路器强制关闭
                    @HystrixProperty(name = "circuitBreaker.forceClosed", value = "false"),
                    // 滚动时间窗设置，该时间用于断路器判断健康度时需要手机信息的持续时间
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    // 滚动时间窗设置，该时间用于断路器判断健康度时，需要收集指标信息的时候会根据设置的时间窗长度拆分成多个“桶”来累计个度量值，
                    // 每个“桶”记录了一段时间内的采集指标。比如10秒内拆分成10个“桶”收集这样，所以timeInMilliseconds必须能被numBuckets整除，否则会抛出异常
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
                    // 该属性用来设置对命令执行的延迟石头使用百分位数来跟踪和计算。如果设置为false，那么所有的概要统计东江返回-1
                    @HystrixProperty(name = "metrics.rollingPercentile.enabled", value = "false"),
                    // 该属性用来设置百分位统计的滚动窗口的持续时间，单位为毫秒
                    @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "60000"),
                    // 该属性用来设置百分比统计滚动窗口使用“桶”的数量
                    @HystrixProperty(name = "metrics.rollingPercentile.numBuckets", value = "60000"),
                    // 该属性用来设置在执行过程中每个“桶”中保留的最大执行次数。如果在滚动时间窗内发生超过该设定值的执行此说，就从最初的位置重写。
                    // 例如，将该值设置为100，滚动窗口为10秒，若在10秒内一个“桶”中发生了500次执行，那么该“桶”中只保留最后的100次执行的统计。
                    // 另外，增加该值的大小将会增加内存量的小猴，并增加排序百分位数所需的计算时间。
                    @HystrixProperty(name = "metrics.rollingPercentile.bucketSize", value = "100"),
                    // 该属性用来设置采集影响断路器状态的健康快照（请求的成功、错误百分比）的间隔等待时间
                    @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds", value = "500"),
                    // 是否开启请求缓存
                    @HystrixProperty(name = "requestCache.enabled", value = "true"),
                    // HystrixCommand的执行和事件是否打印日志到HystrixRequestLog 中
                    @HystrixProperty(name = "requestLog", value = "true")
            },
            threadPoolProperties = {
                    // 该参数用来设置执行命令线程池的核心线程数，该值也就是命令执行的最大并发量
                    @HystrixProperty(name = "coreSize", value = "10"),
                    // 该参数用来设置线程池的最大队列大小。当设置为-1时，线程池将使用SynchronousQueue实现的队列，
                    // 否则将使用LinkedBlockingQueue 实现的队列
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
                    // 该参数用来为队列设置拒绝阈值。通过该参数，即使队列没有达到最大值也能拒绝请求。
                    // 该参数主要时对LinBlockingQueue 队列的补充，因为LinkedBlockingQueue 队列不能动态修改它的对象大小，
                    // 而通过该属性就可以调整拒绝请求的队列大小了。
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "5")
            }
    )
    public String defaultCommon() {
        return "hello world";
    }
```



#### 服务限流	flowlimit

​		秒杀高并发等操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟N个，有序进行



<h4>Hystrix Dashboard</h4>

​		除了隔离以来服务的调用以外，Hystrix还提供了**准实时的调用监控（Hystrix Dashboard）**，Hystrix会秩序地记录所用通过Hystrix发起的请求的执行信息，并以统计报表的形式展示给用户，包括每秒执行多少请求多少成功，多少失败等。Netfix通过Hystrix-metrics-event-stream项目实现了对以上指标的监控。Spring Cloud也提供了Hystrix Dashboard的整合，对健康内容转化成可视化界面。

```python
# 注：新版本的Hystrix需要在被监控的主启动类中指定监控路径，否则会提示 Unable to connect to Command Metirc Stream 404
```



![hystrix04.png](.\static\picture\hystrix04.png)







## Gateway

​		Gateway 是在Spring生态系统上构建的API网关服务，基于Spring 5，Spring Boot 2和Project Reactor等技术。SpringCloud Gateway是基于WebFlux框架实现的，而WebFlux框架底层则使用了高性能的Reactor模式通信框架Netty。

​		Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤功能，例如：熔断、限流、重试等。目标是提供统一的路由方式且基于Filter链的方式提供了网关基本的功能，例如：安全，监控/指标和限流。同时还支持WebSocket

​		Gateway是基于**异步非阻塞模型**上进行开发的，性能方面不需要担心。



![gateway00](.\static\picture\getaway00.png)



部分新特性：

![gateway01](.\static\picture\getaway01.png)



三大核心：**路由------>断言------>过滤**

![gateway03](.\static\picture\getaway03.png)



![gateway02](.\static\picture\getaway02.png)



#### 断言规则：

![gateway04](.\static\picture\getaway04.png)

 

![gateway06](.\static\picture\getaway06.png)



![gateway07](.\static\picture\getaway07.png)



![gateway08](.\static\picture\getaway08.png)



#### 过滤：





















## ResTemplate



![ResTemplate00](.\static\picture\ResTemplate00.png)





```java
HystrixCommandProperties.class
```





























## 资源



####   [CentOS 阿里云镜像](http://mirrors.aliyun.com/centos/)：http://mirrors.aliyun.com/centos/



#### VMware中安装CentOS参考教程：

https://www.runoob.com/w3cnote/vmware-install-centos7.html

https://blog.csdn.net/qq_44714603/article/details/88829423



#### [zookeeper](http://archive.apache.org/dist/zookeepe):http://archive.apache.org/dist/zookeepe



#### [consul](https://www.consul.io/downloads)：https://www.consul.io/downloads

#### [consul中文文档](https://www.springcloud.cc/spring-cloud-consul.html)：https://www.springcloud.cc/spring-cloud-consul.html

#### [consul安装视频教程](https://learn.hashicorp.com/tutorials/consul/get-started-install)：https://learn.hashicorp.com/tutorials/consul/get-started-install



















[^说明]: 部分资料来源于网络

