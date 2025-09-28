package org.example.View.Validation;

import java.math.BigDecimal;

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



}
