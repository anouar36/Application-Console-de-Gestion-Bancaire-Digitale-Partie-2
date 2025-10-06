package org.example.Controller;

import org.example.Modle.BankHistorique;
import org.example.Service.BankService;

import java.util.ArrayList;

public class BankController {


    private BankService bankService;

    public BankController() {
        this.bankService = new BankService();
    }

    public ArrayList<BankHistorique> getAllBankHistorique(){
        return bankService.getAllBankHistorique();
    }
}
