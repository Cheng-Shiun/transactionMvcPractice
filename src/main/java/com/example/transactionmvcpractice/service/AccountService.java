package com.example.transactionmvcpractice.service;

import com.example.transactionmvcpractice.dto.TransactionRequestDto;

public interface AccountService {
    void transfer(Integer from, Integer to, Integer money);

    int check(Integer accountId);

    int deposit(Integer accountId, Integer money);

    int withdraw(Integer accountId, Integer money);
}
