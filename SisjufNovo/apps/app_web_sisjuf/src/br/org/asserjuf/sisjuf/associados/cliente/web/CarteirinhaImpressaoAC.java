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
 * Servlet da tela de impressï¿½o de carteirinhas.
 * @author Rodrigo Falcï¿½o
 *
 */
public class CarteirinhaImpressaoAC extends FrameworkServlet {

	private AssociadoDelegate associadoDelegate;
	
	/**
	 * Representa a implementaï¿½ï¿½o do corpo de execuï¿½ï¿½o da servlet. Instancia o delegate e seta os valores necessï¿½rios no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{

		AssociadoVO associado = new AssociadoVO();
		
		associado.setCodigo(new Integer(request.getParameter("codigo")));
		
		associado = associadoDelegate.findAssociadoByPrimaryKey(associado);
		
		request.setAttribute("associado", associado);
		String strStatusCategoria = null;
		
		if (associado.getStatusCategoria().equals("C")) {
		
			strStatusCategoria = "Sócio Contribuinte";
			
		} else if (associado.getStatusCategoria().equals("O")) {
			
			strStatusCategoria = "Sócio Conveniado";
			
		} else {
			
			strStatusCategoria = "Sócio Usuário";
			
		}
		
		request.setAttribute("categoria", strStatusCategoria);

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