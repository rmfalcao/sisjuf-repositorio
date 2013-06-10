package br.org.asserjuf.sisjuf.financeiro.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.ExtratoAssembler;
import br.org.asserjuf.sisjuf.financeiro.ExtratoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.seguranca.vo.UsuarioVO;

/**
 * Servlet da tela de impressão de extrato.
 * @author Paulo Prado
 */
public class ExtratoImpressaoAC extends FrameworkServlet {

	private FinanceiroDelegate 	financeiroDelegate;
	private UtilDelegate		utilDelegate;
	
	/**
	 * Representa a implementação do corpo de execução da servlet. Instancia o delegate e seta os valores necessários no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{

		SimpleDateFormat formatter 			= new SimpleDateFormat("dd/MM/yyyy");
				
		request.setAttribute("dataAtual", formatter.format(utilDelegate.getCurrentDate()));
		
		
		// montar objeto de filtro
		
		ExtratoFiltroAssembler assembler = new ExtratoFiltroAssembler();
		
		try {
			assembler.setDataInicial(formatter.parse(request.getParameter("dataInicial")));
			assembler.setDataFinal(formatter.parse(request.getParameter("dataFinal")));

		} catch (ParseException e) {
			throw new SmartEnvException(e);
			
		}
		
		assembler.setContaVO(new ContaVO(new Integer(request.getParameter("conta"))));
				
		ExtratoAssembler extratoAssembler = financeiroDelegate.obterExtratoConta(assembler);
		
		request.setAttribute("extrato", extratoAssembler);
		
		UsuarioVO usuarioVO = (UsuarioVO)  request.getSession().getAttribute("usuario");
		
		request.setAttribute("nomeUsuario", usuarioVO.getNome());
		request.setAttribute("nomeDiretor", extratoAssembler.getDiretorFinanceiroVO()==null?"":extratoAssembler.getDiretorFinanceiroVO().getNome());

		request.getRequestDispatcher("/financeiro/extratoPrint.jsp").forward(request, response);
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