package com.nowcoder.community.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;

@Component
public class MailSourceConfigLoader implements BeanPostProcessor, EnvironmentAware {

    private ConfigurableEnvironment environment;

    @Value("${mailsource.config-path}")
    private String configPath;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof JavaMailSenderImpl) {
            String config = FileUtils.readFileToString(new File(configPath + "/mail.json"), Charset.defaultCharset());
            JSONObject configJson = JSON.parseObject(config);
            environment.getSystemProperties().put("spring.mail.host", configJson.getString("host"));
            environment.getSystemProperties().put("spring.mail.port", configJson.getShort("port"));
            environment.getSystemProperties().put("spring.mail.username", configJson.getString("username"));
            environment.getSystemProperties().put("spring.mail.password", configJson.getString("password"));
            environment.getSystemProperties().put("spring.mail.protocol", "smtps");
            environment.getSystemProperties().put("spring.mail.properties.mail.smtp.ssl.enable", true);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
