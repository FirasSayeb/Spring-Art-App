package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Art;
import com.example.demo.domain.Client;
import com.example.demo.repository.ArtRepository;
@Service
public class ArtService {
	@Autowired
	private ArtRepository repo;
	public List<Art> getAll(){
		return repo.findAll();
	}
	public void save(Art a) {
		repo.save(a);
	}
	public Art get(long id) {
		return repo.findById(id).get();
	}
	public void delete(long id) {
		repo.deleteById(id);
	}
	public List<Art> getByClient(Client client) { 
	    return repo.findByClient(client);
	}
	public Art updateArt(Art art) { 
		return repo.save(art);
	}
	public List<Art> findByName(String name) {
		// TODO Auto-generated method stub
		return repo.findByNom(name);
	}
	public List<Art> getAllByClientId(Long clientId) {
		// TODO Auto-generated method stub
		return repo.getAllByClientId(clientId);
	}
}
