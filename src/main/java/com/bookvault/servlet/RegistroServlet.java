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

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet{

	ObjectMapper mapper = new ObjectMapper();
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException {
		String nome = req.getParameter("nome");
		String cidade = req.getParameter("cidade");
		String bairro = req.getParameter("bairro");
		String rua = req.getParameter("rua");
		int casa = Integer.parseInt(req.getParameter("casa"));
		String cpf = req.getParameter("cpf");
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		Funcionario funcionario = new Funcionario(nome,cidade,bairro,rua,casa,cpf,email,senha);
		FuncionarioService.registrar(funcionario);
		PrintWriter out = res.getWriter();
		out.println("ID " + funcionario.getId() + " Funcionario " + funcionario.getNome() + " criado com sucesso");
	}
}

