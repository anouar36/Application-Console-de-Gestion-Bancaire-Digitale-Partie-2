package org.example.Controller;

import org.example.Modle.Account;
import org.example.Modle.AccountType;
import org.example.Modle.Client;
import org.example.Service.AccountService;
import org.example.Validation.Validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountController {
    private AccountService accountService;

    public AccountController() {
        this.accountService=new AccountService();
    }

    public String creatAccount(BigDecimal balance, int typeInt, Client client, int id) {
        try {
            AccountType type;

            switch (typeInt) {
                case 1:
                    type = AccountType.Courant;
                    break;
                case 2:
                    type = AccountType.Epargne;
                    break;
                default:
                    type = AccountType.Credit;
                    break;
            }

            if (!Validation.isBalance(balance)) {
                return "Enter a valid balance";
            }

            Account result = accountService.creatAccount(balance, type, client, id);
            if (result != null) {
                return "Account created successfully";
            } else {
                return "Account creation failed";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public HashMap<String , ArrayList<Account>> listAccounts(){
        return accountService.listAccounts();
    }
    public String closeAccount (String rib){
        boolean rs = accountService.closeAccount(rib);
        if(rs){
            return "The account closed successfully.";


        }else{
            return "Failed to close the account.";
        }

    }

}
