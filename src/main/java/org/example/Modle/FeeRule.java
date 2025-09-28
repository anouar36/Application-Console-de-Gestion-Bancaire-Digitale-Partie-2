package org.example.Modle;

import java.math.BigDecimal;

public class FeeRule {
     private String mode;
     private String operationType;
     private BigDecimal value;
     private String currency;

    public FeeRule(String mode, String operationType, BigDecimal value, String currency) {
        this.mode = mode;
        this.operationType = operationType;
        this.value = value;
        this.currency = currency;
    }

    public String getMode() {
        return mode;
    }

    public String getOperationType() {
        return operationType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
