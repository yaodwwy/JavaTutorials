---
title: CentOS 7 Git仓库及用户操作
date: 2018-06-07 09:54:00
tag: Git
---

## Git仓库管理及常用操作

### 创建仓库：

    $ git init --bare
   

>passwd文件同目录操作：

    htpasswd命令是Apache的Web服务器内置工具，用于创建和更新储存用户名、域和用户基本认证的密码文件。 
    -c：创建一个加密文件； 
    -n：不更新加密文件，只将加密后的用户名密码显示在屏幕上； 
    -m：默认采用MD5算法对密码进行加密； 
    -d：采用CRYPT算法对密码进行加密； 
    -p：不对密码进行进行加密，即明文密码； 
    -s：采用SHA算法对密码进行加密； 
    -b：在命令行中一并输入用户名和密码而不是根据提示输入密码； 
    -D：删除指定的用户。
    
### 创建用户：

    $ htpasswd -d passwd 用户名
    
### 删除用户：

    $ htpasswd -D passwd customizeName

### 克隆项目：
    $ mkdir /data/myProject
    $ cd /data/myProject/
    $ git clone https://github.com/spring-cloud-samples/sso.git


### 提交项目：

    $ git config --global user.name 用户名
    $ git config --global user.email 邮箱
    $ git config --list 
    $ git commit -m 'comments here' //把stage中的修改提交到本地库
    $ git push //把本地库的修改提交到远程库中  
   