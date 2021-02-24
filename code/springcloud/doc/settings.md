### Eureka集群部署设置

eureka集群的原理：相互注册、相互守望

在同一台主机模拟多个Eureka Server 再不同主机上运行时，需要修改主机的hosts文件。(地址：C:\Windows\System32\drivers\etc\hosts)

```python
#################### springcloud-learn ####################
# 模拟三台服务器
127.0.0.1	eureka17001.com
127.0.0.1	eureka17002.com
127.0.0.1	eureka17003.com
```



两个不同端口号上的yml文件也要进行对应修改：

17001端口服务的application.yml文件

```yml
server:
  port: 17001

eureka:
  instance:
    hostname: eureka17001.com  # eureka 服务器的实例地址

  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
    ## 一定要注意这里的地址，这是搭建集群的关键
      defaultZone: http://eureka17002.com:17002/eureka/
```



17002端口服务的application.yml文件

```yml
server:
  port: 17001

eureka:
  instance:
    hostname: eureka17002.com  # eureka 服务器的实例地址

  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
    ## 一定要注意这里的地址，这是搭建集群的关键
      defaultZone: http://eureka17001.com:17001/eureka/
```

