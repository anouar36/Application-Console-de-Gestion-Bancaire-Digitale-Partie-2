package org.example.Modle;

import java.math.BigDecimal;

public class Account {
    private String accountId;
    private BigDecimal balance;
    private AccountType type;
    private Client owner;

    public Account(String accountId, BigDecimal balance, AccountType type, Client owner) {
        this.accountId = accountId;
        this.balance = balance;
        this.type = type;
        this.owner = owner;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountType getType() {
        return type;
    }

    public Client getOwner() {
        return owner;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}

