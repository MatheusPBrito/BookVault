package com.bookvault.servlet;

import com.bookvault.model.Cliente;
import com.bookvault.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet{

	ObjectMapper mapper = new ObjectMapper();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException{
		long id = Long.parseLong(req.getParameter("id"));
		Cliente cliente = ClienteService.consultar(id);
		String json = mapper.writeValueAsString(cliente);
		PrintWriter out = res.getWriter();
		out.println(json);
	}

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException {
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String telefone = req.getParameter("telefone");
		Cliente cliente = new Cliente(nome,email,telefone);	
		ClienteService.registrar(cliente);
		PrintWriter out = res.getWriter();
		out.println("ID " + cliente.getId() + " Cliente " + cliente.getNome() + " criado com sucesso");
	}

}
