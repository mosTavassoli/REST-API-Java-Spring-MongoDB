package com.illimity.mostafa.controller;

import com.illimity.mostafa.customers.Customers;
import com.illimity.mostafa.services.CustomersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;
import java.security.NoSuchAlgorithmException;

@Controller
@Slf4j
@RequestMapping("/register")
public class CustomersRegistrationController {
    private final CustomersService customersService;
    public CustomersRegistrationController(CustomersService customersService) {
        this.customersService = customersService;
    }
    @GetMapping
    public String registerForm() {
        return "registration";
    }
    @PostMapping
    public ResponseEntity<Object> processRegistration(@Valid @RequestBody Customers customer)
            throws NoSuchAlgorithmException {
        log.info(customer.toString());
        URI location = customersService.processRegistration(customer);
        return ResponseEntity.created(location).build();
    }
}
