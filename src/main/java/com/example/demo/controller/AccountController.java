package com.example.demo.controller;

import com.example.demo.entity.AccountEntity;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Endpoint to retrieve account information by userId
    @GetMapping("/{userId}")
    public AccountEntity getAccountByUserId(@PathVariable Long userId) {
        return accountService.getAccountByUserId(userId);
    }

    // Endpoint to create an account for a user (if needed)
    @PostMapping("/create")
    public AccountEntity createAccount(@RequestParam Long userId, @RequestParam(required = false) Double initialBalance) {
        return accountService.createAccount(userId, initialBalance);
    }
}
