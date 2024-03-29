# 初级部分



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
# 访问路径：http://localhost:18015/hystrix.stream
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



## 分布式系统面临的问题：

#### 配置问题

​		微服务意味着要将单体应用中的业务拆分成一个个自服务，每个服务的粒度相对较小，因此系统中会出现大量的服务。由于每个服务都需要必要的配置信息才能运行，所用一套集中式的、动态的配置管理设施是必不可少的。-----------	SpringCloud Config 配置中心  为微服务架构中的微服务提供集中化的外部配置支持 配置服务器为**各个不同微服务应用**的所有环境提供了**一个中心化的外部配置**。

![config00](.\static\picture\config00.png)

![config01](.\static\picture\config01.png)



**label:分支（branch）**

**name：服务名**

**profiles：环境（dev/test/prod）**



![config02](.\static\picture\config02.png)

![config036](.\static\picture\config03.png)



![config04](.\static\picture\config04.png)

![config05](.\static\picture\config05.png)



###### bootstrap.yml

![bootstrap00](.\static\picture\bootstrap00.png)

###### 动态刷新问题

> 由于config Server 项目上是动态更新的，但是，client端的项目中的配置，还是之前的，它不能动态更新，必须重启才行，所以需要开启动态刷新

1、引入依赖

```xml
<!--actuator监控-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

2、在客户端的boostrap中添加注解

```yml
# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

3、controller添加刷新注解

![config06](.\static\picture\config06.png)

4、同时在需要client端发送一个POST请求进行刷新

> 如 curl -X POST "http://localhost:3355/actuator/refresh"

> 两个必须：1.必须是 POST 请求，2.请求地址：http://localhost:3355/actuator/refresh

注意：这里是刷新哪个config client 就像哪个config client 发送相关请求

但是同时又存在一个问题，就是要向每个微服务发送一次POST请求，当微服务数量庞大，又是一个新的问题。同时也为了可以进行广播，一次通知，处处生效，进行大范围的自动刷新。故可采用消息总线。

#### 消息总线 Bus

支持两种消息代理：RabbitMQ和KafKa

![bus](.\static\picture\bus.png)



![bus00](.\static\picture\bus00.png)

![bus01.png](.\static\picture\bus01.png)

###### 相关环境配置

> 安装RabbitMQ的依赖环境 Erlang  下载地址： http://erlang.org/download/otp_win64_21.3.exe 
>
> 安装RabbitMQ   下载地址： http://dl.bintray.com/rabbitmq/all/rabbitmq-server/3.7.14/rabbitmq-server-3.7.14.exe 
>
> 进入 rabbitMQ安装目录的sbin目录下，打开cmd窗口，执行 		rabbitmq-plugins enable rabbitmq_management
>
> 访问		http://localhost:15672/		，输入密码和账号：默认都为 guest



##### 设计思想

1、利用消息总线触发一个**客户端**/bus/refresh，而刷新所有客户端的配置

​		该方案不合适的原因：

​				①	打破了微服务的职责单一性，因为微服务本身是业务模块，它本不应该承担配置刷新的职责。

​				②	破坏了微服务各节点的对等性。

​				③	有一定的局限性。例如，微服务在迁移时，它的网络地址常常会发生变换，此时如果想要做到自动刷新，那会增加更多的修改。

![bus03.png](.\static\picture\bus03.png)

2、利用消息总线触发一个**服务端**ConfigServer的/bus/refresh端点，而刷新所有客户端的配置

![bus02](.\static\picture\bus02.png)

相关配置完成后，修改文件，向config server发送刷新请求

> curl -X POST "http://localhost:3344/actuator/bus-refresh"

注意，之前是向config client 一个个发送请求，但是这次是向 config Server 发送请求，而所有的config client 的配置也都全部更新





![bus04](.\static\picture\bus04.png)



定点通知：

​		指定具体某一个实例生效而不是全部

> ​	公式：http://localhost:配置中心的端口号/actuator/bus-refresh/{destination}

​		/bus/refresh请求不再发送到具体的服务实例上，而是发给config server并通过destination参数类指定需要更新配置的服务或实例

> curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"  (服务名+端口号)



![bus05](.\static\picture\bus05.png)



## 消息驱动

是什么：

​				屏蔽底层消息中间件的差异，降低切换成本，同意消息的编程模型

![stream00](.\static\picture\stream00.png)



原理：

​	标准MQ：	

​						1、生产者/消费者之间靠消息媒介传递信息内容——>Message

​						2、消息必须走特定的通道——>消息通道MessageChannel

​						3、消息通道里的消息如何被消费，谁负责收发处理——>消息通道MessageChannel的子接口SubscribableChannel，由MessageHandler消息处理器所订阅。

![stream01](.\static\picture\stream01.png)

![stream02](.\static\picture\stream02.png)



Stream中的消息通信方式遵循了发布-订阅模式——Topic主题进行广播——（在RabbitMQ中是Exchange，在Kafka中就是Topic）

![stream03](.\static\picture\stream03.png)

> > Binder：很方便的连接中间件，屏蔽差异
> >
> > Channel：通道，是队列Queue的一种抽象，在消息通讯系统中就是实现存储和转发的媒介，通过Channel对队列进行配置
> >
> > Source和Sink：简单的可理解为 参照对象是Spring Cloud Stream自身，从Stream发布消息就是输出，接受消息就是输入。

![stream04](.\static\picture\stream04.png)



注意：在stream中处于同一个group中的多个消费者是竞争关系，就能够保证消息指挥被其中一个应用消费一次。

​			不同组是可以全面消费的（重复消费）。

​			同一组内发生竞争关系，只有其中一个可以消费。

通过分组来解决重复消费的问题

#### 分组

原理：		微服务应用放置于同一个group中，就能够有保证消息只会被其中一个应用消费一次。不同的组是可以消费的，同一个组内会发生竞争关系，只有其中					一个可以消费，就可以保证同一个组的多个微服务实例进行抢占，只有一个可以消费。



#### 持久化





## 分布式请求链路跟踪Sleuth

![sleuth00](.\static\picture\sleuth00.png)

是什么		spring cloud sleuth 提供了一套完成的服务跟踪的解决方案，在分布式系统中提供追踪解决方案并兼容支持了zipkin



安装zipkin：

> 在官网下载zipkin-xxx.jar后，在cmd中运行该jar包， 输入 >    java - jar zipkin-xxx.jar
>
> 浏览器中访问：http://localhost:9411/zipkin/



![sleuth01](.\static\picture\sleuth01.png)

精简后

Trace：类似与树结构的Span集合，表示一条调用链路，存在唯一标识

Span：表示调用链路来源，通俗的理解span就是一次请求信息

![sleuth02](.\static\picture\sleuth02.png)



# 高级部分



## SpringCloud Alibaba 入门简介

参考文档：https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md

最新的pom配置：

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2.2.5.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```



## Nacos

是什么：一个更易于构建的云原生应用的动态服务发现、配置管理和服务管理平台。即注册中心与配置中心。= Eureka + Config + Bus

支持负载均衡

> github地址：  https://github.com/alibaba/Nacos 
>
> Nacos 地址：  https://nacos.io/zh-cn/ 
>
> 下载地址： https://github.com/alibaba/nacos/releases/
>
> 安装：
>
> ​		解压压缩包以后，进入bin目录，打开dos窗口，执行startup.cmd
>
> 访问地址 ： 【http://localhost:8848/nacos/】地址，默认账号密码都是nacos
>
> nacos可以切换 AP 和 CP ,使用如下命令切换成CP模式：
>
> ​		curl -X PUT '$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP'

![nacos00](.\static\picture\nacos00.png)

> 设置虚拟映射启动：-DServer.port=19002

![nacos01](.\static\picture\nacos01.png)



**强一致性(C)、高可用(A)、分区容错性(P)**

![nacos02](.\static\picture\nacos02.png)



![nacos03](.\static\picture\nacos03.png)



#### 为什么配置文件需要 application.yml 和bootstrap.yml 两个

​		Nacos同Springcloud-config一样，在项目初始化时，要保证先从配置中心进行配置拉取，拉取配置之后，才能保证项目的正常启动，

​		Springboot中配置文件的加载是存在优先级顺序的，<font color = red >bootstrap优先级高于application</font>

###### Nacos中的dataid的组成格式与SpringBoot配置文件中的匹配规则



![nacos04](.\static\picture\nacos04.png)

![nacos05](.\static\picture\nacos05.png)



###### Namespace+Group+Data ID

![nacos06](.\static\picture\nacos06.png)



默认情况：

​				Namespace=public，Group=DEFAULT_GROUP，默认Cluster是DEFAULT

​		nacos默认的命名空间是public，Namespace主要是用来实现隔离。

​		Group默认是DEFAULT_GROUP，Group可以把不同的微服务划分到同一个分组里面去。

​		Service就是微服务；一个Service可以包含多个CLuster（集群），Nacos默认Cluster是DEFAULT，Cluster是对指定微服务的一个虚拟划分。

​		最后Instance，就是微服务的实例。

#### 集群版

 ![nacos07](.\static\picture\nacos07.png)

默认Nacos使用嵌入式数据库实现数据的存储。所以，如果启动多个默认配置下的Nacos节点，数据存储是存在一致性问题的。为了解决这个问题，Nacos采用了<font color = red>集中式存储的方式来支持集群化部署，目前支持MySQL的存储</font>    *Nacos默认使用其自带的嵌入式数据库Derby*

Nacos支持三种部署模式

- 单机模式 - 用于测试和单机试用
- 集群模式 - 用于生产环境，确保高可用
- 多集群模式 - 用于多数据中心场景

Windows

​	cmd starup.cmd 或者双击startup.cmd文件

linux

​	starup.sh

##### 单机模式配置持久化配置mysql

- 1、安装数据库，版本要求：5.6.5+
- 2、初始化mysql数据库，数据库初始化文件：conf/nacos-mysql.sql   
- 3、修改conf/application.properties文件，增加支持mysql数据源配置（目前只支持mysql），添加mysql数据源的 url、username、password
  -  (nacos-mysql.sql、application.properties存在于nacos-server的conf目录)

```properties
spring.datasource.platform=mysql

db.num=1
db.url=jdbc:mysql://192.168.1.104:3366/nacos_dev?useUnicode=true&characterEncoding=utf-8&useSSL=false&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=nacos_dev
db.password=123456
```

在以单机模式启动nacos，nacos所有写入嵌入式数据库的数据都写到了mysql

##### 集群版配置

> 一台linux虚拟机：nginx服务器，3个nacos服务，一个mysql数据库。

> nginx的安装参考之前学，使用 ContOs7 至少需要安装gcc库，不然无法编译安装【yum install gcc】

>  nacos下载linux版本的 tar.gz 包：https://github.com/alibaba/nacos/releases/download/1.1.4/nacos-server-1.1.4.tar.gz

> mysql root用户密码为 Dkf!!2020



1. 首先对 nacos 进行持久化操作，步骤类似单机版

2. 对集群进行梳理

   * 修改 nacos/conf 下的cluster文件，最好复制一份进行备份，添加如下内容:

   ```python
   # it is ip
   # example
   192.168.1.105:3333
   192.168.1.105:4444
   192.168.1.105:5555
   ```

3. 模拟三台nacos服务，编辑nacos的startup启动脚本，使其能够支持不同的端口启动多次

   + nacos/bin 目录下有startup.sh	单机版启动直接执行即可，但是集群启动，我们希望可以类似其他软件的shell命令，<font color = red>传递不同的端口号启动不同的nacos实例</font>。

   + 命令：./startup.sh -p 3333 就表示启动端口号为3333的nacos服务器实例，故需进行配置./startup.sh

     ```python
     #
     ```

     ![nacos08](.\static\picture\nacos08.png)



![nacos09](.\static\picture\nacos09.png)

4. Nginx的配置，由它作为负载均衡器

   + 修改nginx的配置文件

   /usr/local/nginx/conf/nigix.conf

   + nginx.conf

     ![nacos10](.\static\picture\nacos10.png)

   + 按照指定启动

     > ./nginx -c /usr/local/nginx/conf/nginx.conf

![nacos11.png](.\static\picture\nacos11.png)





## Sentinel

与Hystrix的对比

| Hystrix                                                      | Sentinel                   |
| ------------------------------------------------------------ | -------------------------- |
| 需要手动搭建监控平台                                         | 单独一个组件，可以独立出来 |
| 没有一套web界面可以给我们进行更加细粒度化的<br/>配置流控、速率控制、服务熔断、服务降级。。。 | 直接界面化的细粒度统一配置 |
|                                                              |                            |

#### 是什么：

​		Sentinel 是面向分布式服务架构的高可用流量防护组件，主要以流量为切入点，从限流、流量整形、熔断降级、系统负载保护、热点防护等多个维度来帮助开发者保障微服务的稳定性



#### 流控规则：

##### 解释说明

			+  资源名：唯一名称，默认请求路径
			+  针对来源：Sentinel可以针对调用者进行限流，填写微服务名，默认default（不区分来源）
   +  阈值类型/单机阈值
      			+  QPS（每秒钟的请求数量）：当调用该api的QPS达到阈值的时候，进行限流
         +  线程数：当调用该api的线程数达到阈值的时候，进行限流

+ 是否集群：不需要集群
+ 流控模式
  + 直接：api达到限流条件时，直接限流
  + 关联：当关联的资源达到阈值时，就限流自己
  + 链路：只记录指定链路上的流量（指定资源从入口资源进来的流量，如果达到阈值，就进行限流）【api级别的针对来源】

+ 流控效果：
  + 快速失败：直接失败，抛出异常
  + Warm Up：根据codeFactor（零加载因子，默认3）的值，从阈值/codeFactor，经过预热是时长，才达到设置的QPS阈值
  + 排队等待：匀速排队，让请求以匀速的速度通过，与值类型必修设置为QPS，否则无效



#### 降级规则

##### 解释说明

Sentinel的断路器是没有<font color="red">半开状态</font>的

+ RT(平均响应时间，秒级)
  + 平均响应时间	<font color="blue"> 超出阈值</font> <font color="red">且</font> <font color="blue">在时间窗口内通过的请求 >= 5</font>，两个条件同时满足后触发降级
  + 窗口期过后关闭断路器
  + RT最大4900（等大的需要通过 -Dcsp.sentinel.statistic.max.rt=XXXX才能生效）
  + ![sentinel00](.\static\picture\sentinel00.png)

+ 异常比例（秒级）
  + QPS >= 5 且异常比例（秒级统计）超过阈值时，触发降级；时间窗口结束后，关闭降级
  + ![sentinel01](\static\picture\sentinel01.png)
  + ![sentinel02](\static\picture\sentinel02.png)

+ 异常数（分钟级）
  + 异常数（分钟统计）超过阈值时，触发降级；时间窗口结束后，关闭降级。
  + ![sentinel03](\static\picture\sentinel03.png)
  + ![sentinel04](\static\picture\sentinel04.png)

​        Sentinel 熔断降级会在调用链路中某个资源出现不稳定状态时（例如调用超时或异常比例升高），对这个资源的调用进行限制，让请求快速失败，避免影响岛其他的资源而导致级联错误。

​		当资源被降级后，在接下来的降级时间窗口之内，对该资源的调用都自动熔断（默认行为是爆出DegradeException）。

#### 热点key限流

![sentinel05](static/picture/sentinel05.png?raw=true)

#### 系统自适应限流

##### 说明

​		Sentinel系统自适应限流从整体维度对应用入口流量进行控制，结合应用的Load、CPU使用率、总体平均RT、入口QPS和并发线程数等几个维度的监控指标，通过自适应的流控策略，让系统的入口流量和系统的负载达到一个平衡，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。

​		系统保护规则是应用整体维度的，而不是资源维度的，并且仅对入口流量生效。入口流量指的是进入应用的流量（EntryType.IN），比如Web服务或者Dubbo服务端接收的请求，都属于入口流量

##### 系统规则支持以下模式：

![sentinel06](static\picture\sentinel06.png?raw=true)

#### 自定义全局BlockHandler处理类

![sentinel07](static/picture/sentinel07.png?raw=true)

### sentinel整合ribbon+openFeign+fallback

#### Ribbon系列



#### Feign系列



#### 熔断框架比较

![sentinel08](static\picture\sentinel08.png)



#### sentinel规则持久化

就是在 sentinel 启动的时候，去 nacos 上读取相关规则配置信息，及将其相关的规则配置持久化到nacos上。

nacos上的配置说明：

```json
[
    {
        "resource": "/testA",
        "limitApp": "default",
        "grade": 1,
        "count": 1,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]

/**
* 
* resource: 资源名称
* limitApp:	来源应用
* grade: 阈值类型，0表示线程数，1表示QPS
* count: 单机阈值
* strategy: 流控模式。0表示直接，1表示关联，2表示链路
* controlBehavior: 流控效果，0表示快速失败，2表示warm Up,2表示排队等待
* clusterMode: 是否是集群
* 
*/
```



## Seata处理分布式事务

一次业务操作需要跨多个数据源或需要跨多个系统进行远程调用，就会产生分布式事务问题：

​		单体应用配拆分成微服务应用，原来的三个模块被拆分成为三个独立的应用，分别使用三个独立的数据源，业务操作需要调用三个服务来完成。此时每个服务内部的数据一致性由本地事务来保证，但是全局的数据一致性问题没法保证。

![seata00](static\picture\seata00.png)

一加三的套件

![seata01](static\picture\seata01.png)



![seata02](static\picture\seata02.png)

新建模块的部分相关配置：

（自己电脑上未解决seata连接数据库MySQL8.0.20的问题，故未作具体实现，替换了驱动包还是不好使）

新建模块 cloudalibaba-seata-order2001 ：

pom依赖：

```xml
	<dependencies>
        <!-- seata -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>seata-all</artifactId>
                    <groupId>io.seata</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-all</artifactId>
            <version>1.0.0</version>
        </dependency>
        <!-- springcloud alibaba nacos 依赖,Nacos Server 服务注册中心 -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <!-- open feign 服务调用 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <!-- springboot整合Web组件 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- 持久层支持 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>

        <!-- 日常通用jar包 -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.qing</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
```

yml配置：

```yaml
server:
  port: 2001
spring:
  application:
    name: seata-order-service
  cloud:
    alibaba:
      seata:
        # 自定义事务组，需要和当时在 seata/conf/file.conf 中的一致
        tx-service-group: dkf_tx_group
    nacos:
      discovery:
        server-addr: localhost:8848
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/seata_order
    username: root
    password: 123456


# 注意，这是自定义的，原来的是mapper_locations
mybatis:
  mapperLocations: classpath:mapper/*.xml

logging:
  level:
    io:
      seata: info
```

config （特殊点）:

```java
//下面是两个配置类，这个是和mybatis整合需要的配置
@Configuration
@MapperScan({"com.dkf.springcloud.alibaba.dao"})
public class MybatisConfig {
}


//这个是配置使用 seata 管理数据源，所以必须配置
package com.dkf.springcloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DataSourceProxyConfig {

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource){
        return new DataSourceProxy(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }
}
```

主启动类：

```java
//这里必须排除数据源自动配置，因为写了配置类，让 seata 管理数据源
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@EnableDiscoveryClient
public class SeataOrderMain2001 {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMain2001.class,args);
    }
}
```



#### Seata使用

```java
	@Override
	//只需要在业务类的方法上加上该注解，name值自定义唯一即可。
    @GlobalTransactional(name = "dkf-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("--------》 开始创建订单");
        orderDao.create(order);
        log.info("--------》 订单微服务开始调用库存，做扣减---Count-");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("--------》 订单微服务开始调用库存，库存扣减完成！！");
        log.info("--------》 订单微服务开始调用账户，账户扣减---money-");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--------》 订单微服务开始调用账户，账户扣减完成!!");
        //修改订单状态，从0到1
        log.info("--------》 订单微服务修改订单状态，start");
        orderDao.update(order.getUserId(),0);
        log.info("--------》 订单微服务修改订单状态，end");

        log.info("--订单结束--");
    }
```

![seata03](static\picture\seata03.png)





![seata04](static\picture\seata04.png)





### seata是什么

​		Seata是一款开源的分布式事务解决方案，致力于提供高性能和简单易用的分布式事务服务。Seata将为用户提供了AT、TCC、SAGA和XA事务模式。

#### AT模式

前提

+ 基于支持本地ACID事务的关系型数据库
+ Java应用，通过JDBC访问数据库

整体机制

+ 一阶段：业务数据和回滚日志记录在同一个本地事务种 提交，释放本地锁和连接资源
+ 二阶段
  - 提交异步化，非常快速的完成
  - 回滚通过一阶段的回滚日志进行反向补偿

在第一阶段，Seata会拦截“业务SQL”

1 解析SQL语义，找到“业务SQl”要更新的业务数据，在业务数据被更新前，将其保存成“before image”

2 执行“业务SQL”更新业务数据，在业务数据更新之后

3 其保存成“after image” 最后生成行锁

以上操作全部在一个数据库事务内完成，这样保证了一阶段操作的原子性



![seata05](static\picture\seata05.png)



![seata06](static\picture\seata06.png)



二阶段回滚：

二阶段如果是回滚的话，Seata就需要回滚一阶段已经执行的“业务SQL”，还原业务数据。

回滚方式便是用“before image”还原业务数据；但在还原之前要首先要校验脏写，对比“数据库当前业务数据”和“after image”，如果两份数据完全一致就说明没有脏写，可以还原业务数据，如果不一致就说明有脏写，出现脏写就需要转人工处理。

![seata07](static\picture\seata07.png)



![seata08](static\picture\seata08.png)





















































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

