package com.illimity.mostafa.controller;

import com.illimity.mostafa.movements.AccountMovements;
import com.illimity.mostafa.services.AccountMovementsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/movements")
public class AccountMovementsController {
    private final AccountMovementsService accountMovementsService;
    public AccountMovementsController(AccountMovementsService accountMovementsService) {
        this.accountMovementsService = accountMovementsService;
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getTenLastMovementsOrderByDate(@PathVariable String customerId)
            throws Exception {
        log.info("Customer ID: "+ customerId);
        List<AccountMovements> accountMovementsList =
                accountMovementsService.getTenLastMovementsOrderByDateService(customerId);
        return new ResponseEntity<>(accountMovementsList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Object> createAccountMovements(@RequestBody AccountMovements accountMovements)
            throws Exception {
        URI location = accountMovementsService
                .createAccountMovementsService(accountMovements);
        return ResponseEntity.created(location).build();
    }
}
