package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailConfig {
    @Bean
    public JavaMailSender mailSender() {
        return new JavaMailSenderImpl();
    }
}
