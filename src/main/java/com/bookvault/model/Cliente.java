package com.bookvault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;

@Entity
public class Cliente{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@Basic
	String nome,email,telefone,reputacao;

	public Cliente(){
		reputacao = "boa";
	}

	public Cliente(String nome, String email, String telefone){
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public long getId(){return id;}

	public String getNome(){return nome;}
	public void setNome(String nome) {this.nome = nome;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public String getReputacao() {return reputacao;}
	public void setReputacao(String reputacao) {this.reputacao = reputacao;}

	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}
}
