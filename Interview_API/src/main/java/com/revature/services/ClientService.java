package com.revature.services;

import java.util.List;

import com.revature.models.Client;

public interface ClientService {

    Client save(Client c);
    Client update(Client c);
    Client delete(Client c);

    List<Client> findAll();
    Client findById(int i);
    Client findByName(String name);
}