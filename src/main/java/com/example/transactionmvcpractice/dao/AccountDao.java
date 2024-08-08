package com.example.transactionmvcpractice.dao;

public interface AccountDao {
    //增加帳戶金額，參數給定帳號和金額
    void transfer(Integer fromAccountId, Integer toAccountId, Integer money);

    int check(Integer accountId);

    int deposit(Integer accountId, Integer money);
}
