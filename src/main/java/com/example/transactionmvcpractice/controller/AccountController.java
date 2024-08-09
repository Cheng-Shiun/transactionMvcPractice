package com.example.transactionmvcpractice.controller;


import com.example.transactionmvcpractice.dto.AccountRequestDto;
import com.example.transactionmvcpractice.dto.TransactionRequestDto;
import com.example.transactionmvcpractice.exception.InsufficientBalanceException;
import com.example.transactionmvcpractice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    //PUT 轉帳 api
    //若轉出帳戶餘額不足 -> 轉帳失敗；反之 -> 轉帳成功
    @PutMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransactionRequestDto transferRequestDto) {
        //自定義返回轉帳失敗的http status code
        try {
        accountService.transfer(transferRequestDto.getFrom(),
                                transferRequestDto.getTo(),
                                transferRequestDto.getMoney());
            return ResponseEntity.ok("帳戶: " + transferRequestDto.getFrom() + "已成功轉帳給"
                    + "帳戶: " + transferRequestDto.getTo() + "一筆" + transferRequestDto.getMoney());
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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

    //UPDATE 帳戶提款 api
    //若餘額不足 -> 提款失敗；反之 -> 提款成功
    @PutMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody AccountRequestDto accountRequestDto) {
        try {
            int newBalance = accountService.withdraw(accountRequestDto.getId(), accountRequestDto.getMoney());
            return ResponseEntity.ok("交易成功，帳戶: " + accountRequestDto.getId() + " " + "存款餘額為:" + newBalance);
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
