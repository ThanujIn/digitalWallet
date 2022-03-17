package com.digitalwallet.wallet.model;

import com.digitalwallet.wallet.dto.PlayerDTO;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UniqueName", columnNames = { "firstName", "lastName" }))
public class Player {

    public Player(){}
    public Player(PlayerDTO playerDTO){
        this.id = playerDTO.getId();
        this.firstName = playerDTO.getFirstName();
        this.lastName = playerDTO.getLastName();
        this.account = playerDTO.getAccount();
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;
}