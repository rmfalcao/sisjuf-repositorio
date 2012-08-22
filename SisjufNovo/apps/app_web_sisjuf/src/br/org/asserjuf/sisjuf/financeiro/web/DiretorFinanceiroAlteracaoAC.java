package br.org.asserjuf.sisjuf.financeiro.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

public class DiretorFinanceiroAlteracaoAC extends FrameworkServlet {

	private FinanceiroDelegate 	financeiroDelegate;
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try {
			DiretorFinanceiroVO diretorFinanceiroVO = new DiretorFinanceiroVO();

			diretorFinanceiroVO.setCodigo(new Integer(request.getParameter("codigo")));
			diretorFinanceiroVO.setNome(request.getParameter("nome"));

			if (request.getParameter("dataInicioGestao")!=null && !request.getParameter("dataInicioGestao").equals("")) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				diretorFinanceiroVO.setDataInicioGestao(formatter.parse(request.getParameter("dataInicioGestao")));
			}

			financeiroDelegate.updateDiretorFinanceiro(diretorFinanceiroVO);
			
			request.setAttribute("msg", "Registro alterado com sucesso.");
		} catch (SmartAppException e) {
			request.setAttribute("errorMsg", e.getMensagem());

		} catch (SmartEnvException e) {
			request.setAttribute("errorMsg", "Ocorreu um erro inesperado. Por favor, contate o administrador do sistema.");

		} catch (ParseException e) {
			request.setAttribute("errorMsg", "A data deve estar no formato DD/MM/AAAA");
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