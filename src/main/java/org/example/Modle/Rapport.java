package org.example.Modle;

import java.util.List;

public class Rapport {
    private List<Credit> credits;
    private List<Transaction> transactions;

    public Rapport(List<Credit> credits, List<Transaction> transactions) {
        this.credits = credits;
        this.transactions = transactions;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
