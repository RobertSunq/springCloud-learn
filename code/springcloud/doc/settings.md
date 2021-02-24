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





