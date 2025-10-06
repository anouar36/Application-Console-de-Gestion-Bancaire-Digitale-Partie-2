package org.example.Controller;

import org.example.Modle.Credit;
import org.example.Service.CreditService;
import org.example.Modle.CurrencyType;
import org.example.Validation.Validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class CreditController {
    private CreditService creditService;

    public CreditController() {
        this.creditService = new CreditService();
    }

    public String creditRequest(String linkedAccount, BigDecimal amount, BigDecimal salary, Date dateStart){


            boolean rs = creditService.creditRequest(linkedAccount,amount,salary,dateStart);

            if (rs){
                return "Your request has been processed";
            }else{
                return "There was an error in your request. Please try again. ✅";
            }
    }
    public ArrayList<Credit> getcreditsRequest() {
        return creditService.getcreditsRequest();
    }
    public String validationCredit(String rib){
        boolean rs = creditService.validationCredit(rib);
        if(rs){
            return  "Credit has been validated successfully";

        }else{
            return  "Please check your RIB and try again.";
        }

    }



}
