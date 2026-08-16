package com.bookvault.service;
import com.bookvault.repository.GenericDAO;
import com.bookvault.model.*;
import java.util.Scanner;
import jakarta.persistence.*;
import java.time.*;
import java.util.List;
import java.util.ArrayList;
public class LivroService{

	static Scanner input = new Scanner(System.in);

	public static void registrar(){
	
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		GenericDAO dao = new GenericDAO(em);
		String titulo,autor,genero,isbn;
		double valor_para_emprestimo;
		System.out.println("Digite o Titulo do livro");	
		titulo = input.nextLine();
		System.out.println("Digite o Gênero do livro");
		genero = input.nextLine();
		System.out.println("Digite o Autor do livro");
		autor = input.nextLine();
		System.out.println("Digite o ISBN do livro");
		isbn = input.nextLine();
		while(true){
			System.out.println("Digite o valor para emprestimo");	
			if (input.hasNextDouble()){
				valor_para_emprestimo = input.nextDouble();	
				break;
			}
			System.out.println("Valor invalido");
		}	
		em.getTransaction().begin();
		dao.create(new Livro(titulo,autor,genero,isbn,valor_para_emprestimo));
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public static void consultar(){
	  	EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
                EntityManager em = emf.createEntityManager();
                GenericDAO dao = new GenericDAO(em);
                while(true){
                        System.out.println("1-Consultar livro por ID");
                        System.out.println("2-Consultar todos os livros");
                        String opcao = input.nextLine();
                        if(opcao.equals("1")){
                                long id = 1;
                                while(true){
                                        System.out.println("Escreva o ID: ");
                                        if(input.hasNextLong()){
                                                id = input.nextLong();
                                                break;
                                        }
                                        else
                                            System.out.println("Opção invalida!");
                                }
                                Livro livro = dao.find(Livro.class,id);
                                System.out.println("ID: " + livro.getId() + " Titulo: " + livro.getTitulo() + " Genero: " + livro.getGenero() + " Autor: " + livro.getAutor() + " ISBN: " + livro.getIsbn() + " valor para emprestimo: " + livro.getValorEmprestimo());
                                break;
                        }
                        else if(opcao.equals("2")){
                                List<Livro> livros = dao.findAll(Livro.class);
                                for (Livro livro : livros){
                                System.out.println("ID: " + livro.getId() + " Titulo: " + livro.getTitulo() + " Genero: " + livro.getGenero() + " Autor: " + livro.getAutor() + " ISBN: " + livro.getIsbn() + " valor para emprestimo: " + livro.getValorEmprestimo());
                                }
                                break;
                        }
                        else
				   System.out.println("Opção invalida");
	      }

	}

}
