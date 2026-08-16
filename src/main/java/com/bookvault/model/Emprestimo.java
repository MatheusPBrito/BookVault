package com.bookvault;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Basic;
import jakarta.persistence.ManyToOne;
import java.time.*;

@Entity
public class Emprestimo{

	@Id
	@GeneratedValue
	long id;
	@Basic
	String situacao;
	LocalDate dataEmprestimo = LocalDate.now(), 
		  dataDevolucao = dataEmprestimo.plusDays(30);
	@ManyToOne
	Cliente cliente;
	@ManyToOne
	Livro livro;
	@ManyToOne
	Funcionario responsavel;

	public Emprestimo() {
			
	}

	public Emprestimo(Cliente cliente, Livro livro, String situacao){
		this.cliente = cliente;
		this.livro = livro;
		//this.responsavel = responsavel;
		this.situacao = situacao;
	}

	public Cliente getCliente(){return cliente;}
        public void setCliente(Cliente cliente) {this.cliente = cliente;}
	public Livro getLivro(){return livro;}
        public void setLivro(Livro livro) {this.livro = livro;}
	public Funcionario getResponsavel(){return responsavel;}
        public void setResponsavel(Funcionario responsavel) {this.responsavel = responsavel;}
	public String getSituacao(){return situacao;}
        public void setSituacao(String situacao) {this.situacao = situacao;}
	public LocalDate getDataDevolucao(){return dataDevolucao;}
	public void print(){System.out.println("ID: " + id + " Cliente: " + cliente.getNome() + " Livro: " + livro.getTitulo() + " Situação: " + situacao + " Emprestado em: " + dataEmprestimo + " Data de devolução: " + dataDevolucao);}
}

