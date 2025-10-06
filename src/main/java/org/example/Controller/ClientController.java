package org.example.Controller;

import org.example.Modle.Client;
import org.example.Service.ClientService;
import org.example.Validation.Validation;

import java.util.ArrayList;

public class ClientController {

    private ClientService clientService ;
    private Client client;
    
    public ClientController() {
        this.clientService = new ClientService();
        this.client = new Client();

    }
    public Client addClient(String name, String email, String address) {

        if (!Validation.isName(name)) {
            System.out.println(" please enter a valid name");
            return null;
        }
        if (!Validation.isGmail(email)) {
            System.out.println(" please enter a valid email");
            return null;
        }
        if (!Validation.isAddresse(address)) {
            System.out.println(" please enter a valid address");
            return null;
        }

        Integer res = clientService.addClient(name, email, address);

        if (res != null) {
            Client client = new Client();
            client.setId(res);
            client.setName(name);
            client.setEmail(email);
            client.setAddress(address);

            return client;
        } else {

            return null;
        }
    }

    public Client getClient(String name,String email){
        return clientService.getClient(name,email);
    }
    public ArrayList<Client> getAllClient(){
        return clientService.getAllClient();
    }
    public String updateClient(int id,int updateInt, String value){



            if (!Validation.isInt(updateInt) || !Validation.isString(value)) {
                return "Pleae you have error in update choice";
            }

            String column = "";
            switch (updateInt) {
                case 1:
                    column = "name";
                    break;
                case 2:
                    column = "email";
                    break;
                case 3:
                    column = "address";
                    break;
            }

            boolean res = clientService.updateClient(id, column, value);
            if (!res) {
                return "Please you have error in your update ";
            }
            return "the " + column + " is updated successful";





    }

}
