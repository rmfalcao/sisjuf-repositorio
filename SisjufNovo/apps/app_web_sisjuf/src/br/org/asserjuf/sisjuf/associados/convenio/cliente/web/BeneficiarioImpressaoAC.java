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
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
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
public class BeneficiarioImpressaoAC extends FrameworkServlet {

	private ConvenioDelegate convenioDelegate;
	private UtilDelegate utilDelegate;
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Representa a implementa??o do corpo de execu??o da servlet. Instancia o delegate e seta os valores necess?rios no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		BeneficiarioVO beneficiario = getBeneficiario(request);
		Collection<BeneficiarioVO> listaBeneficiarios = convenioDelegate.findBeneficiariosByFilter(beneficiario);		
		setValue(request, beneficiario, listaBeneficiarios);
		request.getRequestDispatcher("/associados/beneficiario/beneficiarioPrint.jsp").forward(request, response);
	}

	protected void setValue(HttpServletRequest request,BeneficiarioVO beneficiario,Collection<BeneficiarioVO> listaBeneficiarios) throws SmartEnvException, SmartAppException {
		request.setAttribute("listaBeneficiarios", listaBeneficiarios);
		request.setAttribute("dataAtual", formatter.format(utilDelegate.getCurrentDate()));
		UsuarioVO usuarioVO = (UsuarioVO)  request.getSession().getAttribute("usuario");
		request.setAttribute("nomeUsuario", usuarioVO.getNome());
		request.setAttribute("plano",getValue(beneficiario.getPlano().getNome()));
		request.setAttribute("convenio",getValue(beneficiario.getPlano().getConvenio().getNomeFantasia()));
		request.setAttribute("nome",getValue(beneficiario.getTitular().getNome()));
		request.setAttribute("matricula",getValue(beneficiario.getTitular().getMatriculaJustica()));
		request.setAttribute("dataInicio",getValue(beneficiario.getDataInicioVinculacao()));
		request.setAttribute("dataFim",getValue(beneficiario.getDataFimVinculacao()));
	}
	
	private String getValue(Object object){
		if(object != null){
			return object.toString();
		}
		return "";
	}

	private BeneficiarioVO getBeneficiario(HttpServletRequest request) throws ParseException, SmartEnvException, SmartAppException {
		
		ConvenioVO convenio = new ConvenioVO();
		convenio.setCodigo(Integer.valueOf(request.getParameter("codigoConvenio")));
		
		BeneficiarioVO beneficiario = new BeneficiarioVO();
		beneficiario.setPlano(new PlanoConvenioVO());
		convenio = convenioDelegate.findConvenioByPrimaryKey(convenio);
		beneficiario.getPlano().setConvenio(convenio);
		beneficiario.setTitular(new AssociadoVO());
		
		String valorParameter = getValueFromParameter(request, "plano");
		if(valorParameter != null){
			beneficiario.getPlano().setCodigo(Integer.valueOf(valorParameter));	
			beneficiario.setPlano(convenioDelegate.findPlanoConvenioByPrimaryKey(beneficiario.getPlano()));
		}
		valorParameter = getValueFromParameter(request, "nome");
		if(valorParameter != null){
			beneficiario.getTitular().setNome(valorParameter);	
		}
		valorParameter = getValueFromParameter(request, "matricula");
		if(valorParameter != null){
			beneficiario.getTitular().setMatriculaJustica(valorParameter);
		}
		valorParameter = getValueFromParameter(request, "dataInicio");
		if(valorParameter != null){
			beneficiario.setDataInicioVinculacao(formatter.parse(valorParameter));	
		}
		valorParameter = getValueFromParameter(request, "dataFim");
		if(valorParameter != null){
			beneficiario.setDataFimVinculacao(formatter.parse(valorParameter));
		}
		return beneficiario;
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
		
		convenioDelegate = (ConvenioDelegate)context.getBean("convenioDelegate");
		utilDelegate = (UtilDelegate)context.getBean("utilDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
}