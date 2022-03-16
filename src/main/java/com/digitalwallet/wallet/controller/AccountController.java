package com.digitalwallet.wallet.controller;

import com.digitalwallet.wallet.model.Account;
import com.digitalwallet.wallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(path = "/getAccountById/{accountId}")
    public ResponseEntity<Object> getAccountById(@PathVariable("accountId") String accountId){
        Optional<Account> account = accountService.getAccountById(accountId);
        return account.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElse(null);
    }

    @GetMapping(path = "/getAccountByPlayerId/{playerId}")
    public ResponseEntity<Object> getAccountByPlayerId(@PathVariable("playerId") String playerId){
        Optional<Account> account = accountService.getAccountByPlayerId(playerId);
        return account.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElse(null);
    }
}
