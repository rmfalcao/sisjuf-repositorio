package br.org.asserjuf.sisjuf.financeiro.web;

import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.seguranca.vo.UsuarioVO;

/**
 * Servlet da tela de impressão de bancos.
 * @author Rodrigo Falcão
 *
 */
public class BancoImpressaoAC extends FrameworkServlet {

	private FinanceiroDelegate 	financeiroDelegate;
	private UtilDelegate		utilDelegate;
	
	/**
	 * Representa a implementação do corpo de execução da servlet. Instancia o delegate e seta os valores necessários no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		request.setAttribute("dataAtual", formatter.format(utilDelegate.getCurrentDate()));
		request.setAttribute("bancos", financeiroDelegate.findAllBanco());

		UsuarioVO usuarioVO = (UsuarioVO)  request.getSession().getAttribute("usuario");
		
		request.setAttribute("nomeUsuario", usuarioVO.getNome());
		
		request.getRequestDispatcher("/financeiro/bancoPrint.jsp").forward(request, response);
	}

	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		
		financeiroDelegate = (FinanceiroDelegate)context.getBean("financeiroDelegate");
		utilDelegate = (UtilDelegate)context.getBean("utilDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
}