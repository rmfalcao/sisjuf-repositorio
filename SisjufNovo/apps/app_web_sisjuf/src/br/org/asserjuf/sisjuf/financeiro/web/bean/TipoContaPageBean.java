package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.TipoContaVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

import com.vortice.view.BasePageBean;

/**
 *  Classe que implementa o "bean" (JSF) de sincroniza��o para a entidade tipo de conta.
 * @author Paulo Prado
 */
public class TipoContaPageBean extends BasePageBean {
	
	/**
	 * Classe que implementa o padr�o "proxy" para comunica��o com o "fa�ade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Representa o encapsulamento dos dados de neg�cio da entidade tipo de conta. 
	 */
	private TipoContaVO tipoConta;
	
	/**
	 * Cole��o de tipos de conta, encapsulados na classe TipoContaVO.
	 */
	private Collection tiposContas;
	
	/**
	 * Objeto de log (log4j)
	 */
	private static transient Logger LOG = Logger.getLogger(TipoContaPageBean.class);
	
	/**
	 * Instancia os objetos VO e delegate.
	 */
	public TipoContaPageBean(){
		try{
			tipoConta = new TipoContaVO();
			tiposContas = new ArrayList();
		}catch(Exception e){
			 tratarExcecao(e);
		 }
	}
	
	/**
	 * Salva os dados cadastrais de um tipo de conta.
	 * @return Mensagem p�s-opera��o.
	 */
	public String salvar(){
		try{
			
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(Exception e){
			if (e instanceof SmartAppException){
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Obt�m todas os tipos de contas a serem exibidos na tela.
	 * @return Mensagem p�s-opera��o.
	 */
	public String consultar(){
		try{
			this.tiposContas = delegate.findAllTipoConta(); 
			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			if (e instanceof SmartAppException){
				LOG.debug("Na� que � instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Exclui um tipo de conta.
	 * @return Mensagem p�s-opera��o.
	 */
	public String remover(){
		try{
			FacesMessage msgs = new FacesMessage("Registro removido com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(Exception e){
			if (e instanceof SmartAppException){
				LOG.debug("Na� que � instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Detalha um tipo de conta.
	 * @return Mensagem p�s-opera��o.
	 */
	public String carregar(){
		try{
			return getSucesso();
		}catch(Exception e){
			if (e instanceof SmartAppException){
				LOG.debug("Na� que � instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}

	/**
	 * Obt�m o tipo de conta no bean para ser utilizado pela interface.
	 * @return tipoConta presente no bean.
	 */
	public TipoContaVO getTipoConta() {
		return tipoConta;
	}

	/**
	 *  Grava no bean o tipo de conta vindo da interface.
	 * @param tipoConta Tipo de conta com atributos preenchidos na interface.
	 */
	public void setTipoConta(TipoContaVO tipoConta) {
		this.tipoConta = tipoConta;
	}

	/**
	 * Obt�m cole��o de tipos de conta no bean para ser utilizado pela interface.
	 * @return Cole��o de tipoConta presente no bean.
	 */
	public Collection getTiposContas() {
		return tiposContas;
	}

	/**
	 * Grava no bean a cole��o de tipos de contas vinda da interface.
	 * @param tiposContas Cole��o de tipos de conta com atributos preenchidos na interface.
	 */
	public void setTiposContas(Collection tiposContas) {
		this.tiposContas = tiposContas;
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