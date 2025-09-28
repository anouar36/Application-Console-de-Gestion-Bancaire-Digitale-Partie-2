package org.example.Modle;

import org.example.Repository.CreditRepository;

import java.util.Date;

public class CreditService {
    private CreditRepository creditRepository;
    public CreditService() {
        this.creditRepository = new CreditRepository();
    }

    public boolean creditRequest(String rib, String name,String descreaption , Date dateStarted, Date dateEnd){
        return this.creditRepository.creditRequest(rib,name,descreaption,dateStarted,dateEnd);

    }
}
