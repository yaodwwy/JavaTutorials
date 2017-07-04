###linux java 环境变量

filePath : /etc/profile 


export JAVA_HOME=/home/adam/java/jdk1.8.0_101/
export PATH=$JAVA_HOME/bin:$PATH 
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar 

GRADLE_HOME=/home/adam/java/gradle-3.1
export PATH=$PATH:$GRADLE_HOME/bin 



filePath: /etc/hosts

127.0.0.1		x.service.gomro.cn
127.0.0.1		x.pm.gomro.cn
127.0.0.1		x.login.gomro.cn
10.0.3.13		x.gomro.cn

###chrome通过file协议浏览

"C:\Program Files\Google\Chrome\Application\chrome.exe" --enable-file-cookies --allow-file-access-from-files

###联通ePico参数
IPV4：172.16.1.2
255.255.255.0
配置地址：https://172.16.1.1
用户名：admin
密码：shlt-123
Provision SEGW URL
	联通：112.64.46.132
	电信：210.13.112.166
	

###wildfly启动：
./jboss-cli.sh --connect shutdown
nohup ./standalone.sh &

###MarkdownPro解锁密钥

amped@gmail.com

zxuCUpEKSnR1h3mCvC0aGTKMUcWth2Di2DqhKM7tnYogIFFqqaPDXDEw2GSTq6Y+qdYDtbDJ0XQ1RIWWLtskM0dxXNX7pQw5dug+8GTaIDJIvTLJzcRzOz/e3BSWmv+CykSYfbEfEHtibME8y/mxcWhCZLPQJzgBK3heKIWCQKr26SNCY5jRVOTYVeeFbOZPUfwRnILMqa7cTEyakR6lkaajq9DqUlh65QUIyeC9Pc87ZLT1pquT/v00hE/sWOmqwouzC8aGNPDosvCzl/3/1UscZekjCAcx0ZPw72VNz8VAaSFA1CBjF1EOCpl+zw55EgyJnhDk52LYRbkl8wrmvw==