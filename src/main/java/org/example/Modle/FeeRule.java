package org.example.Modle;

import java.math.BigDecimal;

public class FeeRule {
    private String mode;
    private String operationType;
    private BigDecimal value;


    public FeeRule(String mode, String operationType, BigDecimal value) {
        this.mode = mode;
        this.operationType = operationType;
        this.value = value;
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


    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
