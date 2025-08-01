package com.example.profileservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
    @Configuration
    public class EmailConfig {

        @Bean
        public JavaMailSender javaMailSender() {
            JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
            emailSender.setHost("smtp.gmail.com");
            emailSender.setPort(587);
            emailSender.setUsername("academixofficialmngt@gmail.com");
            emailSender.setPassword("eyjavfbwfvjbqrln");

            Properties props = emailSender.getJavaMailProperties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");

            return emailSender;
        }
    }

