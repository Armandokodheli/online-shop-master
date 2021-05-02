package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Category;
import com.example.onlineshop.entity.Client;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Client findById(int id) {
        try {
            return clientService.findById(id);
        } catch (CategoryNotFoundException c) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not Found", c);
        }


    }

    @PostMapping("/new")
    public Client save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteById(int id) {
        clientService.deleteById(id);
    }
}


