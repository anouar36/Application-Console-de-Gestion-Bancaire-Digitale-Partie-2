package org.example.Service;

import org.example.Modle.*;
import org.example.Repository.BankRepository;
import org.example.Repository.CreditRepository;
import org.example.Repository.TransactionRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class CreditService {
    private CreditRepository creditRepository;
    private ServicFeeRole servicFeeRole;
    private AccountService accountService;
    private TransactionRepository transactionRepository;
    private BankRepository bankRepository;


    public CreditService( ) {
        this.creditRepository = new CreditRepository();
        this.servicFeeRole = new ServicFeeRole();
        this.accountService = new AccountService();
        this.transactionRepository = new TransactionRepository();
        this.bankRepository = new BankRepository();
    }

    public boolean creditRequest(String linked_account , BigDecimal amount, BigDecimal salary ,Date dateStart){

         Account account= accountService.getAccountByLinked_account( linked_account );
        try{

            if( account.getType() == AccountType.Credit ){
                FeeRule feeRule = servicFeeRole.getFeeRole(TransactionType.Credit);

                 BigDecimal interest_rate = amount.add(amount.multiply(feeRule.getValue()).divide(new BigDecimal("100.00")));


                BigDecimal interestRateMonth = salary.multiply(new BigDecimal(0.4));
                 int duration_months = interest_rate.divide(interestRateMonth, 0, RoundingMode.CEILING).intValue();



                return  this.creditRepository.creditRequest( linked_account ,  amount,  interest_rate,  interestRateMonth,  duration_months, dateStart);
            }
            return  false;


        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public ArrayList<Credit> getcreditsRequest(){
        return creditRepository.getcreditsRequest();
    }
    public boolean validationCredit(String rib){
        return creditRepository.validationCredit(rib);
    }

    public boolean deductions() {
        HashMap<String, ArrayList<Account>> listAccounts = accountService.listAccounts();

        for (ArrayList<Account> accounts : listAccounts.values()) {
            for (Account a : accounts) {
                int idClient = a.getOwner().getId();
                String idAccount = a.getAccountId();
                Credit credit = creditRepository.getCreditByIdAccount(idAccount);

                if (credit == null) {
                    continue;
                }

                BigDecimal interestRateMonth = credit.getInterestRateMonth();
                BigDecimal my_payments = credit.getMypayments();
                BigDecimal interestRate = credit.getInterestRate();


                if (interestRate.compareTo(my_payments) > 0) {
                    boolean withdrawn = transactionRepository.withdraw(interestRateMonth, idAccount);
                    if (!withdrawn) {
                        continue;
                    }

                    boolean ded = creditRepository.deductions(interestRateMonth, idAccount);
                    boolean fee = bankRepository.addFee(interestRateMonth);
                    boolean hist = bankRepository.bankHistorique(idClient, idAccount, interestRateMonth, interestRate, my_payments);
                    if(ded && fee && hist){
                        boolean status = creditRepository.changeStatus(credit.getCreditId(), CreditStatus.LATE);
                    } else if (interestRate.compareTo(my_payments) <= 0) {
                        boolean status = creditRepository.changeStatus(credit.getCreditId(), CreditStatus.CLOSED);
                    }

                }
            }
        }

        return true;
    }
    public boolean changeStatus(int creditId, CreditStatus newStatus) {

        return creditRepository.changeStatus(creditId,newStatus);
    }



}
