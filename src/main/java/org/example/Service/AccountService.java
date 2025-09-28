package org.example.Service;

import org.example.Modle.Account;
import org.example.Modle.AccountType;
import org.example.Modle.Client;
import org.example.Repository.AccountRepository;

import java.math.BigDecimal;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();

    public AccountService() {
        this.accountRepository = accountRepository=new AccountRepository();
    }

    public Account creatAccount( BigDecimal balance, AccountType type, Client client,String currency,int id){
        return  this.accountRepository.creatAccount(balance,type,client,currency,id);

    }
}
