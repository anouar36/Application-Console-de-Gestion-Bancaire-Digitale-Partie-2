package org.example.Modle;

import java.util.List;

public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private List<Account> accounts;

    //public Client(String name, String address, String email, List<Account> accounts) {
      //  this.name = name;
        //this.address = address;
        //this.email = email;
        //this.accounts = accounts;
    //}

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setId(int id) {
        this.id = id;
    }
}
