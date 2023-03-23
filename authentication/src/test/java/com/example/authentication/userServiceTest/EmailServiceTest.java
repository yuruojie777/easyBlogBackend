package com.example.authentication.userServiceTest;

import com.example.authentication.service.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
    public void testSendingEmail() throws MessagingException, IOException {
//        emailService.send("yuruojie111@qq.com", "yuruojie777@gmail.com");
    }
}
