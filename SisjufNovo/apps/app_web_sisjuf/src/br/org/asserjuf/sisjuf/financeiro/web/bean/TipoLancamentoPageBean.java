package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

import com.vortice.view.BasePageBean;

/**
 *  Classe que implementa o "bean" (JSF) de sincroniza��o para a entidade tipo de lan�amento.
 * @author Paulo Prado
 */
public class TipoLancamentoPageBean extends BasePageBean {
	
	/**
	 * Classe que implementa o padr�o "proxy" para comunica��o com o "fa�ade" Financeiro.
	 */
	private FinanceiroDelegate delegate;
	
	/**
	 * Representa o encapsulamento dos dados de neg�cio da entidade tipo de lan�amento. 
	 */
	private TipoLancamentoVO tipoLancamento;
	
	/**
	 * Cole��o de tipos de lan�amentos, encapsulados na classe TipoLancamentoVO.
	 */
	private Collection tipoLancamentos;
	
	/**
	 * Objeto de log (log4j)
	 */
	private Logger log = Logger.getLogger(TipoLancamentoPageBean.class);
	
	/**
	 * Instancia os objetos VO e delegate e invoca o m�todo de consulta de todas os tipos de lan�amento.
	 */
	public TipoLancamentoPageBean(){
		try{
			tipoLancamento = new TipoLancamentoVO();
		}catch(Exception e){
			log.error("Erro no Construtor de TipoLancamentoPageBean: ", e);
		}
	}
	
	/**
	 * Salva os dados cadastrais de um tipo de lan�amento.
	 * @return Mensagem p�s-opera��o.
	 */
	public String salvar(){
		try{
			if (tipoLancamento.getCodigo() != null && tipoLancamento.getCodigo().intValue() > 0){
				delegate.updateTipoLancamento(tipoLancamento);
			}else{
				delegate.insertTipoLancamento(tipoLancamento);
			}
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			this.tipoLancamentos = delegate.findAllTipoLancamento();
			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			log.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			log.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			if (e instanceof SmartAppException){
				log.debug("Na� que � instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			log.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Obt�m todas os tipos de lan�amento a serem exibidos na tela.
	 * @return Mensagem p�s-opera��o.
	 */
	public String consultar(){
		try{
			this.tipoLancamentos = delegate.findAllTipoLancamento();
			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			log.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			log.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			if (e instanceof SmartAppException){
				log.debug("Na� que � instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			log.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Exclui um tipo de lan�amento.
	 * @return Mensagem p�s-opera��o.
	 */
	public String remover(){
		try{
			delegate.removeTipoLancamento(tipoLancamento);
			FacesMessage msgs = new FacesMessage("Registro removido com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			this.tipoLancamentos = delegate.findAllTipoLancamento();
			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			log.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			log.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			if (e instanceof SmartAppException){
				log.debug("Na� que � instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			log.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Detalha um tipo de lan�amento.
	 * @return Mensagem p�s-opera��o.
	 */
	public String carregar(){
		try{
			tipoLancamento = delegate.findTipoLancamentoByPrimaryKey(tipoLancamento);
			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			log.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			log.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			if (e instanceof SmartAppException){
				log.debug("Na� que � instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			log.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}

	/**
	 * Obt�m o tipo de lan�amento no bean para ser utilizado pela interface.
	 * @return tipoLancamento presente no bean.
	 */
	public TipoLancamentoVO getTipoLancamento() {
		return tipoLancamento;
	}

	/**
	 *  Grava no bean o tipo de lan�amento vindo da interface.
	 * @param tipoLancamento Tipo de lan�amento com atributos preenchidos na interface.
	 */
	public void setTipoLancamento(TipoLancamentoVO tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	/**
	 * Obt�m cole��o de tipos de lan�amento no bean para ser utilizado pela interface.
	 * @return Cole��o de tipoLancamento presente no bean.
	 */
	public Collection getTipoLancamentos() {
		if (tipoLancamentos == null){
			try {
				tipoLancamentos = delegate.findAllTipoLancamento();
			} catch (Exception e) {
				log.error("ERRO NO MOMENTO DE CARREGAR OS LANCAMENTOS", e);
			}
		}
		return tipoLancamentos;
	}

	/**
	 * Grava no bean a cole��o de tipos de lan�amento vinda da interface.
	 * @param tipoLancamentos Cole��o de tipos de lan�amento com atributos preenchidos na interface.
	 */
	public void setTipoLancamentos(Collection tipoLancamentos) {
		this.tipoLancamentos = tipoLancamentos;
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