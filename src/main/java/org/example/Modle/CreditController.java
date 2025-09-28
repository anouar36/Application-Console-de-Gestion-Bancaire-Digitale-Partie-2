package org.example.Modle;

import java.math.BigDecimal;
import  org.example.Modle.CurrencyType.*;
import org.example.View.Validation.Validation;

public class CreditController {
    private  CreditService creditService;

    public CreditController() {
        this.creditService = new CreditService();
    }


}
