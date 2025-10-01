package org.example.Validation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;

public class Validation {

    public static boolean isGmail(String email){
        return  email !=null && email.contains("@gmail.com");
    }
    public static boolean isName(String name){
        return name != null && !name.matches(".*\\d.*") && name.length() >= 3;
    }
    public static boolean isPassword(String password){
        return  password!=null && password.length()>=6 && password.matches(".*\\d.*");

    }
    public static boolean isAddresse(String adresse){
        return  adresse != null && adresse.length()>=4 ;

    }
    public static boolean isBalance(BigDecimal balance){
        return  balance != null && balance.compareTo(BigDecimal.ZERO) > 0  ;

    }
    public static BigDecimal interestRate(BigDecimal amount,int years){
        BigDecimal interest = amount.multiply(new BigDecimal("0.05")).multiply(new BigDecimal(years));
        return interest;
    }
    public static  boolean isInt(Integer numInt){
            if(numInt != null && numInt.intValue() != 0){
                return  true;
            }else{
                return false;
            }
    }
    public static  boolean isString(String value){
        if(value != null && !value.isEmpty()){
            return  true;
        }else{
            return false;
        }
    }





}
