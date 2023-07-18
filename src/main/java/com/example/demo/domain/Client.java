package com.example.demo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
   
@Entity
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String email;
	private String password;
	@OneToMany(mappedBy="client")
    private List<Art> boughtArts;  
	public Long getId() {  
		return id; 
	} 
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() { 
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Client(Long id, String nom, String email, String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.password = password;
	}
	public Client() {
		super();
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", email=" + email + ", password=" + password + ", boughtArts="
				+ boughtArts + "]";
	}
	  
}
