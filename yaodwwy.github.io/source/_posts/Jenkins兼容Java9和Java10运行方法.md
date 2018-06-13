---
title: Jenkins兼容Java9和Java10运行方法
date: 2018-06-13 09:23:00
tag: 
---
正常运行Jenkins是 `java -jar jenkins.war` 即可，但是环境要求是JDK8的版本。
如果想在更高版本上运行会提示一些环境错误信息。

Jenkins项目目前需要Java 8可靠地运行，并且在更高的Java版本中存在一些已知的兼容性问题。

在新的每周发布版本中，做了更高版本的支持：

Jenkins 2.127 下载地址：
http://mirrors.jenkins.io/war/latest/jenkins.war

>Java9/10+下的运行方式：

    java --illegal-access=debug --add-modules java.xml.bind -jar jenkins.war --enable-future-java

[参考文献](https://jenkins.io/blog/2018/06/08/jenkins-java10-hackathon/)