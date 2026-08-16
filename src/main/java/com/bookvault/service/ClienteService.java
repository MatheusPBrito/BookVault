package com.bookvault.service;
import com.bookvault.repository.GenericDAO;
import com.bookvault.model.*;
import java.util.Scanner;
import jakarta.persistence.*;
import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class ClienteService{
        
        static Scanner input = new Scanner(System.in);
	static String nome,email,reputacao,telefone;

        public static void registrar(){
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
                
		System.out.println("Insira o nome do cliente:");
		nome = input.nextLine();
		System.out.println("Insira o email do cliente");
		email = input.nextLine();
		System.out.println("Insira o telefone do cliente");
		telefone = input.nextLine();
                em.getTransaction().begin();
                dao.create(new Cliente(nome,email,telefone));
                em.getTransaction().commit();
                em.close();
                emf.close();
        }

	public static void consultar(){
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
		while(true){
			System.out.println("1-Consultar cliente por ID");
			System.out.println("2-Consultar todos os clientes");
			String opcao = input.nextLine();
			if(opcao.equals("1")){
				long id = 1;
				while(true){
					System.out.println("Escreva o ID: ");
					if(input.hasNextLong()){
						id = input.nextLong();
						break;
					}
					else{
					    System.out.println("Opção invalida!");
					}
				}
				Cliente cliente = dao.find(Cliente.class,id);
				System.out.println("ID: " + cliente.getId() + " Nome: " + cliente.getNome() + " Email: " + cliente.getEmail() + " Telefone: " + cliente.getTelefone() + " Reputação do cliente: " + cliente.getReputacao());
				break;
			}
			else if(opcao.equals("2")){
				List<Cliente> clientes = dao.findAll(Cliente.class);
				for (Cliente cliente : clientes){
				System.out.println("ID: " + cliente.getId() + " Nome: " + cliente.getNome() + " Email: " + cliente.getEmail() + " Telefone: " + cliente.getTelefone() + " Reputação do cliente: " + cliente.getReputacao());
				}
				break;
			}
			else
				System.out.println("Opção invalida");
		}
	}
}
