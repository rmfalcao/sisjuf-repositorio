package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

import com.vortice.view.BasePageBean;

/**
 *  Classe que implementa o "bean" (JSF) de sincronização para a entidade origem lançamento.
 * @author Paulo Prado
 */
public class OrigemLancamentoPageBean extends BasePageBean {
	
	/**
	 * Classe que implementa o padrão "proxy" para comunicação com o "façade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Representa o encapsulamento dos dados de negócio da entidade origem lançamento. 
	 */
	private OrigemLancamentoVO origemLancamento;
	
	/**
	 * Coleção de origens lançamentos, encapsulados na classe OrigemLancamentoVO.
	 */
	private Collection origemLancamentos;
	
	/**
	 * Objeto de log (log4j)
	 */
	private transient static Logger LOG = Logger.getLogger(OrigemLancamentoPageBean.class);
	
	/**
	 * Instancia os objetos VO e delegate e invoca o método de consulta de todas as origens de lançamento.
	 */
	public OrigemLancamentoPageBean(){
		try{
			origemLancamento = new OrigemLancamentoVO();
		}catch(Exception e){
			LOG.error("Erro no Construtor de OrigemLancamentoPageBean: ", e);
		}
	}
	
	/**
	 * Salva os dados cadastrais de uma origem de lançamento.
	 * @return Mensagem pós-operação.
	 */
	public String salvar(){
		try{
			if (origemLancamento.getCodigo() != null && origemLancamento.getCodigo().intValue() > 0){
				delegate.updateOrigemLancamento(origemLancamento);
			}else{
				delegate.insertOrigemLancamento(origemLancamento);
			}
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			this.origemLancamentos = delegate.findAllOrigemLancamento();
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
				LOG.debug("Naé que é instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Obtém todas as origens de lançamento a serem exibidos na tela.
	 * @return Mensagem pós-operação.
	 */
	public String consultar(){
		try{
			this.origemLancamentos = delegate.findAllOrigemLancamento();
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
				LOG.debug("Naé que é instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Exclui uma origem de lançamento.
	 * @return Mensagem pós-operação.
	 */
	public String remover(){
		try{
			delegate.removeOrigemLancamento(origemLancamento);
			FacesMessage msgs = new FacesMessage("Registro removido com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			this.origemLancamentos = delegate.findAllOrigemLancamento();
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
				LOG.debug("Naé que é instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Detalha uma origem de lançamento.
	 * @return Mensagem pós-operação.
	 */
	public String carregar(){
		try{
			origemLancamento = delegate.findOrigemLancamentoByPrimaryKey(origemLancamento);
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
				LOG.debug("Naé que é instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}

	/**
	 * Obtém a origem de lançamento no bean para ser utilizado pela interface.
	 * @return origemLancamento presente no bean.
	 */
	public OrigemLancamentoVO getOrigemLancamento() {
		return origemLancamento;
	}

	/**
	 *  Grava no bean a origem de lançamento vinda da interface.
	 * @param origemLancamento Origem de lançamento com atributos preenchidos na interface.
	 */
	public void setOrigemLancamento(OrigemLancamentoVO origemLancamento) {
		this.origemLancamento = origemLancamento;
	}

	/**
	 * Obtém coleção de origens de lançamento no bean para ser utilizado pela interface.
	 * @return Coleção de origemLancamento presente no bean.
	 */
	public Collection getOrigemLancamentos() {
		if (origemLancamentos == null){
			this.consultar();
		}
		return origemLancamentos;
	}

	/**
	 * Grava no bean a coleção de origens de lançamento vinda da interface.
	 * @param origemLancamentos Coleção de origens de lançamento com atributos preenchidos na interface.
	 */
	public void setOrigemLancamentos(Collection origemLancamentos) {
		this.origemLancamentos = origemLancamentos;
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