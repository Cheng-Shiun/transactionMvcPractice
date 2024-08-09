package com.example.transactionmvcpractice.service;

import com.example.transactionmvcpractice.dao.AccountDao;
import com.example.transactionmvcpractice.exception.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;


    @Override
    public void transfer(Integer from, Integer to, Integer money) {
        //先查詢from帳戶內的存款夠不夠轉帳
        int fromBalance = accountDao.check(from);

        //若餘額不足 -> 轉帳失敗
        if (fromBalance < money) {
            throw new InsufficientBalanceException("轉出帳戶餘額不足...");
        }

        //存款足夠 -> 繼續執行轉帳作業
        accountDao.transfer(from, to, money);
    }

    @Override
    public int check(Integer accountId) {
        return accountDao.check(accountId);
    }

    @Override
    public int deposit(Integer accountId, Integer money) {
        return accountDao.deposit(accountId, money);
    }

    @Override
    public int withdraw(Integer accountId, Integer money) {
        //先查詢from帳戶內的存款夠不夠轉帳
        int fromBalance = accountDao.check(accountId);

        //若餘額不足 -> 轉帳失敗
        if (fromBalance < money) {
            throw new InsufficientBalanceException("帳戶餘額不足...");
        }

        //存款足夠 -> 繼續執行轉帳作業
        return accountDao.withdraw(accountId, money);
    }
}
