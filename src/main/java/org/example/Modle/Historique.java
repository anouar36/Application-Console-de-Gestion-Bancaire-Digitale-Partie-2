package org.example.Modle;

import java.math.BigDecimal;
import java.util.Date;

public class Historique {
    private int id ;
    private BigDecimal amount ;
    private String clientRIB ;
    private String receiverRIB ;
    private TransactionType transactionType ;
    private Date dateTransaction;

    public Historique(int id, BigDecimal amount, String clientRIB, String senderRIB, TransactionType transactionType, Date dateTransaction) {
        this.id = id;
        this.amount = amount;
        this.clientRIB = clientRIB;
        this.receiverRIB = senderRIB;
        this.transactionType = transactionType;
        this.dateTransaction = dateTransaction;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getClientRIB() {
        return clientRIB;
    }

    public String getReceiverRIB() {
        return receiverRIB;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setClientRIB(String clientRIB) {
        this.clientRIB = clientRIB;
    }

    public void setSenderRIB(String RrceiverRIB) {
        this.receiverRIB = receiverRIB;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
}
