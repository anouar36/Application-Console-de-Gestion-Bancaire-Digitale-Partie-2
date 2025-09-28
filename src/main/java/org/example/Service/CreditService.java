package org.example.Service;

import org.example.Repository.CreditRepository;

import java.math.BigDecimal;

public class CreditService {
    private CreditRepository creditRepository;

    public CreditService( ) {
        this.creditRepository = new CreditRepository();
    }

    //public boolean creditRequest(BigDecimal amount, interest_rate,duration_months, status, linked_account ){

    //}
}
