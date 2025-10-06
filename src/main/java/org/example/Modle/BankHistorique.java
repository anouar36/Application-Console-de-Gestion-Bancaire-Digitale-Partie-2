package org.example.Modle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankHistorique {

    private int id;
    private int idClient;
    private String linkedAccount;
    private BigDecimal interestRateMonth;
    private BigDecimal interestRate;
    private BigDecimal myPayments;
    private LocalDateTime createdAt;

    // Constructor
    public BankHistorique() {}

    public BankHistorique(int id, int idClient, String linkedAccount, BigDecimal interestRateMonth,
                          BigDecimal interestRate, BigDecimal myPayments, LocalDateTime createdAt) {
        this.id = id;
        this.idClient = idClient;
        this.linkedAccount = linkedAccount;
        this.interestRateMonth = interestRateMonth;
        this.interestRate = interestRate;
        this.myPayments = myPayments;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getLinkedAccount() {
        return linkedAccount;
    }

    public void setLinkedAccount(String linkedAccount) {
        this.linkedAccount = linkedAccount;
    }

    public BigDecimal getInterestRateMonth() {
        return interestRateMonth;
    }

    public void setInterestRateMonth(BigDecimal interestRateMonth) {
        this.interestRateMonth = interestRateMonth;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMyPayments() {
        return myPayments;
    }

    public void setMyPayments(BigDecimal myPayments) {
        this.myPayments = myPayments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BankHistorique{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", linkedAccount='" + linkedAccount + '\'' +
                ", interestRateMonth=" + interestRateMonth +
                ", interestRate=" + interestRate +
                ", myPayments=" + myPayments +
                ", createdAt=" + createdAt +
                '}';
    }
}
