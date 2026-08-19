package com.bookvault.service;
import com.bookvault.repository.GenericDAO;
import com.bookvault.model.Funcionario;
import java.util.Scanner;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

public class FuncionarioService{
        
        public static void registrar(Funcionario funcionario){
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
                em.getTransaction().begin();
                dao.create(funcionario);
                em.getTransaction().commit();
                em.close();
                emf.close();
        }

	public static Funcionario consultar(long id){
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
		Funcionario funcionario = dao.find(Funcionario.class,id);
                em.close();
                emf.close();
		return funcionario;
	}

	public static List<Funcionario> consultarTodos(){
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
		List<Funcionario> funcionarios = dao.findAll(Funcionario.class);
                em.close();
                emf.close();
		return funcionarios;
	}

}
