package com.nowcoder.community.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Properties;

@Component
public class MailConfig {

    @Value("${mailsource.config-path}")
    private String configPath;

    @Bean
    @SneakyThrows
    public JavaMailSender mailSender() {
        String config = FileUtils.readFileToString(new File(configPath + "/mail.json"), Charset.defaultCharset());
        JSONObject configJson = JSON.parseObject(config);
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(configJson.getString("host"));
        javaMailSender.setPort(configJson.getInteger("port"));
        javaMailSender.setUsername(configJson.getString("username"));
        javaMailSender.setPassword(configJson.getString("password"));
        javaMailSender.setProtocol("smtps");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.ssl.enable","true");
        return javaMailSender;
    }
}
