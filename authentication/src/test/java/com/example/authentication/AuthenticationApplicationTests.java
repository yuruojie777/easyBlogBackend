package com.example.authentication;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@SpringBootTest
@Slf4j
class AuthenticationApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        HashMap obj = WebClient.builder()
                .baseUrl("http://localhost:8090/api/v1/customer")
                .build()
                .get()
                .uri("/users/email?email=" + "poco@qq.com")
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();

        log.info(obj.get("id").toString());
    }

}
