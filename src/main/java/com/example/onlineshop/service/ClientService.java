package com.example.onlineshop.service;

import com.example.onlineshop.entity.Client;
import com.example.onlineshop.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(int id){
        return  clientRepository.findById(id).orElseThrow(() ->new RuntimeException("not found"));
    }
    public Client save(Client client){
        return clientRepository.save(client);
    }
    public void deleteById(int id){
        clientRepository.deleteById(id);
    }
}
