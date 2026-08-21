package com.bookvault.service;

import com.bookvault.repository.GenericDAO;
import com.bookvault.model.*;
import com.bookvault.service.MultaService;
import jakarta.persistence.*;
import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class EmprestimoService{
	
	static Cliente cliente;
	static Livro livro;
	static Funcionario responsavel;

	public static void registrar(Emprestimo emprestimo){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		em.getTransaction().begin();
		dao.create(emprestimo);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public static Emprestimo consultar(long id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		Emprestimo emprestimo = dao.find(Emprestimo.class,id);	
		if (emprestimo.getDataDevolucao().isBefore(LocalDate.now())){
			emprestimo.setSituacao("atrassado");	
			emprestimo.getCliente().setReputacao("ruim");
			Multa multa = new Multa(emprestimo.getCliente(),emprestimo.getLivro(),emprestimo.getResponsavel());
			MultaService.registrar(multa);
		}
		em.close();
		emf.close();	
		return emprestimo;
	}

	public static List<Emprestimo> consultarTodos(){

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
                List<Emprestimo> emprestimos = dao.findAll(Emprestimo.class);
		for (Emprestimo emprestimo : emprestimos){
			if (emprestimo.getDataDevolucao().isBefore(LocalDate.now())){
				emprestimo.setSituacao("atrassado");	
				emprestimo.getCliente().setReputacao("ruim");
				Multa multa = new Multa(emprestimo.getCliente(),emprestimo.getLivro(),emprestimo.getResponsavel());
				MultaService.registrar(multa);
			}
		}
                em.close();
                emf.close();
                return emprestimos;
       }


	static public void devolucao(long id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		Emprestimo emprestimo = dao.find(Emprestimo.class,id);
		if(emprestimo != null){
			Livro livro = emprestimo.getLivro();
			livro.setDisponivel(true);
			emprestimo.setSituacao("Devolvido");
			em.getTransaction().begin();
			em.getTransaction().commit();
			em.close();
		}
		else{
			System.out.println("Emprestimo não encontrado!");	
		}
		emf.close();
	}


}
