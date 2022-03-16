package com.digitalwallet.wallet.controller;

import com.digitalwallet.wallet.dto.TransactionDTO;
import com.digitalwallet.wallet.model.Transaction;
import com.digitalwallet.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping(path = "/getTransactionById/{transactionId}")
    public Transaction getTransactionById(@PathVariable("transactionId") String transactionId){
        Optional<Transaction> transaction = transactionService.getTransactionById(transactionId);
        return transaction.orElse(null);
    }

    @PostMapping(path = "/addTransaction")
    public void addTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.addTransaction(transactionDTO);
    }

}