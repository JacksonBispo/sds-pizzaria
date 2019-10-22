package br.com.smartpizza.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import br.com.smartpizza.bo.UsuarioBO;
import br.com.smartpizza.dao.PessoaDAO;
import br.com.smartpizza.dao.UsuarioDAO;
import br.com.smartpizza.dto.PessoaDTO;
import br.com.smartpizza.model.Usuario;

public class LoginAdmCommand implements Command{
	

	private UsuarioDAO userDAO;
	private PessoaDAO pessoaDAO;
	private PessoaDTO  pessoaLog;
	private UsuarioBO userBO;
	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response) {
		this.userBO = new UsuarioBO();
		// TODO Auto-generated method stub
		userDAO = new UsuarioDAO();
		pessoaDAO = new PessoaDAO();
		
		String proximo = "login.jsp";
		
		
			
			String usuario = request.getParameter("usuario");
			String senha = request.getParameter("senha");
			Usuario user = new Usuario();
			user.setLogin(usuario);
			user.setSenha(senha);
			
			try {		
				Usuario us = userDAO.consultarUsuario(user);
				
			if(us!=null) {
				
				if(us.getGpUs().equals("Admin")) {
					proximo = "home-admin.jsp";
				}else {
					request.setAttribute("msgErro", "falha na autenticação!");
				}
				pessoaLog = pessoaDAO.getPessoa(user.getId());
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
				session.setAttribute("pessoa", pessoaLog);
				session.setMaxInactiveInterval(60*10);
				 
		}else {
			proximo = "login.jsp";
			request.setAttribute("msgErro", "falha na autenticação!");
		}
		}catch(Exception e) {
			e.printStackTrace();
			proximo = "login.jsp";
			request.setAttribute("msgErro", "falha na autenticação");
		}
		
		
		return proximo;
		
		
	}

}
