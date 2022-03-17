package com.digitalwallet.wallet.model;

import com.digitalwallet.wallet.dto.TransactionDTO;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UniqueTransactionId", columnNames = { "uniqueId"}))
public class Transaction {

    @Id
    private String uniqueId;
    private Boolean isCredit;
    private Double transactionAmount;
    private Double balanceAfterTransaction;
    @ManyToOne(targetEntity=Player.class, fetch= FetchType.EAGER)
    private Player player;

    public Transaction(){}

    public Transaction(TransactionDTO transactionDTO){
        this.uniqueId = transactionDTO.getUniqueId();
        this.isCredit = transactionDTO.getIsCredit();
        this.transactionAmount = transactionDTO.getTransactionAmount();
        this.player = transactionDTO.getPlayer();
    }

    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boolean getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(Boolean isCredit) {
        this.isCredit = isCredit;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

}
