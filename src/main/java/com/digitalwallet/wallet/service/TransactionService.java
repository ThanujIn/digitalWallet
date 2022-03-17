package com.digitalwallet.wallet.service;

import com.digitalwallet.wallet.dto.TransactionDTO;
import com.digitalwallet.wallet.model.Account;
import com.digitalwallet.wallet.model.Player;
import com.digitalwallet.wallet.model.Transaction;
import com.digitalwallet.wallet.repository.AccountRepository;
import com.digitalwallet.wallet.repository.PlayerRepository;
import com.digitalwallet.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    AccountRepository accountRepository;

    public Optional<Transaction> getTransactionById(String transactionId){
        return transactionRepository.findById(transactionId);
    }

    public ResponseEntity<Object> addTransaction(TransactionDTO transactionDTO){
        try{
            if(transactionDTO.getTransactionAmount() > 0.0){
                Optional<Player> player = playerRepository.findById(transactionDTO.getPlayer().getId());
                if(player.isPresent()){
                    Date date = new Date();
                    transactionDTO.setUniqueId("trans" + player.get().getId() + transactionDTO.getIsCredit() + date.getTime());
                    Transaction transaction = new Transaction(transactionDTO);
                    transaction.setPlayer(player.get());
                    Optional<Account> acc = accountRepository.findById(player.get().getId() * 1000L);
                    ResponseEntity<Object> response = validateTransaction(transactionDTO, transaction, acc);
                    if (response != null) return response;
                    return new ResponseEntity<>("Transaction added successfully", HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Transaction registration failed : Player not found", HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("Transaction amount is not valid", HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Transaction failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Object> validateTransaction(TransactionDTO transactionDTO, Transaction transaction, Optional<Account> acc) {
        if(acc.isPresent()){
            Account transactionAccount = acc.get();
            if(Boolean.TRUE.equals(transactionDTO.getIsCredit())){
                transactionAccount.setBalance(transactionAccount.getBalance() + transactionDTO.getTransactionAmount());
                transaction.setBalanceAfterTransaction(transactionAccount.getBalance());
                transactionRepository.save(transaction);
                accountRepository.save(transactionAccount);
                return new ResponseEntity<>("Credit transaction added successfully", HttpStatus.OK);
            }else{
                if(transactionAccount.getBalance() >= transactionDTO.getTransactionAmount()){
                    transactionAccount.setBalance(transactionAccount.getBalance() - transactionDTO.getTransactionAmount());
                    transaction.setBalanceAfterTransaction(transactionAccount.getBalance());
                    transactionRepository.save(transaction);
                    accountRepository.save(transactionAccount);
                    return new ResponseEntity<>("Debit transaction added successfully", HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Balance insufficient for the debit transaction", HttpStatus.BAD_REQUEST);
                }
            }
        }
        return null;
    }

    public ResponseEntity<Object> getTransactionsByPlayerId(String playerId){
        List<Transaction> transaction = transactionRepository.getTransactionsByPlayerId(Long.parseLong(playerId));
        if(!transaction.isEmpty()){
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No transactions for requested player", HttpStatus.OK);
        }
    }
}
