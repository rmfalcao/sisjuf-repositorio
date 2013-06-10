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
 *  Classe que implementa o "bean" (JSF) de sincroniza��o para a entidade origem lan�amento.
 * @author Paulo Prado
 */
public class OrigemLancamentoPageBean extends BasePageBean {
	
	/**
	 * Classe que implementa o padr�o "proxy" para comunica��o com o "fa�ade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Representa o encapsulamento dos dados de neg�cio da entidade origem lan�amento. 
	 */
	private OrigemLancamentoVO origemLancamento;
	
	/**
	 * Cole��o de origens lan�amentos, encapsulados na classe OrigemLancamentoVO.
	 */
	private Collection origemLancamentos;
	
	/**
	 * Objeto de log (log4j)
	 */
	private transient static Logger LOG = Logger.getLogger(OrigemLancamentoPageBean.class);
	
	/**
	 * Instancia os objetos VO e delegate e invoca o m�todo de consulta de todas as origens de lan�amento.
	 */
	public OrigemLancamentoPageBean(){
		try{
			origemLancamento = new OrigemLancamentoVO();
		}catch(Exception e){
			LOG.error("Erro no Construtor de OrigemLancamentoPageBean: ", e);
		}
	}
	
	/**
	 * Salva os dados cadastrais de uma origem de lan�amento.
	 * @return Mensagem p�s-opera��o.
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
	 * Obt�m todas as origens de lan�amento a serem exibidos na tela.
	 * @return Mensagem p�s-opera��o.
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
	 * Exclui uma origem de lan�amento.
	 * @return Mensagem p�s-opera��o.
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
	 * Detalha uma origem de lan�amento.
	 * @return Mensagem p�s-opera��o.
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
	 * Obt�m a origem de lan�amento no bean para ser utilizado pela interface.
	 * @return origemLancamento presente no bean.
	 */
	public OrigemLancamentoVO getOrigemLancamento() {
		return origemLancamento;
	}

	/**
	 *  Grava no bean a origem de lan�amento vinda da interface.
	 * @param origemLancamento Origem de lan�amento com atributos preenchidos na interface.
	 */
	public void setOrigemLancamento(OrigemLancamentoVO origemLancamento) {
		this.origemLancamento = origemLancamento;
	}

	/**
	 * Obt�m cole��o de origens de lan�amento no bean para ser utilizado pela interface.
	 * @return Cole��o de origemLancamento presente no bean.
	 */
	public Collection getOrigemLancamentos() {
		if (origemLancamentos == null){
			this.consultar();
		}
		return origemLancamentos;
	}

	/**
	 * Grava no bean a cole��o de origens de lan�amento vinda da interface.
	 * @param origemLancamentos Cole��o de origens de lan�amento com atributos preenchidos na interface.
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