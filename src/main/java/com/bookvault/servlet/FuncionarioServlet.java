package com.bookvault.servlet;

import com.bookvault.model.Funcionario;
import com.bookvault.service.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/funcionario")
public class FuncionarioServlet extends HttpServlet{

	ObjectMapper mapper = new ObjectMapper();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException{
		String json;
		if(req.getParameter("id") != null){
			long id = Long.parseLong(req.getParameter("id"));
			Funcionario funcionario = FuncionarioService.consultar(id);
			json = mapper.writeValueAsString(funcionario);
			PrintWriter out = res.getWriter();
			out.println(json);
		}
		else {
			List<Funcionario> funcionarios = FuncionarioService.consultarTodos();
			json = mapper.writeValueAsString(funcionarios);
			PrintWriter out = res.getWriter();
			out.println(json);
		}
	}

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException {
		String nome = req.getParameter("nome");
		String cidade = req.getParameter("cidade");
		String bairro = req.getParameter("bairro");
		String rua = req.getParameter("rua");
		int casa = Integer.parseInt(req.getParameter("casa"));
		String cpf = req.getParameter("cpf");
		Funcionario funcionario = new Funcionario(nome,cidade,bairro,rua,casa,cpf);	
		FuncionarioService.registrar(funcionario);
		PrintWriter out = res.getWriter();
		out.println("ID " + funcionario.getId() + " Funcionario " + funcionario.getNome() + " criado com sucesso");
	}
}
