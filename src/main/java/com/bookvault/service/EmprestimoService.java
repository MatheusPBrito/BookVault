package com.bookvault.service;
import com.bookvault.repository.GenericDAO;
import com.bookvault.model.*;
import java.util.Scanner;
import jakarta.persistence.*;
import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class EmprestimoService{
	
	static Scanner input = new Scanner(System.in);
	static Cliente cliente;
	static Livro livro;

	public static void registrar(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		
		while(true){
			System.out.println("Escreva o ID do usuário: ");
			if (!input.hasNextLong()){
				System.out.println("informação incorreta");
				continue;
			}
			cliente = dao.find(Cliente.class,input.nextLong());
			break;
		}
		while(true){
			System.out.println("Escreva o ID do livro: ");
			if (!input.hasNextLong()){
				System.out.println("informação incorreta");
				continue;
			}
			livro = dao.find(Livro.class,input.nextLong());
			if (!livro.getDisponivel()){
				System.out.println("Livro indisponivel!");
				continue;
			}
			break;
		}
		em.getTransaction().begin();
		dao.create(new Emprestimo(cliente,livro,"pendente"));
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public static void consultar(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		List<Emprestimo> emprestimos = new ArrayList<>();	
		while(true){
			System.out.println("Selecione a forma de busca: ");
			System.out.println("1-ID do Emprestimo");	
			System.out.println("2-ID do Cliente");
			System.out.println("3-ID do Livro");	
			System.out.println("4-Listar todos");
			System.out.println("5-Cancelar");
			if(input.hasNextInt()){
				int opcao = input.nextInt();
				if(opcao == 5)
					break;
				if(opcao == 4){
					emprestimos = dao.findAll(Emprestimo.class);
					break;	
				}
				System.out.println("Escreva o ID");
				if(opcao == 1){
					if(input.hasNextLong()){
						long id = input.nextLong();
						Emprestimo emprestimo = dao.find(Emprestimo.class,id);	
						if(emprestimo != null){
							emprestimos.add(emprestimo); 
							break;
						}
					}	
				}
				if(opcao == 2){
						if(input.hasNextLong()){
							long id = input.nextLong();
							cliente = dao.find(Cliente.class,id);	
							if(cliente != null){
								emprestimos = dao.findAllWith(Emprestimo.class,"cliente",cliente);
								break;
							}
						}	
				}
				if(opcao == 3){
						if(input.hasNextLong()){
							long id = input.nextLong();
							livro = dao.find(Livro.class,id);	
							if(cliente != null){
								emprestimos = dao.findAllWith(Emprestimo.class,"livro_id",livro);
								break;
							}
						}	
				}
				System.out.println("não encontrado");
			}
		}
		if(emprestimos.size() >= 1){
			for (Emprestimo emprestimo : emprestimos){
				if (LocalDate.now().isAfter(emprestimo.getDataDevolucao())){
					em.getTransaction().begin();
					emprestimo.setSituacao("atrassado");
					emprestimo.getCliente().setReputacao("devolvedor");
					em.getTransaction().commit();
					em.close();
				}
				emprestimo.print();	
			}
		}
		emf.close();	

	}

	static public void devolucao(){
		long id = 0;
		while(true){
			System.out.println("Digite o id do emprestimo: ");
			if(input.hasNextLong()){
				id = input.nextLong();
				break;
			}
			else
				System.out.println("Valor invalido, digite um número!");
		}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		Emprestimo emprestimo = dao.find(Emprestimo.class,id);
		if(emprestimo != null){
			Livro livro = emprestimo.getLivro();
			livro.setDisponivel(true);
			em.getTransaction().begin();
			emprestimo.setSituacao("Devolvido");
			em.getTransaction().commit();
			em.close();
		}
		else{
			System.out.println("Emprestimo não encontrado!");	
		}
		emf.close();
	
	}


}
