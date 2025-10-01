package org.example.Service;

import org.example.Repository.BankRepository;

import java.math.BigDecimal;

public class BankService {

    private BankRepository bankRepository;

    public BankService() {
        this.bankRepository = new BankRepository();
    }
    public boolean addFee(BigDecimal fee){
        return bankRepository.addFee(fee);
    }
}
