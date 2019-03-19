package com.revature.repos;

import com.revature.models.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Integer> {
	Client getByClientName(String name);
}