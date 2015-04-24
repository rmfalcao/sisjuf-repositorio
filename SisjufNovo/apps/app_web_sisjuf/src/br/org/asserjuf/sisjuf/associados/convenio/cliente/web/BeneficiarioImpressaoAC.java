package br.org.asserjuf.sisjuf.associados.convenio.cliente.web;

import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import com.vortice.seguranca.vo.UsuarioVO;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

/**
 * Servlet da tela de impress?o de carteirinhas.
 * @author Rodrigo Falc?o
 *
 */
public class BeneficiarioImpressaoAC extends FrameworkServlet {

	private ConvenioDelegate convenioDelegate;
	private UtilDelegate utilDelegate;
	
	/**
	 * Representa a implementa??o do corpo de execu??o da servlet. Instancia o delegate e seta os valores necess?rios no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{

		ConvenioVO convenio = new ConvenioVO();
		convenio.setCodigo(Integer.valueOf(request.getParameter("codigoConvenio")));
		BeneficiarioVO beneficiario = new BeneficiarioVO();
		beneficiario.setPlano(new PlanoConvenioVO());
		beneficiario.getPlano().setConvenio(convenio);
		beneficiario.getPlano().setCodigo(Integer.valueOf((request.getParameter("plano"))));
		
		Collection<BeneficiarioVO> listaBeneficiarios = convenioDelegate.findBeneficiariosByFilter(beneficiario);
		
		request.setAttribute("listaBeneficiarios", listaBeneficiarios);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		request.setAttribute("dataAtual", formatter.format(utilDelegate.getCurrentDate()));
		UsuarioVO usuarioVO = (UsuarioVO)  request.getSession().getAttribute("usuario");
		request.setAttribute("nomeUsuario", usuarioVO.getNome());
		
		
		request.getRequestDispatcher("/associados/convenio/beneficiario/beneficiariosPrint.jsp").forward(request, response);
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