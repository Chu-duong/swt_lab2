package com.example.happyprogramingbackend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlEmail(String email, String name, Long id) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("Test email from Spring");
        String htmlContent = "<h1>Hello " + name + "</h1>"
                + "<h1>Confirm account: " + email + " from Happy Programing</h1>"
                + "<a href='http://localhost:8080/verify/" + id + "'>Click here to confirm infomation</a>";

        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    public void sendResetPassword(String email,String name, String password) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("Test email from Spring");
        String htmlContent = "<h1>Hello " + name + "</h1>"
                + "<h1>Your password of account: " + email + "is: "+password+"</h1>"
                + "<a href='http://localhost:3000/login'>Click here to login</a>";

        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }
}