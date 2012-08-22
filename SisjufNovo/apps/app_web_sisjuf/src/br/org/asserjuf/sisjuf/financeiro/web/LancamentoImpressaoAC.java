package br.org.asserjuf.sisjuf.financeiro.web;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import com.vortice.seguranca.vo.UsuarioVO;

import br.com.falc.smartFW.action.SmartHttpServlet;
import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

/**
 * Servlet da tela de impressão de consulta de lançamentos.
 * @author Paulo Prado
 */
public class LancamentoImpressaoAC extends FrameworkServlet {

	private FinanceiroDelegate 	financeiroDelegate;
	private UtilDelegate		utilDelegate;
	
	/**
	 * Representa a implementação do corpo de execução da servlet. Instancia o delegate e seta os valores necessários no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{

		SimpleDateFormat formatter 			= new SimpleDateFormat("dd/MM/yyyy");
				
		request.setAttribute("dataAtual", formatter.format(utilDelegate.getCurrentDate()));
		
		LancamentoFiltroAssembler assembler = new LancamentoFiltroAssembler();
		
		try {
			assembler.setDataEfetivacaoInicial(formatter.parse(request.getParameter("dataIni")));
			assembler.setDataEfetivacaoFinal(formatter.parse(request.getParameter("dataFim")));
			assembler.setFlgEfetivacao(request.getParameter("fEfet"));
			assembler.setFlgPrevisao(request.getParameter("fPrev"));
			if (request.getParameter("conta")!= null && !request.getParameter("conta").equals("")) {
				assembler.setContaVO(new ContaVO(new Integer(request.getParameter("conta"))));
				assembler.setContaVO(financeiroDelegate.findContaByPrimaryKey(assembler.getContaVO()));
			}
			if (request.getParameter("origem")!= null && !request.getParameter("origem").equals("")) {
				assembler.setOrigemLancamentoVO(new OrigemLancamentoVO(new Integer(request.getParameter("origem"))));
			}
			if (request.getParameter("tipo")!= null && !request.getParameter("tipo").equals("")) {
				assembler.setTipoLancamentoVO(new TipoLancamentoVO(new Integer(request.getParameter("tipo"))));
			}
			if (request.getParameter("op")!= null && !request.getParameter("op").equals("")) {
				assembler.setTipoOperacaoVO(new TipoOperacaoVO(new Integer(request.getParameter("op"))));
			}
			if (request.getParameter("vMenor")!= null && !request.getParameter("vMenor").equals("")) {
				assembler.setValorMenor(new Double(request.getParameter("vMenor").replace(".", "").replace(",", ".")));
			}
			if (request.getParameter("vMaior")!= null && !request.getParameter("vMaior").equals("")) {
				assembler.setValorMaior(new Double(request.getParameter("vMaior").replace(".", "").replace(",", ".")));
			}
			assembler.setFlgNaoQuitado(request.getParameter("fNQuit"));


		} catch (ParseException e) {
			throw new SmartEnvException(e);
			
		}
		
		LancamentoAssembler lancamentoAssembler =financeiroDelegate.findLancamentoByFilter(assembler); 
		
		Collection<LancamentoVO> lancamentos = lancamentoAssembler.getLancamentos();
		
		Double totalLancado = new Double(0);
		Double totalPago = new Double(0);
		
		Iterator<LancamentoVO> iterator = lancamentos.iterator();
		   
	   while (iterator.hasNext()) {
		   LancamentoVO temp = iterator.next();
		   
		   totalLancado += temp.getValor();
		   totalPago += temp.getValorPago();
	   }
	   
		NumberFormat	 numberFormatter	= NumberFormat.getInstance();
		numberFormatter.setMinimumFractionDigits(2);
		
		request.setAttribute("lancamentos", lancamentos);
		request.setAttribute("totalLancado", numberFormatter.format(totalLancado));
		request.setAttribute("totalPago", numberFormatter.format(totalPago));
		
		UsuarioVO usuarioVO = (UsuarioVO)  request.getSession().getAttribute("usuario");
		
		request.setAttribute("nomeUsuario", usuarioVO.getNome());
		request.setAttribute("nomeDiretor", lancamentoAssembler.getDiretorFinanceiroVO()==null?"":lancamentoAssembler.getDiretorFinanceiroVO().getNome());

		request.getRequestDispatcher("/financeiro/lancamentoPrint.jsp").forward(request, response);
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