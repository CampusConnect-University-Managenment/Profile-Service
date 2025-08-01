package com.example.profileservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendStudentCredentials(String personalEmail, String officialEmail, String password) {
        String subject = "Your Student Login Credentials";
        String body = String.format(
                "Dear Student,\n\nWelcome to our university!\n\nHere are your login credentials:\n" +
                        "Official Email: %s\nPassword: %s\n\nPlease use these credentials to log in to the student portal.\n\nRegards,\nUniversity Admin",
                officialEmail, password
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(personalEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("academixofficialmngt@gmail.com");

        emailSender.send(message);
    }
}
