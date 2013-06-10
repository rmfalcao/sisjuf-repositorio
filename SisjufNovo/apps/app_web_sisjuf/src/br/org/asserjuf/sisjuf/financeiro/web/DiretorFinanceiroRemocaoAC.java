package br.org.asserjuf.sisjuf.financeiro.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.DiretorFinanceiroVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

public class DiretorFinanceiroRemocaoAC extends FrameworkServlet {

	private FinanceiroDelegate 	financeiroDelegate;
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DiretorFinanceiroVO diretorFinanceiroVO = new DiretorFinanceiroVO();
		diretorFinanceiroVO.setCodigo(new Integer(request.getParameter("codigo")));
		
		try {
			financeiroDelegate.removeDiretorFinanceiro(diretorFinanceiroVO);

			request.setAttribute("msg", "Registro removido com sucesso.");
			
		} catch (SmartAppException e) {
			request.setAttribute("errorMsg", e.getMensagem());

		} catch (SmartEnvException e) {
			request.setAttribute("errorMsg", "Ocorreu um erro inesperado. Por favor, contate o administrador do sistema.");

		}
		
		request.getRequestDispatcher("/Financeiro/DiretorFinanceiro/Listar").forward(request, response);
	}

	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		
		financeiroDelegate = (FinanceiroDelegate)context.getBean("financeiroDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
}