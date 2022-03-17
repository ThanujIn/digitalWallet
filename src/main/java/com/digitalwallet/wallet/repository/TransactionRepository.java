package com.digitalwallet.wallet.repository;

import com.digitalwallet.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> getTransactionsByPlayerId(Long playerId);
}
