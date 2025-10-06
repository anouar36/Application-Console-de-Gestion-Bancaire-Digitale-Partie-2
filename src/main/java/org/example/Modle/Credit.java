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
    private BigDecimal interestRateMonth;
    private  BigDecimal my_payments;


    public Credit(int creditId, BigDecimal amount, BigDecimal interestRate, int durationMonths, CreditStatus status, String linkedAccount,BigDecimal interestRateMonth, BigDecimal my_payments) {
        this.creditId = creditId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.status = status;
        this.linkedAccount = linkedAccount;
        this.interestRateMonth = interestRateMonth;
        this.my_payments = my_payments;
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

    public BigDecimal getMypayments() {
        return my_payments;
    }

    public BigDecimal getInterestRateMonth() {
        return interestRateMonth;
    }
}
