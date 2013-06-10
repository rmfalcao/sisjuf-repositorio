package br.org.asserjuf.sisjuf.financeiro.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

public class DiretorFinanceiroListagemAC extends FrameworkServlet {

	private FinanceiroDelegate 	financeiroDelegate;
	private static transient Logger LOG = Logger.getLogger(DiretorFinanceiroListagemAC.class);
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setAttribute("diretoresFinanceiros", financeiroDelegate.findAllDiretorFinanceiro());
		request.getRequestDispatcher("/financeiro/diretorFinanceiroConsulta.jsp").forward(request, response);
	}
	
	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		
		financeiroDelegate = (FinanceiroDelegate)context.getBean("financeiroDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
}