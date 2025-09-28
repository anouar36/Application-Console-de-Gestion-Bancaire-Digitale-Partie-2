package org.example.Controller;

import org.example.Modle.Client;
import org.example.Service.ClientService;
import org.example.View.Validation.Validation;

public class ClientController {

    private ClientService clientService ;
    private Client client;
    public ClientController() {
        this.clientService = new ClientService();
        this.client = new Client();

    }

    public String addClient(String name, String email, String address){

        if(!Validation.isName(name)){
            return "please entre true name";
        }
        if(!Validation.isGmail(email)){
            return "please entre true email";
        }
        if(!Validation.isAddresse(address)){
            return "entre true address";
        }
        Integer res = clientService.addClient(name, email, address);
        if (res != null) {
            client.setId(res);
            return name + " Has been added successfully.✅";
        } else {
            return "Was not added successfully";
        }

    }
    public Client getClient(String name,String email){
        return clientService.getClient(name,email);
    }
}
