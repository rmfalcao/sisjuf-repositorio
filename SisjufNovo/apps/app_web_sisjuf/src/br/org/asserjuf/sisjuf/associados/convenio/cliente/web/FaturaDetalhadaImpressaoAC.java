package br.org.asserjuf.sisjuf.associados.convenio.cliente.web;

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
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.seguranca.vo.UsuarioVO;

/**
 * Servlet da tela de impress?o de carteirinhas.
 * @author Rodrigo Falc?o
 *
 */
public class FaturaDetalhadaImpressaoAC extends FrameworkServlet {

	private ConvenioDelegate convenioDelegate;
	private UtilDelegate utilDelegate;
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Representa a implementa??o do corpo de execu??o da servlet. Instancia o delegate e seta os valores necess?rios no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		FaturaVO fatura = getFatura(request);
		setValue(request, fatura);
		request.getRequestDispatcher("/associados/convenio/faturaDetalhadaPrint.jsp").forward(request, response);
	}

	protected void setValue(HttpServletRequest request,FaturaVO fatura) throws SmartEnvException, SmartAppException {
		request.setAttribute("dataAtual", formatter.format(utilDelegate.getCurrentDate()));
		UsuarioVO usuarioVO = (UsuarioVO)  request.getSession().getAttribute("usuario");
		request.setAttribute("nomeUsuario", usuarioVO.getNome());
		request.setAttribute("convenio",getValue(fatura.getConvenio().getNomeFantasia()));
		request.setAttribute("dataInicio",getValue(fatura.getDataInicial()));
		request.setAttribute("dataFim",getValue(fatura.getDataFinal()));
		
		double valorFatura = 0;
		for (ItemFaturaVO item : fatura.getItens()) {
			valorFatura += (item.getValor()==null?0:item.getValor().doubleValue());
		}
		request.setAttribute("valorTotalFatura",valorFatura);
		request.setAttribute("listaFaturaDetalhada",fatura.getItens());
	}
	
	private String getValue(Object object){
		if(object != null){
			return object.toString();
		}
		return "";
	}

	private FaturaVO getFatura(HttpServletRequest request) throws ParseException, SmartEnvException, SmartAppException {
		
		FaturaVO fatura = new FaturaVO();
		fatura.setCodigo(Integer.valueOf(request.getParameter("codigoFatura")));
		fatura = convenioDelegate.findByPrimaryKey(fatura);
		
		return fatura;
	}
	
	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		
		convenioDelegate = (ConvenioDelegate)context.getBean("convenioDelegate");
		utilDelegate = (UtilDelegate)context.getBean("utilDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
}