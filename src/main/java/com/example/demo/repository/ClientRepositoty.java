package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Art;
import com.example.demo.domain.Client;



public interface ClientRepositoty extends JpaRepository<Client, Long>  {

	 Client findByEmailAndPassword(String email, String password);

	

	
 
	  
	 
}
 