package org.example.Modle;

import java.math.BigDecimal;

public class Credit {
    private String creditId;
    private BigDecimal amount;
    private double interestRate;
    private int durationMonths;
    private CreditStatus status;
    private Account linkedAccount;
    private CurrencyType currencyType;


    public Credit(String creditId, BigDecimal amount, double interestRate, int durationMonths, CreditStatus status, Account linkedAccount, CurrencyType currencyType) {
        this.creditId = creditId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.status = status;
        this.linkedAccount = linkedAccount;
        this.currencyType = currencyType;
    }

    public String getCreditId() {
        return creditId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public CreditStatus getStatus() {
        return status;
    }

    public Account getLinkedAccount() {
        return linkedAccount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }
}
