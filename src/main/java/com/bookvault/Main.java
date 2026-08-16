package com.bookvault;
import java.util.Scanner;
import com.bookvault.model.*;
import com.bookvault.service.*;

public class Main {
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	String opcao = "";
	boolean running = true;
	while (running){
		System.out.println("Escolha uma das opções:");
		System.out.println("1-Registrar Itens");
		System.out.println("2-Consultar Itens");
		System.out.println("3-Receber Devolução");
		System.out.println("4-Sair do programa");
		opcao = input.nextLine();
		switch(opcao){
			case "1":
				System.out.println("1-Registrar Cliente");
				System.out.println("2-Registrar Livro");
				System.out.println("3-Registrar Emprestimo");
				opcao = input.nextLine();
				switch(opcao){
					case "1":
						ClienteService.registrar();
						break;
					case "2":
						LivroService.registrar();
						break;
					case "3":
						EmprestimoService.registrar();
						break;
							
				}
				break;
			case "2":
				System.out.println("1-Consultar Cliente");
				System.out.println("2-Consultar Livro");
				System.out.println("3-Consultar Emprestimo");
				opcao = input.nextLine();
				switch(opcao){
					case "1":
						ClienteService.consultar();
						break;
					case "2":
						LivroService.consultar();
						break;
					case "3":
						EmprestimoService.consultar();
						break;
							
				}
				break;
			case "3":
				EmprestimoService.devolucao();
				break;
			case "4":
				running = false;
				break;
			default:
				System.out.println("Opção invalida!");
				input.nextLine();
				break;
		}
	}
    }
}
