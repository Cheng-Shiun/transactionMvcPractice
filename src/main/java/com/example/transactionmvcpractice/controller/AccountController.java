package com.example.transactionmvcpractice.controller;

import com.example.transactionmvcpractice.dao.AccountDao;
import com.example.transactionmvcpractice.dto.AccountRequestDto;
import com.example.transactionmvcpractice.dto.TransactionRequestDto;
import com.example.transactionmvcpractice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    //轉帳 api
    @PutMapping("/transfer")
    public String transfer(@RequestBody TransactionRequestDto transferRequestDto) {
        accountService.transfer(transferRequestDto.getFrom(),
                                transferRequestDto.getTo(),
                                transferRequestDto.getMoney());
        return "Transfer Successful！";
    }

    //READ 查詢一筆帳戶存款 api
    @GetMapping("/check/{accountId}")
    public String check(@PathVariable Integer accountId) {
        return "帳戶id: " + accountId + "/" + "帳戶存款: " + accountService.check(accountId);
    }

    //UPDATE 帳戶存款 api
    @PutMapping("/deposit")
    public String deposit(@RequestBody AccountRequestDto accountRequestDto) {
        int newBalance = accountService.deposit(accountRequestDto.getId(), accountRequestDto.getMoney());
        return "已存入:" + accountRequestDto.getMoney() + " 帳戶餘額為: " + newBalance;
    }
}
