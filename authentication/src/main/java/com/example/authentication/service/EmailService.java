package com.example.authentication.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class EmailService {

//    private final JavaMailSender mailSender;
//
//    @Async
//    public void send(String to, String from){
//        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
//        try {
//            mimeMailMessage.setFrom(from);
//            mimeMailMessage.setText("Hello from test!");
//            mimeMailMessage.setRecipients(Message.RecipientType.TO, to);
//        } catch (MessagingException e) {
//            log.error("Failed to send email to {}", to);
//            e.printStackTrace();
//            throw new IllegalArgumentException("Failed to send email");
//        }
//
//    }
}
