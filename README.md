# EmailUserAgent
SCU Computer Networking Course Project

## 一．概述

*配置环境与工具：*

Microsoft Win10； Java （jdk8）；Vue3

*核心依赖：*

java.net.Socket, java.net.ServerSocket, java.lang.Thread

*大概思路：*

![img](file:///G:\Temp\ksohtml11680\wps1.jpg) 

### 二.使用说明

#### 服务启动方法：

##### 通过源码启动Email User Agent Server：

![img](file:///G:\Temp\ksohtml11680\wps2.jpg) 

#### 通过class文件启动Email User Agent Server：

java EmailUserAgentServer

![img](file:///G:\Temp\ksohtml11680\wps3.jpg) 

得到该命令行提示则说明Email User Agent启动成功

 

#### 加载Web 资源：

npm run serve 或者 部署打包好后的静态资源至tomcat/nginx等服务器。

![img](file:///G:\Temp\ksohtml11680\wps4.jpg) 



### 用户使用方法：

访问Web资源，即打开使用界面；

![img](file:///G:\Temp\ksohtml11680\wps5.jpg) 

输入要登录的邮箱和授权码（注：所登录的qq和163邮箱的smtp和pop3服务要打开，否则将会导致后续的操作出现异常）

#### 发送邮件：

![img](file:///G:\Temp\ksohtml11680\wps6.jpg) 

输入内容

![img](file:///G:\Temp\ksohtml11680\wps7.jpg)![img](file:///G:\Temp\ksohtml11680\wps8.jpg) 

点击确认

![img](file:///G:\Temp\ksohtml11680\wps9.jpg)![img](file:///G:\Temp\ksohtml11680\wps10.jpg) 

代理服务器收到HTTP请求，并利用SMTP将邮件发送至目标邮箱

![img](file:///G:\Temp\ksohtml11680\wps11.jpg) 

#### 查看邮件：

登录另一个邮箱则可以看到发出的邮件：

![img](file:///G:\Temp\ksohtml11680\wps12.jpg) 

![img](file:///G:\Temp\ksohtml11680\wps13.jpg) 

邮件过长时只会显示一部分，鼠标移至“内容”上则显示全部内容。

![img](file:///G:\Temp\ksohtml11680\wps14.jpg) 

 

#### 删除邮件：

选择要删除的邮件并点击“删除邮件”，确认后，则成功删除

![img](file:///G:\Temp\ksohtml11680\wps15.jpg) 

![img](file:///G:\Temp\ksohtml11680\wps16.jpg) 

![img](file:///G:\Temp\ksohtml11680\wps17.jpg) 

![img](file:///G:\Temp\ksohtml11680\wps18.jpg) 

![img](file:///G:\Temp\ksohtml11680\wps19.jpg) 