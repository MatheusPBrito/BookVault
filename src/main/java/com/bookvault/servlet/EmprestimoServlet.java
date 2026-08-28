package com.bookvault.servlet;

import com.bookvault.model.*;
import com.bookvault.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/emprestimo")
public class EmprestimoServlet extends HttpServlet {

	ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	@Override	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws IOException {
		long id = Long.parseLong(req.getParameter("id"));	
		Emprestimo emprestimo = EmprestimoService.consultar(id);
		String json =  mapper.writeValueAsString(emprestimo);
		PrintWriter out = res.getWriter();
		out.println(json);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException {
		HttpSession session = req.getSession(false);
		Long funcionarioID = (Long) session.getAttribute("funcionarioID");
		Funcionario responsavel = FuncionarioService.consultar(funcionarioID);
		Cliente cliente = ClienteService.consultar("email",req.getParameter("email"));
		Livro livro = LivroService.consultar(Long.parseLong(req.getParameter("livro")));
		Emprestimo emprestimo = new Emprestimo(cliente,livro,responsavel);	
		EmprestimoService.registrar(emprestimo);
		PrintWriter out = res.getWriter();
		out.println("Emprestimo registrado com sucesso!");
	}
}
