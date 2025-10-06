package org.example.Service;

import org.example.Modle.BankHistorique;
import org.example.Repository.BankRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BankService {

    private BankRepository bankRepository;

    public BankService() {
        this.bankRepository = new BankRepository();
    }
    public boolean addFee(BigDecimal fee){
        return bankRepository.addFee(fee);
    }
    public ArrayList<BankHistorique> getAllBankHistorique(){
        return bankRepository.getAllBankHistorique();
    }
}
