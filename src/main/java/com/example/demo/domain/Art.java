package com.example.demo.domain;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Art {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;       
	@ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
	private double prix;
	private String nom; 
	@Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] data; 
	 @Transient
	    private String base64ImageData;        
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getBase64ImageData() {
        return base64ImageData;
    }

    public void setBase64ImageData(String base64ImageData) {
        this.base64ImageData = base64ImageData;
    }
	public Art(Long id, Client client, double prix, String nom, byte[] data) {
		super();
		this.id = id;
		this.client = client;
		this.prix = prix;
		this.nom = nom;
		this.data = data;
	}
	public Art() {
		super();
	}
	@Override
	public String toString() {
		return "Art [id=" + id + ", client=" + client + ", prix=" + prix + ", nom=" + nom + ", data="
				+ Arrays.toString(data) + "]";  
	}
	
  
}
