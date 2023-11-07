# Community
牛客论坛
## 项目环境
1. JDK-1.8
2. apache-maven-3.9.5
3. SpringBoot 2.7.17
4. MySQL 8.0.29
## 实现过程
### 发送邮件
使用spring-boot-starter-mail完成发送邮件功能，邮箱需要开启SMTP服务，并配置邮箱账号和登录密码。使用JavaMailSender实现邮件发送功能。
### 开发注册功能
在使用邮箱发送激活文件时，由于数据库和激活邮箱的配置参数在application.properties配置文件里，不安全。因此，我把配置参数写在另外的文件中，此文件不受git管理。最终实现了重要信息屏蔽。
### 生成验证码
采用了Kaptcha包，来随机生成验证码
## 学习使用
本项目仅作为学习使用