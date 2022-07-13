package com.illimity.mostafa.services.impl;

import com.illimity.mostafa.customers.Customers;
import com.illimity.mostafa.customers.CustomersRepository;
import com.illimity.mostafa.exception.CustomerNotFoundException;
import com.illimity.mostafa.movements.AccountMovements;
import com.illimity.mostafa.movements.AccountMovementsRepository;
import com.illimity.mostafa.services.AccountMovementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountMovementsServiceImpl implements AccountMovementsService {
    private final AccountMovementsRepository accountMovementsRepository;
    private final CustomersRepository customersRepository;

    @Autowired
    public AccountMovementsServiceImpl(AccountMovementsRepository accountMovementsRepository,
                                       CustomersRepository customersRepository) {
        this.accountMovementsRepository = accountMovementsRepository;
        this.customersRepository = customersRepository;
    }

    public List<AccountMovements> getTenLastMovementsOrderByDateService(String customerId) throws Exception {
        try {
            Optional<Customers> customerExists = customersRepository.findById(customerId);
            if (customerExists.isPresent()) {
                return accountMovementsRepository.
                        getTenLastMovementsOrderByDate(customerId, PageRequest.of(0, 10));
            } else {
                throw new CustomerNotFoundException("The Customer Id does not Exist");
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public URI createAccountMovementsService(AccountMovements accountMovements) throws Exception {
        try {
            Optional<Customers> customerExists = customersRepository.findById(accountMovements.getCustomerId());
            if (customerExists.isPresent()) {
                accountMovements.setDate(LocalDateTime.now());
                AccountMovements saveAccountMovements = accountMovementsRepository.insert(accountMovements);
                return ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(saveAccountMovements.getMovementId()).toUri();
            } else {
                throw new CustomerNotFoundException("The Customer Id does not Exist");
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
