package org.example.Service;

import org.example.Modle.CurrencyType;
import org.example.Repository.CreditRepository;

import java.math.BigDecimal;

public class CreditService {
    private CreditRepository creditRepository;

    public CreditService( ) {
        this.creditRepository = new CreditRepository();
    }

    public boolean creditRequest(double linked_account , BigDecimal amount, CurrencyType currencyType, BigDecimal interest_rate, BigDecimal interestRateMonth, int duration_months ){
        return  this.creditRepository.creditRequest(  linked_account ,  amount,  currencyType,  interest_rate,  interestRateMonth,  duration_months);
    }
}
