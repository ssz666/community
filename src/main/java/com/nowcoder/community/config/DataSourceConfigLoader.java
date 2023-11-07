package com.nowcoder.community.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;

@Component
public class DataSourceConfigLoader implements BeanPostProcessor, EnvironmentAware {

    private ConfigurableEnvironment environment;

    @Value("${datasource.config-path}")
    private String configPath;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MybatisAutoConfiguration) {
            String config = FileUtils.readFileToString(new File(configPath + "/db.json"), Charset.defaultCharset());
            JSONObject configJson = JSON.parseObject(config);
            environment.getSystemProperties().put("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
            environment.getSystemProperties().put("spring.datasource.type", "com.zaxxer.hikari.HikariDataSource");
            environment.getSystemProperties().put("spring.datasource.hikari.maximum-pool-size", 15);
            environment.getSystemProperties().put("spring.datasource.hikari.minimum-idle", 5);
            environment.getSystemProperties().put("spring.datasource.hikari.idle-timeout", 30000);
            environment.getSystemProperties().put("spring.datasource.url", configJson.getString("url"));
            environment.getSystemProperties().put("spring.datasource.username", configJson.getString("username"));
            environment.getSystemProperties().put("spring.datasource.password", configJson.getString("password"));
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
