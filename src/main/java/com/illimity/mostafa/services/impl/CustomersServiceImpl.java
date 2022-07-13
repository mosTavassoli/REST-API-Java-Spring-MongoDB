package com.illimity.mostafa.services.impl;


import com.illimity.mostafa.customers.Customers;
import com.illimity.mostafa.customers.CustomersRepository;
import com.illimity.mostafa.customers.Status;
import com.illimity.mostafa.exception.CustomerNotFoundException;
import com.illimity.mostafa.exception.CustomerPasswordException;
import com.illimity.mostafa.exception.CustomerStatusException;
import com.illimity.mostafa.exception.CustomerUsernameHasTaken;
import com.illimity.mostafa.security.PasswordEncoder;
import com.illimity.mostafa.services.CustomersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;


@Service
@Slf4j
public class CustomersServiceImpl implements CustomersService {
    private final CustomersRepository customersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomersServiceImpl(CustomersRepository customersRepository, PasswordEncoder passwordEncoder) {
        this.customersRepository = customersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customers processLoginCustomerService(Customers customerInput) throws NoSuchAlgorithmException {

        Customers customer = customersRepository.findCustomersByUsername(customerInput.getUsername());
        if (customer == null) {
            throw new CustomerNotFoundException("Username " + customerInput.getUsername() + " Not Found");
        }
        if (!passwordEncoder.matches(customerInput.getPassword(), customer.getPassword())) {
            throw new CustomerPasswordException("Wrong Password");
        } else if (!customer.getStatus().equals(Status.ACTIVE)) {
            throw new CustomerStatusException("The Username is not ACTIVE");
        } else {
            log.info(customer.toString());
            customer.setLastLoginDate(LocalDateTime.now());
            customersRepository.save(customer);
            return new Customers(
                    customer.getFiscalcode(),
                    customer.getName(),
                    customer.getSurname(),
                    customer.getPhoneNumber(),
                    customer.getStatus(),
                    customer.getEmail()
            );
        }
    }

    public URI processRegistration(Customers customer) throws NoSuchAlgorithmException {
        if (customersRepository.findCustomersByUsername(customer.getUsername()) != null) {
            throw new CustomerUsernameHasTaken("The Username already exists");
        } else {
            customer.setCreatedDate(LocalDateTime.now());
            Customers saveCustomer = customersRepository.insert(customer.toCustomers(passwordEncoder));
            return ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saveCustomer.getUsername()).toUri();
        }
    }


}
