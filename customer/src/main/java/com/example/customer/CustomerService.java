package com.example.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final WebClient webClient;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        String result = webClient
                .get()
                .uri("localhost:8099/api/blog")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // todo verify validation of email
        // todo verify if it's fraud

    }
}
