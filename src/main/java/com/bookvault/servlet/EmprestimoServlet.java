package com.bookvault.servlet;

import com.bookvault.model.Emprestimo;
import com.bookvault.service.EmprestimoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
}
