package com.digitalwallet.wallet.service;

import com.digitalwallet.wallet.dto.TransactionDTO;
import com.digitalwallet.wallet.model.Transaction;
import com.digitalwallet.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public Optional<Transaction> getTransactionById(String transactionId){
        return transactionRepository.findById(transactionId);
    }

    public void addTransaction(TransactionDTO transactionDTO){
        transactionRepository.save(new Transaction(transactionDTO));
    }

}
