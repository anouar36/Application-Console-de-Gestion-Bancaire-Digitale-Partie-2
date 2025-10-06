package org.example.Service;

import org.example.Modle.FeeRule;
import org.example.Modle.TransactionType;
import org.example.Repository.FeeRoleRepsitory;

public class ServicFeeRole {

    private FeeRoleRepsitory feeRoleRepsitory;

    public ServicFeeRole() {
        this.feeRoleRepsitory = new FeeRoleRepsitory();
    }
    public FeeRule getFeeRole(TransactionType type){
        String strType = type.name();
        return feeRoleRepsitory.getFeeRole(strType);
    }

}
