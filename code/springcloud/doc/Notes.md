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

