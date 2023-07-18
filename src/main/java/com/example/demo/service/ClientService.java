package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Art;
import com.example.demo.domain.Client;
import com.example.demo.repository.ClientRepositoty;
@Service
public class ClientService {

	@Autowired
	private ClientRepositoty repo;
	public List<Client> getAll(){
		return repo.findAll();
	}
	public void save(Client c) {
		repo.save(c);
	}
	public Client get(long id) {
		return repo.findById(null).get();
	}
	public void delete(long id) {
		 repo.deleteById(id);
	}
	public boolean authenticate(Client client) {
		// TODO Auto-generated method stub
		Client c=repo.findByEmailAndPassword(client.getEmail(),client.getPassword());
		if(c.equals(client)) {
			return true;
		} 
		return false;
	}
	public Client findByEmailAndPassword(String email,String password) {
		return repo.findByEmailAndPassword(email, password);
	}
	public Client getClientById(Long clientId) {
		// TODO Auto-generated method stub
		return repo.findById(clientId).orElse(null);
	}
	
	
	
}
