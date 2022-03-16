package com.digitalwallet.wallet.model;

import com.digitalwallet.wallet.dto.PlayerDTO;
import com.digitalwallet.wallet.dto.TransactionDTO;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    private final String uniqueId;
    private String type;
    private Double transactionAmount;
    @ManyToOne(targetEntity=Player.class, fetch= FetchType.EAGER)
    private Player player;
    @ManyToOne(targetEntity=Account.class, fetch=FetchType.EAGER)
    private Account account;

    public Transaction(TransactionDTO transactionDTO){
        this.uniqueId = transactionDTO.getUniqueId();
        this.type = transactionDTO.getType();
        this.transactionAmount = transactionDTO.getTransactionAmount();
        this.player = transactionDTO.getPlayer();
        this.account = transactionDTO.getAccount();
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Transaction(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
