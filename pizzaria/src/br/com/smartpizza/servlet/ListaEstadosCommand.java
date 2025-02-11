package br.com.smartpizza.servlet;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.smartpizza.dao.EstadoDAO;
import br.com.smartpizza.model.Estado;
@WebServlet(urlPatterns = { "/admin/adminis","/cliente"})
public class ListaEstadosCommand extends HttpServlet  {
	private EstadoDAO estadoDAO = new EstadoDAO();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String proximo = "";
		String param = req.getParameter("param");
		String acao = req.getParameter("acao");
	

		if(param.equalsIgnoreCase("admin")) {
		List<Estado> estados = estadoDAO.listarEstados();
		req.setAttribute("estados", estados);
		proximo = "cadastrar-cliente.jsp";
		}
		else if(param.contentEquals("adminFun")) {
			List<Estado> estados = estadoDAO.listarEstados();
			req.setAttribute("estados", estados);
			proximo = "cadastrar-funcionario.jsp";
		}
		else if(param.equalsIgnoreCase("cliente")) {
			List<Estado> estados = estadoDAO.listarEstados();
			req.setAttribute("estados", estados);
			proximo = "cadastroCliente.jsp";
		}
		else if(param.equalsIgnoreCase("func")) {
			List<Estado> estados = estadoDAO.listarEstados();
			req.setAttribute("estados", estados);
			proximo = "cadastroCliente.jsp";
		}
		
		req.getRequestDispatcher(proximo).forward(req, resp);
	}
	

}
