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



### cneos7上启动zookeeper

***注：默认临时节点***

```python
# 进入到zookeeper安装目录的bin文件
# 关闭防火墙
$ 	systemctl stop firewalld
# 启动zookeeper
$	./zkServer.sh start
$	./zkCli.sh
# 查看状态
[zk: localhost:2181(CONNECTED) 1] 	ls /
[zk: localhost:2181(CONNECTED) 1] 	get /zookeeper
# 查看服务
[zk: localhost:2181(CONNECTED) 1] 	ls /zookeeper
[zk: localhost:2181(CONNECTED) 1]	ls /services
[zk: localhost:2181(CONNECTED) 1]	ls /services/cloud-provider-payment
```

![zookeeper00](.\static\picture\zookeeper00.png)





## GitHub

```python
git config http.sslVerify "false"  # 忽略SSL证书错误
```

