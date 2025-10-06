package org.example.Service;

import org.example.Modle.Account;
import org.example.Modle.AccountType;
import org.example.Modle.Client;
import org.example.Modle.CurrencyType;
import org.example.Repository.AccountRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.example.Repository.AccountRepository;
import org.example.dao.JDBC;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Date;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();

    public AccountService() {
        this.accountRepository = accountRepository=new AccountRepository();
    }

    public Account creatAccount( BigDecimal balance, AccountType type, Client client ,int id){
        return  this.accountRepository.creatAccount(balance,type,client,id);

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
    public Account getAccountByLinked_account(String linked_account){
        String sql = "SELECT * FROM account WHERE account_number = ?";
        try (Connection connection = JDBC.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, linked_account);
            ResultSet rs = stmt.executeQuery();

            Account account = null;
            if (rs.next()) {
                account = new Account(
                        rs.getString("account_number"),
                        rs.getBigDecimal("balance"),
                        AccountType.valueOf(rs.getString("type")),
                        null
                );
            }

            return account;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public boolean closeAccount(String rib) {
        try {
            boolean hasBalance = accountRepository.accountAmount(rib); // true if balance > 0
            boolean creditPaid = accountRepository.accountCredit(rib); // true if the credit is fully paid

            if (!hasBalance && creditPaid) {
                boolean res = accountRepository.closeAccount(rib);
                if (res) {
                    System.out.println("Account closed successfully.");
                    return true;
                } else {
                    System.out.println("Failed to close the account.");
                    return false;
                }
            } else {
                System.out.println("Cannot close the account: balance > 0 or credit not fully paid.");
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
