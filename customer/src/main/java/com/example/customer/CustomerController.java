package com.example.customer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
public record CustomerController(CustomerService customerService) {

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("index here");
    }
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
//        customerService.registerCustomer(customerRegistrationRequest);
    }
}
