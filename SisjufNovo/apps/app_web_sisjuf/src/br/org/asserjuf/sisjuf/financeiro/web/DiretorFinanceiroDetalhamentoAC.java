package br.org.asserjuf.sisjuf.financeiro.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.org.asserjuf.sisjuf.financeiro.DiretorFinanceiroVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

public class DiretorFinanceiroDetalhamentoAC extends FrameworkServlet {

	private FinanceiroDelegate 	financeiroDelegate;
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{

		DiretorFinanceiroVO diretorFinanceiroVO = new DiretorFinanceiroVO();

		diretorFinanceiroVO.setCodigo(new Integer(request.getParameter("codigo")));
		
		
		request.setAttribute("diretorFinanceiroVO", financeiroDelegate.findDiretorFinanceiroByPrimaryKey(diretorFinanceiroVO));

		request.getRequestDispatcher("/financeiro/diretorFinanceiroForm.jsp").forward(request, response);
	}

	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		
		financeiroDelegate = (FinanceiroDelegate)context.getBean("financeiroDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
}