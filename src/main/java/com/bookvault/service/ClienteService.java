package com.bookvault.service;
import com.bookvault.repository.GenericDAO;
import com.bookvault.model.*;
import java.util.Scanner;
import jakarta.persistence.*;
import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class ClienteService{
        
	static String nome,email,reputacao,telefone;

        public static void registrar(Cliente cliente){
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
                em.getTransaction().begin();
                dao.create(cliente);
                em.getTransaction().commit();
                em.close();
                emf.close();
        }

	public static Cliente consultar(long id){
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
		Cliente cliente = dao.find(Cliente.class,id);
                em.close();
                emf.close();
		return cliente;
	}

	public static List<Cliente> consultarTodos(){
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
		List<Cliente> clientes = dao.findAll(Cliente.class);
                em.close();
                emf.close();
		return clientes;
	}

}
