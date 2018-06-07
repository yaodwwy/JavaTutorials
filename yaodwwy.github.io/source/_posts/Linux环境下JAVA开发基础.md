---
title: Linux环境下JAVA开发基础
date: 2018-06-07 09:44:00
tag: Linux环境下JAVA开发基础
---
### Java 环境配置

#### 创建环境目录

    mkdir -m 755 /data
    
#### 下载Java包

    //避免用户验证方式下载
    wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" -c -P /data/ JDK链接 
    
   More info: [进入JAVA下载链接](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

#### 解压JDK包

    tar -zxvf jdk-10.0.1_linux-x64_bin.tar.gz
    
#### 配置JAVA环境变量

    vim /etc/profile.d/jdk.sh
    =======输入以下内容=======>>
    export JAVA_HOME=/data/jdk-10.0.1
    export PATH=$JAVA_HOME/bin:$PATH
    export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
    <<=========================
    source /etc/profile
    java -version

### Tomcat环境配置
    
#### 下载Tomcat并解压

    wget -c -P /data http://mirrors.tuna.tsinghua.edu.cn/apache/tomcat/tomcat-9/v9.0.7/bin/apache-tomcat-9.0.7.tar.gz
    tar -zxvf apache-tomcat-9.0.7.tar.gz

#### Tomcat启动及关闭：

    cd /
    ./data/tomcat/bin/startup.sh
    ./data/tomcat/bin/shutdown.sh
    
#### Tomcat界面管理员配置：

>user.xml(配置管理员用户名密码，用于管理/host-manager和/manager)：

    <role rolename="admin-gui"/>
        <role rolename="admin-script"/>
        <role rolename="manager-gui"/>
        <role rolename="manager-script"/>
        <role rolename="manager-jmx"/>
        <role rolename="manager-status"/>
        <user username="admin" password="sleep@10" 
        roles="admin-gui,admin-script,manager-gui,manager-script,manager-jmx,manager-status"/>
        
>context.xml(配置远程IP允许)：

    <Valve className="org.apache.catalina.valves.RemoteAddrValve"  
    allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1|\d+\.\d+\.\d+\.\d+" />  

#### Tomcat hot deploy 远程热部署支持(JRebel)：

    wget -c -P /data http://dl.zeroturnaround.com/jrebel-stable-nosetup.zip
    unzip jrebel-stable-nosetup.zip
    vim /data/tomcat/bin/catalina-jrebel.sh
    
    =======输入以下内容=======>>
    #!/bin/sh
    export REBEL_HOME="/data/jrebel"
    export CATALINA_PID="/data/tomcat/bin/catalina.pid"
    export JAVA_OPTS="-agentpath:$REBEL_HOME/lib/libjrebel64.so -Dspring.profiles.active=test -Drebel.remoting_plugin=true $JAVA_OPTS"
    `dirname $0`/startup.sh $@
    <<=========================
    
    ./data/tomcat/bin/catalina-jrebel.sh
    
>Notice: 首次部署会全部同步，缓存清理方式：rm -rf /root/.jrebel/cache/*

### WildFly(JBoss)环境配置

#### 下载WildFly并解压

    wget -c -P /data http://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.tar.gz
    tar -zxvf wildfly-10.1.0.Final.tar.gz

##### wildfly启动及关闭：

    cd /
    nohup ./data/standalone.sh &
    ./data/jboss-cli.sh --connect shutdown
