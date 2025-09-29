package org.example.Modle;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Credit {

    private int creditId;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private int durationMonths;
    private CreditStatus status;
    private String linkedAccount;
    private CurrencyType currencyType;
    private BigDecimal interestRateMonth;


    public Credit(int creditId, BigDecimal amount, BigDecimal interestRate, int durationMonths, CreditStatus status, String linkedAccount, CurrencyType currencyType,BigDecimal interestRateMonth) {
        this.creditId = creditId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.status = status;
        this.linkedAccount = linkedAccount;
        this.currencyType = currencyType;
        this.interestRateMonth = interestRateMonth;
    }

    public int getCreditId() {
        return creditId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public CreditStatus getStatus() {
        return status;
    }

    public String  getLinkedAccount() {
        return linkedAccount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }
}
