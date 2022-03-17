package com.digitalwallet.wallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UniqueAccountId", columnNames = { "accountId"}))
public class Account {
    public Account(){}

    public Account(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Id
    private Long accountId;
    private Double balance = 0.0;
    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private Player player;
    private Boolean isActive;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
