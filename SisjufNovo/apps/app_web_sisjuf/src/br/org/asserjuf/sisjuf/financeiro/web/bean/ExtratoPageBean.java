package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.ExtratoAssembler;
import br.org.asserjuf.sisjuf.financeiro.ExtratoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

import com.vortice.view.BasePageBean;

/**
 *  Classe que implementa o "bean" (JSF) de sincroniza��o para a opera��o de extrato.
 * @author Paulo Prado
 *
 */
public class ExtratoPageBean extends BasePageBean {
	
	/**
	 * Classe que implementa o padr�o "proxy" para comunica��o com o "fa�ade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Objeto de log (log4j)
	 */
	private transient static Logger LOG = Logger.getLogger(ExtratoPageBean.class);
	
	/**
	 * Representa o encapsulamento dos dados a serem exibidos no extrato. 
	 */
	private ExtratoAssembler extrato;
	
	/**
	 * Representa o encapsulamento dos dados a serem usados como filtro na emiss�o do extrato. 
	 */
	private ExtratoFiltroAssembler filtro;
	
	/**
	 * Cole��o de contas, encapsulados na classe BancoVO.
	 */
	private Collection contas;
	
	/**
	 * Instancia o delegate e invoca o m�todo de consulta de todas as contas.
	 *
	 */
	public ExtratoPageBean(){
		inicializaFiltro();
	}
	
	/**
	 * Instancia o filtro de extrato.
	 */
	private void inicializaFiltro(){
		filtro = new ExtratoFiltroAssembler();
		filtro.setContaVO(new ContaVO());
	}
	
	/**
 	* Obt�m o extrato para conta informada e no per�odo informado.
 	* @return Mensagem p�s-opera��o.
 	*/
	public String consultar(){
		try{
			extrato = delegate.obterExtratoConta(filtro);
			LOG.debug("Extrato " + extrato);
			LOG.debug("BaixaLancamento " + extrato.getBaixaLancamento());
			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error SmartAppException", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error SmartEnvException", envEx);
			return "falha";
		}catch(Exception e){
	        LOG.error("Exception ", e);
	        String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}

	/**
	 *  Obt�m o encapsulamento com os dados do extrato no bean para serem utilizados na interface.
	 * @return Encapsulamento das informa��es do extrato
	 */
	public ExtratoAssembler getExtrato() {
		return extrato;
	}

	/**
	 * Grava no bean as informa��es do extrato vindas da interface.
	 * @param extrato Encapsulamento das informa��es do extrato
	 */
	public void setExtrato(ExtratoAssembler extrato) {
		this.extrato = extrato;
	}

	/**
	 *  Obt�m o encapsulamento com os dados do filtro do extrato no bean para serem utilizados na interface.
	 * @return Encapsulamento das informa��es do filtro do extrato
	 */
	public ExtratoFiltroAssembler getFiltro() {
		return filtro;
	}

	/**
	 * Grava no bean os dados do filtro do extrato vindas da interface. 
	 * @param filtro
	 */
	public void setFiltro(ExtratoFiltroAssembler filtro) {
		this.filtro = filtro;
	}

	/**
	 * Obt�m cole��o de contas no bean para ser utilizada pela interface.
	 * @return Cole��o de contas presente no bean.
	 */
	public Collection getContas() {
		if (contas == null){
			try {
				Collection collContas = delegate.findAllConta();
				contas = (collContas != null) ? getSelect(collContas, "codigo", "nomeDefault") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR AS CONTAS", e);
			}
		}
		return contas;
	}

	/**
	 * Grava no bean a cole��o de contas  vinda da interface.
	 */
	public void setContas(Collection contas) {
		this.contas = contas;
	}

	public void setDelegate(FinanceiroDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		return false;
	}

	@Override
	protected void sort(String column, boolean ascending) {
	}
}