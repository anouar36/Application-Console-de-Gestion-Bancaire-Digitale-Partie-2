package org.example.Service;

import org.example.Modle.Account;
import org.example.Modle.AccountType;
import org.example.Modle.Client;
import org.example.Modle.CurrencyType;
import org.example.Repository.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();

    public AccountService() {
        this.accountRepository = accountRepository=new AccountRepository();
    }

    public Account creatAccount( BigDecimal balance, AccountType type, Client client,String currency,int id){
        return  this.accountRepository.creatAccount(balance,type,client,currency,id);

    }
    public boolean checkClientAcounts(  String fromAccount, String toAccount){

        return  accountRepository.checkClientAcounts(fromAccount,toAccount);

    }
    public HashMap<String , ArrayList<Account>> listAccounts(){
        return accountRepository.listAccounts();
    }

    public void  addSalary(){
        boolean res=accountRepository.addSalary();
        if(res){
            System.out.println("salary his added succsuf");
        }else{
            System.out.println("salary his not added succsuf");
        }
    }



}
