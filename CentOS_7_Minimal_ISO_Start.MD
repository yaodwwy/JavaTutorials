#CentOS 7 Minimal ISO Start
## 1、安装：略
## 2、网卡激活
    vi /etc/sysconfig/network-scripts/ifcfg-enp0s3
    改 ONBOOT=yes
    重启网卡： service network restart
    ip addr  查看分配网卡情况

## 3、安装ifconfig功能
    yum -y install net-tools
## 4、虚拟机端口转发：
    查看本地环境中的虚拟网卡ip 【主机ip】
    查看虚拟机系统中的网络ip【子系统ip】
    设置虚拟机软件端口转发 主机到子系统
## 5、windows 端口转发到虚拟机
    netsh interface portproxy add v4tov4 listenaddress=10.0.3.123 listenport=22 connectaddress=192.168.56.1 connectport=22
    依次为【windows ip】 【windows 端口】 【虚拟网卡ip】【虚拟网卡端口】
    netsh interface portproxy show  v4tov4
    取消转发：
    netsh interface portproxy delete v4tov4 listenaddress=【windows ip】  listenport=【windows 端口】
## 6、完成以上设置即可从路由向电脑转发对应的ssh请求。
## 7、开放端口
### 开启端口
    firewall-cmd --zone=public --add-port=80/tcp --permanent
    命令含义：
    --zone #作用域 
    --add-port=80/tcp  #添加端口，格式为：端口/通讯协议
    --permanent  #永久生效，没有此参数重启后失效
### 重启防火墙
    firewall-cmd --reload
### 查看端口
    firewall-cmd --list-ports