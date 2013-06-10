package br.org.asserjuf.sisjuf.associados.cliente.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.cliente.AssociadoDelegate;

/**
 * Servlet da tela de impressão de carteirinhas.
 * @author Rodrigo Falcão
 *
 */
public class CarteirinhaImpressaoAC extends FrameworkServlet {

	private AssociadoDelegate associadoDelegate;
	
	/**
	 * Representa a implementação do corpo de execução da servlet. Instancia o delegate e seta os valores necessários no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{

		AssociadoVO associado = new AssociadoVO();
		
		associado.setCodigo(new Integer(request.getParameter("codigo")));
		
		associado = associadoDelegate.findAssociadoByPrimaryKey(associado);
		
		request.setAttribute("associado", associado);
		request.setAttribute("categoria", associado.getStatusCategoria().equals("C")?"Sócio Contribuinte":"Sócio Usuário");

		request.getRequestDispatcher("/associados/carteirinhaPrint.jsp").forward(request, response);
	}

	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		
		associadoDelegate = (AssociadoDelegate)context.getBean("associadoDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
}