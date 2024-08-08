package com.example.transactionmvcpractice.service;

public interface AccountService {
    void transfer(Integer fromAccountId, Integer toAccountId, Integer money);

    int check(Integer accountId);

    int deposit(Integer accountId, Integer money);
}
