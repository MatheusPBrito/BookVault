package com.bookvault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.GeneratedValue;

@Entity
public class Funcionario {
	
	@Id
	@GeneratedValue
	long id;
	@Basic
	String nome,cidade,bairro,rua,cpf;
	int casa;

	public Funcionario(){

	}

	public Funcionario(String nome,String cidade, String bairro, String rua, int casa,String cpf){
		this.nome = nome;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.casa = casa;
		this.cpf = cpf;
	}

	public String getNome(){return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public String getCidade(){return cidade;}
	public void setCidade(String cidade) {this.cidade = cidade;}
	public String getBairro(){return bairro;}
	public void setBairro(String Bairro) {this.bairro = bairro;}
	public String getRua(){return rua;}
	public void setRua(String rua) {this.rua = rua;}
	public int getCasa(){return casa;}
	public void setCasa(int casa) {this.casa = casa;}
	public String getCPF(){return cpf;}
	public void setCPF(String cpf) {this.cpf = cpf;}
}
