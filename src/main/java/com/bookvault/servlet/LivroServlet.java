package com.bookvault.servlet;

import com.bookvault.model.Livro;
import com.bookvault.service.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/livro")
public class LivroServlet extends HttpServlet{

        ObjectMapper mapper = new ObjectMapper();
        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException{
                String json;
                if(req.getParameter("id") != null){
                        long id = Long.parseLong(req.getParameter("id"));
                        Livro livro = LivroService.consultar(id);
                        json = mapper.writeValueAsString(livro);
                        PrintWriter out = res.getWriter();
                        out.println(json);
                }
                else {
                        List<Livro> livros = LivroService.consultarTodos();
                        json = mapper.writeValueAsString(livros);
                        PrintWriter out = res.getWriter();
                        out.println(json);
                }
        }

        @Override
        public void doPost(HttpServletRequest req,HttpServletResponse res)
        throws IOException {
                String titulo = req.getParameter("titulo");
                String autor = req.getParameter("autor");
                String genero = req.getParameter("genero");
		String isbn = req.getParameter("isbn");
                Livro livro = new Livro(titulo,autor,genero,isbn);     
                LivroService.registrar(livro);
                PrintWriter out = res.getWriter();
                out.println("ID " + livro.getId() + " Livro " + livro.getTitulo() + " criado com sucesso");
        }

}
