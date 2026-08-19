package com.bookvault.service;
import com.bookvault.repository.GenericDAO;
import com.bookvault.model.*;
import java.util.Scanner;
import jakarta.persistence.*;
import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class MultaService{
	
	static Cliente cliente;
	static Livro livro;
	static Funcionario responsavel;

	public static void registrar(Multa multa){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		em.getTransaction().begin();
		dao.create(emprestimo);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public static Multa consultar(long id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		Multa multa = dao.find(Emprestimo.class,id);	
		em.close();
		emf.close();	
		return multa;
	}

	public static List<Multa> consultarTodos(){
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		List<Multa> multas = dao.findAll(Emprestimo.class);	
		em.close();
		emf.close();	
		return multas;
	}

}
