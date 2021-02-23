### 2020年解决：IDEA中已配置阿里镜像，但maven无法下载jar包的问题

由于阿里镜像仓库不再支持http请求，故需要修改相关配置为：

```xml
<mirror>
    <id>aliyunmaven</id>
    <mirrorOf>*</mirrorOf>
    <name>阿里云公共仓库</name>
    <url>https://maven.aliyun.com/repository/public</url>
</mirror>
```

同时由于使用https请求的原因，存在SSL证书的验证问题，所以在IDEA中需要增添配置

![maven-importing](.\static\picture\maven-importing.png)

配置如下：

```python
-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
```











[^说明]: 部分资料来源于网络

