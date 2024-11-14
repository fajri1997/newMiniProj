package com.example.demo.service;

import com.example.demo.entity.AccountEntity;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountEntity createAccount(Long userId, Double initialBalance) {
        AccountEntity account = new AccountEntity(userId, initialBalance != null ? initialBalance : 0.0);
        return accountRepository.save(account);
    }

    @Override
    public AccountEntity getAccountByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }
}
