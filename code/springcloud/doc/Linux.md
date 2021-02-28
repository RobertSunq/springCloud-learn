## 命令记录











## 问题总结



#### 给普通用户添加sudo权限

```python
# 切换到root用户下（直接输入：su，然后输入密码）
[root@centos /] su
# /etc/sudoers文件默认是只读的，对root来说也是，因此需先添加sudoers文件的写权限,
[root@centos /] chmod u+w /etc/sudoers
# 编辑sudoers文件
[root@centos /] vi /etc/sudoers
# 找到这行 root ALL=(ALL) ALL,在他下面添加xxx ALL=(ALL) ALL (这里的xxx是用户名)
"""
youuser ALL=(ALL) ALL				--->	允许用户youuser执行sudo命令(需要输入密码).
%youuser ALL=(ALL) ALL				--->	允许用户组youuser里面的用户执行sudo命令(需要输入密码).
youuser ALL=(ALL) NOPASSWD: ALL		--->	允许用户youuser执行sudo命令,并且在执行的时候不输入密码.
%youuser ALL=(ALL) NOPASSWD: ALL	--->	允许用户组youuser里面的用户执行sudo命令,并且在执行的时候不输入密码.
"""
# 撤销sudoers文件写权限:
[root@centos /] chmod u-w /etc/sudoers
```



#### 在CentOS7 上安装Zookeeper

1. 创建安装需要的文件夹

   $	mkdir -p /myself/cs/services/zookeeper

2. 进入到该目录中
   $	cd /myself/cs/services/zookeeper

3. 下载zookerper-3.4.9.tar.gz
   $	wget https://archive.apache.org/dist/zookeeper/zookeeper-3.4.9/zookeeper-3.4.9.tar.gz

4. 解压缩zookerper-3.4.9.tar.gz
   $	tar -zxvf zookeeper-3.4.9.tar.gz

5. 进入到/myself/cs/services/zookeeper/zookerper-3.4.9/conf目录中
   $	cd ./zookeeper-3.4.9/conf/

6. 复制zoo_sample.cfg 文件的并命名为为 zoo.cfg
   $	cp zoo_sample.cfg zoo.cfg

7. 用vim打开zoo.cfg文件并修改为

   ```python
   # The number of milliseconds of each tick
   tickTime=2000
   # The number of ticks that the initial 
   # synchronization phase can take
   initLimit=10
   # The number of ticks that can pass between 
   # sending a request and getting an acknowledgement
   syncLimit=5
   # the directory where the snapshot is stored.
   # do not use /tmp for storage, /tmp here is just 
   # example sakes.
   # 数据文件夹
   dataDir=/myself/cs/services/zookeeper/zokkerper-3.4.9/data
   # 日志文件夹
   dataLogDir=/myself/cs/services/zookeeper/zokkerper-3.4.9/logs
   # the port at which the clients will connect
   # 客户端访问端口
   clientPort=2181
   # the maximum number of client connections.
   # increase this if you need to handle more clients
   #maxClientCnxns=60
   #
   # Be sure to read the maintenance section of the 
   # administrator guide before turning on autopurge.
   #
   # http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
   #
   # The number of snapshots to retain in dataDir
   #autopurge.snapRetainCount=3
   # Purge task interval in hours
   # Set to "0" to disable auto purge feature
   #autopurge.purgeInterval=1
   
   ```

   

8. 保存并关闭 zoo.cfg 文件
   

9. 进入到/myself/cs/services/zookeeper/zookerper-3.4.9/bin
   $	cd ../bin/

10. 用vim打开配置文件 /etc/profile
    $	vim /etc/profile

11. 并在其尾部追加如下内容

    ```python
    #idea - zookeeper-3.4.9 config start - 2017-12-09
    export ZOOKEEPER_HOME=/myself/cs/services/zookeeper/zookeeper-3.4.9/
    export PATH=$ZOOKEEPER_HOME/bin:$PATH
    export PATH
    #idea - zookeeper-3.4.9 config end - 2017-12-09
    ```

12. 使 /etc/profile 文件即可生效
    $	source /etc/profile

13. 启动 zookeeper 服务
    $	zkServer.sh start

14. 查询 zookeeper 状态
    $	zkServer.sh status

15. 关闭 zookeeper 服务
    $	zkServer.sh stop

16. 重启 zookeeper 服务

    $	zkServer.sh restart





<h4>在CentOS7 关闭防火墙</h4>

```python
#  临时关闭
$	sudo systemctl stop firewalld
#   然后reboot 永久关闭
$	sudo systemctl disable firewalld
#   查看防火墙状态
$	sudo systemctl status  firewalld



#   查看已经开放的端口：
firewall-cmd --list-ports
#   开启端口
firewall-cmd --zone=public --add-port=80/tcp --permanent
'''命令含义：
–zone #作用域
–add-port=80/tcp #添加端口，格式为：端口/通讯协议
–permanent #永久生效，没有此参数重启后失效
'''

#   重启防火墙
firewall-cmd --reload #重启firewall
systemctl stop firewalld.service #停止firewall
systemctl disable firewalld.service #禁止firewall开机启动
firewall-cmd --state #查看默认防火墙状态（关闭后显示notrunning，开启后显示running）
```













[^说明]: 部分资料来源于网络

