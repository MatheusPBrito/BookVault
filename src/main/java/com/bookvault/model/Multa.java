package com.bookvault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Basic;
import jakarta.persistence.ManyToOne;

@Entity
public class Multa{

	@Id
	@GeneratedValue
	long id;
	@ManyToOne
	Cliente cliente;
	@ManyToOne
	Livro livro;
	@ManyToOne
	Funcionario responsavel;
	@Basic
	double valor_da_multa;
	String situacao;

	public Multa(){
	
	}

	public Multa(Cliente cliente, Livro livro, Funcionario responsavel){
		this.cliente = cliente;
		this.livro = livro;
		this.responsavel =  responsavel;
		this.valor_da_multa = valor_da_multa;
		this.situacao = situacao;	
	}

	public Cliente getCliente(){return cliente;}
	public void setCliente(Cliente cliente){this.cliente = cliente;}
	public Livro getLivro(){return livro;}
	public void setLivro(Livro livro){this.livro = livro;}
	public Funcionario getResponsavel(){return responsavel;}
	public void setResponsavel(Funcionario responsavel){this.responsavel = responsavel;}
	public double getValorMulta(){return valor_da_multa;}
	public void setValorMulta(double valor_da_multa){this.valor_da_multa = valor_da_multa;}
	public String getSituacao(){return situacao;}
	public void setSituacao(String situacao){this.situacao = situacao;}
}
