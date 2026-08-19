package com.bookvault.service;
import com.bookvault.repository.GenericDAO;
import com.bookvault.model.Livro;
import java.util.Scanner;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
public class LivroService{


	public static void registrar(Livro livro){
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		em.getTransaction().begin();
		dao.create(livro);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public static Livro consultar(long id){
	  	EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
	        Livro livro = dao.find(Livro.class,id);
		return livro;
	 }

	public static List<Livro> consultarTodos(){
	  	EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
		return dao.findAll(Livro.class);
	
	}


}
