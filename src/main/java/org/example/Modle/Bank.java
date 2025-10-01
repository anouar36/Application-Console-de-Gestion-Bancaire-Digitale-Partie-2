package org.example.Modle;

import java.util.Date;

public class Bank {
    private int id ;
    private int bank_name ;
    private int account_rib ;
    private int transaction_type ;
    private int amount ;
    private Date created_at;

    public Bank(int id, int bank_name, int account_rib, int transaction_type, int amount, Date created_at) {
        this.id = id;
        this.bank_name = bank_name;
        this.account_rib = account_rib;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public int getBank_name() {
        return bank_name;
    }

    public int getAccount_rib() {
        return account_rib;
    }

    public int getTransaction_type() {
        return transaction_type;
    }

    public int getAmount() {
        return amount;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBank_name(int bank_name) {
        this.bank_name = bank_name;
    }

    public void setAccount_rib(int account_rib) {
        this.account_rib = account_rib;
    }

    public void setTransaction_type(int transaction_type) {
        this.transaction_type = transaction_type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
