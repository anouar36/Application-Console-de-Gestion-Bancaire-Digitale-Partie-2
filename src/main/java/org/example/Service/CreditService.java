package org.example.Service;

import org.example.Modle.Credit;
import org.example.Modle.CurrencyType;
import org.example.Repository.CreditRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CreditService {
    private CreditRepository creditRepository;

    public CreditService( ) {
        this.creditRepository = new CreditRepository();
    }

    public boolean creditRequest(double linked_account , BigDecimal amount, BigDecimal interest_rate, BigDecimal interestRateMonth, int duration_months ){
        return  this.creditRepository.creditRequest(  linked_account ,  amount,  interest_rate,  interestRateMonth,  duration_months);
    }
    public ArrayList<Credit> getcreditsRequest(){
        return creditRepository.getcreditsRequest();
    }
    public boolean validationCredit(String rib){
        return creditRepository.validationCredit(rib);
    }
}
