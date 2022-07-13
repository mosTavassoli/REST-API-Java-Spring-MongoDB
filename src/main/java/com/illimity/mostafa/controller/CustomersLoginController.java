package com.illimity.mostafa.controller;

import com.illimity.mostafa.customers.Customers;
import com.illimity.mostafa.services.CustomersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/login")
public class CustomersLoginController {
    private final CustomersService customersService;
    public CustomersLoginController(CustomersService customersService) {
        this.customersService = customersService;
    }
    @PostMapping
    public ResponseEntity<Customers> processLoginCustomer(@RequestBody Customers customer)
            throws Exception {
        log.info(customer.toString());
        Customers Customers = customersService.processLoginCustomerService(customer);
        return new ResponseEntity<>(Customers, HttpStatus.OK);
    }
}