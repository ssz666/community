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
### 开发登录功能
开发登录功能时，使用ticket确定登录的用户，数据库维护一张登录用户表，记录用户的登录状态。并且ticket以cookie的形式传给浏览器，最后退出登录也是根据ticket判断退出的用户。
### 显示登录信息
在登录成功后，服务器会将ticket装入cookie一并发给浏览器，并使浏览器重新请求/index网页，此时请求的cookie会带上ticket，因此拦截器会判断cookie中是否包含ticket，展示不同的页面头部。
### 实现修改头像功能
通过SpringMVC提供的MultipartFile接口，可以实现文件上传功能。
### 实现修改密码功能（课后作业）
修改密码功能需要输入三个值，原密码，新密码，确认新密码
原密码在service层判空和正确性校验
新密码也在service层判空以及是否和原密码一样
确认新密码在前端检验与新密码是否一致，通过js判断
最终在数据库中完成修改
### 检查登录状态
由于有些页面需要登录才能访问，因此自定义注解LoginRequired，在controller层对于有些访问路径使用@LoginRequired注解，在拦截器中判断cookie中是否包含ticket，如果包含则放行，否则跳转到登录页面。
## 学习使用
本项目仅作为学习使用