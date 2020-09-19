package br.org.asserjuf.sisjuf.seguranca.cliente.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;


public class LogoutAC extends FrameworkServlet {
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		request.getRequestDispatcher("/login.jsf").forward(request, response);
	}

	protected void initFrameworkServlet() throws ServletException, BeansException {
//		WebApplicationContext context = getWebApplicationContext();
		
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
	
}
