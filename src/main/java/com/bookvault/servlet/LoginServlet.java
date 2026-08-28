package com.bookvault.servlet;

import com.bookvault.model.Funcionario;
import com.bookvault.service.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException {
		String email = req.getParameter("email");	
		String senha = req.getParameter("senha");
		Funcionario funcionario = FuncionarioService.consultar("email",email);
		if (funcionario != null){
			if(BCrypt.checkpw(senha,funcionario.getSenha())){
				HttpSession session = req.getSession();
				session.setAttribute("funcionarioID",funcionario.getId());	
				res.getWriter().println("Login realizado com sucesso");
			}
			else
				res.getWriter().println("Credenciais incorretas");
		}
		else
			res.getWriter().println("usuario não existe");

	}	
}
