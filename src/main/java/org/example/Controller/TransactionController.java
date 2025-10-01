package org.example.Controller;

import org.example.Modle.CurrencyType;
import org.example.Modle.TransactionType;
import org.example.Service.AccountService;
import org.example.Service.BankService;
import org.example.Service.TransactionServic;

import java.math.BigDecimal;

public class TransactionController {
    private TransactionServic transactionServic;
    private AccountService accountService;
    private BankService bankService;

    public TransactionController() {
        this.transactionServic = new TransactionServic();
        this.bankService = new BankService();
    }

    public String deposit(BigDecimal amount, String accountNumber){

        boolean rs = transactionServic.deposit(amount, accountNumber);
        if(rs){
            return "Deposit of " + amount + " MAD to account " + accountNumber + " was successful.";
        }
            return "Deposit failed. Please check the account number or balance.";
    }

    public String withdraw(BigDecimal amount, String accountNumber){
        boolean rs = transactionServic.withdraw(amount, accountNumber);
        if(rs){
            return "Withdrawal of " + amount + " MAD from account " + accountNumber + " was successful.";
        }
            return "Withdrawal failed. Please check the account number or available balance.";
    }

    public String transferExternal(BigDecimal amount, String fromAccount, String toAccount) {

        BigDecimal fee = BigDecimal.valueOf(5);
        BigDecimal totalAmount = amount.subtract(fee);
        boolean res  = bankService.addFee(fee);
        boolean withdrawSuccess = transactionServic.withdraw(totalAmount, fromAccount);

        if (!withdrawSuccess) {
            return "External transfer failed. Withdrawal of " + totalAmount + " MAD from account "
                    + fromAccount + " was not successful.";
        }


        boolean depositSuccess = transactionServic.deposit(amount, toAccount);

        if (!depositSuccess) {
            transactionServic.deposit(totalAmount, fromAccount);
            return "External transfer failed. Deposit to account " + toAccount + " was not successful.";
        }

        boolean transferHistorique = transactionServic.transferHistorique(
                amount,
                fromAccount,
                toAccount,
                TransactionType.TRANSFER_OUT

        );

        if (!transferHistorique) {

            transactionServic.withdraw(amount, toAccount);
            transactionServic.deposit(totalAmount, fromAccount);
            return "External transfer failed. Could not record transaction in history.";
        }

        return "External transfer of " + amount + " MAD from account " + fromAccount +
                " to account " + toAccount + " was successful. Fee: " + fee + " MAD (deducted from the transferred amount).";

    }


    public String transferInternal(BigDecimal amount, String fromAccount, String toAccount) {

        boolean withdrawSuccess = transactionServic.withdraw(amount, fromAccount);

        if (!withdrawSuccess) {
            return "Internal transfer failed. Withdrawal from account " + fromAccount + " was not successful.";
        }

        boolean transferHistorique = transactionServic.transferHistorique(
                amount,
                fromAccount,
                toAccount,
                TransactionType.TRANSFER_IN
        );

        if (!transferHistorique) {
            transactionServic.deposit(amount, fromAccount);
            return "Internal transfer failed. Could not record transaction in history.";
        }

        return "Internal transfer of " + amount + " MAD from account " + fromAccount +
                " to account " + toAccount + " was successful. The amount has been deducted from your account.";
    }









}



