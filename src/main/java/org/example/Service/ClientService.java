package org.example.Service;

import org.example.Modle.Client;
import org.example.Repository.ClientRepository;

import java.util.ArrayList;

public class ClientService {
    private ClientRepository clientRepository ;

    public ClientService() {
        this.clientRepository = clientRepository= new ClientRepository();
    }

    public Integer addClient(String name,String email,String adress){
       return this.clientRepository.addClient(name,email,adress);
    }

    public Client getClient(String name,String email){
        return clientRepository.getClient(name,email);
    }

    public ArrayList<Client> getAllClient(){
        return clientRepository.getAllClient();
    }
    public boolean updateClient(int id,String column , String value){
        return clientRepository.updateClient( id,column,value);

    }
}
