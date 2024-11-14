package com.example.demo.service;

import com.example.demo.entity.AccountEntity;

public interface AccountService {


    AccountEntity createAccount(Long userId, Double initialBalance);

    AccountEntity getAccountByUserId(Long userId);



}



