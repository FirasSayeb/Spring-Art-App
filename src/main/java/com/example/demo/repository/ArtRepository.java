package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Art;
import com.example.demo.domain.Client;


public interface ArtRepository extends JpaRepository<Art, Long>  {

	List<Art> findByClient(Client client);

	List<Art> findByNom(String nom);

	List<Art> getAllByClientId(Long clientId);
         
     
} 
  