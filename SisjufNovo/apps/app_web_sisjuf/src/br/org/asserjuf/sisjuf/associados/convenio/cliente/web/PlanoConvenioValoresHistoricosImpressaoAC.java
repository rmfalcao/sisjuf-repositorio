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
public class PlanoConvenioValoresHistoricosImpressaoAC extends PlanoConvenioImpressaoAC {
	
	/**
	 * Representa a implementa??o do corpo de execu??o da servlet. Instancia o delegate e seta os valores necess?rios no request.
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlanoConvenioVO planoConvenio = getPlanoConvenio(request);
		Collection<PlanoConvenioVO> listaPlanos = convenioDelegate.findHistoricoValorPlanoByPlano(planoConvenio);		
		setValue(request, planoConvenio, listaPlanos);
		request.getRequestDispatcher("/associados/convenio/plano/planoConvenioValoresHistoricosPrint.jsp").forward(request, response);
	}

}