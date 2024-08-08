package com.example.transactionmvcpractice.service;

import com.example.transactionmvcpractice.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Transactional
    @Override
    public void transfer(Integer fromAccountId, Integer toAccountId, Integer money) {
        accountDao.transfer(fromAccountId, toAccountId, money);
    }

    @Override
    public int check(Integer accountId) {
        return accountDao.check(accountId);
    }

    @Override
    public int deposit(Integer accountId, Integer money) {
        return accountDao.deposit(accountId, money);
    }
}
