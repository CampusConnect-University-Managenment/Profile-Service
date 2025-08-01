package com.example.profileservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    private final String FROM_EMAIL = "academixofficialmngt@gmail.com";
    private final String UNIVERSITY_DOMAIN = "@university.edu";

    public void sendStudentCredentials(String personalEmail, String officialEmail, String password) {
        String subject = "Your Student Login Credentials";
        String body = String.format(
                "Dear Student,\n\nWelcome to our university!\n\nHere are your login credentials:\n" +
                        "Official Email: %s\nPassword: %s\n\nPlease use these credentials to log in to the student portal.\n\nRegards,\nUniversity Admin",
                officialEmail, password
        );
        sendEmail(personalEmail, subject, body);
    }

    public void sendFacultyCredentials(String personalEmail, String facultyCode, String password) {
        String officialEmail = facultyCode + UNIVERSITY_DOMAIN;

        String subject = "Your Faculty Login Credentials";
        String body = String.format(
                "Dear Faculty,\n\nWelcome to our university!\n\nHere are your login credentials:\n" +
                        "Official Email: %s\nPassword: %s\n\nPlease use these credentials to log in to the faculty portal.\n\nRegards,\nUniversity Admin",
                officialEmail, password
        );

        sendEmail(personalEmail, subject, body);
    }

    private void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(FROM_EMAIL);

        emailSender.send(message);
    }
}
