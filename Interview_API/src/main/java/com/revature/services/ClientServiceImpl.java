package com.revature.services;

import java.util.List;

import com.revature.models.Client;
import com.revature.repos.ClientRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Override
    public Client save(Client c) {
        return clientRepo.save(c);
    }

    @Override
    public Client update(Client c) {
        return clientRepo.save(c);
    }

    @Override
    public Client delete(Client c) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public Client findById(int i) {
        return clientRepo.getOne(i);
    }

    @Override
    public Client findByName(String name) {
        return clientRepo.getByClientName(name);
    }

    
}