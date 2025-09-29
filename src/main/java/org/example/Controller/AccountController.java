package org.example.Controller;

import org.example.Modle.Account;
import org.example.Modle.AccountType;
import org.example.Modle.Client;
import org.example.Service.AccountService;
import org.example.Validation.Validation;

import java.math.BigDecimal;

public class AccountController {
    private AccountService accountService;

    public AccountController() {
        this.accountService=new AccountService();
    }

    public String creatAccount(BigDecimal balance, int typeInt, Client client, int id) {
        try {
            AccountType type;
            String currency;

            switch (typeInt) {
                case 1:
                    type = AccountType.Courant;
                    currency = "55";
                    break;
                case 2:
                    type = AccountType.Epargne;
                    currency = "10";
                    break;
                default:
                    type = AccountType.Credit;
                    currency = "33";
                    break;
            }

            if (!Validation.isBalance(balance)) {
                return "Enter a valid balance";
            }

            Account result = accountService.creatAccount(balance, type, client, currency, id);
            if (result != null) {
                return "Account created successfully";
            } else {
                return "Account creation failed";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
