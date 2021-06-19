# EmailUserAgent
SCU Computer Networking Course Project

## 一．概述

*配置环境与工具：*

Microsoft Win10； Java （jdk8）；Vue3

*核心依赖：*

java.net.Socket, java.net.ServerSocket, java.lang.Thread

*大概思路：*

![1624109338362](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109338362.png)

### 二.使用说明

#### 服务启动方法：

##### 通过源码启动Email User Agent Server：

![1624109354039](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109354039.png) 

#### 通过class文件启动Email User Agent Server：

java EmailUserAgentServer

![1624109371104](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109371104.png) 

得到该命令行提示则说明Email User Agent启动成功

 

#### 加载Web 资源：

npm run serve 或者 部署打包好后的静态资源至tomcat/nginx等服务器。

![1624109375842](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109375842.png) 



### 用户使用方法：

访问Web资源，即打开使用界面；

![1624109381571](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109381571.png) 

输入要登录的邮箱和授权码（注：所登录的qq和163邮箱的smtp和pop3服务要打开，否则将会导致后续的操作出现异常）

#### 发送邮件：

![1624109389910](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109389910.png) 

输入内容

![img](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109395814.png) ![1624109405902](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109405902.png)

点击确认

![img](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109417269.png) ![1624109421775](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109421775.png)

代理服务器收到HTTP请求，并利用SMTP将邮件发送至目标邮箱

![1624109428094](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109428094.png) 

#### 查看邮件：

登录另一个邮箱则可以看到发出的邮件：

![1624109433579](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109433579.png) 

![1624109439082](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109439082.png) 

邮件过长时只会显示一部分，鼠标移至“内容”上则显示全部内容。

![1624109447557](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109447557.png) 

 

#### 删除邮件：

选择要删除的邮件并点击“删除邮件”，确认后，则成功删除

![1624109454835](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109454835.png) 

![1624109458874](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109458874.png) 

![1624109463272](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109463272.png) 

![1624109468234](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109468234.png) 

![1624109472867](https://github.com/MuZi-lh/EmailUserAgent/img/README/1624109472867.png) 