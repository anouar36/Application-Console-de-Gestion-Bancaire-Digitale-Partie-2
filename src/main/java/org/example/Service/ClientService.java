package org.example.Service;

import org.example.Modle.Client;
import org.example.Repository.ClientRepository;

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
}
