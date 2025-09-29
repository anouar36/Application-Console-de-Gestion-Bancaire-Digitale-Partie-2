package org.example.Controller;

import org.example.Modle.Credit;
import org.example.Service.CreditService;
import org.example.Modle.CurrencyType;
import org.example.Validation.Validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CreditController {
    private CreditService creditService;

    public CreditController() {
        this.creditService = new CreditService();
    }

    public String creditRequest(double linkedAccount, BigDecimal amount,int durationMonths,int currencyInt){
        CurrencyType currencyType ;
        if(!Validation.isCurrencyInt(currencyInt)){
            return "Please entre number of currency entre menu";
        }
        switch (currencyInt){
            case 1 :currencyType = CurrencyType.DOLLAR  ;
            break;
            case 2 :currencyType = CurrencyType.DIRHAM  ;
            break;
            case 3 :currencyType = CurrencyType.EURO    ;
            break;
            default: currencyType = CurrencyType.DIRHAM ;
        }


        int years = durationMonths / 12;
        BigDecimal interestRate = Validation.interestRate(amount,years);
        BigDecimal interestRateMonth = interestRate.divide(new BigDecimal(durationMonths),4,BigDecimal.ROUND_UP);

            boolean rs = creditService.creditRequest(linkedAccount,amount,currencyType,interestRate,interestRateMonth,durationMonths);

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
