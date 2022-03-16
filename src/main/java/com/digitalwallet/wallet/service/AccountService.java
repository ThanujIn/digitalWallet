package com.digitalwallet.wallet.service;

import com.digitalwallet.wallet.model.Account;
import com.digitalwallet.wallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Optional<Account> getAccountById(String accountId){
        return accountRepository.findById(accountId);
    }

    public Optional<Account> getAccountByPlayerId(String playerId){
        return accountRepository.findById(Long.parseLong(playerId) * 1000 + "");
    }

}
