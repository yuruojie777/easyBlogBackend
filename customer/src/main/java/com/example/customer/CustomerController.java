package com.example.customer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CustomerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public ResponseEntity<String> index() {
        kafkaTemplate.send("testTopic", "hello");
        return ResponseEntity.ok("index here");
    }
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
//        customerService.registerCustomer(customerRegistrationRequest);
    }
}
