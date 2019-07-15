package br.org.asserjuf.sisjuf.associados.cliente.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.cliente.AssociadoDelegate;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculadoPlanoAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.seguranca.vo.UsuarioVO;

/**
 * Servlet da tela de impress?o de carteirinhas.
 * @author Rodrigo Falc?o
 *
 */
public class HistoricoVinculacoesImpressaoAC extends FrameworkServlet {
	
	protected AssociadoDelegate associadoDelegate;
	protected ConvenioDelegate convenioDelegate;
	protected UtilDelegate utilDelegate;
	
	/**
	 * Representa a implementa??o do corpo de execu??o da servlet. Instancia o delegate e seta os valores necess?rios no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		VinculadoPlanoAssembler historicoVinculacaoFiltro = getHistoricoVinculacao(request);
		Collection<VinculadoPlanoAssembler> listaVinculacoes = associadoDelegate.findHistoricoVinculadosPlanoByFilter(historicoVinculacaoFiltro);		
		setValue(request, historicoVinculacaoFiltro, listaVinculacoes);
		request.getRequestDispatcher("/associados/historicoVinculacaoPrint.jsp").forward(request, response);
	}

	protected void setValue(HttpServletRequest request,VinculadoPlanoAssembler historicoVinculacaoFiltro,Collection<VinculadoPlanoAssembler> listaVinculacoes) throws SmartEnvException, SmartAppException {
		
		request.setAttribute("listaVinculacoes", listaVinculacoes);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		request.setAttribute("dataAtual", formatter.format(utilDelegate.getCurrentDate()));
		
		UsuarioVO usuarioVO = (UsuarioVO)  request.getSession().getAttribute("usuario");
		request.setAttribute("nomeUsuario", usuarioVO.getNome());
		
		PlanoConvenioVO planosConvenio = new PlanoConvenioVO();
		if(historicoVinculacaoFiltro.getPlano().getCodigo() != null){
			planosConvenio = convenioDelegate.findPlanoConvenioByPrimaryKey(historicoVinculacaoFiltro.getPlano());
		}
		request.setAttribute("plano",getValue(planosConvenio.getNome()));
		
		ConvenioVO convenio = new ConvenioVO();
		if(historicoVinculacaoFiltro.getPlano().getConvenio().getCodigo() != null){
			convenio = convenioDelegate.findConvenioByPrimaryKey(historicoVinculacaoFiltro.getPlano().getConvenio());
		}
		request.setAttribute("convenio",getValue(convenio.getNomeFantasia()));		
		
		AssociadoVO associado = associadoDelegate.findAssociadoByPrimaryKey(historicoVinculacaoFiltro.getAssociado());
		request.setAttribute("associado",getValue(associado.getNome()));
		
		if(historicoVinculacaoFiltro.getAssociadoDependente().getCodigo() != null){
			associado = associadoDelegate.findAssociadoByPrimaryKey(historicoVinculacaoFiltro.getAssociadoDependente());
			request.setAttribute("beneficiario",getValue(convenio.getNomeFantasia()));	
		}
		
	}
	
	protected VinculadoPlanoAssembler getHistoricoVinculacao(HttpServletRequest request) throws ParseException, SmartEnvException, SmartAppException {
		
		 VinculadoPlanoAssembler historicoVinculacaoFiltro = new VinculadoPlanoAssembler();
		 historicoVinculacaoFiltro.setAssociado(new AssociadoVO());
		 historicoVinculacaoFiltro.getAssociado().setCodigo(Integer.valueOf(request.getParameter("associado")));
		 historicoVinculacaoFiltro.setAssociadoDependente(new AssociadoVO());
		 historicoVinculacaoFiltro.setPlano(new PlanoConvenioVO());
		 historicoVinculacaoFiltro.getPlano().setConvenio(new ConvenioVO());
		 
		 String valorParameter = getValueFromParameter(request, "pessoa");
		 if(valorParameter != null){
			 historicoVinculacaoFiltro.getAssociadoDependente().setCodigo(Integer.valueOf(valorParameter));
		 }
		 valorParameter = getValueFromParameter(request, "convenio");
		 if(valorParameter != null){
			 historicoVinculacaoFiltro.getPlano().getConvenio().setCodigo(Integer.valueOf(valorParameter));
		 }
		 valorParameter = getValueFromParameter(request, "plano");
		 if(valorParameter != null){
			 historicoVinculacaoFiltro.getPlano().setCodigo(Integer.valueOf(valorParameter));
		 }
		 
		 return historicoVinculacaoFiltro;
	}
	
	private String getValue(Object object){
		if(object != null){
			return object.toString();
		}
		return "";
	}
	
	private String getValueFromParameter(HttpServletRequest request,String nameParameter){
		if(nameParameter != null && !"".equals(nameParameter)){
			String valor = request.getParameter(nameParameter);
			if(valor != null && !"".equals(valor) && !"undefined".equals(valor)){
				return valor;
			}
		}
		return null;
	}

	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		
		associadoDelegate = (AssociadoDelegate)context.getBean("associadoDelegate");
		convenioDelegate = (ConvenioDelegate)context.getBean("convenioDelegate");
		utilDelegate = (UtilDelegate)context.getBean("utilDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}

}