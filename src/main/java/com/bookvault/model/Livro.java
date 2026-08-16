package com.bookvault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Basic;
import jakarta.persistence.GenerationType;

@Entity
public class Livro{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	boolean disponivel = true;
	@Basic
	String titulo,autor,genero,isbn;
	double valor_para_emprestimo;

	public Livro(){

	}

	public Livro(String titulo, String autor, String genero,String isbn, double valor_para_emprestimo){
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.isbn = isbn;
		this.valor_para_emprestimo = valor_para_emprestimo;
	}
	
	public long getId(){return id;}
	public String getTitulo(){return titulo;}
        public void setTitulo(String titulo) {this.titulo = titulo;}
	public String getAutor(){return autor;}
        public void setAutor(String autor) {this.autor = autor;}
	public String getGenero(){return genero;}
        public void setGenero(String genero) {this.genero = genero;}
	public String getIsbn(){return isbn;}
        public void setIsbn(String isbn) {this.isbn = isbn;}
	public double getValorEmprestimo(){return valor_para_emprestimo;}
        public void setValorEmprestimo(double valor_para_emprestimo) {this.valor_para_emprestimo = valor_para_emprestimo;}
	public boolean getDisponivel() {return disponivel;}
	public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}
