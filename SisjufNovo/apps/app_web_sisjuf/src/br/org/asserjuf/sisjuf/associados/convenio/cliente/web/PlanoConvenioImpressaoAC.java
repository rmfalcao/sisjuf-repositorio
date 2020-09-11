package br.org.asserjuf.sisjuf.associados.convenio.cliente.web;

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
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.seguranca.vo.UsuarioVO;

/**
 * Servlet da tela de impress?o de carteirinhas.
 * @author Rodrigo Falc?o
 *
 */
public class PlanoConvenioImpressaoAC extends FrameworkServlet {

	protected ConvenioDelegate convenioDelegate;
	protected UtilDelegate utilDelegate;
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Representa a implementa??o do corpo de execu??o da servlet. Instancia o delegate e seta os valores necess?rios no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlanoConvenioVO planoConvenio = getPlanoConvenio(request);
		Collection<PlanoConvenioVO> listaPlanos = convenioDelegate.findPlanoConvenioByFilter(planoConvenio);		
		setValue(request, planoConvenio, listaPlanos);
		request.getRequestDispatcher("/associados/convenio/plano/planoConvenioPrint.jsp").forward(request, response);
	}

	protected void setValue(HttpServletRequest request,PlanoConvenioVO planosConvenio,Collection<PlanoConvenioVO> listaPlanos) throws SmartEnvException, SmartAppException {
		request.setAttribute("listaPlanos", listaPlanos);
		request.setAttribute("dataAtual", formatter.format(utilDelegate.getCurrentDate()));
		UsuarioVO usuarioVO = (UsuarioVO)  request.getSession().getAttribute("usuario");
		request.setAttribute("nomeUsuario", usuarioVO.getNome());
		request.setAttribute("plano",getValue(planosConvenio.getNome()));
		request.setAttribute("convenio",getValue(planosConvenio.getConvenio().getNomeFantasia()));
		
	}
	
	protected String getValue(Object object){
		if(object != null){
			return object.toString();
		}
		return "";
	}

	protected PlanoConvenioVO getPlanoConvenio(HttpServletRequest request) throws ParseException, SmartEnvException, SmartAppException {
		
		ConvenioVO convenio = new ConvenioVO();
		convenio.setCodigo(Integer.valueOf(request.getParameter("codigoConvenio")));
		convenio = convenioDelegate.findConvenioByPrimaryKey(convenio);
		
		PlanoConvenioVO planosConvenio = new PlanoConvenioVO();
		
		
		String valorParameter = getValueFromParameter(request, "plano");
		if(valorParameter != null){
			planosConvenio.setCodigo(Integer.valueOf(valorParameter));	
			planosConvenio = convenioDelegate.findPlanoConvenioByPrimaryKey(planosConvenio);
		}
		planosConvenio.setConvenio(convenio);
		return planosConvenio;
	}
	
	protected String getValueFromParameter(HttpServletRequest request,String nameParameter){
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
		
		convenioDelegate = (ConvenioDelegate)context.getBean("convenioDelegate");
		utilDelegate = (UtilDelegate)context.getBean("utilDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
}