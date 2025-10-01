package org.example.Service;

import org.example.Modle.CurrencyType;
import org.example.Modle.TransactionType;
import org.example.Repository.TransactionRepository;

import java.math.BigDecimal;

public class TransactionServic {
    private TransactionRepository transactionRepository;
    private AccountService accountService ;

    public TransactionServic() {
        this.transactionRepository = new TransactionRepository();
        this.accountService = new AccountService();
    }

    public boolean deposit(BigDecimal amount, String accountNumber){

        boolean rowsUpdated = transactionRepository.deposit(amount,accountNumber);

        if (rowsUpdated) {

            if (!transactionRepository.addToHistorique(amount, accountNumber, null, "DEPOSIT")) {
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean withdraw(BigDecimal amount, String accountNumber){
        if(!transactionRepository.withdraw(amount,accountNumber)){
            return false;
        }

        if (!transactionRepository.addToHistorique(amount, accountNumber, null, "WITHDRAWAL")) {
          return  false;
        }
        return  true ;

    }
    public boolean transferExternal(BigDecimal amount, String fromAccount,String toAccount){

        if(amount!=null && fromAccount!= null){
            transactionRepository.deposit(amount,toAccount);
            return true ;
        }
        if (amount!=null && toAccount!= null){
            transactionRepository.withdraw(amount,fromAccount);
            return  true;
        }
        return  false;

    }
    public boolean transferInternal(BigDecimal amount, String fromAccount, String toAccount, String bankCode) {

        boolean res = accountService.checkClientAcounts(fromAccount, toAccount);

        if (res) {
            if (amount != null && fromAccount != null) {
                transactionRepository.deposit(amount, toAccount);
                return true;
            }
            if (amount != null && toAccount != null) {
                transactionRepository.withdraw(amount, fromAccount);
                return true;
            }
            return false;

        }
        return true;
    }

    public boolean transferHistorique(BigDecimal amount,String fromAccount,String toAccount, TransactionType type){
        return transactionRepository.transferHistorique( amount,  fromAccount,  toAccount, type);
    }




}
