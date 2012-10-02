package br.org.asserjuf.sisjuf.services.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;
import br.org.asserjuf.sisjuf.services.ServiceCorrigeLancamentosDuplicados;

public class CorrigeLancamentosDuplicadosAC extends FrameworkServlet {


	private FinanceiroDelegate 	financeiroDelegate;
	
	@Override
	public void initFrameworkServlet() {

		System.out.print("***** INICIANDO THREAD CORRIGE LANÇAMENTOS... ");
		
		WebApplicationContext context = getWebApplicationContext();
		
		ServiceCorrigeLancamentosDuplicados thread = ServiceCorrigeLancamentosDuplicados.getInstance((FinanceiroDelegate)context.getBean("financeiroDelegate"));
		
		thread.start();
		
		System.out.println("[OK]");
	}
	
	@Override
	protected void doService(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {

		
	}

	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
	
}
